package info.beverlyshill.javaspringmvc.hibernate.dao;

import info.beverlyshill.javaspringmvc.dao.PagesDao;
import info.beverlyshill.javaspringmvc.domain.Pages;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pages table Dao implementation
 * 
 * @author bhill2
 * 
 */
@Repository("pagesDao")
@Transactional
public class PagesDaoImpl implements PagesDao {

	final Logger logger = LoggerFactory.getLogger(PagesDaoImpl.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Returns a list of Pages records retrieved for a specific name value
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Pages> findAll(String name) throws Exception {
		return sessionFactory.getCurrentSession()
				.createQuery("from Pages c where c.name = '" + name + "'")
				.list();
	}

	/**
	 * Returns a Pages record retrieved for a specific page Id
	 */
	@Transactional(readOnly = true)
	public Pages findById(int id) throws Exception {
		return (Pages) sessionFactory.getCurrentSession()
				.createQuery("from Pages c where c.pageId = " + id)
				.uniqueResult();
	}

	/**
	 * Persists a Pages object
	 * 
	 */
	@Transactional
	public Pages save(Pages pages) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(pages);
		this.logger.info("Contact saved with id: " + pages.getPageId());
		return pages;
	}

	/**
	 * Deletes a Pages object
	 * 
	 */
	@Transactional
	public void delete(Pages pages) throws Exception {
		sessionFactory.getCurrentSession().delete(pages);
		this.logger.info("Contact deleted with id: " + pages.getPageId());
	}
}
