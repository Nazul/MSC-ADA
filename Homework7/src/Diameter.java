//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 7, C. Diameter of Social Networks
//
// Mario Contreras (705080)
//
//*********************************************************
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
/*
Sample Input
1
6 6
1 2
1 3
3 4
2 4
3 5
5 6

Sample Output
4
*/

class Diameter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int N = sc.nextInt();
		
		// For each test case
		for(int n = 0; n < N; n++) {
			// How many friends (vertices)
			int F = sc.nextInt();
			// How many connections (edges)
			int P = sc.nextInt();
			// Let's build a (empty) graph for this social network
			int[][] sn = new int[F][F];
			for (int i = 0; i < sn.length; i++) {
				for (int j = 0; j < sn.length; j++) {
					sn[i][j] = INF;
				}
			}
			// Add connections (edges)
			for(int p = 0; p < P; p++) {
				// Values from scanner
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				// One undirected edge or two directed edges... same for this particular problem (Enforced-Mutual-Friend model)
				sn[a][b] = 1;
				sn[b][a] = 1;
			}
			// Let's get the distance between vertices using each vertex as a start.
			// For each list, we get the maximum value and compare with the previous results
			// Store the maximum value only
			int max = 0;
			for (int i = 0; i < sn.length; i++) {
				int[] result = dijkstra(sn, i);
				Arrays.sort(result);
				max = Math.max(max, result[F - 1]);
			}
			// Then print it.
			System.out.println(max);
		}
		System.out.println();
		sc.close();
	}

	static int INF = Integer.MAX_VALUE;
	
	static int[] dijkstra(int[][] graph, int start) {
		Stack<Integer> nodeStack = new Stack<>();
		Stack<Integer>  distStack = new Stack<>();
		boolean[] visited = new boolean[graph.length];
		int[] minDistances = new int[graph.length];
		for(int i = 0; i < minDistances.length; i ++) minDistances[i] = INF;
		minDistances[start] = 0;
		nodeStack.push(start);
		distStack.push(0);
		while(!nodeStack.isEmpty()) {
			int currentNode = nodeStack.pop();
			int currentDist = distStack.pop();
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
}

//EOF
