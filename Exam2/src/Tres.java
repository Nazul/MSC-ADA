import java.util.Stack;

public class Tres {
	static boolean ExistsRoute(int[][] map, int T, int start, int end) {
		int[][] map1 = new int[map.length][map.length];
	    for (int i = 0; i < map.length; i++) {
	        System.arraycopy(map[i], 0, map1[i], 0, map1[i].length);
	    }
		// Eliminamos las rutas que no podemos usar
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1.length; j++) {
				map1[i][j] = map1[i][j] - T;
			}
		}

		// DF Search
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(start);
		boolean[] visited = new boolean[map1.length];
		
		while(!stack.isEmpty()) {
			int currentIndex = stack.pop();
			visited[currentIndex] = true;
			if(currentIndex == end) return true;
			for(int i = 0; i < map1.length; i ++) {
				if(map1[currentIndex][i] >= 0 && !visited[i]) {
					stack.push(i);
				}
			}
		}

		return false;
	}
	
	public static void main(String[] args) {
		int[][] graph1 = {
				{ 0, 20,  15,  0},
				{20,  0,   0, 10},
				{15,  0,   0, 25},
				{ 0, 10,  25,  0}
		};
		int[][] graph2 = {
				{ 0, 14, 10,  0,  0,  0,  0,  0,  0 },
				{ 14, 0,  0, 14,  0,  0,  0,  0,  0 },
				{ 10, 0,  0, 10,  0,  0,  0,  0,  0 },
				{ 0, 14, 10,  0, 14, 10,  0,  0,  0 },
				{ 0,  0,  0, 14,  0, 14,  0, 10,  0 },
				{ 0,  0,  0, 10, 14,  0,  0, 14,  0 },
				{ 0,  0,  0,  0,  0,  0,  0, 10, 10 },
				{ 0,  0,  0,  0, 10, 14, 10,  0, 14 },
				{ 0,  0,  0,  0,  0, 10, 10, 14,  0 }
		};
		System.out.println(ExistsRoute(graph1, 12, 0, 3));
		System.out.println(ExistsRoute(graph1, 15, 0, 3));
		System.out.println(ExistsRoute(graph1, 16, 0, 3));
		System.out.println(ExistsRoute(graph2, 15, 0, 8));
	}
}
