package MiniJuegos;
import Listas.CasillaSimple;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Partida;


public class Mina {
    private ImageView imagen;
    private String tipo;
    private Object ubicacion;
    private float posX;  //estos valores se consideran que deben de ser publicos para luego utilizarlos en eventos (QUIZA)
    private float posY;
    private Image exp;


    public Mina() {
        /*This funtion is the constructor of the class
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */

        posY = 0;
        posX = 0;
        imagen = new ImageView(new Image("Imagenes/Minijuegos/sombrero.png"));
        exp = new Image("Imagenes/Minijuegos/Expl.png");
        tipo = "inactiva";


    }


    public void setTipo (String tipox){
        tipo = tipox;
    }
    public String getTipo () {
        return tipo;
    }

    public void setImagen (ImageView newImagen){
        imagen = newImagen;
    }

    public void setUbicacion(Object newUbicacion){
        ubicacion = newUbicacion;
    }

    public Object getUbicacion(){
        return ubicacion;
    }


    public ImageView getImagen () {
        return imagen;
    }

    public void moverseA(CasillaSimple casillita) {

        /*This funtion calls the funtion moverseAcoordenada
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param and object type of CasillaSimple
         */

        ubicacion = casillita;// ahora la nueva casilla donde está el jugador es la introducida

        System.out.println(casillita.getINDEX() + " " + casillita.getTipo());
        moverAcoordenada(casillita.getPosicion()[0], casillita.getPosicion()[1]);


    }

    private void moverAcoordenada(float x, float y) {
        posY = y;
        posX = x;
        imagen.setLayoutX(posX);
        imagen.setLayoutY(posY);

    }

    public void explotar(){
        imagen.setImage(exp);
        imagen.setFitWidth(70);
        imagen.setFitHeight(70);
        Partida.reproducirSonido("exp");
    }


}
