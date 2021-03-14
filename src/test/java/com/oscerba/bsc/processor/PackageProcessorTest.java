package com.oscerba.bsc.processor;

import com.oscerba.bsc.exception.InvalidInputFormatException;
import com.oscerba.bsc.pojo.Package;
import com.oscerba.bsc.store.InMemoryPackageStore;
import com.oscerba.bsc.store.InMemoryWeightStore;
import com.oscerba.bsc.store.PackageStore;
import com.oscerba.bsc.store.WeightStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PackageProcessorTest {

	private static WeightStore weightStore;

	@BeforeAll
	public static void init(){
		weightStore = new InMemoryWeightStore();
	}

	@Test
	public void testProcessLine() throws InvalidInputFormatException {
		PackageStore packageStore = new InMemoryPackageStore();
		PackageProcessor packageProcessor = new PackageProcessor(packageStore, weightStore);
		Map.Entry<String, Package> pckgExpected = new AbstractMap.SimpleEntry<>("12345", new Package(12.5, "12345", null));
		packageProcessor.processLine("12.5 12345");
		assertThat(packageStore.getPackages()).containsExactly(pckgExpected);
	}

	@Test
	public void testProcessLineThrowsExceptionWhenPostalCodeIsShorterThan5Digits() {
		PackageStore packageStore = new InMemoryPackageStore();
		PackageProcessor packageProcessor = new PackageProcessor(packageStore, weightStore);
		assertThatThrownBy(() -> packageProcessor.processLine("12.5 1234")).isInstanceOf(
				InvalidInputFormatException.class
		);
	}
}
