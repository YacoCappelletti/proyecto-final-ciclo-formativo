package com.PortalInmoviliaria.controller;


import com.PortalInmoviliaria.dto.AgenteDto;
import com.PortalInmoviliaria.dto.Mensaje;
import com.PortalInmoviliaria.dto.ViviendaDto;
import com.PortalInmoviliaria.entities.Agente;
import com.PortalInmoviliaria.entities.Vivienda;
import com.PortalInmoviliaria.service.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/viviendas")
public class ViviendaController {
    @Autowired
    ViviendaService viviendaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Vivienda>> listar(){
        try {


            List<Vivienda> viviendas = viviendaService.listar();
            return new ResponseEntity(viviendas, HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Ocurrió un error en la busqueda de viviendas"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <Vivienda> buscar(@PathVariable("id")int id){
        Vivienda vivienda;

        try{
            vivienda = (Vivienda) viviendaService.buscarPorId(id).get();
            return new ResponseEntity(vivienda, HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return new ResponseEntity(new Mensaje("La vivienda buscada no figura  en la base de datos"), HttpStatus.OK);

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody ViviendaDto viviendaDto){


        Vivienda vivienda = new Vivienda(viviendaDto.getPrecio(), viviendaDto.getTitulo(),viviendaDto.getUbicacion(),
                                viviendaDto.getAgente(), viviendaDto.getHabitaciones(), viviendaDto.getServicios(),
                                viviendaDto.getGarajes(), viviendaDto.getDescripcion(),viviendaDto.getClientes());

        try{
            viviendaService.guardar(vivienda);
            return new ResponseEntity(new Mensaje("Vivienda Creada"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar registrar la vivienda"), HttpStatus.OK);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id")int id, @RequestBody ViviendaDto viviendaDto){

        try {
            Vivienda vivienda = viviendaService.buscarPorId(id).get();
            vivienda.setPrecio(viviendaDto.getPrecio());
            vivienda.setTitulo(viviendaDto.getTitulo());
            vivienda.setUbicacion(viviendaDto.getUbicacion());
            vivienda.setAgente(viviendaDto.getAgente());
            vivienda.setHabitaciones(viviendaDto.getHabitaciones());
            vivienda.setServicios(viviendaDto.getServicios());
            vivienda.setGarajes(viviendaDto.getGarajes());
            vivienda.setDescripcion(viviendaDto.getDescripcion());
            vivienda.setClientes(viviendaDto.getClientes());
            viviendaService.guardar(vivienda);
            return new ResponseEntity(new Mensaje("Vivienda actualizada"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar actualizar la vivienda"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id")int id){

        try {
            if (!viviendaService.existe(id)) {
                return new ResponseEntity(new Mensaje("La vivienda no figura en la base de datos"), HttpStatus.NOT_FOUND);

            }
            viviendaService.borrar(id);
            return new ResponseEntity(new Mensaje("Vivienda eliminada de la base de datos"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Hubo un error al intentar borrar la vivienda"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
