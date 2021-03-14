package com.oscerba.bsc.store;

import com.oscerba.bsc.pojo.Package;

import java.util.HashMap;
import java.util.Map;

/**
 * Class represetning in memory store of packages and their weights
 */
public class InMemoryPackageStore extends PackageStore {

	private final Map<String, Package> packages = new HashMap<>();

	@Override
	public void addPackage(Package pckg) {
		String postalCode = pckg.getPostalCode();
		double weight = pckg.getWeight();
		Double price = pckg.getPrice();
		Package newPackage;
		if (packages.containsKey(postalCode)) {
			double newWeight = packages.get(postalCode).getWeight() + weight;
			newPackage = new Package(newWeight, postalCode, price);
		} else {
			newPackage = new Package(weight, postalCode, price);
		}

		packages.put(postalCode, newPackage);
	}

	@Override
	public Map<String, Package> getPackages() {
		return packages;
	}
}
