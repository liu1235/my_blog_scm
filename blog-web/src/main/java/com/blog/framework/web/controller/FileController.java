package com.blog.framework.web.controller;

import com.blog.framework.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-12
 **/
@Slf4j
@Api(tags = "文件管理")
@RestController
public class FileController {

    @Value("${file.uploadPath}")
    private String uploadPath;

    /**
     * 上传文件
     *
     * @return ResultData
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload")
    public ResultData<String> upload(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultData.createSelectResult("文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("---上传的文件名为 {}-------:", fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传路径
        fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + suffixName;
        File dest = new File(uploadPath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            log.error("上传文件异常", e);
        }
        return ResultData.createSelectResult(fileName);
    }
}
