package com.github.ibiber.wakeonlan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class WakeOnLanService {
	private static final int PORT = 9;
	private static final Pattern macSplitPattern = Pattern.compile("(\\:|\\-)");

	public void sendMagicPacket(String broadcastIp, String macAddress) throws IOException {
		byte[] mac = transformMacAddressToBytes(macAddress);
		byte[] magicPacket = generateMagicPacketData(mac);

		sendMagicPacket(broadcastIp, magicPacket);
	}

	private void sendMagicPacket(String broadcastIp, byte[] magicPacket) throws IOException {
		try (DatagramSocket socket = new DatagramSocket()) {
			InetAddress address = InetAddress.getByName(broadcastIp);
			DatagramPacket packet = new DatagramPacket(magicPacket, magicPacket.length, address, PORT);
			socket.setBroadcast(true);
			socket.send(packet);
		}
	}

	private byte[] generateMagicPacketData(byte[] mac) {
		byte[] bytes = new byte[6 + 16 * mac.length];

		for (int i = 0; i < 6; i++) {
			bytes[i] = (byte) 0xff;
		}
		for (int i = 6; i < bytes.length; i += mac.length) {
			System.arraycopy(mac, 0, bytes, i, mac.length);
		}

		return bytes;
	}

	private byte[] transformMacAddressToBytes(String macStr) throws IllegalArgumentException {
		String[] hex = macSplitPattern.split(macStr);

		byte[] resultBytes = new byte[6];
		for (int i = 0; i < 6; i++) {
			resultBytes[i] = (byte) Integer.parseInt(hex[i], 16);
		}

		return resultBytes;
	}
}
