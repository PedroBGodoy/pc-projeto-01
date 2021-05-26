package propriedades.evolucao.templo;

import propriedades.Constantes;

public class AtaqueChuvaDePedras {
  private int custoFe;

  public AtaqueChuvaDePedras() {
    this.setCustoFe(Constantes.CUSTO_FE_ATAQUE_CHUVA_PEDRAS);
  }

  public int getCustoFe() {
    return custoFe;
  }

  public void setCustoFe(int custoFe) {
    this.custoFe = custoFe;
  }
}
