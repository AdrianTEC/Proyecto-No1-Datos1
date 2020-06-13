package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;
import sample.bancoDeTextos;


public class EscribirPalabra extends Application implements Observador, Observado {
    private Pane root;
    private String texto_ingresado;
    private TextField cajaDeTexto;
    private Jugador px;
    private String currentText;
    private Stage ventana;
    private Observador eventManager;
    private int puntaje;
    int tiempo;
    private  Label oracion;
    private  Label pts;
    private Boolean keypress;
    BarraTiempo barrita ;

    public EscribirPalabra() {
        bancoDeTextos bn = new bancoDeTextos();
        px = new Jugador();
        cajaDeTexto= new TextField();
        currentText= bn.giveMeAText();
        ventana= new Stage();
        root = new Pane();
        barrita = new BarraTiempo();
        tiempo= 10000;
        oracion= new Label();
        pts= new Label();
        keypress=false;
    }



    public void setEventManager(Observador eventManager) {
        this.eventManager = eventManager;
    }
    private String mezclarPalabra(){
        String newtext = "";
        for(int i =0 ; i < currentText.length();i++)
            {
                char[] auxtext= currentText.toCharArray();

                int num = (int) (Math.random()*currentText.length());
                char aux= auxtext[i];
                auxtext[i]=auxtext[num];
                auxtext[num]=aux;
                newtext= String.valueOf(auxtext);


            }
        return newtext;
    }
    public void setPx(Jugador px) {
        this.px = px;
    }
    private void changeText()
        {

            bancoDeTextos bn = new bancoDeTextos();
            cajaDeTexto.setText("");
            currentText=bn.giveMeAText();
            oracion.setText(currentText);

        }
    public void verificar()
        {
                texto_ingresado = String.valueOf(cajaDeTexto.getText());

                if (!texto_ingresado.equals(currentText)) {
                    puntaje -= 10;
                    Partida.reproducirSonido("bip");
                    pts.setText("Puntos: " + puntaje + " Tiempo:" + tiempo);



                } else {
                    tiempo -= 500;
                    // victory.setText("¡CORRECTO!"+"\n"+"Ganaste 1 monedas");
                    Partida.reproducirSonido("win");
                    barrita.setTiempo(tiempo);
                    puntaje += 15;
                    pts.setText("Puntos: " + puntaje + " Tiempo:" + tiempo);
                    changeText();

                }


        }


    @Override
    public void start(Stage stage) {
        ventana=stage;
        root.setStyle("-fx-background-color: #202f4a");
        barrita.setObservador(EscribirPalabra.this);
        barrita.setTamano(400,50);
        barrita.getBarra().setLayoutX(150);
        barrita.getBarra().setLayoutY(350);
        barrita.generarContenedor();
        barrita.setTiempo(tiempo);
        barrita.encoger();

        oracion.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        oracion.setStyle("-fx-background-color: #535c94");
        oracion.setPrefSize(400,50);
        oracion.setText(mezclarPalabra());
        oracion.setLayoutX(150);
        oracion.setLayoutY(250);

        pts.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        pts.setStyle("-fx-background-color: #535c94");
        pts.setPrefSize(300,50);
        pts.setText("Puntos: "+puntaje +" Tiempo:"+ tiempo);
        pts.setLayoutX(300);
        pts.setLayoutY(100);
        Label INDICADOR= new Label();
        INDICADOR.setText("Jugador: "+px.getNombre());
        INDICADOR.setLayoutX(0);
        INDICADOR.setLayoutY(0);
        INDICADOR.setPrefSize(200,50);
        INDICADOR.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


        cajaDeTexto.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        cajaDeTexto.setPromptText("Escribe la oracion ordenada aquí");
        cajaDeTexto.setStyle("-fx-background-color: #535c94");
        cajaDeTexto.setPrefSize(400,200);
        cajaDeTexto.setLayoutY(450);
        cajaDeTexto.setLayoutX(150);

        stage.setResizable(false);
        stage.setTitle("escribirPalabra");
        stage.setScene(new Scene(root, 700, 700));
        root.getChildren().addAll(barrita.getBarra(),pts,barrita.getBarraContainer(),oracion,cajaDeTexto,INDICADOR);
        stage.show();
        stage.setOnCloseRequest(event -> notificar());
        stage.addEventFilter(KeyEvent.KEY_RELEASED, evt -> {
            if(evt.getCode().equals(KeyCode.ENTER)){

                  verificar();


            }

        });

    }

    @Override
    public void Update() {
        Label victory= new Label();
        victory.setPrefSize(600,200);
        victory.setLayoutX(50);
        victory.setLayoutY(200);
        victory.setStyle("-fx-background-color: #9cb3d6");
        victory.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        victory.setText("Ganaste : "+"\n"+puntaje/100 +" monedas" +"\n" +"tienes "+ puntaje +" puntos");
        px.setMonedas(px.getMonedas()+puntaje/100);

        Platform.runLater(() ->{ root.getChildren().add(victory);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(()->{ventana.close();});
                            notificar();

                        }
                    },
                    2000
            );

        });
    }

    @Override
    public void Update(int puntaje,Jugador jugador) {}

    @Override
    public void notificar() {
        eventManager.Update(puntaje,px); }
}
