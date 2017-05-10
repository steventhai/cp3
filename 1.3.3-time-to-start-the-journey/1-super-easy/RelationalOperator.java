import java.util.Scanner;

/**
 * 11172
 */
public class RelationalOperator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            op(a, b);
        }
        scanner.close();
    }

    private static void op(int a, int b) {
        if (a > b) {
            System.out.println(">");
        } else if (a < b) {
            System.out.println("<");
        } else {
            System.out.println("=");
        }
    }
}