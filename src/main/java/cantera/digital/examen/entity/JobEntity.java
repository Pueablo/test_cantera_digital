package cantera.digital.examen.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class JobEntity extends PanacheEntity {
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "worked_hours")
    private int workedHours;
    @Column(name = "worked_date")
    private Date workedDate;

}
