// Given weights and values of n items, and a capacity W, 
// maximize the total value in the knapsack by taking fractions of items.
import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {
    public static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0.0;

        for (Item item : items) {
            if (W == 0) {
                break;
            }

            int weightToTake = Math.min(item.weight, W);
            totalValue += weightToTake * ((double) item.value / item.weight);
            W -= weightToTake;
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = { new Item(10, 60), new Item(20, 100), new Item(30, 120) };
        int W = 50;
        System.out.println("Maximum value in knapsack = " + fractionalKnapsack(W, items));
    }
}

/*
Maximum value in knapsack = 240.0
*/
