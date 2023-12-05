package main.java.com.javasql;

import java.sql.*;

public class RegistroClientesVendas {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        // Conexão com o banco de dados
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela clientes
            String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                    "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL" +
                    ")";

            Statement stmtClientes = conn.createStatement();
            stmtClientes.executeUpdate(sqlClientes);
            System.out.println("Tabela 'clientes' criada com sucesso!");

            // Criação da tabela vendas
            String sqlVendas = "CREATE TABLE IF NOT EXISTS vendas (" +
                    "id_venda INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_cliente INT NOT NULL," +
                    "produto_vendido VARCHAR(255) NOT NULL," +
                    "valor DECIMAL(10,2) NOT NULL," +
                    "FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)" +
                    ")";

            Statement stmtVendas = conn.createStatement();
            stmtVendas.executeUpdate(sqlVendas);
            System.out.println("Tabela 'vendas' criada com sucesso!");

            // Inserir dados na tabela clientes
            String nomeCliente = "Ana";
            String emailCliente = "ana@example.com";
            String sqlInsertCliente = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

            PreparedStatement stmtInsertCliente = conn.prepareStatement(sqlInsertCliente);
            stmtInsertCliente.setString(1, nomeCliente);
            stmtInsertCliente.setString(2, emailCliente);
            stmtInsertCliente.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'clientes'!");

            String nomeCliente2 = "Pedro";
            String emailCliente2 = "pedro@example.com";
            String sqlInsertCliente2 = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

            PreparedStatement stmtInsertCliente2 = conn.prepareStatement(sqlInsertCliente2);
            stmtInsertCliente2.setString(1, nomeCliente2);
            stmtInsertCliente2.setString(2, emailCliente2);
            stmtInsertCliente2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'clientes'!");

            // Inserir dados na tabela vendas
            int idCliente = 1; // ID do cliente registrado anteriormente
            String produtoVendido = "Celular";
            double valorVenda = 1200.00;
            String sqlInsertVenda = "INSERT INTO vendas (id_cliente, produto_vendido, valor) VALUES (?, ?, ?)";

            PreparedStatement stmtInsertVenda = conn.prepareStatement(sqlInsertVenda);
            stmtInsertVenda.setInt(1, idCliente);
            stmtInsertVenda.setString(2, produtoVendido);
            stmtInsertVenda.setDouble(3, valorVenda);
            stmtInsertVenda.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'vendas'!");

            int idCliente2 = 2; // ID do cliente registrado anteriormente
            String produtoVendido2 = "Fones";
            double valorVenda2 = 150.00;
            String sqlInsertVenda2 = "INSERT INTO vendas (id_cliente, produto_vendido, valor) VALUES (?, ?, ?)";

            PreparedStatement stmtInsertVenda2 = conn.prepareStatement(sqlInsertVenda2);
            stmtInsertVenda2.setInt(1, idCliente2);
            stmtInsertVenda2.setString(2, produtoVendido2);
            stmtInsertVenda2.setDouble(3, valorVenda2);
            stmtInsertVenda2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'vendas'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
