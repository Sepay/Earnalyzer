package org.example;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

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

        String[] results = CROWebDriving.main(crypto, amount, duration, finalLock);
        String[] results2 = BinanceWebDriving.main(crypto,amount, duration);
        String[] combinedResults = Stream.concat(Arrays.stream(results), Arrays.stream(results2))
                .toArray(String[]::new);
        return combinedResults;
    }
}
