package com.oscerba.bsc.store;

import com.oscerba.bsc.pojo.Package;

import java.util.Map;

public abstract class PackageStore {
	public abstract void addPackage(Package pckg);

	public abstract Map<String, Package> getPackages();
}
