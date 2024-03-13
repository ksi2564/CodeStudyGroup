package seongin.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1948_임계경로 {

    static ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
    static ArrayList<ArrayList<Node>> reverseAdjList = new ArrayList<>();
    static boolean[] visit;
    static int[] cost;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        reverseAdjList = new ArrayList<>();
        visit = new boolean[n + 1];
        cost = new int[n + 1];
        Arrays.fill(cost, Integer.MIN_VALUE);

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
            reverseAdjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList.get(startNode).add(new Node(endNode, weight));
            reverseAdjList.get(endNode).add(new Node(startNode, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(findLongPath());
        visit = new boolean[n + 1];
        System.out.println(countLongPath());
    }

    private static int countLongPath() {
        Queue<Node> queue = new ArrayDeque<>();
        int count = 0;
        queue.add(new Node(end, 0));

        while (!queue.isEmpty()) {
            Node v = queue.poll();
            visit[v.endNode] = true;

            for (Node node : reverseAdjList.get(v.endNode)) {
                if (cost[v.endNode] - node.weight == cost[node.endNode]) {
                    count++;
                    if (!visit[node.endNode]) {
                        visit[node.endNode] = true;
                        queue.add(node);
                    }
                }
            }
        }

        return count;
    }

    private static int findLongPath() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        queue.add(new Node(start, 0));
        cost[start] = 0;

        while (!queue.isEmpty()) {
            Node v = queue.poll();

            for (Node node : adjList.get(v.endNode)) {
                if (cost[node.endNode] < cost[v.endNode] + node.weight) {
                    cost[node.endNode] = cost[v.endNode] + node.weight;
                    queue.add(node);
                }
            }
        }

        return cost[end];
    }

    static class Node {
        int endNode, weight;

        public Node(int endNode, int weight) {
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}
