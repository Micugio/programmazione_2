package lecture15.state.senza.v2;
public class Piglin_Sv2 {
    private PiglinState_Sv2 state;
    public Piglin_Sv2(PiglinState_Sv2 s){
        this.state = s;
    }
    public Piglin_Sv2(){
        this(PiglinState_Sv2.PASSIVE);
    }
    public void see_player_no_gold(){
        switch (this.state){
            case PASSIVE -> {
                this.state = PiglinState_Sv2.AGGRESSIVE_1;
                System.out.println("Pigling vede che non indossi oro, diventa aggressivo");
            }
            case FRIENDLY -> {
                this.state = PiglinState_Sv2.AGGRESSIVE_1;
                System.out.println("Piglin vede che non hai piu oro, diventa aggressivo");
            }
            case AGGRESSIVE_1 -> {}
            case AGGRESSIVE_2 -> {}
            case AGGRESSIVE_3 -> {}
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    public void see_player_wearing_gold(){
        switch (this.state){
            case PASSIVE -> {
                this.state = PiglinState_Sv2.FRIENDLY;
                System.out.println("Piglin vede che indossi oro, e` amichevole");
            }
            case FRIENDLY -> {}
            case AGGRESSIVE_1 -> {
                this.state = PiglinState_Sv2.FRIENDLY;
                System.out.println("Piglin vede che indossi oro, diventa amichevole");
            }
            case AGGRESSIVE_2 -> {
                this.state = PiglinState_Sv2.FRIENDLY;
                System.out.println("Piglin vede che indossi oro, diventa amichevole");
            }
            case AGGRESSIVE_3 -> {}
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    public void unsee_player(){
        this.state = PiglinState_Sv2.PASSIVE;
        System.out.println("Piglin non ti vede piu`");
    }
    public void player_opens_chest(){
        switch (this.state){
            case FRIENDLY -> {
                this.state = PiglinState_Sv2.AGGRESSIVE_2;
                System.out.println("Piglin vede che apri un forziere, diventa arrabbiato2s");
            }
            case PASSIVE -> {
                this.state = PiglinState_Sv2.AGGRESSIVE_2;
                System.out.println("Piglin vede che apri un forziere, diventa arrabbiato2s");
            }
            case AGGRESSIVE_1 -> {}
            case AGGRESSIVE_2 -> {}
            case AGGRESSIVE_3 -> {}
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    public void player_mines_gold(){
        this.player_opens_chest();
    }
    public void player_attacks_piglin(){
        switch (this.state){
            case FRIENDLY -> {this.become_aggro3();}
            case PASSIVE -> {this.become_aggro3();}
            case AGGRESSIVE_1 -> {this.become_aggro3();}
            case AGGRESSIVE_2 -> {this.become_aggro3();}
            case AGGRESSIVE_3 -> {this.become_aggro3();}
            default -> {
                System.out.println("Non hai codificato questa opzione ?");
            }
        }
    }
    private void become_aggro3(){
        this.state = PiglinState_Sv2.AGGRESSIVE_3;
        System.out.println("Piglin viene attaccato, diventa arrabbiato3");
    }
}