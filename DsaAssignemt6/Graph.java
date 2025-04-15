package DsaAssignemt6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Edge {
    int firstVertex;
    int secondVertex;
    int weight;

    Edge(int firstVertex, int secondVertex, int weight) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;

    }
}

interface UndiretedWeightGraph {
    void addElement(int firstVertex, int secondVertex, int weight);

    ArrayList<Edge> mst();

    boolean isConnected();

    int shortestPath(int nodeFirst, int nodeSecond);
}

class UndiretedGraph implements UndiretedWeightGraph{

    private  Map<Integer, ArrayList<Pair>> adj;
    Map<Integer, Integer> parent;

    UndiretedGraph() {
        this.adj = new HashMap<>();
        this.parent = new HashMap<>();
    }

   public void addElement(int firstVertex, int secondVertex, int weight) {
        adj.putIfAbsent(firstVertex, new ArrayList<>());
        adj.putIfAbsent(secondVertex, new ArrayList<>());
        adj.get(firstVertex).add(new Pair(secondVertex, weight));
        adj.get(secondVertex).add(new Pair(firstVertex, weight));

    }

    void dfs(int node, Set<Integer> visit) {
        visit.add(node);

        for (Pair pair : adj.get(node)) {
            if (!visit.contains(pair.first)) {
                dfs(pair.first, visit);
            }
        }
    }

   public boolean isConnected() {

        Set<Integer> visit = new HashSet<>();
        int startnode = adj.keySet().iterator().next();
        dfs(startnode, visit);
        return visit.size() == adj.size();
    }

    ArrayList<Integer> reachable(int node) {

        ArrayList<Integer> reach = new ArrayList<>();
        if (!adj.containsKey(node)) {
            System.out.println(node + " is not present in this graph : ");
            return reach;
        }
        Set<Integer> visit = new HashSet<>();

        dfs(node, visit);
        reach.addAll(visit);

        return reach;

    }

   public int shortestPath(int nodeFirst, int nodeSecond) {

        if (!adj.containsKey(nodeFirst) || !(adj.containsKey(nodeSecond))) {
            System.out.println("vertex is not preesnt in this graph ");
            return 0;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.first - y.first);
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i : adj.keySet()) {
            dist.put(i, Integer.MAX_VALUE);
        }

        dist.put(nodeFirst, 0);

        pq.add(new Pair(0, nodeFirst));
        while (pq.size() != 0) {
            int dis = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();
            for (Pair p : adj.get(node)) {
                int adjNode = p.first;
                int weight = p.second;

                if (dis + weight < dist.get(adjNode)) {
                    dist.put(adjNode, dis + weight);
                    pq.add(new Pair(dist.get(adjNode), adjNode));
                }

            }

        }

        if (dist.get(nodeSecond) != Integer.MAX_VALUE) {
            return Math.abs(dist.get(nodeFirst) - dist.get(nodeSecond));
        } else {
            return -1;
        }

    }

    int find(int x) {
        if (parent.get(x) != x)
            parent.put(x, find(parent.get(x)));
        return parent.get(x);
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py)
            parent.put(px, py);
    }

   public ArrayList<Edge> mst() {
        ArrayList<Edge> mst = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int u : adj.keySet()) {
            for (Pair pair : adj.get(u)) {
                int v = pair.first;
                int w = pair.second;
                String key = Math.min(u, v) + "-" + Math.max(u, v);
                if (!seen.contains(key)) {
                    seen.add(key);
                    edges.add(new Edge(u, v, w));
                }
            }
        }

        edges.sort((a, b) -> a.weight - b.weight);

        for (int node : adj.keySet()) {
            parent.put(node, node);
        }

        for (Edge edge : edges) {
            int pu = find(edge.firstVertex);
            int pv = find(edge.firstVertex);

            if (pu != pv) {
                mst.add(edge);
                union(pu, pv);
            }
        }

        return mst;
    }

    void print(){
         for(Entry<Integer, ArrayList<Pair>> entry : adj.entrySet()){
             System.out.print(entry.getKey() + "->");
            for(Pair p : entry.getValue()){
                 System.out.print("(" + p.first + "," + p.second +")");
            }
            System.out.println();
          
         }
    }

}

public class Graph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UndiretedGraph graph = new UndiretedGraph();

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
                    for (Edge edge : mst) {
                        System.out.println(edge.firstVertex + " " + edge.secondVertex);
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

        graph.print();

    }
}
