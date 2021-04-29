package propriedades;

import propriedades.construcao.PropriedadesFazenda;
import propriedades.construcao.PropriedadesMaravilha;
import propriedades.construcao.PropriedadesMina;
import propriedades.construcao.PropriedadesTemplo;
import propriedades.entidades.PropriedadesAldeao;

/**
 * Armazena todas as propriedades do jogo
 */
public class Propriedades {
    public PropriedadesGerais geral;
    public PropriedadesAldeao aldeao;
    public PropriedadesFazenda fazenda;
    public PropriedadesMina mina;
    public PropriedadesTemplo templo;
    public PropriedadesMaravilha maravilha;

    public Propriedades() {
        this.geral = new PropriedadesGerais();
        this.aldeao = new PropriedadesAldeao(this);
        this.fazenda = new PropriedadesFazenda();
        this.mina = new PropriedadesMina();
        this.templo = new PropriedadesTemplo();
        this.maravilha = new PropriedadesMaravilha();
    }
}
