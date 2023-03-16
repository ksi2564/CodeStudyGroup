package dongyoon.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1949_우수마을 {

	static int N, sum;
	static int[] pop;
	static boolean[] visit;
	static int[][] good; // Nx2 0: 선택X 1: 선택O

	static List<List<Integer>> adjList = new ArrayList<List<Integer>>();

	static void dfs(int idx) {

		visit[idx] = true;
		for (int n : adjList.get(idx)) {
			if (visit[n])
				continue;
			dfs(n);

			good[idx][0] += Math.max(good[n][0], good[n][1]);
			good[idx][1] += good[n][0];

		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		pop = new int[N + 1];
		visit = new boolean[N + 1];
		good = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
			good[i][1] = pop[i];
			adjList.add(new ArrayList<Integer>());
		}
		adjList.add(new ArrayList<Integer>());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}

		dfs(1);

		System.out.println(Math.max(good[1][0], good[1][1]));

	}

}