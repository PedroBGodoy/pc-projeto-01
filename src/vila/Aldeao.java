package vila;

import tela.Tela;

import java.util.concurrent.ArrayBlockingQueue;

public class Aldeao extends Thread {
    private int id;
    private boolean vivo;
    private EstadoAldeao estado;
    private Vila vila;

    private ArrayBlockingQueue<AcaoAldeao> acoes;

    public Aldeao(int id, Vila vila) {
        this.id = id;
        this.estado = EstadoAldeao.PARADO;
        this.vivo = true;
        this.vila = vila;

        // Cria fila de ações com limite de 1 ação
        this.acoes = new ArrayBlockingQueue<>(1);
    }

    public int getID() {
        return this.id;
    }

    public void run() {
        while(this.vivo) {
            this.consumirTarefas();
        }
    }

    private void consumirTarefas() {
        // Consome a proxima tarefa da fila
        AcaoAldeao acao = this.acoes.poll();
        // Caso o aldeao esteja sem tarefa, parar
        if(acao == null) {
            this.acoes.add(new AcaoAldeao(TipoAcaoAldeao.PARAR, 0));
            return;
        }
        // Identifica tipo de tarefa e executa a ação correspondente
        try {
            switch (acao.tipo) {
                case MINERAR -> this.minerar(Integer.parseInt(acao.dado));
                case CONSTRUIR -> this.construir(acao.dado);
                case CULTIVAR -> this.cultivar(Integer.parseInt(acao.dado));
                case ORAR -> this.orar();
                case SACRIFICAR -> this.sacrificar();
                case PARAR -> this.parar();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarTarefa(AcaoAldeao acao) {
        if(!this.vivo || this.acoes.remainingCapacity() == 0) return;
        synchronized (this) {
            this.acoes.add(acao);
            this.notifyAll();
        }
    }

    private synchronized void parar() {
        Tela.i.mostrarAldeao(this.getID(), "parado");
        this.acoes.clear();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void construir(String estrutura) throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "construindo " + estrutura);
        switch (estrutura) {
            case "Fazenda" -> {
                Thread.sleep(this.vila.props.fazenda.getTempoConstrucao());
                this.vila.adicionarFazenda();
            }
            case "Mina de ouro" -> Tela.i.mostrarMensagemErro("Alerta Aldeao", "Ainda não implementado. (Construcao Mina)");
            case "Templo" -> Tela.i.mostrarMensagemErro("Alerta Aldeao", "Ainda não implementado. (Templo)");
            case "Maravilha" -> Tela.i.mostrarMensagemErro("Alerta Aldeao", "Ainda não implementado. (Maravilha)");
        }
    }

    private void cultivar(int numeroFazendo) throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "cultivando FAZENDA " + numeroFazendo);
        Fazenda fazenda = this.vila.buscarFazendaPorID(numeroFazendo);
        fazenda.cultivar(this);
        Thread.sleep(this.vila.props.fazenda.getTempoTransporte());
        this.vila.atualizarComida(this.vila.props.fazenda.getProducaoPorCiclo());
        this.adicionarTarefa(new AcaoAldeao(TipoAcaoAldeao.CULTIVAR, numeroFazendo));
    }

    private void minerar(int numeroMina) throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "minerando mina de ouro " + numeroMina);
        Thread.sleep(this.vila.props.mina.getTempoUso());
    }

    private void orar() throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "orando");
        Thread.sleep(this.vila.props.templo.getTempoUso());
    }

    public void sacrificar() throws InterruptedException {
        this.vivo = false;
        Tela.i.mostrarAldeao(this.getID(), "sacrificado");
        Thread.sleep(this.vila.props.aldeao.getTempoSacrificio());
    }
}
