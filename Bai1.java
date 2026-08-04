import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap n nguyen duong: ");
        int n = scanner.nextInt();
        
        int s = 0;
        
        // Nếu n là số lẻ, giảm n đi 1 để thành số chẵn
        if (n % 2 != 0) {
            n = n - 1;
        }
        
        // Tính tổng các số chẵn từ 2 đến n
        for (int i = 2; i <= n; i = i + 2) {
            s = s + i;
        }
        
        System.out.println("Tong cac so chan = " + s);
        
        scanner.close();
    }
}