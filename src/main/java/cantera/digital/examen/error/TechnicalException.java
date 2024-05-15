package cantera.digital.examen.error;

public class TechnicalException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public TechnicalException(String message) {
        super(message);
        this.message = message;
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
