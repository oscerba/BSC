package com.oscerba.bsc.processor;

import com.oscerba.bsc.exception.InvalidInputFormatException;
import com.oscerba.bsc.pojo.Weight;
import com.oscerba.bsc.store.WeightStore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing prcessor for input of weights and their prices
 */
public class WeightProcessor extends Processor {

	private final WeightStore weightStore;

	private final Pattern linePattern = Pattern.compile("(\\d*\\.?\\d{1,3}) (\\d*\\.?\\d{2})");

	public WeightProcessor(WeightStore weightStore) {
		this.weightStore = weightStore;
	}

	@Override
	public void processLine(String line) throws InvalidInputFormatException {
		Matcher matcher = linePattern.matcher(line);
		if (matcher.matches()) {
			Double weight = Double.valueOf(matcher.group(1));
			double price = Double.parseDouble(matcher.group(2));
			weightStore.addWeight(new Weight(weight, price));
		} else {
			throw new InvalidInputFormatException(String.format("Line [%s] not in valid format", line));
		}
	}
}
