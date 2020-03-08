package com.github.ibiber.wakeonlan;

import java.util.regex.Pattern;

/**
 * Validate IPV4 addresses
 */
public class BroadcastIpV4InputValidator {
	private final static String VALID_IP_EXAMPLE = "192.168.0.255";
	private final static Pattern ipRegex = Pattern
			.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

	public void validateIp(String ip) {
		if (ip == null) {
			throw new IllegalArgumentException(
					addDefaultErrorMessage("No broadcast IP address defined. A broadcast IP address is mandatory."));
		}

		if (!ipRegex.matcher(ip).matches()) {
			throw new IllegalArgumentException(
					addDefaultErrorMessage("Given broadcast IP address '" + ip + "' has the wrong format."));
		}
	}

	private String addDefaultErrorMessage(String error) {
		return error + " A valid broadcast IP address would look like: " + VALID_IP_EXAMPLE;
	}
}
