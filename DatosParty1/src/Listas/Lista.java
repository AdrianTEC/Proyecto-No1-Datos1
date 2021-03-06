package Listas;

public abstract class Lista
{

    public Object ultimo;
    public Object primero;
    public int tamano;
//////////////////////////////////////////////////////////////////////////
////AQUI HAY UN EJEMPLO DE OVERLOAD ///////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////


    //ABSTRACCIÓN PARA NOSOTROS IMPLEMENTAR EL CÓDIGO DE LISTAS LO VEMOS COMO UN CONCEPTO ABSTRACTO
    //EN EL CUAL SE SIMPLIFICÓ A NIVEL DE CÓDIGO LA FORMA EN LA QUE SE DESARROLLAN Y RELACIONAN SUS COMPONENTES



////POLIMORFISMO SE ENCUENTRA EN LAS CLASES QUE HEREDAN DE LISTA Y QUE MODIFICAN SUS MÉTODOS TAL COMO LISTA CIRCULAR
    public int Size()
        {
            return tamano;
        }

    public void ingresarNodo(CasillaSimple casillita)
    {/*This adds a new Casilla to the  list
     *@author  Adrián González
     *@Version 17/05/20
     * @param CasillaSimple casillita
     *
     */
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
    {/*This adds a new Casilla to the  list
     *@author  Adrián González
     *@Version 17/05/20
     * @param CasillaDoble casillita
     *
     */
    {
        if(ultimo ==null)

        {
            ultimo =casillita;
            ((CasillaDoble) ultimo).setINDEX(tamano);
            tamano+=1;
            primero= ultimo;
        }

        else
        {   casillita.setAnterior(ultimo);
            ((CasillaDoble) ultimo).setSiguiente(casillita);
            ultimo =casillita;
            ((CasillaDoble) ultimo).setINDEX(tamano);
            tamano+=1;

        }
    }
}

    public void ingresarNodo(CasillaExtraSimple casillita)
    {/*This adds a new Casilla to the  list
     *@author  Adrián González
     *@Version 17/05/20
     * @param CasillaExtraSimple casillita
     *
     */
        {
            if(ultimo ==null)

            {
                ultimo =casillita;
                ((CasillaExtraSimple) ultimo).setINDEX(tamano);

                tamano+=1;
                primero= ultimo;
            }

            else
            {
                ((CasillaExtraSimple) ultimo).setSiguiente(casillita);
                ultimo =casillita;
                ((CasillaExtraSimple) ultimo).setINDEX(tamano);

                tamano+=1;

            }
        }
    }

    public Object giveMe(int ind)
        {

            /*This funtion returns the Casilla with the inserted index
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param Casilla
             */
            Object puntero = primero;
            int i =0;
            while (i!=ind)
                {

                    if(puntero instanceof  CasillaExtraSimple)
                    {
                        if(((CasillaExtraSimple)puntero).getINDEX()==ind)
                        {
                            return puntero;
                        }
                        puntero= ((CasillaExtraSimple) puntero).getSiguiente();

                        i++;
                    }

                    if(puntero instanceof  CasillaSimple)
                        {
                            if(((CasillaSimple)puntero).getINDEX()==ind)
                                {
                                    return puntero;
                                }
                            puntero= ((CasillaSimple) puntero).getSiguiente();

                            i++;
                        }
                    if(puntero instanceof  CasillaDoble)
                        {
                            if(((CasillaDoble)puntero).getINDEX()==ind) {
                                return puntero;
                            }
                            puntero=((CasillaDoble) puntero).getSiguiente();
                            i++;
                        }
                }
            return puntero;
        }

    public void aplicarPropiedades(String[] propiedades)
        {/*This applies string value to a casilla tipo
         *@author  Adrián González
         *@Version 20/05/20
         * @param String[] propiedades
         *
         */
            //esta función le va a dar identidad a las celdas "rojo" "verde" "dorado"
            Object apuntador; //nesecito un objeto para explorar la lista
            apuntador=primero; //empezaré en el primero
            int i =0;
            while (apuntador!= null )
                {

                            if (apuntador instanceof CasillaSimple) {


                                    ((CasillaSimple) apuntador).setTipo(propiedades[i]);
                                    apuntador = ((CasillaSimple) apuntador).getSiguiente();
                                    if(apuntador!=null  ) {
                                        if (apuntador == primero) {
                                            apuntador = null;
                                        }
                                    }
                            }

                    i++;

                }


        }

    public  void remplazarCasillaSimple(int index)
        {/*This replaces a Casilla Simple to Casilla Doble
         *@author  Adrián González
         *@Version 23/05/20
         * @param index
         *
         */
            CasillaDoble remplazo = new CasillaDoble();
           if(giveMe(index)instanceof  CasillaSimple)
            {
                remplazo.setSiguiente(((CasillaSimple)giveMe(index)).getSiguiente());//le roba el siguiente
                remplazo.setINDEX(((CasillaSimple)giveMe(index)).getINDEX());
                remplazo.setPosicion(((CasillaSimple)giveMe(index)).getPosicion());
                ((CasillaSimple)giveMe(index)).setSiguiente(null);//lo deja sin cómo seguir
                remplazo.setTipo(((CasillaSimple)giveMe(index)).getTipo());//le roba la identidad
                ((CasillaSimple)giveMe(index-1)).setSiguiente(remplazo);//le roba su unico amigo

            }
            else {
               System.out.println("No se puede cambiar esta casilla");

            }

        }



}
