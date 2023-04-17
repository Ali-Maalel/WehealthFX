/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import entities.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import services.UserService;

/**
 *
 * @author lenovo
 */
public class SignUppController implements Initializable{
    UserService ps = new UserService();
    private TextField loginTF;
    private TextField passwordTf;
    private TextField nomTf;
    private TextField typeuserTf;
    private TextField emailTf;
    private TextField prenomTf;
    private TextField telephoneTf;
    private Label welcomeLb;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    private void AfficherPersonnes(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonnes.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    @FXML
    private void AjouterPersonne(ActionEvent event) {
         try {
            String Login = loginTF.getText();
            String nom = nomTf.getText();
            String password = passwordTf.getText();
            String prenom = prenomTf.getText();
            String telephone =telephoneTf.getText();
            String typeuser =typeuserTf.getText();
            String email =emailTf.getText();
            User p = new User( Login,  password,  nom,  prenom,  telephone,  typeuser,  email);
            ps.ajouter(p);
            
            
             //Date d = Date.valueOf(datepicker.getValue());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
