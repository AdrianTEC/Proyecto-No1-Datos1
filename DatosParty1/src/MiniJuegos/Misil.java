package MiniJuegos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Partida;


public class Misil implements Observado {


    private ImageView imagen = new ImageView(new Image("Imagenes/Minijuegos/misil.png"));
    private Image exp= new Image("Imagenes/Minijuegos/Expl.png");
    private int MaximoY=600;
    private float Velocidad=10;
    private boolean sinExplotar= true;
    private Observador observer;
    public void setVelocidad(float velocidad) {
        Velocidad = velocidad;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void explotar(){
        imagen.setImage(exp);
        sinExplotar=false;
        Partida.reproducirSonido("exp");
    }

    public void myObserverIs(Observador o)
        {
            observer=o;
        }
    public void moverse(Double puntero){



        if(puntero<MaximoY && sinExplotar)
            {    puntero=imagen.getLayoutY()+Velocidad;
                 imagen.setLayoutY(puntero);

                Double finalPuntero = puntero;
                new java.util.Timer().schedule(

                        new java.util.TimerTask() {
                            @Override
                            public void run() {


                                moverse(finalPuntero);



                            }
                        },
                        50
                );




            }
        else
        {explotar();
        if(puntero>MaximoY-100){
            System.out.println("explosion");
         notificar();}

        }

    }


    @Override
    public void notificar() {
        observer.Update();
    }
}
