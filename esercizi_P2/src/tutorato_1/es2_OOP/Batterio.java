package tutorato_1.es2_OOP;

public class Batterio {
    private String descrizione = "prova";
    private Morfologia morfologia;

    public Batterio(enum Morfologia) {

    }

    @Override
    public String toString(Microscopio m, Batterio b) {
        return "Microscopio " + m.tipoMicroscopio + ":" + descrizione + "," +
                "Morfologia: " + b.morfologia.getCaratt() + '\'' +
                '}';
    }
}
