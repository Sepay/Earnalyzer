package org.example;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;


@Service
public class Executer {
    public static String[] execute(String crypto, String amount, String duration, String lock) throws InterruptedException, IOException {
        String finalLock;
        if (lock.equals("More")){
            finalLock = lock.toLowerCase();
        }
        else{
            finalLock = lock;
        }

        CompletableFuture<String[]> croFuture = CompletableFuture.supplyAsync(() ->
        {
            try {
                return CROWebDriving.main(crypto, amount, duration, finalLock);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String[]> binanceFuture = CompletableFuture.supplyAsync(() ->
        {
            try {
                return BinanceWebDriving.main(crypto, amount, duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(croFuture, binanceFuture);
            combinedFuture.get(); // Wait for both futures to complete

            String[] results = Stream.concat(Arrays.stream(croFuture.get()), Arrays.stream(binanceFuture.get()))
                    .toArray(String[]::new);
            return results;
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
        }

        return new String[0];
    }
}
