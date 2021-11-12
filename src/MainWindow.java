import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.World;

public class MainWindow {

    @FXML
    HBox hboxMainBox;
    @FXML
    Pane paneGameScreen;
    @FXML
    ImageView imgviewPlayer;
    @FXML
    Image imgPlayer;

    World world;

    @FXML
    void initialize() {
        paneGameScreen = new Pane();
        paneGameScreen.setPrefHeight(900);
        paneGameScreen.setPrefWidth(1440);

        Image imgAboutScreen = new Image("Final Assets/World/PNG/World-AboutArea-1440x900.png");
        imgPlayer = new Image("Final Assets/Player/PNG/Player-Back-Stationary-128x128.png");
        imgviewPlayer = new ImageView(imgPlayer);

        imgviewPlayer.setX(650);
        imgviewPlayer.setY(750);
        paneGameScreen.getChildren().add(imgviewPlayer);
        hboxMainBox.getChildren().add(paneGameScreen);

        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        hboxMainBox.setBackground(bGround);


    }

    /**
     * Generates the Game Screen that the user sees and interacts with.
     */
    @FXML
    void generateGamescreen() {

    }

}
