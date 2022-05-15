import javax.swing.*;

public class Entities {

    String name, location;
    ImageIcon image;
    int hp;
    int attack, howMany = 1;
    int code;
    boolean isAlert, isAlive, isFriendly;
    int maxHP;
    Weapon currentWeapon;
    Armor currentArmor;
    Item[] playersInventory;
    boolean drinkFull;

    public void setHowMany(int howMany){}

    public static class Bear extends Entities{
        public Bear() {
            reset();
        }
        public void reset(){
            image = new ImageIcon(getClass().getClassLoader().getResource("bearSIZED.png"));
            name = "Bear";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "caveLook";
            code = 0;
        }
    }

    public static class Bandits extends Entities{
        public Bandits() {
            reset();
        }
        public void reset(){
            howMany = 1;
            image = new ImageIcon(getClass().getClassLoader().getResource("banditsSized.png"));
            name = "Group of Bandits";
            hp = howMany * 5;
            attack = howMany * 3;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "bandits";
            code = 1;
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 5;
            attack = howMany * 3;
        }
    }

    public static class Boar extends Entities{
        public Boar() {
            reset();
        }
        public void reset(){
            howMany = 1;
            image = new ImageIcon(getClass().getClassLoader().getResource("boarSIZED.png"));
            name = "Boar";
            hp = howMany * 10;
            attack = howMany * 2;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "";
            code = 2;
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 10;
            attack = howMany * 2;
        }
    }

    public static class darkKnight extends Entities{
        public darkKnight() {
            reset();
        }
        public void reset(){
            howMany = 1;
            image = new ImageIcon(getClass().getClassLoader().getResource("darkKnightSIZED.png"));
            name = "Dark Knight";
            hp = howMany * 50;
            attack = howMany * 12;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "stream";
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 50;
            attack = howMany * 10;
        }
    }

    public static class Vagabound extends Entities{
        public Vagabound() {
            reset();
        }
        public void reset(){
            howMany = 1;
            image = new ImageIcon(getClass().getClassLoader().getResource("vagaboundSIZED.png"));
            name = "Vagabound";
            hp = howMany * 20;
            attack = howMany * 6;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "";
            code = 3;
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 20;
            attack = howMany * 6;
        }
    }

    public static class Wolves extends Entities{
        public Wolves() {
            reset();
        }
        public void reset(){
            howMany = 1;
            image = new ImageIcon(getClass().getClassLoader().getResource("wolvesSIZED.png"));
            name = "Pack of Wolves";
            hp = howMany * 4;
            attack = howMany * 2;
            isAlert = false;
            isAlive = true;
            isFriendly = false;
            location = "";
            code = 4;
        }

        public void setHowMany(int howMany){
            this.howMany = howMany;
            hp = howMany * 4;
            attack = howMany * 2;
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
            location = "";
        }
    }

    public static class Shopkeeper extends Entities{
        public Shopkeeper() {
            reset();
        }
        public void reset(){
            name = "Shopkeeper";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = true;
            location = "";
        }
    }

    public static class Lord extends Entities{
        boolean gaveGun;
        public Lord() {
            reset();
        }
        public void reset(){
            name = "lord";
            hp = 20;
            attack = 5;
            isAlert = false;
            isAlive = true;
            isFriendly = true;
            location = "";
            gaveGun = false;
        }
    }

    public static class Guard extends Entities{
        public boolean gaveWrit, toldWrit;

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
            gaveWrit = false;
            toldWrit = false;
            location = "";
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
