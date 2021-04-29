package propriedades.evolucao.templo;

import propriedades.Constantes;
import propriedades.evolucao.BasePropEvolucao;

public class EvNuvemGafanhoto extends BasePropEvolucao {
    public EvNuvemGafanhoto() {
        super(
            0,
            0,
            Constantes.CUSTO_FE_EVOLUCAO_NUVEM_GANFANHOTOS,
            Constantes.TEMPO_EVOLUCAO_NUVEM_GANFANHOTOS_MULT
        );
    }
}
