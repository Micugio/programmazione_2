package lecture22.asimpleMVC.controller;
import lecture22.asimpleMVC.model.StoneBlock;
import lecture22.asimpleMVC.view.StoneBlockView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
public class StoneBlockController implements EventHandler<Event> {
    private StoneBlock st;
    private StoneBlockView sv;
    public StoneBlockController(StoneBlock s){
        this.st = s;
        StoneBlockView v = new StoneBlockView(s.getQty(), s.getName());
        this.sv = v;
        sv.getNameButton().addEventHandler(ActionEvent.ACTION,this);
    }
    public StoneBlockView getSv() { return sv; }
    public StoneBlock getSt() { return st; }
    @Override
    public void handle(Event event) {
        this.logic();
    }
    private void logic(){
        TextInputDialog tid = new TextInputDialog();
        tid.showAndWait();
        String newname = tid.getEditor().getText();
        st.setName(newname);
        sv.getNameButton().setText(st.getName());
    }
    public void ringiovanisci(){
        st.use();
        sv.getQtyText().setText(st.getQty()+"");
    }
}