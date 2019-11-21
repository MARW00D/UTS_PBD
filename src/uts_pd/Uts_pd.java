/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author adib
 */
public class Uts_pd {

    static final String Jdbc_driver = "com.mysql.jdbc.Driver";
    static final String root = "jdbc:mysql://localhost/uts_pemrograman_database";
    static final String username = "root";
    static final String password = "";

    static Connection con;
    static Statement st;
    static ResultSet rs;
    public static String user;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        koneksi();
        try {
            if (login()) {
                update();
                insertdata();
                tampildata();
            } else {
                System.out.println("gagal");
            }
        } catch (Exception e) {
        }
    }

    static void koneksi() {
        try {
            Class.forName(Jdbc_driver);
            con = DriverManager.getConnection(root, username, password);
            st = (Statement) con.createStatement();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    static void insertdata() {
        String nama = JOptionPane.showInputDialog("Nama Produk");
        String harga = JOptionPane.showInputDialog("Harga Produk");
        String sql = "insert into Produk (nama_produk,harga_produk) values ('" + nama + "','" + harga + "')";
        try {
            st.executeUpdate(sql);
            System.out.println("update berhasil");
        } catch (Exception e) {
            System.out.println("update salah");
        }
    }

    static boolean login() {
        boolean cek;

        cek = false;
         user = JOptionPane.showInputDialog("username");
        String pass = JOptionPane.showInputDialog("password");
        String sql = "select * from login_user where username='" + user + "'and password = '" + pass + "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
        }

    }

    static void update() {
        try {
            String sqlup = "update login_user set last_login=now() where username='" + user + "'";
            st.executeUpdate(sqlup);

        } catch (Exception e) {

        }
    }

    static void tampildata() {
        try {
            String sql = "select * from produk";
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
               JOptionPane.showMessageDialog(null, rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        } catch (Exception e) {
        }
    }
}
