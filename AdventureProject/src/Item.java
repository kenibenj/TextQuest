import java.net.URL;

public class Item {

    String name;
    int healValue, price;
    Entities currentUser;
    URL sound;

    public Item useItem(){
        currentUser.hp = currentUser.hp + healValue;
        if(currentUser.hp>currentUser.maxHP){
            currentUser.hp = currentUser.maxHP;
        }
        return new NothingItem();
    }

    public static class Carrot extends Item{
        Carrot(Entities user) {
            name = "Carrot";
            healValue = 2;
            currentUser = user;
            price = 2;
            sound = getClass().getResource("eating.wav");
        }

        public Item useItem(){
            currentUser.hp = currentUser.hp + healValue;
            if(currentUser.hp>currentUser.maxHP){
                currentUser.hp = currentUser.maxHP;
            }
            return new NothingItem();
        }
    }

    public static class Cabbage extends Item{
        Cabbage(Entities user) {
            name = "Cabbage";
            healValue = 4;
            currentUser = user;
            sound = getClass().getResource("eating.wav");
        }

        public Item useItem(){
            currentUser.hp = currentUser.hp + healValue;
            if(currentUser.hp>currentUser.maxHP){
                currentUser.hp = currentUser.maxHP;
            }
            return new NothingItem();
        }
    }

    public static class Bread extends Item{
        Bread(Entities user) {
            name = "Bread";
            healValue = 7;
            currentUser = user;
            sound = getClass().getResource("eating.wav");
        }

        public Item useItem(){
            currentUser.hp = currentUser.hp + healValue;
            if(currentUser.hp>currentUser.maxHP){
                currentUser.hp = currentUser.maxHP;
            }
            return new NothingItem();
        }
    }

    public static class NothingItem extends Item{
        NothingItem() {
            name = "";
            sound = getClass().getResource("eating.wav");
        }

        public Item useItem(){
            return this;
        }
    }
}
