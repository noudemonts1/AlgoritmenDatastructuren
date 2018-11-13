package algoritmen.datastructuren.noudfulco.assignment1;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private LinkedList<Box> adjListArray[];
	private Box[] boxes;
	private int nestedBoxes;

	public Graph(Box[] boxes) {
		this.boxes = boxes;
		nestedBoxes = 0;
	}

	public void createGraph() {
		int listSize = boxes.length - nestedBoxes + 1;
		adjListArray = new LinkedList[listSize];
		adjListArray[0] = new LinkedList<>();
		
		// Add source vertex
		Box source = new Box();
		adjListArray[0].add(source);
		for (int i = 0; i < boxes.length; i++) {
			if (!boxes[i].getNested()) {
				adjListArray[0].add(boxes[i]);
			}
		}
		// Add all other vertices
		int index = 1;
		for (int i = 0; i < boxes.length; i++) {
			//checken of dit beter kan dan met steps
			if (!boxes[i].getNested()) {
				//System.out.println("Step: " + index);
				//System.out.println("Box that is not nested: [" + boxes[i] + "]");
				boxes[i].setPredecessor(source);
				boxes[i].setDistance(Float.POSITIVE_INFINITY);
				adjListArray[index] = new LinkedList<>();
				adjListArray[index].add(boxes[i]);
				for (int j = i + 1; j < boxes.length; j++) {
					if (boxes[i].checkFit(boxes[j]) && !boxes[j].getNested()) {
						//System.out.println("[" + boxes[j] + "] is not yet nested and added");
						adjListArray[index].add(boxes[j]);
					}
				}
				//printGraph();
				index++;
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

	public void removeBoxes(Box box) {
		while (!box.getIsSource()) {
			Box pre = box.getPredecessor();
			box.setNested();
			//System.out.println("Nested box: " + box);
			box = pre;
			nestedBoxes++;
		}
	}

	public Box findShortestDist() {
		float distance = Float.POSITIVE_INFINITY;
		Box box = adjListArray[0].get(0);
		for (int i = 1; i < adjListArray.length; i++) {
			if (adjListArray[i].get(0).getDistance() < distance) {
				distance = adjListArray[i].get(0).getDistance();
				box = adjListArray[i].get(0);
			}
		}
		return box;
	}

	public void relax(Box boxA, Box boxB) {
		float weight = -1;
		if (boxA.getIsSource() || boxB.getIsSource()) {
			weight = 0;
		}
		if (boxB.getDistance() > boxA.getDistance() + weight) {
			boxB.setDistance(boxA.getDistance() + weight);
			boxB.setPredecessor(boxA);
		}
	}

	public void dagShortestPaths() {
		for (int i = 0; i < adjListArray.length; i++) {
			if (adjListArray[i].size() > 1) {
				for (int j = 1; j < adjListArray[i].size(); j++) {
					relax(adjListArray[i].get(0), adjListArray[i].get(j));
				}
			}
		}
	}
	
	public int getNestedBoxes() {
		return nestedBoxes;
	}

//	public void printSizes() {
//		for (int i = 0; i < adjListArray.length; i++) {
//			int count = 0;
//			for (int j = 0; j < adjListArray[i].size(); j++) {
//				count++;
//			}
//			System.out.println(count);
//		}
//	}
}
