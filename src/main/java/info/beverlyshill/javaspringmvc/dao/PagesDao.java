package info.beverlyshill.javaspringmvc.dao;

import info.beverlyshill.javaspringmvc.domain.Pages;

import java.util.List;

import org.springframework.stereotype.Component;


/**
 * Interface of Pages table access
 * @author bhill2
 */
@Component
public interface PagesDao {
	
	// Find all Pages records
	public List<Pages> findAll(String name) throws Exception;
	
	// Find a Page record by pageId
	public Pages findById(int pageId) throws Exception;
	
	// Insert or update a Pages record
	public Pages save(Pages pages) throws Exception;
	
	// Delete a Pages record
	public void delete(Pages pages) throws Exception;
}
