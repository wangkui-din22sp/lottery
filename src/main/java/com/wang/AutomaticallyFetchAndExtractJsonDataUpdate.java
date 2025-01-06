package com.wang;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;

public class AutomaticallyFetchAndExtractJsonDataUpdate {

    public static  CompletableFuture<LotteryDataMatrix> automaticallyFetchAndExtractJsonDataUpdate(String url) {
        return CompletableFuture.supplyAsync(() -> {
        try {
            // The URL endpoint
          //  String url = "https://www.uk-wl.net/Penta5Classic/0d2d48772cd48f41e761ef99f40e3ac0/allday/2025-01-05?_=" + System.currentTimeMillis();

            HttpClient client = HttpClient.newHttpClient();

            // Build the HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Send the request and get the response as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response using Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());

            // Navigate to the "Results" array
            JsonNode resultsNode = rootNode.path("Data").path("Results");
            LotteryDataMatrix lotteryDataMatrix = new LotteryDataMatrix();

            int i = 0;
            for (JsonNode result : resultsNode) {
                Long entryNumber = result.path("PeriodNo").asLong();
                String lotteryNumber = result.path("OpenCode").asText();
                String[] numbers = lotteryNumber.split(","); // Split by comma
                
                //System.out.print(entryNumber + " ");
                for (int j = 0; j < 4; j++) {
                    lotteryDataMatrix.lotteryDataMatrix[i][j] = Byte.valueOf(numbers[j]);
                //    System.out.print(numbers[j] + " ");
                }
                //System.out.println();
                i++;
            }
            // Return the populated LotteryDataMatrix
            return lotteryDataMatrix;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;

        }
    });
    }

        // Schedule the task to run every 5 minutes (300 seconds)
       
    }


