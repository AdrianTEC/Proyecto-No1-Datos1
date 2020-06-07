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
                    "encontraste una moneda"+"\n"+"en tu bolsillo,"+"\n"+"eres 1% más rico",
                    "recibiste una herencia" +"\n"+"de una moneda"
            };

    private String[] cartasAZULES = new String[]
            { "la vida no te sonrie,"+"\n"+ "pero tampoco es"+"\n"+"un mal dia",
                    "Encuentras una bolsa vacia en el suelo, ahora tienes una bolsa",
                    "Cero más cero, sigue siendo cero"
            };
    private String[] cartasROJAS = new String[]
            { "Te asaltan"+"\n"+"amablemente,"+"\n"+"pierdes poco dinero"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda",
                    "Te da hambre, tienes que ir a comprar algo",
                    "Te quito una moneda, solo por que quiero"
            };
    private String [] cartasDORADAS = new String []
            { "Felicidades, encontraste un juego aleatorio, disfrutalo",
                    "Tienes la oportunidad de ganar mas",
                    ""
            };


}