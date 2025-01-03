package com.wang;

public class Tests {

    public static void main(String[] args) {
        int[][] indices = new int[4][3];
        for (int j=0; j<4; j++) {
            for (int k=0; k<3; k++) {
                if (k>=j) indices[j][k] = k+1;
                else indices[j][k] = k;
                System.out.println("indices[" + j + "][" + k + "] = " + indices[j][k]);
            }
            
            
        }
    }
}
