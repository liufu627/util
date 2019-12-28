package alf.util.math;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RadixTest {

    @Test
    public void plus() throws Exception {
        int base=3;
        for(int i=0;i<10;i++) {
            Radix radix = new Radix(base,i);
            System.out.println(String.format("%03d",Integer.valueOf(radix.toString())));
            assertEquals(i, radix.toDec());
        }
    }

    @Test
    public void TestReflect(){
        Class<Integer> integerClass=Integer.class;
        //Integer integer=new Integer();
        try {
            Constructor[] c1 = integerClass.getConstructors();
            for (Constructor item:c1) {
                Class[] parameterTypes = item.getParameterTypes();
                if(parameterTypes.length == 1 && parameterTypes[0] == int.class)
                {
                    Integer integer = (Integer) item.newInstance(20);

                    System.out.println(integer);
                    break;
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        IVehical car = new Car();

        //Car.class.getClassLoader()
        IVehical vehical = (IVehical)Proxy.newProxyInstance(car.getClass().getClassLoader(), Car.class.getInterfaces(), new MyHandler(car));
        vehical.run();
    }
    public interface IVehical {
        void run();
    }

    public class Car implements IVehical {
        public void run() {
            System.out.println("Car会跑");
        }
    }
    public class  MyHandler implements InvocationHandler
    {
        private IVehical target;
        public MyHandler(IVehical t) {
            target = t;
        }

        @Override
        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            System.out.print(target);
            System.out.print("!!." + m.getName()+"(");
            if(args!=null){
                for(int i=0;i<args.length;i++){
                    System.out.print(args[i]);
                    if(i<args.length-1) System.out.print(",");
                }
            }
            System.out.println(")!!");
            return  m.invoke(target,args);
        }
    }

    @Test
    public void TestArr(){

//        int i=100;
//        String d="abc" + i;
//        double[][] arr={
//            {10.0,11.0}
//            ,{9.0}
//            ,{12.0,13.0,14.02}
//        };
//
//        for (double[] arr2 : arr){
//            for(double item :arr2)
//                System.out.print(item);
//            System.out.println();
//        }
//        System.out.println(arr.getClass());
//        Integer[] integers={1,2,3};
//        System.out.println(integers.getClass());
//        Integer[][] integers2={{1,2,3},{1,2,3}};
//        System.out.println(integers2.getClass());
//        int[][] integers3={{1,2,3},{1,2,3}};
//        System.out.println(integers3.getClass());
//        Object[] obj1=new Object[10];
//        System.out.println(obj1.getClass());
//        System.out.println(Object.class);
//
//        System.out.println( new boolean[1]);
//
//        int k=10;
//        Integer[] obj = (Integer[])getT(k);
//
//        if(obj.getClass().isArray()) {
//            for (int item : obj)
//                System.out.println(item);
//        }

        Comparable<Integer> comparable=new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };

    }

    public <T> Object getT(T value) {
         Object arr = Array.newInstance(value.getClass(),10);
        int length = Array.getLength(arr);
        for(int i=0;i<length;i++)
            Array.set(arr,i,value);
         return arr;
    }

    @Test
    public   void roundUpToPowerOf2() {
        int number = 3;
        // assert number >= 0 : "number must be non-negative";
        int value= number >= 30
                ? 30
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;

        System.out.println(value);
    }
}