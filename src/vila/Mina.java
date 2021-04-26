package vila;

import tela.Tela;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Mina {
    private int id;
    private Vila vila;
    private final ArrayList<Aldeao> aldeaos;

    private Semaphore semaphore;

    public Mina(int id, Vila vila) {
        this.id = id;
        this.aldeaos = new ArrayList<>();
        this.vila = vila;
        this.semaphore = new Semaphore(5);

        Tela.i.adicionarMinaOuro(String.valueOf(id), "");
    }

    public int getID() {
        return this.id;
    }

    public void minerar(Aldeao aldeao) {
        try {
            // Tenta iniciar mineracao, caso limite tenha excedido aguardar
            this.semaphore.acquire();

            this.aldeaos.add(aldeao);
            Tela.i.mostrarMinaOuro(this.getID(), this.formatarTextoAldeoes());
            Thread.sleep(this.vila.props.mina.getTempoUso());
            this.aldeaos.remove(aldeao);
            Tela.i.mostrarMinaOuro(this.getID(), this.formatarTextoAldeoes());

            // Liberar espaço Mina
            this.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String formatarTextoAldeoes() {
        synchronized (this.aldeaos) {
            String[] idsAldeoes = this.aldeaos.stream().map(aldeao -> String.valueOf(aldeao.getID())).toArray(String[]::new);
            return String.join(", ", idsAldeoes);
        }
    }
}
