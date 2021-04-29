package propriedades.construcao;

import propriedades.BasePropriedades;
import propriedades.Constantes;

public class PropriedadesMina extends BasePropriedades {
    private int tempoTransporte;
    private int producaoPorCiclo;
    private int custoOuroEvolucao;
    private int custoComidaEvolucao;
    private int qtdProducaoSimultanea;
    private boolean evoluido;
    private int tempoEvolucao;

    public PropriedadesMina() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_MINA);
        this.setCustoOuro(Constantes.CUSTO_OURO_MINA);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_MINA_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_MINA_MULT);
        this.setTempoTransporte(Constantes.HORA_BASE * Constantes.TEMPO_TRANSPORTE_MINA_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_MINA_CICLO);

        this.setCustoOuroEvolucao(Constantes.CUSTO_OURO_EVOLUCAO_MINA);
        this.setCustoComidaEvolucao(Constantes.CUSTO_COMIDA_EVOLUCAO_MINA);

        this.setQtdProducaoSimultanea(Constantes.QTD_PRODUCAO_SIMULTANEA_MINA);
        this.setTempoEvolucao(Constantes.HORA_BASE * Constantes.TEMPO_EVOLUCAO_MINA);
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
    public int getQtdProducaoSimultanea() {
        return this.qtdProducaoSimultanea;
    }
    public void setQtdProducaoSimultanea(int qtdProducaoSimultanea) {
        this.qtdProducaoSimultanea = qtdProducaoSimultanea;
    }

    public void evoluir() {
        this.setQtdProducaoSimultanea(this.getQtdProducaoSimultanea() * 2);
    }

    public int getTempoEvolucao() {
        return tempoEvolucao;
    }

    public void setTempoEvolucao(int tempoEvolucao) {
        this.tempoEvolucao = tempoEvolucao;
    }
}
