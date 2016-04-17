import java.util.Arrays;

public class AgenteViajero {
	static int minTime;
	static int[] bestRoute;
	
	static void processSolution(int[][] graph, int start, int k, int[] solution, boolean[] visited) {
		int N = graph.length;
		if(k == N - 1) {
			// 0-->3-->2-->1-->0
			int time = graph[start][solution[0]] + graph[solution[N - 2]][start];
			
			for (int i = 0; i < N - 2; i++) {
				time += graph[solution[i]][solution[i + 1]];
			}
			if(time < minTime) {
				minTime = time;
				bestRoute = solution;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if(visited[i]) continue; // start ya fue visitado
				int[] newSolution = Arrays.copyOf(solution, solution.length);
				newSolution[k] = i;
				boolean[] newVisited = Arrays.copyOf(visited, visited.length);
				newVisited[i] = true;
				processSolution(graph, start, k + 1, newSolution, newVisited);
			}
		}
	}
	
	static void calculateMinTime(int[][] graph, int start) {
		int N = graph.length;
		minTime = Integer.MAX_VALUE;
		bestRoute = new int[N - 1];
		
		for (int i = 0; i < N; i++) {
			if(i == start) continue;
			int[] solution = new int[N - 1];
			solution[0] = i;
			boolean[] visited = new boolean[N];
			visited[start] = true;
			visited[i] = true;
			processSolution(graph, start, 1, solution, visited);
		}
		
		System.out.println("  Min time: " + minTime);
		System.out.println("Best route: " + start + "," + Arrays.toString(bestRoute) + ", " + start);
	}
	
	static void fasterProcessSolution(int[][] graph, int start, int k, int[] solution, boolean[] visited, int currentTime) {
		int N = graph.length;
		if(k == N - 1) {
			// 0-->3-->2-->1-->0
			int time = currentTime + graph[solution[N - 2]][start];
			
			if(time < minTime) {
				minTime = time;
				bestRoute = solution;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if(visited[i]) continue; // start ya fue visitado
				if(minTime < currentTime + graph[solution[k - 1]][i]) continue; // este camino no tiene futuro
				int[] newSolution = Arrays.copyOf(solution, solution.length);
				newSolution[k] = i;
				boolean[] newVisited = Arrays.copyOf(visited, visited.length);
				newVisited[i] = true;
				fasterProcessSolution(graph, start, k + 1, newSolution, newVisited, currentTime + graph[solution[k - 1]][i]);
			}
		}
	}
	
	static void fasterCalculateMinTime(int[][] graph, int start) {
		int N = graph.length;
		minTime = Integer.MAX_VALUE;
		bestRoute = new int[N - 1];
		
		for (int i = 0; i < N; i++) {
			if(i == start) continue;
			int[] solution = new int[N - 1];
			solution[0] = i;
			boolean[] visited = new boolean[N];
			visited[start] = true;
			visited[i] = true;
			fasterProcessSolution(graph, start, 1, solution, visited, graph[start][i]);
		}
		
		System.out.println("  Min time: " + minTime);
		System.out.println("Best route: " + start + "," + Arrays.toString(bestRoute) + ", " + start);
	}
	
	public static int[][] randomGraph(int N, int max) {
		int[][] g = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				g[i][j] = (int) (max * Math.random());
			}
		}
		return g;
	}
	
	public static void main(String[] args) {
		int[][] graph = {
				{0, 4, 1, 2},
				{3, 0, 5, 6},
				{2, 3, 0, 2},
				{3, 8, 2, 0},
				};
		fasterCalculateMinTime(graph, 0);
		fasterCalculateMinTime(randomGraph(20, 1000), 0);
	}
}
