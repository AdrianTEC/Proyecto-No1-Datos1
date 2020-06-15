package sample;
import Listas.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
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


public class Tablero extends Application {

    //Para realizar el juego se usó abstracción para simplificar la idea del tablero y las relaciones y procesos que ocurren
    // there is the class atributes and encapsulation levels  (private and public)
    private Dado dado1;
    private Dado dado2;
    private Pane  root ;
    private Estrella e ;

    private ListaCircular caminoPrincipal;
    private ListaLineal caminoA;
    private ListaLineal caminoB;
    private ListaLineal caminoC;
    private ListaCircular caminoD;
    private EventManager eventManager;

    private  ListaLineal jugadores;
    private int numeroDeJugadores;
    private int cantidadDeTurnos;
    private int turnodeJugador ;
    private int rondasJugadas;
    private int tipoCasilla;
    private Button cogerCartaV;
    private boolean compraEstrella;
    private Jugador pxA;
    private boolean DEV;
    private boolean sePuedeMover;
    private boolean DevMOVING;
    private boolean primerEstrella;
    private Label ronda;

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
         cogerCartaV=new Button();
         DEV=false;
         jugadores= new ListaLineal();
         eventManager= new EventManager();
         ronda = new Label();
         sePuedeMover=true;
         primerEstrella = true;

    }
    public  void setNumeroDeJugadores(int numeroDeJugadores) {
        this.numeroDeJugadores = numeroDeJugadores;
    }
    public  void setCantidadDeTurnos(int cantidadDeTurnos) {
        this.cantidadDeTurnos = cantidadDeTurnos;
    }
    private void generarTextoEmergente(String text){
        Label victoria = new Label();
        victoria.setText(text);
        victoria.setLayoutX(10);
        victoria.setLayoutY(0);
        victoria.setPrefSize(700,200);
        victoria.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        victoria.setStyle("-fx-background-color: rgb(255,255,255);");
        Platform.runLater(()-> {root.getChildren().add(victoria);

            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(()-> root.getChildren().remove(victoria));
                        }
                    },
                    6000
            );


        });


    }
    private void generarJuego(int puntero,int juego) // función recursiva
        {
            if(puntero==0 && !eventManager.getMinijuegoAbierto())
                {
                    if(!sePuedeMover) {
                        puntero--;
                        Jugador p = eventManager.getGanador();
                        //generarTextoEmergente("El ganador del" + "\n" + "juego fue :" + p.getNombre() + "\n" + "gana " + numeroDeJugadores + "monedas");
                        if (numeroDeJugadores > 2) {  //Aquí se excluye el último jugador ya que pensamos que es lo ideal para el juego

                            generarTextoEmergente("El ganador es el jugador:" + eventManager.getGanador().getNombre()  +"  y gana " + numeroDeJugadores + "monedas" + "\n" + "El sugundo lugar es el jugador:" + eventManager.getSegundo().getNombre() +"  y gana " + (numeroDeJugadores -1) + "monedas"+ "\n" + "El tercer lugar es el jugador" +  eventManager.getTercero().getNombre() +"  y gana " + (numeroDeJugadores -2) + "monedas" );
                            eventManager.getTercero().setMonedas(eventManager.getTercero().getMonedas() + 1);
                        }
                        else{
                            generarTextoEmergente("El ganador del" + "\n" + "juego fue :" + p.getNombre() + "\n" + "gana " + numeroDeJugadores + "monedas");

                        }
                            p.setMonedas(p.getMonedas() + numeroDeJugadores);
                            eventManager.getSegundo().setMonedas(eventManager.getSegundo().getMonedas() + numeroDeJugadores - 1);
                            sePuedeMover = true;


                    }


                }
            if(puntero>0) {
                if(!eventManager.getMinijuegoAbierto()) {

                    puntero--;
                    Jugador p = (Jugador) ((CasillaExtraSimple) jugadores.giveMe(puntero)).getDato();
                    eventManager.generarJuego(p, juego);
                    int finalPuntero = puntero;
                    Platform.runLater(() -> generarJuego(finalPuntero, juego));
                }
                else {
                    int finalPuntero = puntero;
                    new java.util.Timer().schedule(  //recursividad asistida jeje
                            new java.util.TimerTask()
                            {  @Override
                                public void run()
                                {
                                    Platform.runLater(() -> Platform.runLater(() -> generarJuego(finalPuntero, juego))); }}, 3000);

                }
            }
            if(puntero==0&&eventManager.getMinijuegoAbierto()){

                int finalPuntero = puntero;

                new java.util.Timer().schedule(  //recursividad asistida jeje
                        new java.util.TimerTask()
                        {  @Override
                        public void run()
                        {
                            Platform.runLater(() -> Platform.runLater(() -> generarJuego(finalPuntero, juego))); }}, 3000);

            }



        }

    private void cambioTurno(){

        if (turnodeJugador < numeroDeJugadores+1)
            {
                turnodeJugador += 1;
                ronda.setText(rondasJugadas + "/" + cantidadDeTurnos);
            }

        if (turnodeJugador == numeroDeJugadores+1)
            {
                if (rondasJugadas<cantidadDeTurnos) {

                    turnodeJugador = 1;
                    rondasJugadas += 1;


                    if (rondasJugadas==2)
                    {
                        generar(e);
                    }
                    ronda.setText(rondasJugadas + "/" + cantidadDeTurnos); //se actualiza la ronda
                    generarTextoEmergente("¡MINIJUEGO!");
                    sePuedeMover=false;

                    new java.util.Timer().schedule(new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    generarJuego(jugadores.Size(), (int) (Math.random() * 3));
                                }}, 3000
                    );

                }
                else{
                    Label victoria = new Label();
                    int maxMonedas=0;
                    int max=0;
                    String ganador="";
                    for (int i=0; i<jugadores.Size();i++)
                        {
                            Jugador aux= (Jugador) ((CasillaExtraSimple)jugadores.giveMe(i)).getDato();
                            if (aux.getEstrellas() == max){
                                if (aux.getMonedas() > maxMonedas){
                                    ganador= aux.getNombre();
                                    maxMonedas = aux.getMonedas();
                            }
                            }
                            if(aux.getEstrellas()> max){
                                ganador=aux.getNombre();
                                max=aux.getEstrellas();
                                maxMonedas = aux.getMonedas();
                            }
                        }
                    victoria.setText("Fin del juego"+"\n"+ "gana el jugador :" +ganador );
                    victoria.setLayoutX(100);
                    victoria.setLayoutY(130);
                    victoria.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                    victoria.setStyle("-fx-background-color: rgb(255,255,255);");
                    sePuedeMover= false;

                    root.getChildren().add(victoria);
                }
            }


    }
    public  void generar(Estrella es) {
        es.numeroRandom();
        if (rondasJugadas >= 2) {
            if (es.getNumero() == 1) {
                es.setUbicacionEnElMapa(caminoA.giveMe(((int) (Math.random() * 3))+1));
            }
            if (es.getNumero() == 2) {
                es.setUbicacionEnElMapa(caminoB.giveMe(((int) (Math.random() * 3))+1));
            }
            if (es.getNumero() == 3) {
                es.setUbicacionEnElMapa(caminoC.giveMe(((int) (Math.random() * 5))+1));
            }
            if (es.getNumero()==4){
                es.setUbicacionEnElMapa(caminoPrincipal.giveMe(((int) (Math.random() * 30))+1));
            }
            if (primerEstrella){
                root.getChildren().add(es.getImagen());
                primerEstrella = false;
            }
            es.moverseA((CasillaSimple) es.getUbicacionEnElMapa());



        }
    }

    private void moverPersonaje(Jugador px,int numDado,int puntero,boolean hasnotMovingYet)
        {/*This funtion is in charge of moving the players, recognize the actual player kind of Casilla, and turns the Casillas Dobles "intersection"
         *@author Adrián González Jiménez
         *@Version 10/05/2020
         * @param Jugador px, Int num Dado,int puntero,boolean hasnotMovingYet
         */
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


                                if(px.getUbicacionEnElMapa() instanceof  CasillaDoble) // está en una casilla doble?
                                {       //Estoy en una casilla Doble

                                    if(! ((CasillaDoble) px.getUbicacionEnElMapa()).getTipo().contains("i")  ) { //NO es una intersección????
                                        if (px.getDirection())// VECTOR QUE LE DA UNA CASILLA DE INTERSECCIÓN AL JUGADOR  //vas hacia la derecha???
                                        {   //SI TRUE AL SIGUIENTE
                                            px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente());

                                        } else {  //SI FALSE AL ANTERIOR
                                            px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getAnterior());
                                        }

                                    }
                                    else {
                                        CasillaDoble xx= (CasillaDoble) px.getUbicacionEnElMapa(); /// ES UNA INTERSECCIÓN
                                        System.out.println("Medidor");
                                        if(hasnotMovingYet)
                                            {
                                                if  (!((CasillaDoble) px.getUbicacionEnElMapa()).getTipo().equals("Di")){

                                                px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente());
                                                px.setDirection(xx.getRight());
                                                xx.changeDirection();
                                            }
                                                else{
                                                    px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente());
                                                    px.setDirection(xx.getRight());
                                                }
                                            }
                                        else {   px.moverseA((CasillaSimple) ((CasillaDoble) px.getUbicacionEnElMapa()).getSiguiente());

                                             }
                                        }


                                }
                                else
                                    //Estoy en una casilla simple
                                    {
                                        if (((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente() instanceof CasillaDoble)
                                            {   //ME SIGUE UNA CASILLA DOBLE
                                                px.moverseA((CasillaDoble) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente());
                                            }
                                        else
                                            {   //ME SIGUE UNA CASILLA SIMPLE
                                                px.moverseA((CasillaSimple) ((CasillaSimple) px.getUbicacionEnElMapa()).getSiguiente());
                                            }
                                    }

                                Platform.runLater(() -> moverPersonaje(px,numDado,puntero+1,false));
                            }
                        },
                        300
                );
            }


            else {
                String x=((CasillaSimple) px.getUbicacionEnElMapa()).getTipo();
                if (px.getUbicacionEnElMapa() instanceof CasillaDoble) {

                    if (x.equals("Vi") || x.equals("Ri") || x.equals("Ai")) {
                        px.setDirection(   ((CasillaDoble) px.getUbicacionEnElMapa()).getRight()          );
                        ((CasillaDoble) px.getUbicacionEnElMapa()).changeDirection();
                    }
                    if (x.equals("Dx")) {
                        px.setDirection(!px.getDirection());
                    }
                }
                    if (x.equals("V")||x.equals("Vi")){
                        tipoCasilla = 1;
                            cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/barajaV.png"));

                    }
                    if (x.equals("R")||x.equals("Ri")){
                        tipoCasilla = 2;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(R).png"));


                    }
                    if (x.equals("A")||x.equals("Ai")) {
                        tipoCasilla = 3;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(A).png"));

                    }
                    if (x.equals("D") || x.equals("Dx")){
                        tipoCasilla = 4;
                        cogerCartaV.setGraphic(new ImageView("Imagenes/Cartas/baraja(D).png"));


                    }
                    if (x.equals("Di")){
                    eventManager.telRamdom(pxA);
                     }

                for (int i = 0; i<numeroDeJugadores;i++){
                    if(px!= ((CasillaExtraSimple)jugadores.giveMe(i) ).getDato()) {
                        if (px.getUbicacionEnElMapa() == ((Jugador) ((CasillaExtraSimple)jugadores.giveMe(i) ).getDato()).getUbicacionEnElMapa()) {

                            eventManager.duelo(px,((Jugador) ((CasillaExtraSimple)jugadores.giveMe(i) ).getDato()));
                        }
                    }
                }

                cogerCartaV.setLayoutX(240);
                cogerCartaV.setLayoutY(100);
                cogerCartaV.setScaleX(0.2);
                cogerCartaV.setScaleY(0.2);

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
                root.getChildren().removeAll(dado1.cara,dado2.cara);
            dado1.tirar();
            dado2.tirar();

            dado1.cara.setLayoutX(120);
            dado2.cara.setLayoutX(180);

            root.getChildren().addAll(dado1.cara,dado2.cara);
            //Aquí debemos realizar un doble casteo, se realizó una actualización para poder generalizar a las casillas
            //Estas en vez de poderse comunicar entre casillas del mismo tipo lo podrán hacer con otras que no lo sean
            //Entonces ellas tendran de atributo de tipo OBJETO entonces hacemos doble casteo primero por "UBICACION EN EL MAPA"
            //            //Luego lo hacemos por "SIGUIENTE"

                sePuedeMover=false;
                moverPersonaje(px, dado1.getNumero() + dado2.getNumero(), 0,true);

            }
            else { moverPersonaje(px, 1, 0,true); }
        }

    @Override// there is overwriting  this  use  handle method from other class;
    public void start(Stage primaryStage) {
        /*This funtion is in charge of window building and creates the way list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param primaryStage
         */

         //creo una instancia manejadora de eventos

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        e.setImagen(new ImageView("Imagenes/Estrella.png"));


        Baraja barajaVerde;
        Baraja barajaAzul;
        Baraja barajaRoja;
        Baraja barajaDorada;

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
            eventManager.setCamPrincipal(caminoPrincipal);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE A
            Camino FaseA= new Camino();
            FaseA.matrizPosiciones= new float[][]{{129,270},{179,251},{205,203}};

        caminoA = (ListaLineal) FaseA.convertirMatrizALista(new ListaLineal());
        caminoA.aplicarPropiedades(new String[]{"R","V","A"});

        ((CasillaDoble) caminoPrincipal.giveMe(14)).setAnterior((caminoA.primero));
           ((CasillaSimple) caminoA.ultimo).setSiguiente(caminoPrincipal.giveMe(19));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE B
            Camino FaseB= new Camino();
            FaseB.matrizPosiciones= new float[][]{{330,203},{355,251},{402,270}};
        caminoB = (ListaLineal) FaseB.convertirMatrizALista(new ListaLineal());
        caminoB.aplicarPropiedades(new String[]{"D","D","D"});
            ((CasillaDoble) caminoPrincipal.giveMe(22)).setAnterior((caminoB.primero));
            ((CasillaSimple) caminoB.ultimo).setSiguiente(caminoPrincipal.giveMe(27));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CREO LA FASE C

            Camino FaseC= new Camino();
            FaseC.dobleEnlaze=true;
            FaseC.matrizPosiciones= new float[][]{{142, 445},{210, 445},{272, 445},{334, 445},{386, 445}};

        caminoC = (ListaLineal) FaseC.convertirMatrizALista(new ListaLineal());
        caminoC.aplicarPropiedades(new String[]{"R","V","R","V","R"});

        ((CasillaDoble) caminoPrincipal.giveMe(10)).setAnterior((caminoC.primero));
            ((CasillaDoble) caminoC.primero).setAnterior(caminoPrincipal.giveMe(10));
            ((CasillaDoble) caminoC.ultimo).setSiguiente(caminoPrincipal.giveMe(31));
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setAnterior(caminoC.ultimo);
            ((CasillaDoble) caminoPrincipal.giveMe(31)).setRight(false);



        /////////////////////////////////////////////////////////////////////////
            //CREO LA FASE D
        Camino FaseD= new Camino();
        FaseD.dobleEnlaze=true;
        FaseD.matrizPosiciones= new float[][]{{10,80},{117,80},{240,80},{363,80},{480,80},{528,80},{528,138},{528,256},{528,370},{528,488},{528,595},{480,595},{363,595},{240,595},{117,595},{10,595},{10,488} ,{10,370},{10,256},{10,138}   };
        caminoD = (ListaCircular) FaseD.convertirMatrizALista(new ListaCircular());
        caminoD.aplicarPropiedades(new String[]{"Di","Dx","Dx","Dx","Dx","Di","Dx","Dx","Dx","Dx","Di","Dx","Dx","Dx","Dx","Di","Dx","Dx","Dx","Dx"});
        eventManager.setFaseD(caminoD);
        //////////////////////////////////////////////////////////////////////////////7
        //IMAGEN TABLERO
        Image btn           = new Image("Imagenes/Boton.png  ");
        Image TableroImagen = new Image("Imagenes/Tablero.png");
        ImageView tableroImagen = new ImageView(TableroImagen);

        /////////////////////////////////////////////////////////////////////////
        //Aqui se muestra la cantidad de rondas que han pasado y la cantidad de rondas que se deben jugar
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
        pxA=p1;

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
        Compra.setLayoutX(485);Compra.setLayoutY(600);Compra.setScaleX(0.5);Compra.setScaleY(0.5);
        Compra.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
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

                generar(e);
                compraEstrella = false;

            }
        });
        /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        Button Move = new Button("", new ImageView(btn));

        Move.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        Move.setLayoutX(490);Move.setLayoutY(500);Move.setScaleX(0.5);Move.setScaleY(0.5);
        Move.setFont(Font.font("Verdana", FontWeight.BOLD, 30));Move.setText("¡Mover!");
        Move.setContentDisplay(ContentDisplay.CENTER);
        Move.setOnAction(event -> {
            if(sePuedeMover) {
                Partida.encojerBoton(Move);

                if (turnodeJugador == 1) {
                    lanzarDados(p1);
                }
                if (turnodeJugador == 2) {
                    lanzarDados(p2);
                }
                if (turnodeJugador == 3) {
                    lanzarDados(p3);
                }
                if (turnodeJugador == 4) {
                    lanzarDados(p4);
                }
            }
        });
      /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        Button Moved = new Button("", new ImageView(btn));
        Partida.encojerBoton(Moved);
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
        Partida.encojerBoton(EVENT);
        EVENT.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        EVENT.setLayoutX(350);
        EVENT.setLayoutY(-28);
        EVENT.setScaleX(0.1);
        EVENT.setScaleY(0.1);
        EVENT.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        EVENT.setText("Event");
        EVENT.setContentDisplay(ContentDisplay.CENTER);
        EVENT.setOnAction(event -> {
            Partida.encojerBoton(EVENT);

            try {
                eventManager.Dev(pxA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        /////////////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////


        Button Turno = new Button ("", new ImageView(btn));
        Turno.setStyle("-fx-background-color:transparent;");
        Turno.setText("Turno");
        Turno.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Turno.setLayoutX(295);
        Turno.setLayoutY(600);
        Turno.setScaleX(0.5);
        Turno.setScaleY(0.5);
        Turno.setContentDisplay(ContentDisplay.CENTER);


        Turno.setOnAction(actionEvent -> {
            Partida.encojerBoton(Turno);
            cambioTurno();


        });
        ///////////////////////////////////////////////////////////////////////      /////////////////////////////////////////////////////////////////////////////
        cogerCartaV = new Button("");
        cogerCartaV.setStyle("-fx-background-color:transparent;-fx-background-radius: 30");
        cogerCartaV.setOnAction(actionEvent -> {

            if(!sePuedeMover)
             {      sePuedeMover=true;
                    Partida.encojerBoton(cogerCartaV);

                    Carta cartaAuxG = new Carta();
                    if (tipoCasilla == 1) {

                        cartaAuxG = barajaVerde.crearCarta();
                        cartaAuxG.setDescripcion(tipoCasilla);
                        pxA.setMonedas(pxA.getMonedas() + 1);


                    }
                    if (tipoCasilla == 3) {
                        cartaAuxG = barajaAzul.crearCarta();
                        cartaAuxG.setDescripcion(tipoCasilla);

                    }
                    if (tipoCasilla == 2) {
                        cartaAuxG = barajaRoja.crearCarta();
                        cartaAuxG.setDescripcion(tipoCasilla);
                        pxA.setMonedas(pxA.getMonedas() - 1);

                    }
                    if (tipoCasilla == 4) {
                        cartaAuxG = eventManager.takeACard(pxA);
                    }
                    Carta finalCartaAuxG = cartaAuxG;
                    root.getChildren().addAll(cartaAuxG.getCarta(), cartaAuxG.getDescripcion());
                    cartaAuxG.getCarta().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> root.getChildren().removeAll(finalCartaAuxG.getCarta(), finalCartaAuxG.getDescripcion()));
                    cambioTurno();
                }
            });
        ///////////////////////////////////////////////////////////////////////        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        //AQUI SE AGREGAN LOS COMPONENTES

        ///////////////////////////////////////////////////////////////////////        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        root.getChildren().addAll(tableroImagen, Move, ronda,Compra,cogerCartaV);
        if(numeroDeJugadores>=2) {
            //Acá agarro la primera casilla de la lista circular

            root.getChildren().add(p1.getImagen());
            root.getChildren().add(p2.getImagen());
            CasillaExtraSimple pl1=new CasillaExtraSimple();pl1.setDato(p1);
            root.getChildren().add(p1.getRecursos());
            root.getChildren().add(p2.getRecursos());
            CasillaExtraSimple pl2=new CasillaExtraSimple();pl2.setDato(p2);

            jugadores.ingresarNodo(pl1);
            jugadores.ingresarNodo(pl2);
    }
        if(numeroDeJugadores>=3) {
            root.getChildren().add(p3.getImagen());
            root.getChildren().add(p3.getRecursos());
            CasillaExtraSimple pl3=new CasillaExtraSimple();pl3.setDato(p3);

            jugadores.ingresarNodo(pl3);
        }
        if(numeroDeJugadores==4) {
            root.getChildren().add(p4.getImagen());
            root.getChildren().add(p4.getRecursos());
            CasillaExtraSimple pl4=new CasillaExtraSimple();pl4.setDato(p4);

            jugadores.ingresarNodo(pl4);

        }
        eventManager.setJugadores(jugadores);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Datos Party 1");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, evt -> {
            DEV= !DEV;
            if(DEV){
            root.getChildren().addAll(Moved,EVENT,Turno);}
            else {root.getChildren().removeAll(Moved,EVENT,Turno);}
        });

        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(1));
    }
}
