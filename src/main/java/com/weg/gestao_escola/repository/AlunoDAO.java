package com.weg.gestao_escola.repository;

import com.weg.gestao_escola.conexao.Conexao;
import com.weg.gestao_escola.model.Aluno;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoDAO {

    public List<Aluno> listarAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String query = "SELECT id, nome, email, matricula, data_nascimento FROM aluno";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                alunos.add(new Aluno(id, nome, email, matricula, dataNascimento));
            }
        }

        return alunos;
    }

    public Aluno buscarAlunoPorId(int id) throws SQLException{
        String query = "SELECT id, nome, email, matricula, data_nascimento FROM aluno WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int newid = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                return new Aluno(newid, nome, email, matricula, dataNascimento);
            }
        }

        return null;
    }

    public Aluno criar(Aluno aluno) throws SQLException{
        String query = "INSERT INTO aluno (nome, email, matricula, data_nascimento) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aluno.setId(rs.getInt(1));
            }
        }

        return aluno;
    }

    public void atualizar(int id, Aluno aluno) throws SQLException{
        String query = "UPDATE aluno SET nome = ?, email = ?, matricula = ?, data_nascimento = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM aluno WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
