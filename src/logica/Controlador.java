package logica;

import tela.Principal;
import vila.Vila;

/**
 * Classe responsável por armazenar e controlar todas as ações do jogo
 */
public class Controlador {

    private Principal view;
    public Vila vila;

    public Controlador(Principal view) {
        this.view = view;
        System.out.println("Controlador iniciado!");

        this.vila = new Vila();
    }

}
