package home.ginatulin.utils;

import home.ginatulin.models.Product;

import java.util.Random;

public class Randomaizer {
    private static final String[] titles = {"tea", "coffee", "cookies", "apple", "orange",
            "pineapple", "kiwi", "cucumber", "tomato", "potatoes"};

    public static Product getRandomProduct() {
        return Product.builder()
                .id(getRandomInt(0, 10_000))
                .title(getRandomTitle())
                .cost(getRandomInt(1_000, 1_000_000))
                .build();
    }

    private static String getRandomTitle() {
        return titles[getRandomInt(0, 10)];
    }

    private static int getRandomInt(int min, int max) {
        return min + new Random().nextInt(max);
    }
}
