import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyListener implements java.awt.event.KeyListener {
    Game game;

    public KeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                    game.choiceHandler.oneChoice();
                    break;
            case KeyEvent.VK_D:
                game.choiceHandler.twoChoice();
                break;
            case KeyEvent.VK_A:
                game.choiceHandler.threeChoice();
                break;
            case KeyEvent.VK_S:
                game.choiceHandler.fourChoice();
                break;
            case KeyEvent.VK_R:
                game.choiceHandler.resetChoice();
                break;
            case KeyEvent.VK_SPACE:
                if(game.visibilityManager.onGame){
                    System.out.println("damn");
                    game.gui.timer.stop();
                    game.gui.mainTextArea.setText(game.gui.text);
                    game.gui.crawlCounter = 0;
                    break;
                }
                else if(!game.visibilityManager.onGame){
                    game.choiceHandler.startChoice();
                    break;
                }
            case KeyEvent.VK_TAB:
                if(game.visibilityManager.onGame) {
                        for(int i = 0; i < game.gui.inventorySlots.length; i++) {
                            game.gui.inventorySlots[i].setVisible(true);
                        }
                        game.inventoryHandler.inventoryOpen = true;

                        try {
                            SoundEffectHandler.playFile(game.soundEffectHandler.openInv);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }
                    }
                    break;
                }
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
