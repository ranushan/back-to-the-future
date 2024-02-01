package com.ranushan.v4.service.formula;

public class BackDvd implements Dvd {

    @Override
    public double apply(int item) {
        if (item == 0) return 0;
        if (item == 1) return (15 * item) * 1.0;
        if (item == 2) return (15 * item) * 0.9;
        return (15 * item) * 0.8;
    }
}
