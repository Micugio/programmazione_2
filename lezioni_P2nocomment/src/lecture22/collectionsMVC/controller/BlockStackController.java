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
    public BlockStackController(BlockStack r, ArrayList<StoneBlockController> asr){
        this.c = r;
        this.asc = asr;
        this.cv = createClassroomView();
    }
    public void setMv(CollectionsMainView m){
        this.mv = m;
    }
    public BlockStackView getCv() { return cv; }
    public void usaBlocchi(){
        for (StoneBlockController sc : asc) {
            sc.ringiovanisci();
        }
    }
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