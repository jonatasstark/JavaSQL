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
            String nome = "Eduardo";
            String email = "eduardo@example.com";
            String sqlInsertUsuarios = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            
            PreparedStatement stmtInsertUsuarios = conn.prepareStatement(sqlInsertUsuarios);
            stmtInsertUsuarios.setString(1, nome);
            stmtInsertUsuarios.setString(2, email);
            stmtInsertUsuarios.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'usuarios'!");

            String nome2 = "Laura";
            String email2 = "laura@example.com";
            String sqlInsertUsuarios2 = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            
            PreparedStatement stmtInsertUsuarios2 = conn.prepareStatement(sqlInsertUsuarios2);
            stmtInsertUsuarios2.setString(1, nome2);
            stmtInsertUsuarios2.setString(2, email2);
            stmtInsertUsuarios2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'usuarios'!");

            //inserir dados na tabela pedido
            String produto = "Livro de Ficção";
            int quantidade = 3;
            String sqlInsertPedido = "INSERT INTO pedido (id_usuario, produto, quantidade) VALUES (?, ?, ?)";
            
            PreparedStatement stmtInsertPedido = conn.prepareStatement(sqlInsertPedido);
            stmtInsertPedido.setInt(1, 1); // id do usuário
            stmtInsertPedido.setString(2, produto);
            stmtInsertPedido.setInt(3, quantidade);
            stmtInsertPedido.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pedido'!");

            String produto2 = "Fones de Ouvido";
            int quantidade2 = 1;
            String sqlInsertPedido2 = "INSERT INTO pedido (id_usuario, produto, quantidade) VALUES (?, ?, ?)";
            
            PreparedStatement stmtInsertPedido2 = conn.prepareStatement(sqlInsertPedido2);
            stmtInsertPedido2.setInt(1, 2); // id do usuário
            stmtInsertPedido2.setString(2, produto2);
            stmtInsertPedido2.setInt(3, quantidade2);
            stmtInsertPedido2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pedido'!");

            deletarDadosUsuarios(conn, 1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um usuário com base no ID
    public static void deletarDadosUsuarios(Connection conn, int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = " + idUsuario; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Usuário deletado com sucesso");

                String sql2 = "DELETE FROM pedido WHERE id_usuario = " + idUsuario;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, os pedidos também serão removidos
                    System.out.println("Pedidos relacionados ao usuário deletado também foram removidos");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}