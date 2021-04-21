package vila;

import logica.Constantes;
import tela.Principal;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vila {
    private ArrayList<Aldeao> aldeaos = new ArrayList<>();

    private Lock parideira;

    public Vila() {
        this.parideira = new ReentrantLock();
    }

    public void criarAldeao() {
        new Thread(() -> {
            this.parideira.lock();
            Principal.instancia.mostrarPrefeitura("criando aldeão", Color.YELLOW);
            try {
                Thread.sleep(2 * Constantes.HORA_BASE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.callbackCriarAldeao(aldeaos.size()+1);
            this.parideira.unlock();
        }).start();
    }

    public void callbackCriarAldeao(int id) {
        Aldeao aldeao = new Aldeao(id);
        aldeaos.add(aldeao);
        Principal.instancia.adicionarAldeao(Integer.toString(aldeao.getId()), "parado");
        Principal.instancia.mostrarPrefeitura("parada", Color.WHITE);
    }

    public void comandoAldeaoPara(int id) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.parar();
        this.atualizarStatusAldeao(aldeao);
    }

    public void comandoAldeaoContruir(int id, String qual) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.construir();
        this.atualizarStatusAldeao(aldeao, qual);
    }

    public void comandoAldeaoCultivar(int id, int numeroFazenda) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.cultivar();
        this.atualizarStatusAldeao(aldeao, Integer.toString(numeroFazenda));
    }

    public void comandoAldeaoMinerar(int id, int numeroMina) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.minerar();
        this.atualizarStatusAldeao(aldeao, Integer.toString(numeroMina));
    }

    public void comandoAldeaoOrar(int id) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.orar();
        this.atualizarStatusAldeao(aldeao);
    }

    public void comandoAldeaoSacrificar(int id) {
        Aldeao aldeao = this.getAldeaoByID(id);
        aldeao.sacrificar();
        this.atualizarStatusAldeao(aldeao);
    }

    private void atualizarStatusAldeao(Aldeao aldeao) {
        this.atualizarStatusAldeao(aldeao, "");
    }

    private void atualizarStatusAldeao(Aldeao aldeao, String identificador) {
        switch (aldeao.getEstado()) {
            case MINERANDO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "minerando ouro na mina " + identificador);
            case ORANDO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "orando");
            case CULTIVANDO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "cultivando fazenda " + identificador);
            case CONSTRUINDO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "construindo " + identificador);
            case PARADO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "parado");
            case SACRIFICADO -> Principal.instancia.mostrarAldeao(aldeao.getId(), "sacrificado");
        }
    }

    private Aldeao getAldeaoByID(int id) {
        return aldeaos.stream()
                .filter(aldeao -> aldeao.getId() == id)
                .findFirst()
                .get();
    }
}
