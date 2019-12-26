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
public class DownloadAll {

    @ExcelProperty({"课程表","班级/星期节次"})
    String clazz;
    @ExcelProperty({"课程表","一（1）节"})
    String jc11;
    @ExcelProperty({"课程表","一（2）节"})
    String jc12;
    @ExcelProperty({"课程表","一（3）节"})
    String jc13;
    @ExcelProperty({"课程表","一（4）节"})
    String jc14;
    @ExcelProperty({"课程表","一（5）节"})
    String jc15;
    @ExcelProperty({"课程表","一（6）节"})
    String jc16;
    @ExcelProperty({"课程表","一（7）节"})
    String jc17;

    @ExcelProperty({"课程表","二（1）节"})
    String jc21;
    @ExcelProperty({"课程表","二（2）节"})
    String jc22;
    @ExcelProperty({"课程表","二（3）节"})
    String jc23;
    @ExcelProperty({"课程表","二（4）节"})
    String jc24;
    @ExcelProperty({"课程表","二（5）节"})
    String jc25;
    @ExcelProperty({"课程表","二（6）节"})
    String jc26;
    @ExcelProperty({"课程表","二（7）节"})
    String jc27;

    @ExcelProperty({"课程表","三（1）节"})
    String jc31;
    @ExcelProperty({"课程表","三（2）节"})
    String jc32;
    @ExcelProperty({"课程表","三（3）节"})
    String jc33;
    @ExcelProperty({"课程表","三（4）节"})
    String jc34;
    @ExcelProperty({"课程表","三（5）节"})
    String jc35;
    @ExcelProperty({"课程表","三（6）节"})
    String jc36;
    @ExcelProperty({"课程表","三（7）节"})
    String jc37;

    @ExcelProperty({"课程表","四（1）节"})
    String jc41;
    @ExcelProperty({"课程表","四（2）节"})
    String jc42;
    @ExcelProperty({"课程表","四（3）节"})
    String jc43;
    @ExcelProperty({"课程表","四（4）节"})
    String jc44;
    @ExcelProperty({"课程表","四（5）节"})
    String jc45;
    @ExcelProperty({"课程表","四（6）节"})
    String jc46;
    @ExcelProperty({"课程表","四（7）节"})
    String jc47;

    @ExcelProperty({"课程表","五（1）节"})
    String jc51;
    @ExcelProperty({"课程表","五（2）节"})
    String jc52;
    @ExcelProperty({"课程表","五（3）节"})
    String jc53;
    @ExcelProperty({"课程表","五（4）节"})
    String jc54;
    @ExcelProperty({"课程表","五（5）节"})
    String jc55;
    @ExcelProperty({"课程表","五（6）节"})
    String jc56;
    @ExcelProperty({"课程表","五（7）节"})
    String jc57;


}