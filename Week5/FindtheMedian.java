import java.io.*;
import java.util.*;

public class FindtheMedian {

    // 1. Hàm Quick Sort tự viết (không dùng thư viện)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Phân hoạch mảng và lấy vị trí của pivot
            int pi = partition(arr, low, high);
            
            // Đệ quy sắp xếp nửa bên trái và bên phải của pivot
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // 2. Hàm Partition tự viết (chọn phần tử cuối làm pivot)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; 
        int i = (low - 1); // Chỉ số của các phần tử nhỏ hơn hoặc bằng pivot
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Đổi chỗ arr[i] và arr[j] thủ công
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Đưa pivot về đúng vị trí chính giữa của nó
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrItems[i]);
        }
        
        // Gọi hàm Quick Sort tự viết để sắp xếp mảng
        quickSort(arr, 0, n - 1);
        
        // In ra phần tử ở vị trí chính giữa (Median)
        System.out.println(arr[n / 2]);
    }
}