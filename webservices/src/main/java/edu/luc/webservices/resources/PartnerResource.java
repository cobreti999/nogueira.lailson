/**
 *
 */
package edu.luc.webservices.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	@Produces(MediaType.TEXT_HTML)
	public String findByName(@PathParam("login") String name) {
		String ret;
		Partner c = partnerActivity.findPartnerByName(name);
		if (c != null){
			ret = c.toString();
		}else{
			ret = "failed to find partner with name: " + name;
		}
		partnerActivity.closeConnection();
		return ret;
	}
	
	@GET
	@Path("/findAllPartners")
	@Produces(MediaType.TEXT_HTML)
	public String findAllPartners() {
		String partnerString= "";
		List<Partner> partners = partnerActivity.findAllPartners();
		
		if (partners == null){
			partnerString = "There are no partners registered";
		}else if (partners.size() == 0){
			partnerString = "There are no partners registered";
		}else{
			for (int i = 0; i < partners.size(); i++) {
				partnerString  = partnerString + partners.get(i);
				partnerString += "<br>";
			}
		}
		partnerActivity.closeConnection();
		return partnerString;
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
