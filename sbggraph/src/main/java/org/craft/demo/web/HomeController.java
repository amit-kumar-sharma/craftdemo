/**
 * 
 */
package org.craft.demo.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.web.importdata.model.DBInputForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author asharma
 *
 */
@Controller
public class HomeController {
	
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	
	@GetMapping("/")
    public String viewHome(Model model) {
        return viewHomeIndex(model);
    }
	
	@GetMapping("/index")
    public String viewHomeIndex(Model model) {
		model.addAttribute("dbInputForm", new DBInputForm());
		LOGGER.debug(()-> "Serving the home page.");
        return "index";
    }
	
}
