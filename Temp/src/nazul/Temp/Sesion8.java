package nazul.Temp;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Sesion8 {

	static boolean dfSearch(boolean[][] graph, HashMap<String, Integer> map, String start, String end) {		
		PriorityQueue<Integer> stack = new PriorityQueue<Integer>();  // nodos pendientes por procesar
		int startIndex = map.getOrDefault(start, -1);
		int endIndex   = map.getOrDefault(end, -1);
		if(startIndex < 0 || endIndex < 0) return false;
		
		stack.add(startIndex);
		boolean[] visited = new boolean[graph.length];
		int count = 0;
		
		while(!stack.isEmpty()) {
			int currentIndex = stack.remove();
			visited[currentIndex] = true;
			count++;
			if(currentIndex == endIndex) {System.out.println(count);return true;};
			for(int i = 0; i < graph.length; i ++) {
				if(graph[currentIndex][i] && !visited[i]) {
					stack.add(i);
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String names[] = {"1", "2", "3", "4", "5", "6"};
		boolean t = true, f = false;
		boolean[][] graph3 = {
				{f, t, t, f, f, f},
				{t, f, f, t, f, f},
				{t, f, f, t, t, f},
				{f, t, t, f, f, f},
				{f, f, t, f, f, t},
				{f, f, f, f, t, f}
		};
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < names.length; i ++) map.put(names[i], i);

		System.out.println(dfSearch(graph3, map, "1", "1"));
		System.out.println(dfSearch(graph3, map, "1", "2"));
		System.out.println(dfSearch(graph3, map, "1", "3"));
		System.out.println(dfSearch(graph3, map, "1", "4"));
		System.out.println(dfSearch(graph3, map, "1", "5"));
		System.out.println(dfSearch(graph3, map, "1", "6"));

		
//		System.out.println(dfSearch(graph, map, "A", "C"));
//		System.out.println(dfSearch(graph, map, "D", "G"));
//		System.out.println(dfSearch(graph, map, "B", "F"));
//		System.out.println(dfSearch(graph, map, "E", "A"));
//		System.out.println(dfSearch(graph, map, "H", "A"));
	}

}
