package com.tingyu.kjyjcxt.controller;

import com.tingyu.kjyjcxt.common.PublicResult;
import com.tingyu.kjyjcxt.dto.MemberDTO;
import com.tingyu.kjyjcxt.service.MemberService;
import com.tingyu.kjyjcxt.task.TotalTask;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @Author essionshy
 * @Create 2021/6/1 22:08
 * @Version kjyjcxt
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("save")
    public void save(@RequestBody MemberDTO dto){
        try {
            memberService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("get/{id}")
    public PublicResult get(@PathVariable("id") String id) throws Exception {

        ForkJoinPool threadPool=new ForkJoinPool();
        TotalTask totalTask = new TotalTask(1, 100);
        ForkJoinTask<Integer> task = threadPool.submit(totalTask);
        System.out.println(task.get());


        return PublicResult.success();
    }


}
