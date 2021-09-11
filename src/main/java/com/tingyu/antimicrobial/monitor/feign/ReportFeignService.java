package com.tingyu.antimicrobial.monitor.feign;

import com.tingyu.antimicrobial.monitor.dto.ReportData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2021/7/7 22:03
 * @Version kjyjcxt
 */

@FeignClient(value = "REPORT-SERVICE")
public interface ReportFeignService {

    @GetMapping("/report/v1/configure/list")
    Map<String, Object> listConfig();

    @GetMapping("/report/v1/configure/single/list")
    ReportData listConfig(@RequestParam ReportConfigReq req);





}
