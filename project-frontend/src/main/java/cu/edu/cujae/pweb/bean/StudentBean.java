package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StudentBean {

	private String idNum;
	private String firstName;
	private String lastName;
	private String gender;
	private String municipality;
	private String statusID;
	
	public StudentBean() {
		// TODO Auto-generated constructor stub
	}

	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	
	
}
