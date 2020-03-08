package com.github.ibiber.wakeonlan;

import java.util.regex.Pattern;

/**
 * Valid MAC Addresses are: 00:80:41:ae:fd:7e or 00-80-41-ae-fd-7e
 */
public class MacAddressInputValidator {
	private final static String VALID_MAC_EXAMPLE = "00:80:41:ae:fd:7e";
	private final static Pattern macAddressRegex = Pattern.compile("^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$", Pattern.CASE_INSENSITIVE);

	public void validateMacAddress(String macAddress) {
		if (macAddress == null) {
			throw new IllegalArgumentException(
					addDefaultErrorMessage("No MAC address defined. MAC address is mandatory."));
		}

		if (macAddress.length() != 17) {
			throw new IllegalArgumentException(addDefaultErrorMessage("Length of MAC address does not match."));

		}

		if (!macAddressRegex.matcher(macAddress).matches()) {
			throw new IllegalArgumentException(
					addDefaultErrorMessage("Given MAC address '" + macAddress + "' has the wrong format."));
		}
	}

	private String addDefaultErrorMessage(String error) {
		return error + " A valid MAC address would look like: " + VALID_MAC_EXAMPLE;
	}
}
