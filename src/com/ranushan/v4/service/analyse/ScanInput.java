package com.ranushan.v4.service.analyse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ranushan.v4.util.StringUtils.emptyValue;
import static java.util.Collections.emptyList;

public class ScanInput implements Scan {

    private final Scanner scanner;
    private final List<String> moviesList;

    public ScanInput() {
        scanner = new Scanner(System.in); // Get Input from user by Scanner
        moviesList = new ArrayList<>();
    }

    @Override
    public List<String> process() {
        String input; // Store input text from user
        try {
            do {
                System.out.print("Enter your movie or write exit : ");
                input = scanner.nextLine(); // store value
                if(emptyValue(input)) { // Wrong input like empty value
                    System.out.print("Try again => ");
                }
                if(!input.equals("exit") && !emptyValue(input)) { // Adding input to the list
                    moviesList.add(input);
                }
            } while (!"exit".equalsIgnoreCase(input));
        } catch (Exception e) { // Any Exception occurs exit program
            System.out.println("Apologies, an error has occurred.");
            return emptyList();
        }
        return moviesList;
    }

    @Override
    public void finish(List<String> moviesList) {
        scanner.close(); // Close scanner communication
        Scan.super.finish(moviesList);
    }
}
