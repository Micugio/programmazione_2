package tutorato_1.es2_OOP;

public class Microscopio {
    public final String tipoMicroscopio;

    public Microscopio() {
        this.tipoMicroscopio = TipoMicroscopio.OTTICO;
    }

    public Microscopio(String tipoMicroscopio) {
        this.tipoMicroscopio = TipoMicroscopio.ELETTRONICO;
    }

    public void osserva(Batterio b) {
        b.toString();
    }
}
