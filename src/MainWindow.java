import java.io.IOException;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainWindow  {
    @FXML StackPane spaneMain;
    private AudioClip click = new AudioClip(getClass().getResource("Audio/UI/btnClick1.mp3").toExternalForm());
    private ImageView imgviewLogo = new ImageView ( new Image("Final Assets/Logo/Final Logo.png"));

    // UI Images **********************************************************************
    private Image imgStart1 = new Image("Final Assets/UI/PNG/UI-StartBtn1-312x80.png");
    private Image imgStart2 = new Image("Final Assets/UI/PNG/UI-StartBtn2-312x80.png");
    private Image imgAbout1 = new Image("Final Assets/UI/PNG/UI-AboutBtn1-312x80.png");
    private Image imgAbout2 = new Image("Final Assets/UI/PNG/UI-AboutBtn2-312x80.png");
    private Image imgHighscore1 = new Image("Final Assets/UI/PNG/UI-HighscoreBtn1-312x80.png");
    private Image imgHighscore2 = new Image("Final Assets/UI/PNG/UI-HighscoreBtn2-312x80.png");
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
    // *********************************************************************************

    // UI ImageViews **********************************************************************
    private ImageView imgviewStart1 = new ImageView(imgStart1);
    private ImageView imgviewStart2 = new ImageView(imgStart2);
    private ImageView imgviewAbout1 = new ImageView(imgAbout1);
    private ImageView imgviewAbout2 = new ImageView(imgAbout2);
    private ImageView imgviewHighscore1 = new ImageView(imgHighscore1);
    private ImageView imgviewHighscore2 = new ImageView(imgHighscore2);
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
    private ImageView imgviewBackAboutBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewBackHsBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewBackHelpBtn = new ImageView(imgBackBtn1);
    // ************************************************************************************

    // UI VBoxes *********************
    private VBox startVbox = new VBox();
    private VBox aboutVbox = new VBox();
    private VBox hsVbox = new VBox();
    private VBox helpVbox = new VBox();
    // *****************************

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

        Image imgAboutScreen = new Image("Final Assets/UI/PNG/UI-StartScreen-Background-1440x900.png");
        BackgroundImage bImg = new BackgroundImage(imgAboutScreen, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);
        spaneMain.setBackground(bGround);

        // Build Vboxes
        createStartVbox();
        createAboutVbox();
        createHsVbox();
        createHelpVbox();

        // Display startVbox
        spaneMain.getChildren().add(startVbox);
    }


    /**
     * The build of the start screen.
     */
    public void createStartVbox() {

        //onMousePressed/Released-----------------------------------------------------------------------------
        imgviewStart1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewStart1.setImage(imgStart2);
            }
        });
        imgviewStart1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewStart1.setImage(imgStart1);
                
            }
        });

        imgviewAbout1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewAbout1.setImage(imgAbout2);
                // Thread thread = new Thread(() -> click.play());
                // thread.start();
            }
        });
        imgviewAbout1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewAbout1.setImage(imgAbout1);
                
            }
        });

        imgviewHighscore1.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHighscore1.setImage(imgHighscore2);
            }
        });
        imgviewHighscore1.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHighscore1.setImage(imgHighscore1);
            }
        });

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
        //-----------------------------------------------------------------------------------------------

        // Button Functions
        // *************************************************************************************
        imgviewStart1.setOnMouseClicked(event -> {
            try {
                onStartClicked(event);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        imgviewAbout1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(aboutVbox);
            }
        });

        imgviewHighscore1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(hsVbox);
            }
        });
        
        imgviewHelpBtn1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(helpVbox);
            }
        });
        // *************************************************************************************

        imgviewLogo.setFitHeight(512);
        imgviewLogo.setFitWidth(1024);
        imgviewStart1.setFitHeight(80);
        imgviewStart1.setFitWidth(312);
        imgviewAbout1.setFitHeight(80);
        imgviewAbout1.setFitWidth(312);
        imgviewHelpBtn1.setFitHeight(80);
        imgviewHelpBtn1.setFitWidth(312);


        startVbox.getChildren().add(imgviewLogo);
        startVbox.getChildren().add(imgviewStart1);
        startVbox.getChildren().add(imgviewAbout1);
        startVbox.getChildren().add(imgviewHighscore1);
        startVbox.getChildren().add(imgviewHelpBtn1);

        startVbox.setTranslateY(-75);
        startVbox.setAlignment(Pos.CENTER);
        startVbox.setSpacing(10.0);
    }


    /**
     * The build of the "About" screen.
     */
    public void createAboutVbox() {

        //Mouse Pressed/Released------------------------------------------------------------------------------
        imgviewBackAboutBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackAboutBtn.setImage(imgBackBtn2);
            }
        });
        imgviewBackAboutBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackAboutBtn.setImage(imgBackBtn1);
            }
        });
        //-------------------------------------------------------------------------------------------------

        imgviewBackAboutBtn.setFitHeight(80);
        imgviewBackAboutBtn.setFitWidth(312);
        imgviewBackAboutBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(aboutVbox);
                spaneMain.getChildren().add(startVbox);
            }
        });

        
        // aboutVbox.setAlignment(Pos.CENTER);
        // aboutVbox.setSpacing(10.0);
        // aboutVbox.getChildren().add(backBtn);
        // aboutVbox.setTranslateY(350);

        aboutVbox.setAlignment(Pos.TOP_CENTER);
        aboutVbox.setSpacing(10.0);

        Label lbl = new Label("""
        Game Developers:
        Joshua Douglass
        Andrew Fox
        David Goff

        Music:
        'Retro Forest' by David Fesliyan

        Art Design:
        David Goff
        """);

        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        lbl.setTextAlignment(TextAlignment.CENTER);
        aboutVbox.getChildren().add(lbl);
        aboutVbox.getChildren().add(imgviewBackAboutBtn);
    }

    /**
     * The build of the "Highscore" screen.
     */
    public void createHsVbox() {
        // hsVbox.setAlignment(Pos.CENTER);
        // hsVbox.setSpacing(10.0);
        // hsVbox.setTranslateY(350);

        //Mouse Pressed/Released------------------------------------------------------------------------------
        imgviewBackHsBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackHsBtn.setImage(imgBackBtn2);
            }
        });
        imgviewBackHsBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackHsBtn.setImage(imgBackBtn1);
                
            }
        });
        //-------------------------------------------------------------------------------------------------

        imgviewBackHsBtn.setFitHeight(80);
        imgviewBackHsBtn.setFitWidth(312);
        imgviewBackHsBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(hsVbox);
                spaneMain.getChildren().add(startVbox);
            }
        });

        hsVbox.setAlignment(Pos.TOP_CENTER);
        hsVbox.setSpacing(10.0);

        // TODO: Label will get a string of text from High Score or Leaderboard class
        Label lbl = new Label("TestName   TestScore \nTestName   TestScore \nTestName   TestScore \nTestName   TestScore \nTestName   TestScore \n");
        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        hsVbox.getChildren().add(lbl);
        hsVbox.getChildren().add(imgviewBackHsBtn);
    }

    /**
     * The build of the "Help" screen.
     */
    public void createHelpVbox() {
        // vbox.setAlignment(Pos.CENTER);
        // vbox.setSpacing(10.0);
        // vbox.setTranslateY(350);

        //Mouse Pressed/Released------------------------------------------------------------------------------
        imgviewBackHelpBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackHelpBtn.setImage(imgBackBtn2);
            }
        });
        imgviewBackHelpBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewBackHelpBtn.setImage(imgBackBtn1);
            }
        });
        //-------------------------------------------------------------------------------------------------

        imgviewBackHelpBtn.setFitHeight(80);
        imgviewBackHelpBtn.setFitWidth(312);
        imgviewBackHelpBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(helpVbox);
                spaneMain.getChildren().add(startVbox);
            }
        });

        ImageView move = new ImageView(new Image("Final Assets/Help/PNG/Help-Move-156x88.png"));
        ImageView attack = new ImageView(new Image("Final Assets/Help/PNG/Help-Attack-156x88.png"));
        ImageView interact = new ImageView(new Image("Final Assets/Help/PNG/Help-Interact-156x88.png"));
        ImageView travel = new ImageView(new Image("Final Assets/Help/PNG/Help-Travel-156x88.png"));
        ImageView pause = new ImageView(new Image("Final Assets/Help/PNG/Help-Pause-156x88.png"));

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

        Label movelbl = new Label();
        Label attacklbl = new Label();
        Label interactlbl = new Label();
        Label travellbl = new Label();
        Label pauselbl = new Label();

        movelbl.setText("Move: ");
        attacklbl.setText("Attack: ");
        interactlbl.setText("Interact: ");
        travellbl.setText("Travel: ");
        pauselbl.setText("Pause: ");

        movelbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        attacklbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        interactlbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        travellbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");
        pauselbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        HBox moveHbox = new HBox();
        HBox attackHbox = new HBox();
        HBox interactHbox = new HBox();
        HBox travelHbox = new HBox();
        HBox pauseHbox = new HBox();

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

        moveHbox.setAlignment(Pos.CENTER);
        attackHbox.setAlignment(Pos.CENTER);
        interactHbox.setAlignment(Pos.CENTER);
        travelHbox.setAlignment(Pos.CENTER);
        pauseHbox.setAlignment(Pos.CENTER);

        helpVbox.getChildren().add(moveHbox);
        helpVbox.getChildren().add(attackHbox);
        helpVbox.getChildren().add(interactHbox);
        helpVbox.getChildren().add(travelHbox);
        helpVbox.getChildren().add(pauseHbox);
        helpVbox.getChildren().add(imgviewBackHelpBtn);

        helpVbox.setAlignment(Pos.CENTER);
        helpVbox.setSpacing(10.0);
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
        GameWindow controller = loader.getController();

        stage.setScene(scene);
        stage.show();
        controller.initialize(stage);
    }

}