import java.util.Scanner;

public class Bai4_KiemTraTamGiac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap canh a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhap canh b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhap canh c: ");
        double c = scanner.nextDouble();
        
        // Kiểm tra có phải 3 cạnh tam giác không
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println("a, b, c là 3 canh cua tam giac");
            
            // Xác định loại tam giác
            if (a == b && b == c) {
                System.out.println("Day la tam giac deu");
            } else if (a == b || a == c || b == c) {
                System.out.println("Day la tam giac can");
            } else if (a*a == b*b + c*c || b*b == a*a + c*c || c*c == a*a + b*b) {
                System.out.println("Day la tam giac vuong");
            } else {
                System.out.println("Day la tam giac thuong");
            }
        } else {
            System.out.println("a, b, c ko phai la 3 canh cua tam giac");
        }
        
        scanner.close();
    }
}