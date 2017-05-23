import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Newspaper {

    private static Scanner readConsole() {
        return new Scanner(System.in);
    }

    private static Scanner readFile(String fileName) throws IOException {
        return new Scanner(new FileInputStream(fileName));
    }

    public static void main(String[] args) throws Throwable {
        Scanner scanner = readConsole();
        // Scanner scanner = readFile("Newspaper.inp");
        // PrintStream printFile = new PrintStream(new FileOutputStream("Newspaper.out"));
        // System.setOut(printFile);
        
        int n = scanner.nextInt(); // number of tests.

        while (n-- > 0) {

            int k = scanner.nextInt(); // number of paid characters.

            Map<Character, Integer> charToCents = new HashMap<>();

            scanner.nextLine();
            for (int i = 0; i < k; i++) {
                char c = scanner.next().charAt(0);
                int value = scanner.nextInt();
                charToCents.put(c, value);
            }

            int m = scanner.nextInt();
            int cost = 0;

            scanner.nextLine();
            for (int i = 0; i < m; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    if (charToCents.containsKey(ch)) {
                        cost += charToCents.get(ch);
                    }
                }
            }

            int dollar = cost / 100;        
            int cent = cost % 100;
            System.out.printf("%d.%02d$%n", dollar, cent);
        }

        scanner.close();
    }
}