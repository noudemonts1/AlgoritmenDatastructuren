package algoritmen.datastructuren.noudfulco.assignment1;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Box[] boxes = Reader.readFile();
		Arrays.sort(boxes);
		Graph graph = new Graph(boxes);
		graph.printGraph();
		long endTime = System.currentTimeMillis();
		System.out.println("Running time = " + (endTime-startTime) + " ms");
	}

}
