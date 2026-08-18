package lecture15.state.senza.v1;
public class Piglin_Sv1 {
    private PiglinState_Sv1 state;
    public Piglin_Sv1(PiglinState_Sv1 s){
        this.state = s;
    }
    public Piglin_Sv1(){
        this(PiglinState_Sv1.IDLE);
    }
    public void see_player_no_gold(){
        switch (this.state){
            case IDLE -> {
                this.state = PiglinState_Sv1.AGGRESSIVE_1;
                System.out.println("Pigling vede che non indossi oro, diventa aggressivo");
            }
            case FRIENDLY -> {
                this.state = PiglinState_Sv1.AGGRESSIVE_1;
                System.out.println("Piglin vede che non hai piu oro, diventa aggressivo");
            }
            case AGGRESSIVE_1 -> {}
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    public void see_player_wearing_gold(){
        switch (this.state){
            case IDLE -> {
                this.state = PiglinState_Sv1.FRIENDLY;
                System.out.println("Piglin vede che indossi oro, e` amichevole");
            }
            case FRIENDLY -> {}
            case AGGRESSIVE_1 -> {
                this.state = PiglinState_Sv1.FRIENDLY;
                System.out.println("Piglin vede che indossi oro, diventa amichevole");
            }
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    public void unsee_player(){
        this.state = PiglinState_Sv1.IDLE;
        System.out.println("Piglin non ti vede piu`");
    }
}