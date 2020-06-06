package sample;

import MiniJuegos.StopMisil;

import javafx.stage.Stage;

public class EventManager {
    // Yo soy un asistente que trabaja tras el telón asegurandose de qué metodos requieren los actores( eventos)



    private void duelo()//Aparece 10 veces.  ///ADRIAN
    {}
    private void robarMonedas()//Aparece 10 veces //YORDAN
    {}
    private void regalarMonedas()//  //YORDAN
    {}
    private void perderUnaEstrella()// //YORDAN
    {}
    public void ganar2Estrellas(Jugador px)// Aparece 3 veces. //YORDAN


    {
        //px+1 estella
    }
    private void ganar5Estrellas()//Aparece una vez.//YORDAN

    {}
    private void robarEstrella()//Aparece 3 veces
    {}
    private void teletransporte()//Aparece 10 veces. /// ANDRES
    {}
    private void cambioDeLugares()//Aparece 5 veces. ///
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
