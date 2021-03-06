package scheduler;

import java.util.ArrayList;

public class Patient {
	private String firstName;
	private String lastName;
	private ArrayList<Doctor> patientListOfDoctors;
	
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
	
	public ArrayList<Doctor> getListOfDoctors() {
		return patientListOfDoctors;
	}
	
	public void addDoctorToPatientList(Doctor doctor) {
		patientListOfDoctors.add(doctor);
	}
}
