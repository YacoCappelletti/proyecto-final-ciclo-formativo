package com.PortalInmoviliaria.service;
import com.PortalInmoviliaria.entities.Cliente;
import com.PortalInmoviliaria.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository ;

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    public Optional<Cliente> buscarPorId(int id){
        return clienteRepository.findById(id);
    }

    public void guardar(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void borrar(int id){
        clienteRepository.deleteById(id);
    }

    public boolean existe(int id){
        return clienteRepository.existsById(id);
    }
}
