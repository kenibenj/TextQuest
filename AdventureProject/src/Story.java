import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class Story {
    Game game;
    GUI gui;
    VisibilityManager vm;
    Player player = new Player();
    boolean killBanditsComplete;

    Entities.Crewman crewman = new Entities.Crewman();
    Entities.Merchant merchant = new Entities.Merchant();
    Entities.Shopkeeper shopkeeper = new Entities.Shopkeeper();
    Entities.Guard guard = new Entities.Guard();
    Entities.Guard guardCastle = new Entities.Guard();
    Entities.Lord lord = new Entities.Lord();
    Entities.Bear bear = new Entities.Bear();
    Entities.Bandits bandits = new Entities.Bandits();
    Entities.darkKnight darkKnight = new Entities.darkKnight();
    Entities.God god = new Entities.God();
    Entities.Nature nature = new Entities.Nature();
    Entities foe;

    Item.NothingItem nothingItem = new Item.NothingItem();

    ImageIcon shipImage = new ImageIcon(getClass().getClassLoader().getResource("shipSIZED.png"));
    ImageIcon docksImage = new ImageIcon(getClass().getClassLoader().getResource("docksSIZED.png"));
    ImageIcon forestImage = new ImageIcon(getClass().getClassLoader().getResource("forestSIZED.png"));
    ImageIcon gatesImage = new ImageIcon(getClass().getClassLoader().getResource("gatesSIZED.png"));
    ImageIcon streamImage = new ImageIcon(getClass().getClassLoader().getResource("streamSIZED.png"));
    ImageIcon campImage = new ImageIcon(getClass().getClassLoader().getResource("campSIZED.png"));
    ImageIcon caveImage = new ImageIcon(getClass().getClassLoader().getResource("caveSIZED.png"));
    ImageIcon merchantImage = new ImageIcon(getClass().getClassLoader().getResource("marketSIZED.png"));
    ImageIcon townImage = new ImageIcon(getClass().getClassLoader().getResource("townSized.png"));
    ImageIcon castleGatesImage = new ImageIcon(getClass().getClassLoader().getResource("castleGatesSized.png"));
    ImageIcon forest1Image = new ImageIcon(getClass().getClassLoader().getResource("forest1SIZED.png"));
    ImageIcon forest2Image = new ImageIcon(getClass().getClassLoader().getResource("forest2SIZED.png"));
    ImageIcon forest3Image = new ImageIcon(getClass().getClassLoader().getResource("forest3SIZED.png"));
    ImageIcon forest4Image = new ImageIcon(getClass().getClassLoader().getResource("forest4SIZED.png"));
    ImageIcon forest5Image = new ImageIcon(getClass().getClassLoader().getResource("forestSIZED5.png"));
    ImageIcon forest6Image = new ImageIcon(getClass().getClassLoader().getResource("forest6SIZED.png"));
    ImageIcon lordImage = new ImageIcon(getClass().getClassLoader().getResource("lordSIZED.png"));
    ImageIcon shopImage = new ImageIcon(getClass().getClassLoader().getResource("shopSIZED.png"));

    ImageIcon[] forestImages = {forest1Image, forest2Image, forest3Image, forest4Image, forest5Image, forest6Image};
    String[] forestSayings = {"Nothing but birds and trees out here.", "Just how big is this forest anyways?", "How boring.", "At least it's peaceful, right?"};

    public Story(Game game, GUI gui, VisibilityManager vm){
        this.game = game;
        this.gui = gui;
        this.vm = vm;
    }

    public void selectPosition(String nextPosition) throws IOException, UnsupportedAudioFileException {
        gui.actionLabel.setText("");
        switch (nextPosition) {
            case "toTitle" -> toTitle();
            case "ship" -> ship();
            case "crewmanTalk" -> crewmanTalk();
            case "crewmanAttack" -> crewmanAttack();
            case "crewmanGossip" -> crewmanGossip();
            case "docks" -> docks();
            case "lookShip" -> lookShip();
            case "orphan" -> orphan();
            case "orphanKill" -> orphanKill();
            case "merchant" -> merchant();
            case "merchantGossip" -> merchantGossip();
            case "merchantSteal" -> merchantSteal();
            case "merchantBuy" -> merchantBuy();
            case "carrotBuy" -> carrotBuy();
            case "cabbageBuy" -> cabbageBuy();
            case "breadBuy" -> breadBuy();
            case "town" -> town();
            case "castle" -> castle();
            case "enterCastle" -> enterCastle();
            case "askWrit" -> askWrit();
            case "lord" -> lord();
            case "lordTwoYes" -> lordTwoYes();
            case "lordTwoMaybe" -> lordTwoMaybe();
            case "lordThreeYes" -> lordThreeYes();
            case "lordFourNo" -> lordFourNo();
            case "lordFourYes" -> lordFourYes();
            case "lordFive" -> lordFive();
            case "shop" -> shop();
            case "shopSteal" -> shopSteal();
            case "shopBuyWeapons" -> shopBuyWeapons();
            case "knifeBuy" -> knifeBuy();
            case "shortswordBuy" -> shortswordBuy();
            case "greatswordBuy" -> greatswordBuy();
            case "shopBuyArmor" -> shopBuyArmor();
            case "leatherBuy" -> leatherBuy();
            case "chainmailBuy" -> chainmailBuy();
            case "plateBuy" -> plateBuy();
            case "gates" -> gates();
            case "guardTalk" -> guardTalk();
            case "guardTalkTwo" -> guardTalkTwo();
            case "guardAttack" -> guardAttack();
            case "guardRumors" -> guardRumors();
            case "crossroads" -> crossroads();
            case "bandits" -> bandits();
            case "banditsTalk" -> banditsTalk();
            case "banditsSneakAttack" -> banditsSneakAttack();
            case "banditsAttack" -> banditsAttack();
            case "stream" -> stream();
            case "leftStream" -> leftStream();
            case "rightStream" -> rightStream();
            case "cave" -> cave();
            case "caveLeave" -> caveLeave();
            case "caveLook" -> caveLook();
            case "grabSword" -> grabSword();
            case "win" -> win();
            case "fight" -> fight();
            case "playerAttack" -> playerAttack();
            case "enemyAttack" -> enemyAttack();
            case "forest" -> forest();
            case "forestTwo" -> forestTwo();
            case "lose" -> vm.showDeathScreen(foe);
            case "ending" -> ending();
            case "winScreen" -> vm.showWinScreen();
        }
    }

    public void defaultSetup(){
        player.reset();
        bandits.reset();
        bear.reset();
        crewman.reset();
        merchant.reset();
        guard.reset();
        player.reset();
        lord.reset();
        guardCastle.reset();
        darkKnight.reset();
        shopkeeper.reset();
        killBanditsComplete = false;

        game.inventoryHandler.reset();

        for(int i = 0; i < player.playersInventory.length; i++){
            player.playersInventory[i] = nothingItem;
            game.gui.inventorySlots[i].setText(game.story.player.playersInventory[i].name);
        }

        game.inventoryHandler.addItem(new Item.Carrot(player));


        gui.goldLabel.setText("Gold: " + player.gold);
        gui.actionLabel.setText("");

    }

    public void ship(){
        gui.mainImageLabel.setIcon(shipImage);
        player.area = "ship";

        gui.prepareText("You are on the ship that ferried you into the town. A crewman is reeling the sails in.");

        gui.choices[0].setText("Walk off the ship");
        gui.choices[1].setText("Talk to the crewman");
        gui.choices[2].setText("Attack the crewman");
        gui.choices[3].setText("Look around the ship");

        game.position1 = "docks";
        game.position2 = "crewmanTalk";
        game.position3 = "crewmanAttack";
        game.position4 = "lookShip";
        vm.buttonsVisibility();
    }

    public void crewmanTalk(){
        gui.prepareText("Crewman: Piss off, I'm workin here!");

        gui.choices[0].setText("Attack the Crewman");
        gui.choices[1].setText("Ask about rumors");
        gui.choices[2].setText("Go back");
        gui.choices[3].setText("");

        game.position1 = "crewmanAttack";
        game.position2 = "crewmanGossip";
        game.position3 = "ship";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void crewmanAttack() throws IOException, UnsupportedAudioFileException {
        foe = crewman;
        crewman.isFriendly = false;
        gui.prepareText("Crewman: Oi! Knock it eoff! \n\nThe Crewman hits you and the blow knocks you back.");
        gui.actionLabel.setText("You receive 1 damage!");

        vm.setPlayerHP(-1);
        if(player.hp<=0){
            player.hp = 0;
            vm.showDeathScreen(foe);
        }
        else {
            gui.choices[0].setText("Attack the Crewman");
            gui.choices[1].setText("Ask about rumors");
            gui.choices[2].setText("Go back");
            gui.choices[3].setText("");

            game.position1 = "crewmanAttack";
            game.position2 = "crewmanGossip";
            game.position3 = "ship";
            game.position4 = "";
            vm.buttonsVisibility();
        }

        gui.hpLabelNumber.setText(String.valueOf(player.hp));
    }

    public void crewmanGossip(){

        if (crewman.isFriendly) {
            gui.prepareText("Crewman: I've heard some rumors about some bear to the east of the town, now bugger off now would 'ya?");
        }

        else{
            gui.prepareText("The crewman glares at you.\n\nHow spooky.");
        }

        gui.choices[0].setText("Attack crewman");
        gui.choices[1].setText("Leave");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "crewmanAttack";
        game.position2 = "ship";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void docks(){
        gui.mainImageLabel.setIcon(docksImage);
        player.area = "Docks";

        gui.prepareText("You are at the docks of the town. It is a very busy area, with" +
                " plenty of people moving about.\n\nYou see an orphan begging for money, " +
                "a woman at a food stand, as well as " +
                "the rest of the town in the distance.");

        gui.choices[0].setText("Go to the town");
        gui.choices[1].setText("Talk to orphan");
        gui.choices[2].setText("Talk to merchant");
        gui.choices[3].setText("Go back to ship");

        game.position1 = "town";
        game.position2 = "orphan";
        game.position3 = "merchant";
        game.position4 = "ship";
        vm.buttonsVisibility();
    }

    public void lookShip(){
        boolean hasKnife = false;

        for(Item item : player.playersInventory){
            if(item instanceof Weapon.Knife){
                hasKnife = true;
            }
        }
        if(!hasKnife) {
            gui.prepareText("You look around the ship and find a knife.");
            Weapon.Knife knife = new Weapon.Knife(player);
            gui.actionLabel.setText("You've picked up the knife!");
            game.inventoryHandler.addItem(knife);
        }
        else{
            gui.prepareText("There is nothing to be found.");
        }


        gui.choices[0].setText("Walk off the ship");
        gui.choices[1].setText("Talk to the crewman");
        gui.choices[2].setText("Attack the crewman");
        gui.choices[3].setText("");

        game.position1 = "docks";
        game.position2 = "crewmanTalk";
        game.position3 = "crewmanAttack";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void orphan(){
        gui.prepareText("The orphan asks for money.\nYou have nothing to give.\nYou feel bad. You should.");

        gui.choices[0].setText("Go back");
        gui.choices[1].setText("Kill orphan");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "docks";
        game.position2 = "orphanKill";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void orphanKill(){
        foe = god;
        vm.setPlayerHP(-999999999);

        gui.prepareText("You reach out to try and kill the orphan.\n\nGod strikes you down for being immoral.\n\nYou receive 999999999 damage.");

        gui.choices[0].setText("Continue");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "lose";
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void merchant(){
        gui.mainImageLabel.setIcon(merchantImage);
        gui.prepareText("Merchant Woman: Care to buy some carrots or cabbage?");

        gui.choices[0].setText("Buy from merchant");
        gui.choices[1].setText("Ask about gossip");
        gui.choices[2].setText("Steal from merchant");
        gui.choices[3].setText("Go back");

        game.position1 = "merchantBuy";
        game.position2 = "merchantGossip";
        game.position3 = "merchantSteal";
        game.position4 = "docks";
        vm.buttonsVisibility();
    }

    public void merchantBuy(){
        gui.prepareText("Merchant Woman: Isn't spending money fun?");

        gui.choices[0].setText("Buy carrot (1 Gp)");
        gui.choices[1].setText("Buy cabbage (2 Gp)");
        gui.choices[2].setText("Buy bread (3 Gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "carrotBuy";
        game.position2 = "cabbageBuy";
        game.position3 = "breadBuy";
        game.position4 = "docks";
        vm.buttonsVisibility();
    }

    public void carrotBuy(){
        if(player.gold >= 1) {
            gui.prepareText("Merchant Woman: I think those are good for your eyes!");

            game.inventoryHandler.addItem(new Item.Carrot(player));
            player.gold = player.gold - 1;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Merchant Woman: How do you not have a single piece of gold?");
        }

        gui.choices[0].setText("Buy carrot (1 Gp)");
        gui.choices[1].setText("Buy cabbage (2 Gp)");
        gui.choices[2].setText("Buy bread (3 Gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "carrotBuy";
        game.position2 = "cabbageBuy";
        game.position3 = "breadBuy";
        game.position4 = "docks";
        vm.buttonsVisibility();
    }

    public void cabbageBuy(){
        if(player.gold >= 2) {
            gui.prepareText("Merchant Woman: I think the purple ones look neat!");

            game.inventoryHandler.addItem(new Item.Cabbage(player));
            player.gold = player.gold - 2;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Merchant Woman: No gold, no food. Charity is for the priests!");
        }

        gui.choices[0].setText("Buy carrot (1 Gp)");
        gui.choices[1].setText("Buy cabbage (2 Gp)");
        gui.choices[2].setText("Buy bread (3 Gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "carrotBuy";
        game.position2 = "cabbageBuy";
        game.position3 = "breadBuy";
        game.position4 = "docks";
        vm.buttonsVisibility();
    }

    public void breadBuy(){
        if(player.gold >= 3) {
            gui.prepareText("Merchant Woman: I think this bread is fresh, too!");

            game.inventoryHandler.addItem(new Item.Bread(player));
            player.gold = player.gold - 3;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Merchant Woman: Hmmmmmm...\n\nSeems you're too poor.");
        }

        gui.choices[0].setText("Buy carrot (1 Gp)");
        gui.choices[1].setText("Buy cabbage (2 Gp)");
        gui.choices[2].setText("Buy bread (3 Gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "carrotBuy";
        game.position2 = "cabbageBuy";
        game.position3 = "breadBuy";
        game.position4 = "docks";
        vm.buttonsVisibility();
    }

    public void merchantGossip(){

        if(merchant.isFriendly){
            gui.prepareText("Merchant Woman: I heard there's a lost sword to the east of the town!\n\nHow convenient.");
        }
        else {
            gui.prepareText("The merchant has nothing to say to you.\n\nGuess you shouldn't have tried to steal that carrot.");
        }

        gui.choices[0].setText("Steal from merchant");
        gui.choices[1].setText("Leave");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "merchantSteal";
        game.position2 = "docks";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void merchantSteal() throws IOException, UnsupportedAudioFileException {
        int rand = new java.util.Random().nextInt(10);

        if(rand < 7) {
            foe = merchant;
            merchant.isFriendly = false;
            gui.prepareText("You feel a sharp pain as you are tying to stealthily take a carrot." +
                    "\n\nMerchant: You'll have to be quicker than that!" +
                    "\n\n");
            gui.actionLabel.setText("You receive 1 damage!");

            vm.setPlayerHP(-1);
            }

        else{
            gui.prepareText("Preoccupied with something else, the merchant doesn't see you quickly take a cabbage from her stall." +
                    "\n\nToo Easy.");
            gui.actionLabel.setText("You steal a Cabbage!");
            game.inventoryHandler.addItem(new Item.Cabbage(player));
        }

        if (player.hp <= 0) {
            player.hp = 0;
            vm.showDeathScreen(foe);
        } else {
            gui.choices[0].setText("Steal from merchant");
            gui.choices[1].setText("Ask about gossip");
            gui.choices[2].setText("Go back");
            gui.choices[3].setText("");

            game.position1 = "merchantSteal";
            game.position2 = "merchantGossip";
            game.position3 = "docks";
            game.position4 = "";
            vm.buttonsVisibility();
        }
    }

    public void shop(){
        gui.mainImageLabel.setIcon(shopImage);
        gui.prepareText("Shopkeeper: Arms and armor for sale!");

        gui.choices[0].setText("Buy weapons");
        gui.choices[1].setText("Buy armor");
        gui.choices[2].setText("Steal from shopkeeper");
        gui.choices[3].setText("Go back");

        game.position1 = "shopBuyWeapons";
        game.position2 = "shopBuyArmor";
        game.position3 = "shopSteal";
        game.position4 = "town";
        vm.buttonsVisibility();
    }

    public void shopBuyWeapons(){
        gui.prepareText("Shopkeeper: What can I get ya?");

        gui.choices[0].setText("Knife (2 Gp)");
        gui.choices[1].setText("Shortsword (7 Gp)");
        gui.choices[2].setText("Greatsword (20 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "knifeBuy";
        game.position2 = "shortswordBuy";
        game.position3 = "greatswordBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void knifeBuy(){
        if(player.gold >= 2) {
            gui.prepareText("Shopkeeper: Better at cutting carrots than people, but it'll do.");

            game.inventoryHandler.addItem(new Weapon.Knife(player));
            player.gold = player.gold - 2;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Knife (2 Gp)");
        gui.choices[1].setText("Shortsword (7 Gp)");
        gui.choices[2].setText("Greatsword (20 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "knifeBuy";
        game.position2 = "shortswordBuy";
        game.position3 = "greatswordBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void shortswordBuy(){
        if(player.gold >= 7) {
            gui.prepareText("Shopkeeper: That's a deadly piece of steel!");

            game.inventoryHandler.addItem(new Weapon.Shortsword(player));
            player.gold = player.gold - 7;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Knife (2 Gp)");
        gui.choices[1].setText("Shortsword (7 Gp)");
        gui.choices[2].setText("Greatsword (20 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "knifeBuy";
        game.position2 = "shortswordBuy";
        game.position3 = "greatswordBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void greatswordBuy(){
        if(player.gold >= 20) {
            gui.prepareText("Shopkeeper: When it comes to weapons, bigger is better!");

            game.inventoryHandler.addItem(new Weapon.Greatsword(player));
            player.gold = player.gold - 20;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Knife (2 Gp)");
        gui.choices[1].setText("Shortsword (7 Gp)");
        gui.choices[2].setText("Greatsword (20 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "knifeBuy";
        game.position2 = "shortswordBuy";
        game.position3 = "greatswordBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void shopBuyArmor(){
        gui.prepareText("Shopkeeper: What can I get ya?");

        gui.choices[0].setText("Leather (10 Gp)");
        gui.choices[1].setText("Chainmail (30 Gp)");
        gui.choices[2].setText("Plate (50 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "leatherBuy";
        game.position2 = "chainmailBuy";
        game.position3 = "plateBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void leatherBuy(){
        if(player.gold >= 10) {
            gui.prepareText("Shopkeeper: Better than nothing, I suppose.");

            game.inventoryHandler.addItem(new Armor.LeatherArmor(player));
            player.gold = player.gold - 10;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Leather (10 Gp)");
        gui.choices[1].setText("Chainmail (30 Gp)");
        gui.choices[2].setText("Plate (50 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "leatherBuy";
        game.position2 = "chainmailBuy";
        game.position3 = "plateBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void chainmailBuy(){
        if(player.gold >= 30) {
            gui.prepareText("Shopkeeper: Sure, yeah, whatever.");

            game.inventoryHandler.addItem(new Armor.ChainmailArmor(player));
            player.gold = player.gold - 30;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Leather (10 Gp)");
        gui.choices[1].setText("Chainmail (30 Gp)");
        gui.choices[2].setText("Plate (50 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "leatherBuy";
        game.position2 = "chainmailBuy";
        game.position3 = "plateBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void plateBuy(){
        if(player.gold >= 50) {
            gui.prepareText("Shopkeeper: Fine piece of armor, that is.");

            game.inventoryHandler.addItem(new Armor.PlateArmor(player));
            player.gold = player.gold - 50;
            gui.goldLabel.setText("Gold: " + player.gold);
        }
        else{
            gui.prepareText("Shopkeeper: No handouts here!");
        }

        gui.choices[0].setText("Leather (10 Gp)");
        gui.choices[1].setText("Chainmail (30 Gp)");
        gui.choices[2].setText("Plate (50 gp)");
        gui.choices[3].setText("Go back");

        game.position1 = "leatherBuy";
        game.position2 = "chainmailBuy";
        game.position3 = "plateBuy";
        game.position4 = "shop";
        vm.buttonsVisibility();
    }

    public void shopSteal() throws IOException, UnsupportedAudioFileException {
        foe = shopkeeper;
        int rand = new java.util.Random().nextInt(10);

        if(rand < 9) {
            foe = merchant;
            merchant.isFriendly = false;
            gui.prepareText("You feel a sharp pain as you are tying to stealthily take a knife." +
                    "\n\nShopkeeper: What do you think you're doing?" +
                    "\n\n");
            gui.actionLabel.setText("You receive 2 damage!");

            vm.setPlayerHP(-2);
        }

        else{
            gui.prepareText("Preoccupied with something else, the shopkeeper doesn't see you quickly take a shortsword from his display." +
                    "\n\nToo Easy.");
            gui.actionLabel.setText("You receive a Shortsword!");
            game.inventoryHandler.addItem(new Weapon.Shortsword(player));
        }

        if (player.hp <= 0) {
            player.hp = 0;
            vm.showDeathScreen(foe);
        } else {
            gui.choices[0].setText("Buy weapons");
            gui.choices[1].setText("Buy armor");
            gui.choices[2].setText("Steal from shopkeeper");
            gui.choices[3].setText("Go back");

            game.position1 = "shopBuyWeapons";
            game.position2 = "shopBuyArmor";
            game.position3 = "shopSteal";
            game.position4 = "town";
            vm.buttonsVisibility();
        }
    }

    public void town(){
        gui.mainImageLabel.setIcon(townImage);
        player.area = "Town";
        guardCastle.toldWrit = false;

        gui.prepareText("You are in the center of the town. You see the gates that lead out of the city, an arms and armor shop, and the town castle.");

        gui.choices[0].setText("Go to the gates");
        gui.choices[1].setText("Go to the castle");
        gui.choices[2].setText("Go to the shop");
        gui.choices[3].setText("Go back to the docks");

        game.position1 = "gates";
        game.position2 = "castle";
        game.position3 = "shop";
        game.position4 = "docks";
        vm.buttonsVisibility();

    }

    public void castle(){
        gui.mainImageLabel.setIcon(castleGatesImage);
        player.area = "Castle";

        gui.prepareText("You approach the gates of the town castle. There is a guard watching the front gate.\n\nGuard: You need a writ to come inside!");

        gui.choices[0].setText("Enter castle");
        gui.choices[1].setText("Ask about writ");
        gui.choices[2].setText("Go back to town");
        gui.choices[3].setText("");

        game.position1 = "enterCastle";
        game.position2 = "askWrit";
        game.position3 = "town";
        vm.buttonsVisibility();
    }

    public void enterCastle(){
        gui.mainImageLabel.setIcon(castleGatesImage);
        player.area = "Castle";

        if(guard.gaveWrit) {
            gui.prepareText("After showing the gaurd your writ, the gates of the castle slowly open.");

            gui.choices[0].setText("Go to the lord");
            gui.choices[1].setText("Go back to town");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "lord";
            game.position2 = "town";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
        else{
            gui.prepareText("Guard: Are you deaf? I said you need a writ to enter, fool!");

            gui.choices[0].setText("Enter castle");
            gui.choices[1].setText("Ask about writ");
            gui.choices[2].setText("Go back to town");
            gui.choices[3].setText("");

            game.position1 = "enterCastle";
            game.position2 = "askWrit";
            game.position3 = "town";
            vm.buttonsVisibility();
        }
    }

    public void lord(){
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        if(!lord.isAlert) {
            gui.prepareText("You enter a large chamber. You see the lord sitting by a table with a few advisors\n" +
                    "Lord: Greetings! I have heard that you resolved my town's bandit problems. Would you be " +
                    "interested in assisting with another one of my issues?");

            gui.choices[0].setText("'Yes'");
            gui.choices[1].setText("'Maybe later'");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "lordTwoYes";
            game.position2 = "lordTwoMaybe";
            lord.isAlert = true;
        }
        else if(!lord.isFriendly) {
            gui.prepareText("Lord: Have you finally come to help?");

            gui.choices[0].setText("'Yes'");
            gui.choices[1].setText("'Maybe later'");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "lordTwoYes";
            game.position2 = "lordTwoMaybe";
        }
        else if((player.objective == player.objectives[4]) & (player.objectives[4] != player.objectives[5])){
            gui.prepareText("Lord: Have you killed that wretched knight yet?");
            if(darkKnight.isAlive) {

                gui.choices[0].setText("'No'");
                gui.choices[1].setText("");
                gui.choices[2].setText("");
                gui.choices[3].setText("");

                game.position1 = "lordFourNo";
            }
            else{
                gui.choices[0].setText("'Yes'");
                gui.choices[1].setText("");
                gui.choices[2].setText("");
                gui.choices[3].setText("");

                game.position1 = "lordFourYes";
            }
        }
        else{
            gui.prepareText("Lord: Hello again! Have you come to finally end the game?");

            gui.choices[0].setText("End game");
            gui.choices[1].setText("Continue playing");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "winScreen";
            game.position2 = "castle";
        }
        vm.buttonsVisibility();
    }

    public void lordTwoYes(){
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        gui.prepareText("Lord: That is just amazing to hear! I need you to get rid of a certain knight who has been " +
                "harassing our town for quite some time. He only reveals himself to those he deems worthy of combat, but I'm sure you " +
                "shouldn't have any problem with that.");

        gui.choices[0].setText("'I'll do it'");
        gui.choices[1].setText("'Maybe later'");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "lordThreeYes";
        game.position2 = "lordTwoMaybe";
        vm.buttonsVisibility();
    }

    public void lordTwoMaybe(){
        lord.isFriendly = false;
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        gui.prepareText("Lord: Hmmmmm....\nWell if you won't help, I have no more to discuss with you. Begone!");

        gui.choices[0].setText("'Fine, I'll do it.'");
        gui.choices[1].setText("Leave castle");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "lordThreeYes";
        game.position2 = "castle";
        vm.buttonsVisibility();
    }

    public void lordThreeYes(){
        lord.isFriendly = true;
        player.objective = player.objectives[3];
        player.objectives[1] = player.objective;
        player.objectives[2] = player.objective;
        gui.actionLabel.setText("New Objective!");
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        gui.prepareText("Lord: Good luck!\n\nWhat a posh moron.");

        gui.choices[0].setText("Leave castle");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "castle";
        game.position2 = "";
        vm.buttonsVisibility();
    }

    public void lordFourNo(){
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        gui.prepareText("Lord: Then get on with it!");

        gui.choices[0].setText("Leave castle");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "castle";
        vm.buttonsVisibility();
    }

    public void lordFourYes(){
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";

        gui.prepareText("Lord: Splendid! Our town is in your debt, kind stranger.");

        gui.choices[0].setText("End game");
        gui.choices[1].setText("Continue playing");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "winScreen";
        game.position2 = "lordFive";
        vm.buttonsVisibility();
    }

    public void lordFive(){
        gui.mainImageLabel.setIcon(lordImage);
        player.area = "Castle";
        player.objective = player.objectives[5];
        player.objectives[0] = player.objectives[5];
        player.objectives[1] = player.objectives[5];
        player.objectives[2] = player.objectives[5];
        player.objectives[3] = player.objectives[5];
        player.objectives[4] = player.objectives[5];
        gui.actionLabel.setText(gui.actionLabel.getText() + "  ~   New Objective!");

        gui.prepareText("Lord: As your reward, you may have this strange weapon. Do be warned, it seems exceptionally dangerous!");
        game.inventoryHandler.addItem(new Weapon.LaserGun(player));

        gui.choices[0].setText("Leave castle");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "castle";
        vm.buttonsVisibility();
    }

    public void askWrit(){
        gui.mainImageLabel.setIcon(castleGatesImage);
        player.area = "Castle";
        if(player.objective != player.objectives[1]){
            player.objective = player.objectives[1];
            gui.actionLabel.setText("New Objective!");
        }

        if(guardCastle.toldWrit){
            gui.prepareText("Guard: Didn't I just tell you about that?");
        }
        else {
            gui.prepareText("Guard: The lord is busy dealing with outlaws, so maybe you could get a writ if you 'handled' the bandits up north.");
        }
        gui.choices[0].setText("Enter castle");
        gui.choices[1].setText("Ask about writ");
        gui.choices[2].setText("Go back to town");
        gui.choices[3].setText("");

        game.position1 = "enterCastle";
        game.position2 = "askWrit";
        game.position3 = "town";

        guardCastle.toldWrit = true;
        vm.buttonsVisibility();
    }

    public void gates(){
        gui.mainImageLabel.setIcon(gatesImage);
        player.area = "Gates";
        if(player.objective != player.objectives[1]){
            player.objective = player.objectives[1];
            gui.actionLabel.setText("New Objective!");
        }

        gui.prepareText("You approach the gates of the town. A guard is stationed there." +
                "\n\nGuard: Watch out traveller, there have been tales of bandits to the north. I just wish someone would" +
                " take care of them...");

        gui.choices[0].setText("Go outside town");
        gui.choices[1].setText("Talk to guard");
        gui.choices[2].setText("Go inside town");
        gui.choices[3].setText("");

        game.position1 = "crossroads";
        if(bandits.isAlive) {
            game.position2 = "guardTalk";
        }
        else{
            game.position2 = "guardTalkTwo";
        }
        game.position3 = "town";
        vm.buttonsVisibility();
    }

    public void guardTalk(){

        if(guard.isFriendly) {
            gui.prepareText("Guard: Greetings!");
        }

        if(!guard.isFriendly){
            gui.prepareText("Guard: You better not cause any trouble.");
        }

        gui.choices[0].setText("Ask about rumors");
        gui.choices[1].setText("Attack the guard");
        gui.choices[2].setText("Go back");
        gui.choices[3].setText("");

        game.position1 = "guardRumors";
        game.position2 = "guardAttack";
        game.position3 = "gates";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void guardTalkTwo(){

        if(!killBanditsComplete) {
            player.gold = player.gold + 3;
            gui.goldLabel.setText("Gold: " + player.gold);
            gui.actionLabel.setText("You receive " + 3 + " gold!");
            player.objective = player.objectives[2];
            player.objectives[1] = player.objectives[2];
            if(!(player.objective == player.objectives[4])) {
                gui.actionLabel.setText(gui.actionLabel.getText() + "  ~   New Objective!");
            }
            guard.gaveWrit = true;
            killBanditsComplete = true;
        }

        gui.prepareText("Guard: You got rid of the bandit camp? Thank you kind stranger! You should speak to the lord, " +
                "he is looking for someone with your talents. \n\nThe guard gives you a writ of passage to enter the castle.");

        gui.choices[0].setText("Ask about rumors");
        gui.choices[1].setText("Attack the guard");
        gui.choices[2].setText("Go back");
        gui.choices[3].setText("");

        game.position1 = "guardRumors";
        game.position2 = "guardAttack";
        game.position3 = "gates";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void guardRumors(){

        if(guard.isFriendly) {
            gui.prepareText("Guard: There is a creak that runs close to the town. Never drink from the right stream, I've heard!");
        }
        else{
            gui.prepareText("The guard glares at you. \n\nGuess he is still upset.");
        }
        gui.choices[0].setText("Attack the guard");
        gui.choices[1].setText("Go back");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "guardAttack";
        game.position2 = "gates";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void  guardAttack() throws IOException, UnsupportedAudioFileException {
        foe = guard;
        guard.isFriendly = false;
        gui.actionLabel.setText("You receive 3 damage!");

        if(!crewman.isFriendly) {
            gui.prepareText("Guard: I wouldn't do that.\n\nThe guard strikes you.\n\nYou wanna stop attacking everything you see?");
        }
        else{
            gui.prepareText("Guard: I wouldn't do that\n\nThe guard strikes you and knocks you back" +
                    "\n\nYou receive 3 damage.");
        }
        vm.setPlayerHP(-3);
        if(player.hp<=0){
            player.hp = 0;
            vm.showDeathScreen(foe);
        }
        else {

            gui.choices[0].setText("Attack the guard");
            gui.choices[1].setText("Leave");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "guardAttack";
            game.position2 = "gates";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
    }

    public void crossroads(){
        gui.mainImageLabel.setIcon(forestImage);
        player.area = "Crossroads";

        gui.prepareText("You are at the crossroads.\n\nBack south is the town.");

        gui.choices[0].setText("Go north");
        gui.choices[1].setText("Go east");
        gui.choices[2].setText("Go west");
        gui.choices[3].setText("Go south");

        game.position1 = "bandits";
        game.position2 = "cave";
        game.position3 = "stream";
        game.position4 = "gates";
        vm.buttonsVisibility();
    }

    public void bandits(){
        foe = bandits;

        gui.mainImageLabel.setIcon(campImage);
        player.area = "Bandit Camp";

        if(!bandits.isAlert && bandits.isAlive) {
            gui.prepareText("You see a few bandits gathered around a campfire. They haven't seen you yet.");

            gui.choices[0].setText("Attack bandits");
            gui.choices[1].setText("Sneak-attack bandits");
            gui.choices[2].setText("Talk to bandits");
            gui.choices[3].setText("Go back");

            game.position1 = "banditsAttack";
            game.position2 = "banditsSneakAttack";
            game.position3 = "banditsTalk";
            game.position4 = "crossroads";
            vm.buttonsVisibility();
        }

        if(bandits.isAlert && bandits.isAlive) {
            gui.prepareText("The bandits are still alarmed...\n\nAnd quite pissed, it seems!");

            gui.choices[0].setText("Attack bandits");
            gui.choices[1].setText("Go back");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "crossroads";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }

        if(!bandits.isAlive){

            gui.prepareText("The once noisy campfire is now silent, the area littered with the corpses of the bandits you have slain." +
                    "\n\nGood job!");

            gui.choices[0].setText("Enter forest");
            gui.choices[1].setText("Go back");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "forest";
            game.position2 = "crossroads";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
    }

    public void banditsTalk() throws IOException, UnsupportedAudioFileException {
        bandits.isAlert = true;
        bandits.setHowMany(3);
        foe = bandits;

        gui.prepareText("You casually walk up to the bandits to talk. They see you and before you can speak they are all alarmed." +
                " One of the bandits slashes at you.\n\nYou receive 3 points of damage.");

        vm.setPlayerHP(-3);
        if(player.hp<=0){
            player.hp = 0;
            vm.showDeathScreen(foe);
        }

        gui.choices[0].setText("Fight bandits");
        gui.choices[1].setText("Run");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "banditsAttack";
        game.position2 = "crossroads";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void banditsSneakAttack(){
        bandits.isAlert = true;
        bandits.setHowMany(2);
        foe = bandits;

        gui.prepareText("You sneak up on the bandits and manage to quickly defeat one before the others notice.\n\nBandit: What the...?");

        gui.choices[0].setText("Fight bandits");
        gui.choices[1].setText("Run");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "fight";
        game.position2 = "crossroads";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void banditsAttack(){
        bandits.isAlert = true;
        bandits.setHowMany(3);
        foe = bandits;

        gui.prepareText("You charge at the bandits blindly. There are three of them.\n\nBandit: What the...?");

        gui.choices[0].setText("Fight bandits");
        gui.choices[1].setText("Run");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "fight";
        game.position2 = "crossroads";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void stream(){
        foe = darkKnight;
        gui.mainImageLabel.setIcon(streamImage);
        player.area = "Stream";

        gui.prepareText("You come across a creak. There is a fork in the stream, with 2 diverging streams.");

        gui.choices[0].setText("Drink right stream");
        gui.choices[1].setText("Drink left stream");
        gui.choices[2].setText("Go back");
        gui.choices[3].setText("");

        game.position1 = "rightStream";
        game.position2 = "leftStream";
        game.position3 = "crossroads";
        game.position4 = "";

        if((player.objective == player.objectives[4]) & (darkKnight.isAlive)){
            gui.mainImageLabel.setIcon(darkKnight.image);
            gui.prepareText("You walk near the creak. You see a knight in dark armor brandishing his sword.\n\n " +
                    "Dark Knight: I have heard rumors of your skills in combat. Let's see if they are true!");

            gui.choices[0].setText("Fight");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
        vm.buttonsVisibility();
    }


    public void leftStream(){

        if(!player.drinkFull) {
            gui.prepareText("You drink from the left stream. The water is cool and refreshing.\n\nYou regain 3 health.");
            vm.setPlayerHP(3);
            player.drinkFull = true;
        }
        else{
            gui.prepareText("You can't just keep drinking water.");
        }

        gui.choices[0].setText("Go back");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "stream";
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void rightStream() throws IOException, UnsupportedAudioFileException {
        foe = nature;

        if(!player.drinkFull) {
            gui.prepareText("You drink from the right stream. Something about the water tastes off, and you feel a pain" +
                    " in your stomach.\n\nYou receive 2 damage.");

            vm.setPlayerHP(-2);
            if(player.hp<=0){
                player.hp = 0;
                vm.showDeathScreen(foe);
            }

            player.drinkFull = true;
        }
        else{
            gui.prepareText("You can't just keep drinking water.");
        }

        gui.choices[0].setText("Go back");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "stream";
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void cave(){

        gui.mainImageLabel.setIcon(caveImage);
        player.area = "Cave";

        foe = bear;
        if(bear.isAlive) {
            gui.prepareText("You come across a cave. You see a longsword in it.");

            gui.choices[0].setText("Grab longsword");
            gui.choices[1].setText("Look around cave");
            gui.choices[2].setText("Go back");
            gui.choices[3].setText("");

            game.position1 = "grabSword";
            game.position2 = "caveLook";
            game.position3 = "caveLeave";
            game.position4 = "";
            vm.buttonsVisibility();
        }
        else{
            gui.prepareText("The cave has crumbled in on itself, sealing the entrance.");

            gui.choices[0].setText("Go back");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "crossroads";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
    }

    public void caveLeave(){
        gui.mainImageLabel.setIcon(caveImage);
        if(!bear.isAlive) {
            bear.isAlert = true;

            gui.prepareText("As you leave the cave crumbles in on itself.\n\nLooks like you won't be going" +
                    " back in there.");

            gui.choices[0].setText("Go back");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "crossroads";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
        else{
            gui.prepareText("You flee the cave. \n\n It was fairly spooky, after all.");

            gui.choices[0].setText("Go back");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "crossroads";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
        vm.buttonsVisibility();
    }

    public void caveLook(){
        gui.mainImageLabel.setIcon(caveImage);

        if(bear.isAlive && !(bear.isAlert)) {
            gui.prepareText("You look around the cave and see a bear. The bear hasn't noticed you yet.");


            gui.choices[0].setText("Fight bear");
            gui.choices[1].setText("Grab longsword");
            gui.choices[2].setText("Go back");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "grabSword";
            game.position3 = "caveLeave";
            game.position4 = "";
        }
        else if(bear.isAlert && bear.isAlive) {
            gui.prepareText("You look around the cave and see a bear. The bear is now roaming the cave aggressively.");


            gui.choices[0].setText("Fight bear");
            gui.choices[1].setText("Grab longsword");
            gui.choices[2].setText("Go back");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "grabSword";
            game.position3 = "caveLeave";
            game.position4 = "";
        }
        else{
            gui.prepareText("The cave seems to be empty now. The longsword glimmers a bit.");


            gui.choices[0].setText("Grab longsword");
            gui.choices[1].setText("Go back");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "grabSword";
            game.position2 = "caveLeave";
            game.position3 = "";
            game.position4 = "";
        }
        vm.buttonsVisibility();
    }

    public void grabSword() throws IOException, UnsupportedAudioFileException {
        gui.mainImageLabel.setIcon(caveImage);
        bear.isAlert = true;

        if (bear.isAlive) {
            gui.prepareText("You try to grab the sword, but a bear slashes at you before you can.\n\nDamn.");
            gui.actionLabel.setText("You receive 3 damage!");

            vm.setPlayerHP(-3);
            if (player.hp <= 0) {
                player.hp = 0;
                vm.showDeathScreen(foe);
            }

            gui.choices[0].setText("Fight bear");
            gui.choices[1].setText("Run");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "caveLeave";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
        else {
            gui.prepareText("You pick up the sword. It looks a little rusty, but it's better than nothing.");
            gui.actionLabel.setText("You receive a Longsword!");
            game.inventoryHandler.addItem(new Weapon.Longsword(player));

            gui.choices[0].setText("Leave cave");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "caveLeave";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
            vm.buttonsVisibility();
        }
    }

    public void win(){
        String linkingVerb = "is";
        int goldDrop = new Random().nextInt(10);
        player.gold += goldDrop;
        gui.goldLabel.setText("Gold: " + Integer.toString(player.gold));
//        if(foe.howMany>1){
//            linkingVerb = "are";
//        }
//        else{
//            linkingVerb = "is";
//        }

        gui.prepareText("The " + foe.name + " " + linkingVerb + " dead.\n\nYou're quite good at this whole killing thing!");
        gui.actionLabel.setText("You receive " + goldDrop + " gold!");

        if(player.checkLevel()){
            if(gui.actionLabel.getText() == ""){
                gui.actionLabel.setText("Level Up!");
                vm.setPlayerHP(player.maxHP);
            }
            gui.hpLabelNumber.setText(Integer.toString(player.hp));
           gui.actionLabel.setText(gui.actionLabel.getText() + "  ~   Level Up!");

            if(player.level >= 5){
                player.objective = player.objectives[4];
                player.objectives[0] = player.objectives[4];
                player.objectives[1] = player.objectives[4];
                player.objectives[2] = player.objectives[4];
                player.objectives[3] = player.objectives[4];
                gui.actionLabel.setText("New Objective!");
            }
        }

        gui.choices[0].setText("Leave");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = foe.location;
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void fight(){
        foe.isAlert = true;
        gui.prepareText(foe.name+ " HP:  " + foe.hp + "\n\nWhat do you do?");
        gui.mainImageLabel.setIcon(foe.image);

        if(foe == darkKnight) {
            gui.choices[0].setText("Fight");
            gui.choices[1].setText("");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "playerAttack";
        }

        else{
            gui.choices[0].setText("Fight");
            gui.choices[1].setText("Run");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "playerAttack";
            game.position2 = foe.location;
            game.position3 = "";
            game.position4 = "";
        }
        vm.buttonsVisibility();
    }

    public void playerAttack(){

        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        gui.prepareText("You attacked the " + foe.name.toLowerCase() + " and gave " + playerDamage + " damage!");
        gui.actionLabel.setText("You dealt " + playerDamage + " damage!");

        foe.hp = foe.hp - playerDamage;

        gui.choices[0].setText("Continue fight");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");
        vm.buttonsVisibility();

        if(foe.hp>0) {
            game.position1 = "enemyAttack";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
        else if(foe.hp<=0){
            foe.isAlive = false;
            game.position1 = "win";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
    }

    public void enemyAttack(){

        int enemyFinalDamage, enemyDamage;

        enemyDamage = new java.util.Random().nextInt(foe.attack);
        enemyFinalDamage = enemyDamage - player.currentArmor.armorValue;
        if(enemyFinalDamage < 0){
            enemyFinalDamage = 0;
        }

        vm.setPlayerHP(-enemyFinalDamage);
        player.XP += ((enemyDamage + 1)/2);

        gui.prepareText("The " + foe.name + "  attacked you for " + (enemyFinalDamage) + " damage!");
        gui.actionLabel.setText("You receive " + enemyFinalDamage + " damage!");

        gui.choices[0].setText("Continue");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");
        vm.buttonsVisibility();

        if(player.hp>0) {
            game.position1 = "fight";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
        else if(player.hp<=0){
            game.position1 = "lose";
            game.position2 = "";
            game.position3 = "";
            game.position4 = "";
        }
    }

    public void forest(){
        int forestType = new Random().nextInt(6);
        gui.mainImageLabel.setIcon(forestImages[forestType]);
        gui.prepareText("You begin to wander into the forest. Be careful, there are many foes out here...");

        gui.choices[0].setText("Keep wandering");
        gui.choices[1].setText("Go back");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "forestTwo";
        game.position2 = "bandits";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void forestTwo(){
        int forestImageType = new Random().nextInt(6);
        gui.mainImageLabel.setIcon(forestImages[forestImageType]);
        int enemyFound = new Random().nextInt(100);
        if(enemyFound > 70){
            int enemyType = new Random().nextInt(5);
            switch (enemyType){
                case 0: foe = new Entities.Bear(); break;
                case 1: foe = new Entities.Bandits(); foe.setHowMany(new Random().nextInt(3) + 3); break;
                case 2: foe = new Entities.Boar(); break;
                case 3: foe = new Entities.Vagabound(); break;
                case 4: foe = new Entities.Wolves(); foe.setHowMany(new Random().nextInt(5) + 4); break;
            }
            gui.mainImageLabel.setIcon(foe.image);
            foe.location = "forest";
            gui.prepareText("You encounter a " + foe.name + "!");

            gui.choices[0].setText("Fight " + foe.name);
            gui.choices[1].setText("Go back");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "fight";
            game.position2 = "bandits";
            game.position3 = "";
            game.position4 = "";
        }

        else {
            int forestMessageType = new Random().nextInt(4);
            gui.prepareText("You keep wandering into the forest. \n\n" + forestSayings[forestMessageType]);

            gui.choices[0].setText("Keep wandering");
            gui.choices[1].setText("Go back");
            gui.choices[2].setText("");
            gui.choices[3].setText("");

            game.position1 = "forestTwo";
            game.position2 = "bandits";
            game.position3 = "";
            game.position4 = "";
        }
        vm.buttonsVisibility();
    }

    public void lose(){

        gui.prepareText("You are dead. How sad.\n\n<Game Over>");

        gui.choices[0].setText("Go back title");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "toTitle";
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void ending() {

        gui.prepareText("Guard: You have killed the bandits? Thank you, stranger!\n\nWhat a good samaritan you are.\n\n");

        gui.choices[0].setText("Continue");
        gui.choices[1].setText("");
        gui.choices[2].setText("");
        gui.choices[3].setText("");

        game.position1 = "winScreen";
        game.position2 = "";
        game.position3 = "";
        game.position4 = "";
        vm.buttonsVisibility();
    }

    public void toTitle(){

        gui.timer.stop();
        defaultSetup();
        vm.showTitleScreen();
    }
}
