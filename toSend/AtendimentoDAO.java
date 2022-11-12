/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaclinica.core.DAO;

import com.mycompany.sistemaclinica.core.entities.Atendimento;
import com.mycompany.sistemaclinica.core.entities.Pacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtendimentoDAO {

    public List<Atendimento> listarTodos() {
        try {
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;
            ps = conexao.prepareStatement("SELECT * FROM public.atendimento");
            ResultSet rs = ps.executeQuery();
            List<Atendimento> listAtendimentos = new ArrayList<>();

            while (rs.next()) {
                Atendimento rAtendimentos = new Atendimento();

                rAtendimentos.setDisc(rs.getString("disc"));
                rAtendimentos.setData(LocalDateTime.parse(rs.getString("data")));
                rAtendimentos.setNome(rs.getString("nome"));
                rAtendimentos.setCreatedAt(rs.getString("created_at"));
                rAtendimentos.setAtendimento_id(rs.getInt("atendimento_id"));
                rAtendimentos.setStatus(rs.getInt("status"));

                listAtendimentos.add(rAtendimentos);
            }
            return listAtendimentos;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Atendimento> listarPorID(int paciente_id) {
        try {
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;
            ps = conexao.prepareStatement("SELECT * FROM public.atendimento where paciente_id =" + paciente_id);
            ResultSet rs = ps.executeQuery();
            List<Atendimento> listAtendimentos = new ArrayList<>();

            while (rs.next()) {
                Atendimento rAtendimentos = new Atendimento();

                rAtendimentos.setDisc(rs.getString("disc"));
                rAtendimentos.setData(LocalDateTime.parse(rs.getString("data")));
                rAtendimentos.setNome(rs.getString("nome"));
                rAtendimentos.setCreatedAt(rs.getString("created_at"));
                rAtendimentos.setAtendimento_id(rs.getInt("atendimento_id"));
                rAtendimentos.setStatus(rs.getInt("status"));

                listAtendimentos.add(rAtendimentos);
            }
            return listAtendimentos;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void salvar(Atendimento novoAtendimento) {
        try {
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;

            ps = conexao.prepareCall("INSERT INTO public.atendimento(disc,data,nome,status) VALUES ( ? , ?,?,?)");
            ps.setString(1, novoAtendimento.getDisc());
            ps.setString(2, novoAtendimento.getData().toString());
            ps.setString(3, novoAtendimento.getNome());
            ps.setInt(4, novoAtendimento.getStatus());
            ps.execute();
            DBfactory.closeConnection();
            System.out.println("salvou: " + novoAtendimento.getAtendimento_id());
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletar(int id) {
        try {
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;
            ps = conexao.prepareCall("Delete from public.atendimento where atendimento_id=" + id);
            ps.execute();
            DBfactory.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
        public void alterar(Atendimento atendimento) {
        try {
            System.out.println(atendimento.getAtendimento_id());
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;

            ps = conexao.prepareCall("UPDATE public.atendimento SET nome = ? ,disc = ?,data = ?,status = ? where atendimento_id= ?");

            ps.setString(1, atendimento.getNome());
            ps.setString(2, atendimento.getDisc());
            ps.setString(3,atendimento.getData().toString());
            ps.setInt(4, atendimento.getStatus());
            ps.setInt(5,atendimento.getAtendimento_id());

            ps.execute();
            DBfactory.closeConnection();

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public List<Atendimento> listTop(int status){
                    try {
            Connection conexao = DBfactory.getConnection();
            PreparedStatement ps;
            ps = conexao.prepareStatement("SELECT * FROM public.atendimento where status = ? ORDER BY atendimento_id DESC LIMIT 10");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            List<Atendimento> listAtendimentos = new ArrayList<>();

            while (rs.next()) {
                Atendimento rAtendimentos = new Atendimento();

                rAtendimentos.setDisc(rs.getString("disc"));
                rAtendimentos.setData(LocalDateTime.parse(rs.getString("data")));
                rAtendimentos.setNome(rs.getString("nome"));
                rAtendimentos.setCreatedAt(rs.getString("created_at"));
                rAtendimentos.setAtendimento_id(rs.getInt("atendimento_id"));
                rAtendimentos.setStatus(rs.getInt("status"));

                listAtendimentos.add(rAtendimentos);
            }
            return listAtendimentos;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
}
