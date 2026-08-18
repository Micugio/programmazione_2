package tutorato_1.es2_OOP;

import tutorato_1.es1_OOP.Auto;
import tutorato_1.es1_OOP.Nave;

public class Main {
    public static void main(String[] args) {
        Microscopio m = new Microscopio(TipoMicroscopio.ELETTRONICO);
        Batterio b = new Batterio(Morfologia.COCCO);
        m.osserva(b);

        // Output
        // Microscopio elettronico: Streptococco, Morfologia: Cocco, Forma: Sferica
    }
}
