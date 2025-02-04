package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection connection;


    public Doctor(Connection connection){
        this.connection=connection;
    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            //resultSet helps in assigning the data to a variable and moving the pointer
            ResultSet resultSet = preparedStatement.executeQuery();


            System.out.println("Doctors");
            System.out.println("+------------+---------------------+-----------------------------+");
            System.out.println("| Doctor ID  | name                | specialization              |");
            System.out.println("+------------+---------------------+-----------------------------+");


            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name=resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|%-12s|%-21s|%-29s|\n",id ,name,specialization);


            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean checkDoctorsByID(int id){
        String query="Select * FROM doctors where id = ?";
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
