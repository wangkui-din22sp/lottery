package com.wang;

public class ThreeCombinations {

    private static byte [] combination1 = new byte[]{1,4,7};
    private static byte [] combination2 = new byte[]{2,5,8};
    private static byte [] combination3 = new byte[]{3,6,9};
    private static byte [][] combination = new byte[][]{combination1, combination2, combination3};

    public static boolean loopThroughCombinationNo(byte No, byte value) {
        
        for (byte i=0; i<3; i++) {

            if (combination[No][i]== value) {
                return false;
                
            }

        }
        return true;
        
    }

}
