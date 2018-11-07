import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Java {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Your final solution should use System.in, but for testing you may
        // want to read from a file.
        // Scanner scanner = new Scanner(new File("/some/file"));
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            // Scanner.nextDouble is pretty slow.
            double x = Double.parseDouble(scanner.next());
            double y = Double.parseDouble(scanner.next());
            double z = Double.parseDouble(scanner.next());
        }

        // Print your solution.
        System.out.println(5);
    }
}
