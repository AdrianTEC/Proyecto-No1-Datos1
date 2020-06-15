package MiniJuegos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Partida;


public class Mina {
    private ImageView imagen;
    private String tipo;
    private Image exp;


    public Mina() {
        imagen = new ImageView(new Image("Imagenes/Minijuegos/bomb.png"));
        imagen.setFitHeight(50);
        imagen.setFitWidth(50);
        exp = new Image("Imagenes/Minijuegos/Expl.png");
        tipo = "inactiva";
    }
    public void encoger()
        {/*This funtion reduces mina size by little time
         *@author Adrián González
         *@Version x/06/2020
         * @param nothing
         *@returns
         */

            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {


                            imagen.setScaleX(0.5);
                            imagen.setScaleY(0.5);


                        }
                    },
                    200
            );
            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {


                            imagen.setScaleX(1);
                            imagen.setScaleY(1);


                        }
                    },
                     3000
            );
        }
    public void setTipo (String tipox){
        tipo = tipox;
    }
    public String getTipo () {
        return tipo;
    }

    public ImageView getImagen () {
        return imagen;
    }

    public void explotar(){
        imagen.setImage(exp);
        imagen.setFitWidth(70);
        imagen.setFitHeight(70);
        Partida.reproducirSonido("exp");
    }


}
