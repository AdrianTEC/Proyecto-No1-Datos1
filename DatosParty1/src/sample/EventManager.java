package sample;

import Listas.CasillaExtraSimple;
import Listas.CasillaSimple;
import Listas.Pila;
import MiniJuegos.StopMisil;

import javafx.event.Event;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventManager extends Tablero{
    private String[] eventos;
    private Pila barajaDeEventos;
    private String[] memoriaDeInicio;


    public EventManager() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        barajaDeEventos= new Pila();
        eventos=new String[]{"duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo","duelo",  //DUELO
                            "robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas","robarMonedas", //ROBAR MONEDAS
                            "regalarMonedas","regalarMonedas","regalarMonedas","regalarMonedas","regalarMonedas", //Regalar Monedas
                            "perderUnaEstrella","perderUnaEstrella","perderUnaEstrellaPE","perderUnaEstrella","perderUnaEstrella",//PERDER ESTRELLA
                            "ganar2Estrellas","ganar2Estrellas","ganar2Estrellas",//Ganar 2 estrellas
                            "ganar5Estrellas",//Ganar 5 estrellass
                            "robarEstrella","robarEstrella","robarEstrella", //RobarEstrella
                           // "teletransporte","teletransporte","teletransporte","teletransporte","teletransporte","teletransporte","teletransporte","teletransporte","teletransporte","teletransporte", //Teletransporte
                           // "cambioDeLugares","cambioDeLugares","cambioDeLugares","cambioDeLugares","cambioDeLugares"
        };  //IntercambioDeLugares

        memoriaDeInicio=eventos;
        Barajar();
        takeACard(new Jugador(),null);
    }
    public void Barajar()   {


        eventos=memoriaDeInicio;
        for(int i=0; i< eventos.length;i++) {eventos[i]=eventos[(int) (Math.random()*eventos.length)]; }
        barajaDeEventos.especialPush(eventos);


    }

    public void takeACard(Jugador px1, Jugador px2) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CasillaExtraSimple x =barajaDeEventos.peek();  //toma una carta de la pila
        ///esta función es especial, llama al metodo que contiene la carta es casi magia
        Method method = EventManager.class.getMethod(x.getDato(),Jugador.class ,Jugador.class);
        method.invoke(this,px1,px2);
}
    public void duelo(Jugador px1, Jugador px2)//Aparece 10 veces.
    {
        System.out.println("hola");
    }
    public void robarMonedas(Jugador px11, Jugador px22)//Aparece 10 veces
    {
        int cant1 = 10;
        while (cant1>0) {
            int robar = (int) (Math.random() * 3);
            px11.setMonedas(px11.getMonedas() + robar);
            px22.setMonedas(px22.getMonedas() - robar);
            cant1 = cant1 - 1;
            System.out.println("hola");
        }
    }
    public void regalarMonedas(Jugador px11, Jugador px22)//
    {
        int regalo = ((int)(Math.random()*3))*4;
        px11.setMonedas(px11.getMonedas()-regalo);
        px22.setMonedas(px22.getMonedas()+regalo);
        System.out.println("hola");
    }
    public void perderUnaEstrella(Jugador px11, Jugador px22)//
    {
        if (px11.getEstrellas()>0){
            px11.setEstrellas(px11.getMonedas()-1);
            px22.setEstrellas(px22.getMonedas()+1);
        }
        System.out.println("hola");
    }
    public void ganar2Estrellas(Jugador px11)// Aparece 3 veces.
    {
        int cant2 = 3;
        while (cant2 > 0) {
            px11.setEstrellas(px11.getEstrellas() + 2);
            cant2 = cant2 - 1;
            System.out.println("hola");
        }
    }
    public void ganar5Estrellas(Jugador px11, Jugador px22)//Aparece una vez.
    {
        int cant3 = 1;
        while (cant3 > 0) {
            px11.setEstrellas(px11.getEstrellas() + 5);
            cant3 = cant3 - 1;
            System.out.println("hola");
        }
    }
    public void robarEstrella(Jugador px11, Jugador px22)//Aparece 3 veces
    {
        int cant4 = 3;
        while (cant4 > 0) {
            px11.setEstrellas(px11.getEstrellas() + 1);
            px22.setEstrellas(px22.getEstrellas() - 1);
            cant4 = cant4 - 1;
            System.out.println("hola");
        }
    }

    //
    public void teletransporte(Jugador px, Object nuevaUbicación)//Aparece 10 veces.
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





    public void generarJuego(Jugador px) throws Exception {
        int numero = (int) (Math.random() * 3);
        Stage ventana= new Stage();
        if (numero == 0) {
            StopMisil stopMisil= new StopMisil();
            stopMisil.start(ventana);
        }

    }

}
