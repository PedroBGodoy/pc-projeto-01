package propriedades;

public class Propriedades {
    public PropriedadesAldeao aldeao;
    public PropriedadesFazenda fazenda;
    public PropriedadesMina mina;
    public PropriedadesTemplo templo;

    public Propriedades() {
        this.aldeao = new PropriedadesAldeao();
        this.fazenda = new PropriedadesFazenda();
        this.mina = new PropriedadesMina();
        this.templo = new PropriedadesTemplo();
    }
}
