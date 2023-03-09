package com.PortalInmoviliaria.controller;

import com.PortalInmoviliaria.dto.AgenteDto;
import com.PortalInmoviliaria.dto.Mensaje;
import com.PortalInmoviliaria.entities.Agente;
import com.PortalInmoviliaria.entities.Vivienda;
import com.PortalInmoviliaria.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/agentes")
public class AgenteController {
    @Autowired
    AgenteService agenteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Agente>> listar(){
        try {


            List<Agente> agentes = agenteService.listar();
            return new ResponseEntity(agentes, HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Ocurrió un error en la busqueda de agentes"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <Agente> buscar(@PathVariable("id")int id){
        Agente agente;

        try{
            agente = (Agente) agenteService.buscarPorId(id).get();
            return new ResponseEntity(agente, HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return new ResponseEntity(new Mensaje("El agente buscado no existe"), HttpStatus.OK);

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crear( @RequestBody AgenteDto agenteDto ){

        List<Vivienda> viviendas = new ArrayList<Vivienda>();
        Agente agente = new Agente(agenteDto.getNombre(), agenteDto.getApellido(),agenteDto.getTelefono(), agenteDto.getEmail(), viviendas);

        try{
            agenteService.guardar(agente);
            return new ResponseEntity(new Mensaje("Agente creado"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar registrar al agente"), HttpStatus.OK);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id")int id, @RequestBody AgenteDto agenteDto){

        try {
            Agente agente = agenteService.buscarPorId(id).get();
            agente.setNombre(agenteDto.getNombre());
            agente.setApellido(agenteDto.getApellido());
            agente.setTelefono(agenteDto.getTelefono());
            agente.setEmail(agenteDto.getEmail());
            agenteService.guardar(agente);
            return new ResponseEntity(new Mensaje("Agente actualizado"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar registrar al agente"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id")int id){

        try {
            if (!agenteService.existe(id)) {
                return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

            }
            agenteService.borrar(id);
            return new ResponseEntity(new Mensaje("agente eliminado"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Hubo un error al intentar borrar al agente"), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
