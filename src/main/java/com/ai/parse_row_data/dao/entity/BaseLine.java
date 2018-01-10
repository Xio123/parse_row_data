package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.BaseLineMarketEnum;
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
 * @Date:Created in 20:53 2018/1/9
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BaseLine {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime time;
    private BigDecimal baseMarket;
    private BigDecimal aiMarket;
    @Enumerated(value = EnumType.ORDINAL)
    private BaseLineMarketEnum type;
    private BigDecimal gain;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;


}
