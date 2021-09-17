package com.tingyu.antimicrobial.monitor.controller;

import com.tingyu.antimicrobial.monitor.common.PublicResult;
import com.tingyu.antimicrobial.monitor.dto.OnlineSampleDTO;
import com.tingyu.antimicrobial.monitor.service.OnlineSampleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午8:58
 * @scription
 */

@RestController
@RequestMapping("sample/online")
public class OnlineSampleController {

    @Resource
    private OnlineSampleService onlineSampleService;

    @ApiOperation("sample sign in")
    @PostMapping("sign/in")
    public PublicResult signIn(@RequestBody List<OnlineSampleDTO> dtoList) {
        onlineSampleService.signIn(dtoList);
        return PublicResult.success();
    }


}
