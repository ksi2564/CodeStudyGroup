package seongin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
HINT!
https://hoho325.tistory.com/154
최장증가부분집합(LIS)과 이진탐색으로 풀기
LIS 알고리즘을 공부하고 나니 아이디어가 떠올랐다.
Collections 사용해도 되니까 괜히 배열쓰겠다고 하다가 생각 꼬인다.
그냥 떠오르는대로 구현하자.
 */

public class P2568_전깃줄2 {
    static List<Pole> poles = new ArrayList<>();
    static int[] index; // 이진탐색해서 나온 index 값 저장
    static List<Integer> connect = new ArrayList<>(); // 매 순간 살아남는 전봇대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        index = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            poles.add(new Pole(left, right));
        }

        Collections.sort(poles, (p1, p2) -> p1.left - p2.left);

        connect.add(poles.get(0).right);
        index[0] = 1;

        for (int i = 1; i < poles.size(); i++) {
            if (connect.get(connect.size() - 1) < poles.get(i).right) {
                connect.add(poles.get(i).right);
                index[i] = connect.size();
            } else { // 이진탐색 시작
                int idx = binarySearch(poles.get(i).right); // 적절한 위치값
                connect.remove(idx);
                connect.add(idx, poles.get(i).right);
                index[i] = idx + 1;
            }
        }

        sb.append(N - connect.size()).append('\n');

        int connectCnt = connect.size();
        for (int i = N - 1; i >= 0; i--) {
            if (index[i] == connectCnt) {
                index[i] = 0;
                connectCnt--;
            }
        }

        for (int i = 0; i < N; i++) {
            if (index[i] == 0) continue;
            sb.append(poles.get(i).left).append('\n');
        }

        System.out.println(sb);
    }

    static class Pole {
        int left;
        int right;

        public Pole(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int binarySearch(int target) {
        int start = 0;
        int end = connect.size() - 1;
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (connect.get(mid) < target) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}
