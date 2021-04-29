package propriedades;

public class PropriedadesGerais {
    private int qtdOuroInicial;
    private int qtdComidaInicial;
    private int qtdAldeoesInicial;
    private int qtdFazendasInicial;
    private int qtdMinasInicial;

    public PropriedadesGerais() {
        this.setQtdOuroInicial(Constantes.QTD_OURO_INICIAL);
        this.setQtdComidaInicial(Constantes.QTD_COMIDA_INICIAL);
        this.setQtdAldeoesInicial(Constantes.QTD_ALDEOES_INICIAL);
        this.setQtdFazendasInicial(Constantes.QTD_FAZENDAS_INICIAL);
        this.setQtdMinasInicial(Constantes.QTD_MINAS_INICIAL);
    }

    public int getQtdOuroInicial() {
        return qtdOuroInicial;
    }

    public void setQtdOuroInicial(int qtdOuroInicial) {
        this.qtdOuroInicial = qtdOuroInicial;
    }

    public int getQtdComidaInicial() {
        return qtdComidaInicial;
    }

    public void setQtdComidaInicial(int qtdComidaInicial) {
        this.qtdComidaInicial = qtdComidaInicial;
    }

    public int getQtdFazendasInicial() {
        return qtdFazendasInicial;
    }

    public void setQtdFazendasInicial(int qtdFazendasInicial) {
        this.qtdFazendasInicial = qtdFazendasInicial;
    }

    public int getQtdMinasInicial() {
        return qtdMinasInicial;
    }

    public void setQtdMinasInicial(int qtdMinasInicial) {
        this.qtdMinasInicial = qtdMinasInicial;
    }

    public int getQtdAldeoesInicial() {
        return qtdAldeoesInicial;
    }

    public void setQtdAldeoesInicial(int qtdAldeoesInicial) {
        this.qtdAldeoesInicial = qtdAldeoesInicial;
    }
}
