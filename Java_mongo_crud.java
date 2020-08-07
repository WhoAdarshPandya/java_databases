/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mongo_crud;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DC
 */
public class Java_mongo_crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DbConfig.init();
        int choice, id, price;
        String name;
        boolean avail;
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Insert a record ");
            System.out.println("2.Show Record by Id ");
            System.out.println("3.Show all recs");
            System.out.println("4.Delete a Record");
            System.out.println("5.Update the record");
            System.out.println("6.Exit");
            System.out.println("Your input : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter name");
                    name = sc.nextLine();
                    System.out.println("Enter price");
                    price = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Avail (Y/N): ");
                    String temp = sc.nextLine();
                    avail = temp == "Y" ? true : false;
                    DbConfig.insertRecord(new Random().nextInt(), name, price, avail);
                    break;
                case 2:
                    System.out.println("Enter Id to show the record : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    DbConfig.FetchRecordById(id);
                    break;
                case 3:
                    DbConfig.ShowAllRecords();
                    break;
                
                case 4:
                    System.out.println("Enter Id to delete the record : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    DbConfig.DeleteRecordById(id);
                    break;
                
                case 5:
                    System.out.println("Enter Id : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter name");
                    name = sc.nextLine();
                    System.out.println("Enter price");
                    price = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Avail (Y/N): ");
                    String temp2 = sc.nextLine();
                    avail = temp2 == "Y" ? true : false;
                    DbConfig.UpdateRecordById(id, name, price, avail);
                    break;
                
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
}
