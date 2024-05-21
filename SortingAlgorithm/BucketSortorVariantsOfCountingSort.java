import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void bucketSort(float[] array) {
        if (array.length == 0) {
            return;
        }

        // Create empty buckets
        int n = array.length;
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribute elements into buckets
        for (float num : array) {
            int bucketIndex = (int) (num * n);
            buckets[bucketIndex].add(num);
        }

        // Sort individual buckets and concatenate results
        int index = 0;
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
            for (float num : bucket) {
                array[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] array = {0.78f, 0.17f, 0.39f, 0.26f, 0.72f, 0.94f, 0.21f, 0.12f, 0.23f, 0.68f};
        bucketSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

// Sorted array: [0.12, 0.17, 0.21, 0.23, 0.26, 0.39, 0.68, 0.72, 0.78, 0.94]

/*
Bucket sort is an algorithm that distributes elements into several buckets, sorts each bucket individually 
(using another sorting algorithm, often insertion sort or counting sort), and then concatenates the sorted buckets.
*/
