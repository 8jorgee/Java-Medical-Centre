package si.um.feri.aiv.ejb;

import java.util.List;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Patient;

@Local
public interface PatientEJBLocal {

	List<Patient> getAllAssigned();
	List<Patient> getAllUnassigned();
	List<Doctor> getAllDoctors();
	Patient findPatient(String email);
	Doctor findDoctor(String email);
	boolean savePatient(Patient o);
	void saveDoctor(Doctor o);
	void deletePatient(String email);
	void deleteDoctor(String email);
}