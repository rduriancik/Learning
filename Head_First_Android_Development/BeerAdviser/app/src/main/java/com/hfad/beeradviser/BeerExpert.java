package com.hfad.beeradviser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by robert on 29.8.2016.
 */
public class BeerExpert {
    public List<String> getBrands(String color) {
        if (color == null) {
            return Collections.emptyList();
        }

        List<String> brands = new ArrayList<>();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }

        return brands;
    }
}
