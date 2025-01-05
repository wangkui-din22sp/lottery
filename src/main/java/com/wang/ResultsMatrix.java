package com.wang;

import java.util.ArrayList;

public class ResultsMatrix {

    public boolean[][] resultsMatrix = new boolean[288][4];
    public int countStraightFourKills=0;

    byte[] countStraightKills = new byte[]{1,1,1,1};
    // ArrayList<CountStraightKills> countStraightKillsArrayList0 = new ArrayList<CountStraightKills>();
    // ArrayList<CountStraightKills> countStraightKillsArrayList1 = new ArrayList<CountStraightKills>();
    // ArrayList<CountStraightKills> countStraightKillsArrayList2 = new ArrayList<CountStraightKills>();
    // ArrayList<CountStraightKills> countStraightKillsArrayList3 = new ArrayList<CountStraightKills>();
    ArrayList<ArrayList<CountStraightKills>> countStraightKillsArrayLists;
    int[][] total;

    boolean isTwoStraightKills = false;

    public ResultsMatrix() {
        for (int i = 0; i < 288; i++) {
            for (int j = 0; j < 4; j++) {
                resultsMatrix[i][j] = true;
            }
        }
        countStraightKillsArrayLists = new ArrayList<ArrayList<CountStraightKills>>();
        for (int i = 0; i < 4; i++) {
            ArrayList<CountStraightKills> countStraightKillsArrayList = new ArrayList<CountStraightKills>();
            countStraightKillsArrayLists.add(countStraightKillsArrayList);

        }
        total = new int[4][70];
       

    }

    public void calculateStraightKills() {
        for (int j=0; j<4; j++) {
            for (int i = 1; i < 288 ; i++) {
                if (!resultsMatrix[i][j]  && !resultsMatrix[i-1][j]) { 
                    countStraightKills[j]++; isTwoStraightKills = true;
                }
                else { 
                    isTwoStraightKills = false;
                    if (countStraightKills[j]>1) { 
                        countStraightKillsArrayLists.get(j).add(new CountStraightKills(countStraightKills[j], 288-(i-countStraightKills[j]), 288-(i-1) ));
                    }
                    countStraightKills[j] =1;
                }
            }
            if (!resultsMatrix[286][j]  && !resultsMatrix[287][j]) {
                countStraightKillsArrayLists.get(j).add(new CountStraightKills(countStraightKills[j], countStraightKills[j], 1 ));
            }
        }
        // for (int i = 1; i < 288 ; i++) {


        //         if (!resultsMatrix[i][0]  && !resultsMatrix[i-1][0]) { 
        //             countStraightKills[0]++; isTwoStraightKills = true;
                    
        //         }
        //         else { 
        //             isTwoStraightKills = false;
        //             if (countStraightKills[0]>1) { 
        //                 countStraightKillsArrayList0.add(new CountStraightKills(countStraightKills[0], 288-(i-countStraightKills[0]), 288-(i-1) ));}
        //             countStraightKills[0] =1;}     
        // }
        // if (!resultsMatrix[286][0]  && !resultsMatrix[287][0])
        // countStraightKillsArrayList0.add(new CountStraightKills(countStraightKills[0], countStraightKills[0], 1 ));
        total();

        printStraightKills(); 

        }
            
    public void printStraightKills() {
        System.out.println();
        System.out.println("一共有 " +"\u001B[31m"+  countStraightFourKills +"\u001B[0m"+ " 期是四个组合全部杀！");
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.println("组合方式 " + (i+1) + " 连续杀列表。第一个红色数字是连续杀的个数，第二个数字是连续杀的开始期数，第三个数字是连续杀的结束期数");
            for (CountStraightKills countStraightKills : countStraightKillsArrayLists.get(i)) {
                System.out.print( "\u001B[31m" +countStraightKills.getCountStraightKills() + "\u001B[0m"+ " " + countStraightKills.getStartingEntryNumber() + " " + countStraightKills.getEndingEntryNumber()+"   ");
            }
            System.out.println();
            System.out.println("组合方式 "  + (i+1) + " 连续杀总计：");
            for (int j = 0; j < 70;  j++) {
                if (total[i][j] != 0) {
                System.out.println("连续杀 " + "\u001B[31m"+ j + "\u001B[0m"+ " 期，一共有" + total[i][j] +" 次！");}
                }
            System.out.println();
                
        }

    }

    public void total() {
        for (int i = 0; i < 4; i++) {
            for (CountStraightKills countStraightKills : countStraightKillsArrayLists.get(i)) {
                total[i][countStraightKills.getCountStraightKills()]++;
            }
        }   

       
    }
        
    
}



