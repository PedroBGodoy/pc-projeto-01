package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvChuvaPedras extends BasePropEvolucao {
    public EvChuvaPedras() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_CHUVA_PEDRAS,
            Constantes.TEMPO_EVOLUCAO_CHUVA_PEDRAS_MULT
        );
    }
}
