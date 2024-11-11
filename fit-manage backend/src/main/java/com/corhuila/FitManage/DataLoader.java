package com.corhuila.FitManage;

import com.corhuila.FitManage.Entity.Plan;
import com.corhuila.FitManage.IRepository.PlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class DataLoader {

    @Autowired
    private PlanRepository planRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            if (planRepository.count() == 0) {
                planRepository.save(new Plan("Plan Black", 59900.0, "Entrena en cualquiera de nuestras sedes en América Latina", "Mensual"));
                planRepository.save(new Plan("Plan Fit", 79900.0, "Entrena cuando quieras en tu gimnasio de elección", "Mensual"));
                planRepository.save(new Plan("Plan Smart", 99900.0, "Entrena cuando quieras en tu sede de elección", "Mensual"));
            }
        };
    }
}
