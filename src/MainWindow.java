import java.io.IOException;
import java.util.ArrayList;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.HighScore;
import model.Serialization;

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
    private Image imgHelpBtn1 = new Image("Final Assets/UI/PNG/UI-HelpBtn1-312x80.png");
    private Image imgHelpBtn2 = new Image("Final Assets/UI/PNG/UI-HelpBtn2-312x80.png");
    private Image imgBackBtn1 = new Image("Final Assets/UI/PNG/UI-BackBtn1-312x80.png");
    private Image imgBackBtn2 = new Image("Final Assets/UI/PNG/UI-BackBtn2-312x80.png");
    private Image imgEasyBtn1 = new Image("Final Assets/UI/PNG/UI-EasyBtn1-312x80.png");
    private Image imgEasyBtn2 = new Image("Final Assets/UI/PNG/UI-EasyBtn2-312x80.png");
    private Image imgMediumBtn1 = new Image("Final Assets/UI/PNG/UI-MediumBtn1-312x80.png");
    private Image imgMediumBtn2 = new Image("Final Assets/UI/PNG/UI-MediumBtn2-312x80.png");
    private Image imgHardBtn1 = new Image("Final Assets/UI/PNG/UI-HardBtn1-312x80.png");
    private Image imgHardBtn2 = new Image("Final Assets/UI/PNG/UI-HardBtn2-312x80.png");
    private Image imgSubmitBtn1 = new Image("Final Assets/UI/PNG/UI-SubmitBtn1-312x80.png");
    private Image imgSubmitBtn2 = new Image("Final Assets/UI/PNG/UI-SubmitBtn2-312x80.png");
    // *********************************************************************************

    // UI ImageViews **********************************************************************
    private ImageView imgviewStart = new ImageView(imgStart1);
    private ImageView imgviewAbout = new ImageView(imgAbout1);
    private ImageView imgviewHighscore = new ImageView(imgHighscore1);
    private ImageView imgviewBackgroundDim = new ImageView(imgBackgroundDim);
    private ImageView imgviewHelpBtn = new ImageView(imgHelpBtn1);
    private ImageView imgviewBackAboutBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewBackHsBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewBackHelpBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewEasyBtn = new ImageView(imgEasyBtn1);
    private ImageView imgviewMediumBtn = new ImageView(imgMediumBtn1);
    private ImageView imgviewHardBtn = new ImageView(imgHardBtn1);
    private ImageView imgviewSubmitBtn = new ImageView(imgSubmitBtn1);
    private ImageView imgviewDifBackBtn = new ImageView(imgBackBtn1);
    private ImageView imgviewNameSubmitBtn = new ImageView(imgSubmitBtn1);
    // ************************************************************************************

    // UI VBoxes *********************
    private VBox startVbox = new VBox();
    private VBox aboutVbox = new VBox();
    private VBox hsVbox = new VBox();
    private VBox helpVbox = new VBox();
    private VBox difVbox = new VBox();
    private VBox nameVbox = new VBox();
    // *****************************

    DifficultyLevel difficulty;
    String name;
        
    /**
     * Intializes the start screen with music in the background and the font style used for the application.
     * @param stage
     */
    void initialize(Stage stage) {
        // Music
        AudioClip music = new AudioClip(getClass().getResource("Audio/Retro_Forest_-_David_Fesliyan.mp3").toExternalForm());
        music.setVolume(0.5);
        music.setCycleCount(AudioClip.INDEFINITE);
        music.play();

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
        createDifficultyVbox();
        createNameVbox();

        // Display startVbox
        spaneMain.getChildren().add(startVbox);
    }


    /**
     * The build of the start screen.
     */
    public void createStartVbox() {

        //onMousePressed/Released-----------------------------------------------------------------------------
        imgviewStart.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewStart.setImage(imgStart2);
            }
        });
        imgviewStart.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewStart.setImage(imgStart1);
                
            }
        });

        imgviewAbout.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewAbout.setImage(imgAbout2);
                // Thread thread = new Thread(() -> click.play());
                // thread.start();
            }
        });
        imgviewAbout.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewAbout.setImage(imgAbout1);
                
            }
        });

        imgviewHighscore.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHighscore.setImage(imgHighscore2);
            }
        });
        imgviewHighscore.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHighscore.setImage(imgHighscore1);
            }
        });

        imgviewHelpBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBtn.setImage(imgHelpBtn2);
            }
        });
        imgviewHelpBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewHelpBtn.setImage(imgHelpBtn1);
            }
        });
        //-----------------------------------------------------------------------------------------------

        // Button Functions
        // *************************************************************************************
        imgviewStart.setOnMouseClicked(event -> {
            spaneMain.getChildren().remove(startVbox);
            spaneMain.getChildren().add(imgviewBackgroundDim);
            spaneMain.getChildren().add(nameVbox);
        });

        imgviewAbout.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(imgviewBackgroundDim);
                spaneMain.getChildren().add(aboutVbox);
            }
        });

        imgviewHighscore.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(imgviewBackgroundDim);
                spaneMain.getChildren().add(hsVbox);
            }
        });
        
        imgviewHelpBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(startVbox);
                spaneMain.getChildren().add(imgviewBackgroundDim);
                spaneMain.getChildren().add(helpVbox);
            }
        });
        // *************************************************************************************

        imgviewLogo.setFitHeight(512);
        imgviewLogo.setFitWidth(1024);
        imgviewStart.setFitHeight(80);
        imgviewStart.setFitWidth(312);
        imgviewAbout.setFitHeight(80);
        imgviewAbout.setFitWidth(312);
        imgviewHelpBtn.setFitHeight(80);
        imgviewHelpBtn.setFitWidth(312);


        startVbox.getChildren().add(imgviewLogo);
        startVbox.getChildren().add(imgviewStart);
        startVbox.getChildren().add(imgviewAbout);
        startVbox.getChildren().add(imgviewHighscore);
        startVbox.getChildren().add(imgviewHelpBtn);

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
                spaneMain.getChildren().remove(imgviewBackgroundDim);
            }
        });


        aboutVbox.setAlignment(Pos.TOP_CENTER);
        aboutVbox.setSpacing(10.0);

        Label lbl = new Label("""
        Game Developers:
        Joshua Douglas
        Andrew Fox
        David Goff

        Music:
        'Retro Forest' by David Fesliyan

        Coin SFX by Zapsplatt.com

        Art Design:
        David Goff

        Serialization:
        Joshua Douglas

        Gameplay:
        Andrew Fox
        """);


        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 28px; -fx-text-fill: #ffffff;");
        lbl.setTextAlignment(TextAlignment.CENTER);
        Label titleLbl = new Label("ABOUT");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");
        aboutVbox.getChildren().add(titleLbl);
        aboutVbox.getChildren().add(lbl);
        aboutVbox.getChildren().add(imgviewBackAboutBtn);
    }

    /**
     * The build of the "Highscore" screen.
     */
    public void createHsVbox() {

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
                spaneMain.getChildren().remove(imgviewBackgroundDim);
            }
        });

        hsVbox.setAlignment(Pos.TOP_CENTER);
        hsVbox.setSpacing(10.0);

        String highscoresStr = "";
        ArrayList<HighScore> highscoresList;
        try {
            highscoresList = Serialization.loadScores("HIGHSCORES.txt");
            for (int i = 0; i < highscoresList.size(); ++i) {
                highscoresStr = highscoresStr +highscoresList.get(i).getPlayerName() + "   " + highscoresList.get(i).getScore() + " \n";
            }
        } catch (IOException e1) {
            highscoresStr = "No HighScores";
        }
    
        Label lbl = new Label(highscoresStr);
        lbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        Label titleLbl = new Label("HIGHSCORES");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");

        hsVbox.getChildren().add(titleLbl);
        hsVbox.getChildren().add(lbl);
        hsVbox.getChildren().add(imgviewBackHsBtn);
        hsVbox.setTranslateY(100);
    }

    /**
     * The build of the "Help" screen.
     */
    public void createHelpVbox() {

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
        imgviewBackHelpBtn.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                spaneMain.getChildren().remove(helpVbox);
                spaneMain.getChildren().add(startVbox);
                spaneMain.getChildren().remove(imgviewBackgroundDim);
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

        Label titleLbl = new Label("CONTROLS");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");

        helpVbox.getChildren().add(titleLbl);
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
     * The build of the "Set Difficulty" Screen
     */
    @FXML
    public void createDifficultyVbox() {
        Label titleLbl = new Label("SELECT DIFFICULTY");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 48px; -fx-text-fill: #ffffff;");

        //onMousePressed************************************************************************************* 
        imgviewEasyBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewEasyBtn.setImage(imgEasyBtn2);
                imgviewMediumBtn.setImage(imgMediumBtn1);
                imgviewHardBtn.setImage(imgHardBtn1);

                difficulty = DifficultyLevel.EASY;
            }
        });
        imgviewMediumBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewEasyBtn.setImage(imgEasyBtn1);
                imgviewMediumBtn.setImage(imgMediumBtn2);
                imgviewHardBtn.setImage(imgHardBtn1);

                difficulty = DifficultyLevel.MEDIUM;
            }
        });
        imgviewHardBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewEasyBtn.setImage(imgEasyBtn1);
                imgviewMediumBtn.setImage(imgMediumBtn1);
                imgviewHardBtn.setImage(imgHardBtn2);

                difficulty = DifficultyLevel.HARD;
            }
        });


        imgviewSubmitBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (difficulty != null) {
                    imgviewSubmitBtn.setImage(imgSubmitBtn2);    
                }
            }
        });
        imgviewSubmitBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (difficulty != null) {
                    imgviewSubmitBtn.setImage(imgSubmitBtn1);    
                }
            }
        });
        imgviewDifBackBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewDifBackBtn.setImage(imgBackBtn2);
            }
        });
        imgviewDifBackBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewDifBackBtn.setImage(imgBackBtn1);
            }
        });
        //************************************************************************************************************ 

        imgviewSubmitBtn.setOnMouseClicked(event -> {
            if (difficulty != null) {
                try {
                    onStartClicked(event);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                spaneMain.getChildren().remove(imgviewBackgroundDim);
                spaneMain.getChildren().remove(difVbox);
                spaneMain.getChildren().add(startVbox);
            }
        });

        imgviewDifBackBtn.setOnMouseClicked(event -> {
            spaneMain.getChildren().remove(imgviewBackgroundDim);
            spaneMain.getChildren().remove(difVbox);
            spaneMain.getChildren().add(startVbox);
        });
        
        difVbox.getChildren().add(titleLbl);
        difVbox.setAlignment(Pos.CENTER);
        difVbox.getChildren().add(imgviewEasyBtn);
        difVbox.getChildren().add(imgviewMediumBtn);
        difVbox.getChildren().add(imgviewHardBtn);
        difVbox.getChildren().add(new Label("\n"));
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10.0);
        hbox.getChildren().add(imgviewSubmitBtn);
        hbox.getChildren().add(imgviewDifBackBtn);
        difVbox.getChildren().add(hbox);
        difVbox.setSpacing(10.0);
        difVbox.setLayoutY(100);

        imgviewBackgroundDim.setFitHeight(900);
        imgviewBackgroundDim.setFitWidth(1440);
    }

    @FXML
    public void createNameVbox() {
        Label titleLbl = new Label("Enter Gamertag: ");
        titleLbl.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #ffffff;");

        TextField txtField = new TextField();
        txtField.setStyle("-fx-font-family: Minecraft; -fx-font-size: 32px; -fx-text-fill: #000000;");
        txtField.setMaxWidth(250);

        //onMousePressed*************************************************************************************
        imgviewNameSubmitBtn.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewNameSubmitBtn.setImage(imgSubmitBtn2);
            }
        });
        imgviewNameSubmitBtn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imgviewNameSubmitBtn.setImage(imgSubmitBtn1);
            }
        });
        //************************************************************************************************************ 

        imgviewNameSubmitBtn.setOnMouseClicked(event -> {
            spaneMain.getChildren().remove(nameVbox);
            spaneMain.getChildren().add(difVbox);
            name = txtField.getText();
        });

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10.0);
        hbox.getChildren().add(titleLbl);
        hbox.getChildren().add(txtField);

        nameVbox.getChildren().add(hbox);
        nameVbox.getChildren().add(imgviewNameSubmitBtn);
        nameVbox.setLayoutY(400);
        nameVbox.setAlignment(Pos.CENTER);
        nameVbox.setSpacing(10.0);

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
        controller.initialize(stage, difficulty, name);
    }

}