import java.io.*;
import java.util.*;

public class QuicksortInplace {

    // Hàm thực hiện QuickSort đệ quy
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Phân hoạch mảng và lấy vị trí chính xác của pivot
            int pi = partition(arr, low, high);
            
            // In ra toàn bộ mảng sau mỗi lần phân hoạch hoàn tất
            printArray(arr);
            
            // Đệ quy sắp xếp nửa bên trái và nửa bên phải của pivot
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Hàm phân hoạch theo phương pháp Lomuto (chọn phần tử cuối làm pivot)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Chọn phần tử cuối làm pivot
        int i = low;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                // Đổi chỗ arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        
        // Đưa pivot về đúng vị trí của nó (giữa mảng con)
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        
        return i; // Trả về chỉ số của pivot
    }

    // Hàm hỗ trợ in mảng cách nhau bởi khoảng trắng
    public static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        quickSort(arr, 0, n - 1);
        scanner.close();
    }
}