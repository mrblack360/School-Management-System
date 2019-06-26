package info.beverlyshill.javaspringmvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import info.beverlyshill.javaspringmvc.dao.PagesDao;
import info.beverlyshill.javaspringmvc.domain.Pages;
import info.beverlyshill.javaspringmvc.hibernate.dao.PagesDaoImpl;
 
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
 
/**
 * Tests for PagesController class
 * 
 * @author bhill2
 * 
 */
public class PagesControllerIntegrationTest extends AbstractControllerTest {
	
	private MessageSource messageSource = mock(MessageSource.class);
 
	private List<Pages> pagesList = new ArrayList<Pages>();
 
	private List<Pages> nullList = null;
 
	private PagesController pagesController = new PagesController();
	
	private String validNameValue = "Index";
	
	private String nullNameValue = null;
 
	@Before
	public void initPages() throws UnknownHostException {
		String localhostname = java.net.InetAddress.getLocalHost().getHostName();
		System.out.println("The localhostname is: " + localhostname);
		pagesController.messageSource = messageSource;
		// add the records for comparison
		this.addTestRecord("Index", "This is a sample web application built with the Spring framework.", "");
	}
 
	/**
	 * Tests that when the index method is invoked from a GET request the
	 * expected view is returned
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPagesListView() throws Exception {
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String p = pagesController.index(null, uiModel, validNameValue);
		assertNotNull(p);
		assertEquals("Expected view was not returned.","index", p);
	}
	
	/**
	 * Tests that the model returned with the view is what is expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPagesListModel() throws Exception {
		ExtendedModelMap uiModel = new ExtendedModelMap();
		pagesController.index(null, uiModel, validNameValue);
		@SuppressWarnings("unchecked")
		List<Pages> returnedPage = (List<Pages>) uiModel.get("pages");
		assertEquals("Incorrect model data returned.", pagesList.get(0).getTextDesc(),returnedPage.get(0).getTextDesc());
	}
 
 
	/**
	 * Adds a record of parameter values to the pagesList ArrayList
	 * @param name corresponding to name in pagesList
	 * @param description corresponding to textDesc in pagesList
	 * @param detailPage corresponding to detailPage in pagesList
	 */
	private void addTestRecord(String name, String description, String detailPage) {
		Pages newPage = new Pages();
		newPage.setName(name);
		newPage.setTextDesc(description);
		newPage.setDetailPage(detailPage);
		pagesList.add(newPage);
	}
}
