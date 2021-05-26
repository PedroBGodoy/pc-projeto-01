package propriedades.construcao;

import propriedades.BasePropriedades;
import propriedades.Constantes;
import propriedades.evolucao.templo.*;

public class PropriedadesTemplo extends BasePropriedades {
    private int producaoPorCiclo;
    private int producaoPorSacrifio;
    public EvNuvemGafanhoto evNuvemGafanhoto;
    public EvMortePrimogenitos evMortePrimogenitos;
    public EvChuvaPedras evChuvaPedras;
    public EvProtecaoNuvemGafanhoto evProtecaoNuvemGafanhoto;
    public EvProtecaoMortePrimogenitos evProtecaoMortePrimogenitos;
    public EvProtecaoChuvaPedras evProtecaoChuvaPedras;

    public AtaqueNuvemDeGafanhotos ataqueNuvemDeGafanhotos;
    public AtaqueMorteDosPrimogenitos ataqueMorteDosPrimogenitos;
    public AtaqueChuvaDePedras ataqueChuvaDePedras;

    public PropriedadesTemplo() {
        this.setCustoComida(Constantes.CUSTO_COMIDA_TEMPLO);
        this.setCustoOuro(Constantes.CUSTO_OURO_TEMPLO);
        this.setTempoConstrucao(Constantes.HORA_BASE * Constantes.TEMPO_CONSTRUCAO_TEMPLO_MULT);

        this.setTempoUso(Constantes.HORA_BASE * Constantes.TEMPO_USO_TEMPLO_MULT);
        this.setProducaoPorCiclo(Constantes.PRODUCAO_TEMPLO_CICLO);
        this.setProducaoPorSacrifio(Constantes.PRODUCAO_TEMPLO_SACRIFICIO);

        this.evNuvemGafanhoto = new EvNuvemGafanhoto();
        this.evMortePrimogenitos = new EvMortePrimogenitos();
        this.evChuvaPedras = new EvChuvaPedras();

        this.evProtecaoNuvemGafanhoto = new EvProtecaoNuvemGafanhoto();
        this.evProtecaoMortePrimogenitos = new EvProtecaoMortePrimogenitos();
        this.evProtecaoChuvaPedras = new EvProtecaoChuvaPedras();

        this.ataqueNuvemDeGafanhotos = new AtaqueNuvemDeGafanhotos();
        this.ataqueMorteDosPrimogenitos = new AtaqueMorteDosPrimogenitos();
        this.ataqueChuvaDePedras = new AtaqueChuvaDePedras();
    }

    public int getProducaoPorCiclo() {
        return producaoPorCiclo;
    }
    public void setProducaoPorCiclo(int producaoPorCiclo) {
        this.producaoPorCiclo = producaoPorCiclo;
    }
    public int getProducaoPorSacrifio() {
        return producaoPorSacrifio;
    }
    public void setProducaoPorSacrifio(int producaoPorSacrifio) {
        this.producaoPorSacrifio = producaoPorSacrifio;
    }
}
