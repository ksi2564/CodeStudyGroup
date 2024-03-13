package seongin.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1202_보석도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] jewelries = new int[N][2];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            jewelries[i][0] = weight;
            jewelries[i][1] = price;
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelries, (o1, o2) -> o1[0] - o2[0]); // 무게 작은 순
        Arrays.sort(bags); // 용량 작은 순

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]); // 보석 가치 높은 순
        int index = 0;
        long answer = 0;

        for (int bag : bags) {
            for (int i = index; i < jewelries.length; i++) {
                if (bag < jewelries[i][0]) {
                    break;
                }
                pq.offer(jewelries[i]);
                index++;
            }

            if (!pq.isEmpty()) {
                int[] poll = pq.poll();
                answer += poll[1];
            }
        }
        System.out.println(answer);
    }
}
