import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Enemy;
import model.Entity;
import model.Grunt;
import model.NPC;
import model.Serialization;
import model.World;
import model.Zone;
import model.ZoneList;

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
    private Image imgattackAnim = new Image("Final Assets/Player/GIF/Player-Attack-128x128.gif");
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

    // Enemy Images *********
    private Image imgGruntDeath = new Image("Final Assets/Grunt/GIF/Grunt-Death-128x128.gif");
    private Image imgGrunt = new Image("Final Assets/Grunt/PNG/Grunt-Front-Stationary-128x128.png");
    // **********************

    // World Images *********
    private Image startScreen = new Image("Final Assets/World/PNG/World-StartArea-1440x900.png");
    private Image pathway = new Image("Final Assets/World/PNG/World-Pathways-1440x900.png");
    private Image grassyPlains = new Image("Final Assets/World/PNG/World-GrassyPlains-1440x900.png");
    private Image market = new Image("Final Assets/World/PNG/World-RunDown-1440x900.png");
    private Image lumberYard = new Image("Final Assets/World/PNG/World-LumberYard-1440x900.png");
    private Image farm = new Image("Final Assets/World/PNG/World-ForestEdge-1440x900.png");
    private Image wheatFields = new Image("Final Assets/World/PNG/World-WheatField-1440x900.png");
    private Image lake = new Image("Final Assets/World/PNG/World-Lake-1440x900.png");
    private Image graveyard = new Image("Final Assets/World/PNG/World-Graveyard-1440x900.png");
    private Image villageSquare = new Image("Final Assets/World/PNG/World-VillageSquare-1440x900.png");
    // **********************

    // Object Images ********
    private Image imgDSignLeft = new Image("Final Assets/Objects/PNG/Objects-DSign-Left-64x64.png");
    private Image imgDSignRight = new Image("Final Assets/Objects/PNG/Objects-DSign-Right-64x64.png");
    private Image imgSign = new Image("Final Assets/Objects/PNG/Objects-FSign-64x64.png");
    private Image imgArch = new Image("Final Assets/Objects/PNG/Objects-GateArch-256x256.png");
    private Image imgArchposts = new Image("Final Assets/Objects/PNG/Objects-GatePosts-256x256.png");
    private Image imgGravestone = new Image("Final Assets/Objects/PNG/Objects-Gravestone-64x64.png");
    private Image imgHFence = new Image("Final Assets/Objects/PNG/Objects-HFence-256x256.png");
    private Image imgVFence = new Image("Final Assets/Objects/PNG/Objects-VFence-256x256.png");
    private Image imgStump = new Image("Final Assets/Objects/PNG/Objects-Stump-256x256.png");
    private Image imgTree = new Image("Final Assets/Objects/PNG/Objects-Tree-256x256.png");
    private Image imgWell = new Image("Final Assets/Objects/PNG/Objects-Well-256x256.png");
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
    private ImageView imgviewLoadbtn1 = new ImageView(imgLoadbtn1);
    private ImageView imgviewLoadbtn2 = new ImageView(imgLoadbtn2);
    private ImageView imgviewHelpBtn1 = new ImageView(imgHelpBtn1);
    private ImageView imgviewHelpBtn2 = new ImageView(imgHelpBtn2);
    private ImageView imgviewQuitBtn1 = new ImageView(imgQuitBtn1);
    private ImageView imgviewQuitBtn2 = new ImageView(imgQuitBtn2);
    private ImageView imgviewBackBtn1 = new ImageView(imgBackBtn1);
    private ImageView imgviewBackBtn2 = new ImageView(imgBackBtn2);
    // **********************
    
    

    // Model Attributes
    private World world;

    // Temporary list to hold ImageViews. Similar functionality will be implemented
    // with enemy ID's later.
    ArrayList<ImageView> imgViewList;

    @FXML
    void initialize(Stage stage) {
        imgViewList = new ArrayList<ImageView>();

        // Timer for the updateworld method
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> updateWorld()));
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

        // Create player's imageview
        imgviewPlayer = new ImageView(imgPlayerBack);
        imgviewPlayer.setX(650);
        imgviewPlayer.setY(750);
        apaneMain.getChildren().add(imgviewPlayer);

        // Bind the player imageview's x and y to the player object
        world = World.instance();
        world.getPlayer().xProperty().bindBidirectional(imgviewPlayer.xProperty());
        world.getPlayer().yProperty().bindBidirectional(imgviewPlayer.yProperty());

        // Set background for the world
        BackgroundImage bImg = new BackgroundImage(startScreen, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        apaneMain.setBackground(bGround);

        // Generate health bar
        imgviewHeart = new ImageView();
        displayHealth(world.getPlayer().getHealth());
        AnchorPane.setTopAnchor(imgviewHeart, 10.0);
        AnchorPane.setRightAnchor(imgviewHeart, 10.0);
        apaneMain.getChildren().add(imgviewHeart);

        // Generate score label
        lblScore = new Label();
        world.setScore(0);
        lblScore.setText(String.valueOf(world.getScore()));
        lblScore.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        lblScore.textProperty()
                .bind(Bindings.createStringBinding(() -> String.valueOf(world.getScore()), world.scoreProperty()));
        AnchorPane.setBottomAnchor(lblScore, 10.0);
        AnchorPane.setLeftAnchor(lblScore, 10.0);
        apaneMain.getChildren().add(lblScore);

        // Generate location label
        lblLocation = new Label();
        lblLocation.setText(world.getCurrentlocation());
        lblLocation.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        AnchorPane.setTopAnchor(lblLocation, 10.0);
        AnchorPane.setLeftAnchor(lblLocation, 10.0);
        apaneMain.getChildren().add(lblLocation);

        // Adding an NPC to the starter area
        spawnObjects();

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
     * Spawns enemies in the current player's location, barebones implementation for
     * alpha.
     */
    @FXML
    public void spawnEnemies() {

        world.spawnEnemies();

        for (Entity entity : world.getEntityList()) {

            if (entity instanceof Enemy) {

                Enemy enemy = (Enemy) entity;
                ImageView imgViewGrunt = new ImageView(imgGrunt);
                imgViewGrunt.xProperty().bindBidirectional(enemy.xProperty());
                imgViewGrunt.yProperty().bindBidirectional(enemy.yProperty());
                apaneMain.getChildren().add(imgViewGrunt);
                imgViewList.add(imgViewGrunt);

            }

        }

    }

    @FXML
    public void spawnObjects() {
        for (Zone zone : ZoneList.instance().getLevels()) {
            if (world.getCurrentlocation().equals(zone.getZoneName())) {
                for (NPC landObjects : zone.getObjectList()) {

                    switch (landObjects.getDescription()) {
                        
                        case "NPC":
                            ImageView imgviewNPC = new ImageView(imgNPC);
                            apaneMain.getChildren().add(imgviewNPC);
                            imgviewNPC.setLayoutX(landObjects.getX() - imgviewNPC.getLayoutBounds().getMinX());
                            imgviewNPC.setLayoutY(landObjects.getY() - imgviewNPC.getLayoutBounds().getMinY());
                            break;
                        
                        case "tree":
                            ImageView imgviewTree = new ImageView(imgTree);
                            apaneMain.getChildren().add(imgviewTree);
                            imgviewTree.setLayoutX(landObjects.getX() - imgviewTree.getLayoutBounds().getMinX());
                            imgviewTree.setLayoutY(landObjects.getY() - imgviewTree.getLayoutBounds().getMinY());
                            break;
                        
                        case "hFence":
                            ImageView imgviewHFence = new ImageView(imgHFence);
                            apaneMain.getChildren().add(imgviewHFence);
                            imgviewHFence.setLayoutX(landObjects.getX() - imgviewHFence.getLayoutBounds().getMinX());
                            imgviewHFence.setLayoutY(landObjects.getY() - imgviewHFence.getLayoutBounds().getMinY());
                            break;
                        
                        case "archPoles":
                            ImageView imgviewArchPoles = new ImageView(imgArchposts);
                            apaneMain.getChildren().add(imgviewArchPoles);
                            imgviewArchPoles.setLayoutX(landObjects.getX() - imgviewArchPoles.getLayoutBounds().getMinX());
                            imgviewArchPoles.setLayoutY(landObjects.getY() - imgviewArchPoles.getLayoutBounds().getMinY());
                            break;
                        
                        case "arch":
                            ImageView imgviewArch = new ImageView(imgArch);
                            apaneMain.getChildren().add(imgviewArch);
                            imgviewArch.setLayoutX(landObjects.getX() - imgviewArch.getLayoutBounds().getMinX());
                            imgviewArch.setLayoutY(landObjects.getY() - imgviewArch.getLayoutBounds().getMinY());
                            break;
                        
                        default:
                            break;

                    }
                }
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

        while (iterator.hasNext()) {

            Entity entity = iterator.next();

            // Update grunts
            if (entity instanceof Grunt) {

                // Play death animation if dead
                if (((Grunt) entity).isDead()) {

                    for (ImageView imgview : imgViewList) {

                        imgview.setImage(imgGruntDeath);
                        PauseTransition gruntPause = new PauseTransition(Duration.seconds(0.5));
                        gruntPause.setOnFinished(e -> imgview.setVisible(false));
                        gruntPause.play();

                    }

                    // Increase score
                    world.increaseScore(100);

                    // Remove grunt
                    iterator.remove();

                }

            }

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

    /// Animation Attribute ///
    private enum AnimationDirection {

        UP, DOWN, LEFT, RIGHT

    }

    // The direction of the last animation
    private AnimationDirection lastAnimationDirection = AnimationDirection.RIGHT;

    /**
     * Update the player image view's image to reflect movement direction.
     */
    private void processAnimationDirection() {

        if (uPressed.get() && !dPressed.get() && !lPressed.get() && !rPressed.get()) {

            lastAnimationDirection = AnimationDirection.UP;
            imgviewPlayer.setImage(imgPlayerBackMove);
    
        } else if (dPressed.get() && !uPressed.get() && !lPressed.get() && !rPressed.get()) {
    
            lastAnimationDirection = AnimationDirection.DOWN;
            imgviewPlayer.setImage(imgPlayerFrontMove);

        } else if (rPressed.get() && !lPressed.get()) {

            lastAnimationDirection = AnimationDirection.RIGHT;
            imgviewPlayer.setImage(imgPlayerRightMove);
    
        } else if (lPressed.get() && !rPressed.get()) {
    
            lastAnimationDirection = AnimationDirection.LEFT;
            imgviewPlayer.setImage(imgPlayerLeftMove);

        } else if (lastAnimationDirection == AnimationDirection.UP) {

            imgviewPlayer.setImage(imgPlayerBack);

        } else if (lastAnimationDirection == AnimationDirection.DOWN) {

            imgviewPlayer.setImage(imgPlayerFront);
        
        } else if (lastAnimationDirection == AnimationDirection.LEFT) {

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
            uPressed.set(true);

            processAnimationDirection();

            world.getPlayer().setDirection(90);
            break;

        case LEFT:
            lPressed.set(true);
            
            processAnimationDirection();

            world.getPlayer().setDirection(180);
            break;

        case DOWN:
            dPressed.set(true);

            processAnimationDirection();
            
            world.getPlayer().setDirection(270);
            break;

        case RIGHT:
            rPressed.set(true);
            
            processAnimationDirection();

            world.getPlayer().setDirection(0);
            break;

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
            uPressed.set(false);

            processAnimationDirection();

            break;

        case LEFT:
            lPressed.set(false);

            processAnimationDirection();

            break;

        case DOWN:
            dPressed.set(false);

            processAnimationDirection();

            break;

        case RIGHT:
            rPressed.set(false);

            processAnimationDirection();

            break;

        case Z:
            handleInteract();
            break;

        case X:
            spawnEnemies();
            break;

        case SPACE:
            world.getPlayer().attack(world.getPlayer().getDamage());
            handleAttackGraphic();
            break;

        case S:
            try {
                
                Serialization.save(World.instance().getEntityList());

            } catch (IOException e) {

                System.out.println(e.getMessage());
            
            }
            break;

        case L:
            try {

                Serialization.load();

            } catch (IOException e) {

                System.out.println(e.getMessage());

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

    @FXML
    public void pause() {
        ImageView imgviewBD = new ImageView(imgBackgroundDim);
        apaneMain.getChildren().add(imgviewBD);
    }

    @FXML
    public void unpause() {

    }

    /**
     * Displays the attack animation depending on the player's direction.
     */
    @FXML
    public void handleAttackGraphic() {
        int direction = world.getPlayer().getDirection();
        ImageView imgviewAttack = new ImageView(imgattackAnim);
        PauseTransition attackPause = new PauseTransition(Duration.seconds(0.28));
        attackPause.setOnFinished(e -> imgviewAttack.setVisible(false));

        switch (direction) {
        case 270:
            imgviewAttack.setX(world.getPlayer().getX());
            imgviewAttack.setY(world.getPlayer().getY() + 90);
            apaneMain.getChildren().add(imgviewAttack);
            attackPause.play();
            break;

        case 180:
            imgviewAttack.setX(world.getPlayer().getX() - 50);
            imgviewAttack.setY(world.getPlayer().getY());
            apaneMain.getChildren().add(imgviewAttack);
            attackPause.play();
            break;

        case 90:
            imgviewAttack.setX(world.getPlayer().getX());
            imgviewAttack.setY(world.getPlayer().getY() - 90);
            apaneMain.getChildren().add(imgviewAttack);
            attackPause.play();
            break;

        case 0:
            imgviewAttack.setX(world.getPlayer().getX() + 90);
            imgviewAttack.setY(world.getPlayer().getY());
            apaneMain.getChildren().add(imgviewAttack);
            attackPause.play();
            break;

        default:
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
}
