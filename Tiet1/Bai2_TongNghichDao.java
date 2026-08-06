import java.util.Scanner;

public class Bai2_TongNghichDao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap n nguyen duong: ");
        int n = scanner.nextInt();
        
        double s = 0;
        
        // Tính tổng 1 + 1/2 + 1/3 + ... + 1/n
        for (int i = 1; i <= n; i++) {
            s = s + 1.0 / i;
        }
        
        System.out.println("Tong nghich dao = " + s);
        
        scanner.close();
    }
}