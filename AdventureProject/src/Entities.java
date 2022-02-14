public class Entities {

    String name, location;
    int hp;
    int attack, howMany = 1;
    boolean isAlert, isAlive, isFriendly;
    int maxHP;
    Weapon currentWeapon;
    Item[] playersInventory;
    boolean drinkFull;

    public static class Bear extends Entities{
        public Bear() {
            reset();
        }
        public void reset(){
            name = "Bear";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "cave";
        }
    }

    public static class Bandits extends Entities{
        public Bandits() {
            reset();
        }
        public void reset(){
            name = "Bandits";
            hp = howMany * 5;
            attack = howMany * 3;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "bandits";
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 5;
            attack = howMany * 3;
        }
    }

    public static class Merchant extends Entities{
        public Merchant() {
            reset();
        }
        public void reset(){
            name = "Merchant";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = true;
            location = "cave";
        }
    }

    public static class Guard extends Entities{
        public Guard() {
            reset();
        }
        public void reset(){
            name = "Guard";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = true;
            location = "cave";
        }
    }

    public static class Crewman extends Entities{
        public Crewman() {
            reset();
        }
        public void reset(){
            name = "Crewman";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = true;
            location = "cave";
        }
    }

    public static class God extends Entities{
        public God() {
            reset();
        }
        public void reset(){
            name = "God";
            hp = 9999999;
            attack = 99999999;
            isAlert = true;
            isAlive = true;
            isFriendly = true;
            location = "";
            howMany = 2;
        }
    }

    public static class Nature extends Entities{
        public Nature() {
            reset();
        }
        public void reset(){
            name = "The Wilderness";
            hp = 9999999;
            attack = 99999999;
            isAlert = true;
            isAlive = true;
            isFriendly = true;
            location = "";
            howMany = 2;
        }
    }
}
