package vila;

import principal.GameManager;
import propriedades.Propriedades;
import tela.Tela;

import java.awt.*;
import java.util.ArrayList;

public class Vila {
    private final ArrayList<Aldeao> aldeaos;
    private final ArrayList<Fazenda> fazendas;
    private final ArrayList<Mina> minas;
    private Templo templo;
    private Maravilha maravilha;
    private EstadoVila estado;
    private int comida;
    private int ouro;

    public Propriedades props;

    private GameManager gameManager;

    public Vila(GameManager gameManager) {
        this.estado = EstadoVila.PARADA;
        this.aldeaos = new ArrayList<>();
        this.fazendas = new ArrayList<>();
        this.minas = new ArrayList<>();

        this.gameManager = gameManager;

        this.props = new Propriedades();
        this.inicializar();
    }

    /**
     * Aplica valores base como quantidade inicial de
     * comida, ouro, aldeoes, fazendas e minas
     */
    private void inicializar() {
        this.maravilha = new Maravilha(this);
        this.atualizarComida(this.props.geral.getQtdComidaInicial());
        this.atualizarOuro(this.props.geral.getQtdOuroInicial());
        for (int i = 0; i < this.props.geral.getQtdFazendasInicial(); i++)
            this.adicionarFazenda();
        for (int i = 0; i < this.props.geral.getQtdMinasInicial(); i++)
            this.adicionarMina();
        for (int i = 0; i < this.props.geral.getQtdAldeoesInicial(); i++)
            this.adicionarAldeao();
    }

    private void parar() {
        Tela.i.mostrarPrefeitura("parada", Color.WHITE);
        this.estado = EstadoVila.PARADA;
    }

    private void produzirAldeao() {
        int custoOuro = this.props.aldeao.getCustoOuro();
        int custoComida = this.props.aldeao.getCustoComida();
        if(!this.consumirRecursos(custoOuro, custoComida)) return;

        this.estado = EstadoVila.PRODUZINDO_ALDEAO;
        Tela.i.mostrarPrefeitura("produzindo aldeao", Color.YELLOW);
        try {
            Thread.sleep(this.props.aldeao.getTempoConstrucao());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.adicionarAldeao();
        this.parar();
    }

    private void adicionarAldeao() {
        Aldeao novoAldeao = new Aldeao(this.aldeaos.size()+1, this);
        novoAldeao.start();
        aldeaos.add(novoAldeao);
        Tela.i.adicionarAldeao(String.valueOf(novoAldeao.getID()), "parado");
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

    public void comandoTemploLancar(String strPraga, String strInimigo) {
        int qtdFe = 1000;
        String codigoAtaque = "";

        switch (strPraga) {
            case "Nuvem de gafanhotos" -> {
                codigoAtaque = "ATAQUE_NUVEM_GAFANHOTOS";
                qtdFe = this.props.templo.ataqueNuvemDeGafanhotos.getCustoFe();
            }
            case "Morte dos primogênitos" -> {
                codigoAtaque = "ATAQUE_MORTE_PRIMOGENITOS";
                qtdFe = this.props.templo.ataqueMorteDosPrimogenitos.getCustoFe();;
            }
            case "Chuva de pedras" -> {
                codigoAtaque = "ATAQUE_CHUVA_PEDRAS";
                qtdFe = this.props.templo.ataqueChuvaDePedras.getCustoFe();;
            }
        }

        if(this.templo.consumirFe(qtdFe)) {
            this.gameManager.enviarAtaque(codigoAtaque, strInimigo);
        } else {
            Tela.i.mostrarMensagemErro("Alerta Vila!", "Você não tem fé o suficiente!");
        }
    }

    public void adicionarFazenda() {
        Fazenda fazenda = new Fazenda(this.fazendas.size()+1, this);
        this.fazendas.add(fazenda);
    }

    public void adicionarMina() {
        Mina mina = new Mina(this.minas.size()+1, this);
        this.minas.add(mina);
    }

    public void adicionarTemplo() {
        if(this.templo != null) {
            Tela.i.mostrarMensagemErro("Alerta Vila!", "Templo já foi criado!");
            return;
        }
        this.templo = new Templo(this);
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

    public Templo getTemplo() {
        return this.templo;
    }

    public Mina buscarMinaPorID(int idMina) {
        return this.minas
                .stream()
                .filter(mina -> mina.getID() == idMina)
                .findFirst()
                .orElse(null);
    }

    public Maravilha buscarMaravilha() {
        return this.maravilha;
    }

    public boolean consumirRecursos(int ouro, int comida) {
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

    public void comandoPrefeituraEvoluir(String evolucao) {
        if(this.estado != EstadoVila.PARADA) return;
        switch (evolucao) {
            case "Evolução de aldeão" -> new Thread(this::evoluirAldeao).start();
            case "Evolução de fazenda" -> new Thread(this::evoluirFazenda).start();
            case "Evolução de mina de ouro" -> new Thread(this::evoluirMina).start();
        }
    }

    private void evoluirAldeao() {
        int custoOuro = this.props.aldeao.getCustoOuroEvolucao();
        int custoComida = this.props.aldeao.getCustoComidaEvolucao();
        if(!this.consumirRecursos(custoOuro, custoComida)) return;
        Tela.i.mostrarPrefeitura("Evoluindo Aldeão", Color.BLUE);
        try {
            Thread.sleep(this.props.aldeao.getTempoEvolucao());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.props.aldeao.evoluir();
        this.parar();
    }

    private void evoluirFazenda() {
        int custoOuro = this.props.fazenda.getCustoOuroEvolucao();
        int custoComida = this.props.fazenda.getCustoComidaEvolucao();
        if(!this.consumirRecursos(custoOuro, custoComida)) return;
        Tela.i.mostrarPrefeitura("Evoluindo Fazenda", Color.BLUE);
        try {
            Thread.sleep(this.props.fazenda.getTempoEvolucao());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.props.fazenda.evoluir();
        this.fazendas.forEach(fazenda -> fazenda.evoluir(5));
        this.parar();
    }

    private void evoluirMina() {
        int custoOuro = this.props.mina.getCustoOuroEvolucao();
        int custoComida = this.props.mina.getCustoComidaEvolucao();
        if(!this.consumirRecursos(custoOuro, custoComida)) return;
        Tela.i.mostrarPrefeitura("Evoluindo Mina", Color.BLUE);
        try {
            Thread.sleep(this.props.mina.getTempoEvolucao());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.props.mina.evoluir();
        this.minas.forEach(mina -> mina.evoluir(5));
        this.parar();
    }

    public void comandoTemploEvoluir(String evolucao) {
        this.templo.comandoTemploEvoluir(evolucao);
    }

    public void ganharJogo() {
        this.gameManager.enviarVitoria();
    }

    public void receberAtaque(String codigoAtaque) {
        switch (codigoAtaque) {
            case "ATAQUE_NUVEM_GAFANHOTOS" -> {
                if(this.templo.temProtecaoNuvemGafanhotos()) {
                    Tela.i.mostrarMensagem("Proteção!", "Você foi protegido de uma nuvem de gafanhotos!");
                    this.templo.setProtecaoNuvemGafanhotos(false);
                    return;
                }
                this.destruirMetadeFazendas();
                Tela.i.mostrarMensagem("Ataque", "Metade das suas fazendas foram destruidas. Você foi atacado com Chuva de Gafanhotos!");
            }
            case "ATAQUE_MORTE_PRIMOGENITOS" -> {
                if(this.templo.temProtecaoMortePrimogenitos()) {
                    Tela.i.mostrarMensagem("Proteção!", "Você foi protegido da morte dos primogenitos!");
                    this.templo.setProtecaoMortePrimogenitos(false);
                    return;
                }
                this.matarMetadeAldeoes();
                Tela.i.mostrarMensagem("Ataque", "Metade dos seus aldeoes morreram. Você foi atacado com Morte dos Primogenitos!");
            }
            case "ATAQUE_CHUVA_PEDRAS" -> {
                if(this.templo.temProtecaoChuvaDePedras()) {
                    Tela.i.mostrarMensagem("Proteção!", "Você foi protegido de uma chuva de pedras!");
                    this.templo.setProtecaoChuvaDePedras(false);
                    return;
                }
                this.receberChuvaDePedra();
                Tela.i.mostrarMensagem("Ataque", "Você foi atacado com Chuva de Pedras!");
            }
        }
    }

    private void destruirMetadeFazendas() {
        // A fazer
        int qtdFazendas = this.fazendas.size() / 2;
        for (int i = 0; i < qtdFazendas; i++) {
            // Identifica index da ultima fazenda da lista
            int indexFazenda = this.fazendas.size()-1;
            Fazenda fazenda = this.fazendas.get(indexFazenda);

            // Destruir Fazenda
            fazenda.destruirFazenda();
            this.fazendas.remove(fazenda);
            Tela.i.destruirFazenda(fazenda.getID());
        }
    }

    private void destruirMetadeMinaDeOuro() {
        // A fazer
        int qtdMinas = this.minas.size() / 2;
        for (int i = 0; i < qtdMinas; i++) {
            // Identifica index da ultima mina da lista
            int indexMina = this.minas.size()-1;
            Mina mina = this.minas.get(indexMina);

            // Destruir Fazenda
            mina.destruirMina();
            this.minas.remove(mina);
            Tela.i.destruirMina(mina.getID());
        }
    }

    private void matarMetadeAldeoes() {
        // A fazer
        int qtdAldeoes = this.quantidadeAldeoesVivos() / 2;
        for (int i = 0; i < qtdAldeoes; i++) {
            Aldeao aldeao = this.buscarUltimoAldeaoListaVivo();
            if(aldeao == null) {
                break;
            }

            // Destruir Fazenda
            aldeao.matar();
        }
    }

    private int quantidadeAldeoesVivos() {
        int quantidade = 0;
        for (Aldeao aldeao : this.aldeaos) {
            if (aldeao.estaVivo()) quantidade++;
        }
        return quantidade;
    }

    private Aldeao buscarUltimoAldeaoListaVivo() {
        for (int i = this.aldeaos.size(); i > 0; i--) {
            Aldeao aldeao = this.aldeaos.get(i-1);
            if(aldeao.estaVivo()) {
                return aldeao;
            }
        }
        return null;
    }

    private void receberChuvaDePedra() {
        this.matarMetadeAldeoes();
        this.destruirMetadeFazendas();
        this.destruirMetadeMinaDeOuro();
        this.maravilha.destruirMetade();
    }

}