/**
 * 
 */
package org.craft.demo.web.importdata.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.common.DatabaseType;
import org.craft.demo.dataimport.exceptions.DataImportException;
import org.craft.demo.dataimport.service.DataImportService;
import org.craft.demo.web.importdata.model.DBInputForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author asharma
 *
 */
@Controller
public class DataImportController {
	private static final Logger LOGGER = LogManager.getLogger(DataImportController.class);
	
	@Autowired
	private DataImportService dataImportService;
	
	@PostMapping("/submitdbs")
    public String viewHome(DBInputForm dbInputForm, Model model) {
		LOGGER.info(()-> "Received following request from client to process the data import: " + dbInputForm);
		
		model.addAttribute("dbInputForm", new DBInputForm());
		
		try {
			Map<DatabaseType, Path> databases = new HashMap<>();
			databases.put(DatabaseType.ORION, Paths.get(dbInputForm.getOrionDatabasePath()));
			databases.put(DatabaseType.MAGELLAN, Paths.get(dbInputForm.getMagellanDatabasePath()));
			dataImportService.importData(databases);
			model.addAttribute("submitted", true);
		} catch (DataImportException dataImportException) {
			model.addAttribute("error", true);
			model.addAttribute("errorMessage", dataImportException.getMessage());
		}
		
        return "index";
    }
}
