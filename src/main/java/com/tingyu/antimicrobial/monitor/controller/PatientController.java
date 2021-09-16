package com.tingyu.antimicrobial.monitor.controller;

import com.tingyu.antimicrobial.monitor.dto.MemberDTO;
import com.tingyu.antimicrobial.monitor.service.MemberService;
import com.tingyu.antimicrobial.monitor.task.TotalTask;
import com.tingyu.antimicrobial.monitor.common.PublicResult;
import io.swagger.annotations.ApiOperation;
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
public class PatientController {

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

    @ApiOperation("query patient info by id")
    @GetMapping("get/{id}")
    public PublicResult get(@PathVariable("id") String id) throws Exception {
        ForkJoinPool threadPool=new ForkJoinPool();
        TotalTask totalTask = new TotalTask(1, 100);
        ForkJoinTask<Integer> task = threadPool.submit(totalTask);
        System.out.println(task.get());
        return PublicResult.success();
    }


}
