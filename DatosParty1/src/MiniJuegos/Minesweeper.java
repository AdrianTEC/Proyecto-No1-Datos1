package MiniJuegos;
import Listas.CasillaSimple;
import Listas.ListaCircular;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Camino;
import sample.Jugador;

public class Minesweeper extends Application implements Observador {
    private Pane root = new Pane();
    private Jugador px;
    private Label puntaje= new Label();
    private Label cantidadPuntos= new Label();
    private int puntos;
    private float [][] posiciones;


    public  void crearMinas(){
        for (float []i:posiciones) {


            Mina mina = new Mina();
            mina.getImagen().setLayoutX(i[0]);
            mina.getImagen().setLayoutY(i[1]);
            if (Math.random()*10 < 3){
                mina.setTipo("mina");
            }

            mina.getImagen().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (mina.getTipo().equals("mina")) {
                    System.out.println("sirve");
                    mina.explotar();
                    puntos -=15;
                    cantidadPuntos.setText(String.valueOf(puntos));

                }
                else {
                    root.getChildren().remove(mina.getImagen());
                    puntos+=10;
                    cantidadPuntos.setText(String.valueOf(puntos));
                }
            });
            root.getChildren().add(mina.getImagen());
        }
    }





    @Override
    public void start(Stage stage) throws Exception {
        puntos = 0;




        posiciones = new float[][]{    {50, 50}, {150, 50}, {250, 50}, {350, 50}, {450, 50,},
                {550, 50}, {50, 150}, {150, 150}, {250, 150 }, {350, 150},
                {450, 150} , {550, 150 }, {50, 250 }, {150, 250 }, {250, 250},{350, 250},
                {450, 250}, {550, 250}, {50, 350}, {150, 350}, {250, 350},{350, 350},
                {450, 350}, {550, 350}, {50, 450}, {150, 450}, {250, 450}, {350, 450},
                {450, 450}, {550, 450} ,{50, 550}, {150, 550}, {250, 550}, {350, 550}, {450, 550}, {550, 550}   };

        //Convierto el camino a una listaCircular lo cual me permitirá seguir primero las indicaciones del proyecto y
        //segundo recorrer el camino

        BarraTiempo barrita = new BarraTiempo();
        barrita.getBarra().setLayoutX(150);
        barrita.getBarra().setLayoutY(750);
        barrita.setTamano(400,30);
        barrita.setTiempo(20000);
        barrita.encoger();
        barrita.generarContenedor();
        barrita.setObservador(Minesweeper.this);

        puntaje.setText("Puntaje:");
        puntaje.setLayoutX(600);
        puntaje.setLayoutY(30);
        puntaje.setPrefSize(200,50);
        puntaje.setStyle("-fx-background-color: #535c94");
        puntaje.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        cantidadPuntos.setText(String.valueOf(puntos));
        cantidadPuntos.setLayoutX(730);
        cantidadPuntos.setLayoutY(30);
        cantidadPuntos.setPrefSize(200,50);
        cantidadPuntos.setFont(Font.font("Verdana", FontWeight.BOLD, 20));



        root.setStyle("-fx-background-color: #202f4a");
        stage.setResizable(false);
        stage.setTitle("Minesweeper");
        stage.setScene(new Scene(root, 800, 800));
        root.getChildren().addAll(barrita.getBarra(),puntaje,cantidadPuntos,barrita.getBarraContainer());
        stage.show();
        stage.setOnCloseRequest(event -> { System.exit(1);});

        crearMinas();








    }

    @Override
    public void Update() {

    }
}
