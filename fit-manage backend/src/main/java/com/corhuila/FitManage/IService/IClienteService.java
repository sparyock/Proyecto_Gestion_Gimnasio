package com.corhuila.FitManage.IService;

import com.corhuila.FitManage.Dto.IClientreDto;
import com.corhuila.FitManage.Entity.Cliente;
import java.util.List;
import java.util.Optional;


public interface IClienteService {

    List<Cliente> findAll(); // Obtener todos los clientes

    Optional<Cliente> findById(Long id); // Buscar cliente por ID

    Cliente save(Cliente cliente); // Guardar o actualizar un cliente

    void deleteById(Long id); // Eliminar cliente por ID

    Optional<Cliente> findByDocumento(String documento); // Buscar cliente por documento

    Optional<IClientreDto> getValidar(String email, String password); // Validar cliente por email y password
}
