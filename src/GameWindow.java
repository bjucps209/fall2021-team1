import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Enemy;
import model.Entity;
import model.Grunt;
import model.Juggernaut;
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
    // **********************

    // UI ImageViews ************
    private ImageView imgviewBackgroundDim = new ImageView(imgBackgroundDim);
    private ImageView imgviewPauseBtn1 = new ImageView(imgPauseBtn1);
    private ImageView imgviewPauseBtn2 = new ImageView(imgPauseBtn2);
    private ImageView imgviewSaveBtn1 = new ImageView(imgSaveBtn1);
    private ImageView imgviewSaveBtn2 = new ImageView(imgSaveBtn2);
    private ImageView imgviewLoadBtn1 = new ImageView(imgLoadbtn1);
    private ImageView imgviewLoadBtn2 = new ImageView(imgLoadbtn2);
    private ImageView imgviewHelpBtn1 = new ImageView(imgHelpBtn1);
    private ImageView imgviewHelpBtn2 = new ImageView(imgHelpBtn2);
    private ImageView imgviewQuitBtn1 = new ImageView(imgQuitBtn1);
    private ImageView imgviewQuitBtn2 = new ImageView(imgQuitBtn2);
    private ImageView imgviewBackBtn1 = new ImageView(imgBackBtn1);
    private ImageView imgviewBackBtn2 = new ImageView(imgBackBtn2);
    // **********************

    // UI VBoxes *********************
    private VBox pauseVbox = new VBox();
    private VBox helpVbox = new VBox();
    // *****************************

    // Model Attributes
    private World world;

    // Temporary list to hold ImageViews. Similar functionality will be implemented
    // with enemy ID's later.
    ArrayList<ImageView> imgViewList;

    // Game clock
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(35), e -> updateWorld()));

    @FXML
    void initialize(Stage stage) {
        imgViewList = new ArrayList<ImageView>();
        world = World.instance();
        world.setScore(0);

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

        // Set Default Font
        Font.loadFont(getClass().getResourceAsStream("/Final Assets/UI/Minecraft.ttf"), 64);

        // Populating UI VBoxes
        createPauseVbox();
        createHelpVbox();

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

                Label lblMessage = new Label();
                lblMessage.setText(((NPC) interactedEntity).getMessage());
                lblMessage.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
                lblMessage.setLayoutX(interactedEntity.getX() - 53);
                lblMessage.setLayoutY(interactedEntity.getY() - 30);

                apaneMain.getChildren().add(lblMessage);

                PauseTransition labelPause = new PauseTransition(Duration.seconds(3));
                labelPause.setOnFinished(e -> lblMessage.setVisible(false));
                labelPause.play();

                return;

            case ITEM:

                // var item = (Item) interactedEntity;
                // displayText(interactedEntity.getX() - 53, interactedEntity.getY() - 30,
                // item.getMessage());

                // World.instance().increaseScore(item.getScoreIncrease());

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
                imgViewGrunt.xProperty().bindBidirectional(spawnedGrunt.xProperty());
                imgViewGrunt.yProperty().bindBidirectional(spawnedGrunt.yProperty());
                apaneMain.getChildren().add(imgViewGrunt);
                imgViewList.add(imgViewGrunt);

            } else if (entity instanceof Juggernaut) {
                Juggernaut spawnedJuggernaut = (Juggernaut) entity;
                spawnedJuggernaut.setOriginalX(spawnedJuggernaut.getX());
                spawnedJuggernaut.setOriginalY(spawnedJuggernaut.getY());
                ImageView imgViewJuggernaut = new ImageView();
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
        lblScore.textProperty()
                .bind(Bindings.createStringBinding(() -> String.valueOf(world.getScore()), world.scoreProperty()));
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

    /**
     * Displays the background and the objects contained inside of the current zone.
     */
    @FXML
    public void drawWorld() {
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
                            landObjects.setWidth(128);
                            landObjects.setHeight(128);
                            ImageView imgviewNPC = new ImageView(imgNPC);
                            apaneMain.getChildren().add(imgviewNPC);
                            imgviewNPC.setLayoutX(landObjects.getX() - imgviewNPC.getLayoutBounds().getMinX());
                            imgviewNPC.setLayoutY(landObjects.getY() - imgviewNPC.getLayoutBounds().getMinY());
                            break;

                        case "tree":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewTree = new ImageView(imgTree);
                            apaneMain.getChildren().add(imgviewTree);
                            imgviewTree.setLayoutX(landObjects.getX() - imgviewTree.getLayoutBounds().getMinX());
                            imgviewTree.setLayoutY(landObjects.getY() - imgviewTree.getLayoutBounds().getMinY());
                            break;

                        case "hFence":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewHFence = new ImageView(imgHFence);
                            apaneMain.getChildren().add(imgviewHFence);
                            imgviewHFence.setLayoutX(landObjects.getX() - imgviewHFence.getLayoutBounds().getMinX());
                            imgviewHFence.setLayoutY(landObjects.getY() - imgviewHFence.getLayoutBounds().getMinY());
                            break;

                        case "vFence":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewVFence = new ImageView(imgVFence);
                            apaneMain.getChildren().add(imgviewVFence);
                            imgviewVFence.setLayoutX(landObjects.getX() - imgviewVFence.getLayoutBounds().getMinX());
                            imgviewVFence.setLayoutY(landObjects.getY() - imgviewVFence.getLayoutBounds().getMinY());
                            break;

                        case "archPoles":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
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
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewStump = new ImageView(imgStump);
                            apaneMain.getChildren().add(imgviewStump);
                            imgviewStump.setLayoutX(landObjects.getX() - imgviewStump.getLayoutBounds().getMinX());
                            imgviewStump.setLayoutY(landObjects.getY() - imgviewStump.getLayoutBounds().getMinY());
                            break;

                        case "well":
                            landObjects.setWidth(256);
                            landObjects.setHeight(256);
                            ImageView imgviewWell = new ImageView(imgWell);
                            apaneMain.getChildren().add(imgviewWell);
                            imgviewWell.setLayoutX(landObjects.getX() - imgviewWell.getLayoutBounds().getMinX());
                            imgviewWell.setLayoutY(landObjects.getY() - imgviewWell.getLayoutBounds().getMinY());
                            break;

                        case "tomb":
                            landObjects.setWidth(64);
                            landObjects.setHeight(64);
                            ImageView imgviewTomb = new ImageView(imgTomb);
                            apaneMain.getChildren().add(imgviewTomb);
                            imgviewTomb.setLayoutX(landObjects.getX() - imgviewTomb.getLayoutBounds().getMinX());
                            imgviewTomb.setLayoutY(landObjects.getY() - imgviewTomb.getLayoutBounds().getMinY());
                            break;

                        case "lSign":
                            landObjects.setWidth(64);
                            landObjects.setHeight(64);
                            ImageView imgviewlSign = new ImageView(imgDSignLeft);
                            apaneMain.getChildren().add(imgviewlSign);
                            imgviewlSign.setLayoutX(landObjects.getX() - imgviewlSign.getLayoutBounds().getMinX());
                            imgviewlSign.setLayoutY(landObjects.getY() - imgviewlSign.getLayoutBounds().getMinY());
                            break;

                        case "rSign":
                            landObjects.setWidth(64);
                            landObjects.setHeight(64);
                            ImageView imgviewrSign = new ImageView(imgDSignRight);
                            apaneMain.getChildren().add(imgviewrSign);
                            imgviewrSign.setLayoutX(landObjects.getX() - imgviewrSign.getLayoutBounds().getMinX());
                            imgviewrSign.setLayoutY(landObjects.getY() - imgviewrSign.getLayoutBounds().getMinY());
                            break;

                        case "sign":
                            landObjects.setWidth(64);
                            landObjects.setHeight(64);
                            ImageView imgviewSign = new ImageView(imgSign);
                            apaneMain.getChildren().add(imgviewSign);
                            imgviewSign.setLayoutX(landObjects.getX() - imgviewSign.getLayoutBounds().getMinX());
                            imgviewSign.setLayoutY(landObjects.getY() - imgviewSign.getLayoutBounds().getMinY());
                            break;

                        case "house":
                            landObjects.setWidth(512);
                            landObjects.setHeight(512);
                            ImageView imgviewHouse = new ImageView(imgHouse);
                            apaneMain.getChildren().add(imgviewHouse);
                            imgviewHouse.setLayoutX(landObjects.getX() - imgviewHouse.getLayoutBounds().getMinX());
                            imgviewHouse.setLayoutY(landObjects.getY() - imgviewHouse.getLayoutBounds().getMinY());
                            break;

                        case "coord":
                            int spawnNum = ThreadLocalRandom.current().nextInt(10);
                            switch (world.getDifficulty()) {

                                case EASY:
                                    if (spawnNum > 0 && spawnNum <= 8) {
                                        Grunt grunt = new Grunt();
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(128);
                                        grunt.setHeight(128);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 8) {
                                        Juggernaut jugg = new Juggernaut();
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(128);
                                        jugg.setHeight(128);
                                        spawnEnemies(jugg);
                                    }
                                    break;

                                case MEDIUM:
                                    if (spawnNum > 0 && spawnNum <= 6) {
                                        Grunt grunt = new Grunt();
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(128);
                                        grunt.setHeight(128);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 6) {
                                        Juggernaut jugg = new Juggernaut();
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(128);
                                        jugg.setHeight(128);
                                        spawnEnemies(jugg);
                                    }
                                    break;

                                case HARD:
                                    if (spawnNum > 0 && spawnNum <= 4) {
                                        Grunt grunt = new Grunt();
                                        grunt.setX(landObjects.getX());
                                        grunt.setY(landObjects.getY());
                                        grunt.setWidth(128);
                                        grunt.setHeight(128);
                                        spawnEnemies(grunt);

                                    } else if (spawnNum > 4) {
                                        Juggernaut jugg = new Juggernaut();
                                        jugg.setX(landObjects.getX());
                                        jugg.setY(landObjects.getY());
                                        jugg.setWidth(128);
                                        jugg.setHeight(128);
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
                drawScreen(world.getPlayer().getX(), 700, imgPlayerBack);
            }

        } else if (world.getPlayer().getX() <= 15) {
            if (world.getCurrentlocation().getWestZone() != null) {
                world.setCurrentlocation(world.getCurrentlocation().getWestZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(1200, world.getPlayer().getY(), imgPlayerLeft);
            }

        } else if (world.getPlayer().getY() >= 770) {
            if (world.getCurrentlocation().getSouthZone() != null) {
                world.setCurrentlocation(world.getCurrentlocation().getSouthZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(world.getPlayer().getX(), 40, imgPlayerFront);
            }

        } else if (world.getPlayer().getX() >= 1330) {
            if (world.getCurrentlocation().getEastZone() != null) {
                world.setCurrentlocation(world.getCurrentlocation().getEastZone());
                apaneMain.getChildren().removeAll(apaneMain.getChildren());
                drawScreen(50, world.getPlayer().getY(), imgPlayerRight);
            }
        }
    }

    /**
     * Central "timer" method that calls the world's updateWorld() method.
     */
    @FXML
    public void updateWorld() {

        world.updateWorld();
        var iterator = world.getEntityList().iterator();
        drawHealth();

        while (iterator.hasNext()) {

            Entity entity = iterator.next();

            // Update grunts
            if (entity instanceof Grunt) {

                Grunt grunt = (Grunt) entity;
                grunt.navigate();
                updateGruntGraphic(grunt);

                //for (ImageView imgview : imgViewList) {}

                // Increase score if grunt is dead
                if (((Grunt) entity).isDead()) {
                    // Increase score
                    world.increaseScore(100);

                    // Remove grunt
                    iterator.remove();

                }

            } else if (entity instanceof Juggernaut) {
                Juggernaut jugg = (Juggernaut) entity;
                jugg.navigate();
                updateJuggGraphic(jugg);

                //for (ImageView imgview : imgViewList) {}

                if (jugg.isDead()) {
                    world.increaseScore(300);

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

            double speed = world.getPlayer().getSpeed();

            if (uPressed.get()) { // Up

                world.getPlayer().setY(world.getPlayer().getY() - speed);
                imgviewPlayer.setY(world.getPlayer().getY());

            }

            if (dPressed.get()) { // Down

                world.getPlayer().setY(world.getPlayer().getY() + speed);
                imgviewPlayer.setY(world.getPlayer().getY());

            }

            if (lPressed.get()) { // Left

                world.getPlayer().setX(world.getPlayer().getX() - speed);
                imgviewPlayer.setX(world.getPlayer().getX());

            }

            if (rPressed.get()) { // Right

                world.getPlayer().setX(world.getPlayer().getX() + speed);
                imgviewPlayer.setX(world.getPlayer().getX());

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

                    processAnimationDirection();

                    world.getPlayer().setDirection(90);
                }

                break;

            case LEFT:
                if (!isPaused()) {
                    lPressed.set(true);

                    processAnimationDirection();

                    world.getPlayer().setDirection(180);
                }

                break;

            case DOWN:
                if (!isPaused()) {
                    dPressed.set(true);

                    processAnimationDirection();

                    world.getPlayer().setDirection(270);
                }

                break;

            case RIGHT:
                if (!isPaused()) {
                    rPressed.set(true);

                    processAnimationDirection();

                    world.getPlayer().setDirection(0);
                }

                break;

            // processAnimationDirection();

            // world.getPlayer().setDirection(0);
            // break;

            case Q:
                int i = 0; // for pinging the debugger to peek at variables, delete on final release.

            default:
                break;

        }

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
                if (!isPaused()) {
                    switchZones();
                }

                break;

            case SPACE:
                if (!isPaused()) {
                    
                    world.getPlayer().attack(lastAnimationDirection);
                    handleAttackGraphic(lastAnimationDirection);
                
                }

                break;

            case ESCAPE:
                if (isPaused()) {
                    setIsPaused(false);
                    unpause();
                } else {
                    setIsPaused(true);
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

    }

    /**
     * Unpauses the game and hides the pause menu
     */
    @FXML
    public void unpause() {
        timeline.play();
        apaneMain.getChildren().remove(imgviewBackgroundDim);
        apaneMain.getChildren().remove(pauseVbox);
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
        imgviewSaveBtn1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewSaveBtn1.setImage(imgSaveBtn2);
            }
        });
        imgviewSaveBtn1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewSaveBtn1.setImage(imgSaveBtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewLoadBtn1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewLoadBtn1.setImage(imgLoadbtn2);
            }
        });
        imgviewLoadBtn1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewLoadBtn1.setImage(imgLoadbtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewHelpBtn1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBtn1.setImage(imgHelpBtn2);
            }
        });
        imgviewHelpBtn1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBtn1.setImage(imgHelpBtn1);
            }
        });
        // ------------------------------------------------------------------------------------------------------
        imgviewQuitBtn1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewQuitBtn1.setImage(imgQuitBtn2);
            }
        });
        imgviewQuitBtn1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewQuitBtn1.setImage(imgQuitBtn1);
            }
        });
        // ******************************************************************************************************

        // Button Functions
        // *************************************************************************************
        imgviewSaveBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
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
        imgviewLoadBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                try {

                    Serialization.load("SAVEGAME.txt");
                    System.out.println("Game Loaded!");

                } catch (IOException ev) {

                    System.out.println(ev.getMessage());

                }
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewHelpBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                apaneMain.getChildren().remove(pauseVbox);
                apaneMain.getChildren().add(helpVbox);
            }
        });

        // ------------------------------------------------------------------------------------------------------
        imgviewQuitBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                World.instance();
                World.reset();
                Stage stage = (Stage) imgviewQuitBtn1.getScene().getWindow();
                stage.close();
            }
        });
        // ******************************************************************************************************

        pauseVbox.getChildren().add(pauseLbl);
        pauseVbox.getChildren().add(imgviewSaveBtn1);
        pauseVbox.getChildren().add(imgviewLoadBtn1);
        pauseVbox.getChildren().add(imgviewHelpBtn1);
        pauseVbox.getChildren().add(imgviewQuitBtn1);

        pauseVbox.setLayoutX(550);
        pauseVbox.setLayoutY(250);
    }

    @FXML
    public void createHelpVbox() {
        helpVbox.setAlignment(Pos.TOP_CENTER);
        helpVbox.setSpacing(10.0);
        helpVbox.getChildren().add(imgviewBackBtn1);

        // Button Graphic Changing
        // *****************************************************************************
        imgviewBackBtn1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackBtn1.setImage(imgBackBtn2);
            }
        });
        imgviewBackBtn1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackBtn1.setImage(imgBackBtn1);
            }
        });
        // ******************************************************************************************************

        // Button Functions
        // *************************************************************************************
        imgviewBackBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                apaneMain.getChildren().remove(helpVbox);
                apaneMain.getChildren().add(pauseVbox);
            }
        });
        // ******************************************************************************************************

        helpVbox.setLayoutX(550); // TODO: Needs Tweaking
        helpVbox.setLayoutY(250);
    }

    /**
     * Displays the attack animation depending on the player's direction.
     */
    @FXML
    public void handleAttackGraphic(mapDirection direction) {
        
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

    }

    /**
     * Displays the player's health as a bar of hearts.
     * 
     * @param health - the player's current health value.
     */
    public void displayHealth(int health) {

        switch (health) {

            case 10:
                imgviewHeart.setImage(imgHeart10);
                break;

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

        }

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

}
