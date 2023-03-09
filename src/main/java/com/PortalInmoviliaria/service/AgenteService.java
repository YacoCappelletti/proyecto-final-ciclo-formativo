package com.PortalInmoviliaria.service;
import com.PortalInmoviliaria.entities.Agente;
import com.PortalInmoviliaria.repository.AgenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgenteService {
    @Autowired
    AgenteRepository agenteRepository ;

    public List<Agente> listar(){
        return agenteRepository.findAll();
    }
    public Optional<Agente> buscarPorId(int id){
        return agenteRepository.findById(id);
    }

    public void guardar(Agente agente){
        agenteRepository.save(agente);
    }

    public void borrar(int id){
        agenteRepository.deleteById(id);
    }

    public boolean existe(int id){
        return agenteRepository.existsById(id);
    }

}
