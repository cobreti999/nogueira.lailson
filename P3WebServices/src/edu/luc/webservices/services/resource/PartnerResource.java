/**
 *
 */
package edu.luc.webservices.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.webservices.model.Partner;
import edu.luc.webservices.services.PartnerServiceInterface;
import edu.luc.webservices.services.exception.InvalidAddressException;
import edu.luc.webservices.services.workflow.PartnerActivity;



@Path("/partners")
public class PartnerResource implements PartnerServiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PartnerActivity partnerActivity = new PartnerActivity();

	@GET
	@Path("/{login}")
	@Produces({ "application/json" })
	public Response findByName(@PathParam("login") String name) {
		Partner partner = null;
		Response response = null;

		if (null != name) {
			partner = partnerActivity.findPartnerByName(name);
			if (null != partner)
				response = Response.ok().entity(partner).build();
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}
	
	@GET
	@Path("/findAllPartners")
	@Produces({ "application/json" })
	public Response findAllPartners() {
		List<Partner> partners = null;
		Response response = null;
		partners = partnerActivity.findAllPartners();
		if (null != partners)
			response = Response.ok().entity(partners).build();
		return response;
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response create(Partner partner) {
		Response response = null;
		try {
			partner = partnerActivity.create(partner);
			response = Response
					.created(URI.create("/partners/" + partner.getPartnerName()))
					.entity(partner).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@PUT
	@Path("/{partnerId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(Partner partner) {
		Response response = null;
		try {
			partner = partnerActivity.update(partner);
			response = Response.ok().entity(partner).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
}
