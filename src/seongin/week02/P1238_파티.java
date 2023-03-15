package seongin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1238_파티 {
    static List<List<Edge>> comeList = new ArrayList<>(); // 정방향(퇴근하기)
    static List<List<Edge>> goList = new ArrayList<>(); // 역방향(출근하기)
    static PriorityQueue<Edge> comePq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
    static PriorityQueue<Edge> goPq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
    static int[] comeCost;
    static int[] goCost;
    static boolean[] comeVisit;
    static boolean[] goVisit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        int X = Integer.parseInt(st.nextToken()); // 목적지

        comeCost = new int[N + 1];
        goCost = new int[N + 1];
        comeVisit = new boolean[N + 1];
        goVisit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            comeList.add(new ArrayList<>());
            goList.add(new ArrayList<>());
            comeCost[i] = Integer.MAX_VALUE;
            goCost[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            comeList.get(e1).add(new Edge(e2, weight));
            goList.get(e2).add(new Edge(e1, weight));
        }

        dijkstra(X);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, comeCost[i] + goCost[i]);
        }

        System.out.println(max);
    }

    static void dijkstra(int arriveNode) {
        comePq.offer(new Edge(arriveNode, 0));
        comeCost[arriveNode] = 0;
        goPq.offer(new Edge(arriveNode, 0));
        goCost[arriveNode] = 0;

        while (!comePq.isEmpty()) {
            Edge edge = comePq.poll();
            if (comeVisit[edge.no]) continue;

            comeVisit[edge.no] = true;
            for (Edge adjEdge : comeList.get(edge.no)) {
                if (comeCost[edge.no] + adjEdge.weight < comeCost[adjEdge.no]) {
                    comeCost[adjEdge.no] = comeCost[edge.no] + adjEdge.weight;

                    adjEdge.weight = comeCost[adjEdge.no];
                    comePq.offer(adjEdge);
                }
            }
        }

        while (!goPq.isEmpty()) {
            Edge edge = goPq.poll();
            if (goVisit[edge.no]) continue;

            goVisit[edge.no] = true;
            for (Edge adjEdge : goList.get(edge.no)) {
                if (goCost[edge.no] + adjEdge.weight < goCost[adjEdge.no]) {
                    goCost[adjEdge.no] = goCost[edge.no] + adjEdge.weight;

                    adjEdge.weight = goCost[adjEdge.no];
                    goPq.offer(adjEdge);
                }
            }
        }

    }

    static class Edge {
        int no;
        int weight;

        public Edge(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

}
