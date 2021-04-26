package propriedades;

public abstract class BasePropriedades {
    private int tempoConstrucao;
    private int custoComida;
    private int custoOuro;
    private int tempoUso;

    public int getTempoConstrucao() {
        return tempoConstrucao;
    }
    public void setTempoConstrucao(int tempoConstrucao) {
        this.tempoConstrucao = tempoConstrucao;
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
    public int getTempoUso() {
        return tempoUso;
    }
    public void setTempoUso(int tempoUso) {
        this.tempoUso = tempoUso;
    }
}
