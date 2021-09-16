package com.tingyu.antimicrobial.monitor.test;

import cn.hutool.poi.excel.ExcelFileUtil;
import com.tingyu.antimicrobial.monitor.AntimicrobialMonitoringSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author essionshy
 * @CreatDate 2021/9/12 上午10:52
 * @Description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AntimicrobialMonitoringSystemApplication.class})
public class HutoolTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test(MultipartFile file) throws IOException {

        boolean isXls = ExcelFileUtil.isXls(file.getInputStream());




        System.out.println("ceshi ");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }

}
