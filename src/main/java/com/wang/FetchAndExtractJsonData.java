package com.wang;

import org.apache.hc.client5.http.fluent.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

public class FetchAndExtractJsonData {
    public static void main(String[] args) {
        try {
            // The URL endpoint
            String url = "https://www.uk-wl.net/Penta5Classic/0d2d48772cd48f41e761ef99f40e3ac0/allday/2025-01-02?_=" + System.currentTimeMillis();

            // Send the GET request and get the response as a string
            String response = Request.get(url)
                    .execute()
                    .returnContent()
                    .asString();

            // Parse the JSON response using Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            // Navigate to the "Results" array
            JsonNode resultsNode = rootNode.path("Data").path("Results");
            CodeMatrix codeMatrix = new CodeMatrix();

            // Extract and print PeriodNo and OpenCode
            byte countStraightEvenNumber = 1, countStraightOddNumber = 1;
            ArrayList<Byte> countStraightEvenNumbers = new ArrayList<>();
            ArrayList<Byte> countStraightOddNumbers = new ArrayList<>();
            boolean isTwoConsecutiveEven = false, isTwoConsecutiveOdd = false;

            int i = 0;
            for (JsonNode result : resultsNode) {
                Long entryNumber = result.path("PeriodNo").asLong();
                String lotteryNumber = result.path("OpenCode").asText();
                String[] numbers = lotteryNumber.split(",") ; // Split by comma
                
                System.out.print(entryNumber + " ");
                for (int j = 0; j < 4; j++) {
                    codeMatrix.codeMatrix[i][j] = Byte.valueOf(numbers[j]);
                    System.out.print(numbers[j] + " ");
                    
                }
                System.out.println();
                
                byte j=0;
                if (i>=1) {
                    if (codeMatrix.codeMatrix[i][j] % 2 ==0  && codeMatrix.codeMatrix[i-1][j] % 2 ==0) { 
                        countStraightEvenNumber++; isTwoConsecutiveEven = true;
                        if (countStraightEvenNumber==2) {System.out.println(entryNumber+1 + " " + "\u001B[32m" + codeMatrix.codeMatrix[i-1][j]  + "\u001B[0m");}
                        System.out.println(entryNumber + " " + "\u001B[32m" + codeMatrix.codeMatrix[i][j]  + "\u001B[0m");   
                    }
                    else { 
                            if (countStraightEvenNumber>1) countStraightEvenNumbers.add(countStraightEvenNumber); 
                            countStraightEvenNumber =1;}
    
                    if (codeMatrix.codeMatrix[i][j] % 2 ==1  && codeMatrix.codeMatrix[i-1][j] % 2 ==1) { 
                        countStraightOddNumber++;   isTwoConsecutiveOdd = true;
                        if (countStraightOddNumber==2) {System.out.println(entryNumber+1 + " " + "\u001B[34m" + codeMatrix.codeMatrix[i-1][j] + "\u001B[0m" );}
                        System.out.println(entryNumber + " " + "\u001B[34m" + codeMatrix.codeMatrix[i][j] + "\u001B[0m" );   
                    }
                    else {
                            if (countStraightOddNumber>1) countStraightOddNumbers.add(countStraightOddNumber);
                            countStraightOddNumber =1;}

                    if ( (i>=2) && (isTwoConsecutiveEven==false && isTwoConsecutiveOdd==false) && ( 
                        (codeMatrix.codeMatrix[i][j] % 2 ==1  && codeMatrix.codeMatrix[i-2][j] % 2 ==1) || (codeMatrix.codeMatrix[i][j] % 2 ==0  && codeMatrix.codeMatrix[i-2][j] % 2 ==0)) 
                 ){
                        // System.out.println(entryNumber+2 + " " + "\u001B[31m" + codeMatrix.codeMatrix[i-2][j] + "\u001B[0m");
                        // System.out.println(entryNumber+1 + " " + "\u001B[31m" + codeMatrix.codeMatrix[i-1][j] + "\u001B[0m");
                        System.out.println(entryNumber + " " + "\u001B[31m" + codeMatrix.codeMatrix[i][j]+ "\u001B[0m" );}

                    
                    }
    
                else {}

                   



                i++; isTwoConsecutiveEven=false; isTwoConsecutiveOdd=false;

            }
            for (Byte countStraightEvenNumberElement : countStraightEvenNumbers) {
                System.out.println("countStraightEvenNumber: " + countStraightEvenNumberElement);
            }
            for (Byte countStraightOddNumberElement : countStraightOddNumbers) {
                System.out.println("countStraightOddNumber: " + countStraightOddNumberElement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
