package dongyoon.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P9019_DSLR {

	static int T;
	static int A, B;
	static boolean[] visit;

	static int func(int n, char c) {

		switch (c) {
		case 'D': // D 더블
			return (n * 2) % 10000;
		case 'S':
			return n == 0 ? 9999 : n - 1;
		case 'L':
			return (n * 10 + n / 1000) % 10000;
		case 'R':
			return (n % 10) * 1000 + n / 10;
		}
		return 0;

	}

	static String bfs() {
		Queue<Num> queue = new ArrayDeque<>();
		visit = new boolean[10000];
		visit[A] = true;
		queue.offer(new Num(A, ""));
		while (!queue.isEmpty()) {
			Num num = queue.poll();
			if (num.n == B) {
				return num.com;
			}
			if (!visit[func(num.n, 'D')]) {
				visit[func(num.n, 'D')] = true;
				queue.offer(new Num(func(num.n, 'D'), num.com + 'D'));
			}
			if (!visit[func(num.n, 'S')]) {
				visit[func(num.n, 'S')] = true;
				queue.offer(new Num(func(num.n, 'S'), num.com + 'S'));
			}
			if (!visit[func(num.n, 'L')]) {
				visit[func(num.n, 'L')] = true;
				queue.offer(new Num(func(num.n, 'L'), num.com + 'L'));
			}
			if (!visit[func(num.n, 'R')]) {
				visit[func(num.n, 'R')] = true;
				queue.offer(new Num(func(num.n, 'R'), num.com + 'R'));
			}
		}
		return "";
	}

	static class Num {
		int n;
		String com;

		Num(int n, String com) {
			this.n = n;
			this.com = com;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			System.out.println(bfs());
		}

	}

}
