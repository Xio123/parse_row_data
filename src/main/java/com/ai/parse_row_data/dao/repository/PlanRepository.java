package com.ai.parse_row_data.dao.repository;

import com.ai.parse_row_data.dao.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 11:52 2018/1/10
 * @Modified By:
 */
public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {
}
