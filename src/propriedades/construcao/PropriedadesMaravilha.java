package propriedades.construcao;

import propriedades.Constantes;

public class PropriedadesMaravilha {
    private int custoComidaTijolo;
    private int custoOuroTijolo;
    private int tempoConstrucaoTijolo;
    private int tijolosNecessarios;

    public PropriedadesMaravilha() {
        this.setCustoComidaTijolo(Constantes.CUSTO_COMIDA_TIJOLO);
        this.setCustoOuroTijolo(Constantes.CUSTO_OURO_TIJOLO);
        this.setTempoConstrucaoTijolo(Constantes.TEMPO_CONSTRUCAO_TIJOLO_MULT);
        this.setTijolosNecessarios(Constantes.QTD_TIJOLOS_NECESSARIOS);
    }

    public int getCustoComidaTijolo() {
        return custoComidaTijolo;
    }
    public void setCustoComidaTijolo(int custoComidaTijolo) {
        this.custoComidaTijolo = custoComidaTijolo;
    }
    public int getCustoOuroTijolo() {
        return custoOuroTijolo;
    }
    public void setCustoOuroTijolo(int custoOuroTijolo) {
        this.custoOuroTijolo = custoOuroTijolo;
    }
    public int getTempoConstrucaoTijolo() {
        return tempoConstrucaoTijolo;
    }
    public void setTempoConstrucaoTijolo(int tempoConstrucaoTijolo) {
        this.tempoConstrucaoTijolo = tempoConstrucaoTijolo;
    }

    public int getTijolosNecessarios() {
        return tijolosNecessarios;
    }

    public void setTijolosNecessarios(int tijolosNecessarios) {
        this.tijolosNecessarios = tijolosNecessarios;
    }
}
