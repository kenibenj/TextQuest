import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ChoiceHandler implements ActionListener {

    Game game;
    ImageIcon howToPlayImage;

    public ChoiceHandler(Game game){
        this.game = game;
        howToPlayImage = new ImageIcon(getClass().getClassLoader().getResource("howToPlaySIZED.png"));
    }

    public void startChoice(){
        try {
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        game.visibilityManager.showGameScreen();
        game.story.ship();
    }

    public void oneChoice() {
        if (game.visibilityManager.onGame) {
            try {
                game.soundEffectHandler.stop();
                game.story.selectPosition(game.position1);
                game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    public void twoChoice() {
        if (game.visibilityManager.onGame) {
            try {
                game.soundEffectHandler.stop();
                game.story.selectPosition(game.position2);
                game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }
    public void threeChoice() {
        if (game.visibilityManager.onGame) {
            try {
                game.soundEffectHandler.stop();
                game.story.selectPosition(game.position3);
                game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    public void fourChoice() {
        if (game.visibilityManager.onGame) {
            try {
                game.soundEffectHandler.stop();
                game.story.selectPosition(game.position4);
                game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetChoice(){
        game.story.toTitle();
        game.gui.titleCoverLabel.setIcon(game.gui.titleCoverImage);
        try {
            game.soundEffectHandler.stop();
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void howToPlayChoice(){
        game.gui.titleCoverLabel.setIcon(howToPlayImage);
        game.visibilityManager.showHowToPlayScreen();
        try {
            game.soundEffectHandler.stop();
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void goBackChoice(){
        game.gui.titleCoverLabel.setIcon(game.gui.titleCoverImage);
        game.visibilityManager.showTitleScreen();
        try {
            game.soundEffectHandler.stop();
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void volumeUp(){
        SoundEffectHandler.volumeUp();
        try {
            game.soundEffectHandler.stop();
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void volumeDown(){
        SoundEffectHandler.volumeDown();
        try {
            game.soundEffectHandler.stop();
            game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String choice = event.getActionCommand();

        switch (choice){
            case "start" :
                startChoice();
                break;
            case "reset" :
                resetChoice();
                break;
            case "exit" :
                try {
                    game.soundEffectHandler.playFile(game.soundEffectHandler.buttonMain);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            case "c1" :
                oneChoice();
                break;
            case "c2" :
                twoChoice();
                break;
            case "c3" :
                threeChoice();
                break;
            case "c4" :
                fourChoice();
                break;
            case "volumeUp" :
                volumeUp();
                break;
            case "volumeDown" :
                volumeDown();
                break;
            case "howToPlay" :
                howToPlayChoice();
                break;
            case "goBack" :
                goBackChoice();
                break;
        }
    }
}
