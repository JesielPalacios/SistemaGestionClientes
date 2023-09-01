/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jesielvirtual.sistemagestionclientes.dao;

import com.jesielvirtual.sistemagestionclientes.models.Cliente;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesielpalacios
 */
public class ClienteDao {
    
    public Connection conectar() {
        String baseDeDatos = "gestion_clientes";
        String usuario = "root";
        String password = "";
        String host = "localhost";
        String puerto = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String conexionUrl = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
        
        Connection conexion = null;
        
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(conexionUrl, usuario, password);
            // } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
    }

    public void agregar(Cliente cliente) {
        try {
            // String sql = "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `correo`) VALUES ('', 'Pepito', 'Perez', '1234567890', 'asdasdasd@gmail.com');";
            String sql = "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `correo`) VALUES (NULL, '" +
                    cliente.getNombre() + "', '" +
                    cliente.getApellido()+ "', '" +
                    cliente.getTelefono()+ "', '" +
                    cliente.getCorreo()+ "');";
            Statement statement = conectar().createStatement();
            statement.execute(sql);
        // } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizar(Cliente cliente) {
        try {
            String sql = "UPDATE `clientes` SET `nombre` = '" + cliente.getNombre() +
                    "', `apellido` = '" + cliente.getApellido() +
                    "', `telefono` = '" + cliente.getTelefono() +
                    "', `correo` = '" + cliente.getCorreo() +
                    "' WHERE `clientes`.`id` = " + cliente.getId() + ";";
            Statement statement = conectar().createStatement();
            statement.execute(sql);
        // } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Cliente> listar() {
        List<Cliente> listado = new ArrayList<>();

        try {
            Connection conexion = conectar();

            String sql = "SELECT * FROM `clientes`";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            
            while(resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getString("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setCorreo(resultado.getString("correo"));
                
                listado.add(cliente);
            }
        // } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return listado;
    }
        
    public void eliminar(String id) {
        System.out.println("id: " + id);
        
        try {
            Connection conexion = conectar();
            
            String sql = "DELETE FROM `gestion_clientes`.`clientes` WHERE `clientes`.`id` = " + id;
            Statement statement = conexion.createStatement();
            statement.execute(sql);
        // } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar(Cliente cliente) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        if (StringUtils.isEmptyOrWhitespaceOnly(cliente.getId())) {
            agregar(cliente);
        } else {
            actualizar(cliente);
        }
    }
}
