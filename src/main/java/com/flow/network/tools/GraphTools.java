package com.flow.network.tools;

import java.util.*;

public class GraphTools {

    public int[][] Graph;
    public void initGraph(int G_length){
        Graph = new int[G_length][G_length];
        this.G_length= G_length;
        for (int i = 0; i < G_length; i++) {
            for (int j = 0; j < G_length; j++) {
                Graph[i][j] = 0;
            }
        }
        this.visit= new int[G_length];
    }
    public void setGraph(int start,int end){
        Graph[start][end] = 1;
    }
    public void setGraphValue(int start,int end,int value){
        Graph[start][end] = value;
    }
    // 定义一个图

    // 顶点个数
     int G_length ;
    // visit数组，用于在dfs中记录访问过的顶点信息。
     int[] visit ;
    //存储每条可能的路径
     ArrayList<Integer> path = new ArrayList<>();
    // 用于存储所有路径的集合
     public ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    //起点和终点
     int start;
     int end;


     public  void dfs(int u,int end){
        visit[u] = 1;
        path.add(u);
        if(u == end){
            ans.add(new ArrayList<Integer>(path));
        }else{
            for (int i = 0; i < G_length; i++) {
                if(visit[i]==0&&i!=u&&Graph[u][i]==1){
                    dfs(i,end);
                }
            }
        }
        path.remove(path.size()-1);
        visit[u] = 0;
    }

    public static void main(String[] args) {
        GraphTools graphTools = new GraphTools();
        graphTools.initGraph(4);

        graphTools.setGraph(0,1);
        graphTools.setGraph(0,2);

        graphTools.setGraph(2,1);
        graphTools.setGraph(2,3);
        graphTools.setGraph(3,1);


        graphTools.dfs(0,1);
        System.out.println(graphTools.ans);
    }
}
