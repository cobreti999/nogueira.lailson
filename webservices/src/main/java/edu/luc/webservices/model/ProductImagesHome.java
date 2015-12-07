// default package
// Generated Sep 20, 2015 6:10:58 PM by Hibernate Tools 4.3.1
package edu.luc.webservices.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProductImages.
 * @see .ProductImages
 * @author Hibernate Tools
 */
@Stateless
public class ProductImagesHome {

	private static final Log log = LogFactory.getLog(ProductImagesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProductImages transientInstance) {
		log.debug("persisting ProductImages instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProductImages persistentInstance) {
		log.debug("removing ProductImages instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProductImages merge(ProductImages detachedInstance) {
		log.debug("merging ProductImages instance");
		try {
			ProductImages result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductImages findById(Integer id) {
		log.debug("getting ProductImages instance with id: " + id);
		try {
			ProductImages instance = entityManager.find(ProductImages.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
