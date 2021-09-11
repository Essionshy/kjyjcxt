package com.tingyu.antimicrobial.monitor.controller;

import com.tingyu.antimicrobial.monitor.dto.ReportData;
import com.tingyu.antimicrobial.monitor.common.PublicResult;
import com.tingyu.antimicrobial.monitor.feign.ReportFeignService;
import com.tingyu.antimicrobial.monitor.feign.ReportConfigReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2021/7/7 22:06
 * @Version kjyjcxt
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${file.limit}")
    private int limit;

    @Resource
    private ReportFeignService reportFeignService;


    @GetMapping("get/file/limit")
    public PublicResult getFileLimit() {

        return PublicResult.buildAddSuccess(limit);
    }


    @GetMapping("list")
    public List<ReportData> list() {
        ReportConfigReq req = new ReportConfigReq();
        req.setIsLeaf(true);
        req.setOrganizationCode("100001");

        List<ReportData> reportDataList = new ArrayList<>();
        Map<String, Object> map = reportFeignService.listConfig();
        System.out.println(map.get("code"));
        Object msg = map.get("msg");
        List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
        Iterator<Map<String, Object>> iterator = data.iterator();
        while (iterator.hasNext()) {
            ReportData reportData = new ReportData();
            Map<String, Object> next = iterator.next();
            reportData.setCode((String) next.get("code"));
            reportData.setName((String) next.get("name"));
            reportDataList.add(reportData);
        }
        return reportDataList;
    }


    @GetMapping("get")
    public ReportData get() {
        ReportConfigReq req = new ReportConfigReq();
        req.setIsLeaf(true);
        req.setOrganizationCode("100001");
        ReportData reportData = reportFeignService.listConfig(req);
        return reportData;
    }


    @PostMapping("save")
    public PublicResult save(@RequestBody List<ReportData> data) {
        log.info("param data:{}", data);


        return PublicResult.success();
    }

}
