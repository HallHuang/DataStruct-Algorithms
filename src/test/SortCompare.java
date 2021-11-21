package test;

import sort.InsertionSort;
import sort.MergeSort;
import sort.ShellSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 在1-10000完全逆序的数据基础上，比较各个排序算法的实际计算时间
 */
public class SortCompare {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        InputStream resourceAsStream = SortCompare.class.getClassLoader().getResourceAsStream("reverse_arr.txt");
        if (resourceAsStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line;
            while ((line = reader.readLine()) != null) {
                int i = Integer.parseInt(line);
                list.add(i);
            }
            reader.close();
        }
        Integer[] a = new Integer[list.size()];
        list.toArray(a);

        testInsertion(a); //大约21500ms
        testShell(a);   //大约25ms
        testMerge(a);   //大于50ms
    }

    //测试希尔排序
    public static void testShell(Integer[] a) {
        //1.获取执行之前的时间
        long start = System.currentTimeMillis();
        //2.执行算法代码
        ShellSort.sort(a);
        //3.获取执行之后的时间
        long end = System.currentTimeMillis();
        //4.算出程序执行的时间并输出
        System.out.println("希尔排序执行的时间为：" + (end - start) + "毫秒");

    }

    //测试插入排序
    public static void testInsertion(Integer[] a) {
        //1.获取执行之前的时间
        long start = System.currentTimeMillis();
        //2.执行算法代码
        InsertionSort.sort(a);
        //3.获取执行之后的时间
        long end = System.currentTimeMillis();
        //4.算出程序执行的时间并输出
        System.out.println("插入排序执行的时间为：" + (end - start) + "毫秒");
    }

    //测试归并排序
    public static void testMerge(Integer[] a) {
        //1.获取执行之前的时间
        long start = System.currentTimeMillis();
        //2.执行算法代码
        MergeSort.sort(a);
        //3.获取执行之后的时间
        long end = System.currentTimeMillis();
        //4.算出程序执行的时间并输出
        System.out.println("归并排序执行的时间为：" + (end - start) + "毫秒");
    }

}
