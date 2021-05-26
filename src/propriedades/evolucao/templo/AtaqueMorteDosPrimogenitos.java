package propriedades.evolucao.templo;

import propriedades.Constantes;

public class AtaqueMorteDosPrimogenitos {
  private int custoFe;

  public AtaqueMorteDosPrimogenitos() {
    this.setCustoFe(Constantes.CUSTO_FE_ATAQUE_MORTE_PRIMOGENITOS);
  }

  public int getCustoFe() {
    return custoFe;
  }

  public void setCustoFe(int custoFe) {
    this.custoFe = custoFe;
  }
}
