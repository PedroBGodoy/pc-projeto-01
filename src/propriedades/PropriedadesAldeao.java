package propriedades;

public class PropriedadesAldeao extends BasePropriedades {
    private int custoOuroEvolucao;
    private int custoComidaEvolucao;
    private boolean evoluido;
    private int tempoEvolucao;

    private Propriedades props;

    public PropriedadesAldeao(Propriedades props) {
        this.setCustoComida(Constantes.CUSTO_COMIDA_ALDEAO);
        this.setCustoOuro(Constantes.CUSTO_OURO_ALDEAO);

        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_PRODUCAO_ALDEAO_MULT);

        this.setCustoOuroEvolucao(Constantes.CUSTO_OURO_EVOLUCAO_ALDEAO);
        this.setCustoComidaEvolucao(Constantes.CUSTO_COMIDA_EVOLUCAO_ALDEAO);

        this.setTempoEvolucao(Constantes.HORA_BASE * Constantes.TEMPO_EVOLUCAO_ALDEAO);

        this.props = props;
    }

    public int getCustoOuroEvolucao() {
        return custoOuroEvolucao;
    }
    public void setCustoOuroEvolucao(int custoOuroEvolucao) {
        this.custoOuroEvolucao = custoOuroEvolucao;
    }
    public int getCustoComidaEvolucao() {
        return custoComidaEvolucao;
    }
    public void setCustoComidaEvolucao(int custoComidaEvolucao) {
        this.custoComidaEvolucao = custoComidaEvolucao;
    }
    public boolean isEvoluido() {
        return evoluido;
    }

    public void evoluir() {
        this.evoluido = true;
        this.props.fazenda.setTempoUso(this.props.fazenda.getTempoUso() / 2);
        this.props.fazenda.setProducaoPorCiclo(this.props.fazenda.getProducaoPorCiclo() * 2);
        this.props.mina.setTempoUso(this.props.mina.getTempoUso() / 2);
        this.props.mina.setProducaoPorCiclo(this.props.mina.getProducaoPorCiclo() * 2);
    }

    public int getTempoEvolucao() {
        return tempoEvolucao;
    }

    public void setTempoEvolucao(int tempoEvolucao) {
        this.tempoEvolucao = tempoEvolucao;
    }
}
