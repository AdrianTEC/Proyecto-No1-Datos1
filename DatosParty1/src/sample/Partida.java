package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class   Partida extends Application {
    // there is the class atributes and encapsulation levels
    public int numeroDeRondas = 1;
    public int cantidadDeJugadores = 2;

    @Override// there is overwriting  this  use  handle method from other class
    public void start(Stage primaryStage) throws Exception {

        /*This function is in charge of window building and window managing, next you will find some boring graphics configurations
         * this is the principal menu's window
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */
        Image btn = new Image("Imagenes/Boton.png");

        ///////////////////////////////////////////////////
        Label CampoNumeroJugadores = new Label();
        CampoNumeroJugadores.setText("Cantidad de jugadores : " + String.valueOf(cantidadDeJugadores));
        CampoNumeroJugadores.setLayoutX(350);
        CampoNumeroJugadores.setLayoutY(270);
        CampoNumeroJugadores.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        CampoNumeroJugadores.setStyle("-fx-background-color: #c9c3c3;");
        ///////////////////////////////////////////////////
        Button UnoJugadorMas = new Button("", new ImageView(btn));
        UnoJugadorMas.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        UnoJugadorMas.setLayoutX(420);
        UnoJugadorMas.setLayoutY(270);
        UnoJugadorMas.setScaleX(0.5);
        UnoJugadorMas.setScaleY(0.5);
        UnoJugadorMas.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        UnoJugadorMas.setText("1+");
        UnoJugadorMas.setContentDisplay(ContentDisplay.CENTER);
        UnoJugadorMas.setOnAction(new EventHandler<ActionEvent>() {
            @Override//there is overwriting handle function is imported from another class

            public void handle(ActionEvent event) {
                encojerBoton(UnoJugadorMas);

                if (cantidadDeJugadores < 4) {
                    cantidadDeJugadores += 1;
                    CampoNumeroJugadores.setText("Cantidad de jugadores : " + String.valueOf(cantidadDeJugadores));
                }
            }
        });
        ///////////////////////////////////////////////////
        Button UnJugadorMenos = new Button("", new ImageView(btn));
        UnJugadorMenos.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        UnJugadorMenos.setLayoutX(300);
        UnJugadorMenos.setLayoutY(270);
        UnJugadorMenos.setScaleX(0.5);
        UnJugadorMenos.setScaleY(0.5);
        UnJugadorMenos.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        UnJugadorMenos.setText("1-");
        UnJugadorMenos.setContentDisplay(ContentDisplay.CENTER);
        UnJugadorMenos.setOnAction(new EventHandler<ActionEvent>() {
            @Override //there is overwriting handle function is imported from another class
            public void handle(ActionEvent event) {
                encojerBoton(UnJugadorMenos);

                if (cantidadDeJugadores > 2) {
                    cantidadDeJugadores -= 1;
                    CampoNumeroJugadores.setText("Cantidad de jugadores : " + String.valueOf(cantidadDeJugadores));
                }
            }
        });
        ///////////////////////////////////////////////////
        Label campoNumeroDeRondas = new Label();
        campoNumeroDeRondas.setText("Cantidad de rondas : " + String.valueOf(numeroDeRondas));
        campoNumeroDeRondas.setLayoutX(350);
        campoNumeroDeRondas.setLayoutY(400);
        campoNumeroDeRondas.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        campoNumeroDeRondas.setStyle("-fx-background-color: #c9c3c3;");
        ///////////////////////////////////////////////////
        Button UnoMas = new Button("", new ImageView(btn));
        UnoMas.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        UnoMas.setLayoutX(420);
        UnoMas.setLayoutY(400);
        UnoMas.setScaleX(0.5);
        UnoMas.setScaleY(0.5);
        UnoMas.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        UnoMas.setText("1+");
        UnoMas.setContentDisplay(ContentDisplay.CENTER);
        UnoMas.setOnAction(new EventHandler<ActionEvent>() {
            @Override //there is overwriting handle function is imported from another class
            public void handle(ActionEvent event) {
                encojerBoton(UnoMas);

                numeroDeRondas += 1;
                campoNumeroDeRondas.setText("Cantidad de rondas : " + String.valueOf(numeroDeRondas));

            }
        });
        ///////////////////////////////////////////////////
        Button UnoMenos = new Button("", new ImageView(btn));
        UnoMenos.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        UnoMenos.setLayoutX(300);
        UnoMenos.setLayoutY(400);
        UnoMenos.setScaleX(0.5);
        UnoMenos.setScaleY(0.5);
        UnoMenos.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        UnoMenos.setText("1-");
        UnoMenos.setContentDisplay(ContentDisplay.CENTER);
        UnoMenos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    encojerBoton(UnoMenos);



                if (numeroDeRondas > 1) {
                    numeroDeRondas -= 1;
                    campoNumeroDeRondas.setText("Cantidad de rondas : " + String.valueOf(numeroDeRondas));
                }
            }
        });

        ///////////////////////////////////////////////////
        Label advertencia = new Label();
        advertencia.setText("Ingrese una cantidad de"+"\n"+ "jugadores y rondas para empezar");
        advertencia.setLayoutX(350);
        advertencia.setLayoutY(570);
        advertencia.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        advertencia.setStyle("-fx-background-color: #c9c3c3;");

        ///////////////////////////////////////////////////
        Button Empezar = new Button("", new ImageView(btn));
        Empezar.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        //POSICION
        Empezar.setLayoutX(300);
        Empezar.setLayoutY(450);
        //POSICION
        Empezar.setScaleX(0.5);
        Empezar.setScaleY(0.5);
        Empezar.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Empezar.setText("NuevaPartida");
        Empezar.setContentDisplay(ContentDisplay.CENTER);
        Empezar.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {
                encojerBoton(Empezar);

                /*This funtion is in charge of opening the game window
                 *@author Adrián González Jiménez
                 *@Version 02/05/2020
                 * @param Event
                 */

                if (numeroDeRondas !=0 && cantidadDeJugadores!=0) {
                    try {
                        crearTablero();
                        primaryStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });





        Image Portada = new Image("Imagenes/Portada.png");
        ImageView portada = new ImageView(Portada);
        portada.setFitHeight(675);
        portada.setFitWidth(1000);


        Pane root = new Pane();
        root.getChildren().addAll(portada,Empezar,advertencia, UnoJugadorMas, UnJugadorMenos, CampoNumeroJugadores, campoNumeroDeRondas, UnoMas, UnoMenos);
        primaryStage.setTitle("Datos Party 1");
        primaryStage.setScene(new Scene(root, 1000, 675));
        primaryStage.show();


    }
    public  static void reproducirSonido(String nombre) {
        /*This funtion play different types of sounds
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
        String nombreSonido="";
        if(nombre=="boton") {
            nombreSonido="DatosParty1/src/Media/Button.wav";

        }
        if(nombre=="paso") {
            nombreSonido="DatosParty1/src/Media/Step.wav";


        }
        if(nombre=="dado")
        {
            nombreSonido="DatosParty1/src/Media/Dado.wav";

        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println(ex);
        }
    }




    public static void  encojerBoton (Button boton){
        /*This funtion animate buttons when they are pressed
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
        reproducirSonido("boton");
        boton.setScaleX(0.4);
        boton.setScaleY(0.4);
        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        boton.setScaleX(0.5);
                        boton.setScaleY(0.5);
                    }
                },
                100



        );

    }
    public  void crearTablero() throws Exception {
        /*This funtion is instantiate a Tablero Object
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
        Tablero mytable= new Tablero();
        Stage abs= new Stage();
        mytable.setNumeroDeJugadores(cantidadDeJugadores);
        mytable.setCantidadDeTurnos(numeroDeRondas);

        mytable.start(abs);

        }

    public static void main(String[] args) {
        launch(args);
    }
}
    //Comentario de seguridad