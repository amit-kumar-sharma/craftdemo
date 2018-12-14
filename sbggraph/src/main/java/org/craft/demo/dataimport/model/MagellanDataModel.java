/**
 * 
 */
package org.craft.demo.dataimport.model;

/**
 * @author asharma
 *
 */
public class MagellanDataModel {

	private String fullName;
	private String address1;
	private String zip;
	private String primaryPhone;
	private String email;
	
	/**
	 * 
	 */
	public MagellanDataModel() {
		super();
	}

	/**
	 * 
	 * @param fullName
	 * @param address1
	 * @param zip
	 * @param primaryPhone
	 * @param email
	 */
	public MagellanDataModel(String fullName, String address1, String zip, String primaryPhone, String email) {
		super();
		this.fullName = fullName;
		this.address1 = address1;
		this.zip = zip;
		this.primaryPhone = primaryPhone;
		this.email = email;
	}
	
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the primaryPhone
	 */
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Magellan Customer [fullName=%s, address1=%s, zip=%s, primaryPhone=%s, email=%s]", fullName,
				address1, zip, primaryPhone, email);
	}

}
