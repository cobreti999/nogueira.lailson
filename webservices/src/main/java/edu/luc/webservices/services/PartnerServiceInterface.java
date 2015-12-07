package edu.luc.webservices.services;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import edu.luc.webservices.model.Partner;

public interface PartnerServiceInterface extends Serializable {
	
	public Response findByName(String login);

	public Response create(Partner partner);

	public Response update(Partner partner);
	
	public Response findAllPartners();

}
