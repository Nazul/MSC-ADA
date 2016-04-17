//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 7, A. Dennis Dotter
//
// Mario Contreras (705080)
//
//*********************************************************
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
Sample Input
1
3
1.0 1.0
2.0 2.0
2.0 4.0

Sample Output
3.41
*/

class Dennis {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int T = sc.nextInt();
		
		// For each test case
		for(int t = 0; t < T; t++) {
			// How many dots / points
			int N = sc.nextInt();
			// Array of points
			Point[] points = new Point[N];
			for(int n = 0; n < N; n++) {
				// Populate the array with the values from the scanner
				points[n] = new Point(sc.nextDouble(), sc.nextDouble());
			}
			// Let's build a complete graph using the dots.
			double[][] graph = new double[N][N];
			for (int i = 0; i < graph.length; i++) {
				for (int j = i; j < graph.length; j++) {
					// Complete graph - removing loops
					if(i == j) graph[i][j] = INF;
					// Distance between points is the weight of the edge
					else graph[i][j] = Math.sqrt(Math.pow(points[j].x - points[i].x, 2) + Math.pow(points[j].y - points[i].y, 2));
				}
			}
			// Calculating the minimum spanning tree
			List<Edge> mst = kruskal(graph, 0);
			// Get the distance (ink)
			double total = 0;
			for(Edge e : mst) total += e.weight;
			// Then print it.
			System.out.printf("%.2f\n", total);
		}
		System.out.println();
		sc.close();
	}
	
	static double INF = Double.MAX_VALUE;
	
	static class Point {
		double x, y;
		public Point(double x, double y) {
			this.x = x; this.y = y;
		}
		@Override
		public String toString() {
			return String.format("[%.2f, %.2f]", x, y);
		}
	}

	static class Edge implements Comparable<Edge> {
		int vertex1, vertex2;
		double weight;
		public Edge(int v1, int v2, double w) {
			vertex1 = v1;
			vertex2 = v2;
			weight  = w;
		}
		public int compareTo(Edge e) {
			return Double.compare(this.weight, e.weight);
		}
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

	public static int findLeader(int[] parents, int x) {
		while(parents[x] != x) x = parents[x];
		return x;		
	}
}

//EOF
