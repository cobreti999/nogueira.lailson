// default package
// Generated Sep 20, 2015 6:10:58 PM by Hibernate Tools 4.3.1
package edu.luc.webservices.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PartnerComments.
 * @see .PartnerComments
 * @author Hibernate Tools
 */
@Stateless
public class PartnerCommentsHome {

	private static final Log log = LogFactory.getLog(PartnerCommentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PartnerComments transientInstance) {
		log.debug("persisting PartnerComments instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PartnerComments persistentInstance) {
		log.debug("removing PartnerComments instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PartnerComments merge(PartnerComments detachedInstance) {
		log.debug("merging PartnerComments instance");
		try {
			PartnerComments result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PartnerComments findById(Integer id) {
		log.debug("getting PartnerComments instance with id: " + id);
		try {
			PartnerComments instance = entityManager.find(PartnerComments.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
