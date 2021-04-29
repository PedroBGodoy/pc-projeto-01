package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvProtecaoNuvemGafanhoto extends BasePropEvolucao {
    public EvProtecaoNuvemGafanhoto() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_PROTECAO_NUVEM_GANFANHOTOS,
            Constantes.TEMPO_EVOLUCAO_PROTECAO_NUVEM_GANFANHOTOS_MULT
        );
    }
}
