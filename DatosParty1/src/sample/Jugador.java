package sample;


import Listas.CasillaDoble;
import Listas.CasillaSimple;
import javafx.scene.image.ImageView;

public class Jugador
{   // ATRIBUTOS DEL JUGADOR
    private   String nombre;
    private   float posX;  //estos valores se consideran que deben de ser publicos para luego utilizarlos en eventos (QUIZA)
    private   float posY;
    private   int estrellas;
    private   int monedas;
    private ImageView imagen;
    private Object ubicacionEnElMapa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public Object getUbicacionEnElMapa() {
        return ubicacionEnElMapa;
    }



    public Jugador ()
        {
         /*This funtion is the constructor of the class
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
            estrellas= 0;
            monedas=0;
            nombre="";
            posY=0;
            posX=0;


        }


    public  void moverseA(CasillaSimple casillita)

        {

            /*This funtion calls the funtion moverseAcoordenada
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param and object type of CasillaSimple
             */

            ubicacionEnElMapa=casillita;// ahora la nueva casilla donde está el jugador es la introducida

            System.out.println(casillita.getTipo());
            moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);


        }
    public  void moverseA(CasillaDoble casillita)

    {

        /*This funtion calls the funtion moverseAcoordenada
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param and object type of CasillaDoble
         */
        ubicacionEnElMapa=casillita;// ahora la nueva casilla donde está el jugador es la introducida
        moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);



    }

    private void moverAcoordenada(float x, float y)
        {
            posY=y;
            posX=x;
            imagen.setLayoutX(posX);
            imagen.setLayoutY(posY);

        }


}
