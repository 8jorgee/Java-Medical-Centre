package si.um.feri.aiv.vao;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Patient implements Serializable {

	
	public Patient(String name, String surname, String email, String details, Doctor doctor) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.details = details;
		this.doctor = doctor;
	}

	@Id
	@NotBlank
	private String email;

	private String name;
	private String surname;
	private LocalDateTime birthday;
	private String details;
	@ManyToOne
	@JoinColumn(name = "doc_email")
	private Doctor doctor;
	private LocalDateTime timestamp=LocalDateTime.now();

}