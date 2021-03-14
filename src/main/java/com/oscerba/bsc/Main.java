package com.oscerba.bsc;

import com.oscerba.bsc.exception.InvalidInputFormatException;
import com.oscerba.bsc.processor.PackageProcessor;
import com.oscerba.bsc.processor.Processor;
import com.oscerba.bsc.processor.WeightProcessor;
import com.oscerba.bsc.store.InMemoryPackageStore;
import com.oscerba.bsc.store.InMemoryWeightStore;
import com.oscerba.bsc.store.PackageStore;
import com.oscerba.bsc.store.WeightStore;
import com.oscerba.bsc.writer.PackageInfoWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static final String QUIT_COMMAND = "quit";


	public static void main(String[] args) {

		PackageStore packageStore = new InMemoryPackageStore();

		ScheduledExecutorService executorService = null;
		try {
			executorService = Executors.newSingleThreadScheduledExecutor();
			executorService.scheduleAtFixedRate(new PackageInfoWriter(packageStore), 0, 60, TimeUnit.SECONDS);

			WeightStore weightStore = new InMemoryWeightStore();

			if (args.length >= 2) {
				String fileName = args[1];
				processFile(fileName, new WeightProcessor(weightStore));
			}

			PackageProcessor packageProcessor = new PackageProcessor(packageStore, weightStore);
			if (args.length >= 1) {
				String fileName = args[0];
				processFile(fileName, packageProcessor);
			}

			String line;

			try (Scanner scanner = new Scanner(System.in)) {
				while (!(line = scanner.nextLine()).equals(QUIT_COMMAND)) {
					try {
						packageProcessor.processLine(line);
					} catch (InvalidInputFormatException e) {
						logger.error("Invalid format.");
					}
				}
			}
		} finally {
			if (executorService != null) {
				executorService.shutdown();
			}
		}
	}

	private static void processFile(String fileName, Processor weightProcessor) {
		try {
			Stream<String> lines = Files.lines(Path.of(fileName));
			lines.forEach(l -> {
				try {
					weightProcessor.processLine(l);
				} catch (InvalidInputFormatException e) {
					logger.error(String.format("Error during processing line [%s]. Reason: %s.", l, e.getMessage()));
				}
			});
		} catch (IOException e) {
			logger.error(String.format("Error during reading file. Reason: %s", e.getMessage()));
		}
	}
}

