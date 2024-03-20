package seongin.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class P1431_시리얼번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Meta> metas = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String serialNum = br.readLine();
            char[] temp = serialNum.toCharArray();
            int sumNum = 0;
            for (char c : temp) {
                if ('0' <= c && c <= '9') {
                    sumNum += (c - '0');
                }
            }
            metas.add(new Meta(serialNum.length(), sumNum, serialNum));
        }

        Collections.sort(metas);
        for (Meta meta : metas) {
            sb.append(meta.serialNum).append('\n');
        }

        System.out.print(sb);
    }

    static class Meta implements Comparable<Meta> {
        int length, sumNum;
        String serialNum;

        public Meta(int length, int sumNum, String serialNum) {
            this.length = length;
            this.sumNum = sumNum;
            this.serialNum = serialNum;
        }

        @Override
        public int compareTo(Meta o) {
            if (this.length - o.length == 0) {
                if (this.sumNum - o.sumNum == 0) {
                    return this.serialNum.compareTo(o.serialNum);
                } else {
                    return this.sumNum - o.sumNum;
                }
            } else {
                return this.length - o.length;
            }
        }
    }

}
