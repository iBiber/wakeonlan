package com.github.ibiber.wakeonlan;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/wakeonlan/{broadcastIp}/{macAddress}")
public class WakeOnLanRestController {
	private static final Logger logger = LoggerFactory.getLogger(WakeOnLanRestController.class);

	private static final MacAddressInputValidator macValidator = new MacAddressInputValidator();
	private static final BroadcastIpV4InputValidator ipValidator = new BroadcastIpV4InputValidator();
	private static final WakeOnLanService wolService = new WakeOnLanService();

	@GET
	public Response onRequestToSendWolPacket(@PathParam("broadcastIp") String broadcastIp,
			@PathParam("macAddress") String macAddress) {
		logger.info("Request to send WOL message; broadCastIp={}, MAC address={}", broadcastIp, macAddress);

		try {
			ipValidator.validateIp(broadcastIp);
			macValidator.validateMacAddress(macAddress);
		} catch (Exception e) {
			Response response = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			logger.info("Request aborted: {}", response.toString());
			return response;
		}

		try {
			wolService.sendMagicPacket(broadcastIp, macAddress);
			logger.info("Wake on LAN message sent.");
			return Response.ok("Wake on lan message sent").build();
		} catch (IOException e) {
			throw new IllegalStateException("Sending WOL messag failed", e);
		}
	}
}