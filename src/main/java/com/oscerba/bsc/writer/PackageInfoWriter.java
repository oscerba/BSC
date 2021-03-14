package com.oscerba.bsc.writer;

import com.oscerba.bsc.store.PackageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * Class representing writer which will write info about currently stored packages their weight and prices
 */
public class PackageInfoWriter implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PackageInfoWriter.class);
	private final PackageStore packageStore;

	public PackageInfoWriter(PackageStore packageStore) {
		this.packageStore = packageStore;
	}

	@Override
	public void run() {
		logger.info(packageStore.getPackages().values().stream()
						.map(pckg -> String.format("%s %.3f %.2f", pckg.getPostalCode(), pckg.getWeight(), pckg.getPrice()))
						.collect(Collectors.joining("\n")));
	}
}
