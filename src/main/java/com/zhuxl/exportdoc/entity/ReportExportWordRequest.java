package com.zhuxl.exportdoc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * @author 2810010108@qq.com
 * @packageName: com.zhuxl.exportdoc.entity
 * @createTime: 2018/6/13 9:54 with idea 2018.1
 * </p>
 * version: V1.0.0
 */

@Data
@ApiModel(value = "贫困人群报表导出请求对象")
public class ReportExportWordRequest {

    @ApiModelProperty(value = "区域级别", name = "unitLevel")
    private Integer unitLevel;

    @ApiModelProperty(value = "区域编码", name = "unitCode")
    private String unitCode;

    @ApiModelProperty(value = "报表类型", name = "type", notes = "poverty:贫困人群报告;disable:残疾人群报告;poverty_disable:贫困且残疾人群报告")
    private String type;

    @ApiModelProperty(value = "报表标题", name = "title")
    private String title;

    @ApiModelProperty(value = "报告水印", name = "watermark")
    private String watermark;

    @ApiModelProperty(value = "报表生成日期", name = "date")
    private String date;

    @ApiModelProperty(value = "该区域报表描述第一段", name = "description1")
    private String description1;

    @ApiModelProperty(value = "该区域报表描述第二段", name = "description2")
    private String description2;

    @ApiModelProperty(value = "报表中每个图表的内容列表", name = "reports")
    private List<ReportContentRequest> reports;
}

