package propriedades;

public class PropriedadesAldeao extends BasePropriedades {
    private int tempoSacrificio;

    public PropriedadesAldeao() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_ALDEAO);
        this.setCustoOuro(Constantes.CUSTO_OURO_ALDEAO);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_PRODUCAO_ALDEAO_MULT);
        this.setTempoSacrificio(Constantes.HORA_BASE * Constantes.TEMPO_SACRIFICIO_ALDEAO_MULT);
    }

    public int getTempoSacrificio() {
        return this.tempoSacrificio;
    }
    public void setTempoSacrificio(int tempoSacrificio) {
        this.tempoSacrificio = tempoSacrificio;
    }
}
