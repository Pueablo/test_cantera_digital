package cantera.digital.examen.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gender_id")
    private Long genderId;
    @Column(name = "job_id")
    private Long jobId;
    @Column(unique = true)
    private String name;
    @Column(name = "last_name", unique = true)
    private String lastName;
    @Past()
    private Date birthdate;

    /**
     * Custom restriction to verify if the person is over 18 years old
      */
    @AssertTrue()
    public boolean validateBirthday() {
        if (birthdate == null) {
            return false;
        }
        long difference = new Date().getTime() - birthdate.getTime();
        int age = (int) (difference / (1000 * 60 * 60 * 24 * 365.25));
        return age >= 18;
    }

}
