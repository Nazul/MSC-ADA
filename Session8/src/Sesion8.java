import java.util.HashMap;
import java.util.Stack;

public class Sesion8 {

	static boolean dfSearch(boolean[][] graph, HashMap<String, Integer> map, String start, String end) {		
		Stack<Integer> stack = new Stack<Integer>();  // nodos pendientes por procesar
		int startIndex = map.getOrDefault(start, -1);
		int endIndex   = map.getOrDefault(end, -1);
		if(startIndex < 0 || endIndex < 0) return false;
		
		stack.push(startIndex);
		boolean[] visited = new boolean[graph.length];
		
		while(!stack.isEmpty()) {
			int currentIndex = stack.pop();
			visited[currentIndex] = true;
			if(currentIndex == endIndex) return true;
			for(int i = 0; i < graph.length; i ++) {
				if(graph[currentIndex][i] && !visited[i]) {
					stack.push(i);
				}
			}
		}		
		
		return false;
	}
	
	public static void main(String[] args) {
		String names[] = {"A", "B", "C", "D", "E", "F", "G"};
		boolean t = true, f = false;
		boolean[][] graph = { { f, t, t, f, f, f, f },
							  { t, f, t, f, f, f, f },
							  { t, t, f, f, f, f, f },
							  { f, f, f, f, t, t, f },
							  { f, f, f, t, f, f, t },
							  { f, f, f, t, f, f, t },
							  { f, f, f, f, t, t, f }
		};
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < names.length; i ++) map.put(names[i], i);
		
		System.out.println(dfSearch(graph, map, "A", "C"));
		System.out.println(dfSearch(graph, map, "D", "G"));
		System.out.println(dfSearch(graph, map, "B", "F"));
		System.out.println(dfSearch(graph, map, "E", "A"));
		System.out.println(dfSearch(graph, map, "H", "A"));
	}

}
