package propriedades;

public class PropriedadesFazenda extends BasePropriedades {
    private int tempoTransporte;
    private int producaoPorCiclo;

    public PropriedadesFazenda() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_FAZENDA);
        this.setCustoOuro(Constantes.CUSTO_OURO_FAZENDA);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_FAZENDA_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_FAZENDA_MULT);
        this.setTempoTransporte(Constantes.HORA_BASE * Constantes.TEMPO_TRANSPORTE_FAZENDA_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_FAZENDA_CICLO);
    }

    public int getTempoTransporte() {
        return tempoTransporte;
    }
    public void setTempoTransporte(int tempoTransporte) {
        this.tempoTransporte = tempoTransporte;
    }
    public int getProducaoPorCiclo() {
        return producaoPorCiclo;
    }
    public void setProducaoPorCiclo(int producaoPorCiclo) {
        this.producaoPorCiclo = producaoPorCiclo;
    }
}
