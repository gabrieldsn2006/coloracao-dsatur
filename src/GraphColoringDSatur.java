import java.util.HashSet;
import java.util.Set;

public class GraphColoringDSatur {
    private final Graph graph;

    // Arrays para guardar o estado da coloração
    private int[] colors;
    private int[] coloringOrder;
    private int colorCount;
    private boolean isColored;

    // Mapeamento dos estados do Brasil exigido nas instruções (ordem alfabética por sigla)
    private static final String[] ESTADOS = {
            "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
            "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN",
            "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    };

    public GraphColoringDSatur(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
        this.colors = new int[graph.V()]; // Inicializa todas as posições com 0 (sem cor)
        this.coloringOrder = new int[graph.V()];
        this.colorCount = 0;
        this.isColored = false;
    }

    public Graph getGraph() {
        return graph;
    }

    /**
     * Algoritmo DSatur (Degree of Saturation) para coloração.
     */
    public void color() {
        int V = graph.V();
        boolean[] uncolored = new boolean[V]; // Representa o conjunto R
        int[] degrees = new int[V];           // Cache do grau estático dos vértices

        int maxDegree = -1;
        int firstVertex = -1;

        // Prepara os graus e encontra o vértice de maior grau inicial
        for (int v = 0; v < V; v++) {
            uncolored[v] = true;
            degrees[v] = graph.degree(v);

            if (degrees[v] > maxDegree) {
                maxDegree = degrees[v];
                firstVertex = v;
            }
        }

        // Passo 1, 2 e 3: Colorir o vértice de maior grau com a cor 1
        colors[firstVertex] = 1;
        coloringOrder[0] = firstVertex;
        uncolored[firstVertex] = false; // Remove de R
        colorCount = 1;

        // Passo 4: Enquanto R não estiver vazio (restam V-1 vértices)
        for (int i = 1; i < V; i++) {
            int selectedVertex = -1;
            int maxDS = -1;
            int maxDegInTie = -1;

            // a. Calcular DS para todo v em R e b. escolher o que maximiza DS
            for (int v = 0; v < V; v++) {
                if (uncolored[v]) {
                    int currentDS = calculateDS(v);

                    if (currentDS > maxDS) {
                        maxDS = currentDS;
                        selectedVertex = v;
                        maxDegInTie = degrees[v];
                    } else if (currentDS == maxDS) { // Empate de saturação: desempate por grau
                        if (degrees[v] > maxDegInTie) {
                            selectedVertex = v;
                            maxDegInTie = degrees[v];
                        }
                    }
                }
            }

            // c. Encontrar a menor cor (k) viável para o vértice selecionado
            int k = 1;
            boolean[] usedColorsByNeighbors = new boolean[colorCount + 2];

            for (int w : graph.adj(selectedVertex)) {
                if (colors[w] > 0) {
                    usedColorsByNeighbors[colors[w]] = true; // Marca cor do vizinho como indisponível
                }
            }

            // Acha a primeira cor disponível (falsa)
            while (usedColorsByNeighbors[k]) {
                k++;
            }

            // d. Atribuir a cor k ao vértice
            colors[selectedVertex] = k;
            coloringOrder[i] = selectedVertex;

            // e. Atualizar o total de cores usadas se necessário
            if (k > colorCount) {
                colorCount = k;
            }

            // f. Remove o vértice do conjunto R
            uncolored[selectedVertex] = false;
        }

        isColored = true;
    }

    /**
     * Calcula o Grau de Saturação (DS): Número de cores DISTINTAS dos vizinhos.
     */
    private int calculateDS(int v) {
        Set<Integer> distinctColors = new HashSet<>();
        for (int w : graph.adj(v)) {
            if (colors[w] > 0) { // Se o vizinho já foi colorido
                distinctColors.add(colors[w]);
            }
        }
        return distinctColors.size();
    }

    public int getColor(int vertex) {
        if (!isColored) throw new IllegalStateException("Execute color() antes de pedir cores.");
        return colors[vertex];
    }

    public int getColorCount() {
        if (!isColored) throw new IllegalStateException("Execute color() antes.");
        return colorCount;
    }

    public int[] getColoringOrder() {
        if (!isColored) throw new IllegalStateException("Execute color() antes.");
        return coloringOrder;
    }

    public boolean isValidColoring() {
        if (!isColored) return false;

        // Verifica para cada aresta se as cores dos vértices são iguais (o que invalidaria a coloração)
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (colors[v] == colors[w]) {
                    return false; // Conflito encontrado!
                }
            }
        }
        return true;
    }

    public String getLabel(int vertex) {
        if (vertex < 0 || vertex >= ESTADOS.length) {
            return "Desconhecido";
        }
        return ESTADOS[vertex];
    }
}