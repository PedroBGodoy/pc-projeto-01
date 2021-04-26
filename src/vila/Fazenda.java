package vila;

import tela.Tela;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Fazenda {
    private int id;
    private ArrayList<Aldeao> aldeaos;
    private Vila vila;

    private Semaphore semaphore;

    public Fazenda(int id, Vila vila) {
        this.id = id;
        this.aldeaos = new ArrayList<>();
        this.vila = vila;
        this.semaphore = new Semaphore(5);

        Tela.i.adicionarFazenda(String.valueOf(id), "");
    }

    public int getID() {
        return this.id;
    }

    public void cultivar(Aldeao aldeao) {
        try {
            // Tenta iniciar cultivo, caso limite tenha excedido aguardar
            this.semaphore.acquire();

            this.aldeaos.add(aldeao);
            Tela.i.mostrarFazenda(this.getID(), this.formatarTextoAldeoes());
            Thread.sleep(this.vila.props.fazenda.getTempoUso());
            this.aldeaos.remove(aldeao);
            Tela.i.mostrarFazenda(this.getID(), this.formatarTextoAldeoes());

            // Liberar espaço fazenda
            this.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String formatarTextoAldeoes() {
        String[] idsAldeoes = this.aldeaos.stream().map(aldeao -> String.valueOf(aldeao.getID())).toArray(String[]::new);
        return String.join(", ", idsAldeoes);
    }
}
