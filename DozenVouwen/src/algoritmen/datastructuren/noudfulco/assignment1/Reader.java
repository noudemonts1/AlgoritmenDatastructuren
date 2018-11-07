package algoritmen.datastructuren.noudfulco.assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	static Box[] readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\Workspaces\\Java\\Algoritmen en Datastructuren\\DozenVouwen\\src\\algoritmen\\datastructuren\\noudfulco\\assignment1\\Input"))) {
			String line;
			line = br.readLine();
			Box[] boxes = new Box[Integer.parseInt(line)]; // maybe as heap instead of array
			for (int j = 0; j < boxes.length; j++) {
				line = br.readLine();
				String[] values = line.split(" ");
				float[] dimensions = new float[3];
				for (int i = 0; i < 3; i++) {
					float f = Float.parseFloat(values[i]);
					dimensions[i] = f;
				}
				boxes[j] = new Box(dimensions);
			}
			return boxes;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}