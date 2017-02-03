package test;

import java.util.Random;

/**
 * Created by Administrator on 2017/1/29.
 */
public class Randomtest {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(2));
        }
    }
}
