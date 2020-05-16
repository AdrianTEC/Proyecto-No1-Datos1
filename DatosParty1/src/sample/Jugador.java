package sample;


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
    public CasillaSimple ubicacionEnElMapa;

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

            /*This funtion moves the player to other slot position
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param and object type of Casilla
             */


            // Extraigo los componentes de posicion
            posX=casillita.posicion[0];
            posY=casillita.posicion[1];


            ubicacionEnElMapa=casillita;// ahora la nueva casilla donde está el jugador es la introducida
            imagen.setLayoutX(posX);
            imagen.setLayoutY(posY);


            System.out.println("Se movió a : "+String.valueOf(posX)+" ,"+String.valueOf(posY));
            System.out.println("El index de la casilla es:"+ubicacionEnElMapa.INDEX);
        }





}
