import java.io.*;
import java.util.InputMismatchException;

/**
 * Brute force solution.
 * 
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1796
 */
class RotatedSquare {

    private static final int MAX = 100002;

    public static void main(String[] args) throws Throwable {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        // InputReader in = new InputReader(new FileInputStream("RotatedSquare.inp"));
        // OutputWriter out = new OutputWriter(new FileOutputStream("RotatedSquare.out"));

        int largeSize = in.readInt();
        int smallSize = in.readInt();

        while (largeSize != 0 && smallSize != 0) {
            char[][] large = new char[largeSize][largeSize];
            char[][] small = new char[smallSize][smallSize];

            for (int i = 0; i < largeSize; i++) {
                large[i] = in.readString().toCharArray();
            }

            for (int i = 0; i < smallSize; i++) {
                small[i] = in.readString().toCharArray();
            }

            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int result = findSmallInLarge(small, large);
                rotate(small);

                if (i != 0) {
                    strBuilder.append(' ').append(result);
                } else {
                    strBuilder.append(result);
                }
            }

            // Output.
            out.printLine(strBuilder.toString());

            largeSize = in.readInt();
            smallSize = in.readInt();
        }

        out.flush();
        out.close();
    }

    private static void rotate(char[][] small) {
        // Rotate in-place 90 degrees clockwise.
        int length = small.length - 1;
        for (int i = 0; i <= length / 2; i++) {
            for (int j = i; j < length - i; j++) {
                char temp = small[i][j];
                small[i][j] = small[length - j][i];
                small[length - j][i] = small[length - i][length - j];
                small[length - i][length - j] = small[j][length - i];
                small[j][length - i] = temp;
            }
        }
    }

    private static int  findSmallInLarge(char[][] small, char[][] large) {
        int count = 0;
        boolean found = true;
        int largeSize = large.length;
        int smallSize = small.length;
        int length = largeSize - smallSize + 1;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                
                if (large[i][j] == small[0][0]) {

                    found = true;

                    for (int k = 0; k < smallSize; k++) {
                        for (int l = 0; l < smallSize;l++) {
                            if (small[k][l] != large[i + k][j + l]) {
                                found = false;
                                break;
                            }
                        }
                    }

                    if (found) {
                        count++;
                    }
                }
            }
        }

        return count;
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