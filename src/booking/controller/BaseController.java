package booking.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	// method for processing date
	@InitBinder
	public void initBinder(WebDataBinder binder){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
}
