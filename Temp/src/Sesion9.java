import java.util.ArrayList;
import java.util.Arrays;
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
		double[][] graph0 = {
				{         INF, 1.414213562, 3.16227766 },
				{ 1.414213562,         INF,          2 },
				{  3.16227766,           2,        INF }
		};
//		double[] dist = dijkstra(graph0, 0);
//		for(double d : dist) System.out.println(d);
		
		double[][] graph1 = {  { INF, INF, INF,   4, INF,   2 },
				   { INF, INF,   5, INF,   3, INF },
				   { INF,   5, INF,   1,   3,   2 },
				   {   4, INF,   1, INF,   4,   3 },
				   { INF,   3,   3,   4, INF, INF },
				   {   2, INF,   2,   3, INF, INF },
        };
		double[][] graph2 = {
				{INF, 964.141587112598, 1220.34298457442, 784.780224011793, 933.312916443355, 60.4152298679729, 336.005952328229, 933.370773058595, 322.530618701543, 1126.60995912516},
				{964.141587112598, INF, 297.324738291317, 645.071313887077, 411.672199692911, 984.360198301414, 754.597243567719, 32.649655434629, 642.779122249626, 177.417586501451},
				{1220.34298457442, 297.324738291317, INF, 715.967178018657, 426.755199148177, 1232.16110959566, 1042.55887123941, 328.901200970747, 907.273387684219, 260.447691485258},
				{784.780224011793, 645.071313887077, 715.967178018657, INF, 290.043100245464, 762.345066226574, 825.408989531856, 636.447169842085, 605.324706252768, 805.62646431209},
				{933.312916443355, 411.672199692911, 426.755199148177, 290.043100245464, INF, 927.759128222407, 866.028290530973, 414.391119595968, 669.63049512399, 547.244917747072},
				{60.4152298679729, 984.360198301414, 1232.16110959566, 762.345066226574, 927.759128222407, INF, 389.935892166905, 954.289788271886, 349.542558210013, 1150.40340750538},
				{336.005952328229, 754.597243567719, 1042.55887123941, 825.408989531856, 866.028290530973, 389.935892166905, INF, 721.959140118054, 220.857420070053, 891.80379007941},
				{933.370773058595, 32.649655434629, 328.901200970747, 636.447169842085, 414.391119595968, 954.289788271886, 721.959140118054, INF, 611.667393278406, 201.853907566834},
				{322.530618701543, 642.779122249626, 907.273387684219, 605.324706252768, 669.63049512399, 349.542558210013, 220.857420070053, 611.667393278406, INF, 804.079598049845},
				{1126.60995912516, 177.417586501451, 260.447691485258, 805.62646431209, 547.244917747072, 1150.40340750538, 891.80379007941, 201.853907566834, 804.079598049845, INF}
		};
		double[][] graph3 = {
				{INF, 1, 1, INF, INF, INF},
				{1, INF, INF, 1, INF, INF},
				{1, INF, INF, 1, 1, INF},
				{INF, 1, 1, INF, INF, INF},
				{INF, INF, 1, INF, INF, 1},
				{INF, INF, INF, INF, 1, INF}
		};
		double[][] graph4 = {
				{INF,   1, INF,   1, INF, INF},
				{INF, INF,   1, INF, INF, INF},
				{INF,   1, INF, INF,   1,   1},
				{  1, INF, INF, INF, INF, INF},
				{INF, INF,   1, INF, INF, INF},
				{INF, INF,   1, INF, INF, INF}
		};
		System.out.println(Arrays.toString(dijkstra(graph4, 0)));
		System.out.println(Arrays.toString(dijkstra(graph4, 1)));
		System.out.println(Arrays.toString(dijkstra(graph4, 2)));
		System.out.println(Arrays.toString(dijkstra(graph4, 3)));
		System.out.println(Arrays.toString(dijkstra(graph4, 4)));
		//for(Edge e : arm) System.out.println(e.vertex1 + ", " + e.vertex2 + ", " + e.weight);
//		for(int i = 0; i < graph3.length; i++)
//			System.out.println(prim(graph3, i).size());
	}
}
