package propriedades;

public class PropriedadesTemplo extends BasePropriedades {
    private int producaoPorCiclo;
    private int producaoPorSacrifio;

    public PropriedadesTemplo() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_TEMPLO);
        this.setCustoOuro(Constantes.CUSTO_OURO_TEMPLO);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_TEMPLO_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_TEMPLO_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_TEMPLO_CICLO);
        this.setProducaoPorSacrifio(Constantes.PRODUCAO_TEMPLO_SACRIFICIO);
    }

    public int getProducaoPorCiclo() {
        return producaoPorCiclo;
    }
    public void setProducaoPorCiclo(int producaoPorCiclo) {
        this.producaoPorCiclo = producaoPorCiclo;
    }
    public int getProducaoPorSacrifio() {
        return producaoPorSacrifio;
    }
    public void setProducaoPorSacrifio(int producaoPorSacrifio) {
        this.producaoPorSacrifio = producaoPorSacrifio;
    }
}
