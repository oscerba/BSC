package com.oscerba.bsc.processor;

import com.oscerba.bsc.exception.InvalidInputFormatException;
import com.oscerba.bsc.pojo.Package;
import com.oscerba.bsc.store.PackageStore;
import com.oscerba.bsc.store.WeightStore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing processor of input for packages
 */
public class PackageProcessor extends Processor {

	private final PackageStore packageStore;
	private final WeightStore weightStore;

	private final Pattern linePattern = Pattern.compile("(\\d*\\.?\\d{1,3}) (\\d{5})");

	public PackageProcessor(PackageStore packageStore, WeightStore weightStore) {
		this.packageStore = packageStore;
		this.weightStore = weightStore;
	}

	@Override
	public void processLine(String line) throws InvalidInputFormatException {
		Matcher matcher = linePattern.matcher(line);
		if (matcher.matches()) {
			double weight = Double.parseDouble(matcher.group(1));
			String postalCode = matcher.group(2);

			Double aDouble = weightStore.getWeights().floorKey(weight);

			packageStore.addPackage(new Package(weight, postalCode, aDouble));
		} else {
			throw new InvalidInputFormatException(String.format("Line [%s] not in valid format", line));
		}
	}
}
