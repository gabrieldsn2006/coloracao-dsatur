public class Main {
    // Reset
    public static final String RESET = "\u001B[0m";

    // Cores
    public static final String PRETO = "\u001B[30m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";
    public static final String BRANCO = "\u001B[37m";

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException(
                    "informe o arquivo de entrada. Ex.: java -cp ../out Main ../dados/brasil.txt"
            );
        }

        // Leitura do arquivo padronizada com a biblioteca algs4
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        GraphColoringDSatur dsatur = new GraphColoringDSatur(graph);

        StdOut.println("========== Grafo carregado ==========");
        StdOut.println(graph);
        StdOut.println("=====================================\n");

        // Executando o algoritmo implementado
        dsatur.color();

        StdOut.println("----- Ordem de Coloração (DSatur) -----");
        int[] order = dsatur.getColoringOrder();
        for (int i = 0; i < order.length; i++) {
            int v = order[i];
            StdOut.println(String.format("%2dº : %s (v%2d) -> %sCOR %s%s",
                    i+1, dsatur.getLabel(v), v,
                    getAnsiColor(dsatur.getColor(v)), dsatur.getColor(v), RESET));
        }
        StdOut.println();

        printColoredAdjacencyList(graph, dsatur);
        StdOut.println();

        StdOut.println("Total de cores utilizadas na resolução: " + dsatur.getColorCount());
        StdOut.println();

        // Validar e informar se a coloração produzida é válida
        boolean valid = dsatur.isValidColoring();
        if (valid) {
            StdOut.println("[SUCESSO] A coloração produzida é VÁLIDA. Nenhum estado adjacente possui a mesma cor.");
        } else {
            StdOut.println("[FALHA] A coloração é INVÁLIDA. Foram detectados vizinhos compartilhando a mesma cor.");
        }
    }

    public static void printColoredAdjacencyList(Graph graph, GraphColoringDSatur dsatur) {
        StdOut.println("\n===== Lista de Adjacência Colorida =====");

        for (int v = 0; v < graph.V(); v++) {

            String corV = getAnsiColor(dsatur.getColor(v));
            String vertice = corV + dsatur.getLabel(v) + RESET;

            StdOut.print(vertice + " : ");

            for (int w : graph.adj(v)) {
                String corW = getAnsiColor(dsatur.getColor(w));
                String vizinho = corW + dsatur.getLabel(w) + RESET;

                StdOut.print(vizinho + " ");
            }

            StdOut.println();
        }

        StdOut.println("========================================\n");
    }

    public static String getAnsiColor(int color) {
        switch (color) {
            case 1: return VERMELHO;
            case 2: return VERDE;
            case 3: return AMARELO;
            case 4: return AZUL;
            case 5: return ROXO;
            case 6: return CIANO;
            default: return BRANCO;
        }
    }
}