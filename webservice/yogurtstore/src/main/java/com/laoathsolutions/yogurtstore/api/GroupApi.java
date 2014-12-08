package com.laoathsolutions.yogurtstore.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.laoathsolutions.yogurtstore.api.data.GroupId;
import com.laoathsolutions.yogurtstore.api.data.GroupInfo;

@Path("/api/")
public interface GroupApi {
	@GET
	@Produces("application/xml")
	@Path("join/{userId}")
	public GroupId join(@PathParam("userId") String userId);

	@GET
	@Produces("application/xml")
	@Path("groupinfo/{groupId}")
	public GroupInfo groupInfo(@PathParam("groupId") String groupId);
}
