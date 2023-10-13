package com.pruebas;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        List<Integer> values = IntStream.rangeClosed(0, 300).boxed().collect(Collectors.toList());
        Executor executor = Executors.newFixedThreadPool(Math.min(values.size(),200), r -> {
            Thread hilo = new Thread(r);
            hilo.setDaemon(true);
            return hilo;
        });

        long start = System.currentTimeMillis();
        ExternalService service = new ExternalService(executor);
        List<CompletableFuture<Integer>> completableFutureList = values.stream()
                .map(service::processAsync)
                .collect(Collectors.toList());
        completableFutureList.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
        System.out.println("Took: " + (System.currentTimeMillis() - start) + " milliseconds");
    }
}


class ExternalService {
    Executor executor;

    public ExternalService(Executor executor) {
        this.executor = executor;
    }

    Integer process(Integer value) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value + 1;
    }

    CompletableFuture<Integer> processAsync(Integer value) {
        return CompletableFuture.supplyAsync(() -> process(value), executor);
    }

}
