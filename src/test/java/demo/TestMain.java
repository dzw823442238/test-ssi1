package demo;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LPT12013 on 2016/9/20.
 */
public class TestMain {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,3,4,5,2,3);
        list.sort(Integer::compare);
        System.out.println(list.toString());

        List<String> list2 = Arrays.asList("52","334","44","151","42");
        list2.sort((s1,s2) -> Integer.compare(Integer.parseInt(s1),Integer.parseInt(s2)));
        System.out.println(list2.toString());

        list2.stream().map(s1 ->s1.length()).forEach(System.out::println);
        System.out.println("---"+list2.stream().filter(s1 ->s1.length()>2).count());
//        list2.stream().map(String::length);
        System.out.println(list2.toString());

        Foo foo = () -> {};
        System.out.println(foo.equals(new Object()));


    }
}
