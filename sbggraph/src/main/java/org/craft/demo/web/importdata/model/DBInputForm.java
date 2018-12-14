/**
 * 
 */
package org.craft.demo.web.importdata.model;

/**
 * @author asharma
 *
 */
public class DBInputForm {
	private String orionDatabasePath;
	private String magellanDatabasePath;
	
	/**
	 * 
	 */
	public DBInputForm() {
		super();
	}

	/**
	 * @param orionDatabasePath
	 * @param magellanDatabasePath
	 */
	public DBInputForm(String orionDatabasePath, String magellanDatabasePath) {
		super();
		this.orionDatabasePath = orionDatabasePath;
		this.magellanDatabasePath = magellanDatabasePath;
	}

	/**
	 * @return the orionDatabasePath
	 */
	public String getOrionDatabasePath() {
		return orionDatabasePath;
	}

	/**
	 * @param orionDatabasePath the orionDatabasePath to set
	 */
	public void setOrionDatabasePath(String orionDatabasePath) {
		this.orionDatabasePath = orionDatabasePath;
	}

	/**
	 * @return the magellanDatabasePath
	 */
	public String getMagellanDatabasePath() {
		return magellanDatabasePath;
	}

	/**
	 * @param magellanDatabasePath the magellanDatabasePath to set
	 */
	public void setMagellanDatabasePath(String magellanDatabasePath) {
		this.magellanDatabasePath = magellanDatabasePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DBInputForm [orionDatabasePath=%s, magellanDatabasePath=%s]", orionDatabasePath,
				magellanDatabasePath);
	}
	
}
