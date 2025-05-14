package si.um.feri.aiv.crud;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.aiv.vao.Patient;

    public class RestClientTest {
        public static void main(String[] args) {
            Client client = ClientBuilder.newClient();
            Patient p = new Patient("Luka", "Pavlic", "jdkasjfl@feri.cm", "", null);
            Response response = client
                    .target("http://localhost:8080/sampleProject/rest/api/patients")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(p, MediaType.APPLICATION_JSON));
            Patient[] patients = client
                    .target("http://localhost:8080/sampleProject/rest/api/patients/unassigned")
                    .request(MediaType.APPLICATION_JSON)
                    .get(Patient[].class);

            for (Patient pat : patients) {
                System.out.println(pat.getName() + " - " + pat.getEmail());
            }

            client.close();
        }

    }
