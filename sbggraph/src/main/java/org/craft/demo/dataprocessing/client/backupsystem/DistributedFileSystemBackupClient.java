/**
 * 
 */
package org.craft.demo.dataprocessing.client.backupsystem;

import org.craft.demo.dataimport.model.CustomerDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class DistributedFileSystemBackupClient implements BackupSystemClient<CustomerDataModel> {

	@Override
	public void backupData(CustomerDataModel data) {
		//TODO: Backup data to some file may be on Hadoop File System.
	}

}
