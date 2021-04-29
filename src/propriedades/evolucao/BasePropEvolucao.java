package propriedades.evolucao;

public abstract class BasePropEvolucao {
    private int custoComida;
    private int custoOuro;
    private int tempoEvolucao;
    private int custoFe;

    public BasePropEvolucao(int custoComida, int custoOuro, int tempoEvolucao) {
        this.setCustoComida(custoComida);
        this.setCustoOuro(custoOuro);
        this.setTempoEvolucao(tempoEvolucao);
    }

    public BasePropEvolucao(int custoComida, int custoOuro, int custoFe, int tempoEvolucao) {
        this.setCustoComida(custoComida);
        this.setCustoOuro(custoOuro);
        this.setCustoFe(custoFe);
        this.setTempoEvolucao(tempoEvolucao);
    }

    public int getCustoComida() {
        return custoComida;
    }

    public void setCustoComida(int custoComida) {
        this.custoComida = custoComida;
    }

    public int getCustoOuro() {
        return custoOuro;
    }

    public void setCustoOuro(int custoOuro) {
        this.custoOuro = custoOuro;
    }

    public int getTempoEvolucao() {
        return tempoEvolucao;
    }

    public void setTempoEvolucao(int tempoEvolucao) {
        this.tempoEvolucao = tempoEvolucao;
    }

    public int getCustoFe() {
        return custoFe;
    }

    public void setCustoFe(int custoFe) {
        this.custoFe = custoFe;
    }
}
