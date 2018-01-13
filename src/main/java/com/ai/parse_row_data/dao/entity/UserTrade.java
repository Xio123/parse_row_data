package com.ai.parse_row_data.dao.entity;

import com.ai.parse_row_data.constant.SharesTypeEnum;
import com.ai.parse_row_data.constant.TradeActionEnum;
import com.ai.parse_row_data.util.StringUtil;
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
    private LocalDate oldDate;
    private BigDecimal oldPrice;
    private LocalDate newDate;
    private BigDecimal newPrice;
    private BigDecimal gain;
    private String note;

    private Byte isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deletedTime;

    public static UserTrade bulid(String oneItem, String shareType, Map<String, String> userAction) {
//        //------ Details for closed positions on 20170330 , LONG1 ------ATVI    48.68   20170322        50.19   20170330        2.9%    6       Activision Blizzard,    COMPUTER SOFTWARE & SERVICES - MultCWEI    130.25  20170320        131.79  20170330        1.0%    8       Clayton Williams Ene    ENERGY - Independent Oil & Gas
//        0 1 2 3 4 5 6 7
//        //ATVI    48.68   20170322        50.19   20170330        2.9%    6       Activision Blizzard,    COMPUTER SOFTWARE & SERVICES - Mult
        String[] items = oneItem.split("\\s+");
        String name = items[0];
        TradeActionEnum action=TradeActionEnum.valueOf(userAction.get(name));
        SharesTypeEnum type = SharesTypeEnum.valueOf(shareType);
        LocalDate oldDate = LocalDate.parse(items[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
        BigDecimal oldPrice = new BigDecimal(items[1]);
        LocalDate newDate = LocalDate.parse(items[4], DateTimeFormatter.ofPattern("yyyyMMdd"));
        BigDecimal newPrice = new BigDecimal(items[3]);
        BigDecimal gain = new BigDecimal(items[3].split("%")[0]);
        String note = "";
        for (int i = 7; i < items.length; i++) {
            note += (items[i] + " ");
        }
        return UserTrade.builder()
                .name(name).type(type)
                .oldDate(oldDate).oldPrice(oldPrice).action(action)
                .newDate(newDate).newPrice(newPrice).gain(gain).note(note)
                .build();

    }


}
