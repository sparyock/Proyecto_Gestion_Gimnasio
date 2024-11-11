package com.corhuila.FitManage.Service;


import com.corhuila.FitManage.Dto.IClientreDto;
import com.corhuila.FitManage.Entity.Cliente;
import com.corhuila.FitManage.IRepository.ClienteRepository;
import com.corhuila.FitManage.IService.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("El email ya existe.");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> findByDocumento(String documento) {
        return clienteRepository.findByDocumento(documento);
    }
    @Override
    public Optional<IClientreDto> getValidar(String email, String password) {
        return clienteRepository.getValidar(email, password);
    }
}
