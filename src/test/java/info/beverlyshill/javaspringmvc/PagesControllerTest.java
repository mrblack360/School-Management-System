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
public class PagesControllerTest extends AbstractControllerTest {

	private PagesDaoImpl pagesDao = mock(PagesDaoImpl.class);
	
	private MessageSource messageSource = mock(MessageSource.class);

	private List<Pages> pagesList = new ArrayList<Pages>();

	private List<Pages> nullList = null;

	private PagesController pagesController = new PagesController();
	
	private String validNameValue = "Index";
	
	private String nullNameValue = null;

	@Before
	public void initPages() {
		// set the mocked pagesDao in pagesController
		pagesController.setPagesDao(pagesDao);
		pagesController.messageSource = messageSource;
		// add the records for comparison
		this.addTestRecord("Index", "Test 1.", "");
		this.addTestRecord("Index", "Test 2", "");
	}

	/**
	 * Tests that when the index method is invoked from a GET request the
	 * expected view is returned
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPagesListView() throws Exception {
		String localhostname = java.net.InetAddress.getLocalHost().getHostName();
		System.out.println("The localhostname is: " + localhostname);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String p = pagesController.index(null, uiModel, validNameValue);
		assertNotNull(p);
		assertEquals("Expected view was not returned.","index", p);
	}
	
	/**
	 * Tests that when the index method is invoked from a GET request
	 * with a null name value and the expected view is returned
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPagesListViewNullName() throws Exception {
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String p = pagesController.index(null, uiModel, null);
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
		for(int i=0; i<returnedPage.size();i++) {
			assertEquals("Incorrect model data returned.", pagesList.get(i).getTextDesc(),returnedPage.get(i).getTextDesc());
		}
	}

	/**
	 * Tests that the controller collaborates correctly with the dao class it
	 * uses to obtain the domain data
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPagesListDomainData() throws Exception {
		ExtendedModelMap uiModel = new ExtendedModelMap();
		given(pagesDao.findAll("Index")).willReturn(pagesList);
		pagesController.index(null, uiModel, null);
		verify(pagesDao, times(1)).findAll("Index");
	}
	
	/**
	 * Test that the pagesDao is returned
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPagesDao() throws Exception {
		PagesDao pd = pagesController.getPagesDao();
		assertEquals("The expected and returned PagesDao are not equal." ,pagesDao, pd);
	}

	/**
	 * Tests that the view method throws an exception upon an error situation
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getPagesWithNullValue() throws Exception {
		Mockito.when(pagesDao.findAll("null")).thenThrow(Exception.class);
		pagesController.getPagesDao().findAll("null");
	}
	
	/**
	 * Tests that the view displays error message with 
	 * null Pages data
	 * 
	 * @throws Exception
	 */
	@Test
	public void retrieveNullPagesAddsErrorMessageToModel() throws Exception {
		Model uiModel = new ExtendedModelMap();
		pagesController.setModel(uiModel);
		Locale locale = null;
		Mockito.when(messageSource.getMessage("findall_retrieve_fail", new Object[] {}, locale)).thenReturn("success");
		Mockito.when(pagesDao.findAll(nullNameValue)).thenThrow(Exception.class);
		pagesController.retrievePagesData(null, uiModel, nullNameValue);
		uiModel = pagesController.getModel();
		assertTrue("Model does not contain attribute message and should.",uiModel.containsAttribute("message"));
	}
	
	/**
	 * Tests that the view does not display an error
	 * message with a valid Pages list
	 * 
	 * @throws Exception
	 */
	@Test
	public void retrievePagesAddsNoErrorMessageToModel() throws Exception {
		Model uiModel = new ExtendedModelMap();
		pagesController.setModel(uiModel);
		given(pagesDao.findAll(validNameValue)).willReturn(pagesList);
		pagesController.retrievePagesData(null, uiModel, validNameValue);
		uiModel = pagesController.getModel();
		assertFalse("Model contains attribute message and should not.",uiModel.containsAttribute("message"));
	}
	
	/**
	 * Tests that the view sets Pages data on model
	 * 
	 * @throws Exception
	 */
	@Test
	public void addsPagesToModel() throws Exception {
		Model uiModel = new ExtendedModelMap();
		pagesController.addDataToModel(uiModel, pagesList);
		uiModel = pagesController.getModel();
		assertTrue("Model does not contain pages attribute and should.",uiModel.containsAttribute("pages"));
	}
	
	/**
	 * Tests that the view does not set null Pages data on model
	 * 
	 * @throws Exception
	 */
	@Test
	public void addsPagesToModelNull() throws Exception {
		Model uiModel = new ExtendedModelMap();
		pagesController.addDataToModel(uiModel, null);
		uiModel = pagesController.getModel();
		assertFalse("Model contains pages attribute and should not.",uiModel.containsAttribute("pages"));
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
