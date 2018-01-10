package com.ai.parse_row_data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 20:57 2018/1/9
 * @Modified By:
 */
@Getter
@AllArgsConstructor
public enum BaseLineMarketEnum implements BaseKVEnum{

    USA_DJIA(0,"道琼斯指数");

    private int code;
    private String describe;

}
