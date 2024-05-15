package cantera.digital.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkedHoursDto {
    private Long employeeId;
    private Date startDate;
    private Date endDate;
}
