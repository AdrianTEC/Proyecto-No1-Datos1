package sample;
import Listas.CasillaDoble;
import Listas.CasillaSimple;
import Listas.ListaCircular;
import Listas.ListaLineal;
import javafx.application.Application;
import javafx.application.Platform;
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

import java.awt.*;

public class Tablero extends Application {
    // there is the class atributes and encapsulation levels  (private and public)
    private ListaCircular caminoPrincipal;
    private ListaLineal caminoC;
    private ListaLineal caminoA;
    private ListaLineal caminoB;
    private EventManager eventManager;

    // private fases

   private int numeroDeJugadores;
   private int cantidadDeTurnos;
   private int rondasJugadas= 1;
   private int turnodeJugador = 1;
   private int dato;
   private Dado dado1;
   private Dado dado2;
   private boolean compraEstrella;
   private Baraja barajaVerde;
   private Baraja barajaAzul;
   private Baraja barajaRoja;
   public int tipoCasilla = 4;

    public  Tablero()
    {
        numeroDeJugadores=2;
         cantidadDeTurnos=10;
         compraEstrella = false;
    }

    public void setNumeroDeJugadores(int numeroDeJugadores) {
        this.numeroDeJugadores = numeroDeJugadores;
    }

    public int getCantidadDeTurnos() {
        return cantidadDeTurnos;
    }

    public void setCantidadDeTurnos(int cantidadDeTurnos) {
        this.cantidadDeTurnos = cantidadDeTurnos;
    }

    public int getDato(){
        return dato;
    }


    public  boolean DevMOVING =false;


    //IMAGEN JUGADORES
    Image J1=new Image("Imagenes/1.png");
    Image J2=new Image("Imagenes/2.png");
    Image J3=new Image("Imagenes/3.png");
    Image J4=new Image("Imagenes/4.png");
    Pane root = new Pane();

    Image estrella = new Image ("Imagenes/Estrella.png");

    Estrella e = new Estrella();

    public void generar(Estrella es) {
        if(rondasJugadas>=2) {
            es.numeroRandom();
            es.setUbicacionEnElMapa(caminoPrincipal.giveMe(es.getNumero()));
            es.moverseA((CasillaSimple) es.getUbicacionEnElMapa());
            root.getChildren().add(es.getImagen());
        }
    }

    public void actualizar(){
    }

    public void MoverEstrella(){
        e.numeroRandom();
        e.setUbicacionEnElMapa(caminoPrincipal.giveMe(e.getNumero()));
        e.moverseA((CasillaSimple) e.getUbicacionEnElMapa());
    }

    /*This funtion is in charge of controlling the game boards
     *@author Adrián González Jiménez
     *@Version 02/05/2020
     * @param Stage
     */
    private void moverPersonaje(Jugador px,int numDado,int puntero)
        {
            Partida.reproducirSonido("paso");
            if( puntero <numDado) {
                if(px.getUbicacionEnElMapa()==e.getUbicacionEnElMapa())
                    {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                if(px.getMonedas() > 1) {
                                    compraEstrella = true;
                                }


                            }
// ...
                    });
                    }
                new java.util.Timer().schedule(


                        new java.util.TimerTask() {
                            @Override
                            public void run() {

                                if(px.getUbicacionEnElMapa() instanceof  CasillaDoble)
                                    {
                                        if(px.getDirection()) {
                                            px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente());
                                        }
                                        else {
                                            px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getAnterior());

                                        }
                                    }


                                else  {
                                        System.out.println(px.getUbicacionEnElMapa());
                                        if (((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente() instanceof CasillaSimple) {
                                            px.moverseA((CasillaSimple) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente());

                                        }
                                        else {
                                            px.moverseA((CasillaDoble) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente());

                                        }
                                    }
                                    moverPersonaje(px,numDado,puntero+1);




                            }
                        },
                        300
                );
            }


            else {

                String x=((CasillaSimple) px.getUbicacionEnElMapa()).getTipo();
                if (px.getUbicacionEnElMapa() instanceof CasillaDoble) {


                    if (x=="Vi"|| x=="Ri" ||x=="Ai") {
                        System.out.println("se ha cambiado la orientación");
                        px.setDirection(   ((CasillaDoble) px.getUbicacionEnElMapa()).getRight()          );
                        ((CasillaDoble) px.getUbicacionEnElMapa()).changeDirection();
                    }
                }
                    if (x == "V"){
                        tipoCasilla = 1;
                    }
                    if (x == "R"){
                        tipoCasilla = 2;
                    }
                    if (x == "A") {
                        tipoCasilla = 3;
                    }
                }

            }
    public void lanzarDados(Jugador px)
        {
            /*This funtion throw the dices and ask players to move
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Jugador
         */
            if(!DevMOVING) {
            dado1.tirar();
            dado2.tirar();


            dado1.cara.setLayoutX(120);
            dado2.cara.setLayoutX(180);

            root.getChildren().addAll(dado1.cara,dado2.cara);
            //Aquí debemos realizar un doble casteo, se realizó una actualización para poder generalizar a las casillas
            //Estas en vez de poderse comunicar entre casillas del mismo tipo lo podrán hacer con otras que no lo sean
            //Entonces ellas tendran de atributo de tipo OBJETO entonces hacemos doble casteo primero por "UBICACION EN EL MAPA"
            //            //Luego lo hacemos por "SIGUIENTE"

                moverPersonaje(px, dado1.getNumero() + dado2.getNumero(), 0);
            }
            else
                {

                    moverPersonaje(px, 1, 0);

                }
        }
    @Override// there is overwriting  this  use  handle method from other class;
    public void start(Stage primaryStage) throws Exception {
        /*This funtion is in charge of window building and creates the way list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */
        //CREO EL PANE  EN EL AGREGARE COSAS COMO BOTONES IMAGENES O LABELES

        eventManager= new EventManager(); //creo una instancia manejadora de eventos

        e.setImagen(new ImageView(estrella));

        Image cartaVerde=new Image("Imagenes/Cartas/baraja(V).png");
        Image cartaRoja=new Image("Imagenes/Cartas/baraja(R).png");
        Image cartaAzul=new Image("Imagenes/Cartas/baraja(A).png");



        barajaVerde = new Baraja();
        barajaVerde.setTipoBaraja("V");
        barajaVerde.setBaraja(new ImageView(cartaVerde));
        barajaVerde.getBaraja().setFitWidth(90);
        barajaVerde.getBaraja().setFitHeight(90);
        barajaVerde.getBaraja().setLayoutY(610);

        barajaAzul = new Baraja();
        barajaAzul.setTipoBaraja("A");
        barajaAzul.setBaraja(new ImageView(cartaAzul));
        barajaAzul.getBaraja().setFitWidth(90);
        barajaAzul.getBaraja().setFitHeight(90);
        barajaAzul.getBaraja().setLayoutY(610);
        barajaAzul.getBaraja().setLayoutX(720);

        barajaRoja = new Baraja();
        barajaRoja.setTipoBaraja("R");
        barajaRoja.setBaraja(new ImageView(cartaRoja));
        barajaRoja.getBaraja().setFitWidth(90);
        barajaRoja.getBaraja().setFitHeight(90);
        barajaRoja.getBaraja().setLayoutY(610);
        barajaRoja.getBaraja().setLayoutX(830);






        //CREO LOS DOS DADOS
         dado1= new Dado();
         dado2= new Dado();


        //CREO UN OBJETO LLAMADO FASE INICIAL QUE ES IGUAL A UN NUEVO CAMINO
            //LE ASIGNO UNA MATRIZ DE POSICIONES QUE CORRESPONDE A LAS UBICACIONES DE TODOS LOS LUGARES DONDE SE PODRAN MOVER
            Camino FaseInicial= new Camino();
            FaseInicial.matrizPosiciones= new float[][]{    {427, 531}, {375, 531}, {333, 531}, {291, 531}, {246, 531,},
                                                            {204, 531}, {162, 531}, {110, 531}, {70, 494 }, {70, 445},
                                                            {70, 402} , {70, 359 }, {70, 316 }, {70, 273 }, {70, 230},{70, 179},
                                                            {110, 142}, {162, 142}, {204, 142}, {246, 142}, {291, 142},{333, 142},
                                                            {375, 142}, {427, 142}, {470, 185}, {470, 230}, {470, 273}, {470, 316},
                                                            {470, 359}, {470, 402} ,{470, 445}, {470, 488}   };


            //Convierto el camino a una listaCircular lo cual me permitirá seguir primero las indicaciones del proyecto y
            //segundo recorrer el camino

            caminoPrincipal= (ListaCircular) FaseInicial.convertirMatrizALista(new ListaCircular());   //extraigo la lista circular

            caminoPrincipal.aplicarPropiedades(new String[]{"D","V","R","A","R","V","A","R","D","Vi","A","R","A","Vi","R","D","R","A","R","V","R","Vi","A","D","A","V","R","V","A","V","Ai","R"});
            caminoPrincipal.remplazarCasillaSimple(10);
            caminoPrincipal.remplazarCasillaSimple(31);
            caminoPrincipal.remplazarCasillaSimple(14);
            caminoPrincipal.remplazarCasillaSimple(22);

        //CREO LA FASE A
            Camino FaseA= new Camino();
            FaseA.matrizPosiciones= new float[][]{{129,270},{179,251},{205,203}};
            caminoA= (ListaLineal) FaseA.convertirMatrizALista(new ListaLineal());
           ((CasillaDoble) caminoPrincipal.giveMe(14)).setAnterior((caminoA.primero));
           ((CasillaSimple)caminoA.ultimo).setSiguiente(caminoPrincipal.giveMe(19));
        //CREO LA FASE B
            Camino FaseB= new Camino();
            FaseB.matrizPosiciones= new float[][]{{330,203},{355,251},{402,270}};
            caminoB= (ListaLineal) FaseB.convertirMatrizALista(new ListaLineal());
            ((CasillaDoble) caminoPrincipal.giveMe(22)).setAnterior((caminoB.primero));
            ((CasillaSimple)caminoB.ultimo).setSiguiente(caminoPrincipal.giveMe(27));

        //CREO LA FASE C

            Camino FaseC= new Camino();
            FaseC.dobleEnlaze=true;
            FaseC.matrizPosiciones= new float[][]{{142, 445},{210, 445},{272, 445},{334, 445},{386, 445}};
            caminoC= (ListaLineal) FaseC.convertirMatrizALista(new ListaLineal());
            ((CasillaDoble) caminoPrincipal.giveMe(10)).setAnterior((caminoC.primero));
            ((CasillaDoble)caminoC.primero).setAnterior(caminoPrincipal.giveMe(10));
            ((CasillaDoble)caminoC.ultimo).setSiguiente(caminoPrincipal.giveMe(31));
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setAnterior(caminoC.ultimo);
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setRight(false);



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
        p1.setImagen(new ImageView(J1));
        Jugador p2 = new Jugador();
        p2.setImagen(new ImageView(J2));
        Jugador p3 = new Jugador();
        p3.setImagen(new ImageView(J3));
        Jugador p4 = new Jugador();
        p4.setImagen(new ImageView(J4));

        p1.moverseA((CasillaSimple) caminoPrincipal.primero);
        p2.moverseA((CasillaSimple)caminoPrincipal.primero);
        p3.moverseA((CasillaSimple)caminoPrincipal.primero);
        p4.moverseA((CasillaSimple)caminoPrincipal.primero);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Se muestran datos de P1
        Label datosP1 = new Label();
        datosP1.setText(String.valueOf("P1  " + p1.getMonedas()) + "   "+ p1.getEstrellas());
        datosP1.setLayoutX(565);
        datosP1.setLayoutY(300);
        datosP1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        datosP1.setStyle("-fx-background-color: rgba(243,236,250,0.63);");




        // Se muestran datos de P2
        Label datosP2 = new Label();
        datosP2.setText(String.valueOf("P2  " + p2.getMonedas()) + "   "+ p2.getEstrellas());
        datosP2.setLayoutX(565);
        datosP2.setLayoutY(340);
        datosP2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        datosP2.setStyle("-fx-background-color: rgba(243,236,250,0.63);");


        // Se muestran datos de P3
        Label datosP3 = new Label();
        datosP3.setText(String.valueOf("P3  " + p3.getMonedas()) + "   "+ p3.getEstrellas());
        datosP3.setLayoutX(565);
        datosP3.setLayoutY(380);
        datosP3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        datosP3.setStyle("-fx-background-color: rgba(243,236,250,0.63);");




        // Se muestran datos de P4
        Label datosP4 = new Label();
        datosP4.setText(String.valueOf("P4  " + p4.getMonedas()) + "   "+ p4.getEstrellas());
        datosP4.setLayoutX(565);
        datosP4.setLayoutY(420);
        datosP4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        datosP4.setStyle("-fx-background-color: rgba(243,236,250,0.63);");




/////////////////////////////////////////////////////////////////////////////
        //Boton comprar
        Button Compra = new Button("", new ImageView(btn));
        Compra.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        //POSICION
        Compra.setLayoutX(420);
        Compra.setLayoutY(600);
        //POSICION
        Compra.setScaleX(0.5);
        Compra.setScaleY(0.5);
        Compra.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Compra.setText("¡Comprar Estrella!");
        Compra.setContentDisplay(ContentDisplay.CENTER);
        Compra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (compraEstrella == true) {
                    Partida.encojerBoton(Compra);
                    if (turnodeJugador == 1) {
                        p1.comprarEstrella();
                        datosP1.setText(String.valueOf("P1  " + p1.getMonedas()) + "   "+ p1.getEstrellas());
                        MoverEstrella();
                    }
                    if (turnodeJugador == 2) {
                        p2.comprarEstrella();
                        datosP2.setText(String.valueOf("P2  " + p2.getMonedas()) + "   "+ p2.getEstrellas());
                        MoverEstrella();
                    }
                    if (turnodeJugador == 3) {
                        p3.comprarEstrella();
                        datosP3.setText(String.valueOf("P3  " + p3.getMonedas()) + "   "+ p3.getEstrellas());
                        MoverEstrella();
                    }
                    if (turnodeJugador == 4) {
                        p4.comprarEstrella();
                        datosP4.setText(String.valueOf("P4  " + p4.getMonedas()) + "   "+ p4.getEstrellas());
                        MoverEstrella();

                    }
                    compraEstrella = false;

                }
            }
        });






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
                //Lo que quisiera hacer es algo así



            }
        });

        Button Moved = new Button("", new ImageView(btn));

        Moved.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        //POSICION
        Moved.setLayoutX(490);
        Moved.setLayoutY(10);
        //POSICION
        Moved.setScaleX(0.5);
        Moved.setScaleY(0.5);
        Moved.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Moved.setText("¡DEV!");
        Moved.setContentDisplay(ContentDisplay.CENTER);
        Moved.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DevMOVING=!DevMOVING;
            }
        });

        Button EVENT = new Button("", new ImageView(btn));

        EVENT.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        //POSICION
        EVENT.setLayoutX(400);
        EVENT.setLayoutY(10);
        //POSICION
        EVENT.setScaleX(0.5);
        EVENT.setScaleY(0.5);
        EVENT.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        EVENT.setText("Event");
        EVENT.setContentDisplay(ContentDisplay.CENTER);
        EVENT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    eventManager.generarJuego();
                } catch (Exception e) {
                    e.printStackTrace();
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
                        if (rondasJugadas==2)
                        {
                            generar(e);
                        }
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

        Button cogerCartaV = new Button();
        cogerCartaV.setStyle("-fx-background-color:transparent;");
        //POSICION
        cogerCartaV.setLayoutX(495);
        Turno.setLayoutY(620);
        //POSICION
        cogerCartaV.setScaleX(0.5);
        cogerCartaV.setScaleY(0.5);


        //AQUI SE AGREGAN LOS COMPONENTES

        root.getChildren().addAll(tableroImagen, Move, Turno, ronda,victoria,Moved,EVENT,Compra);


        if(numeroDeJugadores>=2) {
            //Acá agarro la primera casilla de la lista circular

            root.getChildren().add(p1.getImagen());
            root.getChildren().add(p2.getImagen());

            root.getChildren().add(datosP1);

            root.getChildren().add(datosP2);

        }
        if(numeroDeJugadores>=3) {
            root.getChildren().add(p3.getImagen());

            root.getChildren().add(datosP3);

        }
        if(numeroDeJugadores==4) {
            root.getChildren().add(p4.getImagen());

            root.getChildren().add(datosP4);



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


                    if (evt.getCode().equals(KeyCode.ESCAPE)) {
                        Carta cartaAuxG = new Carta();
                        System.out.println(tipoCasilla);

                        if (tipoCasilla == 1) {
                            cartaAuxG = barajaVerde.crearCarta();
                            root.getChildren().add(cartaAuxG.getCarta());

                        }
                        if (tipoCasilla == 3){
                            cartaAuxG = barajaAzul.crearCarta();
                            root.getChildren().add(cartaAuxG.getCarta());

                        }
                        if (tipoCasilla == 2){
                            cartaAuxG = barajaRoja.crearCarta();
                            root.getChildren().add(cartaAuxG.getCarta());

                        }


                            }
                    }
            });
        primaryStage.show();




    }







}
