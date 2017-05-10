import java.util.Scanner;

/**
 * 11727 - Cost Cutting
 */
public class CostCutting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int count = 1;
        while (t-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            System.out.printf("Case %d: %d%n", count++, findMid(a, b, c));
        }
        scanner.close();
    }

    private static int findMid(int a, int b, int c) {
        return Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
    }
}