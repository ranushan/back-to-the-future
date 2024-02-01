package com.ranushan.v4.service.analyse;

import com.ranushan.v4.service.formula.BackDvd;
import com.ranushan.v4.service.formula.OtherDvd;

import java.math.BigDecimal;
import java.util.List;

public interface Scan {

    /**
     * Processing movie
     * @return List of movies
     */
    List<String> process();

    /**
     * Get amount for all movies
     * @param moviesList All movies providing by User
     */
    default void finish(List<String> moviesList) {
        var backToFuture = moviesList.stream()
                .filter(m -> m.startsWith("Back to the Future"))
                .toList()
                .size();
        var otherMovies = moviesList.stream()
                .filter(m -> !m.startsWith("Back to the Future"))
                .toList()
                .size();
        System.out.printf("Amount : %.2f €%n",
                BigDecimal.valueOf(new BackDvd().apply(backToFuture))
                        .add(BigDecimal.valueOf(new OtherDvd().apply(otherMovies)))
                        .doubleValue());
    }
}
