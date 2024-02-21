package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import com.dxc.mypersonalbankapi.properties.PropertyValues;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClienteDBRepository implements IClientesRepo {
    private String db_url;

// modifico para commit
    @Override
    public List<Cliente> getAll() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente c");
        ) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                final String tipo_personal = "personal";
                String dType = rs.getString("dtype").toLowerCase();

                if (dType.contains(tipo_personal)) {

                    clientes.add(
                        new Personal(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("direccion"),
                                rs.getDate("alta").toLocalDate(),
                                rs.getBoolean("activo"),
                                rs.getBoolean("moroso"),
                                rs.getString("dni")
                        )
                    );
                } else {
                    String[] row = new String[1];
                    row[0] = rs.getString("unidades_de_negocio");


                    clientes.add(
                        new Empresa(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("direccion"),
                                rs.getDate("alta").toLocalDate(),
                                rs.getBoolean("activo"),
                                rs.getBoolean("moroso"),
                                rs.getString("cif"),
                                row
                        )

                    );
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return clientes;
    }

    @Override
    public Cliente getClientById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cliente addClient(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setBoolean(2, cliente.isActivo());
            stmt.setString(3, cliente.getAlta().toString());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getEmail());
            stmt.setBoolean(6, cliente.isMoroso());
            stmt.setString(7, cliente.getNombre());

            if (cliente.getClass().getName().indexOf("Personal") >= 0) {
                stmt.setString(1, "Personal");
                stmt.setString(8, null);
                stmt.setString(9, null);
                stmt.setString(10, ((Personal) cliente).getDni());
            } else {
                stmt.setString(1, "Empresa");
                stmt.setString(8, ((Empresa) cliente).getCif());
                String unidades = String.join(",", ((Empresa) cliente).getUnidadesNegocio());
                stmt.setString(9, unidades);
                stmt.setString(10, null);
            }


            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                cliente.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Usuario creado erroneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cliente;
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public String getdb_url() {
        /*return null;--> ESto devuelve error en el Test*/
        return this.db_url;
    }
}
