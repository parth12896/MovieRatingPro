package com.util;

public class FeatureCheckUtil {

	public boolean checkStatementHasFeature(String sentence, String feature) {

		boolean hasFeature = false;

		String[] value = sentence.split(" ");

		if (value != null) {

			for (String value1 : value) {

				if (value1 != null && !value1.isEmpty()) {
					value1 = value1.toUpperCase();
				}

				if (value1.contains(feature)) {

					return true;

				}
			}
		}

		return hasFeature;
	}

}
