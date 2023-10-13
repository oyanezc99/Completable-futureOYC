package com.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class App2 {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = writeAsync();
        try {
            String result = completableFuture.get();
            System.out.println(result);
            System.out.println(Thread.currentThread().getName());
            System.out.println("-----------------------------------------");
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> writeAsync(){
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        List<String> list = new ArrayList<>();
        list.add("KK");
        list.add("PK");
        list.add("SK");

        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(2000);
                String joinStr = String.join(",", list);
                System.out.println(Thread.currentThread().getName());
                completableFuture.complete(joinStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        return  completableFuture;
    }


}
