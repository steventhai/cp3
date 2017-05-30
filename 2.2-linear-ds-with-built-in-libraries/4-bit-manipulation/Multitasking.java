
import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 11926 - Multitasking
 * 
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=3077
 */
class Multitasking {

    public static void main(String[] args) throws Throwable {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        // InputReader in = new InputReader(new FileInputStream("Multitasking.inp"));
        // OutputWriter out = new OutputWriter(new FileOutputStream("Multitasking.out"));

        int n = in.readInt(); // number of one time tasks.
        int m = in.readInt(); // number of repeating tasks.

        while (n != 0 || m != 0) {
            BitSet bitSet = new BitSet(1000001);
            boolean conflict = false;
            for (int i = 0; i < n; i++) {
                int start = in.readInt();
                int end = in.readInt();

                BitSet range = bitSet.get(start, end);

                if (range.isEmpty()) {
                    bitSet.set(start, end);
                } else {
                    conflict = true;
                }
                // if (range.get(start) && range.get(end) && range.cardinality() > 2) {
                //     conflict = true;
                // } else if (!(range.get(start) && range.get(end)) && range.cardinality() > 1) {
                //     conflict = true;
                // } else if (!(range.get(start) || range.get(end)) && range.cardinality() > 0) {
                //     conflict = true;
                // } else {
                //     bitSet.set(start, end + 1);
                // }
            }

            for (int i = 0; i < m; i++) {
                int start = in.readInt();
                int end = in.readInt();
                int interval = in.readInt();

                while (start <= 1000000) {

                    if (end > 1000000) {
                        end = 1000000;
                    }

                    BitSet range = bitSet.get(start, end);

                    // if (range.get(start) && range.get(end) && range.cardinality() > 2) {
                    //     conflict = true;
                    // } else if (!(range.get(start) && range.get(end)) && range.cardinality() > 1) {
                    //     conflict = true;
                    // } else if (!(range.get(start) || range.get(end)) && range.cardinality() > 0) {
                    //     conflict = true;
                    // } else {
                    //     bitSet.set(start, end + 1);
                    // }

                    if (range.isEmpty()) {
                        bitSet.set(start, end);
                    } else {
                        conflict = true;
                    }

                    start += interval;
                    end += interval;
                }
            }

            // Output.
            if (conflict) {
                out.printLine("CONFLICT");
            } else {
                out.printLine("NO CONFLICT");
            }

            n = in.readInt();
            m = in.readInt();
        }

        out.flush();
        out.close();
    }

    //FAST IO
    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }

    }

    private static class IOUtils {
        public static int[] readIntArray(InputReader in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = in.readInt();
            return array;
        }
    }
}