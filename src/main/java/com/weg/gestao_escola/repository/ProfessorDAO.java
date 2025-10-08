package com.weg.gestao_escola.repository;

import com.weg.gestao_escola.conexao.Conexao;
import com.weg.gestao_escola.model.Professor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorDAO {

    public List<Professor> listarProfessores() throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String query = "SELECT id, nome, email, disciplina FROM professor";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                professores.add(new Professor(id, nome, email, disciplina));
            }
        }

        return professores;
    }

    public Professor buscarProfessorPorId(int id) throws SQLException{
        String query = "SELECT id, nome, email, disciplina FROM professor WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int newid = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                return new Professor(newid, nome, email, disciplina);
            }
        }

        return null;
    }

    public Professor criar(Professor professor) throws SQLException{
        String query = "INSERT INTO professor (nome, email, disciplina) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                professor.setId(rs.getInt(1));
            }
        }

        return professor;
    }

    public void atualizar(int id, Professor professor) throws SQLException{
        String query = "UPDATE professor SET nome = ?, email = ?, disciplina = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM professor WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
