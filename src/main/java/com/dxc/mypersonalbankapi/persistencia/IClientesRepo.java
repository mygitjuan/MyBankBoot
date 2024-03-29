package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;

import java.util.List;

public interface IClientesRepo {
    public List<Cliente> getAll() throws Exception;

    public Cliente getClientById(Integer id) throws Exception;

    public Cliente addClient(Cliente cliente) throws Exception;

    public boolean deleteClient(Cliente cliente) throws Exception;

    public Cliente updateClient(Cliente cliente) throws Exception;
    public String getdb_url();

}
