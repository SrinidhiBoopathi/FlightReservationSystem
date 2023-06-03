package dao;

import Helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class CustomerDAO {
    public static void signup(String f_name,String l_name,String mail,String password,String gender,String dob,String aadhar,String p_no){
        ConnectionHelper help=new ConnectionHelper();
        Connection connectt=help.helper();
        if(connectt!=null) {
            try {
                Statement statement = connectt.createStatement();
                String use="use flight_reservation";
                statement.execute(use);

                String ctable="create table if not exists cust_table(f_name varchar(255),l_name varchar(255),mail varchar(255),password varchar(255),gender varchar(255),dob varchar(255),aadhar varchar(255),p_no varchar(255))";
                statement.execute(ctable);

                String insert_ctable="INSERT INTO cust_table (f_name, l_name, mail, password, gender, dob, aadhar, p_no) " +
                        "VALUES ('" + f_name + "','" + l_name + "','" + mail + "','" + password + "','" + gender + "','" + dob + "','" + aadhar + "','"+ p_no +"')";
                statement.executeUpdate(insert_ctable);

                connectt.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public static String getPassword(String mail){
        ConnectionHelper help=new ConnectionHelper();
        Connection connectt=help.helper();
        String s="";
        if(connectt!=null) {
            try {
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);

                String query="select password from cust_table where mail='"+mail+"'";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    s = resultSet.getString("password");
                }

            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return s;
    }



}
