/**
 * 
 */
package org.craft.demo.dataprocessing.client.backupsystem;

/**
 * @author asharma
 *
 */
public interface BackupSystemClient<T> {

	public void backupData(T data);
}
