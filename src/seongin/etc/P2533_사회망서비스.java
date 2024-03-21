package seongin.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2533_사회망서비스 {
    static int N;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int[][] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2]; // 내가 얼리어답터(1)일 때, 일반인(0)일 때
        visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }

        dfs(1);
        System.out.print(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int num) {
        visit[num] = true;
        dp[num][0] = 0;
        dp[num][1] = 1;

        for (int child : adj.get(num)) {
            if (!visit[child]) {
                dfs(child);
                dp[num][0] += dp[child][1]; // 내가 일반인이면 내 자식은 모두 얼리어답터여야 함.
                dp[num][1] += Math.min(dp[child][0], dp[child][1]); // 내가 얼리어답터면 상관없음(얼리어답터 적은게 장땡)
            }
        }
    }
}
