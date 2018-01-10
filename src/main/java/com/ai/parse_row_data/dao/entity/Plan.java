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
 * @Date:Created in 21:17 2018/1/9
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Plan {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime newData;
    private String name;
    @Enumerated(value = EnumType.ORDINAL)
    private SharesTypeEnum type;
    @Enumerated(value = EnumType.ORDINAL)
    private TradeActionEnum action;
    private BigDecimal rank;
    private String note;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;

}
