package com.oscerba.bsc.pojo;

import java.util.Objects;

/**
 * Class representing package with postal code, weight and the price.
 */
public class Package {

	private final double weight;
	private final String postalCode;
	private final Double price;

	public Package(double weight, String postalCode, Double price) {
		this.weight = weight;
		this.postalCode = postalCode;
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Package{" +
						"weight=" + getWeight() +
						", postalCode='" + postalCode + '\'' +
						", price=" + getPrice() +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Package aPackage = (Package) o;
		return Double.compare(aPackage.weight, weight) == 0 && postalCode.equals(aPackage.postalCode) && Objects.equals(price, aPackage.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(weight, postalCode, price);
	}
}
