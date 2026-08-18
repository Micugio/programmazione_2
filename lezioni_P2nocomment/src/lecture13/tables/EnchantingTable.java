package lecture13.tables;
import lecture10.bindings.Block;
import lecture13.exceptions.*;
public class EnchantingTable extends CraftingTable{
    @Override
    public Block craftAdvanced(String item) throws InventoryFullException {
        try {
            return super.craftAdvanced(item);
        }catch (RecipeMissingException e){ }
        return null;
    }
    @Override
    public Block craftGeneral(String item) throws CraftingException {
        return super.craftGeneral(item);
    }
    private void enchant(String item, int level) throws EnchantingException {
        throw new LevelNotEnoughException("Requires level 30, but player is level 27.");
    }
}