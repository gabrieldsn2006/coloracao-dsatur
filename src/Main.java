public class Main {
    public static void main(String[] args) {
//        cores:
//        \u001B[30m → preto
//        \u001B[31m → vermelho
//        \u001B[32m → verde
//        \u001B[33m → amarelo
//        \u001B[34m → azul
//        \u001B[35m → roxo
//        \u001B[36m → ciano
//        \u001B[37m → branco

        String azul = "\u001B[34m";
        String branco = "\u001B[37m";
        System.out.println(azul + "meu texto" + branco);

        if (args.length < 1) {
            throw new IllegalArgumentException(
                    "informe o arquivo de entrada. Ex.: java Main ../dados/brasil.txt"
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
            StdOut.println((i + 1) + "º colorido: " + dsatur.getLabel(v) + " (Vértice " + v + ") -> Cor Atribuída: " + dsatur.getColor(v));
        }
        StdOut.println();

        StdOut.println("----- Cores Finais (Por Estado) -----");
        for (int v = 0; v < graph.V(); v++) {
            StdOut.println(dsatur.getLabel(v) + " -> Cor " + dsatur.getColor(v));
        }
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
}