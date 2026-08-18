package tutorato_1.es2_OOP;

public enum Morfologia {
    COCCO("cocco", "sferica"),
    BACILLO("bacillo", "bastoncino"),
    SPIRILLO("spirillo", "spirale");

    private final String caratt;
    private final String forma;

    // Il costruttore di un enum è privato (di default).
    Morfologia(String caratt, String forma) {
        this.caratt = caratt;
        this.forma = forma;
    }

    public String getCaratt() {
        return caratt;
    }

    public String getForma() {
        return forma;
    }
}
}
