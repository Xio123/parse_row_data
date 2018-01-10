package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.SharesTypeEnum;
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
 * @Date:Created in 21:02 2018/1/9
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserPortfolio {

    @Id
    @GeneratedValue
    private Long id;
    private String userCode;
    private String name;
    private LocalDateTime oldData;
    private BigDecimal oldPrice;
    private BigDecimal oldRank;
    private LocalDateTime newData;
    private BigDecimal newPrice;
    private BigDecimal newRank;
    private Integer holdDay;
    private BigDecimal gain;
    @Enumerated(value = EnumType.ORDINAL)
    private SharesTypeEnum type;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;


}
