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

   public boolean estaVivo() {
        return this.vivo;
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
                int custoComida = this.vila.props.fazenda.getCustoComida();
                int custoOuro = this.vila.props.fazenda.getCustoOuro();
                if(!this.vila.consumirRecursos(custoOuro, custoComida)) {
                    this.parar();
                    return;
                }
                Thread.sleep(this.vila.props.fazenda.getTempoConstrucao());
                this.vila.adicionarFazenda();
            }
            case "Mina de ouro" -> {
                int custoComida = this.vila.props.mina.getCustoComida();
                int custoOuro = this.vila.props.mina.getCustoOuro();
                if(!this.vila.consumirRecursos(custoOuro, custoComida)) {
                    this.parar();
                    return;
                }
                Thread.sleep(this.vila.props.mina.getTempoConstrucao());
                this.vila.adicionarMina();
            }
            case "Templo" -> {
                if(this.vila.getTemplo() != null) return;
                int custoComida = this.vila.props.templo.getCustoComida();
                int custoOuro = this.vila.props.templo.getCustoOuro();
                if(!this.vila.consumirRecursos(custoOuro, custoComida)) {
                    this.parar();
                    return;
                }
                Thread.sleep(this.vila.props.templo.getTempoConstrucao());
                this.vila.adicionarTemplo();
            }
            case "Maravilha" -> {
                int custoComida = this.vila.props.maravilha.getCustoComidaTijolo();
                int custoOuro = this.vila.props.maravilha.getCustoOuroTijolo();
                if(!this.vila.consumirRecursos(custoOuro, custoComida)) {
                    this.parar();
                    return;
                }
                Maravilha maravilha = this.vila.buscarMaravilha();
                if(maravilha.pendenteFinalizacao()) {
                    maravilha.ajudarConstrucao();
                    this.adicionarTarefa(new AcaoAldeao(TipoAcaoAldeao.CONSTRUIR, "Maravilha"));
                } else {
                    this.parar();
                }
            }
        }
    }

    private void cultivar(int numeroFazendo) throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "cultivando Fazenda " + numeroFazendo);
        Fazenda fazenda = this.vila.buscarFazendaPorID(numeroFazendo);
        if(fazenda == null) return;
        fazenda.cultivar(this);
        Thread.sleep(this.vila.props.fazenda.getTempoTransporte());
        this.vila.atualizarComida(this.vila.props.fazenda.getProducaoPorCiclo());
        this.adicionarTarefa(new AcaoAldeao(TipoAcaoAldeao.CULTIVAR, numeroFazendo));
    }

    private void minerar(int numeroMina) throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "minerando Mina de Ouro " + numeroMina);
        Mina mina = this.vila.buscarMinaPorID(numeroMina);
        if(mina == null) return;
        mina.minerar(this);
        Thread.sleep(this.vila.props.mina.getTempoTransporte());
        this.vila.atualizarOuro(this.vila.props.mina.getProducaoPorCiclo());
        this.adicionarTarefa(new AcaoAldeao(TipoAcaoAldeao.MINERAR, numeroMina));
    }

    private void orar() throws InterruptedException {
        Tela.i.mostrarAldeao(this.getID(), "orando");
        Templo templo = this.vila.getTemplo();
        if(templo == null) return;
        templo.orar();
        this.adicionarTarefa(new AcaoAldeao(TipoAcaoAldeao.ORAR));
    }

    private void sacrificar() {
        Templo templo = this.vila.getTemplo();
        if(templo == null) return;
        templo.registrarSacrificio();
        Tela.i.mostrarAldeao(this.getID(), "sacrificado");
        this.vivo = false;
    }

    public void matar() {
        Tela.i.mostrarAldeao(this.getID(), "morto");
        this.vivo = false;
    }
}
