package com.corhuila.FitManage.Service;

import com.corhuila.FitManage.Entity.Plan;
import com.corhuila.FitManage.IRepository.PlanRepository;
import com.corhuila.FitManage.IService.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }
}
