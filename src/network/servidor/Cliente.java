package network.servidor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

public class Cliente implements Runnable {
    public int id;
    public String nome;
    public String civilizacao;

    private Socket socket;
    private Scanner entrada;
    private Formatter saida;

    private Servidor servidor;

    public Cliente(Socket socket, int id, Servidor servidor) {
        this.id = id;
        this.servidor = servidor;
        try {
            this.socket = socket;
            this.saida = new Formatter(new OutputStreamWriter(socket.getOutputStream()));
            saida.flush();
            this.entrada = new Scanner(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String mensagem;
        // Fica em loop escutando por novas mensagens
        try {
            while((mensagem = this.entrada.nextLine()) != null){
                this.tratarComando(this.id, mensagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cliente desconectado!");
        }
    }

    private void fecharConexao() {
        try {
            this.saida.close();
            this.entrada.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void alterarNome(String nome) {
        this.nome = nome;
        this.servidor.enviarComandoTodosExeto(String.format("NICK#%d#%s", this.id, nome), this.id);
    }

    private void anunciarConexao() {
        this.servidor.enviarComandoTodosExeto(String.format("NOVO_JOGADOR#%d#%s#%s#%s", this.id, this.nome, this.civilizacao, this.socket.getInetAddress().getHostName()), this.id);
    }

    private void desconectar() {
        this.servidor.enviarComandoTodosExeto(String.format("DESCONECTADO#%d#%s", this.id, this.nome), this.id);
        this.fecharConexao();
        this.servidor.removerCliente(this.id);
    }

    private void enviarListaJogadores() {
        Collection<Cliente> clientes = this.servidor.getClientes();
        clientes.forEach(cliente -> {
            if(cliente.id != this.id) {
                this.servidor.enviarComando(String.format("NOVO_JOGADOR#%d#%s#%s#%s", cliente.id, cliente.nome, cliente.civilizacao, cliente.socket.getInetAddress().getHostName()), this.id);
            }
        });
    }

    private void enviarMensagem(String mensagem) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        this.servidor.enviarComandoTodos(String.format("MSG#%s [%s] [%s]: %s", this.nome, dtf.format(now), this.civilizacao, mensagem));
    }

    private void iniciarJogo() {
        this.servidor.enviarComandoTodos("INICIAR_JOGO");
    }

    private void encerrarJogo() {
        this.servidor.enviarComandoTodos("ENCERRAR_JOGO");
    }

    private void informarVitoria() {
        this.servidor.enviarComandoTodos("VITORIA#"+this.nome);
    }

    private void tratarComando(Integer id, String comando) {
        System.out.println("Comando recebido: " + comando);
        String[] parametros = comando.split("#");
        if(parametros.length == 0) {
            System.out.println("[Servidor] Comando inválido. Quantidade de parametros invalido!");
            return;
        }

        switch (parametros[0]) {
            // ----- CONEXAO -----
            case "CONECTADO" -> {
                this.nome = parametros[1];
                this.civilizacao = parametros[2];
                this.anunciarConexao();
                this.enviarListaJogadores();
            }
            case "DESCONECTAR" -> this.desconectar();

            // ----- GERAL -----
            case "NICK" -> this.alterarNome(parametros[1]);
            case "MSG" -> this.enviarMensagem(parametros[1]);

            // ----- CONTROLE JOGO -----
            case "INICIAR_JOGO" -> this.iniciarJogo();
            case "ENCERRAR_JOGO", "DESTRUIR_JOGO" -> this.encerrarJogo();
            case "VITORIA" -> this.informarVitoria();

            // ----- ATAQUES -----
            case "ATAQUE_NUVEM_GAFANHOTOS",
                 "ATAQUE_MORTE_PRIMOGENITOS",
                 "ATAQUE_CHUVA_PEDRAS" -> this.enviarAtaque(parametros[0], parametros[1]);

            default -> System.out.println("[Servidor] Comando inválido: " + parametros[0]);
        }
    }

    public void enviarComando(String comando) {
        System.out.println("Enviando comando para " + this.id + " comando: " + comando);
        this.saida.format("%s\n", comando);
        this.saida.flush();
    }

    private void enviarAtaque(String codigoAtaque, String alvo) {
        Collection<Cliente> clientes = this.servidor.getClientes();
        clientes.forEach(cliente -> {
            if(cliente.nome.equals(alvo)) {
                cliente.enviarComando(codigoAtaque);
            }
        });
    }
}
