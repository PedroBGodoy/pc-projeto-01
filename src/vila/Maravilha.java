package vila;

import tela.Tela;

public class Maravilha {
    private int tijolos;
    private final Vila vila;

    public Maravilha(Vila vila) {
        this.tijolos = 0;
        this.vila = vila;
    }

    public void ajudarConstrucao() {
        if(!this.pendenteFinalizacao()) return;
        try {
            Thread.sleep(this.vila.props.maravilha.getTempoConstrucaoTijolo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.adicionarTijolo();
    }

    private void adicionarTijolo() {
        this.tijolos++;
        Tela.i.habilitarMaravilha();
        Tela.i.mostrarMaravilha(this.tijolos);
        if(!this.pendenteFinalizacao()) {
            this.vila.ganharJogo();
        }
    }

    public void destruirMetade() {
        this.tijolos -= this.tijolos / 2;
        Tela.i.mostrarMaravilha(this.tijolos);
    }

    public boolean pendenteFinalizacao() {
        return this.tijolos < this.vila.props.maravilha.getTijolosNecessarios();
    }
}
