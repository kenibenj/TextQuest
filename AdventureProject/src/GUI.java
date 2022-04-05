import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

public class GUI {

    JFrame window;
    JPanel titleScreen, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, mainImagePanel, healthPanel, inventoryPanel, utilityButtonPanel,
            deathImagePanel, deathTextPanel, deathButtonsPanel, equipmentButtonPanel, inventoryButtonPanel, informationButtonPanel, cover, equipmentPanel, informationPanel,
            actionPanel, titleCover;
    JLabel titleScreenLabel, hpLabelNumber, weaponLabelName, areaLabelName, mainImageLabel, healthLabel, coverLabel,
            deathTextLabel1, deathTextLabel2, deathImageLabel, actionLabel, goldLabel, titleCoverLabel;
    JButton startButton, resetButton, exitButton, deathButtonContinue, deathButtonExit, mainMenuExit, inventoryImageButton, informationButton, equipmentButton;
    JTextArea mainTextArea, equipmentTextArea,informationTextArea;
    JButton[] choices = new JButton[4];
    JButton[] inventorySlots = new JButton[6];
    Font fontMainText, fontOptions, fontStats, fontTitle, fontHealth, fontStart;
    String text;
    MouseHandler mouseHandler = new MouseHandler();

    ImageIcon mainImage, healthImage, coverImage, deathImage, titleCoverImage;

    Color gamerGrey = new Color(40, 40, 40);
    Color inkBrown = new Color(57,33,5);

    Border borderBorder = BorderFactory.createLineBorder(inkBrown, 2);

    Game game;


    //Text Crawl
    int crawlCounter = 0;
    URL textCrawlSoundURL = getClass().getResource("TextCrawl.wav");

    Timer timer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            char character[] = text.toCharArray();
            int arrayNumber = character.length;

            String addedCharacter = "";

            addedCharacter = String.valueOf(character[crawlCounter]);
            mainTextArea.append(addedCharacter);
            crawlCounter++;

            if(crawlCounter%2 == 0) {
                try {
                    SoundEffectHandler.playFile(textCrawlSoundURL);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }

            if(crawlCounter == arrayNumber){
                crawlCounter = 0;
                timer.stop();
            }
        }
    });


    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            timer.stop();
            mainTextArea.setText(text);
            crawlCounter = 0;
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public void prepareText(String readingText){
        crawlCounter = 0;
        text = readingText;
        mainTextArea.setText("");
        timer.start();
    }


    public GUI(Game game) throws IOException, UnsupportedAudioFileException {
        this.game = game;

        //Fonts
        try{
            fontMainText = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("KnightsQuest.ttf")).deriveFont(25f);
            fontTitle = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("buffied.regular.ttf")).deriveFont(100f);
            fontStats = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("KnightsQuest.ttf")).deriveFont(26f);
            fontHealth = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("KnightsQuest.ttf")).deriveFont(42f);
            fontOptions = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("KnightsQuest.ttf")).deriveFont(30f);
            fontStart = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("KnightsQuest.ttf")).deriveFont(40f);

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(fontMainText);
        }
        catch(IOException | FontFormatException e){
        }

    }

    public void createUI(ChoiceHandler choiceHandler, InventoryHandler inventoryHandler){



        //Window
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().setBackground(gamerGrey);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("swordICON.png")));
        window.addMouseListener(mouseHandler);
        window.addKeyListener(game.keyListener);

        //Title Screen
        titleScreen = new JPanel();
        titleScreen.setBounds(100, 100, 600, 150);
        titleScreen.setBackground(gamerGrey);
        titleScreenLabel = new JLabel();
        titleScreenLabel.setForeground(inkBrown);
        titleScreenLabel.setFont(fontTitle);
//        Border borderTitle = BorderFactory.createLineBorder(inkBrown, 3);
//        titleScreen.setBorder(borderTitle);
        titleScreen.add(titleScreenLabel);

        window.add(titleScreen);


        //Start Button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,420,220,120);
        startButtonPanel.setBackground(gamerGrey);
        startButtonPanel.setBorder(borderBorder);
        startButtonPanel.setLayout(new GridLayout(2,1));

        startButton = new JButton("START");
        startButton.setBackground(gamerGrey);
        startButton.setForeground(inkBrown);
        startButton.setFont(fontStart);
        startButton.addActionListener(choiceHandler);
        startButton.addKeyListener(game.keyListener);
        startButton.setFocusPainted(false);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        mainMenuExit = new JButton("Exit");
        mainMenuExit.setBackground(gamerGrey);
        mainMenuExit.setForeground(inkBrown);
        mainMenuExit.addActionListener(choiceHandler);
        mainMenuExit.setFont(fontStart);
        mainMenuExit.setActionCommand("exit");
        mainMenuExit.setFocusPainted(false);
        mainMenuExit.setOpaque(false);
        startButtonPanel.add(mainMenuExit);

        window.add(startButtonPanel);


        //Death Screen
        deathTextPanel = new JPanel();
        deathTextPanel.setBounds(140,250,520,150);
        deathTextPanel.setBackground(gamerGrey);
        deathTextPanel.setLayout(new BoxLayout(deathTextPanel, BoxLayout.Y_AXIS));

        deathTextLabel1 = new JLabel("You were killed by Unknown forces");
        deathTextLabel1.setBackground(Color.blue);
        deathTextLabel1.setForeground(inkBrown);
        deathTextLabel1.setFont(fontOptions);
        deathTextLabel1.setAlignmentX(.5f);
        deathTextPanel.add(deathTextLabel1);

        deathTextLabel2 = new JLabel("Don’t let the bastards grind you down!");
        deathTextLabel2.setBackground(Color.red);
        deathTextLabel2.setForeground(inkBrown);
        deathTextLabel2.setFont(fontMainText);
        deathTextLabel2.setAlignmentX(.5f);
        deathTextLabel2.setPreferredSize(new Dimension(140,200));
        deathTextPanel.add(deathTextLabel2);

        window.add(deathTextPanel);

        //Death Image
        deathImagePanel = new JPanel();
        deathImagePanel.setBounds(0,260,400,520);
        deathImagePanel.setBackground(gamerGrey);

        deathImageLabel = new JLabel();
        deathImage = new ImageIcon(getClass().getClassLoader().getResource("death.png"));
        deathImageLabel.setIcon(deathImage);
        deathImagePanel.add(deathImageLabel);

        window.add(deathImagePanel);


        //Death Buttons
        deathButtonsPanel = new JPanel();
        deathButtonsPanel.setBounds(300,420,200,100);
        deathButtonsPanel.setBackground(gamerGrey);
        deathButtonsPanel.setLayout(new GridLayout(2,1));

        deathButtonContinue = new JButton("Main Menu");
        deathButtonContinue.setBackground(gamerGrey);
        deathButtonContinue.setForeground(inkBrown);
        deathButtonContinue.addActionListener(choiceHandler);
        deathButtonContinue.addKeyListener(game.keyListener);
        deathButtonContinue.setFont(fontStart);
        deathButtonContinue.setActionCommand("reset");
        deathButtonContinue.setFocusPainted(false);
        deathButtonContinue.setOpaque(false);
        deathButtonsPanel.add(deathButtonContinue);

        deathButtonExit = new JButton("Exit");
        deathButtonExit.setBackground(gamerGrey);
        deathButtonExit.setForeground(inkBrown);
        deathButtonExit.addActionListener(choiceHandler);
        deathButtonExit.addKeyListener(game.keyListener);
        deathButtonExit.setFont(fontStart);
        deathButtonExit.setActionCommand("exit");
        deathButtonExit.setFocusPainted(false);
        deathButtonExit.setOpaque(false);
        deathButtonsPanel.add(deathButtonExit);

        window.add(deathButtonsPanel);


        //Utility Buttons
        utilityButtonPanel = new JPanel();
        utilityButtonPanel.setBounds(8,8, 150, 35);
        utilityButtonPanel.setBackground(gamerGrey);
        utilityButtonPanel.setLayout(new GridLayout(1,4));

        exitButton = new JButton();
        exitButton.setBackground(gamerGrey);
        exitButton.setForeground(inkBrown);
        exitButton.addActionListener(choiceHandler);
        exitButton.addKeyListener(game.keyListener);
        exitButton.setActionCommand("exit");
        exitButton.setFocusPainted(false);
        exitButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("exitButton.png")));
        exitButton.setOpaque(false);
        utilityButtonPanel.add(exitButton);


        resetButton = new JButton();
        resetButton.setBackground(gamerGrey);
        resetButton.setForeground(inkBrown);
        resetButton.addActionListener(choiceHandler);
        resetButton.addKeyListener(game.keyListener);
        resetButton.setActionCommand("reset");
        resetButton.setFocusPainted(false);
        resetButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("reset.png")));
        resetButton.setOpaque(false);
        utilityButtonPanel.add(resetButton);

        JButton volumeUp = new JButton();
        volumeUp.setBackground(gamerGrey);
        volumeUp.setForeground(inkBrown);
        volumeUp.addActionListener(choiceHandler);
        volumeUp.addKeyListener(game.keyListener);
        volumeUp.setActionCommand("volumeUp");
        volumeUp.setFocusPainted(false);
        volumeUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("volumeUp.png")));
        volumeUp.setOpaque(false);
        utilityButtonPanel.add(volumeUp);

        JButton volumeDown = new JButton();
        volumeDown.setBackground(gamerGrey);
        volumeDown.setForeground(inkBrown);
        volumeDown.addActionListener(choiceHandler);
        volumeDown.addKeyListener(game.keyListener);
        volumeDown.setActionCommand("volumeDown");
        volumeDown.setFocusPainted(false);
        volumeDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("volumeDown.png")));
        volumeDown.setOpaque(false);
        utilityButtonPanel.add(volumeDown);

        window.add(utilityButtonPanel);


        //Action Menu
        actionPanel = new JPanel();
        actionPanel.setBounds(30,280,406,40);
        actionPanel.setBackground(gamerGrey);
        Border mainTextBorder = BorderFactory.createLineBorder(inkBrown, 3);
        actionPanel.setBorder(mainTextBorder);
        actionPanel.setOpaque(false);

        actionLabel = new JLabel("This is the action menu!");
        actionLabel.setFont(fontStats);
        actionPanel.add(actionLabel);
        window.add(actionPanel);


        //Main Text Area
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(30,330,406,200);
        mainTextPanel.setBackground(gamerGrey);
        Border borderInventory = BorderFactory.createLineBorder(inkBrown, 2);
        mainTextPanel.setBorder(borderInventory);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("Main Text Area");
        mainTextArea.setBounds(30,310,390,200);
        mainTextArea.setBackground(gamerGrey);
        mainTextArea.setForeground(inkBrown);
        mainTextArea.setFont(fontMainText);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);


        //Choices Panel
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(460,380,280,150);
        choiceButtonPanel.setBackground(gamerGrey);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        window.add(choiceButtonPanel);

        for(int i = 0; i < 4; i++){
            choices[i] = new JButton();
            choices[i].setBackground(gamerGrey);
            choices[i].setForeground(inkBrown);
            choices[i].setFont(fontOptions);
            choices[i].addActionListener(choiceHandler);
            choices[i].addKeyListener(game.keyListener);
            choices[i].setActionCommand("c"+(i+1));
            choices[i].setFocusPainted(false);
            choices[i].setFocusTraversalKeysEnabled(false);
            choiceButtonPanel.add(choices[i]);
        }


        //Health Panel
        healthPanel = new JPanel();
        healthPanel.setBounds(660,300,70,70);
        healthPanel.setBackground(gamerGrey);
        healthPanel.setLayout(new OverlayLayout(healthPanel));

        healthLabel = new JLabel();
        healthImage = new ImageIcon(getClass().getClassLoader().getResource("heartSIZED.png"));
        healthLabel.setIcon(healthImage);
        healthLabel.setAlignmentX(.5f);
        healthLabel.setAlignmentY(.5f);
        healthPanel.add(healthLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(fontHealth);
        hpLabelNumber.setForeground(inkBrown);
        hpLabelNumber.setAlignmentX(.5f);
        hpLabelNumber.setAlignmentY(.56f);
        healthPanel.add(hpLabelNumber);

        window.add(healthPanel);


        //InventoryButton
        inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setBounds(460,40, 74, 74);
        inventoryButtonPanel.setBackground(gamerGrey);
        inventoryButtonPanel.setLayout(new OverlayLayout(inventoryButtonPanel));
        inventoryButtonPanel.setBorder(borderBorder);
        inventoryButtonPanel.setOpaque(false);

        inventoryImageButton = new JButton();
        inventoryImageButton.setBackground(gamerGrey);
        inventoryImageButton.setForeground(inkBrown);
        inventoryImageButton.addActionListener(inventoryHandler);
        inventoryImageButton.setActionCommand("openInventory");
        inventoryImageButton.addKeyListener(game.keyListener);
        inventoryImageButton.setFocusPainted(false);
        inventoryImageButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("inventoryButton.png")));
        inventoryImageButton.setOpaque(false);
        inventoryButtonPanel.add(inventoryImageButton);

        window.add(inventoryButtonPanel);


        //equipmentButton
        equipmentButtonPanel = new JPanel();
        equipmentButtonPanel.setBounds(460,118, 74, 74);
        equipmentButtonPanel.setBackground(gamerGrey);
        equipmentButtonPanel.setLayout(new OverlayLayout(equipmentButtonPanel));
        equipmentButtonPanel.setBorder(borderBorder);
        equipmentButtonPanel.setOpaque(false);

        equipmentButton = new JButton();
        equipmentButton.setBackground(gamerGrey);
        equipmentButton.setForeground(inkBrown);
        equipmentButton.addActionListener(inventoryHandler);
        equipmentButton.setActionCommand("openEquipment");
        equipmentButton.addKeyListener(game.keyListener);
        equipmentButton.setFocusPainted(false);
        equipmentButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("equipment.png")));
        equipmentButton.setOpaque(false);
        equipmentButtonPanel.add(equipmentButton);

        window.add(equipmentButtonPanel);

        equipmentPanel = new JPanel();
        equipmentPanel.setBounds(560,40, 180, 230);
        equipmentPanel.setBorder(borderInventory);
        equipmentPanel.setBackground(gamerGrey);
        equipmentPanel.setOpaque(false);

        equipmentTextArea = new JTextArea("equipment");
        equipmentTextArea.setBounds(560,40, 180, 230);
        equipmentTextArea.setBackground(gamerGrey);
        equipmentTextArea.setForeground(inkBrown);
        equipmentTextArea.setFont(fontMainText);
        equipmentTextArea.setLineWrap(true);
        equipmentTextArea.setWrapStyleWord(true);
        equipmentTextArea.setEditable(false);
        equipmentTextArea.setOpaque(false);

        equipmentPanel.add(equipmentTextArea);

        window.add(equipmentPanel);


        //informationButton
        informationButtonPanel = new JPanel();
        informationButtonPanel.setBounds(460,196, 74, 74);
        informationButtonPanel.setBackground(gamerGrey);
        informationButtonPanel.setLayout(new OverlayLayout(informationButtonPanel));
        informationButtonPanel.setBorder(borderBorder);
        informationButtonPanel.setOpaque(false);

        informationButton = new JButton();
        informationButton.setBackground(gamerGrey);
        informationButton.setForeground(inkBrown);
        informationButton.addActionListener(inventoryHandler);
        informationButton.setActionCommand("openInformation");
        informationButton.addKeyListener(game.keyListener);
        informationButton.setFocusPainted(false);
        informationButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sc.png")));
        informationButton.setOpaque(false);
        informationButtonPanel.add(informationButton);

        window.add(informationButtonPanel);

        informationPanel = new JPanel();
        informationPanel.setBounds(560,40, 180, 230);
        informationPanel.setBorder(borderInventory);
        informationPanel.setBackground(gamerGrey);
        informationPanel.setOpaque(false);

        informationTextArea = new JTextArea();
        informationTextArea.setBounds(560,40, 180, 230);
        informationTextArea.setBackground(gamerGrey);
        informationTextArea.setForeground(inkBrown);
        informationTextArea.setFont(fontMainText);
        informationTextArea.setLineWrap(true);
        informationTextArea.setWrapStyleWord(true);
        informationTextArea.setEditable(false);
        informationTextArea.setOpaque(false);

        informationPanel.add(informationTextArea);

        window.add(informationPanel);


        //Inventory Panel
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(560,40, 180, 230);
        inventoryPanel.setLayout(new GridLayout(6,1));
        inventoryPanel.setBorder(borderInventory);
        inventoryPanel.setBackground(gamerGrey);

        for(int i = 0; i < 6; i++){
            inventorySlots[i] = new JButton();
            inventorySlots[i].setBackground(gamerGrey);
            inventorySlots[i].setForeground(inkBrown);
            inventorySlots[i].setFont(fontStats);
            inventorySlots[i].addActionListener(inventoryHandler);
            inventorySlots[i].setActionCommand("slot" + (i+1));
            inventorySlots[i].addKeyListener(game.keyListener);
            inventorySlots[i].setFocusPainted(false);
            inventorySlots[i].setVisible(false);
            inventorySlots[i].setOpaque(false);
            inventorySlots[i].setFocusTraversalKeysEnabled(false);
            inventoryPanel.add(inventorySlots[i]);
        }

        window.add(inventoryPanel);


        //Gold and Weapon Panel
        playerPanel = new JPanel();
        playerPanel.setBounds(460,300,200,80);
        playerPanel.setBackground(gamerGrey);
        playerPanel.setLayout(new GridLayout(2,1));
        window.add(playerPanel);

        goldLabel = new JLabel();
        goldLabel.setFont(fontStats);
        goldLabel.setForeground(inkBrown);
        playerPanel.add(goldLabel);

        weaponLabelName = new JLabel();
        weaponLabelName.setFont(fontStats);
        weaponLabelName.setForeground(inkBrown);
        playerPanel.add(weaponLabelName);


        //Main Image
        mainImagePanel = new JPanel();
        mainImagePanel.setBounds(30,40,406,230);
        mainImagePanel.setBackground(gamerGrey);

        mainImageLabel = new JLabel();

        mainImage = new ImageIcon(getClass().getClassLoader().getResource("shipSIZED.png"));

        mainImageLabel.setIcon(mainImage);
        mainImagePanel.add(mainImageLabel);

        window.add(mainImagePanel);

//        //Title Cover
//        titleCoverImage = new ImageIcon(getClass().getClassLoader().getResource("titleSIZED.png"));
//        titleCover = new JPanel();
//        titleCover.setBounds(0,0,800,600);
//        titleCover.setLayout(new OverlayLayout(titleCover));
//        titleCoverLabel = new JLabel();
//        titleCoverLabel.setIcon(titleCoverImage);
//        titleCover.add(titleCoverLabel);
//        window.add(titleCover);

        //Cover
        coverImage = new ImageIcon(getClass().getClassLoader().getResource("backgroundSIZED.png"));
        titleCoverImage = new ImageIcon(getClass().getClassLoader().getResource("title.png"));
        cover = new JPanel();
        cover.setBounds(0,0,800,600);
        cover.setLayout(new OverlayLayout(cover));
        coverLabel = new JLabel();
        coverLabel.setIcon(coverImage);
        titleCoverLabel = new JLabel();
        titleCoverLabel.setIcon(titleCoverImage);
        cover.add(titleCoverLabel);
        cover.add(coverLabel);
        window.add(cover);

//        cardPanel.add(titleScreen, "titleScreen");
//        cardPanel.add(startButtonPanel, "startButtonPanel");
//        cardPanel.add(startButton, "startButton");
//        cardPanel.add(inventoryPanel, "inventoryPanel");
//        cardPanel.add(healthPanel, "healthPanel");
//        cardPanel.add(utilityButtonPanel, "utilityButtonPanel");
//        cardPanel.add(playerPanel, "playerPanel");
//        cardPanel.add(mainImagePanel, "mainImagePanel");
//        cardPanel.add(mainTextArea, "mainTextArea");
//        cardPanel.add(deathTextPanel, "deathTextPanel");
//        cardPanel.add(deathButtonExit, "deathButtonExit");
//        cardPanel.add(deathButtonContinue, "deathButtonContinue");
//        cardPanel.add(deathButtonsPanel, "deathButtonsPanel");
//        cardPanel.add(deathImagePanel, "deathImagePanel");
//        cardLayout.show(cardPanel,"titleScreen");
//        window.add(cardPanel);

        titleScreen.setOpaque(false);
        startButtonPanel.setOpaque(false);
        startButton.setOpaque(false);
        inventoryPanel.setOpaque(false);
        healthPanel.setOpaque(false);
        utilityButtonPanel.setOpaque(false);
        playerPanel.setOpaque(false);
        mainImagePanel.setOpaque(false);
        mainTextArea.setOpaque(false);
        deathTextPanel.setOpaque(false);
        deathButtonExit.setOpaque(false);
        deathButtonContinue.setOpaque(false);
        deathButtonsPanel.setOpaque(false);
        deathImagePanel.setOpaque(false);

        for(int i = 0; i < 4; i++){
            choices[i].setOpaque(false);
        }
        choiceButtonPanel.setOpaque(false);
        mainTextPanel.setOpaque(false);

        window.setVisible(true);
    }
}
