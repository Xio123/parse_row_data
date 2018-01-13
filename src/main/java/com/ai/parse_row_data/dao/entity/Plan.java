package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.SharesTypeEnum;
import com.ai.parse_row_data.constant.TradeActionEnum;
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
    private LocalDate newData;
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

    public static Plan bulid(String str,String time) {
        String[] baseStr = str.trim().split("\\s+");
        SharesTypeEnum sharesTypeEnum=SharesTypeEnum.valueOf(baseStr[0]);
        String name=baseStr[1];
        TradeActionEnum action=TradeActionEnum.valueOf(baseStr[2]);
        LocalDate data = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        BigDecimal rank=new BigDecimal(baseStr[7]);
        String note="";
        for(int i=8;i<baseStr.length;i++){
            note+=baseStr[i]+" ";
        }
        return Plan.builder().action(action).type(sharesTypeEnum).name(name).newData(data).rank(rank).note(note).build();

    }

}
