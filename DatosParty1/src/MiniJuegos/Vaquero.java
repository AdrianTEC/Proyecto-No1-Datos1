package MiniJuegos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Partida;

public class Vaquero {

    private ImageView imagenVaquero= new ImageView();
    private ImageView sombrero= new ImageView("Imagenes/Minijuegos/sombrero.png");

    public ImageView imagencita() {
        return imagenVaquero;
    }

    public ImageView Sombrero() {
        return sombrero;
    }

    private void moverSombrero(int puntero){
        if(puntero>0) {
            puntero-=40;
            sombrero.setLayoutY(puntero);
            int finalPuntero = puntero;
            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {


                           moverSombrero(finalPuntero);



                        }
                    },
                    50
            );
        }
        else {
            Partida.reproducirSonido("win");
        }
    }

    public void dead()
    {
        moverSombrero((int) sombrero.getLayoutY());


    }

    public Vaquero(float x, float y,float posSombX, Image imagen)
        {
            imagenVaquero.setImage(imagen);
            imagenVaquero.setLayoutX(x);
            imagenVaquero.setLayoutY(y);


            sombrero.setLayoutX(x+posSombX);
            sombrero.setLayoutY(y-60);

        }





}
