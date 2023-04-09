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
        items.add(new Item( 4L, "Samsung", "128 Gb", 3000));
        items.add(new Item( 5L, "Meizu", "8 Gb", 300));
        items.add(new Item( 6L, "Ri", "80 Gb", 1000));
    }
public static void addItem(Item item){
  items.add(item);
}
    public static List <Item> getItems(){
        return items;
    }
    public static Item getItemById(Long id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
