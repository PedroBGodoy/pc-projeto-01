package propriedades;

public class PropriedadesMina extends BasePropriedades {
    private int tempoTransporte;
    private int producaoPorCiclo;

    public PropriedadesMina() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_MINA);
        this.setCustoOuro(Constantes.CUSTO_OURO_MINA);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_MINA_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_MINA_MULT);
        this.setTempoTransporte(Constantes.HORA_BASE * Constantes.TEMPO_TRANSPORTE_MINA_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_MINA_CICLO);
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
