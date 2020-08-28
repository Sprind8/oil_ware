package testpro;

import java.util.Scanner;

public class TestYearDays {//哪一年一个月的天数。
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year=scanner.nextInt();
        int months=scanner.nextInt();
        int[] D=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if(year%400==0||year%4==0&&year%100!=0){
            D[1]+=1;
        }
        System.out.println( D[months-1]);
    }

}
