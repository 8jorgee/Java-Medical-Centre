package si.um.feri.aiv.restServices;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.aiv.ejb.PatientEJBLocal;
import si.um.feri.aiv.vao.Patient;

import java.util.List;

    @Path("/api")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public class PatientRestService {

        @EJB
        private PatientEJBLocal dao;

        @GET
        @Path("/patients/assigned")
        public List<Patient> getAllAssignedPatients() throws Exception {
            return dao.getAllAssigned();
        }

        @GET
        @Path("/patients/unassigned")
        public List<Patient> getAllUnassignedPatients() throws Exception {
            return dao.getAllUnassigned();
        }

        @POST
        @Path("/patients")
        public Response addOrUpdatePatient(Patient patient) throws Exception {
            if (!dao.savePatient(patient)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Doctor capacity exceeded").build();
            }
            return Response.ok(patient).build();
        }

        @DELETE
        @Path("/patients/{email}")
        public void deletePatient(@PathParam("email") String email) throws Exception {
            dao.deletePatient(email);
        }

    }


