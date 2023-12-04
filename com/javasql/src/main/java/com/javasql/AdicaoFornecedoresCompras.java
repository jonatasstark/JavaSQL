package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdicaoFornecedoresCompras {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela fornecedores
            String sqlFornecedores = "CREATE TABLE IF NOT EXISTS fornecedores (" +
                    "id_fornecedor INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "contato VARCHAR(100) NOT NULL" +
                    ")";

            Statement stmtFornecedores = conn.createStatement();
            stmtFornecedores.executeUpdate(sqlFornecedores);
            System.out.println("Tabela 'fornecedores' criada com sucesso!");

            // Criação da tabela compras
            String sqlCompras = "CREATE TABLE IF NOT EXISTS compras (" +
                    "id_compra INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_fornecedor INT NOT NULL," +
                    "produto_comprado VARCHAR(255) NOT NULL," +
                    "quantidade INT NOT NULL," +
                    "FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id_fornecedor)" +
                    ")";

            Statement stmtCompras = conn.createStatement();
            stmtCompras.executeUpdate(sqlCompras);
            System.out.println("Tabela 'compras' criada com sucesso!");

            // Inserir dados na tabela fornecedores
            String nomeFornecedor = "Fornecedor A";
            String contatoFornecedor = "Contato A";
            String sqlInsertFornecedor = "INSERT INTO fornecedores (nome, contato) VALUES (?, ?)";

            PreparedStatement stmtInsertFornecedor = conn.prepareStatement(sqlInsertFornecedor);
            stmtInsertFornecedor.setString(1, nomeFornecedor);
            stmtInsertFornecedor.setString(2, contatoFornecedor);
            stmtInsertFornecedor.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'fornecedores'!");

            // Inserir dados na tabela compras
            int idFornecedor = 1; // Coloque o ID correto do fornecedor
            String produtoComprado = "Produto A";
            int quantidade = 10;
            String sqlInsertCompra = "INSERT INTO compras (id_fornecedor, produto_comprado, quantidade) VALUES (?, ?, ?)";

            PreparedStatement stmtInsertCompra = conn.prepareStatement(sqlInsertCompra);
            stmtInsertCompra.setInt(1, idFornecedor);
            stmtInsertCompra.setString(2, produtoComprado);
            stmtInsertCompra.setInt(3, quantidade);
            stmtInsertCompra.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'compras'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}

