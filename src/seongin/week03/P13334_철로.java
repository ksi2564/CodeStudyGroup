package seongin.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
오른쪽 점 기준 정렬 후 기준에 만족하는 것을 큐에 넣고 왼쪽 기준 정렬하면
해당 큐의 가장 왼쪽만 철로 안에 들어가면 모두 철로 내에 자리한다는 점이 중요했다.
 */
public class P13334_철로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Line> rightPq = new PriorityQueue<>(((o1, o2) -> o1.right - o2.right));
        PriorityQueue<Line> leftPq = new PriorityQueue<>(((o1, o2) -> o1.left - o2.left));

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l1 = Integer.parseInt(st.nextToken());
            int l2 = Integer.parseInt(st.nextToken());
            rightPq.offer(new Line(Math.min(l1, l2), Math.max(l1, l2)));
        }
        int d = Integer.parseInt(br.readLine());
        int max = 0;
        while (!rightPq.isEmpty()) {
            Line line = rightPq.poll(); // 끝 점이 가장 앞인 line 선택
            leftPq.offer(line);

            while (!leftPq.isEmpty()) {
                Line temp = leftPq.peek();
                if (temp.left >= line.right - d) break; // 해당 왼쪽 점이 선택된 라인 끝점에서 시작되는 철도 길이에 포함된다면 멈춤
                leftPq.poll();
            }

            max = Math.max(max, leftPq.size());
        }
        System.out.println(max);
    }

    static class Line {
        int left;
        int right;

        public Line(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}
