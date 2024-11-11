package com.corhuila.FitManage.IRepository;

import com.corhuila.FitManage.Entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {

    List<Membresia> findByClienteId(Long clienteId);
}
