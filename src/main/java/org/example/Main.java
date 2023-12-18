package org.example;

import org.example.utils.CSVUtil;
import org.example.utils.GraphUtil;

public class Main {
    public static void main(String[] args) {
        CSVUtil.init();
//        for(MashupEntry mashupEntry : MashupEntry.mashupList)
//            System.out.println(mashupEntry);
        GraphUtil.generateGraph();
    }
}