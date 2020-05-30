package sample;
import Listas.CasillaDoble;
import Listas.CasillaSimple;
import Listas.ListaCircular;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
   public  ImageView dadoActual;

    public int numeroDeJugadores;
   public int cantidadDeTurnos;
   public  int rondasJugadas= 1;
   public int turnodeJugador = 1;

    //IMAGEN LADOS DEL DADO
    Image D1=new Image("Imagenes/Dados-1.png.png");
    Image D2=new Image("Imagenes/Dados-2.png.png");
    Image D3=new Image("Imagenes/Dados-3.png.png");
    Image D4=new Image("Imagenes/Dados-4.png.png");
    Image D5=new Image("Imagenes/Dados-5.png.png");
    Image D6=new Image("Imagenes/Dados-6.png.png");
    //IMAGEN JUGADORES
    Image J1=new Image("Imagenes/1.png");
    Image J2=new Image("Imagenes/2.png");
    Image J3=new Image("Imagenes/3.png");
    Image J4=new Image("Imagenes/4.png");
    Pane root = new Pane();



    /*This funtion is in charge of controlling the game boards
     *@author Adrián González Jiménez
     *@Version 02/05/2020
     * @param Stage
     */
    private void moverPersonaje(Jugador px,int numDado,int puntero)
        {
            Partida.reproducirSonido("paso");
            if( puntero <numDado) {

                new java.util.Timer().schedule(

                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                if (px.ubicacionEnElMapa instanceof CasillaSimple) {
                                    px.moverseA((CasillaSimple) ((CasillaSimple) px.ubicacionEnElMapa).siguiente);


                                }
                                if (px.ubicacionEnElMapa instanceof CasillaDoble) {
                                    px.moverseA((CasillaSimple) ((CasillaDoble) px.ubicacionEnElMapa).siguiente);
                                }
                                moverPersonaje(px,numDado,puntero+1);

                            }
                        },
                        500
                );
            }


        }
    public void lanzarDados(Jugador px)
        { /*This funtion throw the dices and ask players to move
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Jugador
         */

            Dado.tirar();
            if (Dado.getNumero() == 1) {
                dadoActual=new ImageView(D1); }
            if (Dado.getNumero() == 2) {
                dadoActual=new ImageView(D2); }
            if (Dado.getNumero() == 3) {
                dadoActual=new ImageView(D3); }
            if (Dado.getNumero() == 4) {
                dadoActual=new ImageView(D4); }
            if (Dado.getNumero() == 5) {
                dadoActual=new ImageView(D5); }
            if (Dado.getNumero() == 6) {
                dadoActual=new ImageView(D6); }

            dadoActual.setFitHeight(100);
            dadoActual.setFitWidth(100);
            dadoActual.setLayoutX(120);
            dadoActual.setLayoutY(600);
            root.getChildren().add(dadoActual);
            //Aquí debemos realizar un doble casteo, se realizó una actualización para poder generalizar a las casillas
            //Estas en vez de poderse comunicar entre casillas del mismo tipo lo podrán hacer con otras que no lo sean
            //Entonces ellas tendran de atributo de tipo OBJETO entonces hacemos doble casteo primero por "UBICACION EN EL MAPA"
            //            //Luego lo hacemos por "SIGUIENTE"

            moverPersonaje(px,Dado.getNumero(),0);

        }
    @Override// there is overwriting  this  use  handle method from other class;
    public void start(Stage primaryStage) throws Exception {
        /*This funtion is in charge of window building and creates the way list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */
        //CREO EL PANE  EN EL AGREGARE COSAS COMO BOTONES IMAGENES O LABELES





        //CREO UN OBJETO LLAMADO FASE INICIAL QUE ES IGUAL A UN NUEVO CAMINO
            //LE ASIGNO UNA MATRIZ DE POSICIONES QUE CORRESPONDE A LAS UBICACIONES DE TODOS LOS LUGARES DONDE SE PODRAN MOVER
            Camino FaseInicial= new Camino();
            FaseInicial.matrizPosiciones= new float[][]{{470, 230}, {470, 273}, {470, 316}, {470, 359}, {470, 402}, {470, 445}, {470, 488}, {427, 531}, {375, 531}
                                        , {333, 531}, {291, 531}, {246, 531,}, {204, 531}, {162, 531}, {110, 531}, {70, 494}, {70, 445}, {70, 402}, {70, 359}, {70, 316}, {70, 273}, {70, 230}};


            //Convierto el camino a una listaCircular lo cual me permitirá seguir primero las indicaciones del proyecto y
            //segundo recorrer el camino
            FaseInicial.convertirMatrizAListaCircular();

            caminoPrincipal= FaseInicial.casillas;   //extraigo la lista circular
        //CREO LA FASE C

            Camino FaseC= new Camino();
            FaseC.dobleEnlaze=true; //ESTA LISTA VA A SER DOBLE ENLAZADA
            FaseC.matrizPosiciones= new float[][]{{123,123},{453,231},{234,343}};



        //IMAGEN TABLERO
        Image btn = new Image("Imagenes/Boton.png");
        Image TableroImagen = new Image("Imagenes/Tablero.png");
        ImageView tableroImagen = new ImageView(TableroImagen);
        /////////////////////////////////////////////////////////////////////////
        //Aqui se muestra la cantidad de rondas que han pasado y la cantidad de rondas que se deben jugar
        Label ronda = new Label();
        ronda.setText(rondasJugadas + "/" + cantidadDeTurnos);
        ronda.setLayoutX(605);
        ronda.setLayoutY(130);
        ronda.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ronda.setStyle("-fx-background-color: rgba(243,236,250,0.63);");


        ///SE CREAN LOS JUGADORES ACÁ //////////////////////////////////////////////////////////////////////////////////
        Jugador p1 = new Jugador();
        p1.imagen = new ImageView(J1);
        Jugador p2 = new Jugador();
        p2.imagen = new ImageView(J2);
        Jugador p3 = new Jugador();
        p3.imagen = new ImageView(J3);
        Jugador p4 = new Jugador();
        p4.imagen = new ImageView(J4);

        p1.moverseA((CasillaSimple) caminoPrincipal.primero);
        p2.moverseA((CasillaSimple)caminoPrincipal.primero);
        p3.moverseA((CasillaSimple)caminoPrincipal.primero);
        p4.moverseA((CasillaSimple)caminoPrincipal.primero);



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

                //Como los jugadores pueden estar en dos tipos de casillas diferentes
                //y como es obligatorio enviarle El tipo de casilla al cual se va  a mover
                // hay que considerar que "ubicacionEnElMapa" es de tipo objeto
                // por lo tanto hay que saber que
                // tipo de objeto es en el que el jugador está (tipo casilla simple o doble)
                Partida.encojerBoton(Move);
                Jugador px= new Jugador();
                if (turnodeJugador==1) {
                    lanzarDados(p1);
                  }
                if (turnodeJugador==2) {
                    lanzarDados(p2);

                }
                if (turnodeJugador==3) {
                    lanzarDados(p3);

                }
                if (turnodeJugador==4) {
                    lanzarDados(p4);

                }






            }
        });
        // Controlar de quien es turno
        Button Turno = new Button ("", new ImageView(btn));
        Turno.setStyle("-fx-background-color:transparent;");
        //POSICION
        Turno.setLayoutX(495);
        Turno.setLayoutY(420);
        //POSICION
        Turno.setScaleX(0.5);
        Turno.setScaleY(0.5);


        Label victoria = new Label();

        Turno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (turnodeJugador < numeroDeJugadores+1) {
                    turnodeJugador += 1;
                    ronda.setText(rondasJugadas + "/" + cantidadDeTurnos);
                }

                if (turnodeJugador == numeroDeJugadores+1) {
                    if (rondasJugadas<cantidadDeTurnos) {

                            turnodeJugador = 1;
                            rondasJugadas += 1;
                            ronda.setText(rondasJugadas + "/" + cantidadDeTurnos);

                    }
                    else{
                        victoria.setText("Victory Royale");
                        victoria.setLayoutX(100);
                        victoria.setLayoutY(130);
                        victoria.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                        victoria.setStyle("-fx-background-color: rgb(255,255,255);");
                    }
                }

            }
        });


        //AQUI SE AGREGAN LOS COMPONENTES

        root.getChildren().addAll(tableroImagen, Move, Turno, ronda,victoria);


        if(numeroDeJugadores>=2) {
            //Acá agarro la primera casilla de la lista circular

            root.getChildren().add(p1.imagen);
            root.getChildren().add(p2.imagen);


        }
        if(numeroDeJugadores>=3) {
            root.getChildren().add(p3.imagen);

        }
        if(numeroDeJugadores==4) {
            root.getChildren().add(p4.imagen);

        }




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
