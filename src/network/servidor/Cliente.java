package network.servidor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

public class Cliente implements Runnable {
    public int id;
    public String nome;

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
        this.servidor.enviarComandoTodosExeto(String.format("NOVO_JOGADOR#%d#%s", this.id, this.nome), this.id);
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
                this.servidor.enviarComando(String.format("NOVO_JOGADOR#%d#%s", cliente.id, cliente.nome), this.id);
            }
        });
    }

    private void enviarMensagem(String mensagem) {
        this.servidor.enviarComandoTodos(String.format("MSG#%s: %s", this.nome, mensagem));
    }

    private void tratarComando(Integer id, String comando) {
        System.out.println("Comando recebido: " + comando);
        String[] parametros = comando.split("#", 2);
        if(parametros.length == 0) {
            System.out.println("[Servidor] Comando inválido. Quantidade de parametros invalido!");
            return;
        }

        switch (parametros[0]) {
            case "CONECTADO":
                System.out.println(String.format("Cliente conectado ID: %d Nome: %s", this.id, parametros[1]));
                this.nome = parametros[1];
                this.anunciarConexao();
                this.enviarListaJogadores();
                break;
            case "DESCONECTAR":
                System.out.println(String.format("Cliente desconectado ID: %d", this.id));
                this.desconectar();
                break;
            case "NICK":
                System.out.println(id + " alterando nome para: " + parametros[1]);
                this.alterarNome(parametros[1]);
                break;
            case "MSG":
                System.out.println("Mensagem recebida: " + parametros[1]);
                this.enviarMensagem(parametros[1]);
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
                System.out.println("[Servidor] Comando inválido: " + parametros[0]);
        }
    }

    public void enviarComando(String comando) {
        System.out.println("Enviando comando para " + this.id + " comando: " + comando);
        this.saida.format("%s\n", comando);
        this.saida.flush();
    }
}
