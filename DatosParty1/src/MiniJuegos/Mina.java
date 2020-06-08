package MiniJuegos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Partida;


public class Mina {
    private ImageView imagen;
    private String tipo;
    private Image exp;


    public Mina() {
        imagen = new ImageView(new Image("Imagenes/Minijuegos/Mina.png"));
        imagen.setFitHeight(50);
        imagen.setFitWidth(50);
        exp = new Image("Imagenes/Minijuegos/Expl.png");
        tipo = "inactiva";
    }

    public void setTipo (String tipox){
        tipo = tipox;
    }
    public String getTipo () {
        return tipo;
    }

    public ImageView getImagen () {
        return imagen;
    }

    public void explotar(){
        imagen.setImage(exp);
        imagen.setFitWidth(70);
        imagen.setFitHeight(70);
        Partida.reproducirSonido("exp");
    }


}
