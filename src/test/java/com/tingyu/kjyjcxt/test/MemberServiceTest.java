package com.tingyu.kjyjcxt.test;

import com.tingyu.kjyjcxt.dto.MemberDTO;
import com.tingyu.kjyjcxt.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:11
 * @Version kjyjcxt
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testCreate() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setId("200002");
        member.setAge(10);
        member.setName("张三");
        memberService.insert(member);

    }

    @Test
    public void testBigDecimal() {

        List<String> moneyList = new ArrayList<>();
        moneyList.add("100.00");
        moneyList.add("20.00");
        moneyList.add("40.00");
        moneyList.add("50.00");
        BigDecimal total = BigDecimal.ZERO;


        System.out.println(moneyList.stream().map(m -> {
            return new BigDecimal(m);
        }).count());


    }

    @Test
    public void test() throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        Future<Integer> future=null;
        try {

            for (int i = 1; i <= 20; i++) {
                final int tempInt = i;
                final int[] sum = {0};
               /* threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "处理了" + tempInt + "号任务");
                    try {
                        TimeUnit.MICROSECONDS.sleep(300L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });*/

               future = threadPool.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {

                        sum[0] = sum[0] + tempInt;
                        return sum[0];
                    }
                });




            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();//释放资源
        }

        System.out.println(future.get());
        System.out.println(Thread.currentThread().getName() + "执行完成...");


    }


}
