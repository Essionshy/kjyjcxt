package com.tingyu.kjyjcxt.task;

import java.util.concurrent.RecursiveTask;

/**
 * @Author essionshy
 * @Create 2021/7/26 21:56
 * @Version kjyjcxt
 */
public class TotalTask extends RecursiveTask<Integer> {

    private int begin;
    private int end;

    private int result;

    public TotalTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= 10) {
            result = calculateSingle(begin, end);
        } else {
            int mid = (end + begin) / 2;
            TotalTask left = new TotalTask(begin, mid);
            TotalTask right = new TotalTask(mid + 1, end);
            left.fork();
            right.fork();
            result = left.join() + right.join();
        }
        return result;
    }

    private Integer calculateSingle(int begin, int end) {
        int result = 0;
        for (int i = begin; i <= end; i++) {
            result += i;
        }
        return result;
    }
}
