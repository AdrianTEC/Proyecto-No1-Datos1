package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;


public class EscribirPalabra extends Application implements Observador {
    private Pane root = new Pane();
    private String texto_ingresado;
    private TextField cajaDeTexto= new TextField();
    private Jugador px;
    private String currentText="hola";

    public EscribirPalabra(Jugador px) {
        this.px = px;

    }

    public void verificar()
        {   Label victory= new Label();
            victory.setPrefSize(600,200);
            victory.setLayoutX(50);
            victory.setLayoutY(200);
            victory.setStyle("-fx-background-color: #9cb3d6");
            victory.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            texto_ingresado=  String.valueOf(cajaDeTexto.getText());

            System.out.println(texto_ingresado.getClass().getName() + currentText.getClass().getName() );

            if (texto_ingresado.equals(currentText))
            {
              victory.setText("¡CORRECTO!"+"\n"+"Ganaste 3 monedas");
                //px.setMonedas(px.getMonedas+3);
                Partida.reproducirSonido("win");

            }
            else {

                victory.setText("¡Mal!"+"\n"+"perdiste contundentemente");

            }


            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    root.getChildren().add(victory);
                }
            });
        }


    @Override
    public void start(Stage stage) throws Exception {
        root.setStyle("-fx-background-color: #202f4a");


        BarraTiempo barrita = new BarraTiempo();
        barrita.getBarra().setLayoutX(150);
        barrita.getBarra().setLayoutY(350);
        barrita.setTamano(400,50);
        barrita.setTiempo(5000);
        barrita.encoger();
        barrita.generarContenedor();
        barrita.setObservador(EscribirPalabra.this);
        Label oracion= new Label();
        oracion.setLayoutX(150);
        oracion.setLayoutY(250);
        oracion.setPrefSize(400,50);
        oracion.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        oracion.setText("   le errpo omec ercan");
        oracion.setStyle("-fx-background-color: #535c94");

        cajaDeTexto.setPrefSize(400,200);
        cajaDeTexto.setLayoutY(450);
        cajaDeTexto.setLayoutX(150);
        cajaDeTexto.setStyle("-fx-background-color: #535c94");
        cajaDeTexto.setPromptText("Escribe la oracion ordenada aquí");
        cajaDeTexto.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        stage.setResizable(false);
        stage.setTitle("Save D3");
        stage.setScene(new Scene(root, 700, 700));
        root.getChildren().addAll(barrita.getBarra(),barrita.getBarraContainer(),oracion,cajaDeTexto);
        stage.show();
        stage.setOnCloseRequest(event -> { System.exit(1);});
    }

    @Override
    public void Update() {
        verificar();
    }
}
