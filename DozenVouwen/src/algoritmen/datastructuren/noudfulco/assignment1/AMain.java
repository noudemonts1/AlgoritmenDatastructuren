package algoritmen.datastructuren.noudfulco.assignment1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AMain {
	
	 public static void main(String[] args) throws FileNotFoundException, IOException {
	        // Your final solution should use System.in, but for testing you may
	        // want to read from a file.
	        // Scanner scanner = new Scanner(new File("/some/file"));
	        Scanner scanner = new Scanner(System.in);

	        Box [] boxes = new Box [scanner.nextInt()];
	        for (int i = 0; i < boxes.length; i++) {
	        	float[] dimensions = new float[3];
	            dimensions[0] = Float.parseFloat(scanner.next());
	            dimensions[1] = Float.parseFloat(scanner.next());
	            dimensions[2] = Float.parseFloat(scanner.next());
	            boxes[i] = new Box(dimensions);
	        }
	        Arrays.sort(boxes);
	        Graph graph = new Graph(boxes);
	        int count = 0;
			while (graph.getNestedBoxes() < boxes.length) {
				count++;
				graph.createGraph();
				graph.dagShortestPaths();
				graph.removeBoxes(graph.getBestBox());
			}
	        
	        // Print your solution.
	        System.out.println(count);
	    }

}
