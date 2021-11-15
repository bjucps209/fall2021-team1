import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.PopupWindow.AnchorLocation;

public class MainWindow  {
    @FXML AnchorPane apaneMain;

    void initialize(Stage stage) {
        AudioClip music = new AudioClip(getClass().getResource("Audio/Retro_Forest_-_David_Fesliyan.mp3").toExternalForm());
        music.play();

        startScreen();
    }


//TODO: FIX SIZE OF SCREEN
    public void startScreen() {
        clearVBox();

        Image imgAboutScreen = new Image("Final Assets/UI/PNG/UI-StartScreen-Background-1440x900.png");
        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);
        apaneMain.setBackground(bGround);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);

        Image logo = new Image("Final Assets/Logo/Final Logo.png");
        Image start = new Image("Final Assets/UI/PNG/UI-StartBtn1-312x80.png");
        Image about = new Image("Final Assets/UI/PNG/UI-AboutBtn1-312x80.png");
        Image highscore = new Image("Final Assets/UI/PNG/UI-HighscoreBtn1-312x80.png");

        ImageView Logo = new ImageView(logo);
        ImageView Startbtn = new ImageView(start);
        ImageView Aboutbtn = new ImageView(about);
        ImageView HSbtn = new ImageView(highscore);

    
        //onMousePressed/Released-----------------------------------------------------------------------------
        Startbtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Startbtn.setImage(new Image("Final Assets/UI/PNG/UI-StartBtn2-312x80.png"));
            }
        });
        Startbtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Startbtn.setImage(new Image("Final Assets/UI/PNG/UI-StartBtn1-312x80.png"));
            }
        });

        Aboutbtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Aboutbtn.setImage(new Image("Final Assets/UI/PNG/UI-AboutBtn2-312x80.png"));
            }
        });
        Aboutbtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Aboutbtn.setImage(new Image("Final Assets/UI/PNG/UI-AboutBtn1-312x80.png"));
            }
        });

        HSbtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                HSbtn.setImage(new Image("Final Assets/UI/PNG/UI-HighscoreBtn2-312x80.png"));
            }
        });
        HSbtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                HSbtn.setImage(new Image("Final Assets/UI/PNG/UI-HighscoreBtn1-312x80.png"));
            }
        });
        //-----------------------------------------------------------------------------------------------


        Startbtn.setOnMouseClicked(event -> {
            try {
                onStartClicked(event);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Aboutbtn.setOnMouseClicked(aboutEvent -> aboutScreen(aboutEvent));

        Startbtn.setFitHeight(40); //TODO: FIX SIZE 
        Startbtn.setFitWidth(156);
        Aboutbtn.setFitHeight(40);
        Aboutbtn.setFitWidth(156);
        HSbtn.setFitHeight(40);
        HSbtn.setFitWidth(156);

        vbox.getChildren().add(Logo);
        vbox.getChildren().add(Startbtn);
        vbox.getChildren().add(Aboutbtn);
        vbox.getChildren().add(HSbtn);
        
        vbox.setLayoutX(100);

        apaneMain.getChildren().add(vbox);
    }

    //Mouse Pressed/Released------------------------------------------------------------------------------
    public void aboutScreen(MouseEvent e) {
        clearVBox();

        Image back = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
        ImageView backBtn = new ImageView(back);

        backBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                backBtn.setImage(new Image("Final Assets/UI/PNG/UI-BackBtn2-312x80.png"));
            }
        });
        backBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                backBtn.setImage(new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png"));
            }
        });
        //-------------------------------------------------------------------------------------------------

        backBtn.setFitHeight(40);
        backBtn.setFitWidth(156);
        backBtn.setOnMouseClicked(backEvent -> callStartScreen(backEvent));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);
        vbox.getChildren().add(backBtn);
        vbox.setLayoutX(270);

        apaneMain.getChildren().add(vbox);
    }

    public void callStartScreen(MouseEvent e) {
        startScreen();
    }

    public void clearVBox() {
        try {
            apaneMain.getChildren().remove(0);
        } catch (IndexOutOfBoundsException e) {

        }

    }

    public void onStartClicked(MouseEvent e) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        var scene = new Scene(loader.load());
        var stage = new Stage();
        GameWindow controller = loader.getController();

        stage.setScene(scene);
        stage.show();
        controller.initialize(stage);

    }

}






//Custom font code?-----------------------------------------------------
// Font font = Font.loadFont("Final Assets/UI/Pixel Font.ttf", 45.0);
// Label lbl = new Label();
// lbl.setText("Hello");
// lbl.setFont(font);
// vbox.getChildren().add(lbl);
//----------------------------------------------------------------------