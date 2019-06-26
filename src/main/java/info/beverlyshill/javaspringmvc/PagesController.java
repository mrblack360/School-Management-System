package info.beverlyshill.javaspringmvc;

import info.beverlyshill.javaspringmvc.dao.PagesDao;
import info.beverlyshill.javaspringmvc.domain.Pages;
import info.beverlyshill.javaspringmvc.util.Message;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application index page.
 * 
 * @author bhill2
 */
@Controller
public class PagesController {

	@Autowired
	private PagesDao pagesDao;

	@Autowired
	MessageSource messageSource;

	private String defaultNameValue = "Index";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<Pages> pages = null;
	
	private Model model;

	/**
	 * Selects the index view to render by returning its name NS
	 * sets the Pages data that is retrieved to the model.
	 * 
	 * @param locale is internationalization locale
	 * @param model is the Spring model
	 * @return index view
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, String nameValue) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource.xml");
		ctx.refresh();
		// if nameValues attribute is null, use default value
		if(null == nameValue) {
			nameValue = defaultNameValue;
		}
		if(null == getPagesDao()) {
			// set the dao object for Pages data
			setPagesDao(ctx.getBean("pagesDao", PagesDao.class));
		}
		// Retrieve all Pages records with specified value for name column
		retrievePagesData(locale, model, nameValue);
		// After retrieval see if no records were returned
		getDataCount(locale, model);
		// Add pages data to model
		addDataToModel(model, pages);
		return "index";
	}
	
	/**
	 * Adds the retrieved Pages data to the model
	 * @param model is the Spring model
	 */
	public void addDataToModel(Model model, List<Pages> pages) {
		if(null != pages) {
			model.addAttribute("pages", pages);
		}
		this.setModel(model);
	}
	
	/**
	 * Gets count of records that have been
	 * retrieved and if 0, adds error message
	 * to the model, otherwise logs number of
	 * records retrieved.
	 * @param locale is internationalization locale
	 * @param model is the Spring model
	 */
	public void getDataCount(Locale locale, Model model) {
		// Log the number of Pages records retrieved
		if (pages.size() == 0) {
			model.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"findall_retrieve_fail", new Object[] {}, locale)));
			logger.error("Error retrieving Pages data. No records were returned.");
		} else {
			// Log the number of Pages records retrieved
			logger.info("Pages record count: " + pages.size());
		}
	}
	
	/**
	 * Retrieves and sets Pages retrieved. If there is an exception
	 * a message is added to the model and logged.
	 * @param locale is internationalization locale
	 * @param model is the Spring model
	 */
	public void retrievePagesData(Locale locale, Model model, String nameValue) {
		try {
			pages = pagesDao.findAll(nameValue);
		} catch (Exception e) {
			model.addAttribute(
					"message",
					new Message("Error retrieving Pages data.", messageSource.getMessage(
							e.getMessage(), new Object[] {},
							locale)));
			logger.error("Error retrieving Pages data.", e.getMessage());
		} 
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}

	public PagesDao getPagesDao() {
		return pagesDao;
	}

	public void setPagesDao(PagesDao pagesDao) {
		this.pagesDao = pagesDao;
	}
}
