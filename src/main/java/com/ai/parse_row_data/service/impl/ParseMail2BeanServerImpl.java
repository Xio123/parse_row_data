package com.ai.parse_row_data.service.impl;

import com.ai.parse_row_data.dao.entity.BaseLine;
import com.ai.parse_row_data.dao.entity.Plan;
import com.ai.parse_row_data.dao.entity.UserPortfolio;
import com.ai.parse_row_data.dao.entity.UserTrade;
import com.ai.parse_row_data.util.StringUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:解析邮件内容的实现类
 * @Date:Created in 12:35 2018/1/10
 * @Modified By:
 */
@Component
@Getter
public class ParseMail2BeanServerImpl extends ParseMailContent {

    @Autowired
    private Bean2DbServiceImpl bean2DbService;

    private List<BaseLine> baseLines = new ArrayList<>();
    private List<Plan> plans = new ArrayList<>();
    private List<UserPortfolio> userPortfolios = new ArrayList<>();
    private List<UserTrade> userTrades = new ArrayList<>();
    private Map<String, String> userTradeAction = new HashMap<>();

    @Override
    public void parse2BaseLines(String s) {
        baseLines.clear();
        String[] baseStr = s.split("%");
        for (int i = 1; i < baseStr.length; i++) {
            baseLines.add(BaseLine.bulid(baseStr[i]));
        }
        bean2DbService.saveBaseLines(baseLines);
    }

    @Override
    public void parse2Plans(String s) {
        plans.clear();
        String time = s.split("note")[1].substring(0, 10);
        String[] base = s.split(time);
        for (int i = 1; i < base.length; i++) {
            plans.add(Plan.bulid(base[i], time));
        }
        bean2DbService.savePlans(plans);
    }

    @Override
    public void parse2UserPortfolios(List<String> str) {
        userPortfolios.clear();
        for (String item : str) {
            String type = item.split(",")[1].trim().split(" ")[0];
            String baseItem = item.split("num_positions")[1];
            baseItem = StringUtil.clearNumSpace(baseItem);
            String[] onItem = baseItem.split("%");
            BigDecimal totalGain = new BigDecimal(onItem[onItem.length - 1].split("=")[1].trim());
            for (int i = 0; i < onItem.length - 1; i++) {
                userPortfolios.add(UserPortfolio.UserPortfolio(onItem[i], totalGain, type));
            }
        }
        bean2DbService.saveUserPortfolios(userPortfolios);
    }

    @Override
    public void parse2UserTrades(List<String> str, String userAction) {
        parse2UserTradeAction(userAction);
        userTrades.clear();
        for (String item : str) {
            String typeTrade = item.split("------")[2];
            String type = item.split("------")[1].split(",")[1].trim();
            String[] typeTrades = StringUtil.splitStrByaA(typeTrade);
            for (String oneItem : typeTrades) {
                userTrades.add(UserTrade.bulid(oneItem, type, userTradeAction));
            }
        }
        bean2DbService.saveUserTrades(userTrades);
    }


    public void parse2UserTradeAction(String str) {
        userTradeAction.clear();
        String time = str.split("note")[1].substring(0, 10);
        String[] items = str.split("note")[1].split(time);
        for (String item : items) {
            if (!StringUtils.isBlank(item)) {
                String[] bar = item.split("\\s+");
                userTradeAction.put(bar[2], bar[3]);
            }
        }
    }

}
