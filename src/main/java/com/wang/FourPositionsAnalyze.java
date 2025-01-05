package com.wang;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FourPositionsAnalyze {
public static void main(String[] args) {

try {
    // The URL endpoint
    String url = "https://www.uk-wl.net/Penta5Classic/0d2d48772cd48f41e761ef99f40e3ac0/allday/2025-01-03?_=" + System.currentTimeMillis();

    HttpClient client = HttpClient.newHttpClient();

    // Build the HTTP GET request
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

    // Send the request and get the response as a String
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    // // Check if the response is successful (HTTP status 200)
    // if (response.statusCode() == 200) {

    // Parse the JSON response using Jackson
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(response.body());

    // Navigate to the "Results" array
    JsonNode resultsNode = rootNode.path("Data").path("Results");

    CodeMatrix codeMatrix = new CodeMatrix();
    ResultsMatrix resultsMatrix = new ResultsMatrix();

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

    

        boolean canGoThroughSuccessfully0 = false;
        if ( codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][1] && codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][2] ) 
            {resultsMatrix.resultsMatrix[i][0] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + "\u001B[0m");
        }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j2==j1) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, codeMatrix.codeMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, codeMatrix.codeMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, codeMatrix.codeMatrix[i][2])) {
                        canGoThroughSuccessfully0 = true;     }
                    else {
                        canGoThroughSuccessfully0 = false; 
                        break outerloop;
                    }
                }
            }
        }
        if (canGoThroughSuccessfully0) {
            Set<Integer> validValues = new HashSet<>(); validValues.add(0);validValues.add(1);validValues.add(2);validValues.add(6);
            if ( validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][1]) % 10 ) || 
                validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][2]) % 10 ) ||
                validValues.contains( (codeMatrix.codeMatrix[i][1] + codeMatrix.codeMatrix[i][2]) % 10 ) ) {
                System.out.print("    " +  "\u001B[32m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + "\u001B[0m");
            }
            else {
                canGoThroughSuccessfully0 = false;
                resultsMatrix.resultsMatrix[i][0] = false;
                System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + "\u001B[0m");
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][0] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + "\u001B[0m");
        }
        }

        boolean canGoThroughSuccessfully3 = false;
        if ( codeMatrix.codeMatrix[i][1]==codeMatrix.codeMatrix[i][2] && codeMatrix.codeMatrix[i][1]==codeMatrix.codeMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][1] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
        }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, codeMatrix.codeMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, codeMatrix.codeMatrix[i][2]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, codeMatrix.codeMatrix[i][3])) {
                        canGoThroughSuccessfully3 = true;  }
                    else {      
                        canGoThroughSuccessfully3 = false; 
                        break outerloop;
                    }
                }
            }
        }
        if (canGoThroughSuccessfully3) {
            Set<Integer> validValues = new HashSet<>(); validValues.add(0);validValues.add(1);validValues.add(2);validValues.add(6);
            if ( validValues.contains( (codeMatrix.codeMatrix[i][1] + codeMatrix.codeMatrix[i][2]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][1] + codeMatrix.codeMatrix[i][3]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][2] + codeMatrix.codeMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3]);
                }
            else {
                canGoThroughSuccessfully3 = false;
                resultsMatrix.resultsMatrix[i][1] = false;
                System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
                
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][1] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
        }
        }


        boolean canGoThroughSuccessfully1 = false;
        if ( codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][1] && codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][2] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
            }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, codeMatrix.codeMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, codeMatrix.codeMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, codeMatrix.codeMatrix[i][3])) { 
                            canGoThroughSuccessfully1 = true;     }
                        else {
                            canGoThroughSuccessfully1 = false; 
                            break outerloop;
                        }
                    
                }
            }
        }
        if (canGoThroughSuccessfully1) {
            Set<Integer> validValues = new HashSet<>(); validValues.add(0);validValues.add(1);validValues.add(2);validValues.add(6);
            if ( validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][1]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][3]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][1] + codeMatrix.codeMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
                }
            else {
                canGoThroughSuccessfully1 = false;
                resultsMatrix.resultsMatrix[i][2] = false;
                System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
                
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][2] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][1] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
        }
        }
        

        boolean canGoThroughSuccessfully2 = false;
        if ( codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][2] && codeMatrix.codeMatrix[i][0]==codeMatrix.codeMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][3] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");}
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, codeMatrix.codeMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, codeMatrix.codeMatrix[i][2]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, codeMatrix.codeMatrix[i][3])) {
                        canGoThroughSuccessfully2 = true;     }
                    else {  
                        canGoThroughSuccessfully2 = false; 
                        break outerloop;
                    }
                            
                }                    
            }
        }   
        if (canGoThroughSuccessfully2) {
            Set<Integer> validValues = new HashSet<>(); validValues.add(0);validValues.add(1);validValues.add(2);validValues.add(6);
            if ( validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][2]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][0] + codeMatrix.codeMatrix[i][3]) % 10 ) || 
                    validValues.contains( (codeMatrix.codeMatrix[i][2] + codeMatrix.codeMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
                }
            else {
                canGoThroughSuccessfully2 = false;
                resultsMatrix.resultsMatrix[i][3] = false;
                System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
            }
            }
            else {
            resultsMatrix.resultsMatrix[i][3] = false;
            System.out.print("    " +  "\u001B[31m" + codeMatrix.codeMatrix[i][0] + " " + codeMatrix.codeMatrix[i][2] + " " + codeMatrix.codeMatrix[i][3] + "\u001B[0m");
        }
        }



        if (!canGoThroughSuccessfully0 && !canGoThroughSuccessfully1 && !canGoThroughSuccessfully2 && !canGoThroughSuccessfully3) {
        resultsMatrix.countStraightFourKills++;
        System.out.println("\u001B[31m" + "   KILL KILL KILL KILL" + "\u001B[0m" );}

        // System.out.println();
        // for (int j=0; j<4; j++) {
        //     System.out.println("resultsMatrix[" + i + "][" + j + "] = " + resultsMatrix.resultsMatrix[i][j]);
        // }
        
        i++;
        System.out.println();

        
    }

resultsMatrix.calculateStraightKills();


// int[][] indices = new int[4][3];
// for (int j=0; j<4; j++) {
//     for (int k=0; k<3; k++) {
//         if (k>=j) indices[j][k] = k+1;
//         else indices[j][k] = k;
//         System.out.println("indices[" + j + "][" + k + "] = " + indices[j][k]);
//     }
    
    
// }

} catch (Exception e) {
    e.printStackTrace();}

}

   

 

}
