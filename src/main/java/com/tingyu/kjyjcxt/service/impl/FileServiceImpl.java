package com.tingyu.kjyjcxt.service.impl;

import com.tingyu.kjyjcxt.service.ExcelReader;
import com.tingyu.kjyjcxt.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2021/7/16 19:00
 * @Version kjyjcxt
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    /**
     * 读取EXCEL文件信息并返回数据列表
     * @param file
     * @return
     */
    @Override
    public Map<String, Object> read(MultipartFile file) throws Exception {
        ExcelReader reader = new ExcelReader(file);
        reader.read();
        Map<String, Object> map = reader.getMap();
        return map;
    }
}
