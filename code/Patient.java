package HospitalManagementSystem;

import java.lang.invoke.StringConcatFactory;
import java.sql.*;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }

    public void addPatient(){
        System.out.println("enter patient name");
        String name=scanner.next();
        System.out.println("enter patient age");
        int age=scanner.nextInt();
        System.out.println("enter patient gender");
        String gender = scanner.next();

        try{
            String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0) System.out.println("patient added successfully");
            else System.out.println("failed to add patient");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            //resultSet helps in assigning the data to a variable and moving the pointer
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("patients");
            System.out.println("+------------+---------------------+----------------+------------+");
            System.out.println("| patient ID | name                | Age            | Gender     |");
            System.out.println("+------------+---------------------+----------------+------------+");

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%-12s|%-21s|%-16s|%-12s|\n",id,name,age,gender);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean checkPatient(int id){
        String query="Select * FROM patients where id =?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) return true;
            else return false;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
