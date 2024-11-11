package com.corhuila.FitManage.Service;

import com.corhuila.FitManage.Entity.Factura;
import com.corhuila.FitManage.IRepository.FacturaRepository;
import com.corhuila.FitManage.IService.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService implements IFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Factura> findByClienteId(Long clienteId) {
        return facturaRepository.findByClienteId(clienteId);
    }
}
