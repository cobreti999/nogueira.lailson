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
		return PartnerDAO.findByName(name);
	}
	
	public List<Partner> findAllPartners() {
		return PartnerDAO.findAllPartners();
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
			if (partner.getPartnerId() != null) {
				session.saveOrUpdate(partner);
				session.getTransaction().commit();
			} 
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

}
