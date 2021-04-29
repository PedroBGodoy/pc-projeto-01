package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvMortePrimogenitos extends BasePropEvolucao {
    public EvMortePrimogenitos() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_MORTE_PRIMOGENITOS,
            Constantes.TEMPO_EVOLUCAO_MORTE_PRIMOGENITOS_MULT
        );
    }
}
