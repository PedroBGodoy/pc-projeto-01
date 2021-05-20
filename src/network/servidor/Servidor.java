package network.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    private ServerSocket server;

    private ConcurrentHashMap<Integer, Cliente> clientes;
    private ExecutorService poolClientes;

    private int porta;

    public Servidor(int porta) {
        this.porta = porta;
        this.clientes = new ConcurrentHashMap<>();
        this.poolClientes = Executors.newCachedThreadPool();
    }

    public int getPorta() {
        return this.porta;
    }
    public void setPorta(int porta) {
        this.porta = porta;
    }

    public void iniciar() {
        Socket socket;

        try {
            this.server = new ServerSocket(this.getPorta());
            System.out.println("Servidor iniciado na porta: " + this.getPorta());
            while (true) {
                socket = server.accept();
                Cliente cliente = new Cliente(socket, this.clientes.size()+1, this);
                this.clientes.put(this.clientes.size()+1, cliente);
                this.poolClientes.execute(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarComandoTodos(String comando) {
        this.clientes.forEach((id, cliente) -> {
            cliente.enviarComando(comando);
        });
    }

    public void enviarComandoTodosExeto(String comando, int id) {
        this.clientes.forEach((_id, cliente) -> {
            if(_id != id) {
                cliente.enviarComando(comando);
            }
        });
    }

    public void enviarComando(String comando, int id) {
        Cliente cliente = this.getClientById(id);
        if(cliente == null) {
            System.out.println("Erro ao tentar enviar mensagem ao cliente " + id);
        }
        cliente.enviarComando(comando);
    }

    private Cliente getClientById(int id) {
        return this.clientes.get(id);
    }

    public Collection<Cliente> getClientes() {
        return this.clientes.values();
    }

    public void removerCliente (int id) {
        this.clientes.remove(id);
    }
}
