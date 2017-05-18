import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * 11507 - Bender B. Rodríguez Problem
 */
class Bender {

    private static final String X_PLUS = "+x";
    private static final String X_MINUS = "-x";
    private static final String Y_PLUS = "+y";
    private static final String Y_MINUS = "-y";
    private static final String Z_PLUS = "+z";
    private static final String Z_MINUS = "-z";
    private static final String NO = "No";

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(new FileInputStream("Bender.inp"));

        int l = scanner.nextInt(); // length of the wire.
            
        while(l != 0) {
            
            String direction = X_PLUS;

            for (int i = l - 1; i >= 1; i--) {
                direction = process(direction, scanner.next()); // "((\\+|\\-){1}(y|z){1})|No"
            }

            System.out.println(direction);

            l = scanner.nextInt();
        }

        scanner.close();
    }

    private static String process(String initial, String next) {
        if (next.charAt(0) == 'N') return initial;

        char currentSign = initial.charAt(0);
        char currentDir = initial.charAt(1);
        char newSign = next.charAt(0);
        char newDir = next.charAt(1);

        int sign = currentSign == '-' ? -1 : 1;
        
        if (changeSign(currentDir, currentSign, newSign, newDir)) sign = -sign;

        if (currentDir == 'x') currentDir = newDir;
        else if (currentDir == 'y' && newDir == 'y') currentDir = 'x';
        else if (currentDir == 'z' && newDir == 'z') currentDir = 'x';

        if (sign == 1) return String.valueOf(new char[]{'+', currentDir});
        else return String.valueOf(new char[]{'-', currentDir});
    }

    private static boolean changeSign(char current, char currentSign, char newSign, char newDirection) {
        if (current == 'x' && newSign == '-') return true;
        if (current == 'y' && newSign == '+' && newDirection == 'y') return true;
        if (current == 'z' && newSign == '+' && newDirection == 'z') return true;

        return false;
    }
}
