package com.oscerba.bsc.store;

import com.oscerba.bsc.pojo.Weight;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Class represetning in memory store of weight and their prces
 **/
public class InMemoryWeightStore extends WeightStore {

	private final NavigableMap<Double, Weight> weights = new TreeMap<>();

	@Override
	public void addWeight(Weight weight) {
		weights.put(weight.getWeight(), weight);
	}

	@Override
	public NavigableMap<Double, Weight> getWeights() {
		return weights;
	}
}
