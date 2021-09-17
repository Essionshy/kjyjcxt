package com.tingyu.antimicrobial.monitor.controller;

import com.tingyu.antimicrobial.monitor.common.PublicResult;
import com.tingyu.antimicrobial.monitor.dto.OfflineSampleDTO;
import com.tingyu.antimicrobial.monitor.service.OfflineSampleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author essionshy
 * @CreatDate 2021/9/17 下午10:02
 * @Description
 */
@RestController
@RequestMapping("sample/offline")
public class OfflineSampleController {
    @Resource
    private OfflineSampleService offlineSampleService;

    @ApiOperation("sample import save")
    @PostMapping("import/save")
    public PublicResult importSave(@RequestBody List<OfflineSampleDTO> dtoList) {
        offlineSampleService.importSave(dtoList);
        return PublicResult.success();
    }


}
