package vila;

import tela.Tela;

public class Templo {
    private Vila vila;
    private int fe;

    public Templo(Vila vila) {
        this.vila = vila;
        this.fe = 0;
        Tela.i.habilitarTemplo();
    }

    public void orar() {
        try {
            Thread.sleep(this.vila.props.templo.getTempoUso());
            this.adicionarFe(this.vila.props.templo.getProducaoPorCiclo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void registrarSacrificio() {
        this.adicionarFe(this.vila.props.templo.getProducaoPorSacrifio());
    }

    private void adicionarFe(int quantidade) {
        this.fe += quantidade;
        Tela.i.mostrarOferendaFe(this.fe);
    }
}
