package vila;

public class AcaoAldeao {
    public TipoAcaoAldeao tipo;
    public String dado;

    public AcaoAldeao(TipoAcaoAldeao tipo) {
        this.tipo = tipo;
    }

    public AcaoAldeao(TipoAcaoAldeao tipo, String dado) {
        this.tipo = tipo;
        this.dado = dado;
    }

    public AcaoAldeao(TipoAcaoAldeao tipo, int dado) {
        this.tipo = tipo;
        this.dado = String.valueOf(dado);
    }
}
