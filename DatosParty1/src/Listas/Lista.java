package Listas;

public class Lista
{

    public Object ultimo;
    public Object primero;
    public int tamano;
//////////////////////////////////////////////////////////////////////////
////AQUI HAY UN EJEMPLO DE OVERLOAD ///////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
    public void ingresarNodo(CasillaSimple casillita)
    {
        if(ultimo ==null)

        {
            ultimo =casillita;
             ((CasillaSimple) ultimo).setINDEX(tamano);
            tamano+=1;
            primero= ultimo;
        }

        else
        {
            ((CasillaSimple) ultimo).setSiguiente(casillita);
           ultimo =casillita;
            ((CasillaSimple) ultimo).setINDEX(tamano);
            tamano+=1;

        }
    }
    public void ingresarNodo(CasillaDoble casillita)
        {
            {
                if(ultimo ==null)

                {
                    ultimo =casillita;
                    ((CasillaDoble) ultimo).setINDEX(tamano);
                    tamano+=1;
                    primero= ultimo;
                }

                else
                {
                    ((CasillaDoble) ultimo).setSiguiente(casillita);
                    ultimo =casillita;
                    ((CasillaDoble) ultimo).setINDEX(tamano);
                    tamano+=1;

                }
            }
        }

    public CasillaSimple giveMe(int ind)
        /*This funtion returns the Casilla with the inserted index
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Casilla
         */
        {   CasillaSimple casillaSimpleActual =(CasillaSimple) primero;
            if(ind <= tamano) {
                while (casillaSimpleActual.getINDEX() != ind) {
    
                    casillaSimpleActual = (CasillaSimple) casillaSimpleActual.getSiguiente();
                }
                return casillaSimpleActual;
            }
            else
            {
                return null;
            }
    
    
        }

}
