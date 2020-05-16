package Listas;

public class ListaSimple extends Lista
    {

        public ListaSimple()
            {
                ultimo=null;
                tamano=0;
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
