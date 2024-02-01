package com.ranushan.v4;

import com.ranushan.v4.service.analyse.Scan;
import com.ranushan.v4.service.analyse.ScanFile;
import com.ranushan.v4.service.analyse.ScanInput;

import java.util.Scanner;

public class BackToTheFuture {

    public static void main(String[] args) {
        int select = selectScanningMode();
        Scan scan = select == 1 ? new ScanInput() : new ScanFile();
        var movies = scan.process();
        scan.finish(movies);
    }

    private static int selectScanningMode() {
        Scanner s = new Scanner(System.in);
        int select;
        do {
            System.out.print("Choose input (press 1) or file (press 2) : ");
            select = s.nextInt();
        } while (select != 1 && select != 2);
        return select;
    }
}


