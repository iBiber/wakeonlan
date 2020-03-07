package com.github.ibiber.wakeonlan;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/wakeonlan/{macAddress}")
public class WakeOnLanRestController {

    @GET
    public Response getSimpleData(@PathParam("macAddress") String macAddress){
        // TODO: Validate input
    	System.out.println(macAddress);
        
    	// TODO: Send Magic Packet
    	
        return Response.ok().build();
    }
}