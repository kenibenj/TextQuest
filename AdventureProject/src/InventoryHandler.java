import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InventoryHandler implements ActionListener {

    Game game;
    boolean inventoryOpen = false;
    boolean equipmentOpen = false;
    boolean infoOpen = false;

    public InventoryHandler(Game game){
        this.game = game;
    }

    public void reset(){
        inventoryOpen = false;
        for(int i = 0; i < game.gui.inventorySlots.length; i++) {
            game.gui.inventorySlots[i].setVisible(false);
        }
    }

    public void addItem(Item item){
        boolean inventoryFull = true;
        for(int i = 0; i < game.story.player.playersInventory.length; i++){
            if (game.story.player.playersInventory[i] instanceof Item.NothingItem){
                game.story.player.playersInventory[i] = item;
                game.gui.inventorySlots[i].setText(game.story.player.playersInventory[i].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                game.gui.actionLabel.setText("You received a " + item.name);
                inventoryFull = false;
                break;
            }
            else{
                inventoryFull = true;
            }
        }
        if(inventoryFull){
            game.gui.actionLabel.setText("Your inventory is full!");
        }
    }
    public void start(){
        game.gui.inventoryPanel.setVisible(false);
        game.gui.equipmentPanel.setVisible(false);
        game.gui.informationPanel.setVisible(false);
    }

    public void openInventory(){
        game.gui.inventoryPanel.setVisible(true);
        game.gui.equipmentPanel.setVisible(false);
        game.gui.informationPanel.setVisible(false);
        for(int i = 0; i < game.gui.inventorySlots.length; i++) {
            game.gui.inventorySlots[i].setVisible(true);
        }
        inventoryOpen = true;

        try {
            SoundEffectHandler.playFile(game.soundEffectHandler.openInv);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }

    public void openInformation(){
        game.gui.inventoryPanel.setVisible(false);
        game.gui.equipmentPanel.setVisible(false);
        game.gui.informationPanel.setVisible(true);
        try {
            SoundEffectHandler.playFile(game.soundEffectHandler.openInfo);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }

    public void openEquipment(){
        game.gui.inventoryPanel.setVisible(false);
        game.gui.equipmentPanel.setVisible(true);
        game.gui.informationPanel.setVisible(false);
        try {
            SoundEffectHandler.playFile(game.soundEffectHandler.openEquip);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }

    public void modifyEquipment(){
        boolean visible = game.gui.equipmentTextArea.isVisible();
        game.gui.equipmentTextArea.setText("Attack: " + game.story.player.currentWeapon.damage + "\n" +
                "Armor: " + game.story.player.currentArmor.armorValue + "\n");
        game.gui.equipmentTextArea.setVisible(visible);
    }

    public void modifyInformation(){
        boolean visible = game.gui.informationTextArea.isVisible();
        game.gui.informationTextArea.setText("Level: " + game.story.player.level + "\n" +
                "XP: " + game.story.player.XP + "\n" +
                "Area: " + game.story.player.area + "\n" +
                "Objective: " + game.story.player.objective + "\n");
        game.gui.informationTextArea.setVisible(visible);
    }

    public void updateInfoEquip(){
        modifyEquipment();
        modifyInformation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String choice = e.getActionCommand();

        switch (choice){
            case "openInventory" :
                openInventory();
                break;
            case "openInformation" :
                openInformation();
                break;
            case "openEquipment" :
                openEquipment();
                break;
            case "slot1" :
                game.story.player.playersInventory[0] = game.story.player.playersInventory[0].useItem();
                game.gui.inventorySlots[0].setText(game.story.player.playersInventory[0].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
            case "slot2" :
                game.story.player.playersInventory[1] = game.story.player.playersInventory[1].useItem();
                game.gui.inventorySlots[1].setText(game.story.player.playersInventory[1].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
            case "slot3" :
                game.story.player.playersInventory[2] = game.story.player.playersInventory[2].useItem();
                game.gui.inventorySlots[2].setText(game.story.player.playersInventory[2].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
            case "slot4" :
                game.story.player.playersInventory[3] = game.story.player.playersInventory[3].useItem();
                game.gui.inventorySlots[3].setText(game.story.player.playersInventory[3].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
            case "slot5" :
                game.story.player.playersInventory[4] = game.story.player.playersInventory[4].useItem();
                game.gui.inventorySlots[4].setText(game.story.player.playersInventory[4].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
            case "slot6" :
                game.story.player.playersInventory[5] = game.story.player.playersInventory[5].useItem();
                game.gui.inventorySlots[5].setText(game.story.player.playersInventory[5].name);
                game.gui.weaponLabelName.setText("Weapon: " + game.story.player.currentWeapon.name);
                game.gui.hpLabelNumber.setText(String.valueOf(game.story.player.hp));
                break;
        }
    }
}
