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
		int listSize = boxes.length + 1 - nestedBoxes;
//		System.out.println("Listsize is: " + listSize);
//		System.out.println("Boxes: " + boxes.length);
//		System.out.println("NestedBoxes: " + nestedBoxes);
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
		int step = 0;
		for (int i = 0; i < boxes.length; i++) {
			//checken of dit beter kan dan met steps
			if (!boxes[i].getNested()) {
				adjListArray[step + 1] = new LinkedList<>();
				adjListArray[step + 1].add(boxes[i]);
				for (int j = i + 1; j < boxes.length; j++) {
					if (boxes[i].checkFit(boxes[j]) && !boxes[i].getNested())
						adjListArray[step + 1].add(boxes[j]);
				}
				step++;
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
			box = pre;
			nestedBoxes++;
		}
	}

	public Box findShortestDist() {
		float distance = Float.POSITIVE_INFINITY;
		Box box = adjListArray[0].get(0);
//		int count = 0;
		for (int i = 1; i < adjListArray.length; i++) {
			if (adjListArray[i].get(0).getDistance() < distance) {
//				count++;
				distance = adjListArray[i].get(0).getDistance();
				box = adjListArray[i].get(0);
//				System.out.println(distance);
			}
		}
//		System.out.println(count);
		return box;
	}

	public void relax(Box boxA, Box boxB) {
		float weight = -1;
//		System.out.println("Hoe lang is een chinees");
		if (boxA.getIsSource() || boxB.getIsSource()) {
//			System.out.println("Relax if statement 1");
			weight = 0;
		}
		if (boxB.getDistance() > boxA.getDistance() + weight) {
//			System.out.println("Relax if statement 2");
			boxB.setDistance(boxA.getDistance() + weight);
			boxB.setPredecessor(boxA);
		}
	}

	public void dagShortestPaths() {
		for (int i = 0; i < adjListArray.length; i++) {
			//System.out.println("List size: " + adjListArray[i].size());
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
