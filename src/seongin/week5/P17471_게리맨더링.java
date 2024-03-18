package seongin.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17471_게리맨더링 {
    static int N, totalPopulation = 0, answer = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int[] population;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        population = new int[N];
        selected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                adj.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        separate(0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        System.out.println(answer);
    }

    private static void separate(int idx) {
        if (idx == N) {
            ArrayList<Integer> aList = new ArrayList<>();
            ArrayList<Integer> bList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (selected[i]) {
                    aList.add(i);
                } else {
                    bList.add(i);
                }
            }
            if (aList.size() == 0 || bList.size() == 0) return;

            // a, b 지역구 각각 인접해있는지 체크
            if (linkedCheck(aList) && linkedCheck(bList)) {
                // 인구 차이 계산
                int temp = 0;
                for (int a : aList) {
                    temp += population[a];
                }
                answer = Math.min(answer, Math.abs(totalPopulation - 2 * temp));
            }
            return;
        }

        selected[idx] = true;
        separate(idx + 1);
        selected[idx] = false;
        separate(idx + 1);
    }

    private static boolean linkedCheck(ArrayList<Integer> list) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[N];
        visit[list.get(0)] = true;
        queue.offer(list.get(0));

        int count = 1;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int i = 0; i < adj.get(v).size(); i++) {
                int adjNode = adj.get(v).get(i);
                if (list.contains(adjNode) && !visit[adjNode]) {
                    queue.offer(adjNode);
                    visit[adjNode] = true;
                    count++;
                }
            }
        }

        return count == list.size();
    }
}
