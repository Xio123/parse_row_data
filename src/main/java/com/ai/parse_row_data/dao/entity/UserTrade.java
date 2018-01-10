package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.SharesTypeEnum;
import com.ai.parse_row_data.constant.TradeActionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 21:10 2018/1/9
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserTrade {

    @Id
    @GeneratedValue
    private Long id;
    private String userCode;
    private String name;
    @Enumerated(value = EnumType.ORDINAL)
    private TradeActionEnum action;
    @Enumerated(value = EnumType.ORDINAL)
    private SharesTypeEnum type;
    private LocalDateTime oldDate;
    private BigDecimal oldPrice;
    private LocalDateTime newDate;
    private BigDecimal newPrice;
    private BigDecimal gain;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;
}
