package com.ai.parse_row_data.service.impl;

import com.ai.parse_row_data.dao.entity.BaseLine;
import com.ai.parse_row_data.dao.entity.Plan;
import com.ai.parse_row_data.dao.entity.UserPortfolio;
import com.ai.parse_row_data.dao.entity.UserTrade;
import com.ai.parse_row_data.dao.repository.BaseLineRepository;
import com.ai.parse_row_data.dao.repository.PlanRepository;
import com.ai.parse_row_data.dao.repository.UserPortfolioRepository;
import com.ai.parse_row_data.dao.repository.UserTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:35 2018/1/10
 * @Modified By:
 */
@Component
public class Bean2DbServiceImpl{

    @Autowired
    private BaseLineRepository baseLineRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserPortfolioRepository userPortfolioRepository;

    @Autowired
    private UserTradeRepository userTradeRepository;

    public BaseLine saveBaseLine(BaseLine entity){
        return baseLineRepository.save(entity);
    }

    public List<BaseLine> saveBaseLines(List<BaseLine> entities){
        return baseLineRepository.save(entities);
    }

    public Plan savePlan(Plan entity){
        return planRepository.save(entity);
    }

    public List<Plan> savePlans(List<Plan> entities){
        return planRepository.save(entities);
    }

    public UserPortfolio saveUserPortfolio(UserPortfolio entity){
        return userPortfolioRepository.save(entity);
    }

    public List<UserPortfolio> saveUserPortfolios(List<UserPortfolio> entities){
        return userPortfolioRepository.save(entities);
    }

    public UserTrade saveUserTrade(UserTrade entity){
        return userTradeRepository.save(entity);
    }

    public List<UserTrade> saveUserTrades(List<UserTrade> entities){
        return userTradeRepository.save(entities);
    }


}
