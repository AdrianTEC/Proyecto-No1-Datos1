package MiniJuegos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;


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

    public HighestCard (){
        px1 = new Jugador();
        px2 = new Jugador();
        root = new Pane();
        instruccion = new Label();
        puntaje1 = new Label();
        puntaje2 = new Label();
        auxiliar = 2;


    }

    public void definirGanador(){
        if (numero1 > numero2){
            instruccion.setText("¡Jugador 1 ha ganado!");
            px1.setMonedas(px1.getMonedas() +2);
        }
        if (numero1 == numero2){
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
        for (float []i:posiciones) {

            Carta carta = new Carta();
            carta.getImagen().setLayoutX(i[0]);
            carta.getImagen().setLayoutY(i[1]);

            carta.setTipo((int) (Math.random()*10));

            carta.getImagen().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (auxiliar ==1){
                    root.getChildren().remove(carta.getImagen());
                    puntaje2.setText("Carta del Jugador 2:     " + carta.getTipo());
                    numero2 = carta.getTipo();
                    auxiliar -= 1;

                }
                if (auxiliar == 2){
                    root.getChildren().remove(carta.getImagen());
                    puntaje1.setText( "Carta del Jugador 1:    " + carta.getTipo());
                    numero1 = carta.getTipo();
                    auxiliar -= 1;
                    instruccion.setText("Jugador 2 Escoge una carta");
                }

                else{
                    definirGanador();
                }

            });
            root.getChildren().add(carta.getImagen());
        }
    }

    @Override
    public void start(Stage stage) {

        posiciones = new float[][]{    {50, 200}, {150, 200}, {250, 200}, {350, 200}, {450, 200,},
                {50, 400}, {150, 400}, {250, 400}, {350, 400 }, {450, 400}};


        instruccion.setText("Jugador 1 Escoge una carta");
        instruccion.setLayoutX(100);
        instruccion.setLayoutY(30);
        instruccion.setPrefSize(1000,50);
        instruccion.setFont(Font.font("Verdana", FontWeight.BOLD, 40));



        puntaje1.setLayoutX(30);
        puntaje1.setLayoutY(600);
        //puntaje1.setPrefSize(1000,50);
        puntaje1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


        puntaje2.setLayoutX(350);
        puntaje2.setLayoutY(600);
        //puntaje2.setPrefSize(1000,50);
        puntaje2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


        root.setStyle("-fx-background-color: #16C24A");
        stage.setResizable(false);
        stage.setTitle("HighestCard");
        stage.setScene(new Scene(root, 800, 700));
        root.getChildren().addAll(instruccion,puntaje1,puntaje2);
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(1));


        crearCartas();
    }
}
