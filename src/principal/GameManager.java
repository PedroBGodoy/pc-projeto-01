package principal;

import network.cliente.Cliente;
import network.servidor.Servidor;

public class GameManager {
    private Cliente cliente;

    public boolean criarJogo(String ip, int porta, String nome) {
        this.iniciarServidor();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        this.conectarServidor(ip, porta, nome);
        return true;
    }

    public void iniciarServidor() {
        Thread serverThread = new Thread(() -> {
            Servidor servidor = new Servidor(7777);
            servidor.iniciar();
        });
        serverThread.start();
    }

    public boolean conectarServidor(String ip, int porta, String nome) {
        this.cliente = new Cliente(nome);
        cliente.conectar(ip, porta, nome);
        return true;
    }

    public void desconectar() {
        this.cliente.desconectar();
    }

    public void enviarMensagem(String mensagem) {
        this.cliente.enviarMensagem(mensagem);
    }
}
