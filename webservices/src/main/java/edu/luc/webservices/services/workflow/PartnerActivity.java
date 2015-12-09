package edu.luc.webservices.services.workflow;
import edu.luc.webservices.model.Partner;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.dao.PartnerDAO;
import edu.luc.webservices.services.exception.InvalidAddressException;

public class PartnerActivity {

	public Partner findPartnerByName(String name) {
		PartnerDAO.session = PartnerDAO.factory.getCurrentSession();
		Partner partner = PartnerDAO.findByName(name);
		setLinks(partner, "get");
		return partner;
	}
	
	public List<Partner> findAllPartners() {
		PartnerDAO.session = PartnerDAO.factory.getCurrentSession();
		List<Partner> partners = PartnerDAO.findAllPartners();
		for (int i=0; i < partners.size(); i++){
			System.out.println(partners.get(i));
			setLinks(partners.get(i), "get");
		}
		return partners;
	}

	public Partner create(Partner partner) throws InvalidAddressException {
		return createOrUpdate(partner);
	}

	public Partner update(Partner partner) throws InvalidAddressException {
		return createOrUpdate(partner);
	}

	public Partner createOrUpdate(Partner partner)
			throws InvalidAddressException {
		validateAddressAndPayment(partner);
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(partner);
			session.getTransaction().commit();
			setLinks(partner, "create");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partner;
	}

	private void validateAddressAndPayment(Partner partner)
			throws InvalidAddressException {
		if (partner == null || partner.getPartnerAddress() == null)
			throw new InvalidAddressException();
	}

	public boolean validateUserAuth(Partner partner) {
		return PartnerDAO.validateUserAuthentication(partner.getPartnerName(),
				partner.getPartnerCredentials().toString());
	}
	
	public void closeConnection(){
		PartnerDAO.session.close();
	}
	
	private void setLinks(Partner partner, String action) {
		Link link = new Link(action, 
			"http://localhost:8080/webservices/webapi/partners/" + partner.getPartnerId());	
		partner.setLinks(link);
	}
}
