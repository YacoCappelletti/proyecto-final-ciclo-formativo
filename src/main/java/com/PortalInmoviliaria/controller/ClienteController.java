package com.PortalInmoviliaria.controller;
import com.PortalInmoviliaria.dto.ClienteDto;
import com.PortalInmoviliaria.dto.Mensaje;
import com.PortalInmoviliaria.entities.Cliente;
import com.PortalInmoviliaria.entities.Vivienda;
import com.PortalInmoviliaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar(){
        try {

            List<Cliente> clientes = clienteService.listar();
            return new ResponseEntity(clientes, HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Ocurrió un error en la busqueda de clientes"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity <Cliente> buscar(@PathVariable("id")int id){
        Cliente cliente;

        try{
            cliente = (Cliente) clienteService.buscarPorId(id).get();
            return new ResponseEntity(cliente, HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return new ResponseEntity(new Mensaje("El cliente buscado no existe"), HttpStatus.OK);

    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear( @RequestBody ClienteDto clienteDto ){

        List<Vivienda> viviendas = new ArrayList<Vivienda>();
        Cliente cliente = new Cliente(clienteDto.getNombre(), clienteDto.getApellido(),clienteDto.getTelefono(), clienteDto.getEmail(), viviendas);

        try{
            clienteService.guardar(cliente);
            return new ResponseEntity(new Mensaje("Cliente creado correctamente"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar registrar al cliente"), HttpStatus.OK);

    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id")int id, @RequestBody ClienteDto clienteDto){

        try {
            Cliente cliente = clienteService.buscarPorId(id).get();
            cliente.setNombre(clienteDto.getNombre());
            cliente.setApellido(clienteDto.getApellido());
            cliente.setTelefono(clienteDto.getTelefono());
            cliente.setEmail(clienteDto.getEmail());
            clienteService.guardar(cliente);
            return new ResponseEntity(new Mensaje("Cliente actualizado"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(new Mensaje("Hubo un error al intentar registrar al cliente"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id")int id){

        try {
            if (!clienteService.existe(id)) {
                return new ResponseEntity(new Mensaje("El cliente no existe"), HttpStatus.NOT_FOUND);

            }
            clienteService.borrar(id);
            return new ResponseEntity(new Mensaje("Cliente eliminado"), HttpStatus.OK);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity(new Mensaje("Hubo un error al intentar borrar al cliente"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
