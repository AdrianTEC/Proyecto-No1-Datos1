package MiniJuegos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BarraTiempo implements Observado
{

    private int tiempo; //en milisegundos
    private ImageView barra= new ImageView(new Image("Imagenes/Minijuegos/TimeBar.png"));
    private int tamano;
    private Observador observador;
    private int tamano_original;
    private int tiempo_origianl;

    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    private   ImageView barraContainer = new ImageView(new Image("Imagenes/Minijuegos/TimeContainer.png"));


    private int tiempoParaActualizarse=100;

    public ImageView getBarra() {
        return barra;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
        tiempo_origianl=tiempo;



    }
    public void generarContenedor()
        {
            barraContainer.setLayoutX(barra.getLayoutX());
            barraContainer.setLayoutY(barra.getLayoutY());
            barraContainer.setFitWidth(barra.getFitWidth());
            barraContainer.setFitHeight(barra.getFitHeight());
        }
    public ImageView getBarraContainer() {
        return barraContainer;
    }

    public void setTamano(int tamano, int tamanoY) {
        this.tamano = tamano;
        tamano_original=tamano;
        barra.setFitWidth(tamano);
        barra.setFitHeight(tamanoY);
    }

    public void encoger()
    {/*This funtion reduce ImageView size
     *@author Adrián González
     *@Version 12/06/2020
     * @param nothing
     *@returns null
     */

        tiempo-=tiempoParaActualizarse;
        if(tamano>0) {
            tamano = tamano_original * tiempo / tiempo_origianl;//regla de 3
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            barra.setFitWidth(tamano);
                                encoger();
                        }
                    },
                    tiempoParaActualizarse
            );
        }
        else
            {
                notificar();
                barra.setFitWidth(1);
            }
    }


    @Override
    public void notificar() {
        observador.Update();
    }
}
