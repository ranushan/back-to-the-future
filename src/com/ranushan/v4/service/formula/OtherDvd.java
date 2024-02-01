package com.ranushan.v4.service.formula;

public class OtherDvd implements Dvd {

    @Override
    public double apply(int item) {
        return item * 20.0;
    }
}
