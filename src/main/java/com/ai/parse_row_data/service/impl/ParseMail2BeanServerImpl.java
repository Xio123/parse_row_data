package com.ai.parse_row_data.service.impl;

import com.ai.parse_row_data.dao.entity.BaseLine;
import com.ai.parse_row_data.dao.entity.Plan;
import com.ai.parse_row_data.dao.entity.UserPortfolio;
import com.ai.parse_row_data.dao.entity.UserTrade;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:解析邮件内容的实现类
 * @Date:Created in 12:35 2018/1/10
 * @Modified By:
 */
@Service
@Getter
public class ParseMail2BeanServerImpl extends ParseMailContent {

    private List<BaseLine> baseLines;
    private List<Plan> plans;
    private List<UserPortfolio> userPortfolios;
    private List<UserTrade> userTrades;

    @Override
    void parse2BaseLines(String s) {
        baseLines=null;
    }

    @Override
    void parse2Plans(String s) {
        plans=null;
    }

    @Override
    void parse2UserPortfolios(String s) {
        userPortfolios=null;
    }

    @Override
    void parse2UserTrades(String s) {
        userTrades=null;
    }


}
