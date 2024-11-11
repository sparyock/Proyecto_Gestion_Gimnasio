package com.corhuila.FitManage.Service;

import com.corhuila.FitManage.Entity.Membresia;
import com.corhuila.FitManage.IRepository.MembresiaRepository;
import com.corhuila.FitManage.IService.IMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaService implements IMembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Override
    public List<Membresia> findAll() {
        return membresiaRepository.findAll();
    }

    @Override
    public Optional<Membresia> findById(Long id) {
        return membresiaRepository.findById(id);
    }

    @Override
    public Membresia save(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

    @Override
    public void deleteById(Long id) {
        membresiaRepository.deleteById(id);
    }

    @Override
    public List<Membresia> findByClienteId(Long clienteId) {
        return membresiaRepository.findByClienteId(clienteId);
    }
}
