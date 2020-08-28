package testpro;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.valueOf(scanner.nextLine());
        HashMap<Integer, Integer> mapx = new HashMap<Integer, Integer>();//保存x的值
        HashMap<Integer, Integer> mapy = new HashMap<Integer, Integer>();//保存y的值
        int a = -1, b = -1;
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                if (string.equals(""))
                {
                break;}
          String[] ss = string.split(",");
                int x = Integer.valueOf(ss[0]);
                int y = Integer.valueOf(ss[1]);
                if (!mapx.containsKey(x))
                    mapx.put(x, 1);
                else if (mapx.containsKey(x) && a == -1) {
                    a = x;
                } else if (mapx.containsKey(x) && a != -1 && x != a)
                    System.out.println("-1,-1");

                if (!mapy.containsKey(y))
                    mapy.put(y, 1);
                else if (mapy.containsKey(y) && b == -1)
                    b = y;
                else if (mapy.containsKey(y) && b != -1 && y != b)
                    System.out.println("-1,-1");
            }
            if(b==-1)
            {
                b = Integer.MAX_VALUE;
               while (mapy.keySet().iterator().hasNext())
                  b = Math.min(b, mapy.keySet().iterator().next());
            }
            if(a == -1)
        {
            a = Integer.MAX_VALUE;
            while (mapx.keySet().iterator().hasNext())
                a = Math.min(a, mapx.keySet().iterator().next());
        }
        System.out.println(+a + "," + b);
    }}
//        if(a==-1)
//        {    a=Integer.MAX_VALUE;
//        while(mapx.keySet().iterator().hasNext())
//            a=Math.min(a, mapx.keySet().iterator().next());}
//
//        if(b==-1)
//        {   b=Integer.MAX_VALUE;
//        while(mapx.keySet().iterator().hasNext())
//            b=Math.min(b, mapx.keySet().iterator().next());}
//
//
//
//        System.out.println(+a+","+b);
//    }
