import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        String[][] color = new String[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                color[i][j] = in.next();

        int q = in.nextInt();
        int[][] query = new int[q][4];

        for (int i = 0; i < q; i++)
            for (int j = 0; j < 4; j++)
                query[i][j] = in.nextInt();

        int[] result = calculate(color, query);

        for (int i : result)
            System.out.println(i);
    }

    public static int[] calculate(String[][] color, int[][] query) {
        int[] result = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            int x1 = query[i][0] - 1;
            int y1 = query[i][1] - 1;
            int x2 = query[i][2] - 1;
            int y2 = query[i][3] - 1;

            int[] counts = new int[3]; // 0: red, 1: blue, 2: green
            for (int x = x1; x <= x2; x++)
                for (int y = y1; y <= y2; y++)
                    counts[getIndex(color[x][y])]++;

            int count = 0;
            for (int c : counts)
                if (c > 0)
                    count++;

            result[i] = count;
        }

        return result;
    }

    public static int getIndex(String color) {
        if (color.equals("red")) return 0;
        if (color.equals("blue")) return 1;
        return 2; // green
    }
}
