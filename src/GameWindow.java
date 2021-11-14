import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import model.World;

public class GameWindow  {


    // Entire game is displayed through AnchorPane, gives more flexibility than
    // an HBox or a VBox. Allows for an adjustable screen as well through
    // screen anchors.


    // Control Attributes

    //https://gist.github.com/Da9el00/5f698b3839f00ab3e0f28118edd6c947
    private BooleanProperty uPressed = new SimpleBooleanProperty();
    private BooleanProperty lPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty rPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = uPressed.or(lPressed).or(dPressed).or(rPressed);
    
    // FXML Attributes
    @FXML
    private AnchorPane apaneMain;
    @FXML
    private ImageView imgviewPlayer;
    @FXML
    private Image imgPlayerBack, imgPlayerLeft, imgPlayerFront, imgPlayerRight, imgPlayerBackMove,
                  imgPlayerLeftMove, imgPlayerRightMove, imgPlayerFrontMove;

    // Model Attributes
    private World world;

    // placeholder
    private int speed = 3;

    @FXML
    void initialize(Stage stage) {  

        keyPressed.addListener((ObservableValue, booleanVal, timer) -> {
            if (!booleanVal) {
                playerMovement.start();
            } else {
                playerMovement.stop();
            }
        });

        //Images *****************************
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
        
        imgviewPlayer = new ImageView(imgPlayerBack);

        imgviewPlayer.setX(650);
        imgviewPlayer.setY(750);
        apaneMain.getChildren().add(imgviewPlayer);



        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        apaneMain.setBackground(bGround);
        

        
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


    }


    AnimationTimer playerMovement = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (uPressed.get()) {
                imgviewPlayer.setLayoutY(imgviewPlayer.getLayoutY() - speed);
            }

            if (dPressed.get()) {
                imgviewPlayer.setLayoutY(imgviewPlayer.getLayoutY() + speed);
            }

            if (lPressed.get()) {
                imgviewPlayer.setLayoutX(imgviewPlayer.getLayoutX() - speed);
            }

            if (rPressed.get()) {
                imgviewPlayer.setLayoutX(imgviewPlayer.getLayoutX() + speed);
            }
        }
    };


    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {

            case UP:
                uPressed.set(true);
                imgviewPlayer.setImage(imgPlayerBackMove);
                break;
            case LEFT:
                lPressed.set(true);
                imgviewPlayer.setImage(imgPlayerLeftMove);
                break;
            case DOWN:
                dPressed.set(true);
                imgviewPlayer.setImage(imgPlayerFrontMove);
                break;
            case RIGHT:
                rPressed.set(true);
                imgviewPlayer.setImage(imgPlayerRightMove);
                break;
        }
    }

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
        }
        
    }


    /**
     * Generates the Game Screen that the user sees and interacts with.
     */
    @FXML
    void generateGamescreen() {

    }



}
