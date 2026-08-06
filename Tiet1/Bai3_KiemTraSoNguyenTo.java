import java.util.Scanner;

public class Bai3_KiemTraSoNguyenTo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap so nguyen n: ");
        int n = scanner.nextInt();
        
        boolean laSoNguyenTo = true;
        
        // Số nhỏ hơn 2 không phải số nguyên tố
        if (n < 2) {
            laSoNguyenTo = false;
        } else {
            // Kiểm tra từ 2 đến căn bậc hai của n
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    laSoNguyenTo = false;
                    break;
                }
            }
        }
        
        if (laSoNguyenTo) {
            System.out.println(n + " la so nguyen to");
        } else {
            System.out.println(n + " khong phai la so nguyen to");
        }
        
        scanner.close();
    }
}