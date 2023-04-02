package seongin.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2463_비용 {
    static int[] parents;
    static int[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Edge> list = new ArrayList<>();
        long sum = 0;
        long result = 0;

        int n = Integer.parseInt(st.nextToken()); // 정점의 수
        int m = Integer.parseInt(st.nextToken()); // 간선의 수
        parents = new int[n + 1]; // 자신의 부모 노드 기록
        children = new int[n + 1]; // 자신이 부모인 집합의 원소 수

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 비용

            list.add(new Edge(Math.min(x, y), Math.max(x, y), c));
            sum += c;
        }

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            children[i] = 1;
        }

        list.sort(((o1, o2) -> o2.cost - o1.cost)); // 비용 기준 내림차순 정렬

        for (int i = 0; i < m; i++) {
            Edge edge = list.get(i);
            result += sum * union(edge.x, edge.y);
            result %= 1000000000;
            sum -= edge.cost;
        }

        System.out.println(result);
    }

    static long union(int x, int y) {
        int parentA = find_set(x);
        int parentB = find_set(y);
        if (parentA == parentB) return 0; // 같은 집합이면 계산할 필요 x

        parents[parentA] = parents[parentB];
        long cnt = (long) children[parentA] * children[parentB];
        children[parentB] += children[parentA];
        children[parentA] = 0;

        return cnt;
    }

    private static int find_set(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find_set(parents[n]);
    }


    static class Edge {
        int x, y, cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
