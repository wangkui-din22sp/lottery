package com.wang;

import org.apache.hc.client5.http.fluent.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            byte countStraightEvenNumber = 1, countStraightOddNumber = 1, countAlternatingNumber = 2;
            ArrayList<CountStraightEvenNumber> countStraightEvenNumberList = new ArrayList<CountStraightEvenNumber>();
            ArrayList<CountStraightOddNumber> countStraightOddNumberList = new ArrayList<CountStraightOddNumber>();
            ArrayList<CountAlternatingPattern> countAlternatingPatternList = new ArrayList<CountAlternatingPattern>();
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
                            if (countStraightEvenNumber>1) { 
                            countStraightEvenNumberList.add(new CountStraightEvenNumber(countStraightEvenNumber, entryNumber+countStraightEvenNumber, entryNumber+1));}
                            countStraightEvenNumber =1;}
    
                    if (codeMatrix.codeMatrix[i][j] % 2 ==1  && codeMatrix.codeMatrix[i-1][j] % 2 ==1) { 
                        countStraightOddNumber++;   isTwoConsecutiveOdd = true;
                        if (countStraightOddNumber==2) {System.out.println(entryNumber+1 + " " + "\u001B[34m" + codeMatrix.codeMatrix[i-1][j] + "\u001B[0m" );}
                        System.out.println(entryNumber + " " + "\u001B[34m" + codeMatrix.codeMatrix[i][j] + "\u001B[0m" );   
                    }
                    else {
                            if (countStraightOddNumber>1) { 
                            countStraightOddNumberList.add(new CountStraightOddNumber(countStraightOddNumber, entryNumber+countStraightOddNumber, entryNumber+1));}
                            countStraightOddNumber =1;}

                    if ( (i>=2) && (isTwoConsecutiveEven==false && isTwoConsecutiveOdd==false) && ( 
                        (codeMatrix.codeMatrix[i][j] % 2 ==1  && codeMatrix.codeMatrix[i-2][j] % 2 ==1) || (codeMatrix.codeMatrix[i][j] % 2 ==0  && codeMatrix.codeMatrix[i-2][j] % 2 ==0)) 
                    ){
                        // System.out.println(entryNumber+2 + " " + "\u001B[31m" + codeMatrix.codeMatrix[i-2][j] + "\u001B[0m");
                        // System.out.println(entryNumber+1 + " " + "\u001B[31m" + codeMatrix.codeMatrix[i-1][j] + "\u001B[0m");
                        countAlternatingNumber++;
                        System.out.println(entryNumber + " " + "\u001B[31m" + codeMatrix.codeMatrix[i][j]+ "\u001B[0m" );
                    
                    }
                    else { 
                            if (countAlternatingNumber>2) {
                            countAlternatingPatternList.add(new CountAlternatingPattern(countAlternatingNumber, entryNumber+countAlternatingNumber, entryNumber+1));
                            countAlternatingNumber =2;

                            } }


                    
                    }
    
                else {}

                   



                i++; isTwoConsecutiveEven=false; isTwoConsecutiveOdd=false;

            }
            
            System.out.println(" ");
            for (CountStraightEvenNumber countStraightEvenNumberListElement : countStraightEvenNumberList) {
                System.out.println("\u001B[32m"+"countStraightEvenNumber: " + countStraightEvenNumberListElement.getCountStraightEvenNumber());
                System.out.println("StartingEntryNumber: " + countStraightEvenNumberListElement.getStartingEntryNumber());
                System.out.println("EndingEntryNumber: " + countStraightEvenNumberListElement.getEndingEntryNumber());
            }
            System.out.println("total size of countStraightEvenNumberList: " + countStraightEvenNumberList.size());
            System.out.println(" ");

            for (CountStraightOddNumber countStraightOddNumberListElement : countStraightOddNumberList) {
                System.out.println("\u001B[34m"+"countStraightOddNumber: " + countStraightOddNumberListElement.getCountStraightOddNumber());
                System.out.println("StartingEntryNumber: " + countStraightOddNumberListElement.getStartingEntryNumber());
                System.out.println("EndingEntryNumber: " + countStraightOddNumberListElement.getEndingEntryNumber());
            }
            System.out.println("total size of countStraightOddNumberList: " + countStraightOddNumberList.size());
            System.out.println(" ");

            for (CountAlternatingPattern countAlternatingPatternListElement : countAlternatingPatternList) {
                System.out.println("\u001B[31m"+"countAlternatingPattern: " + countAlternatingPatternListElement.getCountAlternatingPattern());
                System.out.println("StartingEntryNumber: " + countAlternatingPatternListElement.getStartingEntryNumber());
                System.out.println("EndingEntryNumber: " + countAlternatingPatternListElement.getEndingEntryNumber());
            }
            System.out.println("total size of countAlternatingPatternList: " + countAlternatingPatternList.size());
            System.out.println(" ");




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
