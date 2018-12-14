/**
 * 
 */
package org.craft.demo.application;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.service.DataImportTaskExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author asharma
 *
 */
@SpringBootApplication
@ComponentScan("org.craft.demo")
public class Application {
	
	private static final Logger LOGGER = LogManager.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @PreDestroy
    private void onExit() {
    	LOGGER.info("Exiting the APP. Finishing up the cleanup activity.");
    	LOGGER.info("Shutting Down application's task executor.");
    	DataImportTaskExecutor.shutDownExecutorService();
    	LOGGER.info("Exiting the APP. Done with the cleanup activity.");
    }
}
