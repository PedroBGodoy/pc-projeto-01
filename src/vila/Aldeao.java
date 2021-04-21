package vila;

public class Aldeao {
    private int id;
    private EstadoAldeao estado;

    public Aldeao(int id) {
        this.id = id;
        this.estado = EstadoAldeao.PARADO;
    }

    public int getId() {
        return id;
    }

    public EstadoAldeao getEstado() {
        return this.estado;
    }

    public boolean isSacrificado() {
        return this.estado == EstadoAldeao.SACRIFICADO;
    }

    public void parar() {
        if(this.isSacrificado()) return;
        this.estado = EstadoAldeao.PARADO;
    }

    public void cultivar() {
        if(this.isSacrificado()) return;
        this.estado = EstadoAldeao.CULTIVANDO;
    }

    public void construir() {
        if(this.isSacrificado()) return;
        this.estado = EstadoAldeao.CULTIVANDO;
    }

    public void minerar() {
        if(this.isSacrificado()) return;
        this.estado = EstadoAldeao.MINERANDO;
    }

    public void orar() {
        if(this.isSacrificado()) return;
        this.estado = EstadoAldeao.ORANDO;
    }

    public void sacrificar() {
        this.estado = EstadoAldeao.SACRIFICADO;
    }
}
