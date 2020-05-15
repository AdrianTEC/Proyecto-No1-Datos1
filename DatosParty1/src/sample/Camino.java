package sample;


public class Camino
{
    public float[][] matrizPosiciones ;
    public ListaCircular casillas;
    public void  convertirMatrizAListaCircular()
        {
         /*This funtion is in charge of converting "matrizPosiciones" to Casilla and adding this to "casillas" round list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */

            casillas= new ListaCircular();
                for(float[] i : matrizPosiciones)
                    {
                        Casilla nuevo= new Casilla();
                        nuevo.posicion=i;
                        casillas.ingresarNodo(nuevo);
                    }


        }


        //prueba123

        //las drogas son malas
        //kkkk
        //kekw







}
