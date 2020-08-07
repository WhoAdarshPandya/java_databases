/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mysql_crud;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DC
 */
public class Java_mysql_crud {

    public static String url = "jdbc:mysql://localhost:1008/java_crud";
    public static String user = "root";
    public static String password = "";
    public static Connection GlobalCon = null;
    public static ResultSet GlobalRs = null;
    public static Statement GlobalStat = null;

    /**
     * @param args the command line arguments
     */
    public static void InsertStatement(String name, String year, String director) {
        try {
            String query = "INSERT INTO movie(name,year,director) VALUES(?,?,?)";
            PreparedStatement pd = GlobalCon.prepareStatement(query);
            pd.setString(1, name);
            pd.setString(2, year);
            pd.setString(3, director);
            pd.execute();
            System.out.println("inserted");
        } catch (SQLException ex) {
            Logger.getLogger(Java_mysql_crud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void DeleteMovie(int id) {
        String query = "delete from movie where id = ?";
        PreparedStatement pd;
        try {
            pd = GlobalCon.prepareStatement(query);
            pd.setInt(1, id);
            pd.execute();
            System.out.println("movie deleted ");
        } catch (SQLException ex) {
            Logger.getLogger(Java_mysql_crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void UpdateMovie(String name, String year, String dir, int id) {
        String query = "UPDATE movie set name = ?, year = ? ,director = ? where id = ?";
        try {
            PreparedStatement pd = GlobalCon.prepareStatement(query);
            pd.setString(1, name);
            pd.setString(2, year);
            pd.setString(3, dir);
            pd.setInt(4, id);
            pd.execute();
            System.out.println("updated...");
        } catch (SQLException ex) {
            Logger.getLogger(Java_mysql_crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ShowList() {
        String query = "select * from movie";
        try {
            GlobalRs = GlobalStat.executeQuery(query);
            System.out.println("********movies********");
            while (GlobalRs.next()) {
                System.out.println("Movie id : " + GlobalRs.getInt(1) + " Movie Name : " + GlobalRs.getString(2) + " Movie Year : " + GlobalRs.getString(3) + " Director : " + GlobalRs.getString(4));
            }
            System.out.println("********movies********");
        } catch (SQLException ex) {
            Logger.getLogger(Java_mysql_crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        int choice, id;
        String name, year, dir;
        // TODO code application logic here
        try {
            Class.forName("com.mysql.jdbc.Driver");
            GlobalCon = DriverManager.getConnection(
                    "jdbc:mysql://localhost:1008/jdbc_crud", "root", "");
            if (GlobalCon == null) {
                System.out.println("not initialized");
            } else {
                System.out.println("*****connceted to db*****");

            }
            GlobalStat = GlobalCon.createStatement();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. list all movies");
                System.out.println("2. Insert a movie");
                System.out.println("3. Delete a movie");
                System.out.println("4. Update the movie");
                System.out.println("5. Exit");
                System.out.println("\nyour input : ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("list");
                        ShowList();
                        break;
                    case 2:
                        System.out.println("enter details....");
                        System.out.println("Enter movie name : ");
                        name = sc.nextLine();
                        System.out.println("Enter Movie year : ");
                        year = sc.nextLine();
                        System.out.println("Enter dir name : ");
                        dir = sc.nextLine();
                        InsertStatement(name, year, dir);
                        break;
                    case 3:
                        System.out.println("Enter movie number to delete it : ");
                        id = sc.nextInt();
                        sc.nextLine();
                        DeleteMovie(id);
                        break;
                    case 4:
                        System.out.println("Enter The movie no you want to update : ");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter movie name : ");
                        name = sc.nextLine();
                        System.out.println("Enter movie year : ");
                        year = sc.nextLine();
                        System.out.println("Enter movie dir : ");
                        dir = sc.nextLine();
                        UpdateMovie(name, year, dir, id);
                        break;
                    case 5:
                        GlobalCon.close();
                        System.out.println("exiting....");
                        System.exit(0);
                    default:
                        System.out.println("invalid choice , try again...");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
