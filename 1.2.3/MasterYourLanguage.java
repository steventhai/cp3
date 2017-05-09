import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MasterYourLanguage {
    public static void main(String[] arg) throws Throwable {
        printFormattedDouble();
        printPi(4);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2010, 7, 9);
        dateOfWeek(calendar);
        printSorted(10);
    }

    private static void printFormattedDouble() throws IOException {
        double[] numbers = Files.lines(Paths.get("MasterYourLanguage.txt")).mapToDouble(Double::parseDouble).toArray();
        System.out.format("%7.3f%n", numbers[0]);
        System.out.format("%7.3f%n", numbers[1]);
    }

    /**
     * Print PI to n digit after decimal point (rounded).
     * n <= 15.
     */
    private static void printPi(int n) {
        double PI = Math.PI;
        System.out.format("%." + n + "f%n", PI);
    }

    /**
     * Determine day of the week (Monday, etc) from a specified Date.
     */
    private static void dateOfWeek(Calendar someDate) {
        System.out.println(new SimpleDateFormat("EEEE").format(someDate.getTime()));
    }

    /**
     * Print n unique integer in sorted order.
     */
    private static void printSorted(int n) {
        Set<Integer> set = new TreeSet<>();
        Random random = new Random();
        while (set.size() < n) {
            while(!set.add(random.nextInt(n)));
        }
        System.out.println(set);
    }
}