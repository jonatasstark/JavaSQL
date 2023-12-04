package com.javasql;

import java.sql.*;

public class CadastroUsuarioPedido {
    public static void main(String[] args) {
        //Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        // Conexão com o banco de dados
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela usuarios
            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id_usuario INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "email VARCHAR(100) NOT NULL" +
                    ")";
            
            Statement stmtUsuarios = conn.createStatement();
            stmtUsuarios.executeUpdate(sqlUsuarios);
            System.out.println("Tabela 'usuarios' criada com sucesso!");

            // Criação da tabela pedido
            String sqlPedido = "CREATE TABLE IF NOT EXISTS pedido (" +
                    "id_pedido INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_usuario INT," +
                    "produto VARCHAR(100) NOT NULL," +
                    "quantidade INT NOT NULL," +
                    "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)" +
                    ")";
            
            Statement stmtPedido = conn.createStatement();
            stmtPedido.executeUpdate(sqlPedido);
            System.out.println("Tabela 'pedido' criada com sucesso!");

            //inserir dados na tabela usuarios
            String nome = "Gabriel Lucas";
            String email = "gabriel@gmail.com";
            String sqlInsertUsuarios = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            
            PreparedStatement stmtInsertUsuarios = conn.prepareStatement(sqlInsertUsuarios);
            stmtInsertUsuarios.setString(1, nome);
            stmtInsertUsuarios.setString(2, email);
            stmtInsertUsuarios.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'usuarios'!");

            //inserir dados na tabela pedido
            String produto = "Teste";
            int quantidade = 5;
            String sqlInsertPedido = "INSERT INTO pedido (id_usuario, produto, quantidade) VALUES (?, ?, ?)";
            
            PreparedStatement stmtInsertPedido = conn.prepareStatement(sqlInsertPedido);
            stmtInsertPedido.setInt(1, 1); // id do usuário
            stmtInsertPedido.setString(2, produto);
            stmtInsertPedido.setInt(3, quantidade);
            stmtInsertPedido.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pedido'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}