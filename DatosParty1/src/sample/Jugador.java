package sample;


import Listas.CasillaDoble;
import Listas.CasillaSimple;
import javafx.scene.image.ImageView;

public class Jugador
{   // ATRIBUTOS DEL JUGADOR
    public  String nombre;
    public  float posX;  //estos valores se consideran que deben de ser publicos para luego utilizarlos en eventos (QUIZA)
    public  float posY;
    public  int estrellas;
    public  int monedas;
    public ImageView imagen;
    public Object ubicacionEnElMapa;

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
            moverAcoordenada(casillita.posicion[0],casillita.posicion[1]);


        }
    public  void moverseA(CasillaDoble casillita)

    {

        /*This funtion calls the funtion moverseAcoordenada
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param and object type of CasillaDoble
         */
        ubicacionEnElMapa=casillita;// ahora la nueva casilla donde está el jugador es la introducida
        moverAcoordenada(casillita.posicion[0],casillita.posicion[1]);



    }

    private void moverAcoordenada(float x, float y)
        {
            posY=y;
            posX=x;
            imagen.setLayoutX(posX);
            imagen.setLayoutY(posY);

        }


}
