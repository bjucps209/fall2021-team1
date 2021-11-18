import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
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
import model.Living;
import model.NPC;
import model.World;

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
    private Image imgPlayerBack, imgPlayerLeft, imgPlayerFront, imgPlayerRight, imgPlayerBackMove, imgPlayerLeftMove,
            imgPlayerRightMove, imgPlayerFrontMove; // needs organizing
    @FXML
    private Label lblScore, lblLocation;

    @FXML
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
    private Image imgattackAnim = new Image("Final Assets/Player/GIF/Player-Attack-128x128.gif");

    private Image imgNPC = new Image("Final Assets/NPC/PNG/NPC-Front-Stationary-128x128.png");

    private Image imgGrunt = new Image("Final Assets/Grunt/PNG/Grunt-Front-Stationary-128x128.png");


    // Model Attributes
    private World world;

    @FXML
    void initialize(Stage stage) {

        // Images *****************************
        Image imgAboutScreen = new Image("Final Assets/World/PNG/World-AboutArea-1440x900.png");
        imgPlayerBack = new Image("Final Assets/Player/PNG/Player-Back-Stationary-128x128.png");
        imgPlayerFront = new Image("Final Assets/Player/PNG/Player-Front-Stationary-128x128.png");
        imgPlayerLeft = new Image("Final Assets/Player/PNG/Player-Left-Stationary-128x128.png");
        imgPlayerRight = new Image("Final Assets/Player/PNG/Player-Right-Stationary-128x128.png");

        imgPlayerBackMove = new Image("Final Assets/Player/GIF/Player-Back-Walking-128x128.gif");
        imgPlayerLeftMove = new Image("Final Assets/Player/GIF/Player-Left-Walking-128x128.gif");
        imgPlayerRightMove = new Image("Final Assets/Player/GIF/Player-Right-Walking-128x128.gif");
        imgPlayerFrontMove = new Image("Final Assets/Player/GIF/Player-Front-Walking-128x128.gif");
        // ***********************************

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
        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, BackgroundRepeat.NO_REPEAT,
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
        // TEMPORARY ***
        world.setScore(0);
        // *************
        lblScore.setText(String.valueOf(world.getScore()));
        lblScore.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        AnchorPane.setBottomAnchor(lblScore, 10.0);
        AnchorPane.setLeftAnchor(lblScore, 10.0);
        apaneMain.getChildren().add(lblScore);

        // Generate location label
        lblLocation = new Label();
        // TEMPORARY *****
        world.setCurrentlocation("Start Area");
        // ****************
        lblLocation.setText(world.getCurrentlocation());
        lblLocation.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
        AnchorPane.setTopAnchor(lblLocation, 10.0);
        AnchorPane.setLeftAnchor(lblLocation, 10.0);
        apaneMain.getChildren().add(lblLocation);


        // Adding an NPC to the starter area
        world.getEntityList().add(new NPC("Welcome to Terrene!\nPress 'x' to spawn an enemy."));

        for (Entity entity : world.getEntityList()) {

            if (entity instanceof NPC) {

                ImageView imgviewNPC = new ImageView(imgNPC);
                imgviewNPC.xProperty().bindBidirectional(entity.xProperty());
                imgviewNPC.yProperty().bindBidirectional(entity.yProperty());
                apaneMain.getChildren().add(imgviewNPC);

            }

        }


    }

    /**
     * Calls the Player's interact method, if an entity is within range of the player displays the entities message as a Label.
     */
    @FXML
    public void handleInteract() {

        Entity interactedEntity = world.getPlayer().interact();

        if (interactedEntity == null) { return; }

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
                // displayText(interactedEntity.getX() - 53, interactedEntity.getY() - 30, item.getMessage());

                // World.instance().increaseScore(item.getScoreIncrease());
        
            default:

                return;

        }

    }

    
    /**
     * Spawns enemies in the current player's location, barebones implementation for alpha.
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

            }
        }
    }


    // Animation timer specifically for the player, does not effect the other entities movement.
    AnimationTimer playerMovement = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {

            double speed = world.getPlayer().getSpeed();

            if (uPressed.get()) {

                world.getPlayer().setY(world.getPlayer().getY() - speed);
                imgviewPlayer.setY(world.getPlayer().getY());

            }

            if (dPressed.get()) {

                world.getPlayer().setY(world.getPlayer().getY() + speed);
                imgviewPlayer.setY(world.getPlayer().getY());

            }

            if (lPressed.get()) {

                world.getPlayer().setX(world.getPlayer().getX() - speed);
                imgviewPlayer.setX(world.getPlayer().getX());

            }

            if (rPressed.get()) {

                world.getPlayer().setX(world.getPlayer().getX() + speed);
                imgviewPlayer.setX(world.getPlayer().getX());

            }

        }

    };

    /**
     * Handles various different cases when the player presses a key.
     * @param event - the key currently held down.
     */
    public void keyPressed(KeyEvent event) {

        switch (event.getCode()) {

        case UP:
            uPressed.set(true);
            imgviewPlayer.setImage(imgPlayerBackMove);
            world.getPlayer().setDirection(90);
            break;

        case LEFT:
            lPressed.set(true);
            imgviewPlayer.setImage(imgPlayerLeftMove);
            world.getPlayer().setDirection(180);
            break;

        case DOWN:
            dPressed.set(true);
            imgviewPlayer.setImage(imgPlayerFrontMove);
            world.getPlayer().setDirection(270);
            break;

        case RIGHT:
            rPressed.set(true);
            imgviewPlayer.setImage(imgPlayerRightMove);
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
     * @param event - the key the player released.
     */
    public void keyReleased(KeyEvent event) {

        switch (event.getCode()) {

        case UP:
            uPressed.set(false);
            imgviewPlayer.setImage(imgPlayerBack);
            break;

        case LEFT:
            lPressed.set(false);
            imgviewPlayer.setImage(imgPlayerLeft);
            break;

        case DOWN:
            dPressed.set(false);
            imgviewPlayer.setImage(imgPlayerFront);
            break;

        case RIGHT:
            rPressed.set(false);
            imgviewPlayer.setImage(imgPlayerRight);
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

        default:
            break;

        }

    }

    /**
     * Displays the attack animation depending on where the player is standing.
     */
    @FXML
    public void handleAttackGraphic() {
        int direction = world.getPlayer().getDirection();
        ImageView imgviewAttack = new ImageView(imgattackAnim);
        PauseTransition attackPause = new PauseTransition(Duration.seconds(0.3));
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

    /**
     * Display a text message on the screen at the given coordinates.
     * @param x the x coordinate of the message
     * @param y the y coordinate of the message
     * @param message the message to display
     */
    // private void displayText(double x, double y, String message) {

    //     Label lblMessage = new Label();
    //     lblMessage.setText(message);
    //     lblMessage.setStyle("-fx-font-family: Minecraft; -fx-font-size: 24px; -fx-text-fill: #ffffff;");
    //     lblMessage.setLayoutX(x);
    //     lblMessage.setLayoutY(y);
     
    //     apaneMain.getChildren().add(lblMessage);
     
    //     PauseTransition labelPause = new PauseTransition(Duration.seconds(3));
    //     labelPause.setOnFinished(e -> lblMessage.setVisible(false));
    //     labelPause.play();

    // }

    /**
     * Generates the Game Screen that the user sees and interacts with.
     */
    @FXML
    void generateGamescreen() {

    }

}
