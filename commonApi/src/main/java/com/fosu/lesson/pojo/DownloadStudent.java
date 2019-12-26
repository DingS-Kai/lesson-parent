package com.fosu.lesson.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 *基础数据类.这里的排序和excel里面的排序一致
 *
 * @author lhz
 * @date 2019/12/25
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(15)
public class DownloadStudent {

    @ExcelProperty({"课程表","星期"})
    String week;
    @ExcelProperty({"课程表","第一节"})
    String jc1;
    @ExcelProperty({"课程表","第二节"})
    String jc2;
    @ExcelProperty({"课程表","第三节"})
    String jc3;
    @ExcelProperty({"课程表","第四节"})
    String jc4;
    @ExcelProperty({"课程表","第五节"})
    String jc5;
    @ExcelProperty({"课程表","第六节"})
    String jc6;
    @ExcelProperty({"课程表","第七节"})
    String jc7;
}