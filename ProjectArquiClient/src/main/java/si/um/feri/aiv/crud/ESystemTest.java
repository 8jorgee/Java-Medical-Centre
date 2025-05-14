package si.um.feri.aiv.crud;

import si.um.feri.aiv.ejb.PatientEJBRemote;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Patient;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ESystemTest {
    public static void main(String[] args) throws Exception {

        Properties props=new Properties();
        props.put("java.naming.factory.initial","org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put("java.naming.provider.url","remote+http://127.0.0.1:8080");
        props.put("jboss.naming.client.ejb.context","true");
        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
        InitialContext ctx=new InitialContext(props);


        PatientEJBRemote c = (PatientEJBRemote) ctx.lookup("ejb:/sampleProject/PatientMemoryEJB!si.um.feri.aiv.ejb.PatientEJBRemote");
        Doctor doctor = new Doctor("Jorge", "Marcos", "@FERI", 10);
        Patient p = new Patient("Jorge", "M", "@FERI", "", doctor);
        Patient g = new Patient("Jorge", "M", "@hotmail", "", doctor);
        Patient e = new Patient("Jorge", "M", "@yahoo", "", doctor);
        c.savePatient(p);
        c.savePatient(g);
        c.savePatient(e);
        System.out.println(c.getAllUnassigned());


    }
}
