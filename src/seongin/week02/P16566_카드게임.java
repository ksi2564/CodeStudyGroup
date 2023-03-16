package seongin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16566_카드게임 {
    static int[] cardSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] myCards = new int[M];
        cardSet = new int[M];

        for (int i = 0; i < M; i++) {
            cardSet[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            myCards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(myCards);

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = M - 1;
            while (start < end) {
                int mid = (start + end) / 2;
                if (myCards[mid] > target) end = mid;
                else start = mid + 1;
            }
            // 없앨 index가 정해짐
            sb.append(myCards[findSet(end)]).append('\n');
            cardSet[end]++;
        }
        System.out.print(sb);
    }

    static int findSet(int index) {
        if (cardSet[index] == index) return index;
        return findSet(cardSet[index]);
    }
}