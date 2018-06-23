package com.zhuxl.exportdoc.service;

import com.zhuxl.exportdoc.entity.ReportExportWordRequest;

import java.io.File;

/**
 * <p>
 *
 * @packageName: com.zhuxl.exportdoc.service.impl
 * @className:
 * @description:
 * @author: 2810010108@qq.com
 * @createTime: 2018/6/23 14:15 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
public interface ReportService {

    File exportWord(ReportExportWordRequest exportWordRequest);
}
