package si.um.feri.aiv.ejb;



import jakarta.ejb.Remote;
import si.um.feri.aiv.vao.Patient;

import java.util.List;

@Remote
public interface PatientEJBRemote {

    List<Patient> getAllUnassigned();

    Patient findPatient(String email);

    void savePatient(Patient o);

    void deletePatient(String email);
}
