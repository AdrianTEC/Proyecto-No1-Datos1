package Listas;

public class Lista
{

    public CasillaSimple ultimo;
    public CasillaSimple primero;
    public int tamano;

    public void ingresarNodo(CasillaSimple casillita)
    {
        if(ultimo ==null)

        {
            ultimo =casillita;
            ultimo.INDEX=tamano;
            tamano+=1;
            primero= ultimo;
        }

        else
        {
            ultimo.siguiente=casillita;
            ultimo =casillita;
            ultimo.INDEX=tamano;
            tamano+=1;

        }
    }
    public CasillaSimple giveMe(int ind)
        /*This funtion returns the Casilla with the inserted index
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Casilla
         */
        {   CasillaSimple casillaSimpleActual =primero;
            if(ind <= tamano) {
                while (casillaSimpleActual.INDEX != ind) {
    
                    casillaSimpleActual = casillaSimpleActual.siguiente;
                }
                return casillaSimpleActual;
            }
            else
            {
                return null;
            }
    
    
        }

}
