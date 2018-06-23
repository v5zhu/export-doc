package com.zhuxl.exportdoc.service.impl;

import com.zhuxl.exportdoc.entity.ReportExportWordRequest;
import com.zhuxl.exportdoc.service.ReportService;
import com.zhuxl.exportdoc.util.WordGeneratorUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * <p>
 *
 * @packageName: com.zhuxl.exportdoc.service.impl
 * @className:
 * @description:
 * @author: 2810010108@qq.com
 * @createTime: 2018/6/23 14:16 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public File exportWord(ReportExportWordRequest exportWordRequest) {

        //解析参数
        Map<String, String> datas = WordGeneratorUtils.parseToMap(exportWordRequest);

        //导出
        File word = WordGeneratorUtils.createDoc(datas);
        return word;
    }
}
