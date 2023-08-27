package com.czc;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();
        int[][] cnt = new int[n][26];
        for (int i = n - 1; i >= 0; i--) {
            cnt[i] = Arrays.copyOf(i < n - 1 ? cnt[i + 1] : new int[26], 26);
            cnt[i][s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (j > 0) System.out.print(" ");
                System.out.print(cnt[i][j] > 0 ? 1 : 0);
            }
            System.out.println();
        }
    }
}

