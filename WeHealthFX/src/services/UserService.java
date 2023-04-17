/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Database;

/**
 *
 * @author lenovo
 */
public class UserService implements IService<User>{
    Connection cnx;
    public UserService(){
        cnx = Database.getInstance().getCnx();
    }
    @Override
    public void ajouter(User t) throws SQLException {
        String req = "insert into user(id ,Login,roles,password,nom,prenom,telephone,blocked,typeuser,email) values" + "('" +t.getId()+","+ t.getLogin() + ","+t.getRoles()+"," + t.getPassword() + "," + t.getNom()+ "," + t.getPrenom()+ "," + t.getTelephone()+ "," + t.getTypeuser()+ "," + t.getEmail() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(User t) throws SQLException {
        String req = "UPDATE users SET Login=?, password=?, nom=?, prenom=?, telephone=?, typeuser=?, email=? WHERE id=?";
        
        PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getNom());
            ps.setString(4, t.getPrenom());
            ps.setString(5, t.getTelephone());
            ps.setString(6, t.getTypeuser());
            ps.setString(7, t.getEmail());
            ps.setInt(8, t.getId()); // Assuming User has an id field
            ps.executeUpdate();
    }
    

    @Override
    public void supprimer(User t) throws SQLException {
        String req = "DELETE FROM users WHERE id=?";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from users ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            User p =new User();
            p.setId(rs.getInt("id"));
            p.setLogin(rs.getString("login"));
            p.setPassword(rs.getString("password"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTelephone(rs.getString("telephone"));
            p.setTypeuser(rs.getString("typeuser"));
            p.setBlocked(rs.getBoolean("blocked"));
            p.setEmail(rs.getString("email"));
            users.add(p);
        }
        return users ;
    }
    
    public List<User> recupererById(int id) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from users where id= ?";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            User p =new User();
            p.setId(rs.getInt("id"));
            p.setLogin(rs.getString("login"));
            p.setPassword(rs.getString("password"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTelephone(rs.getString("telephone"));
            p.setTypeuser(rs.getString("typeuser"));
            p.setBlocked(rs.getBoolean("blocked"));
            p.setEmail(rs.getString("email"));
            users.add(p);
        }
        return users ;
    }
    public User loginUser(User u) {
        try {

            String sql = " select * from user where email ='" + u.getEmail() + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            User us = new User();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setEmail(rs.getString("email"));
                us.setPassword(rs.getString("password"));
                us.setTypeuser(rs.getString("type"));

            }
            return us;
        } catch (SQLException ex) {
            //Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
