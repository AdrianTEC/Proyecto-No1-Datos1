package sample;

import Listas.CasillaSimple;
import MiniJuegos.StopMisil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EventManager extends Tablero{
    // Yo soy un asistente que trabaja tras el telón asegurandose de qué metodos requieren los actores( eventos)
    private Object ubicacion1;



    private void duelo()//Aparece 10 veces.
    {}
    private void robarMonedas()//Aparece 10 veces
    {}
    private void regalarMonedas()//
    {}
    private void perderUnaEstrella()//
    {}
    private void ganar2Estrellas()// Aparece 3 veces.
    {}
    private void ganar5Estrellas()//Aparece una vez.
    {}
    private void robarEstrella()//Aparece 3 veces
    {}

    //
    public void teletransporte(Jugador px, Object nuevaUbicación)//Aparece 10 veces.
    {
        px.setUbicacionEnElMapa(nuevaUbicación);
        px.moverseA((CasillaSimple) px.getUbicacionEnElMapa());
    }


    public void cambioDeLugares(Jugador px1, Jugador px2)//Aparece 5 veces.//
    {
        ubicacion1 = px1.getUbicacionEnElMapa();
        px1.moverseA((CasillaSimple) px2.getUbicacionEnElMapa());
        px2.moverseA((CasillaSimple) ubicacion1);

    }





    public void generarJuego() throws Exception {
        int numero = (int) (Math.random() * 3);
        Stage ventana= new Stage();
        if (numero == 0) {
            StopMisil stopMisil= new StopMisil();
            stopMisil.start(ventana);
        }

    }

}
