package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.modelos.clientes.StatusMessage;
import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import com.dxc.mypersonalbankapi.persistencia.ClienteRepositoryJPA;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
@Tag(name = "My Bank API", description = "My Bank API management APIs")
public class ClienteControllerBoot {
    private static final Logger logger = LoggerFactory.getLogger(ClienteControllerBoot.class);

    @Autowired
    private ClienteRepositoryJPA repo;

    @PostMapping(value = "/personal", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> save(@RequestBody Personal newCliente) throws Exception {
        logger.info("newCliente:" + newCliente);
        newCliente.setId(null);

      if (newCliente.validar()) {
          return new ResponseEntity<>((Personal) repo.addClient(newCliente), HttpStatus.CREATED);
      }
      else {
          return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Cliente no válido"), HttpStatus.BAD_REQUEST);
      }
    }

    @PostMapping(value = "/empresa", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object > saveEmpresa(@RequestBody Empresa newCliente) throws Exception {
        logger.info("newCliente:" + newCliente);
        newCliente.setId(null);
         if (newCliente.validar()) {
             return new ResponseEntity<>((Empresa) repo.addClient(newCliente), HttpStatus.CREATED);
         } else {
          return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Cliente Empresa no válido"), HttpStatus.BAD_REQUEST);
      }

    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(repo.getAll(), HttpStatus.OK);
    }


}
