public class Player extends Entities{
    int gold, level, XP, XPCap, XPNeeded;
    String objective,area;
    String[] objectives;
    

    Player(){
        reset();
    }

    public void reset() {
        drinkFull = false;
        playersInventory = new Item[6];
        hp = 15; //15
        maxHP = 15; //15
        gold = 10; //10
        level = 1;
        XP = 0;
        XPCap = 10 + ((level - 1) * 2);
        XPNeeded = XPCap - XP;
        currentWeapon = new Weapon.Fists(this);
        currentArmor = new Armor.Clothes(this);
        objectives = new String[]{"Explore the town, maybe make some friends...Who knows?", "Clear out the bandits to the north. Talk to Gate Guard when it's done.", "Talk to the town lord. How Exciting!", "Reach level 5. This might take awhile...\nEnemies are found in the forest outside of town.", "Defeat the mysterious knight near the stream. What's his deal, anyway? \nTalk to the lord when done.", "You have won the respect of the town! When you want to end the game, speak to the lord."};
        objective = objectives[0];
    }

    public void levelUp(){
        level++;
        maxHP += 5;
        XPCap += 10 + ((level - 1) * 5);
        hp = maxHP;
        drinkFull = false;
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
