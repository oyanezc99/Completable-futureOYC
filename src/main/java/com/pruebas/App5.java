package com.pruebas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello ")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "World!!!"));
        System.out.println(completableFuture.get());
        System.out.println("-------------------------------------------");

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello ")
                .thenCombine(CompletableFuture.supplyAsync(()->"World..."),(s1,s2)->s1+s2);
        System.out.println(completableFuture2.get());
        System.out.println("-------------------------------------------");
       /* CompletableFuture<Void> completableFuture3 =*/ CompletableFuture.supplyAsync(() -> "Hello ")
                .thenAcceptBoth(CompletableFuture
                        .supplyAsync(() -> "World!!!..."), (s1, s2) -> System.out.println(s1 + s2));
        //completableFuture3.get();

    }
}
