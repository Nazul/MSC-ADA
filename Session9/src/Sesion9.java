import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Sesion9 {

	static double INF = Double.MAX_VALUE;
	
	static class Edge implements Comparable<Edge> {
		int vertex1, vertex2;
		double weight;
		public Edge(int v1, int v2, double w) {
			vertex1 = v1;
			vertex2 = v2;
			weight  = w;
		}
		public int compareTo(Edge e) {
//			Devolver negativo si esta arista es menor que la arista e
//			Devolver 0 si son iguales y positivo en otro caso
			if(this.weight < e.weight) return -1;
			if(this.weight > e.weight) return  1;
			return 0;
//			return Double.compare(this.weight, e.weight);
		}
	}

	public static List<Edge> prim(double[][] graph, int start) {
		return null;
	}

	public static int findLeader(int[] parents, int x) {
		while(parents[x] != x) x = parents[x];
		return x;		
	}
	
	public static boolean join(int[] parents, int[] rank, int i, int j) {
		int leaderI = findLeader(parents, i);
		int leaderJ = findLeader(parents, j);
		if(leaderI == leaderJ) return false;
		int rankLeaderI = rank[leaderI];
		int rankLeaderJ = rank[leaderJ];
		if(rankLeaderI > rankLeaderJ || rankLeaderI == rankLeaderJ && leaderI > leaderJ) {
			parents[leaderJ] = leaderI;
			rank[leaderI] += rank[leaderJ];
		} else {
			parents[leaderI] = leaderJ;
			rank[leaderJ] += rank[leaderI];
		}			
		return true;
	}
	
	public static List<Edge> kruskal(double[][] graph, int start) {
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		List<Edge> arm = new ArrayList<Edge>(graph.length - 1);
		for(int i = 0; i < graph.length; i ++) {
			for(int j = i + 1; j < graph[0].length; j ++) {
				if(graph[i][j] != INF) {
					Edge e = new Edge(i, j, graph[i][j]);
					queue.offer(e);
				}
			}
		}
		int[] parents = new int[graph.length];
		for(int i = 0; i < parents.length; i ++) parents[i] = i;
		int[] rank = new int[graph.length];
		for(int i = 0; i < rank.length; i ++) rank[i] = 1;
		
		while(arm.size() < graph.length - 1) {
			Edge minEdge = queue.poll();
			if(join(parents, rank, minEdge.vertex1, minEdge.vertex2) ) {
				arm.add(minEdge);
			}
		}
		return arm;
	}
	
	public static double[] dijkstra(double[][] graph, int start) {
		Stack<Integer> nodeStack = new Stack<Integer>(); 
		Stack<Double>  distStack = new Stack<Double>();
		boolean[] visited = new boolean[graph.length];
		double[] minDistances = new double[graph.length];
		for(int i = 0; i < minDistances.length; i ++) minDistances[i] = INF;
		minDistances[start] = 0;
		nodeStack.push(start);
		distStack.push(0.0);
		while(!nodeStack.isEmpty()) {
			int currentNode = nodeStack.pop();
			double currentDist = distStack.pop();
			if(visited[currentNode] && minDistances[currentNode] <= currentDist) continue;
			visited[currentNode] = true;
			minDistances[currentNode] = currentDist;
			for(int i = 0; i < graph.length; i ++) {
				if(graph[currentNode][i] != INF) {
					nodeStack.push(i);
					distStack.push(currentDist + graph[currentNode][i]);
				}
			}
		}
		return minDistances;
	}
	
	public static void main(String[] args) {
		double[][] graph0 = { { INF, 2, 5.5, 4 }, { INF, INF, 3, 1 }, { INF, INF, INF, INF }, { INF, INF, INF, INF }};
		double[] dist = dijkstra(graph0, 0);
		for(double d : dist) System.out.println(d);
		
		double[][] graph1 = {  { INF, INF, INF,   4, INF,   2 },
							   { INF, INF,   5, INF,   3, INF },
							   { INF,   5, INF,   1,   3,   2 },
							   {   4, INF,   1, INF,   4,   3 },
							   { INF,   3,   3,   4, INF, INF },
							   {   2, INF,   2,   3, INF, INF },
		};
		List<Edge> arm = kruskal(graph1, 0);
		for(Edge e : arm) System.out.println(e.vertex1 + ", " + e.vertex2 + ", " + e.weight);
	}
}
