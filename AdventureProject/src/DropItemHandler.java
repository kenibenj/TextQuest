import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropItemHandler implements ActionListener {
    Game game;
    int slot;
    public DropItemHandler(Game game) {
        this.game = game;
    }

    public int setSlot(int slot){
        this.slot = slot;
        return slot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.inventoryHandler.dropItem(slot);
    }
}
