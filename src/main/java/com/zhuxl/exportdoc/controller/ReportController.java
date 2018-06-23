package com.zhuxl.exportdoc.controller;

import com.zhuxl.exportdoc.entity.ReportExportWordRequest;
import com.zhuxl.exportdoc.service.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

/**
 * <p>
 *
 * @packageName: com.zhuxl.exportdoc.controller
 * @className:
 * @description:
 * @author: 2810010108@qq.com
 * @createTime: 2018/6/23 14:18 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @ApiOperation(value = "贫困人群综合分析报告导出word文档", notes = "贫困人群综合分析报告导出word文档")
    @PostMapping("/poverty_export_word.ajax")
    public void povertyExportWord(HttpServletRequest request, HttpServletResponse response,
                                  @Valid @RequestBody ReportExportWordRequest exportWordRequest) {

        File file = reportService.exportWord(exportWordRequest);

        InputStream fin = null;
        OutputStream out = null;
        try {
            // 调用工具类WordGeneratorUtils的createDoc方法生成Word文档
            fin = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件
            // 设置文件名编码解决文件名乱码问题
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes(), "iso-8859-1"));

            out = response.getOutputStream();
            byte[] buffer = new byte[512];
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("导出失败", e);
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (out != null) {
                    out.close();
                }
                if (file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                throw new RuntimeException("导出失败", e);
            }
        }

    }

}
