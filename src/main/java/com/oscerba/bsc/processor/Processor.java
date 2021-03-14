package com.oscerba.bsc.processor;

import com.oscerba.bsc.exception.InvalidInputFormatException;

public abstract class Processor {
	public abstract void processLine(String line) throws InvalidInputFormatException;
}
