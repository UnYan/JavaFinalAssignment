package org.example.utils;


import org.example.entry.APIEntry;
import org.example.entry.MashupEntry;


public class GraphUtil {
    public static int[][] graph;
    public static void generateGraph(){
        int i, j;
        CSVUtil.init();
        graph = new int[APIEntry.size][APIEntry.size];
        for (i = 0; i < graph.length; i++) {
            for (j = 0; j < graph[i].length; j++) {
                graph[i][j] = 0;
            }
        }
        for(MashupEntry mashupEntry : MashupEntry.mashupList){
            int size = mashupEntry.relatedApis.size();
            for(i = 0; i < size - 1; i ++){
                for(j = i + 1; j < size; j ++){
                    APIEntry apiEntry1 = mashupEntry.relatedApis.get(i);
                    APIEntry apiEntry2 = mashupEntry.relatedApis.get(j);
                    graph[apiEntry1.ID][apiEntry2.ID] += 1;
                    graph[apiEntry2.ID][apiEntry1.ID] += 1;
                }
            }
        }
        int count = 0;
        for (i = 0; i < graph.length; i++) {
            for (j = 0; j < graph[i].length; j++) {
                if(graph[i][j] != 0) {
                    count ++;
                    System.out.println(APIEntry.apiList.get(i).name + " - " + APIEntry.apiList.get(j).name + " : " + graph[i][j]);
                }
            }
        }
        System.out.println(count);
    }
}
