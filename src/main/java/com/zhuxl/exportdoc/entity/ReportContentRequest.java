package com.zhuxl.exportdoc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * @author 2810010108@qq.com
 * @package com.zhuxl.exportdoc.entity
 * @createTime: 2018/6/13 9:54 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
@Data
@ApiModel("单个图表请求对象")
public class ReportContentRequest {

    @ApiModelProperty(value = "报表中排列序号", name = "serial")
    private Integer serial;

    @ApiModelProperty(value = "单个图表标题", name = "title")
    private String title;

    @ApiModelProperty(value = "单个图表base64编码值", name = "base64")
    private String base64;

    @ApiModelProperty(value = "单个图表内容总结", name = "summary")
    private String summary;

    @ApiModelProperty(value = "该标题下存在多个报表", name = "children")
    private List<ReportContentRequest> children;
}
