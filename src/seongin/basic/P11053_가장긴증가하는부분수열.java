package seongin.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;

        for (int i = 1; i < N; i++) {
            int val = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    if (dp[j] > val) {
                        val = dp[j];
                    }
                }
            }
            dp[i] = val + 1;
        }

        int ans = 0;

        for (int i : dp) {
            if (ans < i) ans = i;
        }

        System.out.println(ans);
        System.out.println(Arrays.toString(dp));
    }
}
