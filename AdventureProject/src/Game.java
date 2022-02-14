import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
public class Game {

    String position1, position2,position3,position4;
    GUI gui = new GUI(this);
    public VisibilityManager visibilityManager = new VisibilityManager(gui);
    Story story = new Story(this, gui, visibilityManager);
    ChoiceHandler choiceHandler = new ChoiceHandler(this);
    SoundEffectHandler soundEffectHandler = new SoundEffectHandler();
    KeyListener keyListener = new KeyListener(this);
    InventoryHandler inventoryHandler = new InventoryHandler(this);

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException{

        new Game();
    }

    public Game() throws IOException, UnsupportedAudioFileException{
        gui.createUI(choiceHandler, inventoryHandler);
        story.defaultSetup();
        visibilityManager.showTitleScreen();
    }
}
