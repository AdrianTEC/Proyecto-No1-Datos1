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
        {
            /*This funtion returns the Casilla with the inserted index
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param Casilla
             */
            CasillaSimple casillaSimpleActual =(CasillaSimple) primero;
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
    public void aplicarPropiedades(String[] propiedades)
        {
            Object apuntador= new Object();
            apuntador=primero;
            int i =0;
            while (apuntador!= null )
                {
                    if (apuntador instanceof CasillaSimple)
                        {
                             ((CasillaSimple) apuntador).setTipo(propiedades[i]);
                             apuntador=((CasillaSimple) apuntador).getSiguiente();
                            if(((CasillaSimple) apuntador).getSiguiente()== primero)
                            {
                                apuntador=null;
                            }
                        }
                    if (apuntador instanceof CasillaDoble)
                    {
                        ((CasillaDoble) apuntador).setTipo(propiedades[i]);
                        apuntador=((CasillaDoble) apuntador).getSiguiente();
                        if(((CasillaDoble) apuntador).getSiguiente()== primero)
                        {
                            apuntador=null;
                        }
                    }
                    i++;

                }


        }
}
