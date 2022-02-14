public class Weapon extends Item {
    int damage;


    public static class Fists extends Weapon{
        public Fists(Entities user){
            name = "Fists";
            damage = 4;
            currentUser = user;
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
