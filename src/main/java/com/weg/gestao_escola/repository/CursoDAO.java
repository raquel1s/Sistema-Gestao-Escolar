package com.weg.gestao_escola.repository;

import com.weg.gestao_escola.conexao.Conexao;
import com.weg.gestao_escola.model.Curso;
import com.weg.gestao_escola.util.GerarIn;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDAO {

    public List<Curso> listarCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT id, nome, codigo FROM curso";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                cursos.add(new Curso(id, nome, codigo));
            }
        }

        return cursos;
    }

    public List<String> listarNomeProfessores(int id) throws SQLException {
        List<String> professores = new ArrayList<>();
        String query = "SELECT p.nome as professor " +
                "FROM professor p " +
                "LEFT JOIN turma t " +
                "ON p.id = t.professor_id " +
                "LEFT JOIN curso c " +
                "ON t.curso_id = c.id " +
                "WHERE c.id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                professores.add(rs.getString("professor"));
            }
        }

        return professores;
    }

    public List<String> listaProfessorNome(List<Integer> idsProfessores) throws SQLException {
        String query = """
                SELECT p.nome
                FROM professor p
                LEFT JOIN turma t
                ON p.id = t.professor_id
                WHERE p.id IN """+ GerarIn.gerando(idsProfessores.size());

        List<String> nomeProfessores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for(int i = 0; i < idsProfessores.size(); i++){
                stmt.setInt(i + 1, idsProfessores.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                nomeProfessores.add(nome);
            }
        }
        return nomeProfessores;
    }

    public Curso buscarCursoPorId(int id) throws SQLException{
        String query = "SELECT id, nome, codigo FROM curso WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int newid = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                return new Curso(newid, nome, codigo);
            }
        }

        return null;
    }

    public Curso criar(Curso curso) throws SQLException{
        String query = "INSERT INTO curso (nome, codigo) VALUES (?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                curso.setId(rs.getInt(1));
            }
        }

        return curso;
    }

    public void atualizar(int id, Curso curso) throws SQLException{
        String query = "UPDATE curso SET nome = ?, codigo = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM curso WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
