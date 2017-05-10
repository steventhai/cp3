import java.util.Scanner;

/**
 * 11498 - Division of Nlogonia
 */
public class DivisionOfNlogonia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t != 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            
            for (int i = 0; i < t; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                op(n, m, x, y);
            }
            t = scanner.nextInt();
        }
        scanner.close();
    }

    private static void op(int n, int m, int x, int y) {
        if (x == n || y == m) {
            System.out.println("divisa");
            return;
        }

        if (x > n &&  y > m) {
            System.out.println("NE");
        } else if (x < n && y > m) {
            System.out.println("NO");
        } else if (x < n && y < m) {
            System.out.println("SO");
        } else if (x > n && y < m) {
            System.out.println("SE");
        }
    }
}