import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainWindow  {
    @FXML StackPane spaneMain;
    private AudioClip click = new AudioClip(getClass().getResource("Audio/UI/btnClick1.mp3").toExternalForm());

    /**
     * Intializes the start screen with music in the background and the font style used for the application.
     * @param stage
     */
    void initialize(Stage stage) {
        // Music
        AudioClip music = new AudioClip(getClass().getResource("Audio/Retro_Forest_-_David_Fesliyan.mp3").toExternalForm());
        music.setVolume(0.5);
        //music.play();

        // Font
        Font.loadFont(getClass().getResourceAsStream("/Final Assets/UI/Minecraft.ttf"), 64);

        // Start Screen
        startScreen();
    }


    /**
     * The build of the start screen.
     */
    public void startScreen() {
        clearVBox();

        Image imgAboutScreen = new Image("Final Assets/UI/PNG/UI-StartScreen-Background-1440x900.png");
        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);
        spaneMain.setBackground(bGround);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);

        Image logo = new Image("Final Assets/Logo/Final Logo.png");
        Image start = new Image("Final Assets/UI/PNG/UI-StartBtn1-312x80.png");
        Image about = new Image("Final Assets/UI/PNG/UI-AboutBtn1-312x80.png");
        Image highscore = new Image("Final Assets/UI/PNG/UI-HighscoreBtn1-312x80.png");
        Image help = new Image("Final Assets/UI/PNG/UI-HelpBtn1-312x80.png");

        ImageView Logo = new ImageView(logo);
        ImageView Startbtn = new ImageView(start);
        ImageView Aboutbtn = new ImageView(about);
        ImageView HSbtn = new ImageView(highscore);
        ImageView Helpbtn = new ImageView(help);

    
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
                Thread thread = new Thread(() -> click.play());
                thread.start();
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

        Helpbtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Helpbtn.setImage(new Image("Final Assets/UI/PNG/UI-HelpBtn2-312x80.png"));
            }
        });
        Helpbtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Helpbtn.setImage(new Image("Final Assets/UI/PNG/UI-HelpBtn1-312x80.png"));
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

        HSbtn.setOnMouseClicked(hsEvent -> hsScreen(hsEvent));
        
        Helpbtn.setOnMouseClicked(helpEvent -> helpScreen(helpEvent));


        Logo.setFitHeight(512);
        Logo.setFitWidth(1024);
        Startbtn.setFitHeight(80);
        Startbtn.setFitWidth(312);
        Aboutbtn.setFitHeight(80);
        Aboutbtn.setFitWidth(312);
        HSbtn.setFitHeight(80);
        HSbtn.setFitWidth(312);


        vbox.getChildren().add(Logo);
        vbox.getChildren().add(Startbtn);
        vbox.getChildren().add(Aboutbtn);
        vbox.getChildren().add(HSbtn);
        vbox.getChildren().add(Helpbtn);

        vbox.setTranslateY(-75);

        spaneMain.getChildren().add(vbox);
    }


    /**
     * The build of the "About" screen.
     */
    public void aboutScreen(MouseEvent e) {
        clearVBox();
        Image back = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
        ImageView backBtn = new ImageView(back);


        //Mouse Pressed/Released------------------------------------------------------------------------------
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

        backBtn.setFitHeight(80);
        backBtn.setFitWidth(312);
        backBtn.setOnMouseClicked(backEvent -> callStartScreen(backEvent));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);
        vbox.getChildren().add(backBtn);
        vbox.setTranslateY(350);

        VBox v = new VBox();
        v.setAlignment(Pos.TOP_CENTER);
        v.setSpacing(10.0);

        Label lbl = new Label("""
        Game Developers:
        Joshua Douglas
        Andrew Fox
        David Goff

        Music:
        'Retro Forest' by David Fesliyan

        Art Design:
        David Goff

        Serialization:
        Joshua Douglas

        Gameplay:
        Andrew Fox
        """);

        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        lbl.setTextAlignment(TextAlignment.CENTER);
        v.getChildren().add(lbl);


        spaneMain.getChildren().add(v);
        spaneMain.getChildren().add(vbox);
    }

    /**
     * The build of the "Highscore" screen.
     */
    public void hsScreen(MouseEvent e) {
        clearVBox();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);
        vbox.setTranslateY(350);

        Image back = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
        ImageView backBtn = new ImageView(back);

        //Mouse Pressed/Released------------------------------------------------------------------------------
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

        backBtn.setFitHeight(80);
        backBtn.setFitWidth(312);
        backBtn.setOnMouseClicked(backEvent -> callStartScreen(backEvent));

        VBox v = new VBox();
        v.setAlignment(Pos.TOP_CENTER);
        v.setSpacing(10.0);

        // TODO: Label will get a string of text from High Score or Leaderboard class
        Label lbl = new Label("TestName   TestScore \nTestName   TestScore \nTestName   TestScore \nTestName   TestScore \nTestName   TestScore \n");
        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        v.getChildren().add(lbl);
        vbox.getChildren().add(backBtn);

        spaneMain.getChildren().add(v);
        spaneMain.getChildren().add(vbox);
    }

    /**
     * The build of the "Help" screen.
     */
    public void helpScreen(MouseEvent e) {
        clearVBox();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);
        vbox.setTranslateY(350);

        Image back = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
        ImageView backBtn = new ImageView(back);

        //Mouse Pressed/Released------------------------------------------------------------------------------
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

        backBtn.setFitHeight(80);
        backBtn.setFitWidth(312);
        backBtn.setOnMouseClicked(backEvent -> callStartScreen(backEvent));

        vbox.getChildren().add(backBtn);
        spaneMain.getChildren().add(vbox);
    }

    /**
     * Calls the start screen with a MouseEvent.
     * @param e <- MouseEvent
     */
    public void callStartScreen(MouseEvent e) {
        startScreen();
    }

    /**
     * Clears the vboxes that populates the screen.
     */
    public void clearVBox() {
        int size = spaneMain.getChildren().size();
        for (int i = 0; i < size; ++i) {
            spaneMain.getChildren().remove(0);
        }
    }

    /**
     * Starts the game.
     * @param e <- Mouse Event
     * @throws IOException
     */
    public void onStartClicked(MouseEvent e) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        var scene = new Scene(loader.load());
        var stage = new Stage();

        // TODO add difficulty selection after start is pressed

        GameWindow controller = loader.getController();

        stage.setScene(scene);
        stage.show();
        controller.initialize(stage);
    }

}