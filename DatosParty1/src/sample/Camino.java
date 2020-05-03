package sample;


public class Camino
{
    private float[][] matrizPosiciones ={{470,230},{470,273},{470,316},{470,359},{470,402} ,{470,445},{470,488},{427,531} ,{375,531}
                                        ,{333,531},{291,531},{246,531,},{204,531},{162,531},{110,531},{70,494},{70,445},{70,402},{70,359},{70,316},{70,273},{70,230} };
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








}
