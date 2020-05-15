package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Tablero extends Application {
    // there is the class atributes and encapsulation levels  (private and public)
    public ListaCircular caminoPrincipal;
   // private fases
   // public dado dados
   // public int jugadores
   // private int cuantosHanMovido

   public  int numerodeRondas;

    /*This funtion is in charge of controlling the game boards
     *@author Adrián González Jiménez
     *@Version 02/05/2020
     * @param Stage
     */
    @Override// there is overwriting  this  use  handle method from other class;
    public void start(Stage primaryStage) throws Exception {
        /*This funtion is in charge of window building and creates the way list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */


        Camino FaseInicial= new Camino();
        FaseInicial.matrizPosiciones= new float[][]{{470, 230}, {470, 273}, {470, 316}, {470, 359}, {470, 402}, {470, 445}, {470, 488}, {427, 531}, {375, 531}
                , {333, 531}, {291, 531}, {246, 531,}, {204, 531}, {162, 531}, {110, 531}, {70, 494}, {70, 445}, {70, 402}, {70, 359}, {70, 316}, {70, 273}, {70, 230}};

        //Convierto el camino a una listaCircular
        FaseInicial.convertirMatrizAListaCircular();

        caminoPrincipal= FaseInicial.casillas;   //extraigo la lista circular




        //IMAGEN TABLERO
        Image btn = new Image("Imagenes/Boton.png");
        Image TableroImagen = new Image("Imagenes/Tablero.png");
        ImageView tableroImagen = new ImageView(TableroImagen);

        //IMAGEN JUGADORES
        Image J1=new Image("Imagenes/1.png");
        Image J2=new Image("Imagenes/2.png");
        Image J3=new Image("Imagenes/3.png");
        Image J4=new Image("Imagenes/4.png");

        ///SE CREA EL JUGADOR ACÁ //////////////////////////////////////////////////////////////////////////////////

        Jugador p1= new Jugador();
        p1.imagen= new ImageView(J1);
        //Acá agarro la primera casilla de la lista circular
        p1.moverseA(caminoPrincipal.primero);




//          ______________________________________
//_________/    MoveBUTTON
        Button Move = new Button("", new ImageView(btn));

        Move.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        //POSICION
        Move.setLayoutX(490);
        Move.setLayoutY(500);
        //POSICION
        Move.setScaleX(0.5);
        Move.setScaleY(0.5);
        Move.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Move.setText("¡Mover!");
        Move.setContentDisplay(ContentDisplay.CENTER);
        Move.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                p1.moverseA(p1.ubicacionEnElMapa.siguiente);

            }
        });


        //AQUI SE AGREGAN LOS COMPONENTES
        Pane root = new Pane();
        root.getChildren().addAll(tableroImagen,p1.imagen,Move);


        primaryStage.setResizable(false);
        primaryStage.setTitle("Datos Party 1");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
            {


                @Override // there is overwriting  this  use  handle method from import javafx.scene.input.KeyEvent;
                public void handle(KeyEvent evt)
                    {
                        /*This funtion is in charge of catching key press
                         *@author Adrián González Jiménez
                         *@Version 02/05/2020
                         * @param KeyEvent
                         */


                        if (evt.getCode().equals(KeyCode.ESCAPE))
                            {

                            }
                    }
            });
        primaryStage.show();




    }







}
