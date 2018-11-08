package algoritmen.datastructuren.noudfulco.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;

public class Box implements Comparable<Box>{

	private float[] dimensions;
	private float distance;
	private Box predecessor;

	public Box(){
		dimensions = new float[]{0,0,0};
		distance = 0;
	}

	public Box(float[] dimensions) {
		this.dimensions = dimensions;
		Arrays.sort(dimensions);
		distance = Float.POSITIVE_INFINITY;
	}

	public float[] getDimensions() {
		return dimensions;
	}

	public boolean checkFit(Box other) {
		float[] otherDims = other.getDimensions();
		for(int i = 0; i < dimensions.length; i++) {
			if(dimensions[i] >= otherDims[i])
				return false;
		}
		return true;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public void setPredecessor(Box box) {
		this.predecessor = box;
	}

	@Override
	public String toString() {
		return dimensions[0] + " " + dimensions[1] + " " + dimensions[2] + "\n" ;
	}

	@Override
	public int compareTo(Box other) {
		Float boxA = dimensions[0];
		Float boxB = other.getDimensions()[0];
		return boxA.compareTo(boxB);
	}
}
