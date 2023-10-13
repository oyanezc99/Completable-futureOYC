package com.pruebas;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class App3 {

    public static void main(String[] args){
        /*el metodo runAsync() ejecuta un runable
        por lo que no devuelve ningun valor*/
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()-> {
                    System.out.println("My task1 is completed");
        });

        try{
            /*el metodo .get() espera la finalizacion del async sino
            proseguira con los demas procesos*/
            completableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*newFixedThreadPool(n) ejecuta la tarea con una
        cantidad de hilos seleccionada = n */
        System.out.println("-------------------");
        CompletableFuture.runAsync(()-> {
            System.out.println("My task2 is completed");
        }, Executors.newFixedThreadPool(2));

        System.out.println("-------------------");

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(()->"Hello World!!!");
        String strResult = "";
        try{
            strResult = supplyAsync.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        System.out.println(strResult);
        System.out.println("-------------------");
        /*newCachedThreadPool() ejecuta el async pero autogestionando
        de forma ilimitada los hilos dependiendo el requerimiento*/
        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync((()->"Hello World!!!"), Executors.newCachedThreadPool());
        strResult = "";
        try{
            strResult = supplyAsync2.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        System.out.println(strResult);
    }
}
