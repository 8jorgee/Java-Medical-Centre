package si.um.feri.aiv.ejb;


import java.util.List;
import java.util.logging.Logger;


import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import si.um.feri.aiv.mail.EmailService;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Patient;

import javax.print.Doc;

@Stateless
public class PatientMemoryEJB implements PatientEJBLocal, PatientEJBRemote {

	Logger log=Logger.getLogger(PatientMemoryEJB.class.toString());

	@PersistenceContext
	private EntityManager em;

	@Inject
	EmailService emailService;

	@Override
	public List<Patient> getAllAssigned() {
		log.info("DAO: get all assigned");
		TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.doctor IS NOT NULL AND p.doctor.doctorName <> ''", Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Patient> getAllUnassigned() {
		log.info("DAO: get all unassigned");
		TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.doctor IS NULL", Patient.class);
		return query.getResultList();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		log.info("DAO: get all unassigned");
		TypedQuery<Doctor> query = em.createQuery("SELECT d FROM Doctor d", Doctor .class);
		return query.getResultList();
	}

	@Override
	public Patient findPatient(String email)  {
		log.info("DAO: finding "+email);
		return em.find(Patient.class, email);
	}

	@Override
	public Doctor findDoctor(String email)  {
		log.info("DAO: finding "+email);
		return em.find(Doctor.class, email);
	}

	@Override
	public boolean savePatient(Patient o)  {
		log.info("DAO: saving "+o);
		if (o.getDoctor() != null && o.getDoctor().getEmail() != null) {
			Doctor d = em.find(Doctor.class, o.getDoctor().getEmail());
			if (d.getCurrentPatients() >= d.getMaxPatients()) {
				emailService.sendFailureEmail(o, d);
				return false;
			}
			d.setCurrentPatients(d.getCurrentPatients() + 1);
			o.setDoctor(d);
			if (findPatient(o.getEmail()) == null) {
				em.persist(o);
			} else {
				em.merge(o);
			}
			emailService.sendConfirmationEmails(d, o);
			em.merge(d);
			return true;
		}else {
			if (findPatient(o.getEmail()) == null)
				em.persist(o);
			else
				em.merge(o);
			return true;
		}

	}

	@Override
	public void saveDoctor(Doctor d)  {
		log.info("DAO: saving "+d);
		if (findDoctor(d.getEmail()) == null)
			em.persist(d);
		else
			em.merge(d);
	}

	@Override
	public void deletePatient(String email) {
		log.info("DAO: deleting "+email);
		Patient p = findPatient(email);
		if (p != null) em.remove(p);
	}

	@Override
	public void deleteDoctor(String email) {
		log.info("DAO: deleting "+email);
		Doctor p = findDoctor(email);
		if (p != null) em.remove(p);
	}


}