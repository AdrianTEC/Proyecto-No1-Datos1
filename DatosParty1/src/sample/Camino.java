package sample;


import Listas.CasillaDoble;
import Listas.CasillaSimple;
import Listas.ListaCircular;

public class Camino
{
    public float[][] matrizPosiciones ;
    public ListaCircular casillas;
    public boolean dobleEnlaze; ///este boleano me permite decidir si crearé la lista como una normal o como una doble enlazada

    public void  convertirMatrizAListaCircular()
        {
         /*This funtion is in charge of converting "matrizPosiciones" to Casilla and adding this to "casillas" round list
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */

         //pueden haber dos tipos de listas circulares, las normales con casillas simples o dobles

            casillas= new ListaCircular();
            if (!dobleEnlaze)
                {
                    for(float[] i : matrizPosiciones)
                    {
                        CasillaSimple nuevo= new CasillaSimple();
                        nuevo.setPosicion(i);
                        casillas.ingresarNodo(nuevo);
                    }
                }
            else{
                for(float[] i : matrizPosiciones)
                {
                    CasillaDoble nuevo= new CasillaDoble();
                    nuevo.setPosicion(i);
                    casillas.ingresarNodo(nuevo);
                }
            }


        }








}
