package lecture22.asimpleMVC.controller;

import lecture22.asimpleMVC.model.StoneBlock;
import lecture22.asimpleMVC.view.StoneBlockView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

public class StoneBlockController implements EventHandler<Event> {
    //@ Il controller deve avere un link al model e uno alla view
    private StoneBlock st;
    private StoneBlockView sv;

    public StoneBlockController(StoneBlock s){
        this.st = s;
        StoneBlockView v = new StoneBlockView(s.getQty(), s.getName());
        this.sv = v;
        //@ Questo controller viene implementato come event handler
        //@ Pertanto si setta come handler al bottone della view
        sv.getNameButton().addEventHandler(ActionEvent.ACTION,this);
    }

    public StoneBlockView getSv() { return sv; }
    public StoneBlock getSt() { return st; }

    @Override
    public void handle(Event event) {
        this.logic();
    }

    //@ Il metodo `logic` chiama gli effetti della funzionalita` prima sul model e poi ne aggiorna la view
    private void logic(){
        TextInputDialog tid = new TextInputDialog();
        tid.showAndWait();
        String newname = tid.getEditor().getText();
        st.setName(newname);
        sv.getNameButton().setText(st.getName());
    }

    //@ Il metodo `ringiovanisci` e` usato dopo, con il MVC per le collections
    //@ Come `logic`, prima aggiorna il model, poi la view
    public void ringiovanisci(){
        st.use();
        sv.getQtyText().setText(st.getQty()+"");
    }
}
