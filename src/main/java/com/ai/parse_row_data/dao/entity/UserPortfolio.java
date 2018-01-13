package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.SharesTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
    private LocalDate oldData;
    private BigDecimal oldPrice;
    private BigDecimal oldRank;
    private LocalDate newData;
    private BigDecimal newPrice;
    private BigDecimal newRank;
    private Integer holdDay;
    private BigDecimal gain;
    @Enumerated(value = EnumType.ORDINAL)
    private SharesTypeEnum type;
    private BigDecimal totalGain;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;

    public static UserPortfolio UserPortfolio(String oneItem, BigDecimal totalGain, String shareType) {
        String[] iteams = oneItem.split("\\s+");
        String name = iteams[0];
        LocalDate oldData = LocalDate.parse(iteams[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
        BigDecimal oldPrice = new BigDecimal(iteams[1]);
        BigDecimal oldRank = new BigDecimal(iteams[3]);
        LocalDate newData = LocalDate.parse(iteams[5], DateTimeFormatter.ofPattern("yyyyMMdd"));
        BigDecimal newPrice = new BigDecimal(iteams[4]);
        BigDecimal newRank = new BigDecimal(iteams[6]);
        Integer holdDay = Integer.valueOf(iteams[7]);
        BigDecimal gain = new BigDecimal(iteams[8]);
        SharesTypeEnum type = SharesTypeEnum.valueOf(shareType);
        return UserPortfolio.builder().name(name).oldData(oldData).oldPrice(oldPrice).oldRank(oldRank)
                .newData(newData).newPrice(newPrice).newRank(newRank)
                .holdDay(holdDay).gain(gain).type(type).totalGain(totalGain).build();
    }

}
