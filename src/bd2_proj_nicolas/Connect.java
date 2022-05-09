package bd2_proj_nicolas;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Connect {
    public Statement stm; 
    public ResultSet rs; 
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String caminho = "jdbc:sqlserver://localhost:1433;databaseName=Northwind";
    private String usuario = "nicolas";
    private String senha = "786600";
    public Connection conexao;
    
    public void conectar() {
        try {
            System.setProperty("jdbc.Drivers", driver); 
            conexao = DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectado.", "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de conexão.\nERRO: " + ex.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void desconectar() { 
        try {
            conexao.close(); 
            JOptionPane.showMessageDialog(null, "Conexão encerrada.", "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão.\nERRO: " + ex.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public String geralSelect(int enumID){
        String result = "";
        String consulta = "Select * from [" + Tabelas.valueOf(enumID).getNome() + "]";
        try {
            stm = conexao.createStatement();
            rs = stm.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                for(int i = 1; i <= Tabelas.valueOf(enumID).getNumCol(); i++){
                    result = result + "|" + rs.getString(i) + "|";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int geralInsert(int enumID, String[] values){
        int result = 0;
        int i; System.out.println(Tabelas.valueOf(enumID).getNome()); System.out.println(values.length);
        for(i = 0; i < values.length; i++){
            System.out.println(values[i]);
        }        
        String consulta = "INSERT INTO " + Tabelas.valueOf(enumID).getNome() + " VALUES(";
        for(i = 0; i < values.length - 1; i++){
            consulta = consulta + "'" + values[i] + "',";
        }
        consulta = consulta + "'" + values[i] + "');";
        System.out.println(consulta);
        
        try {
            stm = conexao.createStatement();
            result = stm.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public int delete(int enumID, String linhaID){
        int result = 0;
        String nomeAtribID = Tabelas.valueOf(enumID).getAttributes()[1];
        System.out.println(nomeAtribID); System.out.println(Tabelas.valueOf(enumID).getNome());
        String consulta = "DELETE FROM " + Tabelas.valueOf(enumID).getNome() + " WHERE " + nomeAtribID + " = " + "'"+linhaID+"'";
        System.out.println(consulta);
        try {
            stm = conexao.createStatement();
            result = stm.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    int geralUpdate(int enumID, String[] values) {
        int result = 0;
        int i; System.out.println(Tabelas.valueOf(enumID).getNome()); System.out.println(values.length - 1);
        for(i = 0; i < values.length - 1; i++){
            System.out.println(values[i]);
        }
        
        String consulta = "UPDATE " + Tabelas.valueOf(enumID).getNome() + " SET ";
        for(i = 1; i < values.length; i++){
            if(i < values.length - 1){
                consulta = consulta + Tabelas.valueOf(enumID).getAttributes()[i] + " = '" + values[i] + "',";
            }
            else{
                consulta = consulta + Tabelas.valueOf(enumID).getAttributes()[i] + " = '" + values[i] + "' ";            
            }
        }
        consulta = consulta + " WHERE " + Tabelas.valueOf(enumID).getNome() +"." + Tabelas.valueOf(enumID).getAttributes()[0] + " = '" + values[0] + "';";
        System.out.println(consulta);
        
        try {
            stm = conexao.createStatement();
            result = stm.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
