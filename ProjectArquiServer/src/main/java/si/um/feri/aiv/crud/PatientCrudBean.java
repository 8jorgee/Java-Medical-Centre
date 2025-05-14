package si.um.feri.aiv.crud;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Named;
import si.um.feri.aiv.ejb.PatientEJBLocal;

import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Patient;

import java.io.Serializable;
import java.util.List;

@Named("demo")
@SessionScoped
public class PatientCrudBean implements Serializable {

	private static final long serialVersionUID = -8979220536758073133L;

	@EJB
	private PatientEJBLocal dao;

	private Patient selectedPerson=new Patient();
	private Doctor selectedDoctor=new Doctor();

	private String selectedEmail;


	public List<Patient> getAllAssignedPeople() throws Exception {
		return dao.getAllAssigned();
	}

	public List<Patient> getAllUnassignedPeople() throws Exception {
		return dao.getAllUnassigned();
	}

	public List<Doctor> getAllDoctors() throws Exception {
		return dao.getAllDoctors();
	}
	
	public String savePerson() throws Exception {
		if (selectedPerson.getDoctor().getEmail() == null) {
			selectedPerson.setDoctor(null);
		}
		if(!dao.savePatient(selectedPerson)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error", "Doctor capacity exceeded"));
			return null;
		}

		return "home";
	}


	public void deletePerson(Patient o) throws Exception {
		dao.deletePatient(o.getEmail());
	}

	public String saveDoc() throws Exception {
		dao.saveDoctor(selectedDoctor);
		return "home";
	}


	public void deleteDoc(Doctor o) throws Exception {
		dao.deleteDoctor(o.getEmail());
	}

	public void setSelectedEmail(String email) throws Exception {
		selectedEmail =email;
		selectedPerson =dao.findPatient(email);
		selectedDoctor =dao.findDoctor(email);
		if(selectedPerson ==null && selectedDoctor ==null) {
			selectedPerson = new Patient();
			selectedDoctor = new Doctor();
		}
	}


	public String cancelAction(){
		return "home";
	}

	public String navigateToUnassigned() throws Exception{
		return "allUnassigned";
	}

	public String navigateToAssigned() throws Exception{
		return "allAssigned";
	}

	public String navigateToHome() throws Exception{
		return "home";
	}

	public String navigateToEditPatient() throws Exception{
		return "editPatients";
	}

	public String navigateToEditDoctor() throws Exception{
		return "editDoctor";
	}

	public String navigateToDetails() throws Exception{
		return "details";
	}
	public String navigateToDetailsDoctor() throws Exception{
		return "detailsDoctor";
	}

	public String navigateToDoctors() throws Exception{
		return "Doctors";
	}

	
	public String getSelectedEmail() { return selectedEmail; }

	public Patient getSelectedPerson() {
		if (selectedPerson.getDoctor() == null) {
			selectedPerson.setDoctor(new Doctor());
		}
		return selectedPerson;
	}

	public Doctor getSelectedDoctor() { return selectedDoctor;}

	public void setSelectedPerson(Patient selectedPerson) { this.selectedPerson = selectedPerson; }

	public void setSelectedDoctor(Doctor selectedDoctor) {this.selectedDoctor = selectedDoctor;}


}
