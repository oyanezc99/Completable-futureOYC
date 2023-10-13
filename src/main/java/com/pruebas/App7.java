package com.pruebas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App7 {
    public static void main(String[] args) {
        runAsyncHandle("Omar");
        runAsyncHandle(null);
        runAsyncUnHandle("Carla");
        runAsyncUnHandle(null);
    }

    private static void runAsyncHandle(String name){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            if(name == null)
                throw new RuntimeException("Computation Error!!!");
            return "Hello, " + name;
        }).handle((result,error) -> result!=null?result:"Hello Stranger!!! " + error.getMessage());
        try {
            System.out.println(completableFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runAsyncUnHandle(String name) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            if(name == null)
                throw new RuntimeException("Computation Error!!!");
            return "Hello, " + name;
        });
        try {
            System.out.println(completableFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Hello Stranger " + e.getMessage());
        }
    }


}
