package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.BaseLineMarketEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDate time;
    private BigDecimal baseMarket;
    private BigDecimal aiMarket;
    private String type;
    private BigDecimal gain;

    private Byte isDeleted;
    private LocalDate createdTime;
    private LocalDate updatedTime;
    private LocalDate deletedTime;

    public static BaseLine bulid(String str) {
        String[] baseStr = str.split("\\s+");
        LocalDate time = LocalDate.parse(baseStr[0].trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        BigDecimal baseMarket = new BigDecimal(baseStr[7].trim());
        BigDecimal aiMarket = new BigDecimal(baseStr[8].trim());
        BigDecimal gain = new BigDecimal(baseStr[9].trim());
        return BaseLine.builder()
                .baseMarket(baseMarket).time(time).aiMarket(aiMarket).gain(gain).type(BaseLineMarketEnum.USA_DJIA.name()).isDeleted((byte)0)
                .build();
    }
}
