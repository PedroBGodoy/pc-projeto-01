package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvProtecaoChuvaPedras extends BasePropEvolucao {
    public EvProtecaoChuvaPedras() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_PROTECAO_CHUVA_PEDRAS,
            Constantes.TEMPO_EVOLUCAO_PROTECAO_CHUVA_PEDRAS_MULT
        );
    }
}
