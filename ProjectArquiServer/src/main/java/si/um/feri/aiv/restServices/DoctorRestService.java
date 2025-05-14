package si.um.feri.aiv.restServices;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.aiv.ejb.PatientEJBLocal;
import si.um.feri.aiv.vao.Doctor;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DoctorRestService {

    @EJB
    private PatientEJBLocal dao;

    @GET
    @Path("/doctors")
    public List<Doctor> getAllDoctors() throws Exception {
        return dao.getAllDoctors();
    }

    @POST
    @Path("/doctors")
    public Response addOrUpdateDoctor(Doctor doctor) throws Exception {
        dao.saveDoctor(doctor);
        return Response.ok(doctor).build();
    }

    @DELETE
    @Path("/doctors/{email}")
    public void deleteDoctor(@PathParam("email") String email) throws Exception {
        dao.deleteDoctor(email);
    }
}
