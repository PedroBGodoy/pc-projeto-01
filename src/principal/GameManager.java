package principal;

import network.cliente.Cliente;
import network.servidor.Servidor;
import tela.Tela;
import vila.Vila;

public class GameManager {
    private Cliente cliente;

    private Vila vila;

    public boolean criarJogo(String ip, int porta, String nome, String civilizacao) {
        this.iniciarServidor();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        this.conectarServidor(ip, porta, nome, civilizacao);
        return true;
    }

    public void iniciarServidor() {
        Thread serverThread = new Thread(() -> {
            Servidor servidor = new Servidor(7777);
            servidor.iniciar();
        });
        serverThread.start();
    }

    public boolean conectarServidor(String ip, int porta, String nome, String civilizacao) {
        this.cliente = new Cliente(this);
        cliente.conectar(ip, porta, nome, civilizacao);
        return true;
    }

    public void iniciarJogo() {
        this.vila = new Vila(this);
        Tela.i.comandoIniciarJogo(this.vila);
    }

    public void desconectar() {
        this.cliente.desconectar();
    }

    public void solicitarInicioJogo() {
        this.cliente.solicitarInicioJogo();
    }

    public void solicitarEncerramentoJogo() {
        this.cliente.solicitarEncerramentoJogo();
    }

    public void encerrarJogo() {
        Tela.i.comandoEncerrarJogo();
    }

    public void enviarVitoria() {
        this.cliente.enviarVitoria();
    }

    public void informarVitoria(String nomeJogador) {
        Tela.i.comandoVitoria(nomeJogador);
        this.encerrarJogo();
    }

    public void enviarAtaque(String codigoAtaque, String nomeJogador) {
        this.cliente.enviarAtaque(codigoAtaque, nomeJogador);
    }

    public void receberAtaque(String codigoAtaque) {
        this.vila.receberAtaque(codigoAtaque);
    }

    public void enviarMensagem(String mensagem) {
        this.cliente.enviarMensagem(mensagem);
    }
}
