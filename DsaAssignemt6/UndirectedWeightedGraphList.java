package DsaAssignemt6;
import java.util.*;

interface Graph {
    boolean isConnected();

    List<Integer> reachable(int a);

    List<Edge> mst();

    List<Integer> shortestPath(int a, int b);
}

class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    
    public String toString() {
        return "(" + source + " -> " + destination + " : " + weight + ")";
    }
}

class UndirectedWeightedGraph implements Graph {
    private  List<Edge> edges;
    private  Set<Integer> nodes;

    public UndirectedWeightedGraph() {
        this.edges = new ArrayList<>();
        this.nodes = new HashSet<>();
    }

    public void addElement(int  u, int v, int w) {
        edges.add(new Edge(u, v, w));
        edges.add(new Edge(v, u, w));
        nodes.add(u);
        nodes.add(v);
    }

    
    public boolean isConnected() {
        if (nodes.isEmpty())
            return true;

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int start = nodes.iterator().next();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                for (Edge edge : edges) {
                    if (edge.source == node && !visited.contains(edge.destination)) {
                        stack.push(edge.destination);
                    }
                }
            }
        }

        return visited.size() == nodes.size();
    }

   
    public ArrayList<Integer> reachable(int a) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> reachableNodes = new ArrayList<>();

        stack.push(a);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                reachableNodes.add(node);
                for (Edge edge : edges) {
                    if (edge.source == node && !visited.contains(edge.destination)) {
                        stack.push(edge.destination);
                    }
                }
            }
        }
        return reachableNodes;
    }

   
    public ArrayList<Edge> mst() {
        ArrayList<Edge> result = new ArrayList<>();
        edges.sort(Comparator.comparingInt(e -> e.weight));
        Map<Integer, Integer> parent = new HashMap<>();
        for (int node : nodes)
            parent.put(node, node);

        for (Edge edge : edges) {
            int root1 = find(edge.source, parent);
            int root2 = find(edge.destination, parent);
            if (root1 != root2) {
                result.add(edge);
                parent.put(root1, root2);
            }
        }

        
        Set<String> seen = new HashSet<>();
        ArrayList<Edge> uniqueEdges = new ArrayList<>();
        for (Edge edge : result) {
            String key = edge.source < edge.destination ? edge.source + "-" + edge.destination : edge.destination + "-" + edge.source;
            if (!seen.contains(key)) {
                seen.add(key);
                uniqueEdges.add(edge);
            }
        }

        return uniqueEdges;
    }

    private int find(int node, Map<Integer, Integer> parent) {
        if (parent.get(node) != node) {
            parent.put(node, find(parent.get(node), parent));
        }
        return parent.get(node);
    }

   
    public List<Integer> shortestPath(int a, int b) {
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        for (int node : nodes) {
            dist.put(node, Integer.MAX_VALUE);
            prev.put(node, -1);
        }
        dist.put(a, 0);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(-1, a, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.destination;

            for (Edge edge : edges) {
                if (edge.source == u) {
                    int v = edge.destination;
                    int alt = dist.get(u) + edge.weight;
                    if (alt < dist.get(v)) {
                        dist.put(v, alt);
                        prev.put(v, u);
                        pq.add(new Edge(u, v, alt));
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (Integer at = b; at != -1; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

public class  UndirectedWeightedGraphList {
    public static void main(String[] args) {

        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
        Scanner sc = new Scanner(System.in);
        

        while (true) {
            System.out.println("1  : add vertex ");
            System.out.println("2  : check is conected ? ");
            System.out.println("3  : find reachable node");
            System.out.println("4  : find shorted path ");
            System.out.println("5  : minimum spaning tree ");

            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter the vertex first ");
                    int firstVertex = sc.nextInt();
                    System.out.println("Enter the vertex second");
                    int secondVertex = sc.nextInt();
                    System.out.println("Enter the weight betwee firstvertex and secondvertex : ");
                    int weight = sc.nextInt();
                    graph.addElement(firstVertex, secondVertex, weight);

                    break;

                case 2:
                    System.out.println("is graph connected : " + graph.isConnected());
                    break;

                case 3:
                    System.out.println("enter the vertex to get reachable vertex ");
                    int reachVertex = sc.nextInt();
                    ArrayList<Integer> reachableNode = graph.reachable(reachVertex);
                    for (Integer reach : reachableNode) {
                        System.out.print(reach + " ");
                    }
                    break;

                case 4:
                    System.out.println("enter the vertex 1 ");
                    int vertexFirst = sc.nextInt();
                    System.out.println("enter the  vertex  2");
                    int vertexSecond = sc.nextInt();
                    System.out.println("\n sortest path : " + graph.shortestPath(vertexFirst, vertexSecond));

                    break;

                case 5:
                    ArrayList<Edge> mst = graph.mst();
                    for (Edge s : mst) {
                        System.out.println(s);
                    }
                    break;

                default:
                    break;
            }
           
            System.out.println("Exit press -1 ");
            int press = sc.nextInt();
            if (press == -1) {
                break;
            }

        }

    }

}