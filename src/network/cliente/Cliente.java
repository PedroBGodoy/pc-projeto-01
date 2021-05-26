package network.cliente;

import principal.GameManager;
import tela.Tela;

import java.io.*;
import java.net.Socket;
import java.util.Formatter;

public class Cliente {
    private BufferedReader entrada;
    private Formatter saida;

    private GameManager gameManager;

    public Cliente(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void conectar(String ip, int porta, String nome, String civilizacao) {
        try {
            Socket socket = new Socket(ip, porta);
            this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.saida = new Formatter(new OutputStreamWriter(socket.getOutputStream()));

            Thread listener = new Thread(() -> {
               String mensagem;

               try {
                   while((mensagem = this.entrada.readLine()) != null) {
                        this.tratarComando(mensagem);
                   }
               } catch (EOFException e) {
                   System.out.println("Conexao perdida!");
               } catch(IOException e) {
                   e.printStackTrace();
               }
            });
            listener.start();

            // Envia pacote de conexão
            this.enviarComando(String.format("CONECTADO#%s#%s", nome, civilizacao));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tratarComando(String comando) {
        System.out.println("Comando recebido: " + comando);
        String[] parametros = comando.split("#");
        if(parametros.length == 0) {
            System.out.println("[Cliente] Comando inválido");
            return;
        }

        switch (parametros[0]) {
            // ----- CONEXAO -----
            case "NOVO_JOGADOR" -> {
                Tela.i.adicionarMensagem(parametros[2] + " entrou");
                Tela.i.adicionarJogador(parametros[2], parametros[3], parametros[4], "aguardando inicio da partida...");
            }
            case "DESCONECTADO" -> {
                Tela.i.adicionarMensagem(parametros[2] + " saiu");
                Tela.i.removerJogador(parametros[2]);
            }

            // ----- GERAL -----
            case "NICK" -> System.out.println("Alterando nome para: " + parametros[1]);
            case "MSG" -> Tela.i.adicionarMensagem(parametros[1]);

            // ----- CONTROLE JOGO -----
            case "INICIAR_JOGO" -> this.gameManager.iniciarJogo();
            case "ENCERRAR_JOGO", "DESTRUIR_JOGO" -> this.gameManager.encerrarJogo();
            case "VITORIA" -> this.gameManager.informarVitoria(parametros[1]);

            // ----- ATAQUES -----
            case "ATAQUE_NUVEM_GAFANHOTOS",
                 "ATAQUE_MORTE_PRIMOGENITOS",
                 "ATAQUE_CHUVA_PEDRAS"  -> this.gameManager.receberAtaque(parametros[0]);

            default -> System.out.println("[Cliente] Comando inválido");
        }
    }

    private void enviarComando(String comando) {
        this.saida.format("%s\n", comando);
        this.saida.flush();
    }

    public void desconectar() {
        this.enviarComando("DESCONECTAR");
    }

    public void enviarMensagem(String mensagem) {
        this.enviarComando("MSG#" + mensagem);
    }

    public void solicitarInicioJogo() {
        this.enviarComando("INICIAR_JOGO");
    }

    public void solicitarEncerramentoJogo() {
        this.enviarComando("ENCERRAR_JOGO");
    }

    public void enviarVitoria() {
        this.enviarComando("VITORIA");
    }

    public void enviarAtaque(String codigoAtaque, String alvo) {
        this.enviarComando(String.format("%s#%s", codigoAtaque, alvo));
    }
}
