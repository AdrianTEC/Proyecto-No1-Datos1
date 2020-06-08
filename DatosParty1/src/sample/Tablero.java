package sample;
import javafx.stage.Stage;
import javafx.scene.Scene;
import Listas.ListaLineal;
import Listas.CasillaDoble;
import Listas.CasillaSimple;
import Listas.ListaCircular;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.scene.control.ContentDisplay;

import java.lang.reflect.InvocationTargetException;

public class Tablero extends Application {
    // there is the class atributes and encapsulation levels  (private and public)
    private Dado dado1;
    private Dado dado2;
    private Pane  root ;
    private Estrella e ;

    private ListaCircular caminoPrincipal;
    private EventManager eventManager;

    private int numeroDeJugadores;
    private int cantidadDeTurnos;
    private int turnodeJugador ;
    private int rondasJugadas;
    private int tipoCasilla;
    private Button cogerCartaV;

    private Baraja barajaVerde;
    private Baraja barajaAzul;
    private Baraja barajaRoja;
    private Baraja barajaDorada;

    private Jugador pxA;



    private boolean DevMOVING;
    private boolean compraEstrella;


    public  Tablero()
    {
         numeroDeJugadores=2;
         cantidadDeTurnos=10;
         compraEstrella = false;
         turnodeJugador = 1;
         rondasJugadas= 1;
         tipoCasilla = 4;
         DevMOVING =false;
         root = new Pane();
         e = new Estrella();
         pxA = new Jugador();

    }
    public void setNumeroDeJugadores(int numeroDeJugadores) {
        this.numeroDeJugadores = numeroDeJugadores;
    }
    public void setCantidadDeTurnos(int cantidadDeTurnos) {
        this.cantidadDeTurnos = cantidadDeTurnos;
    }

    public void generar(Estrella es) {
        if(rondasJugadas>=2) {
            es.numeroRandom();
            es.setUbicacionEnElMapa(caminoPrincipal.giveMe(es.getNumero()));
            es.moverseA((CasillaSimple) es.getUbicacionEnElMapa());
            root.getChildren().add(es.getImagen());
        }
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
        {   pxA = px;
            Partida.reproducirSonido("paso");
            if( puntero <numDado) {
                if(px.getUbicacionEnElMapa()==e.getUbicacionEnElMapa())
                    {
                        // ...
                        Platform.runLater(() -> {
                            if(px.getMonedas() > 1) {
                                compraEstrella = true;
                            }
                        });
                    }
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                if(px.getUbicacionEnElMapa() instanceof  CasillaDoble)
                                    {
                                        if(px.getDirection()) { px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente()); }
                                        else { px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getAnterior()); }
                                    }
                                else  {
                                        if (((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente() instanceof CasillaSimple) { px.moverseA((CasillaSimple) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente()); }
                                        else {px.moverseA((CasillaDoble) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente()); }
                                    }
                                Platform.runLater(() -> {
                                    System.out.println(px);
                                    moverPersonaje(px,numDado,puntero+1);

                                });
                            }
                        },
                        300
                );
            }


            else {

                String x=((CasillaSimple) px.getUbicacionEnElMapa()).getTipo();
                if (px.getUbicacionEnElMapa() instanceof CasillaDoble) {

                    if (x.equals("Vi") || x.equals("Ri") || x.equals("Ai")) {
                        System.out.println("se ha cambiado la orientación");
                        px.setDirection(   ((CasillaDoble) px.getUbicacionEnElMapa()).getRight()          );
                        ((CasillaDoble) px.getUbicacionEnElMapa()).changeDirection();
                    }
                }
                    if (x.equals("V")||x.equals("Vi")){
                        tipoCasilla = 1;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/barajaV.png"));
                        cogerCartaV.setLayoutX(-100);
                        cogerCartaV.setLayoutY(250);
                        cogerCartaV.setText("");
                        cogerCartaV.setScaleX(0.2);
                        cogerCartaV.setScaleY(0.2);


                    }
                    if (x.equals("R")||x.equals("Ri")){
                        tipoCasilla = 2;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(R).png"));
                        cogerCartaV.setLayoutX(-100);
                        cogerCartaV.setLayoutY(250);
                        cogerCartaV.setText("");
                        cogerCartaV.setScaleX(0.2);
                        cogerCartaV.setScaleY(0.2);

                    }
                    if (x.equals("A")||x.equals("Ai")) {
                        tipoCasilla = 3;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(A).png"));
                        cogerCartaV.setLayoutX(-100);
                        cogerCartaV.setLayoutY(250);
                        cogerCartaV.setText("");
                        cogerCartaV.setScaleX(0.2);
                        cogerCartaV.setScaleY(0.2);
                    }
                    if (x.equals("D")){
                        tipoCasilla = 4;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(D).png"));
                        cogerCartaV.setLayoutX(-100);
                        cogerCartaV.setLayoutY(250);
                        cogerCartaV.setText("");
                        cogerCartaV.setScaleX(0.2);
                        cogerCartaV.setScaleY(0.2);
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
            else { moverPersonaje(px, 1, 0); }
        }

    @Override// there is overwriting  this  use  handle method from other class;
    public void start(Stage primaryStage) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*This funtion is in charge of window building and creates the way list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */

        eventManager= new EventManager(); //creo una instancia manejadora de eventos

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        e.setImagen(new ImageView("Imagenes/Estrella.png"));



        barajaVerde = new Baraja();barajaVerde.setTipoBaraja("V");

        barajaAzul = new Baraja();barajaAzul.setTipoBaraja("A");

        barajaRoja = new Baraja();barajaRoja.setTipoBaraja("R");

        barajaDorada = new Baraja();barajaDorada.setTipoBaraja("D");

        //CREO LOS DOS DADOS
         dado1= new Dado();
         dado2= new Dado();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Camino FaseInicial= new Camino();
            FaseInicial.matrizPosiciones= new float[][]{    {427, 531}, {375, 531}, {333, 531}, {291, 531}, {246, 531,}, {204, 531}, {162, 531}, {110, 531}, {70, 494 }, {70, 445}, {70, 402} , {70, 359 }, {70, 316 }, {70, 273 }, {70, 230},{70, 179}, {110, 142}, {162, 142}, {204, 142}, {246, 142}, {291, 142},{333, 142}, {375, 142}, {427, 142}, {470, 185}, {470, 230}, {470, 273}, {470, 316}, {470, 359}, {470, 402} ,{470, 445}, {470, 488}   };

            caminoPrincipal= (ListaCircular) FaseInicial.convertirMatrizALista(new ListaCircular());   //extraigo la lista circular

            caminoPrincipal.aplicarPropiedades(new String[]{"D","V","R","A","R","V","A","R","D","Vi","A","R","A","Vi","R","D","R","A","R","V","R","Vi","A","D","A","V","R","V","A","V","Ai","R"});
            caminoPrincipal.remplazarCasillaSimple(10);
            caminoPrincipal.remplazarCasillaSimple(31);
            caminoPrincipal.remplazarCasillaSimple(14);
            caminoPrincipal.remplazarCasillaSimple(22);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE A
            Camino FaseA= new Camino();
            FaseA.matrizPosiciones= new float[][]{{129,270},{179,251},{205,203}};
        ListaLineal caminoA = (ListaLineal) FaseA.convertirMatrizALista(new ListaLineal());
           ((CasillaDoble) caminoPrincipal.giveMe(14)).setAnterior((caminoA.primero));
           ((CasillaSimple) caminoA.ultimo).setSiguiente(caminoPrincipal.giveMe(19));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE B
            Camino FaseB= new Camino();
            FaseB.matrizPosiciones= new float[][]{{330,203},{355,251},{402,270}};
        ListaLineal caminoB = (ListaLineal) FaseB.convertirMatrizALista(new ListaLineal());
            ((CasillaDoble) caminoPrincipal.giveMe(22)).setAnterior((caminoB.primero));
            ((CasillaSimple) caminoB.ultimo).setSiguiente(caminoPrincipal.giveMe(27));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE C

            Camino FaseC= new Camino();
            FaseC.dobleEnlaze=true;
            FaseC.matrizPosiciones= new float[][]{{142, 445},{210, 445},{272, 445},{334, 445},{386, 445}};
        ListaLineal caminoC = (ListaLineal) FaseC.convertirMatrizALista(new ListaLineal());
            ((CasillaDoble) caminoPrincipal.giveMe(10)).setAnterior((caminoC.primero));
            ((CasillaDoble) caminoC.primero).setAnterior(caminoPrincipal.giveMe(10));
            ((CasillaDoble) caminoC.ultimo).setSiguiente(caminoPrincipal.giveMe(31));
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setAnterior(caminoC.ultimo);
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setRight(false);


        /////////////////////////////////////////////////////////////////////////

        //IMAGEN TABLERO
        Image btn           = new Image("Imagenes/Boton.png  ");
        Image TableroImagen = new Image("Imagenes/Tablero.png");
        ImageView tableroImagen = new ImageView(TableroImagen);

        /////////////////////////////////////////////////////////////////////////
        //Aqui se muestra la cantidad de rondas que han pasado y la cantidad de rondas que se deben jugar
        Label ronda = new Label();
        ronda.setLayoutX(605);ronda.setLayoutY(130);
        ronda.setText(rondasJugadas + "/" + cantidadDeTurnos);
        ronda.setStyle("-fx-background-color: rgba(243,236,250,0.63);");
        ronda.setFont(Font.font("Verdana", FontWeight.BOLD, 25));


        ///SE CREAN LOS JUGADORES ACÁ //////////////////////////////////////////////////////////////////////////////////
        Jugador p1 = new Jugador(); Jugador p2 = new Jugador(); Jugador p3 = new Jugador(); Jugador p4 = new Jugador();
        p1.setImagen(new ImageView("Imagenes/1.png"));p2.setImagen(new ImageView("Imagenes/2.png"));p3.setImagen(new ImageView("Imagenes/3.png"));p4.setImagen(new ImageView("Imagenes/4.png"));

        p1.moverseA((CasillaSimple) caminoPrincipal.primero);
        p2.moverseA((CasillaSimple)caminoPrincipal.primero);
        p3.moverseA((CasillaSimple)caminoPrincipal.primero);
        p4.moverseA((CasillaSimple)caminoPrincipal.primero);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Se muestran datos de P1

        p1.getRecursos().setLayoutX(565);p1.getRecursos().setLayoutY(300);
        p1.setNombre("1");
        p1.        actualizarRecursos();


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Se muestran datos de P2

        p2.getRecursos().setLayoutX(565);p2.getRecursos().setLayoutY(340);
        p2.setNombre("2");
        p2.        actualizarRecursos();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Se muestran datos de P3
        p3.getRecursos().setLayoutX(565);p3.getRecursos().setLayoutY(380);
        p3.setNombre("3");
        p3.        actualizarRecursos();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Se muestran datos de P4
        p4.getRecursos().setLayoutX(565);p4.getRecursos().setLayoutY(420);
        p4.setNombre("4");
        p4.        actualizarRecursos();

        /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        //Boton comprar
        Button Compra = new Button("", new ImageView(btn));
        Compra.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        Compra.setLayoutX(420);
        Compra.setLayoutY(600);
        Compra.setScaleX(0.5);
        Compra.setScaleY(0.5);
        Compra.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Compra.setText("¡Comprar Estrella!");
        Compra.setContentDisplay(ContentDisplay.CENTER);
        Compra.setOnAction(event -> {
            if (compraEstrella) {
                Partida.encojerBoton(Compra);
                if (turnodeJugador == 1) {
                    p1.comprarEstrella();
                    p1.getRecursos().setText("P1  " + p1.getMonedas() + "   "+ p1.getEstrellas()); }
                if (turnodeJugador == 2) {
                    p2.comprarEstrella();
                    p2.getRecursos().setText("P2  " + p2.getMonedas() + "   "+ p2.getEstrellas()); }
                if (turnodeJugador == 3) {
                    p3.comprarEstrella();
                    p3.getRecursos().setText("P3  " + p3.getMonedas() + "   "+ p3.getEstrellas()); }
                if (turnodeJugador == 4) {
                    p4.comprarEstrella();
                    p4.getRecursos().setText("P4  " + p4.getMonedas() + "   "+ p4.getEstrellas()); }

                MoverEstrella();
                compraEstrella = false;

            }
        });
        /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        Button Move = new Button("", new ImageView(btn));

        Move.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        Move.setLayoutX(490);
        Move.setLayoutY(500);
        Move.setScaleX(0.5);
        Move.setScaleY(0.5);
        Move.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Move.setText("¡Mover!");
        Move.setContentDisplay(ContentDisplay.CENTER);
        Move.setOnAction(event -> {

            Partida.encojerBoton(Move);

            if (turnodeJugador==1) { lanzarDados(p1); }
            if (turnodeJugador==2) { lanzarDados(p2); }
            if (turnodeJugador==3) { lanzarDados(p3); }
            if (turnodeJugador==4) { lanzarDados(p4); }

        });
      /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        Button Moved = new Button("", new ImageView(btn));

        Moved.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        Moved.setLayoutX(490);
        Moved.setLayoutY(-28);
        Moved.setScaleX(0.5);
        Moved.setScaleY(0.5);
        Moved.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Moved.setText("¡DEV!");
        Moved.setContentDisplay(ContentDisplay.CENTER);
        Moved.setOnAction(event -> DevMOVING = !DevMOVING);


        /////////////////////////////////////////////////////////////////////////////        /////////////////////////////////////////////////////////////////////////////

        Button EVENT = new Button("", new ImageView(btn));

        EVENT.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        EVENT.setLayoutX(350);
        EVENT.setLayoutY(-28);
        EVENT.setScaleX(0.5);
        EVENT.setScaleY(0.5);
        EVENT.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        EVENT.setText("Event");
        EVENT.setContentDisplay(ContentDisplay.CENTER);
        EVENT.setOnAction(event -> {
            try {
                eventManager.teletransporte(p1, caminoPrincipal.giveMe((int)(Math.random()*31) + 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////


        Button Turno = new Button ("", new ImageView(btn));
        Turno.setStyle("-fx-background-color:transparent;");
        Turno.setLayoutX(495);
        Turno.setLayoutY(420);
        Turno.setScaleX(0.5);
        Turno.setScaleY(0.5);


        Label victoria = new Label();

        Turno.setOnAction(actionEvent -> {
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

        });
        ///////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        Button cogerCartaV = new Button("", new ImageView(btn));
        cogerCartaV.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        cogerCartaV.setLayoutX(190);
        cogerCartaV.setLayoutY(-28);
        cogerCartaV.setScaleX(0.5);
        cogerCartaV.setScaleY(0.5);
        cogerCartaV.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        cogerCartaV.setText("¡Carta!");
        cogerCartaV.setContentDisplay(ContentDisplay.CENTER);
        cogerCartaV.setOnAction(actionEvent -> {
            Carta cartaAuxG = new Carta();
            System.out.println(tipoCasilla);

            if (tipoCasilla == 1) {
                cartaAuxG = barajaVerde.crearCarta();
                cartaAuxG.setDescripcion(tipoCasilla);
                pxA.setMonedas(pxA.getMonedas()+1);


                root.getChildren().addAll(cartaAuxG.getCarta(),cartaAuxG.getDescripcion());

            }
            if (tipoCasilla == 3){
                cartaAuxG = barajaAzul.crearCarta();
                cartaAuxG.setDescripcion(tipoCasilla);
                root.getChildren().addAll(cartaAuxG.getCarta(),cartaAuxG.getDescripcion());


            }
            if (tipoCasilla == 2){
                cartaAuxG = barajaRoja.crearCarta();
                cartaAuxG.setDescripcion(tipoCasilla);
                pxA.setMonedas(pxA.getMonedas()-1);
                root.getChildren().addAll(cartaAuxG.getCarta(),cartaAuxG.getDescripcion());
            }
            Carta finalCartaAuxG = cartaAuxG;
            cartaAuxG.getCarta().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> root.getChildren().removeAll(finalCartaAuxG.getCarta(), finalCartaAuxG.getDescripcion()));
        });
        ///////////////////////////////////////////////////////////////////////        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        //AQUI SE AGREGAN LOS COMPONENTES

        ///////////////////////////////////////////////////////////////////////        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        root.getChildren().addAll(tableroImagen, Move, Turno, ronda,victoria,Moved,EVENT,Compra,cogerCartaV);
        if(numeroDeJugadores>=2) {
            //Acá agarro la primera casilla de la lista circular

            root.getChildren().add(p1.getImagen());
            root.getChildren().add(p2.getImagen());

            root.getChildren().add(p1.getRecursos());
            root.getChildren().add(p2.getRecursos());

        }
        if(numeroDeJugadores>=3) {
            root.getChildren().add(p3.getImagen());
            root.getChildren().add(p3.getRecursos());

        }
        if(numeroDeJugadores==4) {
            root.getChildren().add(p4.getImagen());
            root.getChildren().add(p4.getRecursos());
        }
        primaryStage.setResizable(false);
        primaryStage.setTitle("Datos Party 1");
        primaryStage.setScene(new Scene(root, 700, 700));

        primaryStage.show();
    }
}
