import java.util.Arrays;

public class AgenteViajero {

//  **** BACKTRACKING *****
	static int minTime;
	static int[] bestRoute;

	static void fasterProcessSolution(int graph[][], int start, int k, int[] solution, boolean[] visited, int currentTime) {
		int N = graph.length;
		if(k == N - 1) {
			int time = currentTime + graph[solution[k - 1]][start];
			if(time < minTime) {
				minTime = time;
				bestRoute = solution;
			}
		} else {
			for(int i = 0; i < N; i ++) {
				if(visited[i]) continue;	// el nodo ya fue visitado
				if(minTime < currentTime + graph[solution[k - 1]][i]) continue;	// este camino no tiene futuro
				int[] newSolution = Arrays.copyOf(solution, solution.length);
				newSolution[k] = i;
				boolean[] newVisited = Arrays.copyOf(visited, visited.length);
				newVisited[i] = true;
				fasterProcessSolution(graph, start, k + 1, newSolution, newVisited, currentTime + graph[solution[k - 1]][i]);
			}
		}
	}
	
	static void fasterCalculateMinTime(int graph[][], int start) {
		int N = graph.length;
		minTime = Integer.MAX_VALUE;
		bestRoute = new int[N - 1];
		
		for(int i = 0; i < N; i ++) {
			if(i == start) continue;
			int[] solution 	  = new int[N - 1];
			solution[0] 	  = i;
			boolean[] visited = new boolean[N];
			visited[start]    = true;
			visited[i]        = true;
			fasterProcessSolution(graph, start, 1, solution, visited, graph[start][i]);
		}
		
		System.out.println("Min time: " + minTime);
		System.out.println("Best route: " + start + ", " + Arrays.toString(bestRoute) + ", " + start);
	}
	
//	**** HILL-CLIMBING *****	
	static int rand(int a, int b) {
		return a + (int) ((b - a + 1) * Math.random());
	}
	
	static void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;
	}
	
	static int getTime(int[] solution, int[][] graph, int start) {
		int time = graph[start][solution[0]] + graph[solution[solution.length - 1]][start];
		for(int i = 0; i < solution.length - 1; i ++) {
			time += graph[solution[i]][solution[i + 1]];
		}
		return time;
	}
	
	static void runHC(int G, int[][] graph, int start) {
//		Crear ruta aleatoria válida: permutación aleatoria (Fisher-Yates)
		int[] solution = new int[graph.length - 1];
		for(int s = 0, g = 0; g < graph.length; g ++) {
			if(g == start) continue;
			solution[s] = g;
			s ++;
		}
		for(int s = 0; s < solution.length - 1; s ++) {
			int t = rand(s + 1, solution.length - 1);
			swap(solution, s, t);
		}
		int time = getTime(solution, graph, start);		
		for(int g = 0; g < G; g ++) {
//			int maxWeight = graph[start][solution[0]];
//			int maxS = 0;
//			for(int s = 0; s < solution.length - 1; s ++) {
//				if(graph[solution[s]][solution[s + 1]] > maxWeight) {
//					maxWeight = graph[solution[s]][solution[s + 1]];
//					maxS = s + 1;
//				}
//			}
			int i1 = rand(0, solution.length - 1);
			int i2 = rand(0, solution.length - 1);
			while(i2 == i1) i2 = rand(0, solution.length - 1);
			swap(solution, i1, i2);
			int time1 = getTime(solution, graph, start);
			if(time1 < time) {
				time = time1;
			} else {
				swap(solution, i1, i2);
			}
		}
		System.out.println("Min time: " + time);
		System.out.println("Best route: " + start + ", " + Arrays.toString(solution) + ", " + start);		
	}
	
	static void runRandom(int G, int[][] graph, int start) {
		int bestTime = Integer.MAX_VALUE;
		int[] bestSolution = new int[graph.length - 1];
		for(int gen = 0; gen < G; gen ++) {
	//		Crear ruta aleatoria válida: permutación aleatoria (Fisher-Yates)
			int[] solution = new int[graph.length - 1];
			for(int s = 0, g = 0; g < graph.length; g ++) {
				if(g == start) continue;
				solution[s] = g;
				s ++;
			}
			for(int s = 0; s < solution.length - 1; s ++) {
				int t = rand(s + 1, solution.length - 1);
				swap(solution, s, t);
			}
			int time = getTime(solution, graph, start);
			if(time < bestTime) {
				bestTime = time;
				bestSolution = solution;
			}
		}
		System.out.println("Min time: " + bestTime);
		System.out.println("Best route: " + start + ", " + Arrays.toString(bestSolution) + ", " + start);		
	}
	
	public static int[][] randomGraph(int N, int max) {
		int g[][] = new int[N][N];
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < N; j ++) {
				if(i == j) continue;
				g[i][j] = (int) (max * Math.random());
			}
		}
		return g;
	}
	
	public static void main(String[] args) {
		int graph[][] = randomGraph(500, 1000);
		runRandom(1_000_000, graph, 0);
		runHC(1_000_000, graph, 0);
//		fasterCalculateMinTime(graph, 0);
	}
}
