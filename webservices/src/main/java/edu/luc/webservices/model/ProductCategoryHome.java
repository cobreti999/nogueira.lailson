// default package
// Generated Sep 20, 2015 6:10:58 PM by Hibernate Tools 4.3.1
package edu.luc.webservices.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProductCategory.
 * @see .ProductCategory
 * @author Hibernate Tools
 */
@Stateless
public class ProductCategoryHome {

	private static final Log log = LogFactory.getLog(ProductCategoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProductCategory transientInstance) {
		log.debug("persisting ProductCategory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProductCategory persistentInstance) {
		log.debug("removing ProductCategory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProductCategory merge(ProductCategory detachedInstance) {
		log.debug("merging ProductCategory instance");
		try {
			ProductCategory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductCategory findById(Integer id) {
		log.debug("getting ProductCategory instance with id: " + id);
		try {
			ProductCategory instance = entityManager.find(ProductCategory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
