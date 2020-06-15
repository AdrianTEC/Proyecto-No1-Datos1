package sample;

import Listas.*;
import MiniJuegos.*;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.lang.reflect.Method;

public class EventManager extends Baraja implements Observador{
    private String[] eventos;
    private Pila barajaDeEventos;
    private String[] memoriaDeInicio;
    private ListaCircular camPrincipal;
    private ListaCircular faseD;
    private ListaLineal jugadores;
    private Boolean robando;
    private Jugador pxa;
    private Carta actual;
    private Boolean minijuegoAbierto;
    private Jugador ganador;
    private int maxPuntaje;
    private int segundoPuntaje;
    private int tercerPuntaje;
    private Jugador segundo;
    private Jugador tercero;

    public EventManager() {
        setTipoBaraja("D");
        barajaDeEventos= new Pila();
        eventos=new String[]{"duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo",  //DUELO
                            "robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas", //ROBAR MONEDAS
                            "regalarMonedas","regalarMonedas","regalarMonedas","regalarMonedas","regalarMonedas", //Regalar Monedas
                            "perderUnaEstrella","perderUnaEstrella","perderUnaEstrella","perderUnaEstrella","perderUnaEstrella",//PERDER ESTRELLA
                            "ganar2Estrellas","ganar2Estrellas","ganar2Estrellas",//Ganar 2 estrellas
                            "ganar5Estrellas",//Ganar 5 estrellass
                            "robarEstrella","robarEstrella","robarEstrella", //RobarEstrella
                            "telRamdom","telRamdom","telRamdom","telAfaseD","telAfaseD","telAfaseD","telRamdom","telRamdom","telRamdom","telRamdom", //Teletransporte
                            "cambioDeLugares","cambioDeLugares","cambioDeLugares","cambioDeLugares","cambioDeLugares"
        };  //IntercambioDeLugares
        ganador=new Jugador();
        maxPuntaje=0;
        memoriaDeInicio=eventos;
        minijuegoAbierto=false;
        Barajar();
        robando=false;
        pxa= new Jugador();
        actual= new Carta();
        segundo = new Jugador();
        tercero = new Jugador();
    }

    public Boolean getMinijuegoAbierto() {
        return minijuegoAbierto;
    }

    public Jugador getGanador() {

        return ganador;
    }

    public Jugador getSegundo() {
        return segundo;
    }

    public Jugador getTercero() {
        return tercero;
    }

    public void setCamPrincipal(ListaCircular camPrincipal) {
        this.camPrincipal = camPrincipal;
    }

    public void setFaseD(ListaCircular faseD) {
        this.faseD = faseD;
    }

    public void setJugadores(ListaLineal jugadores) {
        this.jugadores = jugadores;

        for(int i=0; i<jugadores.Size();i++)
            {
                int finalI1 = i;
                    Jugador aux= (Jugador) ((CasillaExtraSimple) jugadores.giveMe(finalI1)).getDato();
                     aux.getImagen().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                        if(robando){
                            robando=false;
                            robarA(pxa, (Jugador) ((CasillaExtraSimple) jugadores.giveMe(finalI1)).getDato());
                        }

                    });



            }
    }

    public void Barajar()   {
        /*shuffle a new stack of events
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
        System.out.println("Se barajó la pila de eventos");
        eventos=memoriaDeInicio;
        for(int i=0; i< eventos.length;i++)
        {
            String aux= eventos[i];
            int numb=(int) (Math.random()*eventos.length);
            eventos[i]=eventos[numb];
            eventos[numb]= aux;
        }
        barajaDeEventos.especialPush(eventos);
    }

    public Carta takeACard(Jugador px1) {
        /*This take a card from pila and creates an event
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Jugador px1,px2
         */
         pxa=px1; //actualizo el jugador actual
         CasillaExtraSimple x =barajaDeEventos.pop();  //toma una carta de la pila
         actual= crearCarta();
         actual.setDescripcion("Has activado un evento! "+"\n"+ "el evento activado es"+"\n"+" de tipo :" + x.getDato());

        if(barajaDeEventos.ultimo==null){
            Barajar();
        }

        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {
                    Platform.runLater(()-> magicfuntion(px1,x));
                    }
                },
                3000
        );


        return actual;
    }
    private  Jugador dameUnJugador()
    {
            Jugador aux;
            aux= (Jugador) ((CasillaExtraSimple) jugadores.giveMe((int) (Math.random()*jugadores.Size()))).getDato();
            if(aux==pxa)
                {
                    while (aux==pxa) {
                        aux = (Jugador) ((CasillaExtraSimple) jugadores.giveMe((int) (Math.random() * jugadores.Size()))).getDato();
                    }
                }
            return aux;
        }

    public void Dev(Jugador px1)
    {
        try {
            ///esta función es especial, llama al metodo que contiene la carta es casi magia

            Method method = EventManager.class.getMethod("telAfaseD",Jugador.class );
            method.invoke(this,px1);
        }catch (Exception ignored){
        }
    }
    private void magicfuntion(Jugador px1, CasillaExtraSimple x)
    {
            try {
                ///esta función es especial, llama al metodo que contiene la carta es casi magia
                System.out.println(x.getDato());
                Method method = EventManager.class.getMethod((String) x.getDato(),Jugador.class );
                method.invoke(this,px1);
            }catch (Exception ignored){
            }
        }
    public void duelo(Jugador px1, Jugador px2) //Aparece 10 veces.
    {
        try {


            int numero = (int) (Math.random() * 3);
            Stage ventana = new Stage();
            if (px2 == null) {
                px2 = dameUnJugador();
            }
            if (numero == 0) {
                CowBoys vaqueros = new CowBoys();
                vaqueros.setPxs(px1, px2);
                vaqueros.start(ventana);
            }
            if (numero == 1) {
                Force force = new Force();
                force.setPxs(px1, px2);
                force.start(ventana);
            }
            if (numero == 2) {
                HighestCard highestCard = new HighestCard();
                highestCard.setPxs(px1, px2);
                highestCard.start(ventana);
            }
        }
        catch (Exception e) {
        }
        }


    public void robarMonedas(Jugador px11)//Aparece 10 veces
    {
        robando=true;
    }

    public void regalarMonedas(Jugador px11)//
    {
            int regalo = ((int)(Math.random()*3))*4;
            px11.setMonedas(px11.getMonedas()-regalo);
            for(int i=0;i<jugadores.Size();i++)
                {
                    Jugador aux = dameUnJugador();
                    aux.setMonedas(aux.getMonedas()+regalo);
                }
        }
    public void perderUnaEstrella(Jugador px11)//
    {
        if (px11.getEstrellas()>0){
            px11.setEstrellas(px11.getEstrellas()-1);
            Jugador j=dameUnJugador();
            j.setEstrellas(j.getEstrellas() + 1);

        }
    }
    public void ganar2Estrellas(Jugador px11)// Aparece 3 veces.
    {
        px11.setEstrellas(px11.getEstrellas()+2);
    }
    public void ganar5Estrellas(Jugador px11)//Aparece una vez.
    {
        px11.setEstrellas(px11.getEstrellas()+5);

    }



    private void teletransporte(Jugador px, Object nuevaUbicacion)//Aparece 10 veces.
        {
            px.setUbicacionEnElMapa(nuevaUbicacion);  //nuevaUbicación es caminoPrincipal.giveMe(px.numeroRandom())
            px.moverseA((CasillaSimple) px.getUbicacionEnElMapa());
        }


    public void cambioDeLugares(Jugador px1)//Aparece 5 veces.//
        {
            Jugador px2= dameUnJugador();
            // Yo soy un asistente que trabaja tras el telón asegurandose de qué metodos requieren los actores( eventos)
            Object ubicacion1 = px1.getUbicacionEnElMapa();
            px1.moverseA((CasillaSimple) px2.getUbicacionEnElMapa());
            px2.moverseA((CasillaSimple) ubicacion1);

        }

    public void telAfaseD(Jugador px)
        {   CasillaDoble i= (CasillaDoble) faseD.giveMe((int) (Math.random()*faseD.Size()));
            while (i.getTipo().equals("Di")){
                i= (CasillaDoble) faseD.giveMe((int) (Math.random()*faseD.Size()));

            }
            teletransporte(px,i);
        }
    public void telRamdom(Jugador px11)
        {
            teletransporte( px11,camPrincipal.giveMe((int) (Math.random()*37)));
        }


    public void robarEstrella(Jugador px11)//Aparece 3 veces
    {
        px11.setEstrellas(px11.getEstrellas()+1);
        Jugador pxAux=dameUnJugador();
        pxAux.setEstrellas(pxAux.getEstrellas()-1);
    }

    public void generarJuego(Jugador px,int numero)  {


        Platform.runLater(()->{
                    try {
                        minijuegoAbierto=true;
                        Stage ventana= new Stage();
                        if (numero == 0) {
                            StopMisil stopMisil= new StopMisil();
                            stopMisil.setPx(px);
                            stopMisil.start(ventana);
                            stopMisil.setEventManager(this);
                        }
                        if(numero==1)
                        {
                            EscribirPalabra escribirPalabra= new EscribirPalabra();
                            escribirPalabra.setPx(px);
                            escribirPalabra.start(ventana);
                            escribirPalabra.setEventManager(this);
                        }
                        if(numero==2)
                        {

                            Minesweeper minesweeper= new Minesweeper();
                            minesweeper.setPx(px);
                            minesweeper.start(ventana);
                            minesweeper.setEventManager(this);
                        }
                    }catch (Exception ignored){}

        });
    }
    private void robarA(Jugador benefiado, Jugador victima)
    { int regalo = (int)(Math.random()*3);
        benefiado.setMonedas(benefiado.getMonedas()+regalo);
        victima.setMonedas(victima.getMonedas()-regalo);


    }

    @Override
    public void Update() {



    }

    @Override
    public void Update(int puntaje, Jugador jugador) {
        minijuegoAbierto=false;
        if(maxPuntaje< puntaje)
            {
                segundo = ganador;
                segundoPuntaje = maxPuntaje;
                maxPuntaje=puntaje;
                ganador=jugador;

            }
        else{
            if (segundoPuntaje > puntaje) {
                tercero = segundo;
                tercerPuntaje = segundoPuntaje;
                tercero = jugador;
                tercerPuntaje = puntaje;


            }
            else{
                segundo = tercero;
                segundoPuntaje = tercerPuntaje;
                segundo = jugador;
                segundoPuntaje = puntaje;
            }

        }
    }
}
