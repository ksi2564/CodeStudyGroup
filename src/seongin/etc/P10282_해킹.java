package seongin.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P10282_해킹 {
    static int[] cost;
    static boolean[] visit;
    static ArrayList<ArrayList<int[]>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            cost = new int[n + 1];
            visit = new boolean[n + 1];
            Arrays.fill(cost, Integer.MAX_VALUE);

            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adj.get(b).add(new int[]{a, s});
            }

            infect(c);
            int answer = 0;
            for (boolean b : visit) {
                if (b) {
                    answer++;
                }
            }
            sb.append(answer).append(' ');
            int max = 0;
            for (int i : cost) {
                if (i == Integer.MAX_VALUE) continue;
                max = Math.max(max, i);
            }
            sb.append(max).append('\n');
        }
        System.out.print(sb);
    }

    private static void infect(int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        cost[startNode] = 0;
        pq.offer(new int[]{startNode, 0});

        while (!pq.isEmpty()) {
            int[] infectedCom = pq.poll();
            visit[infectedCom[0]] = true;
            for (int[] dependedCom : adj.get(infectedCom[0])) {
                if (cost[infectedCom[0]] + dependedCom[1] < cost[dependedCom[0]]) {
                    cost[dependedCom[0]] = cost[infectedCom[0]] + dependedCom[1];
                    pq.offer(dependedCom);
                }
            }
        }
    }
}
