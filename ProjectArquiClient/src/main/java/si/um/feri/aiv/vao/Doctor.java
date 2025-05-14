package si.um.feri.aiv.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

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
    }

    @Id
    private String email;

    private String doctorName;
    private String surname;
    private int maxPatients;
}