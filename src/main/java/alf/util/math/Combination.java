package alf.util.math;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Alfred liu
 *
 */
public class Combination {

    public   static String Bin(int value)
    {
        boolean zerobelow = false;
        if(value==0) return "0";
        if(value<0) {
            zerobelow=true;
            value = -value;
        }

        //BigDecimal
        StringBuilder sb=new StringBuilder();
        int a=0b1;
        while (true){
            if(value==0 )break;
            sb.insert(0,value &a);
            value=value>>>1;
        }
        if(zerobelow)
            sb.insert(0,"-");
        return sb.toString();
    }

    public static String[] GetCombinations(int startValue,int n) {
        List<String> list=new ArrayList<String>();
        //int startValue=0b10000110;
        while(true) {
            startValue++;
            //String tmp = Integer.toBinaryString(start).substring(1);
            String tmp=Bin(startValue).substring(1);
//            String tmp = str;
//            str.substring(str.length() - 6, str.length() - 1);
            int foundOne = 0;
            for (int index = 0; index < tmp.length(); index++) {
                if (tmp.charAt(index) == '1') foundOne++;
            }
            if(foundOne==tmp.length()) {
                break;
            }

            if(foundOne==n){
                list.add(tmp);
            }
        }

        return list.toArray(new String[0]);
    }

    public static  int Factorial(int m){
        if(m<=1)
            return 1;
        return  m*Factorial(m-1);
    }
    public  static  int CalcCombination(int m, int n){
        return Factorial(m)/(Factorial(n)*Factorial(m-n));
    }
    
}
