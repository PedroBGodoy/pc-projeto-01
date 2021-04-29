package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvProtecaoMortePrimogenitos extends BasePropEvolucao {
    public EvProtecaoMortePrimogenitos() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_PROTECAO_MORTE_PRIMOGENITOS,
            Constantes.TEMPO_EVOLUCAO_PROTECAO_MORTE_PRIMOGENITOS_MULT
        );
    }
}
