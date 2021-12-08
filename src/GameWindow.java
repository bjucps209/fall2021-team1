import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.Enemy;
import model.Entity;
import model.Grunt;
import model.HighScore;
import model.Item;
import model.Juggernaut;
import model.Leaderboard;
import model.NPC;
import model.Serialization;
import model.World;
import model.Zone;
import model.ZoneList;
import model.Juggernaut.JuggernautState;
import model.World.mapDirection;

public class GameWindow {

    // Entire game is displayed through AnchorPane, gives more flexibility than
    // an HBox or a VBox. Allows for an adjustable screen as well through
    // screen anchors.

    // Control Attributes

    // https://gist.github.com/Da9el00/5f698b3839f00ab3e0f28118edd6c947
    private BooleanProperty uPressed = new SimpleBooleanProperty();
    private BooleanProperty lPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty rPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = uPressed.or(lPressed).or(dPressed).or(rPressed);

    // Id
    int nextId = 0;

    // Cheat Mode
    private boolean cheatModeEnabled = false;

    // FXML Attributes
    @FXML
    private AnchorPane apaneMain;
    @FXML
    private ImageView imgviewPlayer, imgviewHeart;
    @FXML
    private boolean isPaused;

    @FXML
    private Label lblScore, lblLocation;

    @FXML
    // Heart Images ********
    private Image imgHeart10 = new Image("Final Assets/UI/PNG/10-hearts.png");
    private Image imgHeart9 = new Image("Final Assets/UI/PNG/9-hearts.png");
    private Image imgHeart8 = new Image("Final Assets/UI/PNG/8-hearts.png");
    private Image imgHeart7 = new Image("Final Assets/UI/PNG/7-hearts.png");
    private Image imgHeart6 = new Image("Final Assets/UI/PNG/6-hearts.png");
    private Image imgHeart5 = new Image("Final Assets/UI/PNG/5-hearts.png");
    private Image imgHeart4 = new Image("Final Assets/UI/PNG/4-hearts.png");
    private Image imgHeart3 = new Image("Final Assets/UI/PNG/3-hearts.png");
    private Image imgHeart2 = new Image("Final Assets/UI/PNG/2-hearts.png");
    private Image imgHeart1 = new Image("Final Assets/UI/PNG/1-hearts.png");
    // *********************

    // Player Images *******
    private Image imgPlayerAttackFront = new Image("Final Assets/Player/GIF/Player-Front-Attack-128x192.gif");
    private Image imgPlayerAttackBack = new Image("Final Assets/Player/GIF/Player-Back-Attack-128x128.gif");
    private Image imgPlayerAttackLeft = new Image("Final Assets/Player/GIF/Player-Attack-Left-128x128.gif");
    private Image imgPlayerAttackRight = new Image("Final Assets/Player/GIF/Player-Right-Attack-128x128.gif");
    private Image imgPlayerBack = new Image("Final Assets/Player/PNG/Player-Back-Stationary-128x128.png");
    private Image imgPlayerFront = new Image("Final Assets/Player/PNG/Player-Front-Stationary-128x128.png");
    private Image imgPlayerLeft = new Image("Final Assets/Player/PNG/Player-Left-Stationary-128x128.png");
    private Image imgPlayerRight = new Image("Final Assets/Player/PNG/Player-Right-Stationary-128x128.png");
    private Image imgPlayerBackMove = new Image("Final Assets/Player/GIF/Player-Back-Walking-128x128.gif");
    private Image imgPlayerLeftMove = new Image("Final Assets/Player/GIF/Player-Left-Walking-128x128.gif");
    private Image imgPlayerRightMove = new Image("Final Assets/Player/GIF/Player-Right-Walking-128x128.gif");
    private Image imgPlayerFrontMove = new Image("Final Assets/Player/GIF/Player-Front-Walking-128x128.gif");
    private Image imgPlayerDeath = new Image("Final Assets/Player/GIF/Player-Death-128x128.gif");
    // **********************

    // NPC Images ***********
    private Image imgNPC = new Image("Final Assets/NPC/PNG/NPC-Front-Stationary-128x128.png");
    // **********************

    // Grunt Images *********
    private Image imgGruntDeath = new Image("Final Assets/Grunt/GIF/Grunt-Death-128x128.gif");
    private Image imgGrunt = new Image("Final Assets/Grunt/PNG/Grunt-Front-Stationary-128x128.png");
    private Image imgGruntRightMove = new Image("Final Assets/Grunt/GIF/Grunt-Right-Walking-128x128.gif");
    private Image imgGruntBackMove = new Image("Final Assets/Grunt/GIF/Grunt-Back-Walking-128x128.gif");
    private Image imgGruntFrontMove = new Image("Final Assets/Grunt/GIF/Grunt-Front-Walking-128x128.gif");
    private Image imgGruntLefttMove = new Image("Final Assets/Grunt/GIF/Grunt-Left-Walking-128x128.gif");
    // **********************

    // Jugg Images **********
    private Image imgJugg = new Image("Final Assets/Jugg/PNG/Jugg-Front-Stationary-192x192.png");
    private Image imgJuggDeath = new Image("Final Assets/Jugg/GIF/Jugg-Death-192x192.gif");
    private Image imgJuggRightMoveSlow = new Image("Final Assets/Jugg/GIF/Jugg-Right-Walking-192x192-150ms.gif");
    private Image imgJuggRightMoveFast = new Image("Final Assets/Jugg/GIF/Jugg-Right-Walking-192x192-50ms.gif");
    private Image imgJuggLeftMoveSlow = new Image("Final Assets/Jugg/GIF/Jugg-Left-Walking-192x192-150ms.gif");
    private Image imgJuggLefttMoveFast = new Image("Final Assets/Jugg/GIF/Jugg-Left-Walking-192x192-50ms.gif");
    private Image imgJuggFrontMoveSlow = new Image("Final Assets/Jugg/GIF/Jugg-Front-Walking-192x192-150ms.gif");
    private Image imgJuggFrontMoveFast = new Image("Final Assets/Jugg/GIF/Jugg-Front-Walking-192x192-50ms.gif");
    private Image imgJuggBackMoveSlow = new Image("Final Assets/Jugg/GIF/Jugg-Back-Walking-192x192-150ms.gif");
    private Image imgJuggBackMoveFast = new Image("Final Assets/Jugg/GIF/Jugg-Back-Walking-192x192-50ms.gif");
    private Image imgJuggBackAttack = new Image("Final Assets/Jugg/GIF/Jugg-Back-Attack-192x192.gif");
    private Image imgJuggFrontAttack = new Image("Final Assets/Jugg/GIF/Jugg-Front-Attack-192x192.gif");
    private Image imgJuggRightAttack = new Image("Final Assets/Jugg/GIF/Jugg-Right-Attack-192x192.gif");
    private Image imgJuggLeftAttack = new Image("Final Assets/Jugg/GIF/Jugg-Left-Attack-192x192.gif");
    // **********************

    // Wizard Images **********
    private Image imgWizardFront = new Image("Final Assets/Wizard/PNG/Wizard-Front-Stationary-128x128.png");
    private Image imgWizardFrontMove = new Image("Final Assets/Wizard/GIF/Wizard-Front-Walking-128x128.gif");
    private Image imgWizardBackMove = new Image("Final Assets/Wizard/GIF/Wizard-Back-Walking-128x128.gif");
    private Image imgWizardRighttMove = new Image("Final Assets/Wizard/GIF/Wizard-Right-Walking-128x128.gif");
    private Image imgWizardLeftMove = new Image("Final Assets/Wizard/GIF/Wizard-Left-Walking-128x128.gif");
    private Image imgWizardFrontAttack = new Image("Final Assets/Wizard/GIF/Wizard-Front-Attack-128x128.gif");
    private Image imgWizardBackAttack = new Image("Final Assets/Wizard/GIF/Wizard-Back-Attack-128x128.gif");
    private Image imgWizardRightAttack = new Image("Final Assets/Wizard/GIF/Wizard-Right-Attack-128x128.gif");
    private Image imgWizardLeftAttack = new Image("Final Assets/Wizard/GIF/Wizard-Left-Attack-128x128.gif");
    private Image imgWizardProjectile = new Image("Final Assets/Wizard/GIF/Wizard-Projectile-128x128.gif");
    private Image imgWizardDeath = new Image("Final Assets/Wizard/GIF/Wizard-Death-128x128.gif");
    // **********************

    // Object Images ********
    private Image imgDSignLeft = new Image("Final Assets/Objects/PNG/Objects-DSign-Left-64x64.png");
    private Image imgDSignRight = new Image("Final Assets/Objects/PNG/Objects-DSign-Right-64x64.png");
    private Image imgSign = new Image("Final Assets/Objects/PNG/Objects-FSign-64x64.png");
    private Image imgArch = new Image("Final Assets/Objects/PNG/Objects-GateArch-256x256.png");
    private Image imgArchposts = new Image("Final Assets/Objects/PNG/Objects-GatePosts-256x256.png");
    private Image imgTomb = new Image("Final Assets/Objects/PNG/Objects-Gravestone-64x64.png");
    private Image imgHFence = new Image("Final Assets/Objects/PNG/Objects-HFence-256x256.png");
    private Image imgVFence = new Image("Final Assets/Objects/PNG/Objects-VFence-256x256.png");
    private Image imgStump = new Image("Final Assets/Objects/PNG/Objects-Stump-256x256.png");
    private Image imgTree = new Image("Final Assets/Objects/PNG/Objects-Tree-256x256.png");
    private Image imgWell = new Image("Final Assets/Objects/PNG/Objects-Well-256x256.png");
    private Image imgHouse = new Image("Final Assets/Objects/PNG/Objects-House-Front-512x512.png");
    private Image imgCoin = new Image("Final Assets/Coin/GIF/Coin-68x68.gif");
    // **********************

    // UI Images ************
    private Image imgBackgroundDim = new Image("Final Assets/UI/PNG/UI-BackgroundDim-1440x900.png");
    private Image imgPauseBtn1 = new Image("Final Assets/UI/PNG/UI-PauseBtn1-80x80.png");
    private Image imgPauseBtn2 = new Image("Final Assets/UI/PNG/UI-PauseBtn2-80x80.png");
    private Image imgSaveBtn1 = new Image("Final Assets/UI/PNG/UI-SaveBtn1-312x80.png");
    private Image imgSaveBtn2 = new Image("Final Assets/UI/PNG/UI-SaveBtn2-312x80.png");
    private Image imgLoadbtn1 = new Image("Final Assets/UI/PNG/UI-LoadBtn1-312x80.png");
    private Image imgLoadbtn2 = new Image("Final Assets/UI/PNG/UI-LoadBtn2-312x80.png");
    private Image imgHelpBtn1 = new Image("Final Assets/UI/PNG/UI-HelpBtn1-312x80.png");
    private Image imgHelpBtn2 = new Image("Final Assets/UI/PNG/UI-HelpBtn2-312x80.png");
    private Image imgQuitBtn1 = new Image("Final Assets/UI/PNG/UI-QuitBtn1-312x80.png");
    private Image imgQuitBtn2 = new Image("Final Assets/UI/PNG/UI-QuitBtn2-312x80.png");
    private Image imgBackBtn1 = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
    private Image imgBackBtn2 = new Image("Final Assets/UI/PNG/UI-BackBtn2-312x80.png");
    private Image imgResumeBtn1 = new Image("Final Assets/UI/PNG/UI-ResumeBtn1-312x80.png");
    private Image imgResumeBtn2 = new Image("Final Assets/UI/PNG/UI-ResumeBtn2-312x80.png");
    // **********************

    // UI ImageViews ************
    private ImageView imgviewBackgroundDim = new ImageView(imgBackgroundDim);
    private ImageView imgviewPauseBtn = new ImageView(imgPauseBtn1);
    private ImageView imgviewSaveBtn = new ImageView(imgSaveBtn1);
    private ImageView imgviewLoadBtn = new ImageView(imgLoadbtn1);
    private ImageView imgviewHelpBtn = new ImageView(imgHelpBtn1);
    private ImageView imgviewQuitBtn = new ImageView(imgQuitBtn1);
    private ImageView imgviewGOQuitBtn = new ImageView(imgQuitBtn1);
    private ImageView imgviewHelpBackBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewResumeBtn = new ImageView(imgResumeBtn1);
    // **********************

    // UI VBoxes *********************
    private VBox pauseVbox = new VBox();
    private VBox helpVbox = new VBox();
    private VBox gameOverVbox = new VBox();
    // *****************************

    // Audio
    // *******************************************************************************************************
    private AudioClip click = new AudioClip(getClass().getResource("Audio/UI/btnClick1.mp3").toExternalForm());
    private AudioClip juggHit = new AudioClip(getClass().getResource("Audio/SFX/juggsound.wav").toExternalForm());
    private AudioClip gruntHit = new AudioClip(getClass().getResource("Audio/SFX/grunthit.wav").toExternalForm());
    private AudioClip coinSound = new AudioClip(getClass().getResource("Audio/SFX/Retro realistic coins.wav").toExternalForm());
    private AudioClip magicSound = new AudioClip(getClass().getResource("Audio/SFX/magic1.mp3").toExternalForm());
    private AudioClip deathSound = new AudioClip(getClass().getResource("Audio/SFX/deathsound.wav").toExternalForm());
    private AudioClip gameOverMusic = new AudioClip(getClass().getResource("Audio/gameover.wav").toExternalForm());
    private AudioClip swordSlash = new AudioClip(getClass().getResource("Audio/SFX/swordslash.wav").toExternalForm());
    // *************************************************************************************************************

    // Model Attributes
    private World world;

    // Temporary list to hold ImageViews. Similar functionality will be implemented
    // with enemy ID's later.
    ArrayList<ImageView> imgViewList;

    // Game clock
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(35), e -> updateWorld()));

    // Player's Name
    private String name;

    private AudioClip music;


    @FXML
    void initialize(Stage stage, DifficultyLevel difficulty, String name, AudioClip music) {
        this.music = music;
        click.volumeProperty().set(.2);
        swordSlash.volumeProperty().set(.4);
        coinSound.volumeProperty().set(.4);

        imgViewList = new ArrayList<ImageView>();
        world = World.instance();
        world.setDifficulty(difficulty);
        world.setScore(0);

        this.name = name;

        // Timer for the updateworld method
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Set up user control ******************************************
        keyPressed.addListener((ObservableValue, booleanVal, timer) -> {
            if (!booleanVal) {
                playerMovement.start();
            } else {
                playerMovement.stop();
            }
        });

        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed(event);
            }
        });

        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyReleased(event);
            }
        });
        // ***************************************************************

        // Display player
        drawPlayer(650, 750, imgPlayerBack);

        // Generate health bar
        drawHealth();

        // Generate score label
        drawScore();

        // Generate location label
        drawLocationLabel();

        // Building the entire scene of a zone.
        drawWorld();

        // Generate Pause Button
        drawPauseButton();

        // Set Default Font
        Font.loadFont(getClass().getResourceAsStream("/Final Assets/UI/Minecraft.ttf"), 64);

        // Populating UI VBoxes
        createPauseVbox();
        createHelpVbox();

    }

    /**
     * Displays the score increase upon killing an enemy or collecting a coin above the player's score count.
     * @param scoreBonues - the score to display
     */
    @FXML
    public void displayScoreIncrease(int scoreBonus) {

        Label lblMessage = new Label();
        lblMessage.setText("+ " + scoreBonus + "!");
        lblMessage.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        AnchorPane.setBottomAnchor(lblMessage, 40.0);
        AnchorPane.setLeftAnchor(lblMessage, 8.0);
        lblMessage.setAlignment(Pos.CENTER);

        apaneMain.getChildren().add(lblMessage);

        PauseTransition labelItemPause = new PauseTransition(Duration.seconds(1));
        labelItemPause.setOnFinished(e -> lblMessage.setVisible(false));
        labelItemPause.play();

    }

    /**
     * Calls the Player's interact method, if an entity is within range of the
     * player displays the entities message as a Label.
     */
    @FXML
    public void handleInteract() {

        Entity interactedEntity = world.getPlayer().interact();

        if (interactedEntity == null) {
            return;
        }

        switch (interactedEntity.getType()) {

            case NPC:

                Label lblNPCMessage = new Label();
                lblNPCMessage.setText(((NPC) interactedEntity).getMessage());
                lblNPCMessage.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
                lblNPCMessage.setLayoutX(interactedEntity.getX() - 60);
                lblNPCMessage.setLayoutY(interactedEntity.getY() - 20);
                lblNPCMessage.setAlignment(Pos.CENTER);

                apaneMain.getChildren().add(lblNPCMessage);

                PauseTransition labelPause = new PauseTransition(Duration.seconds(3));
                labelPause.setOnFinished(e -> lblNPCMessage.setVisible(false));
                labelPause.play();

                return;

            case ITEM:
                Item interactedItem = (Item) interactedEntity;

                if (interactedItem.getDescription().equals("coin")) {

                    for (ImageView imgview : imgViewList) {
                        if (interactedItem.getX() == imgview.getLayoutX()
                                && interactedItem.getY() == imgview.getLayoutY()) {
                            apaneMain.getChildren().remove(imgview);
                            world.getCurrentlocation().getObjectList().remove(interactedItem);
                        }
                    }

                    coinSound.play();

                    world.setScore(world.getScore() + ((Item) interactedItem).getScoreIncrease());
                    drawScore();
                    displayScoreIncrease(interactedItem.getScoreIncrease());
                }

                return;

            default:

                return;

        }

    }

    /**
     * Spawns enemies in the current player's location.
     * 
     * @param enemy - the enemy to be spawned
     */
    @FXML
    public void spawnEnemies(Enemy enemy) {

        world.spawnEnemies(enemy);

        for (Entity entity : world.getEntityList()) {

            if (entity instanceof Grunt) {

                Grunt spawnedGrunt = (Grunt) entity;
                spawnedGrunt.setOriginalX(spawnedGrunt.getX());
                spawnedGrunt.setOriginalY(spawnedGrunt.getY());
                ImageView imgViewGrunt = new ImageView();
                imgViewGrunt.setUserData(spawnedGrunt.getId());
                imgViewGrunt.xProperty().bindBidirectional(spawnedGrunt.xProperty());
                imgViewGrunt.yProperty().bindBidirectional(spawnedGrunt.yProperty());
                apaneMain.getChildren().add(imgViewGrunt);
                imgViewList.add(imgViewGrunt);

            } else if (entity instanceof Juggernaut) {
                Juggernaut spawnedJuggernaut = (Juggernaut) entity;
                spawnedJuggernaut.setOriginalX(spawnedJuggernaut.getX());
                spawnedJuggernaut.setOriginalY(spawnedJuggernaut.getY());
                ImageView imgViewJuggernaut = new ImageView();
                imgViewJuggernaut.setUserData(spawnedJuggernaut.getId());
                imgViewJuggernaut.xProperty().bindBidirectional(spawnedJuggernaut.xProperty());
                imgViewJuggernaut.yProperty().bindBidirectional(spawnedJuggernaut.yProperty());
                apaneMain.getChildren().add(imgViewJuggernaut);
                imgViewList.add(imgViewJuggernaut);
            }

        }

    }

    /**
     * Draws the player to the screen.
     * 
     * @param x           - the x coordinate the player will be placed at
     * @param y           - the y coordinate the player will be placed at
     * @param playerImage - the image to load into the player's image view.
     */
    @FXML
    public void drawPlayer(double x, double y, Image playerImage) {
        // Create player's imageview
        imgviewPlayer = new ImageView(playerImage);
        imgviewPlayer.setX(x);
        imgviewPlayer.setY(y);
        apaneMain.getChildren().add(imgviewPlayer);

        // Bind the player imageview's x and y to the player object
        world.getPlayer().xProperty().bindBidirectional(imgviewPlayer.xProperty());
        world.getPlayer().yProperty().bindBidirectional(imgviewPlayer.yProperty());
    }

    /**
     * Displays the player's heart meter on the top right of the screen.
     */
    @FXML
    public void drawHealth() {
        if (imgviewHeart != null) {
            apaneMain.getChildren().remove(imgviewHeart);
        }

        imgviewHeart = new ImageView();
        displayHealth(world.getPlayer().getHealth());
        AnchorPane.setTopAnchor(imgviewHeart, 10.0);
        AnchorPane.setRightAnchor(imgviewHeart, 10.0);
        apaneMain.getChildren().add(imgviewHeart);
    }

    /**
     * Draws the score label on the bottom left of the screen.
     */
    @FXML
    public void drawScore() {
        if (lblScore != null) {
            apaneMain.getChildren().remove(lblScore);
        }

        lblScore = new Label();
        lblScore.setText(String.valueOf(world.getScore()));
        lblScore.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        if (!cheatModeEnabled) {

            lblScore.textProperty()
                    .bind(Bindings.createStringBinding(() -> String.valueOf(world.getScore()), world.scoreProperty()));

        } else {

            lblScore.setText("Cheat Mode Enabled");

        }
        AnchorPane.setBottomAnchor(lblScore, 10.0);
        AnchorPane.setLeftAnchor(lblScore, 10.0);
        apaneMain.getChildren().add(lblScore);
    }

    /**
     * Draws the location label on the top right of the screen.
     */
    @FXML
    public void drawLocationLabel() {
        if (lblLocation != null) {
            apaneMain.getChildren().remove(lblLocation);
        }

        lblLocation = new Label();
        lblLocation.setText(world.getCurrentlocation().getZoneName());
        lblLocation.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        AnchorPane.setTopAnchor(lblLocation, 10.0);
        AnchorPane.setLeftAnchor(lblLocation, 10.0);
        apaneMain.getChildren().add(lblLocation);
    }

    @FXML
    public void drawPauseButton() {
        imgviewPauseBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewPauseBtn.setImage(imgPauseBtn2);
                });
                thread.start();
            }
        });
        imgviewPauseBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewPauseBtn.setImage(imgPauseBtn1);
            }
        });

        imgviewPauseBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                pause();
            }
        });

        imgviewPauseBtn.setFitHeight(40);
        imgviewPauseBtn.setFitWidth(40);
        AnchorPane.setBottomAnchor(imgviewPauseBtn, 10.0);
        AnchorPane.setRightAnchor(imgviewPauseBtn, 10.0);
        apaneMain.getChildren().add(imgviewPauseBtn);
    }

    /**
     * Displays the background and the objects contained inside of the current zone.
     */
    @FXML
    public void drawWorld() {
        nextId = 0;
        world.getEntityList().clear();
        imgViewList.clear();
        for (Zone zone : ZoneList.instance().getLevels()) {
            if (world.getCurrentlocation().getZoneName().equals(zone.getZoneName())) {

                // Set background for the zone
                BackgroundImage bImg = new BackgroundImage(new Image(zone.getBackgroundPath()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background bGround = new Background(bImg);
                apaneMain.setBackground(bGround);

                // Draw items inside of the zone onto the screen
                for (NPC landObjects : zone.getObjectList()) {

                    switch (landObjects.getDescription()) {

                        case "NPC":
                            landObjects.setWidth(64);
                            landObjects.setHeight(96);
                            ImageView imgviewNPC = new ImageView(imgNPC);
                            apaneMain.getChildren().add(imgviewNPC);
                            imgviewNPC.setLayoutX(landObjects.getX() - imgviewNPC.getLayoutBounds().getMinX());
                            imgviewNPC.setLayoutY(landObjects.getY() - imgviewNPC.getLayoutBounds().getMinY());
                            break;

                        case "tree":
                            landObjects.setWidth(184);
                            landObjects.setHeight(256);
                            ImageView imgviewTree = new ImageView(imgTree);
                            apaneMain.getChildren().add(imgviewTree);
                            imgviewTree.setLayoutX(landObjects.getX() - imgviewTree.getLayoutBounds().getMinX());
                            imgviewTree.setLayoutY(landObjects.getY() - imgviewTree.getLayoutBounds().getMinY());
                            break;

                        case "hFence":
                            landObjects.setWidth(212);
                            landObjects.setHeight(68);
                            ImageView imgviewHFence = new ImageView(imgHFence);
                            apaneMain.getChildren().add(imgviewHFence);
                            imgviewHFence.setLayoutX(landObjects.getX() - imgviewHFence.getLayoutBounds().getMinX());
                            imgviewHFence
                                    .setLayoutY(landObjects.getY() - imgviewHFence.getLayoutBounds().getMinY() - 84);
                            break;

                        case "vFence":
                            landObjects.setWidth(28);
                            landObjects.setHeight(212);
                            ImageView imgviewVFence = new ImageView(imgVFence);
                            apaneMain.getChildren().add(imgviewVFence);
                            imgviewVFence
                                    .setLayoutX(landObjects.getX() - imgviewVFence.getLayoutBounds().getMinX() - 84);
                            imgviewVFence.setLayoutY(landObjects.getY() - imgviewVFence.getLayoutBounds().getMinY());
                            break;

                        case "archPoles":
                            landObjects.setWidth(0);
                            landObjects.setHeight(0);
                            ImageView imgviewArchPoles = new ImageView(imgArchposts);
                            apaneMain.getChildren().add(imgviewArchPoles);
                            imgviewArchPoles
                                    .setLayoutX(landObjects.getX() - imgviewArchPoles.getLayoutBounds().getMinX());
                            imgviewArchPoles
                                    .setLayoutY(landObjects.getY() - imgviewArchPoles.getLayoutBounds().getMinY());
                            break;

                        case "arch":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewArch = new ImageView(imgArch);
                            apaneMain.getChildren().add(imgviewArch);
                            imgviewArch.setLayoutX(landObjects.getX() - imgviewArch.getLayoutBounds().getMinX());
                            imgviewArch.setLayoutY(landObjects.getY() - imgviewArch.getLayoutBounds().getMinY());
                            break;

                        case "stump":
                            landObjects.setWidth(96);
                            landObjects.setHeight(68);
                            ImageView imgviewStump = new ImageView(imgStump);
                            apaneMain.getChildren().add(imgviewStump);
                            imgviewStump.setLayoutX(landObjects.getX() - imgviewStump.getLayoutBounds().getMinX() - 48);
                            imgviewStump
                                    .setLayoutY(landObjects.getY() - imgviewStump.getLayoutBounds().getMinY() - 172);
                            break;

                        case "well":
                            landObjects.setWidth(132);
                            landObjects.setHeight(204);
                            ImageView imgviewWell = new ImageView(imgWell);
                            apaneMain.getChildren().add(imgviewWell);
                            imgviewWell.setLayoutX(landObjects.getX() - imgviewWell.getLayoutBounds().getMinX() - 42);
                            imgviewWell.setLayoutY(landObjects.getY() - imgviewWell.getLayoutBounds().getMinY() - 42);
                            break;

                        case "tomb":
                            landObjects.setWidth(52);
                            landObjects.setHeight(60);
                            ImageView imgviewTomb = new ImageView(imgTomb);
                            apaneMain.getChildren().add(imgviewTomb);
                            imgviewTomb.setLayoutX(landObjects.getX() - imgviewTomb.getLayoutBounds().getMinX());
                            imgviewTomb.setLayoutY(landObjects.getY() - imgviewTomb.getLayoutBounds().getMinY());
                            break;

                        case "lSign":
                            landObjects.setWidth(28);
                            landObjects.setHeight(56);
                            ImageView imgviewlSign = new ImageView(imgDSignLeft);
                            apaneMain.getChildren().add(imgviewlSign);
                            imgviewlSign.setLayoutX(landObjects.getX() - imgviewlSign.getLayoutBounds().getMinX());
                            imgviewlSign.setLayoutY(landObjects.getY() - imgviewlSign.getLayoutBounds().getMinY());
                            break;

                        case "rSign":
                            landObjects.setWidth(28);
                            landObjects.setHeight(56);
                            ImageView imgviewrSign = new ImageView(imgDSignRight);
                            apaneMain.getChildren().add(imgviewrSign);
                            imgviewrSign.setLayoutX(landObjects.getX() - imgviewrSign.getLayoutBounds().getMinX());
                            imgviewrSign.setLayoutY(landObjects.getY() - imgviewrSign.getLayoutBounds().getMinY());
                            break;

                        case "sign":
                            landObjects.setWidth(52);
                            landObjects.setHeight(64);
                            ImageView imgviewSign = new ImageView(imgSign);
                            apaneMain.getChildren().add(imgviewSign);
                            imgviewSign.setLayoutX(landObjects.getX() - imgviewSign.getLayoutBounds().getMinX());
                            imgviewSign.setLayoutY(landObjects.getY() - imgviewSign.getLayoutBounds().getMinY());
                            break;

                        case "house":
                            landObjects.setWidth(472);
                            landObjects.setHeight(300);
                            ImageView imgviewHouse = new ImageView(imgHouse);
                            apaneMain.getChildren().add(imgviewHouse);
                            imgviewHouse.setLayoutX(landObjects.getX() - imgviewHouse.getLayoutBounds().getMinX());
                            imgviewHouse
                                    .setLayoutY(landObjects.getY() - imgviewHouse.getLayoutBounds().getMinY() - 124);
                            break;

                        case "coin":
                            landObjects.setWidth(0);
                            landObjects.setHeight(0);
                            ImageView imgviewCoin = new ImageView(imgCoin);
                            apaneMain.getChildren().add(imgviewCoin);
                            imgviewCoin.setLayoutX(landObjects.getX() - imgviewCoin.getLayoutBounds().getMinX());
                            imgviewCoin.setLayoutY(landObjects.getY() - imgviewCoin.getLayoutBounds().getMinY());
                            imgViewList.add(imgviewCoin);
                            break;

                        case "coord":
                            int spawnNum = ThreadLocalRandom.current().nextInt(10);
                            switch (world.getDifficulty()) {

                                case EASY:
                                    if (spawnNum >= 0 && spawnNum <= 8) {
                                        Grunt grunt = new Grunt(nextId + 1);
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(64);
                                        grunt.setHeight(96);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 8) {
                                        Juggernaut jugg = new Juggernaut(nextId + 1);
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(144);
                                        jugg.setHeight(156);
                                        spawnEnemies(jugg);
                                    }
                                    break;

                                case MEDIUM:
                                    if (spawnNum >= 0 && spawnNum <= 6) {
                                        Grunt grunt = new Grunt(nextId + 1);
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(64);
                                        grunt.setHeight(96);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 6) {
                                        Juggernaut jugg = new Juggernaut(nextId + 1);
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(144);
                                        jugg.setHeight(156);
                                        spawnEnemies(jugg);
                                    }
                                    break;

                                case HARD:
                                    if (spawnNum >= 0 && spawnNum <= 4) {
                                        Grunt grunt = new Grunt(nextId + 1);
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(64);
                                        grunt.setHeight(96);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 4) {
                                        Juggernaut jugg = new Juggernaut(nextId + 1);
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(144);
                                        jugg.setHeight(156);
                                        spawnEnemies(jugg);
                                    }
                                    break;

                                default:
                                    break;

                            }

                        default:
                            break;

                    }
                }
            }
        }
    }

    /**
     * Draws the entire game screen.
     * 
     * @param x           - the x value to set the player at
     * @param y           - the y value to set the player at
     * @param playerImage - the image to load into the player imageview.
     */
    @FXML
    public void drawScreen(double x, double y, Image playerImage) {
        drawPlayer(x, y, playerImage);
        drawWorld();
        drawHealth();
        drawLocationLabel();
        drawScore();
        drawPauseButton();
    }

    /**
     * Switches the zone the player is in depending on his x and y coordinates.
     * Only switches if the direction the player is trying to move to is not null.
     */
    @FXML
    public void switchZones() {

        if (world.getPlayer().getY() <= 15) {

            if (world.getCurrentlocation().getNorthZone() != null) {

                world.setCurrentlocation(world.getCurrentlocation().getNorthZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(720, 800, imgPlayerBack);

            }

        } else if (world.getPlayer().getX() <= 15) {

            if (world.getCurrentlocation().getWestZone() != null) {

                world.setCurrentlocation(world.getCurrentlocation().getWestZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(1300, 450, imgPlayerLeft);

            }

        } else if (world.getPlayer().getY() >= 770) {

            if (world.getCurrentlocation().getSouthZone() != null) {

                world.setCurrentlocation(world.getCurrentlocation().getSouthZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(720, 20, imgPlayerFront);

            }

        } else if (world.getPlayer().getX() >= 1330) {

            if (world.getCurrentlocation().getEastZone() != null) {

                world.setCurrentlocation(world.getCurrentlocation().getEastZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(20, 450, imgPlayerRight);

            }

        }

        processAnimationDirection();

    }

    /**
     * Central "timer" method that calls the world's updateWorld() method.
     */
    @FXML
    public void updateWorld() {

        world.updateWorld();
        var iterator = world.getEntityList().iterator();
        drawHealth();

        if (world.isGameOver()) {

            imgviewPlayer.setImage(imgPlayerDeath);
            PauseTransition playerDeathPause = new PauseTransition(Duration.seconds(0.5));
            playerDeathPause.setOnFinished(e -> imgviewPlayer.setVisible(false));
            playerDeathPause.play();

            timeline.pause();
            uPressed.set(false);
            lPressed.set(false);
            dPressed.set(false);
            rPressed.set(false);
            setIsPaused(true);

            apaneMain.getChildren().add(imgviewBackgroundDim);
            createGameOverVbox(String.valueOf(world.getScore()));
            apaneMain.getChildren().add(gameOverVbox);

            music.stop();
            
            gameOverMusic.volumeProperty().set(.07);
            gameOverMusic.play();
        }

        while (iterator.hasNext()) {

            Entity entity = iterator.next();

            // Update grunts
            if (entity instanceof Grunt) {

                Grunt grunt = (Grunt) entity;
                grunt.navigate();
                updateGruntGraphic(grunt);

                // for (ImageView imgview : imgViewList) {}

                // Increase score if grunt is dead
                if (((Grunt) entity).isDead()) {
                    // Increase score
                    Thread gruntThread = new Thread(() -> {
                        deathSound.play();
                    });
                    gruntThread.start();
                    world.increaseScore(100);
                    displayScoreIncrease(100);

                    // Remove grunt
                    iterator.remove();

                }

            } else if (entity instanceof Juggernaut) {
                Juggernaut jugg = (Juggernaut) entity;
                jugg.navigate();
                updateJuggGraphic(jugg);

                // for (ImageView imgview : imgViewList) {}

                if (jugg.isDead()) {
                    Thread juggThread = new Thread(() -> {
                        deathSound.play();
                    });
                    juggThread.start();
                    world.increaseScore(300);
                    displayScoreIncrease(300);

                    iterator.remove();
                }
            }

        }

    }

    /**
     * Changes the grunt's ImageView to the appropriate animation based on its
     * direction and movement.
     * 
     * @param grunt - the grunt to animate
     */
    @FXML
    public void updateGruntGraphic(Grunt grunt) {
        ImageView imgview = new ImageView();

        for (ImageView foundImageView : imgViewList) {
            if (foundImageView.getX() == grunt.getX() && foundImageView.getY() == grunt.getY())
                imgview = foundImageView;
        }

        if (grunt.getDirection() >= 0 && grunt.getDirection() < 90
                || grunt.getDirection() > 270 && grunt.getDirection() <= 360) {
            imgview.setImage(imgGruntRightMove);

        } else if (grunt.getDirection() == 90) {
            imgview.setImage(imgGruntBackMove);

        } else if (grunt.getDirection() > 90 && grunt.getDirection() <= 180
                || grunt.getDirection() > 180 && grunt.getDirection() < 270) {
            imgview.setImage(imgGruntLefttMove);

        } else if (grunt.getDirection() == 270) {
            imgview.setImage(imgGruntFrontMove);
        }

        if (grunt.isDead()) {
            imgview.setImage(imgGruntDeath);
            ImageView gruntdeathImageView = imgview;
            PauseTransition gruntPause = new PauseTransition(Duration.seconds(0.5));
            gruntPause.setOnFinished(e -> gruntdeathImageView.setVisible(false));
            gruntPause.play();
        }
    }

    /**
     * Changes the juggernaut's ImageView to the appropriate animation based on its
     * direction and movement.
     * 
     * @param jugg - the juggernaut to be animated
     */
    @FXML
    public void updateJuggGraphic(Juggernaut jugg) {
        ImageView imgview = new ImageView();

        for (ImageView foundImageView : imgViewList) {
            if (foundImageView.getX() == jugg.getX() && foundImageView.getY() == jugg.getY())
                imgview = foundImageView;
        }

        if (jugg.getState() == JuggernautState.PATROL || jugg.getState() == JuggernautState.ATTACK) {

            if (jugg.getDirection() >= 0 && jugg.getDirection() < 90
                    || jugg.getDirection() > 270 && jugg.getDirection() <= 360) {
                imgview.setImage(imgJuggRightMoveSlow);

            } else if (jugg.getDirection() == 90) {
                imgview.setImage(imgJuggBackMoveSlow);

            } else if (jugg.getDirection() > 90 && jugg.getDirection() <= 180
                    || jugg.getDirection() > 180 && jugg.getDirection() < 270) {
                imgview.setImage(imgJuggLeftMoveSlow);

            } else if (jugg.getDirection() == 270) {
                imgview.setImage(imgJuggFrontMoveSlow);
            }

        } else if (jugg.getState() == JuggernautState.FRENZY) {

            if (jugg.getDirection() >= 0 && jugg.getDirection() < 90
                    || jugg.getDirection() > 270 && jugg.getDirection() <= 360) {
                imgview.setImage(imgJuggRightMoveFast);

            } else if (jugg.getDirection() == 90) {
                imgview.setImage(imgJuggBackMoveFast);

            } else if (jugg.getDirection() > 90 && jugg.getDirection() <= 180
                    || jugg.getDirection() > 180 && jugg.getDirection() < 270) {
                imgview.setImage(imgJuggLefttMoveFast);

            } else if (jugg.getDirection() == 270) {
                imgview.setImage(imgJuggFrontMoveFast);
            }

        }

        if (jugg.isHitPlayer()) {

            if (jugg.getDirection() >= 0 && jugg.getDirection() < 90
                    || jugg.getDirection() > 270 && jugg.getDirection() <= 360) {
                ImageView juggHitImageView = imgview;
                PauseTransition juggPause = new PauseTransition(Duration.seconds(0.9));
                juggPause.setOnFinished(e -> juggHitImageView.setImage(imgJuggRightAttack));
                juggPause.play();
                jugg.setHitPlayer(false);
                updateJuggGraphic(jugg);

            } else if (jugg.getDirection() == 90) {
                ImageView juggHitImageView = imgview;
                PauseTransition juggPause = new PauseTransition(Duration.seconds(0.9));
                juggPause.setOnFinished(e -> juggHitImageView.setImage(imgJuggBackAttack));
                juggPause.play();
                jugg.setHitPlayer(false);
                updateJuggGraphic(jugg);

            } else if (jugg.getDirection() > 90 && jugg.getDirection() <= 180
                    || jugg.getDirection() > 180 && jugg.getDirection() < 270) {
                ImageView juggHitImageView = imgview;
                PauseTransition juggPause = new PauseTransition(Duration.seconds(0.9));
                juggPause.setOnFinished(e -> juggHitImageView.setImage(imgJuggLeftAttack));
                juggPause.play();
                jugg.setHitPlayer(false);
                updateJuggGraphic(jugg);

            } else if (jugg.getDirection() == 270) {
                ImageView juggHitImageView = imgview;
                PauseTransition juggPause = new PauseTransition(Duration.seconds(0.9));
                juggPause.setOnFinished(e -> juggHitImageView.setImage(imgJuggRightAttack));
                juggPause.play();
                jugg.setHitPlayer(false);
                updateJuggGraphic(jugg);
            }
        }

        if (jugg.isDead()) {
            imgview.setImage(imgJuggDeath);
            ImageView juggdeathImageView = imgview;
            PauseTransition juggPause = new PauseTransition(Duration.seconds(0.5));
            juggPause.setOnFinished(e -> juggdeathImageView.setVisible(false));
            juggPause.play();
        }
    }

    // Animation timer specifically for the player, does not effect the other
    // entities movement.
    AnimationTimer playerMovement = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            var player = world.getPlayer();
            double speed = player.getSpeed();

            var obstacles = world.getCurrentlocation().getObjectList().stream().filter(e -> (e.isCollidable()))
                    .toList();

            if (uPressed.get() && !dPressed.get()) { // Up

                for (double i = 0; i < speed; i += 0.1) {

                    player.setY(player.getY() - 0.1);

                    for (Entity obstacle : obstacles) {

                        if (player.intersects(obstacle))
                            player.setY(player.getY() + 0.1);

                    }

                }

                imgviewPlayer.setY(player.getY());

            }

            if (dPressed.get() && !uPressed.get()) { // Down

                for (double i = 0; i < speed; i += 0.1) {

                    player.setY(player.getY() + 0.1);

                    for (Entity obstacle : obstacles) {

                        if (player.intersects(obstacle))
                            player.setY(player.getY() - 0.1);

                    }

                }

                imgviewPlayer.setY(player.getY());

            }

            if (lPressed.get() && !rPressed.get()) { // Left

                for (double i = 0; i < speed; i += 0.1) {

                    player.setX(player.getX() - 0.1);

                    for (Entity obstacle : obstacles) {

                        if (player.intersects(obstacle))
                            player.setX(player.getX() + 0.1);

                    }

                }

                imgviewPlayer.setX(player.getX());

            }

            if (rPressed.get() && !lPressed.get()) { // Right

                for (double i = 0; i < speed; i += 0.1) {

                    player.setX(player.getX() + 0.1);

                    for (Entity obstacle : obstacles) {

                        if (player.intersects(obstacle))
                            player.setX(player.getX() - 0.1);

                    }

                }

                imgviewPlayer.setX(player.getX());

            }

        }

    };

    // The direction of the last animation
    private mapDirection lastAnimationDirection = mapDirection.RIGHT;

    /**
     * Update the player image view's image to reflect movement direction.
     */
    private void processAnimationDirection() {

        if (uPressed.get() && !dPressed.get() && !lPressed.get() && !rPressed.get()) {

            lastAnimationDirection = mapDirection.UP;
            imgviewPlayer.setImage(imgPlayerBackMove);

        } else if (dPressed.get() && !uPressed.get() && !lPressed.get() && !rPressed.get()) {

            lastAnimationDirection = mapDirection.DOWN;
            imgviewPlayer.setImage(imgPlayerFrontMove);

        } else if (rPressed.get() && !lPressed.get()) {

            lastAnimationDirection = mapDirection.RIGHT;
            imgviewPlayer.setImage(imgPlayerRightMove);

        } else if (lPressed.get() && !rPressed.get()) {

            lastAnimationDirection = mapDirection.LEFT;
            imgviewPlayer.setImage(imgPlayerLeftMove);

        } else if (lastAnimationDirection == mapDirection.UP) {

            imgviewPlayer.setImage(imgPlayerBack);

        } else if (lastAnimationDirection == mapDirection.DOWN) {

            imgviewPlayer.setImage(imgPlayerFront);

        } else if (lastAnimationDirection == mapDirection.LEFT) {

            imgviewPlayer.setImage(imgPlayerLeft);

        } else {

            imgviewPlayer.setImage(imgPlayerRight);

        }

    }

    /**
     * Handles various different cases when the player presses a key.
     * 
     * @param event - the key currently held down.
     */
    public void keyPressed(KeyEvent event) {

        switch (event.getCode()) {

            case UP:
                if (!isPaused()) {
                    uPressed.set(true);

                    world.getPlayer().setDirection(90);
                }

                break;

            case LEFT:
                if (!isPaused()) {
                    lPressed.set(true);

                    world.getPlayer().setDirection(180);
                }

                break;

            case DOWN:
                if (!isPaused()) {
                    dPressed.set(true);

                    world.getPlayer().setDirection(270);
                }

                break;

            case RIGHT:
                if (!isPaused()) {
                    rPressed.set(true);

                    world.getPlayer().setDirection(0);
                }

                break;

            case C:

                if (isPaused())
                    break;

                if (cheatModeEnabled) {

                    cheatModeEnabled = false;
                    world.getPlayer().setDamage(1);
                    world.getPlayer().setMaxHealth(5);
                    world.getPlayer().setHealth(5);

                } else {

                    cheatModeEnabled = true;
                    world.getPlayer().setDamage(1000);
                    world.getPlayer().setMaxHealth(1000);
                    world.getPlayer().setHealth(1000);

                }

                drawScore();

                break;

            default:
                break;

        }

        processAnimationDirection();

    }

    /**
     * Handles various different cases when a player releases a key
     * 
     * @param event - the key the player released.
     */
    public void keyReleased(KeyEvent event) {

        switch (event.getCode()) {

            case UP:
                if (!isPaused()) {
                    uPressed.set(false);

                    processAnimationDirection();
                }

                break;

            case LEFT:
                if (!isPaused()) {
                    lPressed.set(false);

                    processAnimationDirection();
                }

                break;

            case DOWN:
                if (!isPaused()) {
                    dPressed.set(false);

                    processAnimationDirection();
                }

                break;

            case RIGHT:
                if (!isPaused()) {
                    rPressed.set(false);

                    processAnimationDirection();
                }

                break;

            case Z:
                if (!isPaused()) {
                    handleInteract();
                }

                break;

            case X:

                if (!isPaused())
                    switchZones();
                break;

            case SPACE:
                if (!isPaused()) {

                    Enemy enemy = world.getPlayer().attack(lastAnimationDirection);

                    handleAttackGraphic(lastAnimationDirection, enemy);

                }

                break;

            case ESCAPE:
                if (isPaused()) {
                    // setIsPaused(false);
                    unpause();
                } else if (world.isGameOver()) {

                } else {
                    // setIsPaused(true);
                    pause();
                }

                break;

            default:
                break;

        }

    }

    /**
     * Pauses the game and brings up the pause menu
     */
    @FXML
    public void pause() {
        timeline.pause();
        uPressed.set(false);
        lPressed.set(false);
        dPressed.set(false);
        rPressed.set(false);
        processAnimationDirection();

        apaneMain.getChildren().add(imgviewBackgroundDim);
        apaneMain.getChildren().add(pauseVbox);

        setIsPaused(true);
    }

    /**
     * Unpauses the game and hides the pause menu
     */
    @FXML
    public void unpause() {
        timeline.play();
        apaneMain.getChildren().remove(imgviewBackgroundDim);
        apaneMain.getChildren().remove(pauseVbox);
        setIsPaused(false);
    }

    /**
     * Populates a VBox with the buttons for the pause menu
     */
    @FXML
    public void createPauseVbox() {
        pauseVbox.setAlignment(Pos.TOP_CENTER);
        pauseVbox.setSpacing(10.0);

        Label pauseLbl = new Label();
        pauseLbl.setText("GAME PAUSED");
        pauseLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");
        pauseLbl.setTextAlignment(TextAlignment.CENTER);

        // Button Graphic Changing
        // *****************************************************************************
        imgviewResumeBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewResumeBtn.setImage(imgResumeBtn2);
                });
                thread.start();
            }
        });
        imgviewResumeBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewResumeBtn.setImage(imgResumeBtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewSaveBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewSaveBtn.setImage(imgSaveBtn2);
                });
                thread.start();
            }
        });
        imgviewSaveBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewSaveBtn.setImage(imgSaveBtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewLoadBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewLoadBtn.setImage(imgLoadbtn2);
                });
                thread.start();
            }
        });
        imgviewLoadBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewLoadBtn.setImage(imgLoadbtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewHelpBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewHelpBtn.setImage(imgHelpBtn2);
                });
                thread.start();
            }
        });
        imgviewHelpBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBtn.setImage(imgHelpBtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewQuitBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewQuitBtn.setImage(imgQuitBtn2);
                });
                thread.start();
                Platform.runLater(thread);
            }
        });
        imgviewQuitBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewQuitBtn.setImage(imgQuitBtn1);
            }
        });
        // ******************************************************************************************************

        // Button Functions
        // *************************************************************************************
        imgviewResumeBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                unpause();
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewSaveBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                try {

                    Serialization.save("SAVEGAME.txt", World.instance().getEntityList());
                    System.out.println("Game Saved!");

                } catch (IOException ev) {

                    System.out.println(ev.getMessage());

                }
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewLoadBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                try {

                    apaneMain.getChildren().removeAll(apaneMain.getChildren());
                    Serialization.load("SAVEGAME.txt");
                    drawScreen(world.getPlayer().getX(), world.getPlayer().getY(), imgPlayerRight);
                    unpause();
                    setIsPaused(false);
                    System.out.println("Game Loaded!");

                } catch (IOException ev) {

                    System.out.println(ev.getMessage());

                }
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewHelpBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                apaneMain.getChildren().remove(pauseVbox);
                apaneMain.getChildren().add(helpVbox);
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewQuitBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                World.instance();
                World.reset();
                Stage stage = (Stage) imgviewQuitBtn.getScene().getWindow();
                stage.close();

                try {
                    openMainWin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // ******************************************************************************************************

        pauseVbox.getChildren().add(pauseLbl);
        pauseVbox.getChildren().add(imgviewResumeBtn);
        pauseVbox.getChildren().add(imgviewSaveBtn);
        pauseVbox.getChildren().add(imgviewLoadBtn);
        pauseVbox.getChildren().add(imgviewHelpBtn);
        pauseVbox.getChildren().add(imgviewQuitBtn);

        pauseVbox.setLayoutX(550);
        pauseVbox.setLayoutY(250);
    }

    @FXML
    public void createHelpVbox() {
        // Mouse
        // Pressed/Released------------------------------------------------------------------------------
        imgviewHelpBackBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewHelpBackBtn.setImage(imgBackBtn2);
                });
                thread.start();
            }
        });
        imgviewHelpBackBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBackBtn.setImage(imgBackBtn1);
            }
        });
        // -------------------------------------------------------------------------------------------------

        imgviewHelpBackBtn.setFitHeight(80);
        imgviewHelpBackBtn.setFitWidth(312);
        imgviewHelpBackBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                apaneMain.getChildren().remove(helpVbox);
                apaneMain.getChildren().add(pauseVbox);
            }
        });

        ImageView move = new ImageView(new Image("Final Assets/Help/PNG/Help-Move-156x88.png"));
        ImageView attack = new ImageView(new Image("Final Assets/Help/PNG/Help-Attack-156x88.png"));
        ImageView interact = new ImageView(new Image("Final Assets/Help/PNG/Help-Interact-156x88.png"));
        ImageView travel = new ImageView(new Image("Final Assets/Help/PNG/Help-Travel-156x88.png"));
        ImageView pause = new ImageView(new Image("Final Assets/Help/PNG/Help-Pause-156x88.png"));
        ImageView cheat = new ImageView(new Image("Final Assets/Help/PNg/Help-Cheat-156x88.png"));

        move.setFitHeight(88);
        move.setFitWidth(156);
        attack.setFitHeight(88);
        attack.setFitWidth(156);
        interact.setFitHeight(88);
        interact.setFitWidth(156);
        travel.setFitHeight(88);
        travel.setFitWidth(156);
        pause.setFitHeight(88);
        pause.setFitWidth(156);
        cheat.setFitHeight(88);
        cheat.setFitWidth(156);

        Label movelbl = new Label("Move: ");
        Label attacklbl = new Label("Attack: ");
        Label interactlbl = new Label("Interact: ");
        Label travellbl = new Label("Travel: ");
        Label pauselbl = new Label("Pause: ");
        Label cheatlbl = new Label("Cheat: ");

        movelbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        attacklbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        interactlbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        travellbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        pauselbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        cheatlbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        HBox moveHbox = new HBox();
        HBox attackHbox = new HBox();
        HBox interactHbox = new HBox();
        HBox travelHbox = new HBox();
        HBox pauseHbox = new HBox();
        HBox cheatHbox = new HBox();

        moveHbox.getChildren().add(movelbl);
        moveHbox.getChildren().add(move);
        attackHbox.getChildren().add(attacklbl);
        attackHbox.getChildren().add(attack);
        interactHbox.getChildren().add(interactlbl);
        interactHbox.getChildren().add(interact);
        travelHbox.getChildren().add(travellbl);
        travelHbox.getChildren().add(travel);
        pauseHbox.getChildren().add(pauselbl);
        pauseHbox.getChildren().add(pause);
        cheatHbox.getChildren().add(cheatlbl);
        cheatHbox.getChildren().add(cheat);

        moveHbox.setAlignment(Pos.CENTER);
        attackHbox.setAlignment(Pos.CENTER);
        interactHbox.setAlignment(Pos.CENTER);
        travelHbox.setAlignment(Pos.CENTER);
        pauseHbox.setAlignment(Pos.CENTER);
        cheatHbox.setAlignment(Pos.CENTER);

        Label titleLbl = new Label("CONTROLS");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");

        helpVbox.getChildren().add(titleLbl);
        helpVbox.getChildren().add(moveHbox);
        helpVbox.getChildren().add(attackHbox);
        helpVbox.getChildren().add(interactHbox);
        helpVbox.getChildren().add(travelHbox);
        helpVbox.getChildren().add(pauseHbox);
        helpVbox.getChildren().add(cheatHbox);
        helpVbox.getChildren().add(imgviewHelpBackBtn);

        helpVbox.setAlignment(Pos.CENTER);
        helpVbox.setSpacing(10.0);

        helpVbox.setLayoutX(550);
        helpVbox.setLayoutY(82);
    }

    @FXML
    public void createGameOverVbox(String score) {
        Label titleLbl = new Label("GAME OVER!");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");
        // Mouse
        // Pressed/Released------------------------------------------------------------------------------
        imgviewGOQuitBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Thread thread = new Thread(() -> {
                    click.play();
                    imgviewGOQuitBtn.setImage(imgQuitBtn2);
                });
                thread.start();
            }
        });
        imgviewGOQuitBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewGOQuitBtn.setImage(imgQuitBtn1);
            }
        });
        // -------------------------------------------------------------------------------------------------

        imgviewGOQuitBtn.setFitHeight(80);
        imgviewGOQuitBtn.setFitWidth(312);
        imgviewGOQuitBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                World.instance();
                World.reset();
                Stage stage = (Stage) imgviewGOQuitBtn.getScene().getWindow();
                stage.close();
                gameOverMusic.stop();

                try {
                    openMainWin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Label scoreLbl = new Label("Score: " + score);
        scoreLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        gameOverVbox.getChildren().add(titleLbl);
        gameOverVbox.getChildren().add(scoreLbl);

        Leaderboard leaderboard;
        try {
            leaderboard = new Leaderboard(Serialization.loadScores("HIGHSCORES.txt"));
            ArrayList<HighScore> newLeaderboard = leaderboard.process(name, Integer.parseInt(score));
            if (leaderboard.isHighScore()) {
                Serialization.saveScores("HIGHSCORES.txt", newLeaderboard);
                Label newHs = new Label("New Highscore!");
                newHs.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
                gameOverVbox.getChildren().add(newHs);
            }
        } catch (IOException e1) {
            Label newHs = new Label("Highscore File Not Found 8(");
            newHs.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
            gameOverVbox.getChildren().add(newHs);
        }

        String hsNames = "";
        String hsScores = "";
        String hsPlace = "";
        ArrayList<HighScore> highscoresList;
        try {
            highscoresList = Serialization.loadScores("HIGHSCORES.txt");
            for (int i = 0; i < highscoresList.size(); ++i) {
                hsPlace = hsPlace + (i + 1) + ".\n";
                hsNames = hsNames + highscoresList.get(i).getPlayerName() + "\n";
                hsScores = hsScores + highscoresList.get(i).getScore() + "\n";
            }
        } catch (IOException e1) {
            hsPlace = "HIGHSCORES FILE NOT FOUND :(";
            hsNames = "HIGHSCORES FILE NOT FOUND :(";
            hsScores = "HIGHSCORES FILE NOT FOUND :(";
        }

        Label hsPlacelbl = new Label(hsPlace);
        Label hsNameslbl = new Label(hsNames);
        Label hsScoreslbl = new Label(hsScores);
        hsPlacelbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        hsNameslbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        hsScoreslbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        hsPlacelbl.setTextAlignment(TextAlignment.LEFT);
        hsNameslbl.setTextAlignment(TextAlignment.LEFT);
        hsScoreslbl.setTextAlignment(TextAlignment.RIGHT);

        Label title2Lbl = new Label("HIGHSCORES");
        title2Lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");

        gameOverVbox.getChildren().add(title2Lbl);
        HBox h = new HBox();
        h.setSpacing(20.0);
        h.getChildren().add(hsPlacelbl);
        h.getChildren().add(hsNameslbl);
        h.getChildren().add(hsScoreslbl);
        h.setAlignment(Pos.CENTER);
        gameOverVbox.getChildren().add(h);

        gameOverVbox.getChildren().add(imgviewGOQuitBtn);
        gameOverVbox.setAlignment(Pos.CENTER);
        gameOverVbox.setSpacing(10.0);
        gameOverVbox.setLayoutX(550);
        gameOverVbox.setLayoutY(100);
    }

    /**
     * Displays the attack animation depending on the player's direction.
     */
    @FXML
    public void handleAttackGraphic(mapDirection direction, Enemy enemy) {

        PauseTransition attackPause = new PauseTransition(Duration.seconds(0.32));
        attackPause.setOnFinished(e -> processAnimationDirection());

        switch (direction) {

            case DOWN:

                imgviewPlayer.setImage(imgPlayerAttackFront);
                attackPause.play();
                break;

            case LEFT:

                imgviewPlayer.setImage(imgPlayerAttackLeft);
                attackPause.play();
                break;

            case UP:

                imgviewPlayer.setImage(imgPlayerAttackBack);
                attackPause.play();
                break;

            case RIGHT:

                imgviewPlayer.setImage(imgPlayerAttackRight);
                attackPause.play();
                break;

        }

        swordSlash.play();

        if (enemy != null) {
            if (enemy instanceof Grunt) {
                gruntHit.play();
            } else if (enemy instanceof Juggernaut) {
                juggHit.play();
            }
        }

    }

    /**
     * Displays the player's health as a bar of hearts.
     * 
     * @param health - the player's current health value.
     */
    public void displayHealth(int health) {

        switch (health) {

            case 9:
                imgviewHeart.setImage(imgHeart9);
                break;

            case 8:
                imgviewHeart.setImage(imgHeart8);
                break;

            case 7:
                imgviewHeart.setImage(imgHeart7);
                break;

            case 6:
                imgviewHeart.setImage(imgHeart6);
                break;

            case 5:
                imgviewHeart.setImage(imgHeart5);
                break;

            case 4:
                imgviewHeart.setImage(imgHeart4);
                break;

            case 3:
                imgviewHeart.setImage(imgHeart3);
                break;

            case 2:
                imgviewHeart.setImage(imgHeart2);
                break;

            case 1:
                imgviewHeart.setImage(imgHeart1);
                break;

            default:
                apaneMain.getChildren().remove(imgviewHeart);
                break;

        }

    }

    public void openMainWin() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        var scene = new Scene(loader.load());
        var stage0 = new Stage();

        MainWindow controller = loader.getController();

        stage0.setScene(scene);
        stage0.show();
        controller.initialize(stage0);
    }

    public void setIsPaused(boolean bool) {
        this.isPaused = bool;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public BooleanBinding getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(BooleanBinding keyPressed) {
        this.keyPressed = keyPressed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
