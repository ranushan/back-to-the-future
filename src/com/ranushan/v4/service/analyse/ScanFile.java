package com.ranushan.v4.service.analyse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.emptyList;

public class ScanFile implements Scan {

    private final List<String> moviesList;
    private Scanner scanner;

    public ScanFile() {
        scanner = new Scanner(System.in); // Get Input File
        moviesList = new ArrayList<>();
    }

    @Override
    public List<String> process() {
        System.out.print("Enter the absolute path of your file : ");
        File file = new File(scanner.nextLine());
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                moviesList.add(scanner.nextLine());
            }
        } catch (Exception e) { // Any Exception occurs exit program
            System.out.println("Apologies, an error has occurred.");
            return emptyList();
        }
        return moviesList;
    }

    @Override
    public void finish(List<String> moviesList) {
        scanner.close();
        Scan.super.finish(moviesList);
    }
}
