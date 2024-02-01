package com.ranushan.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BackToTheFuture {

    private static final List<String> moviesList = new ArrayList<>();

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

        double count = 0.0;
        return BigDecimal.valueOf(new BackDvd().apply(count, backToFuture))
                .add(BigDecimal.valueOf(new OtherDvd().apply(count, otherMovies)))
                .doubleValue();
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

@FunctionalInterface
interface Dvd {
    double apply(double count, int item);
}


class BackDvd implements Dvd {

    @Override
    public double apply(double count, int item) {
        if(item == 0) return count + 0;
        if(item == 1) return count + ((15 * item) * 1.0);
        if(item == 2) return count + ((15 * item) * 0.9);
        return count + ((15 * item) * 0.8);
    }
}

class OtherDvd implements Dvd {

    @Override
    public double apply(double count, int item) {
        return count + (item * 20);
    }
}