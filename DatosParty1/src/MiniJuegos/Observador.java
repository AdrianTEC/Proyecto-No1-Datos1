package MiniJuegos;

import sample.Jugador;

public interface Observador {

    public  void Update();
    public  void Update(int puntaje, Jugador jugador);
}
