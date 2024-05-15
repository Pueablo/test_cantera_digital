package cantera.digital.examen.util;

public class Calculate {

    public static int differenceBetweenDate(long difference){
        return (int) (difference / (1000 * 60 * 60 * 24 * 365.25));
    }
}
