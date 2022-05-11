public class Player extends Entities{
    int gold, armor, level, XP, XPCap, XPNeeded;
    String objective,area;

    String[] objectives = {"Explore the town, maybe make some friends...Who knows?","Clear out the bandits to the north. Talk to Gate Guard when it's done.", "Talk to the Lord. \nHow Exciting!", "Reach level 5. \n\nThis might take awhile..."};

    Player(){
        reset();
    }

    public void reset() {
        drinkFull = false;
        playersInventory = new Item[6];
        hp = 15;
        maxHP = 15;
        gold = 10;
        armor = 0;
        level = 1;
        XP = 0;
        XPCap = 2;
        XPNeeded = XPCap - XP;
        currentWeapon = new Weapon.Fists(this);
        currentArmor = new Armor.Clothes(this);
        objective = objectives[0];
    }

    public void levelUp(){
        level++;
        maxHP += 5;
        XPCap += 10 + ((level - 1) * 2);
        hp = maxHP;
    }

    public boolean checkLevel(){
        if(this.XP >= this.XPCap) {
            levelUp();
            return true;
        }
        else{
            return false;
        }
    }
}
