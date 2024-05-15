package cantera.digital.examen.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@Builder
public class ResponseMessageEmployee {
    private Long id;
    private boolean success;
}