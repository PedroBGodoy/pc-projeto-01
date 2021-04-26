package propriedades;

public class PropriedadesMina extends BasePropriedades {
    private int tempoTransporte;

    public PropriedadesMina() {
        this.setTempoTransporte(Constantes.HORA_BASE * Constantes.TEMPO_TRANSPORTE_MINA_MULT);
        this.setCustoComida(Constantes.HORA_BASE * Constantes.CUSTO_COMIDA_MINA);
        this.setCustoOuro(Constantes.HORA_BASE * Constantes.CUSTO_OURO_MINA);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_MINA_MULT);
        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_MINA_MULT);
    }

    public int getTempoTransporte() {
        return tempoTransporte;
    }
    public void setTempoTransporte(int tempoTransporte) {
        this.tempoTransporte = tempoTransporte;
    }

}
