package propriedades;

/**
 * Classe para armazenar parametros base do jogo
 */
public class Constantes {

    /**
     * Representa 1 hora em jogo. Valor definido em milisegundos (ms)
     */
    public static final int HORA_BASE = 10;

    // PROPRIEDADES INICIO JOGO
    public static final int QTD_OURO_INICIAL = 10000;
    public static final int QTD_COMIDA_INICIAL = 15000;
    public static final int QTD_ALDEOES_INICIAL = 50;
    public static final int QTD_FAZENDAS_INICIAL = 10;
    public static final int QTD_MINAS_INICIAL = 10;

    // ALDEAO
    public static final int TEMPO_PRODUCAO_ALDEAO_MULT = 20;
    public static final int CUSTO_OURO_ALDEAO = 0;
    public static final int CUSTO_COMIDA_ALDEAO = 100;
    public static final int CUSTO_OURO_EVOLUCAO_ALDEAO = 5000;
    public static final int CUSTO_COMIDA_EVOLUCAO_ALDEAO = 5000;
    public static final int TEMPO_EVOLUCAO_ALDEAO = 100;

    // FAZENDA
    public static final int TEMPO_CONSTRUCAO_FAZENDA_MULT = 30;
    public static final int TEMPO_USO_FAZENDA_MULT = 1;
    public static final int TEMPO_TRANSPORTE_FAZENDA_MULT = 2;
    public static final int CUSTO_OURO_FAZENDA = 500;
    public static final int CUSTO_COMIDA_FAZENDA = 100;
    public static final int PRODUCAO_FAZENDA_CICLO = 10;
    public static final int CUSTO_OURO_EVOLUCAO_FAZENDA = 5000;
    public static final int CUSTO_COMIDA_EVOLUCAO_FAZENDA = 500;
    public static final int TEMPO_EVOLUCAO_FAZENDA = 100;
    public static final int QTD_PRODUCAO_SIMULTANEA_FAZENDA = 5;

    // MINA
    public static final int TEMPO_CONSTRUCAO_MINA_MULT = 40;
    public static final int TEMPO_USO_MINA_MULT = 2;
    public static final int TEMPO_TRANSPORTE_MINA_MULT = 3;
    public static final int CUSTO_OURO_MINA = 0;
    public static final int CUSTO_COMIDA_MINA = 1000;
    public static final int PRODUCAO_MINA_CICLO = 5;
    public static final int CUSTO_OURO_EVOLUCAO_MINA = 1000;
    public static final int CUSTO_COMIDA_EVOLUCAO_MINA = 2000;
    public static final int TEMPO_EVOLUCAO_MINA = 100;
    public static final int QTD_PRODUCAO_SIMULTANEA_MINA = 5;

    // TEMPLO
    public static final int TEMPO_CONSTRUCAO_TEMPLO_MULT = 100;
    public static final int TEMPO_USO_TEMPLO_MULT = 5;
    public static final int CUSTO_OURO_TEMPLO = 2000;
    public static final int CUSTO_COMIDA_TEMPLO = 2000;
    public static final int PRODUCAO_TEMPLO_CICLO = 2;
    public static final int PRODUCAO_TEMPLO_SACRIFICIO = 100;

    // EVOLUÇÕES TEMPLO
    public static final int CUSTO_FE_EVOLUCAO_NUVEM_GANFANHOTOS = 1000;
    public static final int TEMPO_EVOLUCAO_NUVEM_GANFANHOTOS_MULT = 50;

    public static final int CUSTO_FE_EVOLUCAO_MORTE_PRIMOGENITOS = 1500;
    public static final int TEMPO_EVOLUCAO_MORTE_PRIMOGENITOS_MULT = 100;

    public static final int CUSTO_FE_EVOLUCAO_CHUVA_PEDRAS = 2000;
    public static final int TEMPO_EVOLUCAO_CHUVA_PEDRAS_MULT = 200;

    public static final int CUSTO_FE_EVOLUCAO_PROTECAO_NUVEM_GANFANHOTOS = 5000;
    public static final int TEMPO_EVOLUCAO_PROTECAO_NUVEM_GANFANHOTOS_MULT = 500;

    public static final int CUSTO_FE_EVOLUCAO_PROTECAO_MORTE_PRIMOGENITOS = 6000;
    public static final int TEMPO_EVOLUCAO_PROTECAO_MORTE_PRIMOGENITOS_MULT = 600;

    public static final int CUSTO_FE_EVOLUCAO_PROTECAO_CHUVA_PEDRAS = 7000;
    public static final int TEMPO_EVOLUCAO_PROTECAO_CHUVA_PEDRAS_MULT = 700;

    // ATAQUES
    public static final int CUSTO_FE_ATAQUE_NUVEM_GAFANHOTOS = 500;
    public static final int CUSTO_FE_ATAQUE_MORTE_PRIMOGENITOS = 750;
    public static final int CUSTO_FE_ATAQUE_CHUVA_PEDRAS = 10000;

    // MARAVILHA
    public static final int QTD_TIJOLOS_NECESSARIOS = 100000;
    public static final int CUSTO_COMIDA_TIJOLO = 1;
    public static final int CUSTO_OURO_TIJOLO = 1;
    public static final int TEMPO_CONSTRUCAO_TIJOLO_MULT = 10;
}
