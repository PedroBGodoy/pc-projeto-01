package network.cliente;

import tela.Tela;

import java.io.*;
import java.net.Socket;
import java.util.Formatter;

public class Cliente {
    private int id;
    private String nome;

    private Socket socket;
    private BufferedReader entrada;
    private Formatter saida;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void conectar(String ip, int porta, String nome) {
        try {
            this.socket = new Socket(ip, porta);
            this.entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.saida = new Formatter(new OutputStreamWriter(this.socket.getOutputStream()));

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

            // Defini nome assim que conectar ao servidor
            this.enviarComando("CONECTADO#"+nome);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tratarComando(String comando) {
        String[] parametros = comando.split("#");
        if(parametros.length == 0) {
            System.out.println("[Cliente] Comando inválido");
            return;
        }

        switch (parametros[0]) {
            case "NICK":
                System.out.println("Alterando nome para: " + parametros[1]);
                break;
            case "MSG":
                Tela.i.adicionarMensagem(parametros[1]);
                break;
            case "NOVO_JOGADOR":
                Tela.i.adicionarMensagem(parametros[2] + " entrou");
                Tela.i.adicionarJogador(parametros[2], parametros[2], parametros[2], parametros[2]);
                break;
            case "DESCONECTADO":
                Tela.i.adicionarMensagem(parametros[2] + " saiu");
                Tela.i.removerJogador(parametros[2]);
                break;
            case "ATAQUE_NUVEM_GAFANHOTOS":
                System.out.println(id + " atacou com nuvem de gafanhotos " + parametros[1]);
                // Enviar ataque a jogador
                break;
            case "ATAQUE_MORTE_PRIMOGENITOS":
                System.out.println(id + " atacou com morte dos primogenitos " + parametros[1]);
                // Enviar ataque a jogador
                break;
            case "ATAQUE_CHUVA_PEDRAS":
                System.out.println(id + " atacou com nuvem de gafanhotos em " + parametros[1]);
                // Enviar ataque a jogador
                break;
            default:
                System.out.println("[Cliente] Comando inválido");
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
}
