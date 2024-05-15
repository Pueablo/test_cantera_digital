package cantera.digital.examen.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@Builder
public class ResponseMessageTotalHoursWorked {
    private Integer totalWorkedHours;
    private boolean success;
}