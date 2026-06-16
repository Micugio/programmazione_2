package lecture22.collectionsMVC.controller;

import lecture22.asimpleMVC.controller.StoneBlockController;
import lecture22.collectionsMVC.model.BlockStack;
import lecture22.collectionsMVC.view.BlockStackView;
import lecture22.collectionsMVC.view.CollectionsMainView;
import lecture22.asimpleMVC.view.StoneBlockView;
import java.util.ArrayList;
import java.util.Comparator;

public class BlockStackController {
    private BlockStack c;
    private BlockStackView cv;
    private ArrayList<StoneBlockController> asc;
    private CollectionsMainView mv;

    //@ Al controller passiamo anche la lista di Block controller per gestire in modo uniforme azioni sulla lista
    public BlockStackController(BlockStack r, ArrayList<StoneBlockController> asr){
        this.c = r;
        this.asc = asr;
        this.cv = createClassroomView();
    }
    //? Perche` non possiamo passare mainview nel costruttore?
    //@ ---
    public void setMv(CollectionsMainView m){
        this.mv = m;
    }
    public BlockStackView getCv() { return cv; }

    //@ Il metodo `usaBlocchi` chiama un comportamento su tutti i Blocks.
    //@ Per farlo, chiama quel comportamento sul controller, che e` responsabile di fare il comportamento sul modello e sulla view corrispondente
    public void usaBlocchi(){
        for (StoneBlockController sc : asc) {
            sc.ringiovanisci();
        }
    }

    //@ Il metodo `controllerSort` serve per ordinare la collection
    public void controllerSort(Comparator<StoneBlockController> comp){
        this.asc.sort(comp);
        this.c = new BlockStack();
        for (StoneBlockController sc : this.asc){
            this.c.add(sc.getSt());
        }
        this.cv = this.createClassroomView();
        this.mv.updateClassroom(this.cv);
    }

    private BlockStackView createClassroomView(){
        ArrayList<StoneBlockView> asv = new ArrayList<>();
        for (StoneBlockController s: this.asc) {
            asv.add(s.getSv());
        }
        return new BlockStackView(asv, this);
    }
}
