/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatabuku;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.databukuimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dito
 */
public class databukuDAO implements databukuimplement{
    Connection connection;
    
    final String select = "select * from buku;";
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga) VALUES (?, ?, ?, ?);";
    final String update = "update buku set judul=?, penulis=?, rating=?, harga=? where id=?";
    final String delete = "delete from buku where id=?";

    public databukuDAO(){
        connection = connector.connection();
    }
    
    @Override
    public void insert(databuku b) {
       PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getPenulis());
            statement.setDouble(3, b.getRating());
            statement.setDouble(4, b.getHarga());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                b.setId(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(databuku b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getPenulis());
            statement.setDouble(3, b.getRating());
            statement.setDouble(4, b.getHarga());
            statement.setInt(5, b.getId());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<databuku> getAll() {
        List<databuku> dabuk = null;
        try{
            dabuk = new ArrayList<databuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                databuku buku = new databuku();
                buku.setId(rs.getInt("id"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setRating(rs.getDouble("rating"));
                buku.setHarga(rs.getDouble("harga"));
                dabuk.add(buku);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return dabuk;
    }
}
