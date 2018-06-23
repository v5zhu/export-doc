package com.zhuxl.exportdoc.util;

import com.zhuxl.exportdoc.entity.ReportContentRequest;
import com.zhuxl.exportdoc.entity.ReportExportWordRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author 2810010108@qq.com
 * @packageName: com.zhuxl.exportdoc.util
 * @className: WordGeneratorUtils
 * @description: 文档生成工具类
 * @createTime: 2018/6/13 9:54 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
public class WordGeneratorUtils {
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;


    private static class FreemarkerTemplate {
        public static final String POVERTY = "poverty";

    }

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(WordGeneratorUtils.class, "/freemarker/template");
        allTemplates = new HashMap();
        try {
            allTemplates.put(FreemarkerTemplate.POVERTY, configuration.getTemplate(FreemarkerTemplate.POVERTY + ".ftl"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private WordGeneratorUtils() {
        throw new AssertionError();
    }

    public static File createDoc(Map<String, String> dataMap) {
        try {
            String name = dataMap.get("title") + dataMap.get("date") + ".doc";
            File f = new File(name);
            Template t = allTemplates.get(dataMap.get("template"));
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
            return f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("生成word文档失败");
        }
    }

    public static Map<String, String> parseToMap(ReportExportWordRequest request) {
        Map<String, String> datas = new HashMap(32);
        //主标题
        datas.put("title", request.getTitle());
        datas.put("date", request.getDate());
        datas.put("watermark", request.getWatermark());
        datas.put("description1", request.getDescription1());
        datas.put("description2", request.getDescription2());


        //遍历设置报表
        List<ReportContentRequest> contents = request.getReports();
        datas.put("template", request.getType());
        for (ReportContentRequest c : contents) {
            if (c.getChildren() == null || c.getChildren().size() == 0) {
                //无子报表
                datas.put("title_" + c.getSerial(), c.getTitle());
                datas.put("base64_" + c.getSerial(), c.getBase64());
                datas.put("summary_" + c.getSerial(), c.getSummary());
            } else {
                //有多个子报表
                datas.put("title_" + c.getSerial(), c.getTitle());
                for (ReportContentRequest subc : c.getChildren()) {
                    datas.put("title_" + c.getSerial() + "_" + subc.getSerial(), subc.getTitle());
                    datas.put("base64_" + c.getSerial() + "_" + subc.getSerial(), subc.getBase64());
                    datas.put("summary_" + c.getSerial() + "_" + subc.getSerial(), subc.getSummary());
                }
            }
        }
        return datas;
    }
}
