package com.tingyu.kjyjcxt.controller;

import com.tingyu.kjyjcxt.common.PublicResult;
import com.tingyu.kjyjcxt.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2021/7/16 18:58
 * @Version kjyjcxt
 */

@RestController
@RequestMapping("file")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public PublicResult upload(MultipartFile file) throws Exception {
        Map<String, Object> map = fileService.read(file);
        return PublicResult.success(map);
    }

}
