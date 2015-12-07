// default package
// Generated Sep 20, 2015 6:10:58 PM by Hibernate Tools 4.3.1
package edu.luc.webservices.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProductComments.
 * @see .ProductComments
 * @author Hibernate Tools
 */
@Stateless
public class ProductCommentsHome {

	private static final Log log = LogFactory.getLog(ProductCommentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProductComments transientInstance) {
		log.debug("persisting ProductComments instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProductComments persistentInstance) {
		log.debug("removing ProductComments instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProductComments merge(ProductComments detachedInstance) {
		log.debug("merging ProductComments instance");
		try {
			ProductComments result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductComments findById(Integer id) {
		log.debug("getting ProductComments instance with id: " + id);
		try {
			ProductComments instance = entityManager.find(ProductComments.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
