package sample;

import MiniJuegos.StopMisil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EventManager {
    // Yo soy un asistente que trabaja tras el telón asegurandose de qué metodos requieren los actores( eventos)



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
    private void teletransporte()//Aparece 10 veces.
    {}
    private void cambioDeLugares()//Aparece 5 veces.
    {}





    public void generarJuego() throws Exception {
        int numero = (int) (Math.random() * 3);
        Stage ventana= new Stage();
        if (numero == 0) {
            StopMisil stopMisil= new StopMisil();
            stopMisil.start(ventana);
        }

    }

}
