package vila;

import tela.Tela;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Fazenda {
    private int id;
    private Vila vila;
    private final ArrayList<Aldeao> aldeaos;

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

            this.adicionarAldeao(aldeao);
            Tela.i.mostrarFazenda(this.getID(), this.formatarTextoAldeoes());
            Thread.sleep(this.vila.props.fazenda.getTempoUso());
            this.removerAldeao(aldeao);
            Tela.i.mostrarFazenda(this.getID(), this.formatarTextoAldeoes());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Liberar espaço fazenda
            this.semaphore.release();
        }
    }

    private synchronized void adicionarAldeao(Aldeao aldeao) {
        this.aldeaos.add(aldeao);
    }

    private synchronized void removerAldeao(Aldeao aldeao) {
        this.aldeaos.remove(aldeao);
    }

    private String formatarTextoAldeoes() {
        // Faz copia antes de utilizar para não ter conflito
        ArrayList<Aldeao> copia = new ArrayList<>(this.aldeaos);
        String[] idsAldeoes = copia
                .stream()
                .filter(Objects::nonNull)
                .map(aldeao -> String.valueOf(aldeao.getID()))
                .toArray(String[]::new);
        return String.join(", ", idsAldeoes);
    }

    public void evoluir(int aumento) {
        this.semaphore.release(aumento);
    }
}
