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
                        ((CasillaSimple) primero).INDEX= tamano;
                        tamano +=1;
                        ultimo=primero;//y a su vez este será el último
                        ((CasillaSimple) ultimo).INDEX= tamano;
                        ((CasillaSimple) primero).siguiente=((CasillaSimple) ultimo); //por lo tanto el primero apuntará al último
                    }

                else
                    {
                        ((CasillaSimple) ultimo).siguiente=x;
                        ((CasillaSimple) ultimo).INDEX= tamano;
                        tamano +=1;
                         x.siguiente=((CasillaSimple) primero);// el ultimo volverá a apuntar al primero
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
                ((CasillaDoble) primero).INDEX= tamano;
                tamano +=1;
                ultimo=primero;//y a su vez este será el último
                ((CasillaDoble) ultimo).INDEX= tamano;
                ((CasillaDoble) primero).siguiente=((CasillaDoble) ultimo); //por lo tanto el primero apuntará al último
                ((CasillaDoble) primero).anterior=((CasillaDoble) ultimo);
            }

            else
            {
                ((CasillaDoble) ultimo).siguiente=x;// el que sigue despues del ultimo sera el agregado
                ((CasillaDoble) ultimo).INDEX= tamano;
                tamano +=1;
                x.siguiente=((CasillaDoble) primero);// el ultimo volverá a apuntar al primero
                x.anterior= ((CasillaDoble) ultimo);// el que va detras del agregado será el ultimo por ahora conocido
                ((CasillaDoble) primero).anterior=x;// el que va detras del primero ahora será el agregado

                ultimo=x;  //el ultimo ahora será el agregado
            }

        }












    }
