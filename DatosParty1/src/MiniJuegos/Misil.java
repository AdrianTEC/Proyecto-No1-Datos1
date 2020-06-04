package MiniJuegos;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Misil  {
    private ImageView imagen = new ImageView(new Image("Imagenes/Minijuegos/misil.png"));
    private int MaximoY;
    private int Velocidad;


    public void explotar(){
        System.out.println("kabun!");
    }

    public void moverse(Double puntero){


        imagen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
            }
        });

        if(puntero<MaximoY)
            {    puntero=imagen.getLayoutY()+Velocidad;
                 imagen.setLayoutY(puntero);
                 moverse(puntero);


            }
        else
        {explotar();}

    }





}
