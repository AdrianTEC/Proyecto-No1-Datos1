package sample;

public class bancoDeTextos {

    public String textoDeCartaTipo(int tipo){
        String random = new String();
        int x = (int)(Math.random()*3);
        if (tipo == 1){

            random = cartasVERDES [x];

        }
        if (tipo == 3){

            random = cartasAZULES [x];

        }
        if (tipo == 2){

            random = cartasROJAS [x];

        }
        if (tipo == 4){

            random = cartasDORADAS [x];

        }
        return random;
    }

    private String[] cartasVERDES= new String[]
            {"te encontraste una"+"\n"+ "moneda en el suelo,"+"\n"+"eres 1% más rico"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda",
             "encontraste una"+"\n"+"moneda"+"\n"+"en tu bolsillo,"+"\n"+"eres 1% más rico"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda",
             "recibiste una"+"\n"+"herencia" +"\n"+"de una moneda"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda"
            };

    private String[] cartasAZULES = new String[]
            { "la vida no te sonrie,"+"\n"+ "pero tampoco es"+"\n"+"un mal dia",
              "¿Tu mala suerte"+"\n"+"te trajo aqui?",
              "Cero más cero,"+"\n"+"sigue siendo cero"
            };
    private String[] cartasROJAS = new String[]
            { "Te asaltan"+"\n"+"amablemente,"+"\n"+"pierdes poco dinero"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda",
              "Te da hambre,"+"\n"+"tienes que ir"+"\n"+"a comprar algo"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda",
              "Tu mala suerte"+"\n"+"te trajo aqui"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda"
            };
    private String [] cartasDORADAS = new String []
            { "Felicidades,"+"\n"+"encontraste"+"\n"+"un juego aleatorio,"+"\n"+"disfrutalo"+"\n"+"\n"+"\n"+"\n"+
                    "          Que inicie"+"\n"+"          el juego!",
              "Tienes la"+"\n"+"oportunidad"+"\n"+"de ganar mas"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "          Que inicie"+"\n"+"          el juego!",
              "Demuestra tu"+"\n"+"hablilidad!"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "          Que inicie"+"\n"+"          el juego!"
            };


}