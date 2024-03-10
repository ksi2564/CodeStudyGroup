package seongin.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P9328_열쇠 {
    static int H, W;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Map<Character, ArrayList<int[]>> doors;
    static Set<Character> keys;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            visit = new boolean[H][W];
            answer = 0;
            for (int h = 0; h < H; h++) {
                map[h] = br.readLine().toCharArray();
            }

            doors = new HashMap<>(); // 풀지 못한 문 기록
            keys = new HashSet<>(); // 가지고 있는 열쇠 기록

            st = new StringTokenizer(br.readLine());
            char[] temp = st.nextToken().toCharArray();
            for (char c : temp) {
                keys.add(c);
            }

            for (int h = 0; h < H; h++) {
                // 현재는 길일때만 들어갈 수 있도록 했는데
                // 문이여도 열 수 있는 문이거나
                // 열쇠가 놓여있다면 가능하도록 수정할 것
                if ((map[h][0] != '*' && !visit[h][0])) {
                    checker(h, 0);
                }
                if (map[h][W - 1] != '*' && !visit[h][W - 1]) {
                    checker(h, W - 1);
                }
            }
            for (int w = 0; w < W; w++) {
                if (map[0][w] != '*' && !visit[0][w]) {
                    checker(0, w);
                }
                if (map[H - 1][w] != '*' && !visit[H - 1][w]) {
                    checker(H - 1, w);
                }
            }
            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == '*' || visit[nx][ny]) continue;
            checker(nx, ny);
        }
    }

    private static void checker(int nx, int ny) {
        if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') { // 문이면
            char key = (char) (map[nx][ny] + 32);
            if (keys.contains(key)) { // 만난 문의 열쇠가 있다면
                // dfs 진행
                visit[nx][ny] = true;
                dfs(nx, ny);
            } else {
                if (!doors.containsKey(key)) { // 처음 만난 문이라면
                    doors.put(key, new ArrayList<>());
                }
                doors.get(key).add(new int[]{nx, ny}); // 좌표 저장
            }
        } else if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') { // 열쇠면
            keys.add(map[nx][ny]);
            if (doors.getOrDefault(map[nx][ny], new ArrayList<>()).size() != 0) {
                for (int[] door : doors.get(map[nx][ny])) {
                    visit[door[0]][door[1]] = true;
                    dfs(door[0], door[1]);
                }
                doors.remove(map[nx][ny]);
            }
            visit[nx][ny] = true;
            dfs(nx, ny);
        } else if (map[nx][ny] == '$') {
            answer++;
            visit[nx][ny] = true;
            dfs(nx, ny);
        } else {
            visit[nx][ny] = true;
            dfs(nx, ny);
        }
    }
}
