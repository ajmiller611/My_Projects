package scheduler;

import java.util.ArrayList;

public class Doctor {
	private String firstName;
	private String lastName;
	private String specialization;
	private ArrayList<Patient> patientList;
	
	public Doctor() {
		
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void addPatientToDoctorList(Patient patient) {
		patientList.add(patient);
	}
}
