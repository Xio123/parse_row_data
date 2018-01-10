package com.ai.parse_row_data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 21:11 2018/1/9
 * @Modified By:
 */
@Getter
@AllArgsConstructor
public enum TradeActionEnum implements BaseKVEnum {

    SELL(0, "卖出"),
    SHORT(1, "做空"),
    COVER(2, "填补做空");

    private int code;
    private String describe;
}

