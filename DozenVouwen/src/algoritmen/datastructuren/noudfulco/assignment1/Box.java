package algoritmen.datastructuren.noudfulco.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;

public class Box implements Comparable<Box> {

	private float[] dimensions;
	private boolean isSource;
	private float distance;
	private Box predecessor;
	private boolean isNested;

	/**
	 * When no parameters are given, we want to create the Source vertice. This
	 * vertice is initialized with no dimensions (or [0, 0, 0]), distance equal to
	 * 0, isSource set to true, and isNested set to false.
	 */
	public Box() {
		dimensions = new float[] { 0, 0, 0 };
		distance = 0;
		isSource = true;
		isNested = true;
	}

	/**
	 * When the dimensions parameter is given, we create a vertice that with given
	 * @param dimensions
	 */
	public Box(float[] dimensions) {
		this.dimensions = dimensions;
		Arrays.sort(dimensions);
		distance = Float.POSITIVE_INFINITY;
		isSource = false;
		isNested = false;
	}

	public float[] getDimensions() {
		return dimensions;
	}

	/**
	 * Checks if this box fits in another box
	 * @param other box
	 * @return
	 */
	public boolean checkFit(Box other) {
		float[] otherDims = other.getDimensions();
		for (int i = 0; i < dimensions.length; i++) {
			if (dimensions[i] >= otherDims[i])
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
		return dimensions[0] + " " + dimensions[1] + " " + dimensions[2];
	}

	@Override
	public int compareTo(Box other) {
		Float boxA = dimensions[0];
		Float boxB = other.getDimensions()[0];
		return boxA.compareTo(boxB);
	}

	public boolean getIsSource() {
		return isSource;
	}

	public Box getPredecessor() {
		return predecessor;
	}

	public void setNested() {
		isNested = true;
	}

	public boolean getNested() {
		return isNested;
	}
}
