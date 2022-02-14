import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Random;

public class VisibilityManager {

    GUI gui;
    Timer fadeTimer;
    boolean onTitle, onGame;

    static String[] qoutes = {
            "They don't care about the eyes at all.",
            "Don’t let the bastards grind you down!",
            "Where did Jack go?",
            "It happens every now and then…", "Get real.", "What’s wrong with air guitar?"
    };

    public void setPlayerHP(int modifier){
        gui.game.story.player.hp = gui.game.story.player.hp + modifier;
        if(gui.game.story.player.hp<10){
            gui.hpLabelNumber.setAlignmentY(.5f);
        }
        if(gui.game.story.player.hp>=10){
            gui.hpLabelNumber.setAlignmentY(.56f);
        }
        if(gui.game.story.player.hp<0){
            gui.game.story.player.hp = 0;
        }
        if(gui.game.story.player.hp>15){
            gui.game.story.player.hp = gui.game.story.player.maxHP;
        }
        gui.hpLabelNumber.setText(String.valueOf(gui.game.story.player.hp));
    }

    public VisibilityManager(GUI userInterface){
        gui = userInterface;
        onTitle = true;
        onGame = false;
    }

    public void showTitleScreen(){
        onTitle = true;
        onGame = false;

        gui.titleScreenLabel.setText("TEXT QUEST");
        gui.titleScreen.setVisible(true);
        gui.startButtonPanel.setVisible(true);

        gui.deathButtonsPanel.setVisible(false);
        gui.deathTextPanel.setVisible(false);
        gui.mainTextPanel.setVisible(false);
        gui.choiceButtonPanel.setVisible(false);
        gui.playerPanel.setVisible(false);
        gui.mainImagePanel.setVisible(false);
        gui.healthPanel.setVisible(false);
        gui.inventoryPanel.setVisible(false);
        gui.utilityButtonPanel.setVisible(false);
        gui.deathImagePanel.setVisible(false);
        gui.inventoryButtonPanel.setVisible(false);
        gui.equipmentButtonPanel.setVisible(false);
        gui.informationButtonPanel.setVisible(false);
        gui.equipmentPanel.setVisible(false);
        gui.informationPanel.setVisible(false);
        gui.actionPanel.setVisible(false);
    }

    public void showGameScreen(){
        onTitle = false;
        onGame = true;

        gui.titleScreen.setVisible(false);
        gui.startButtonPanel.setVisible(false);
        gui.deathButtonsPanel.setVisible(false);
        gui.deathTextPanel.setVisible(false);

        gui.mainTextPanel.setVisible(true);
        gui.choiceButtonPanel.setVisible(true);
        gui.playerPanel.setVisible(true);
        gui.mainImagePanel.setVisible(true);
        gui.healthPanel.setVisible(true);
        gui.inventoryPanel.setVisible(true);
        gui.utilityButtonPanel.setVisible(true);
        gui.deathImagePanel.setVisible(false);
        gui.inventoryButtonPanel.setVisible(true);
        gui.equipmentButtonPanel.setVisible(true);
        gui.informationButtonPanel.setVisible(true);
        gui.equipmentPanel.setVisible(true);
        gui.informationPanel.setVisible(true);
        gui.actionPanel.setVisible(true);
    }

    public void showDeathScreen(Entities foe) throws IOException, UnsupportedAudioFileException {
        onTitle = true;
        onGame = false;

        URL deathBells = getClass().getResource("deathBells.wav");
        SoundEffectHandler.playFile(deathBells);

        String messageIndex = qoutes[new Random().nextInt(qoutes.length)];
        gui.deathTextLabel2.setText("\"" + messageIndex + "\"");

        gui.timer.stop();
        gui.titleScreen.setVisible(true);
        gui.deathButtonsPanel.setVisible(true);
        gui.deathTextPanel.setVisible(true);
        gui.deathImagePanel.setVisible(true);

        gui.titleScreenLabel.setText("YOU DIED");
        if (foe.howMany == 1) {
            gui.deathTextLabel1.setText("A " + foe.name.toLowerCase(Locale.ROOT) + " killed you!");
        }
        else{
            gui.deathTextLabel1.setText(foe.name + " killed you!");
        }

        gui.startButtonPanel.setVisible(false);
        gui.mainTextPanel.setVisible(false);
        gui.choiceButtonPanel.setVisible(false);
        gui.playerPanel.setVisible(false);
        gui.mainImagePanel.setVisible(false);
        gui.healthPanel.setVisible(false);
        gui.inventoryPanel.setVisible(false);
        gui.utilityButtonPanel.setVisible(false);
        gui.inventoryButtonPanel.setVisible(false);
        gui.equipmentButtonPanel.setVisible(false);
        gui.informationButtonPanel.setVisible(false);
        gui.equipmentPanel.setVisible(false);
        gui.informationPanel.setVisible(false);
        gui.actionPanel.setVisible(false);

    }

    public void showWinScreen() throws IOException, UnsupportedAudioFileException {
        onTitle = true;
        onGame = false;

        URL deathBells = getClass().getResource("winTrumpets.wav");
        SoundEffectHandler.playFile(deathBells);

        String messageIndex = qoutes[new Random().nextInt(qoutes.length)];
        gui.deathTextLabel2.setText("\"" + messageIndex + "\"");

        gui.timer.stop();
        gui.titleScreen.setVisible(true);
        gui.deathButtonsPanel.setVisible(true);
        gui.deathTextPanel.setVisible(true);

        gui.titleScreenLabel.setText("YOU WIN");

        gui.deathTextLabel1.setText("You have earned the respect of the town!");

        gui.startButtonPanel.setVisible(false);
        gui.mainTextPanel.setVisible(false);
        gui.choiceButtonPanel.setVisible(false);
        gui.playerPanel.setVisible(false);
        gui.mainImagePanel.setVisible(false);
        gui.healthPanel.setVisible(false);
        gui.inventoryPanel.setVisible(false);
        gui.utilityButtonPanel.setVisible(false);
        gui.inventoryButtonPanel.setVisible(false);
        gui.equipmentButtonPanel.setVisible(false);
        gui.informationButtonPanel.setVisible(false);
        gui.equipmentPanel.setVisible(false);
        gui.informationPanel.setVisible(false);
        gui.actionPanel.setVisible(false);

    }

    public void buttonsVisibility(){
        for(int i = 0; i < gui.choices.length; i++){
            if(gui.choices[i].getText() == ""){
                gui.choices[i].setVisible(false);
            }
            else {
                gui.choices[i].setVisible(true);
            }
        }
    }



    static int opacityCounter = 0;

    public void fadeOut(JPanel frame){

        System.out.println(opacityCounter);
        opacityCounter = 0;
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setBackground(new Color(0,0,0,opacityCounter));
                opacityCounter++;
                gui.window.add(frame);

                if(opacityCounter >= 255){
                    opacityCounter = 255;
                    fadeTimer.stop();
                }
                System.out.println(opacityCounter);
            }
        });
        fadeTimer.start();
    }
}
