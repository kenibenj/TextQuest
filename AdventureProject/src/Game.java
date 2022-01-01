import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    JFrame window;
    Container container;
    JPanel titleScreen, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleScreenLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    JButton startButton,choice1,choice2,choice3,choice4;
    Color gamerGrey = new Color(40, 40, 40);
    Font standard = new Font("Times New Roman", Font.PLAIN, 30);
    JTextArea mainTextArea;
    int playerHP, bearHP;
    String weapon, position;
    titleScreenHandler tsHandler = new titleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    boolean merchantReputation, crewmenReputation, guardReputation, banditsAlive, drinkFull, bearAlert;

    public static void main(String[] args) {
        new Game();
    }
    public Game(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(gamerGrey);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        titleScreen = new JPanel();
        titleScreen.setBounds(100, 100, 600, 150);
        titleScreen.setBackground(gamerGrey);
        titleScreenLabel = new JLabel("Adventure");
        titleScreenLabel.setForeground(Color.white);
        titleScreenLabel.setFont(new Font("Times New Roman", Font.PLAIN, 90));

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(gamerGrey);

        startButton = new JButton("Start");
        startButton.setBackground(gamerGrey);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);


        titleScreen.add(titleScreenLabel);
        startButtonPanel.add(startButton);

        container.add(startButtonPanel);
        container.add(titleScreen);
    }

    public void createGameScreen(){
        titleScreen.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,600,250);
        mainTextPanel.setBackground(gamerGrey);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea("Main Text Area");
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(gamerGrey);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250,350,300,150);
        choiceButtonPanel.setBackground(gamerGrey);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        container.add(choiceButtonPanel);

        choice1 = new JButton();
        choice1.setBackground(gamerGrey);
        choice1.setForeground(Color.white);
        choice1.setFont(standard);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choice1.setFocusPainted(false);
        choiceButtonPanel.add(choice1);

        choice2 = new JButton();
        choice2.setBackground(gamerGrey);
        choice2.setForeground(Color.white);
        choice2.setFont(standard);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choice2.setFocusPainted(false);
        choiceButtonPanel.add(choice2);

        choice3 = new JButton();
        choice3.setBackground(gamerGrey);
        choice3.setForeground(Color.white);
        choice3.setFont(standard);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choice3.setFocusPainted(false);
        choiceButtonPanel.add(choice3);

        choice4 = new JButton();
        choice4.setBackground(gamerGrey);
        choice4.setForeground(Color.white);
        choice4.setFont(standard);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choice4.setFocusPainted(false);
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(100,15,600,50);
        playerPanel.setBackground(gamerGrey);
        playerPanel.setLayout(new GridLayout(1,4));
        container.add(playerPanel);

        hpLabel = new JLabel("HP");
        hpLabel.setFont(standard);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(standard);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(standard);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponLabelName = new JLabel();
        weaponLabelName.setFont(standard);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        playerSetup();
    }

    public void playerSetup(){
        playerHP = 15;
        bearHP = 10;
        weapon = "Fists";
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText(String.valueOf(playerHP));
        merchantReputation = true;
        crewmenReputation = true;
        guardReputation = true;
        banditsAlive = true;
        drinkFull = false;
        bearAlert = false;

        ship();
    }

    public void ship(){
        position = "ship";
        mainTextArea.setText("You are on the ship that ferried you into the town. A crewmen is reeling the sails in.");

        choice1.setText("Walk off the ship");
        choice2.setText("Talk to the crewmen");
        choice3.setText("Attack the crewmen");
        choice4.setText("Look around the ship");
    }

    public void talkCrewmen(){
        position = "talkCrewmen";
        mainTextArea.setText("Crewmen: Piss off, I'm workin here!");

        choice1.setText("Attack the Crewmen");
        choice2.setText("Ask about rumors");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void attackCrewmen(){
        position = "attackCrewmen";
        mainTextArea.setText("Crewmen: Oi! Knock it eoff! \n\nThe Crewmen hits you and the blow knocks you back" +
                "\n\nYou receive 1 damage");

        playerHP = playerHP - 1;
        hpLabelNumber.setText(Integer.toString(playerHP));
        crewmenReputation = false;

        choice1.setText("Attack the Crewmen");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void docks(){
        position = "docks";
        mainTextArea.setText("You are at the docks of the town. It is a very busy area, with" +
                " plenty of people moving about.\n\nYou see an orphan begging for money, " +
                "a woman at a merchant's stand, as well as " +
                "the gates that lead outside the city");

        choice1.setText("Go to the gates");
        choice2.setText("Talk to the orphan");
        choice3.setText("Talk to the merchant");
        choice4.setText("Go back to the ship");
    }

    public void lookShip(){
        position = "lookShip";
        String areaItem = "Knife";
        if(weapon != areaItem) {
            mainTextArea.setText("You look around the ship and find a knife\n\nYou take the knife.");
            weapon = areaItem;
            weaponLabelName.setText(weapon);
        }
        else{
            mainTextArea.setText("There is nothing to be found.");
        }

        choice1.setText("Walk off the Ship");
        choice2.setText("Talk to the crewmen");
        choice3.setText("Attack the crewmen");
        choice4.setText("");
    }

    public void orphan(){
        position = "orphan";
        mainTextArea.setText("The orphan asks for money.\nYou have nothing to give.\nYou feel bad. You should.");

        choice1.setText("Leave");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void merchant(){
        position = "merchant";
        mainTextArea.setText("Merchant Woman: Care to buy some carrots or cabbage?");

        choice1.setText("Steal from merchant");
        choice2.setText("Ask about gossip");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void merchantGossip(){
        position = "merchantGossip";

        if(merchantReputation){
            mainTextArea.setText("Merchant Woman: I heard there's a lost sword to the east of the town!\n\nHow convenient");
        }
        else {
            mainTextArea.setText("The merchant has nothing to say to you.\n\nGuess you shouldn't have tried to steal that carrot");
        }

        choice1.setText("Steal from the merchant");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void merchantSteal(){
        position = "merchantSteal";
        mainTextArea.setText("You feel a sharp pain as you are tying to stealthily take a carrot" +
                "\n\nMerchant: You'll have to be quicker than that!" +
                "\n\nYou receive 1 damage");

        playerHP = playerHP - 1;
        hpLabelNumber.setText(Integer.toString(playerHP));
        merchantReputation = false;

        choice1.setText("Steal from merchant");
        choice2.setText("Ask about gossip");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void crewmenGossip(){
        position = "crewmenGossip";

        if (crewmenReputation) {
            mainTextArea.setText("Crewmen: I've 'eard some rumors about some sword or sumtin to the east of the town, now bugger away now would 'ya?");
        }

        else{
            mainTextArea.setText("The crewmen glares at you\n\nHow spooky.");
        }

        choice1.setText("Attack crewmen");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void gates(){
        position = "gates";

        mainTextArea.setText("You approach the gates that lead outside the town. A guard is stationed there." +
                "\n\nGuard: Watch out traveller, there have been tales of bandits to the north");

        choice1.setText("Go through gates");
        choice2.setText("Attack the guard");
        choice3.setText("Talk to guard");
        choice4.setText("Go back to docks");
    }

    public void guardTalk(){
        position = "guardTalk";

        if(guardReputation) {
            mainTextArea.setText("Guard: There is a creak that runs close to the town. \nNever drink from the left stream, I've heard!");
        }
        else{
            mainTextArea.setText("The guard glares at you. \n\nGuess he is still upset.");
        }
        choice1.setText("Attack the guard");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void  guardAttack(){
        position = "guardAttack";
        guardReputation = false;

        if(!guardReputation && !crewmenReputation) {
            mainTextArea.setText("Guard: I wouldn't do that.\n\nThe guard strikes you, and" +
                    " you receive 3 damage.\n\nYou wanna stop attacking everything you see?");
        }
        else{
            mainTextArea.setText("Guard: I wouldn't do that\n\nThe guard strikes you and knocks you back" +
                    "\n\nYou receive 3 damage.");
        }
        playerHP = playerHP - 3;
        hpLabelNumber.setText(Integer.toString(playerHP));

        choice1.setText("Attack the guard");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void crossRoads(){
        position = "crossRoads";

        mainTextArea.setText("You are at the crossroads. You hear distant voices to the north.\n\nBack south is the town");

        choice1.setText("Go north");
        choice2.setText("Go east");
        choice3.setText("Go west");
        choice4.setText("Go south");
    }

    public void bandits(){
        position = "bandits";

        mainTextArea.setText("You approach the voices and see a few bandits gathered around a campfire. They haven't seen you yet");

        choice1.setText("Attack bandits");
        choice2.setText("Sneak-attack bandits");
        choice3.setText("Talk to bandits");
        choice4.setText("Go back");
    }

    public void banditsDead(){
        position = "banditsDead";

        mainTextArea.setText("All the bandits are dead.\n\n...\n\n Good job!");

        choice1.setText("-Go back");
        choice2.setText(" ");
        choice3.setText("");
        choice4.setText("");
    }

    public void stream(){
        position = "stream";

        mainTextArea.setText("You come across a creak. There is a fork in the stream, with 2 diverging streams");

        choice1.setText("Drink right stream");
        choice2.setText("Drink left stream");
        choice3.setText("Go back");
        choice4.setText("");
    }


    public void leftStream(){
        position = "leftStream";

        if(!drinkFull) {
            mainTextArea.setText("You drink from the left stream. The water is cool and refreshing.\n\nYou regain 1 health");
            playerHP = playerHP + 1;
            if(playerHP >15){
                playerHP = 15;
            }
            hpLabelNumber.setText(Integer.toString(playerHP));
            drinkFull = true;
        }
        else{
            mainTextArea.setText("You can't just keep drinking water.");
        }
        choice1.setText("Drink right stream");
        choice2.setText("Go back");
        choice3.setText("");
        choice4.setText("");
    }

    public void rightStream(){
        position = "rightStream";

        if(!drinkFull) {
            mainTextArea.setText("You drink from the right stream. Something about the water tastes off, and you feel a pain" +
                    "in your stomach\n\nYou receive 1 damage");

            playerHP = playerHP - 1;
            hpLabelNumber.setText(Integer.toString(playerHP));
            drinkFull = true;
        }
        else{
            mainTextArea.setText("You can't just keep drinking water.");
        }
        choice1.setText("Drink left stream");
        choice2.setText("Go back");
        choice3.setText("");
        choice4.setText("");
    }

    public void cave(){
        position = "cave";

        if(!bearAlert) {
            mainTextArea.setText("You come across a cave. You see a longsword in it.");

            choice1.setText("Grab longsword");
            choice2.setText("Look around cave");
            choice3.setText("Go back");
            choice4.setText("");
        }
        else{
            mainTextArea.setText("The cave has crumbled in on itself, sealing the entrance.");

            choice1.setText("Go back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        }
    }

    public void caveLeave(){
        position = "caveLeave";
        bearAlert = true;

        mainTextArea.setText("As you leave the cave crumbles in on itself.\n\nLooks like you won't be going" +
                " back in there.");

        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void lookCave(){
        position = "lookCave";

        mainTextArea.setText("You look around the cave and see a bear. The bear hasn't noticed you yet");


        choice1.setText("Fight bear");
        choice2.setText("Grab longsword");
        choice3.setText("Go back");
        choice4.setText("");
    }

    public void grabSword(){
        position = "grabSword";

        mainTextArea.setText("You grab the sword, but the noise alerts a bear in the cave.\n\nThe bear slashes at you" +
                "\n\nYou receive 3 points of damage");

        weapon = "longsword";
        weaponLabelName.setText(weapon);
        playerHP = playerHP - 3;
        hpLabelNumber.setText(Integer.toString(playerHP));

        choice1.setText("Fight bear");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }

    public void fightBear(){
        position = "fightBear";
        mainTextArea.setText("bear HP:  "+ bearHP + "\n\nWhat do you do?");

        choice1.setText("Fight bear");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }



    public class titleScreenHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {

            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {

            String choice = event.getActionCommand();

            switch(position){
                case "ship":
                    switch (choice){
                        case "c1" : docks(); break;
                        case "c2" : talkCrewmen(); break;
                        case "c3" : attackCrewmen();break;
                        case "c4" : lookShip();break;
                    }
                    break;
                case "talkCrewmen":
                    switch (choice){
                        case "c1" : attackCrewmen(); break;
                        case "c2" : crewmenGossip(); break;
                        case "c3" : ship(); break;
                    }
                    break;
                case "attackCrewmen":
                    switch (choice){
                        case "c1" : attackCrewmen(); break;
                        case "c2" : ship(); break;
                    }
                    break;
                case "crewmenGossip":
                    switch (choice){
                        case "c1" : attackCrewmen(); break;
                        case "c2" : ship(); break;
                    }
                    break;
                case "docks":
                    switch (choice){
                        case "c1" : gates(); break;
                        case "c2" : orphan(); break;
                        case "c3" : merchant(); break;
                        case "c4" : ship(); break;
                    }
                    break;
                case "lookShip":
                    switch (choice){
                        case "c1" : docks(); break;
                        case "c2" : talkCrewmen();break;
                        case "c3" : attackCrewmen();break;
                    }
                    break;
                case "orphan":
                    switch (choice){
                        case "c1" : docks();break;
                    }
                    break;
                case "merchant":
                    switch (choice){
                        case "c1" : merchantSteal();break;
                        case "c2" : merchantGossip();break;
                        case "c3" : docks();break;
                    }
                    break;
                case "merchantGossip":
                    switch (choice){
                        case "c1" : merchantSteal();break;
                        case "c2" : docks();break;
                    }
                    break;
                case "merchantSteal":
                    switch (choice){
                        case "c1" : merchantSteal();break;
                        case "c2" : merchantGossip();break;
                        case "c3" : docks();break;
                    }
                    break;
                case "gates":
                    switch (choice){
                        case "c1" : crossRoads(); break;
                        case "c2" : guardAttack(); break;
                        case "c3" : guardTalk(); break;
                        case "c4" : docks();break;
                    }
                    break;
                case "guardAttack":
                    switch (choice){
                        case "c1" : guardAttack();break;
                        case "c2" : gates();break;
                    }
                    break;
                case "guardTalk":
                    switch (choice){
                        case "c1" : guardAttack();break;
                        case "c2" : gates();break;
                    }
                    break;
                case "crossRoads":
                    switch (choice){
                        case "c1" : bandits(); break;
                        case "c2" : cave(); break;
                        case "c3" : stream();break;
                        case "c4" : gates(); break;
                    }
                    break;
                case "stream":
                    switch (choice){
                        case "c1" : rightStream(); break;
                        case "c2" : leftStream(); break;
                        case "c3" : gates();break;
                    }
                    break;
                case "rightStream":
                    switch (choice){
                        case "c1" : leftStream(); break;
                        case "c2" : stream(); break;
                    }
                    break;
                case "leftStream":
                    switch (choice){
                        case "c1" : rightStream(); break;
                        case "c2" : stream(); break;
                    }
                    break;
                case "bandits":
                    switch (choice){
                        case "c1" : break;
                        case "c2" : break;
                        case "c3" : break;
                        case "c4" : crossRoads(); break;
                    }
                    break;
                case "cave":
                    switch (choice){

                        case "c1" : if (!bearAlert){grabSword(); break;} else{crossRoads(); break;}
                        case "c2" : if (!bearAlert){lookCave(); break;} else{break;}
                        case "c3" : if (!bearAlert){crossRoads(); break;} else{break;}
                    }
                    break;
                case "grabSword":
                    switch (choice){
                        case "c1" : fightBear(); break;
                        case "c2" : caveLeave(); break;
                    }
                    break;

                case "lookCave":
                    switch (choice){
                        case "c1" : fightBear(); break;
                        case "c2" : grabSword(); break;
                        case "c3" : caveLeave(); break;
                    }
                    break;

                case "caveLeave":
                    switch (choice){
                        case "c1" : crossRoads(); break;
                    }
                    break;

                case "fightBear":
                    switch (choice){
                        case "c1" : break;
                        case "c2" : caveLeave();
                    }
                    break;
            }
        }
    }
}
