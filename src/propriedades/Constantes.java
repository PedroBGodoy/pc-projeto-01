package propriedades;

/**
 * Classe para armazenar parametros base do jogo
 */
public class Constantes {

    // GERAL
    /**
     * Representa 1 hora em jogo. Valor definido em milisegundos (ms)
     */
    public static final int HORA_BASE = 100;

    // ALDEAO
    public static final int TEMPO_PRODUCAO_ALDEAO_MULT = 20;
    public static final int CUSTO_OURO_ALDEAO = 0;
    public static final int CUSTO_COMIDA_ALDEAO = 100;
    public static final int TEMPO_SACRIFICIO_ALDEAO_MULT = 10;

    // FAZENDA
    public static final int TEMPO_CONSTRUCAO_FAZENDA_MULT = 30;
    public static final int TEMPO_USO_FAZENDA_MULT = 1;
    public static final int TEMPO_TRANSPORTE_FAZENDA_MULT = 2;
    public static final int CUSTO_OURO_FAZENDA = 500;
    public static final int CUSTO_COMIDA_FAZENDA = 100;
    public static final int PRODUCAO_FAZENDA_CICLO = 10;

    // MINA
    public static final int TEMPO_CONSTRUCAO_MINA_MULT = 40;
    public static final int TEMPO_USO_MINA_MULT = 2;
    public static final int TEMPO_TRANSPORTE_MINA_MULT = 3;
    public static final int CUSTO_OURO_MINA = 0;
    public static final int CUSTO_COMIDA_MINA = 1000;
    public static final int PRODUCAO_MINA_CICLO = 5;
}
