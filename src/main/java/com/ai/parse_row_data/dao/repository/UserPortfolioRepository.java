package com.ai.parse_row_data.dao.repository;

import com.ai.parse_row_data.dao.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 11:53 2018/1/10
 * @Modified By:
 */
public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long>, JpaSpecificationExecutor<UserPortfolio> {
}
