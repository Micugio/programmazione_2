package lecture22.multiInputsMVC.controller;
import javafx.scene.control.TextInputDialog;
import lecture22.multiInputsMVC.view.MultiStoneBlockView;
import lecture22.asimpleMVC.model.StoneBlock;
public class MultiStoneBlockController {
    private StoneBlock st;
    private MultiStoneBlockView sv;
    public MultiStoneBlockController(StoneBlock s){
        this.st = s;
        MultiStoneBlockView v = new MultiStoneBlockView(s.getQty(), s.getName(), this);
        this.sv = v;
    }
    public MultiStoneBlockView getSv() { return sv; }
    public void logic(){
        TextInputDialog tid = new TextInputDialog();
        tid.showAndWait();
        String newname = tid.getEditor().getText();
        st.setName(newname);
        sv.getNameButton().setText(st.getName());
    }
}