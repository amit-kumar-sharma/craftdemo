/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author asharma
 *
 * This class holds a thread-pool that could execute data import tasks in parallel.
 */
public class DataImportTaskExecutor {
	
	private static final short THREAD_POOL_SIZE = 10;
	private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	
	/**
	 * Executes the given task asynchronously.
	 * @param dataImportTask the runnable task to execute.
	 */
	public static void executeTask(final DataImportTask dataImportTask) {
		if(dataImportTask == null) {
			throw new IllegalArgumentException("\"dataImportTask\" can not be null. Please provide a valid task to execute.");
		}
		executor.execute(dataImportTask);
	}
	
	public static void shutDownExecutorService() {
		executor.shutdownNow();
	}

}
