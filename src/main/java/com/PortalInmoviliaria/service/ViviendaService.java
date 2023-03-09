package com.PortalInmoviliaria.service;
import com.PortalInmoviliaria.entities.Vivienda;
import com.PortalInmoviliaria.repository.ViviendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ViviendaService {

    @Autowired
    ViviendaRepository viviendaRepository ;

    public List<Vivienda> listar(){
        return viviendaRepository.findAll();
    }
    public Optional<Vivienda>buscarPorId(int id){
        return viviendaRepository.findById(id);
    }

    public void guardar(Vivienda vivienda){
        viviendaRepository.save(vivienda);
    }

    public void borrar(int id){
        viviendaRepository.deleteById(id);
    }

    public boolean existe(int id){
        return viviendaRepository.existsById(id);
    }
}
