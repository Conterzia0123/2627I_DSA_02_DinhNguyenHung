import java.io.*;
import java.util.*;

public class Quicksort1 {
    public static List<Integer> quickSort(List<Integer> arr) {
        int pivot = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int num : arr) {
            if (num < pivot) {
                left.add(num);
            } else if (num > pivot) {
                right.add(num);
            } else {
                equal.add(num);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        result.addAll(left);
        result.addAll(equal);
        result.addAll(right);
        
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(arrItems[i]));
        }
        
        List<Integer> result = quickSort(arr);
        
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}