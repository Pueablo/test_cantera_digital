package cantera.digital.examen.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@ToString
@Builder
public class ResponseMessagePaymentEmployee {
    private BigDecimal payment;
    private boolean success;
}