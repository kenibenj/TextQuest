import java.net.URL;

public class Armor extends Item{
    int armorValue;

    public Item useItem(){
        Item switchArmor = currentUser.currentArmor;
        if (switchArmor instanceof Armor.Clothes){
            switchArmor = new NothingItem();
        }
        currentUser.currentArmor = this;
        return switchArmor;
    }

    public static class Clothes extends Armor{
        Clothes(Entities user){
            name = "Clothes";
            armorValue = 0;
            currentUser = user;
            sound = getClass().getResource("armor.wav");
        }
    }

    public static class LeatherArmor extends Armor{
        LeatherArmor(Entities user){
            name = "Leather Armor";
            armorValue = 1;
            currentUser = user;
            sound = getClass().getResource("armor.wav");
        }
    }

    public static class ChainmailArmor extends Armor{
        ChainmailArmor(Entities user){
            name = "Chainmail Armor";
            armorValue = 3;
            currentUser = user;
            sound = getClass().getResource("armor.wav");
        }
    }

    public static class PlateArmor extends Armor{
        PlateArmor(Entities user){
            name = "Plate Armor";
            armorValue = 5;
            currentUser = user;
            sound = getClass().getResource("armor.wav");
        }
    }
}
