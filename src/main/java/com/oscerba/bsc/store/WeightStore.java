package com.oscerba.bsc.store;

import com.oscerba.bsc.pojo.Weight;

import java.util.NavigableMap;

public abstract class WeightStore {
	public abstract void addWeight(Weight weight);

	public abstract NavigableMap<Double, Weight> getWeights();
}
