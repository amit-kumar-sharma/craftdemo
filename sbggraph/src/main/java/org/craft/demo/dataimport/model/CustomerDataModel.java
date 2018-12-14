/**
 * 
 */
package org.craft.demo.dataimport.model;

import org.apache.commons.lang.StringUtils;

/**
 * @author asharma
 *
 */
public class CustomerDataModel {
	private String name;
	private String street;
	private String zip;
	private String phone;
	private String email;
	
	/**
	 * 
	 */
	public CustomerDataModel() {
		super();
	}

	/**
	 * 
	 * @param name
	 * @param street
	 * @param zip
	 * @param phone
	 * @param email
	 */
	public CustomerDataModel(String name, String street, String zip, String phone, String email) {
		super();
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? (phone==null? (name == null ? 0 : name.hashCode()): phone.hashCode()) : email.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDataModel other = (CustomerDataModel) obj;
		if (!StringUtils.equals(email, other.getEmail())) {
			return false;
		} else if (!StringUtils.equals(phone, other.getPhone())) {
			return false;
		} else if (!StringUtils.equals(street, other.getStreet())) {
			return false;
		} else if (!StringUtils.equals(zip, other.getZip())) {
			return false;
		} else if (!StringUtils.equals(name, other.getName())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("Customer [name=%s, street=%s, zip=%s, phone=%s, email=%s]", name, street, zip, phone,
				email);
	}
}
