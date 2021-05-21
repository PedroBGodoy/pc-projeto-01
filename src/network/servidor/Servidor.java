package network.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    private final ConcurrentHashMap<Integer, Cliente> clientes;
    private final ExecutorService poolClientes;

    private final int porta;

    public Servidor(int porta) {
        this.porta = porta;
        this.clientes = new ConcurrentHashMap<>();
        this.poolClientes = Executors.newCachedThreadPool();
    }

    public void iniciar() {
        Socket socket;

        try {
            ServerSocket server = new ServerSocket(this.porta);
            System.out.println("Servidor iniciado na porta: " + this.porta);
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
            return;
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
