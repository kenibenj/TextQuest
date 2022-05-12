import java.net.URL;

public class Weapon extends Item {
    int damage;

    public static class Fists extends Weapon{
        public Fists(Entities user){
            name = "Fists";
            damage = 4;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }

    }

    public static class Knife extends Weapon{
        public Knife(Entities user){
            name = "Knife";
            damage = 6;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }
    }

    public static class Longsword extends Weapon{
        public Longsword(Entities user){
            name = "Longsword";
            damage = 10;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }
    }

    public static class Shortsword extends Weapon{
        public Shortsword(Entities user){
            name = "Shortsword";
            damage = 8;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }
    }

    public static class Greatsword extends Weapon{
        public Greatsword(Entities user){
            name = "Greatsword";
            damage = 14;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }
    }

    public static class LaserGun extends Weapon{
        public LaserGun(Entities user){
            name = "Laser gun";
            damage = 1000;
            currentUser = user;
            sound = getClass().getResource("weapon.wav");
        }

        public Item useItem(){
            Item switchWeapon = currentUser.currentWeapon;
            if (switchWeapon instanceof Weapon.Fists){
                switchWeapon = new NothingItem();
            }
            currentUser.currentWeapon = this;
            return switchWeapon;
        }
    }
}
