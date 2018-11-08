package algoritmen.datastructuren.noudfulco.assignment1;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private int numVertices;
	private LinkedList<Box> adjListArray[];
	private Box[] boxes;

	public Graph(Box[] boxes) {
		this.boxes = boxes;
		adjListArray = new LinkedList[boxes.length + 1];
		adjListArray[0] = new LinkedList<>();
		// Add source vertex
		Box source = new Box();
		adjListArray[0].add(source);
		for (int i = 0; i < boxes.length; i++) {
			adjListArray[0].add(boxes[i]);
		}
		// Add all other vertices
		for (int i = 0; i < boxes.length; i++) {
			adjListArray[i + 1] = new LinkedList<>();
			adjListArray[i + 1].add(boxes[i]);
			for (int j = i + 1; j < boxes.length; j++) {
				if (boxes[i].checkFit(boxes[j]))
					adjListArray[i + 1].add(boxes[j]);
			}
		}
	}

	public LinkedList<Box>[] getGraph() {
		return adjListArray;
	}

	public void printGraph() {
		for (int i = 0; i < adjListArray.length; i++) {
			System.out.println(adjListArray[i]);
		}
	}
	
	public Box findShortestDist() {
		float distance = Float.POSITIVE_INFINITY;
		Box box = adjListArray[0].get(0);
		int count = 0;
		for (int i = 1; i<adjListArray.length; i++) {
			if(adjListArray[i].get(0).getDistance() < distance) {
				count++;
				distance = adjListArray[i].get(0).getDistance();
				box = adjListArray[i].get(0);
				System.out.println(distance);
			}
		}
		System.out.println(count);
		return box;
	}

	public void relax(Box boxA, Box boxB) {
		float weight = -1;
		if (boxA.getDistance() == 0 || boxB.getDistance() == 0)
			weight = 0;
		if (boxB.getDistance() > boxA.getDistance() + weight) {
			boxB.setDistance(boxA.getDistance() + weight);
			boxB.setPredecessor(boxA);
		}
	}

	public void dagShortestPaths() {
		for (int i = 0; i < adjListArray.length; i++) {
			if (adjListArray[i].size() > 1) {
				for (int j = 1; j < adjListArray[i].size(); i++) {
					relax(adjListArray[i].get(0), adjListArray[i].get(j));
				}
			}
		}
	}
}
