package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.modelos.clientes.StatusMessage;
import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import com.dxc.mypersonalbankapi.persistencia.ClienteRepositoryJPA;
import io.swagger.v3.oas.annotations.Parameter;
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
import javax.validation.constraints.Min;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
          return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Cliente Personal no válido. El formato es 123456789X"), HttpStatus.BAD_REQUEST);
      }
    }

    @PostMapping(value = "/empresa", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object > saveEmpresa(@RequestBody Empresa newCliente) throws Exception {
        logger.info("newCliente:" + newCliente);
        newCliente.setId(null);
         if (newCliente.validar()) {
             return new ResponseEntity<>((Empresa) repo.addClient(newCliente), HttpStatus.CREATED);
         } else {
          return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Cliente Empresa no válido. El formato es B31313133"), HttpStatus.BAD_REQUEST);
      }

    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getAll() {
        List<Cliente> listCliente = repo.getAll();
        if (listCliente == null) {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Cliente NO ENCONTRADO"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>( repo.getAll(), HttpStatus.OK);
        }

    }

    @Operation(summary = "Get a client personal by id", description = "Returns a personal client as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The client was not found")
    })
    @RequestMapping(value = "/{cid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getOne(
            @Parameter(name = "id", description = "Client id", example = "1", required = true)
            @PathVariable("cid")
            @Min(1) Integer cid
    ) throws Exception {
        Cliente unCliente = repo.getClientById(cid);
        if (unCliente == null) {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Cliente NO ENCONTRADO"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(repo.getClientById(cid), HttpStatus.OK);
        }



    }
    @PutMapping(value = "/{cid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> update(@PathVariable("cid") @Min(1) Integer id, @RequestBody Cliente cli) throws Exception {
        if (id == cli.getId()) {
            return new ResponseEntity<>(repo.updateClient(cli), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y cli.id deben cohincidir"), HttpStatus.PRECONDITION_FAILED);
        }
    }

}
