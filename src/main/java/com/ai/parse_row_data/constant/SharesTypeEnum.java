package com.ai.parse_row_data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 21:06 2018/1/9
 * @Modified By:
 */
@Getter
@AllArgsConstructor
public enum SharesTypeEnum implements BaseKVEnum {

    LONG1(0, "大型股，波动小，风险低"),
    LONG2(1, "小心股，波动大，收益高"),
    SHORT(2, "做空股");


    private int code;
    private String describe;
}
