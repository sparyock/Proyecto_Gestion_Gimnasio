package com.corhuila.FitManage.IRepository;

import com.corhuila.FitManage.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
