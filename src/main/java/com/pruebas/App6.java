package com.pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Array.asList crea una lista fija donde no se puede
        agregar ni quitar elementos*/
        List<CompletableFuture<String>> futuros = Arrays.asList(
                CompletableFuture.supplyAsync(()->"India"),
                CompletableFuture.supplyAsync(()->"is"),
                CompletableFuture.supplyAsync(()->"a beautiful country"));
        /*new CompletableFuture[0] va para que no infiera
        erroneamente el tipo de datos y segundo para que empiece
        en cero*/
        /*recordar que a veces hay que pasar elementos individuales
        * se debe convertir en un arreglo ya que no soporta una
        * lista pero eso solo se debe a como fue creado el metodo
        * a usar*/
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futuros.toArray(new CompletableFuture[0]));
        completableFuture.thenRun(()->{
            List<String> resultados = futuros.stream().map(CompletableFuture::join).collect(Collectors.toList());
            String rpta = String.join(" ", resultados);
            System.out.println(rpta);
        });

        completableFuture.join();

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(()->"India");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->"is");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->"beautiful country");

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(future,future1,future2);

        System.out.println(completableFuture.get());
        System.out.println(future.isDone());
        System.out.println(future1.isDone());
        System.out.println(future2.isDone());

        String result = Stream.of(future,future1,future2).map(CompletableFuture::join).collect(Collectors.joining(" "));
        System.out.println(result);

        completableFuture.thenRun(()->{
            String strR = Stream.of(future,future1,future2).map(CompletableFuture::join).collect(Collectors.joining(" "));
            System.out.println(strR);
        });
        completableFuture.join();*/




    }
}
