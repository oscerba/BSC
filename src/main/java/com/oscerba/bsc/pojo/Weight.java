package com.oscerba.bsc.pojo;

/**
 * Class representing pricing for weight pf the packages.
 */
public class Weight {
	private final Double weight;
	private final double price;

	public Weight(Double weight, double price) {
		this.weight = weight;
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public double getPrice() {
		return price;
	}
}
