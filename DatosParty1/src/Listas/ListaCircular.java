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
                System.out.println(tamano);
                if (primero==null)//si no hay valores en la lista
                    {
                        primero= x;//el primer valor será el agregado
                        primero.INDEX= tamano;
                        tamano +=1;
                        ultimo=primero;//y a su vez este será el último
                        ultimo.INDEX= tamano;
                        primero.siguiente=ultimo; //por lo tanto el primero apuntará al último
                    }

                else
                    {
                        ultimo.siguiente=x;
                        ultimo.INDEX= tamano;
                        tamano +=1;
                        x.siguiente=primero;// el ultimo volverá a apuntar al primero
                        ultimo=x;  //el ultimo ahora será el agregado
                    }

            }
















    }
