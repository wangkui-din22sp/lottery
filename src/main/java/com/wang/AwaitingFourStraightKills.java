package com.wang;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import javax.sound.sampled.*;
import java.io.IOException;


public class AwaitingFourStraightKills {
public static void main(String[] args) {
    
    String url = "https://www.uk-wl.net/Penta5Classic/0d2d48772cd48f41e761ef99f40e3ac0/allday/2025-01-06?_=" + System.currentTimeMillis();
    // future.complete(lotteryDataMatrix);
    // Fetch the data asynchronously
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Define the task that sends the HTTP request and processes the response
    Runnable awaitingFourStraightKills = new Runnable() {
        @Override
        public void run() {
            try {

                CompletableFuture<LotteryDataMatrix> future = AutomaticallyFetchAndExtractJsonDataUpdate.automaticallyFetchAndExtractJsonDataUpdate(url);

                future.thenAccept(lotteryDataMatrix -> {
                    if (lotteryDataMatrix != null) {
                        processLotteryData(lotteryDataMatrix);
                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    scheduler.scheduleAtFixedRate(awaitingFourStraightKills, 0, 50, TimeUnit.SECONDS);


}

// private static void playMusicMP3(String filePath) {
//     try {
//         BitstreamDecoder decoder = new BitstreamDecoder();
//         InputStream mp3Stream = new FileInputStream(new File(filePath));
//         BitstreamDecoderInputStream decoderInputStream = new BitstreamDecoderInputStream(mp3Stream);

//         decoder.decode(decoderInputStream);
//     } catch (IOException | BitstreamDecoderException e) {
//         e.printStackTrace();
//     }
// }

private static void playSound(String filePath) {
    try {
        // Get the file and create a sound input stream
        File audioFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        // Get a clip to play the audio
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        // Start playing the music
        clip.start();
        
        // Optional: Loop the music (if desired)
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        // Keep the program running for the duration of the music
        Thread.sleep(clip.getMicrosecondLength() / 1000);
        
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
        e.printStackTrace();
    }
}



public static void processLotteryData(LotteryDataMatrix lotteryDataMatrix) {

    ResultsMatrix resultsMatrix = new ResultsMatrix();
    int i=0;
    boolean canGoThroughSuccessfully0 = false;
        if ( lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][1] && lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][2] ) 
            {resultsMatrix.resultsMatrix[i][0] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + "\u001B[0m");
        }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j2==j1) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, lotteryDataMatrix.lotteryDataMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, lotteryDataMatrix.lotteryDataMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, lotteryDataMatrix.lotteryDataMatrix[i][2])) {
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
            if ( validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][1]) % 10 ) || 
                validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][2]) % 10 ) ||
                validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][1] + lotteryDataMatrix.lotteryDataMatrix[i][2]) % 10 ) ) {
                System.out.print("    " +  "\u001B[32m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + "\u001B[0m");
            }
            else {
                canGoThroughSuccessfully0 = false;
                resultsMatrix.resultsMatrix[i][0] = false;
                System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + "\u001B[0m");
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][0] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + "\u001B[0m");
        }
        }

        boolean canGoThroughSuccessfully3 = false;
        if ( lotteryDataMatrix.lotteryDataMatrix[i][1]==lotteryDataMatrix.lotteryDataMatrix[i][2] && lotteryDataMatrix.lotteryDataMatrix[i][1]==lotteryDataMatrix.lotteryDataMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][1] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
        }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, lotteryDataMatrix.lotteryDataMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, lotteryDataMatrix.lotteryDataMatrix[i][2]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, lotteryDataMatrix.lotteryDataMatrix[i][3])) {
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
            if ( validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][1] + lotteryDataMatrix.lotteryDataMatrix[i][2]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][1] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][2] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3]);
                }
            else {
                canGoThroughSuccessfully3 = false;
                resultsMatrix.resultsMatrix[i][1] = false;
                System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
                
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][1] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
        }
        }


        boolean canGoThroughSuccessfully1 = false;
        if ( lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][1] && lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][2] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
            }
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, lotteryDataMatrix.lotteryDataMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, lotteryDataMatrix.lotteryDataMatrix[i][1]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, lotteryDataMatrix.lotteryDataMatrix[i][3])) { 
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
            if ( validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][1]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][1] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
                }
            else {
                canGoThroughSuccessfully1 = false;
                resultsMatrix.resultsMatrix[i][2] = false;
                System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
                
            }
        }
        else {
            resultsMatrix.resultsMatrix[i][2] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][1] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
        }
        }
        

        boolean canGoThroughSuccessfully2 = false;
        if ( lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][2] && lotteryDataMatrix.lotteryDataMatrix[i][0]==lotteryDataMatrix.lotteryDataMatrix[i][3] ) 
            {resultsMatrix.resultsMatrix[i][3] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");}
        else {
        outerloop:
        for (byte j1=0; j1<3; j1++) {
            for (byte j2=0; j2<3; j2++) {
                if (j1==j2) continue;
                for (byte j3=0; j3<3; j3++) {
                    if (j3==j1 || j3==j2) continue;
                    if (ThreeCombinations.loopThroughCombinationNo(j1, lotteryDataMatrix.lotteryDataMatrix[i][0]) || 
                        ThreeCombinations.loopThroughCombinationNo(j2, lotteryDataMatrix.lotteryDataMatrix[i][2]) || 
                        ThreeCombinations.loopThroughCombinationNo(j3, lotteryDataMatrix.lotteryDataMatrix[i][3])) {
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
            if ( validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][2]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][0] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) || 
                    validValues.contains( (lotteryDataMatrix.lotteryDataMatrix[i][2] + lotteryDataMatrix.lotteryDataMatrix[i][3]) % 10 ) ) {
                    System.out.print("    " +  "\u001B[32m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
                }
            else {
                canGoThroughSuccessfully2 = false;
                resultsMatrix.resultsMatrix[i][3] = false;
                System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
            }
            }
            else {
            resultsMatrix.resultsMatrix[i][3] = false;
            System.out.print("    " +  "\u001B[31m" + lotteryDataMatrix.lotteryDataMatrix[i][0] + " " + lotteryDataMatrix.lotteryDataMatrix[i][2] + " " + lotteryDataMatrix.lotteryDataMatrix[i][3] + "\u001B[0m");
        }
        }



        if (!canGoThroughSuccessfully0 && !canGoThroughSuccessfully1 && !canGoThroughSuccessfully2 && !canGoThroughSuccessfully3) {
        resultsMatrix.countStraightFourKills++;
        System.out.println("\u001B[31m" + "   KILL KILL KILL KILL" + "\u001B[0m" );
        playSound("src/main/resources/kills.wav");
        }
        else System.out.println();

        // System.out.println();
        // for (int j=0; j<4; j++) {
        //     System.out.println("resultsMatrix[" + i + "][" + j + "] = " + resultsMatrix.resultsMatrix[i][j]);
        // }
        
        i++;
        System.out.println();

        
    }
}


// int[][] indices = new int[4][3];
// for (int j=0; j<4; j++) {
//     for (int k=0; k<3; k++) {
//         if (k>=j) indices[j][k] = k+1;
//         else indices[j][k] = k;
//         System.out.println("indices[" + j + "][" + k + "] = " + indices[j][k]);
//     }
    
    
// }





   

 


