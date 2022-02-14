public class Player extends Entities{
    int gold, armor, level, XP;
    String objective,area;

    String[] objectives = {"Explore the town, maybe make some friends...Who knows?","Clear out the bandits to the north"};

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
        currentWeapon = new Weapon.Fists(this);
        objective = objectives[0];
    }
}
