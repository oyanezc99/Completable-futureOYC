package com.pruebas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App4 {

    public static void main(String[] args) throws ExecutionException , InterruptedException{
        /*Recordar que el thenApply() solo puede instanciarce
         en la creacion del supplyAsinc , si lo hacemos separado
          existe la posibilidad que haya error de concurrencia*/
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(()->"Beautiful").thenApply(s -> s+" World");

        String result = completableFuture.get();
        System.out.println(result);

        System.out.println("----------------------------------");
        /*Aqui el thenAccept() ejecuta acciones al
        finalizar el suppliAsync pero puede consumiendo el resultado*/
        CompletableFuture<String> completableFuture1 = CompletableFuture
                .supplyAsync(()->"Beautiful");
        completableFuture1.thenAccept(s -> {
                    System.out.println("My computation is " + s);
                });
        completableFuture1.get();

        System.out.println("----------------------------------");
        /*Aqui el theRun() ejecuta acciones al
        finalizar el supplyAsync pero sin consumir el resultado*/
        CompletableFuture<String> completableFuture2 = CompletableFuture
                .supplyAsync(()->"Beautiful");
        completableFuture2.thenRun(() ->{
            System.out.println("My task is done..");
            System.out.println("Ejecuto antes");
        });

        String str = completableFuture2.get();
        System.out.println("Se imprimira el resultado");
        System.out.println(str);
    }
}
