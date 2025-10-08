package com.weg.gestao_escola.repository;

import com.weg.gestao_escola.conexao.Conexao;
import com.weg.gestao_escola.model.Aula;
import com.weg.gestao_escola.model.AulaResposta;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaDAO {

    public List<AulaResposta> listarAulas() throws SQLException {
        List<AulaResposta> aulas = new ArrayList<>();
        String query = "SELECT a.id, t.nome as nome_turma, a.data_hora, a.assunto " +
                "FROM aula a " +
                "LEFT JOIN turma t " +
                "ON a.turma_id = t.id";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nomeTurma = rs.getString("nome_turma");
                LocalDateTime dataHora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");

                aulas.add(new AulaResposta(id, nomeTurma, dataHora, assunto));
            }
        }

        return aulas;
    }

    public AulaResposta buscarAulaPorIdResposta(int id) throws SQLException{
        String query = "SELECT a.id, t.nome as nome_turma, a.data_hora, a.assunto " +
                "FROM aula a " +
                "LEFT JOIN turma t " +
                "ON a.turma_id = t.id " +
                "WHERE a.id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int newid = rs.getInt("id");
                String nomeTurma = rs.getString("nome_turma");
                LocalDateTime dataHora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");

                return new AulaResposta(newid, nomeTurma, dataHora, assunto);
            }
        }

        return null;
    }

    public Aula buscarAulaPorId(int id) throws SQLException{
        String query = "SELECT id, turma_id, data_hora, assunto FROM aula WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int newid = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                LocalDateTime dataHora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");

                return new Aula(newid, turmaId, dataHora, assunto);
            }
        }

        return null;
    }

    public Aula criar(Aula aula) throws SQLException{
        String query = "INSERT INTO aula (turma_id, data_hora, assunto) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, aula.getTurmaId());
            stmt.setObject(2, aula.getDataHora());
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aula.setId(rs.getInt(1));
            }
        }

        return aula;
    }

    public void atualizar(int id, Aula aula) throws SQLException{
        String query = "UPDATE aula SET turma_id = ?, data_hora = ?, assunto = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, aula.getTurmaId());
            stmt.setObject(2, aula.getDataHora());
            stmt.setString(3, aula.getAssunto());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM aula WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
