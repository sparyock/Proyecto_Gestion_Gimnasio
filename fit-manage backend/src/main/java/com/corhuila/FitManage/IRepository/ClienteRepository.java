package com.corhuila.FitManage.IRepository;

import com.corhuila.FitManage.Dto.IClientreDto;
import com.corhuila.FitManage.Entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento(String documento);
    @Query(value = "SELECT * FROM clientes WHERE email = :email AND password = :password ",nativeQuery = true)
    Optional<IClientreDto> getValidar(String email, String password);
    boolean existsByEmail(String email);
}
