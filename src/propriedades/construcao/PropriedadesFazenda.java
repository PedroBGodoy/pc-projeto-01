package propriedades.construcao;

import propriedades.BasePropriedades;
import propriedades.Constantes;

public class PropriedadesFazenda extends BasePropriedades {
    private int tempoTransporte;
    private int producaoPorCiclo;
    private int custoOuroEvolucao;
    private int custoComidaEvolucao;
    private int qtdProducaoSimultanea;
    private boolean evoluido;
    private int tempoEvolucao;

    public PropriedadesFazenda() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_FAZENDA);
        this.setCustoOuro(Constantes.CUSTO_OURO_FAZENDA);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_FAZENDA_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_FAZENDA_MULT);
        this.setTempoTransporte(Constantes.HORA_BASE * Constantes.TEMPO_TRANSPORTE_FAZENDA_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_FAZENDA_CICLO);

        this.setCustoOuroEvolucao(Constantes.CUSTO_OURO_EVOLUCAO_FAZENDA);
        this.setCustoComidaEvolucao(Constantes.CUSTO_COMIDA_EVOLUCAO_FAZENDA);

        this.setQtdProducaoSimultanea(Constantes.QTD_PRODUCAO_SIMULTANEA_FAZENDA);
        this.setTempoEvolucao(Constantes.HORA_BASE * Constantes.TEMPO_EVOLUCAO_FAZENDA);
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
        return this.evoluido ? this.qtdProducaoSimultanea * 2 : this.qtdProducaoSimultanea;
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
