// default package
// Generated Sep 20, 2015 6:10:58 PM by Hibernate Tools 4.3.1
package edu.luc.webservices.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Partner.
 * @see .Partner
 * @author Hibernate Tools
 */
@Stateless
public class PartnerHome {

	private static final Log log = LogFactory.getLog(PartnerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Partner transientInstance) {
		log.debug("persisting Partner instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Partner persistentInstance) {
		log.debug("removing Partner instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Partner merge(Partner detachedInstance) {
		log.debug("merging Partner instance");
		try {
			Partner result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Partner findById(Integer id) {
		log.debug("getting Partner instance with id: " + id);
		try {
			Partner instance = entityManager.find(Partner.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
