package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;


public class HighestCard extends Application {
    private Pane root;
    private Jugador px1;
    private Jugador px2;
    private Label instruccion;
    private Label puntaje1;
    private Label puntaje2;
    private float [][] posiciones;
    private int auxiliar;
    private int numero1;
    private int numero2;
    private Stage v;

    public HighestCard (){
        px1 = new Jugador();
        px2 = new Jugador();
        root = new Pane();
        instruccion = new Label();
        puntaje1 = new Label();
        puntaje2 = new Label();
        auxiliar = 2;
        v=null;


    }
    public void setPxs(Jugador px1,Jugador px2) {
        this.px1 = px1;
        this.px2=px2;
    }
    public void definirGanador(){
        /*This choose a winner
         *@author  Andrés Quirós
         *@Version 1/05/2020
         * @param
         *
         */
        if (numero1 > numero2){
            instruccion.setText("¡Jugador 1 ha ganado!");
            px1.setMonedas(px1.getMonedas() +2);
        }
        else if (numero1 == numero2){
            instruccion.setText("¡Empate!");
            px1.setMonedas(px1.getMonedas() +1);
            px2.setMonedas(px2.getMonedas() +1);
        }
        else{
            instruccion.setText("¡Jugador 2 ha ganado!");
            px2.setMonedas(px2.getMonedas() +2);
        }

    }


    public  void crearCartas(){
        /*This create cards and place it in the root
         *@author  Andrés Quirós
         *@Version 1/05/2020
         * @param
         *
         */
        for (float []i:posiciones) {

            CartaJueg cartaJueg = new CartaJueg();
            cartaJueg.getImagen().setLayoutX(i[0]+500);
            cartaJueg.getImagen().setLayoutY(i[1]-100);
            cartaJueg.getImagen().setScaleX(2);
            cartaJueg.getImagen().setScaleY(2);

            cartaJueg.setTipo((int) (Math.random()*11+1));

            cartaJueg.getImagen().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Partida.reproducirSonido("win");
                if (auxiliar ==1){
                    root.getChildren().remove(cartaJueg.getImagen());
                    numero2 = cartaJueg.getTipo();

                    puntaje2.setText("Carta del Jugador 2:     " + numero2);
                    auxiliar -= 1;

                }
                if (auxiliar == 2){
                    root.getChildren().remove(cartaJueg.getImagen());
                    numero1 = cartaJueg.getTipo();

                    puntaje1.setText( "Carta del Jugador 1:    " + numero1);
                    auxiliar -= 1;
                    instruccion.setText("Jugador 2 Escoge una carta");
                }

                else{
                    definirGanador();
                    new java.util.Timer().schedule(

                            new java.util.TimerTask() {
                                @Override
                                public void run() {

                                    Platform.runLater(() -> v.close());

                                }
                            },
                            2000
                    );
                }

            });
            root.getChildren().add(cartaJueg.getImagen());
        }
    }

    @Override
    public void start(Stage stage) {
        v=stage;
        posiciones = new float[][]{    {50, 200}, {150, 200}, {250, 200}, {350, 200}, {450, 200,},
                {50, 400}, {150, 400}, {250, 400}, {350, 400 }, {450, 400}};

        ImageView fondo= new ImageView("Imagenes/Minijuegos/TABLE.png");
        fondo.setLayoutY(-50);
        fondo.setLayoutX(-100);

        fondo.setScaleX(fondo.getScaleX()/1.1);
        fondo.setScaleY(fondo.getScaleY()/1.1);

        instruccion.setText("Jugador 1 Escoge una carta");
        instruccion.setLayoutX(100);
        instruccion.setLayoutY(30);
        instruccion.setPrefSize(1000,50);
        instruccion.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        instruccion.setStyle("-fx-background-color: rgba(243,236,250,0.63);");


        puntaje1.setLayoutX(30);
        puntaje1.setLayoutY(600);
        puntaje1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        puntaje1.setStyle("-fx-background-color: rgba(243,236,250,0.63);");

        puntaje2.setLayoutX(350);
        puntaje2.setLayoutY(600);
        puntaje2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        puntaje2.setStyle("-fx-background-color: rgba(243,236,250,0.63);");

        root.setStyle("-fx-background-color: #16C24A");
        stage.setResizable(false);
        stage.setTitle("HighestCard");
        stage.setScene(new Scene(root, 1300, 700));
        root.getChildren().addAll(fondo,instruccion,puntaje1,puntaje2);
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(1));


        crearCartas();
    }
}
