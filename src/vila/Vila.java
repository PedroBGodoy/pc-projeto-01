package vila;

import propriedades.Propriedades;
import tela.Tela;

import java.awt.*;
import java.util.ArrayList;

public class Vila {
    private ArrayList<Aldeao> aldeaos;
    private ArrayList<Fazenda> fazendas;
    private ArrayList<Mina> minas;
    private EstadoVila estado;
    private int comida;
    private int ouro;

    public Propriedades props;

    public Vila() {
        this.estado = EstadoVila.PARADA;
        this.aldeaos = new ArrayList<>();
        this.fazendas = new ArrayList<>();
        this.minas = new ArrayList<>();

        this.props = new Propriedades();

        this.atualizarComida(1000);
        this.atualizarOuro(1000);
    }

    private void parar() {
        Tela.i.mostrarPrefeitura("parada", Color.WHITE);
        this.estado = EstadoVila.PARADA;
    }

    private void produzirAldeao() {
        if(this.comida < this.props.aldeao.getCustoComida() || this.comida < this.props.aldeao.getCustoOuro()) {
            Tela.i.mostrarMensagemErro("Alerta Vila", "Recursos insuficientes!");
            this.parar();
            return;
        }
        this.atualizarComida(-this.props.aldeao.getCustoComida());
        this.atualizarOuro(-this.props.aldeao.getCustoOuro());

        this.estado = EstadoVila.PRODUZINDO_ALDEAO;
        Tela.i.mostrarPrefeitura("produzindo aldeao", Color.YELLOW);
        try {
            Thread.sleep(this.props.aldeao.getTempoConstrucao());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Aldeao novoAldeao = new Aldeao(this.aldeaos.size()+1, this);
        novoAldeao.start();
        aldeaos.add(novoAldeao);
        Tela.i.adicionarAldeao(String.valueOf(novoAldeao.getID()), "parado");
        this.parar();
    }

    private void evoluir() {
        Tela.i.mostrarMensagemErro("Vila", "Ainda não implementado");
    }

    public void comandoCriarAldeao() {
        if(this.estado != EstadoVila.PARADA) return;
        new Thread(this::produzirAldeao).start();
    }

    public void comandoAldeaoParar(int idAldeao) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.PARAR, 0);
        aldeao.adicionarTarefa(acao);
    }

    public void comandoAldeaoConstruir(int idAldeao, String estrutura) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.CONSTRUIR, estrutura);
        aldeao.adicionarTarefa(acao);
    }

    public void comandoAldeaoCultivar(int idAldeao, int numeroFazenda) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.CULTIVAR, numeroFazenda);
        aldeao.adicionarTarefa(acao);
    }

    public void comandoAldeaoMinerar(int idAldeao, int numeroMina) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.MINERAR, numeroMina);
        aldeao.adicionarTarefa(acao);
    }

    public void comandoAldeaoOrar(int idAldeao) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.ORAR, 1000);
        aldeao.adicionarTarefa(acao);
    }

    public void comandoAldeaoSacrificar(int idAldeao) {
        Aldeao aldeao = this.buscarAldeaoPorID(idAldeao);
        AcaoAldeao acao = new AcaoAldeao(TipoAcaoAldeao.SACRIFICAR, 1000);
        aldeao.adicionarTarefa(acao);
    }

    public void adicionarFazenda() {
        Fazenda fazenda = new Fazenda(this.fazendas.size()+1, this);
        this.fazendas.add(fazenda);
    }

    public void adicionarMina() {
        Mina mina = new Mina(this.minas.size()+1, this);
        this.minas.add(mina);
    }

    public void atualizarOuro(int diferenca) {
        this.ouro += diferenca;
        Tela.i.mostrarOuro(this.ouro);
    }

    public void atualizarComida(int diferenca) {
        this.comida += diferenca;
        Tela.i.mostrarComida(this.comida);
    }

    private Aldeao buscarAldeaoPorID(int idAldeao) {
        return this.aldeaos
                .stream()
                .filter(aldeao -> aldeao.getID() == idAldeao)
                .findFirst()
                .orElse(null);
    }

    public Fazenda buscarFazendaPorID(int idFazenda) {
        return this.fazendas
                .stream()
                .filter(fazenda -> fazenda.getID() == idFazenda)
                .findFirst()
                .orElse(null);
    }

    public Mina buscarMinaPorID(int idMina) {
        return this.minas
                .stream()
                .filter(mina -> mina.getID() == idMina)
                .findFirst()
                .orElse(null);
    }

    public boolean consumirRecursos(int ouro, int comida) {
        System.out.println("Ouro atual: " + this.ouro + " Consumo: " + ouro);
        if(this.ouro - ouro < 0) {
            Tela.i.mostrarMensagemErro("Alerta Vila", "Ouro insuficiente!");
            return false;
        }
        if(this.comida - comida < 0) {
            Tela.i.mostrarMensagemErro("Alerta Vila", "Comida insuficiente!");
            return false;
        }
        this.atualizarComida(-comida);
        this.atualizarOuro(-ouro);
        return true;
    }

}
