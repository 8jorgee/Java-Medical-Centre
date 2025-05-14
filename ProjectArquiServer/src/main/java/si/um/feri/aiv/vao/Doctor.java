package si.um.feri.aiv.vao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Doctor implements Serializable {


    public Doctor(String doctorName, String surname, String email, int maxPatients) {
        this.doctorName = doctorName;
        this.surname = surname;
        this.email = email;
        this.maxPatients = maxPatients;
        this.currentPatients=0;
    }

    @Id
    private String email;

    private String doctorName;
    private String surname;
    private int maxPatients;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();
    private int currentPatients;
}
