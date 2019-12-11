package alf.util.math;

import lombok.Getter;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author alfred liu
 * Radix
 */
public class Radix {

    @Getter
    int base;

    int[] arrItems;
    public int[] getItems() {
        return arrItems;
    }

    /**
     * Constructor
     * @param base radix base
     * @param value a decimalism value
     * @throws Exception
     */
    public Radix(int base,int value) throws Exception{
        this(base,toArray(base,value));
    }

    /**
     * Constructor
     * @param base radix base
     * @param values  a array that includes values based radix of base
     * @throws Exception
     */
    public  Radix(int base,int[] values) throws Exception{
        this.base =base;
        this.arrItems =values;
    }

    /**
     * Convert a value to a array that includes a set of values based radix of base
     * @param base  radix of base
     * @param value a decimalism value
     * @return
     * @throws Exception
     */
    public static int[] toArray(int base,int value) throws Exception{
        if(base<2) throw new Exception("The parameter 'base' must be greater than 2.");
        if(value<0) throw new Exception("The parameter 'value' must be greater than 2 or equal to  2.");

        List<Integer> list=new ArrayList<>();
        int tmp=value;
        do {
            list.add(tmp%base);
            tmp=tmp/base;
        }while(tmp!=0);
        int[] result=new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return  result;
    }

    @Override
    public String toString() {
        var sb=new StringBuilder();
        for(int i=arrItems.length-1;i>=0;i--){
            sb.append(arrItems[i]);
        }
        return sb.toString();
    }

    /**
     * Convert current object to a decimalism value
     * @return
     */
    public int toDec(){
        return toDec(this);
    }

    /**
     * Convert current object to a decimalism value
     * @return
     */
    public int toDec(Radix radix){
        return toDec(radix.base,radix.arrItems);
    }
    /**
     * Convert current object to a decimalism value
     * @return
     */
    public  static int toDec(int base,int[] list){
        double value=0;
        for(int i=list.length-1;i>=0;i--){
            value+=Math.pow(base,i)*list[i];
        }
        return (int)value;
    }

    public Radix Plus(int... values) throws Exception{
        return  ConPlus(true,values);
    }
    public Radix ConPlus(boolean plusFlag,int... values) throws Exception {
        if(values==null||values.length<1) throw new Exception("The parameter 'values' is null or empty.");
        for(int i=0;i<values.length;i++){
            if(values[i]>=base||values[i]<0)
                throw new Exception("The parameter 'values' include illegal value. the item value must be greater than 0 and less than base '"+base +"'." );
        }
        int length=values.length>arrItems.length?values.length:arrItems.length;
        int[] result=new int[length];
        int[] arrLeft=new int[length];
        int[] arrRight=new int[length];

        int[] arr4list=arrItems;
        System.arraycopy(arr4list,0,arrLeft,0,arr4list.length);
        System.arraycopy(values,0,arrRight,0, values.length);

        for(int i=0,step=0;i<length;i++){
            if(plusFlag){
                int value = arrLeft[i] + arrRight[i] + step;
                result[i] =value%base;
                step=value>=base?1:0;
            }
        }

        for (int i=result.length-1;i>=0;i--){
            if(result[i]>0){
                int[] tmpArr= Arrays.copyOfRange(result,0,i+1);
                return new Radix(this.base,tmpArr);
            }
        }

        return new Radix(this.base,result);
    }

}
