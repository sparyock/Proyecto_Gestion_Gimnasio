package com.corhuila.FitManage.Controller;

import com.corhuila.FitManage.Entity.Plan;
import com.corhuila.FitManage.IService.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @GetMapping
    public List<Plan> getAllPlanes() {
        return planService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Optional<Plan> plan = planService.findById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan savedPlan = planService.save(plan);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
        if (!planService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        plan.setId(id);
        Plan updatedPlan = planService.save(plan);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        if (!planService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        planService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
