package com.tingyu.antimicrobial.monitor.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2021/7/16 19:00
 * @Version kjyjcxt
 */
public interface FileService {
    Map<String,Object> read(MultipartFile file) throws  Exception;
}
