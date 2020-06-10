package sample;

import Listas.*;
import MiniJuegos.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventManager extends Baraja {
    private String[] eventos;
    private Pila barajaDeEventos;
    private String[] memoriaDeInicio;
    private ListaCircular camPrincipal;
    private ListaLineal faseD;
    private ListaLineal jugadores;
    private Boolean robando;
    private Jugador pxa;
    private Carta actual;

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

        memoriaDeInicio=eventos;
        Barajar();
        robando=false;
        pxa= new Jugador();
        actual= new Carta();
    }

    public void setCamPrincipal(ListaCircular camPrincipal) {
        this.camPrincipal = camPrincipal;
    }

    public void setFaseD(ListaLineal faseD) {
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

    public void takeACard(Jugador px1,Jugador px2) {
        /*This take a card from pila and creates an event
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Jugador px1,px2
         */
        pxa=px1;

        CasillaExtraSimple x =barajaDeEventos.peek();  //toma una carta de la pila


         actual= crearCarta();
         actual.setDescripcion((String) x.getDato());

        if(barajaDeEventos.ultimo==null){
            Barajar();
        }
        magicfuntion(px1,px2,x);

}

    private void magicfuntion(Jugador px1,Jugador px2, CasillaExtraSimple x)
        {
            try {
                ///esta función es especial, llama al metodo que contiene la carta es casi magia
                System.out.println(x.getDato());
                Method method = EventManager.class.getMethod((String) x.getDato(),Jugador.class ,Jugador.class);
                method.invoke(this,px1,px2);
            }catch (NoSuchMethodException e){
                System.out.println(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    public void duelo(Jugador px1, Jugador px2) throws Exception//Aparece 10 veces.
    {
        int numero = (int) (Math.random() * 3);
        Stage ventana= new Stage();
        if (numero == 0) {
            CowBoys vaqueros= new CowBoys();
            vaqueros.setPxs(px1,px2);
            vaqueros.start(ventana);
        }
        if(numero==1)  {
            Force force= new Force();
            force.setPxs(px1,px2);
            force.start(ventana);
        }
        if(numero==2)
        {
            HighestCard highestCard= new HighestCard();
            highestCard.setPxs(px1,px2);
            highestCard.start(ventana);
        }


    }
    public void robarMonedas(Jugador px11, Jugador px22)//Aparece 10 veces
    {
        robando=true;
    }
    private void robarA(Jugador benefiado, Jugador victima)
        { int regalo = (int)(Math.random()*3);
        benefiado.setMonedas(benefiado.getMonedas()+regalo);
        victima.setMonedas(victima.getMonedas()-regalo);


        }
    public void regalarMonedas(Jugador px11, Jugador px22)//
    {
        int regalo = ((int)(Math.random()*3))*4;
        px11.setMonedas(px11.getMonedas()-regalo);
        for(int i=0;i<jugadores.Size();i++)
            {
                Jugador aux= (Jugador) ((CasillaExtraSimple) jugadores.giveMe(i)).getDato();
                aux.setMonedas(aux.getMonedas()+regalo);
            }
    }
    public void perderUnaEstrella(Jugador px11, Jugador px22)//
    {
        if (px11.getEstrellas()>0){
            px11.setEstrellas(px11.getEstrellas()-1);
            px22.setEstrellas(px22.getEstrellas()+1);
        }
    }
    public void ganar2Estrellas(Jugador px11,Jugador px22)// Aparece 3 veces.
    {
        px11.setEstrellas(px11.getEstrellas()+2);
    }
    public void ganar5Estrellas(Jugador px11, Jugador px)//Aparece una vez.
    {
        px11.setEstrellas(px11.getEstrellas()+5);

    }
    public void robarEstrella(Jugador px11, Jugador px22)//Aparece 3 veces
    {
        px11.setEstrellas(px11.getEstrellas()+1);
        px22.setEstrellas((px22.getEstrellas()-1));
    }

    //

    private void teletransporte(Jugador px, Object nuevaUbicación)//Aparece 10 veces.
        {
            px.setUbicacionEnElMapa(nuevaUbicación);  //nuevaUbicación es caminoPrincipal.giveMe(px.numeroRandom())
            px.moverseA((CasillaSimple) px.getUbicacionEnElMapa());
        }


    public void cambioDeLugares(Jugador px1, Jugador px2)//Aparece 5 veces.//
        {
            // Yo soy un asistente que trabaja tras el telón asegurandose de qué metodos requieren los actores( eventos)
            Object ubicacion1 = px1.getUbicacionEnElMapa();
            px1.moverseA((CasillaSimple) px2.getUbicacionEnElMapa());
            px2.moverseA((CasillaSimple) ubicacion1);

        }

    public void telAfaseD(Jugador px, Jugador px2)
        {
            teletransporte(px,faseD.giveMe(1) );
        }
    public void telRamdom(Jugador px11,Jugador px22)
        {
            teletransporte( px11,camPrincipal.giveMe((int) (Math.random()*37)));
        }
    public void generarJuego(Jugador px) throws Exception {
        int numero = (int) (Math.random() * 3);
        Stage ventana= new Stage();
        if (numero == 0) {
            StopMisil stopMisil= new StopMisil();
            stopMisil.start(ventana);
        }
        if(numero==1)
            {
                EscribirPalabra escribirPalabra= new EscribirPalabra();
                escribirPalabra.setPx(px);
                escribirPalabra.start(ventana);
            }
        if(numero==2)
        {

            Minesweeper minesweeper= new Minesweeper();
            minesweeper.setPx(px);
            minesweeper.start(ventana);
        }

    }

}
