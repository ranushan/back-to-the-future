package com.ranushan.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntToDoubleFunction;

public class BackToTheFuture {

    private static final List<String> moviesList = new ArrayList<>();
    private static double count = 0.0;

    public static void main(String[] args) {
        // Store input text from user
        String input;
        // Get Input from user by Scanner
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                input = read(scanner);
            } catch (Exception e) { // Any Exception occurs exit program
                System.out.println("Apologies, an error has occurred.");
                return;
            }
        } while (!"exit".equalsIgnoreCase(input));
        scanner.close(); // Close scanner communication
        System.out.printf("Amount : %.2f €%n", analyseMovie());
    }

    /**
     * Read value providing by User
     * @param scanner Scanner in console
     * @return input value
     */
    private static String read(Scanner scanner) {
        String input;
        System.out.print("Enter your movie or write exit : ");
        input = scanner.nextLine(); // store value
        if(emptyValue(input)) { // Wrong input like empty value
            System.out.print("Try again => ");
        }
        if(!input.equals("exit") && !emptyValue(input)) { // Adding input to the list
            moviesList.add(input);
        }
        return input;
    }

    /**
     * Analyse movie input providing by User
     * @return amount
     */
    private static double analyseMovie() {
        var backToFuture = moviesList.stream()
                .filter(m -> m.startsWith("Back to the Future"))
                .toList()
                .size();
        var otherMovies = moviesList.stream()
                .filter(m -> !m.startsWith("Back to the Future"))
                .toList()
                .size();

        discount(backToFuture,
                b -> count + (15 * b) * 1.0,
                b -> count + (15 * b) * 0.9,
                b -> count + (15 * b) * 0.8
        );

        if (otherMovies > 0) {
            count += (otherMovies * 20);
        }
        return count;
    }

    private static void discount(
            int backToFuture,
            IntToDoubleFunction noReduc,
            IntToDoubleFunction twoReduc,
            IntToDoubleFunction thrReduc
    ) {
        count += switch (backToFuture) {
            case 0 -> 0;
            case 1 -> noReduc.applyAsDouble(backToFuture);
            case 2 -> twoReduc.applyAsDouble(backToFuture);
            default -> thrReduc.applyAsDouble(backToFuture);
        };
    }

    /**
     * Check if input value providing by User is empty or blank
     * @param value Input value
     * @return Is input is empty or blank
     */
    private static boolean emptyValue(String value) {
        return value.isBlank() || value.isEmpty();
    }
}
