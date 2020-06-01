package Listas;

//NOTA ESTE CODIGO ES INSPIRADO EN EL QUE SE ENSEÑA A HACER EN EL SIGUIENTE TUTORIAL https://www.youtube.com/watch?v=J1T5VJ93SAk

public class ListaCircular extends Lista
    {

        public ListaCircular()

            {

                primero= null;      //es necesario que empiecen como un valor nulo, mas adelante se les brindará un valor
                ultimo=null;
                tamano =0;

            }
        @Override
        public void ingresarNodo(CasillaSimple x)
            {

                    /*This funtion adds new component to a list
                     *@author Adrián González Jiménez
                     *@Version 02/05/2020
                     * @param Casilla
                     */
                if (primero==null)//si no hay valores en la lista
                    {
                        primero= x;//el primer valor será el agregado
                        ((CasillaSimple) primero).setINDEX(tamano);
                        tamano +=1;
                        ultimo=primero;//y a su vez este será el último
                        ((CasillaSimple) ultimo).setINDEX(tamano);
                        ((CasillaSimple) primero).setSiguiente(((CasillaSimple) ultimo));; //por lo tanto el primero apuntará al último
                    }

                else
                    {
                        ((CasillaSimple) ultimo).setSiguiente(x);
                        ((CasillaSimple) ultimo).setINDEX(tamano);
                        tamano +=1;
                         x.setSiguiente(((CasillaSimple) primero));// el ultimo volverá a apuntar al primero
                        ultimo=x;  //el ultimo ahora será el agregado
                    }

            }

        @Override
        public void ingresarNodo(CasillaDoble x)
         {

            /*This funtion adds new component to a list
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param CasillaDoble
             */
            if (primero==null)//si no hay valores en la lista
            {
                primero= x;//el primer valor será el agregado
                ((CasillaDoble) primero).setINDEX(tamano);
                tamano +=1;
                ultimo=primero;//y a su vez este será el último
                ((CasillaDoble) ultimo).setINDEX(tamano);
                ((CasillaDoble) primero).setSiguiente(((CasillaDoble) ultimo)); //por lo tanto el primero apuntará al último
                ((CasillaDoble) primero).setAnterior(((CasillaDoble) ultimo));
            }

            else
            {
                ((CasillaDoble) ultimo).setSiguiente(x);// el que sigue despues del ultimo sera el agregado
                ((CasillaDoble) ultimo).setINDEX(tamano);
                tamano +=1;
                x.setSiguiente(((CasillaDoble) primero));// el ultimo volverá a apuntar al primero
                x.setAnterior (((CasillaDoble) ultimo));// el que va detras del agregado será el ultimo por ahora conocido
                ((CasillaDoble) primero).setAnterior(x);// el que va detras del primero ahora será el agregado

                ultimo=x;  //el ultimo ahora será el agregado
            }

        }












    }
