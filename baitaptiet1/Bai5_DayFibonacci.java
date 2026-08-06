import java.util.Scanner;

public class Bai5_DayFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap n nguyen duong: ");
        int n = scanner.nextInt();
        
        int a = 0; // Số Fibonacci thứ 1
        int b = 1; // Số Fibonacci thứ 2
        
        System.out.print("Day Fibonacci: ");
        
        if (n == 1) {
            System.out.print(a);
        } else if (n == 2) {
            System.out.print(a + " " + b);
        } else {
            System.out.print(a + " " + b);
            
            // Tính và hiển thị các số tiếp theo
            for (int i = 3; i <= n; i++) {
                int c = a + b;
                System.out.print(" " + c);
                a = b;
                b = c;
            }
        }
        
        System.out.println();
        scanner.close();
    }
}