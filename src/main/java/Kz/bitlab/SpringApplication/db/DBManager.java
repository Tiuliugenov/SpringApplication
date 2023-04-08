package Kz.bitlab.SpringApplication.db;

import ch.qos.logback.core.util.COWArrayList;

import java.util.List;
import java.util.ArrayList;
import Kz.bitlab.SpringApplication.models.Item;


public class DBManager {
    private static List <Item> items =new ArrayList<>();

    static {
        items.add(new Item( 1L, "Iphone", "128 Gb", 3000));
        items.add(new Item( 2L, "Nokia", "8 Gb", 300));
        items.add(new Item( 3L, "Lg", "80 Gb", 1000));
        items.add(new Item( 1L, "Iphone", "128 Gb", 3000));
        items.add(new Item( 2L, "Nokia", "8 Gb", 300));
        items.add(new Item( 3L, "Lg", "80 Gb", 1000));
    }

    public static List <Item> getItems(){
        return items;
    }
}
