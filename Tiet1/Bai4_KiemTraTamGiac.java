import java.util.Scanner;

public class Bai4_KiemTraTamGiac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Nhap a: ");
        int a = scanner.nextInt();
        System.out.println(" Nhap b: ");       
        int b = scanner.nextInt();
        System.out.println(" Nhap c: ");
        int c = scanner.nextInt();
        if (a+b>c && a+c>b && b+c>a) {
            System.out.println("a, b, c la 3 canh cua tam giac");
            if (a==b && b==c){
                System.out.println("day la tam giac deu");
            }
            else if (a+b==c || a+c==b || b+c==a){
                System.out.println("day la tam giac deu");
            }
            else if (a*a + b*b == c*c || a*a + c*c == b*b || b*b + c*c == a*a){
                System.out.println("day la tam giac vuong");
            }
            else {
                System.out.println(" day la tam giac thuong");
            }  
        }
        else {
            System.out.println("a, b, c khong phai la 3 canh cua tam giac");
        }

        scanner.close();
    }
}
