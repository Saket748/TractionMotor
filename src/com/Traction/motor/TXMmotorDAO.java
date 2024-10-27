package com.Traction.motor;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;

public class TXMmotorDAO {

    public static boolean insertTXMDataInDB(MotorTable motorTable){
        boolean f = false;
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = "insert into MotorTable(MotorNumber,ProjectName,SupplyDate) values(?,?,?)";
            PreparedStatement pstm = con.prepareStatement(q);
            //now set the values at '?' by pstm
            pstm.setString(1,motorTable.getMotorNumber());
            pstm.setString(2,motorTable.getProjectName());
            pstm.setString(3, motorTable.getSupplyDate());

            pstm.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }
    // now delete the motor from table
    public static boolean DeleteTXMDataInDB(int Sno){
        boolean f = false;
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = "delete from MotorTable where SNO = ?";
            PreparedStatement pstm = con.prepareStatement(q);
            //now set the values at '?' by pstm
            pstm.setInt(1,Sno);
            //execute the
            pstm.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }
    //showing the data
    public static void ShowAllMotors() {
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = "select * from MotorTable";
            Statement stm = con.createStatement();
            //execute the query stored in ResultSet
            ResultSet set = stm.executeQuery(q);

            while (set.next()){
                String MotorNum = set.getString("MotorNumber");
                System.out.println(MotorNum);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //showing data Date Wise
    public static void ShowAllMotorsByProject(String projectName) throws IOException {
        try {

            Connection con = ConnectionProvider.createC();
            String q = "Select MotorNumber from MotorTable where ProjectName = "+"'"+projectName+"'";
            Statement stm = con.createStatement();
            ResultSet set = stm.executeQuery(q);
            while(set.next()){
                String temp = set.getString(1);
                System.out.println(temp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void ShowAllMotorsByDate(String stdate,String endDate) throws IOException{
        try {

            Connection con = ConnectionProvider.createC();
            String q = "SELECT DISTINCT MotorNumber\n" +
                    "FROM MotorTable\n" +
                    "WHERE SupplyDate between " +"'" + stdate+"'" + "And" +"'"+endDate +"'";
            Statement stm = con.createStatement();
            ResultSet set = stm.executeQuery(q);
            while(set.next()){
                String temp = set.getString("MotorNumber");
                System.out.println(temp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /*=================================================================================*/
    // Now we are Starting new Table MotorTable ends here

    public static boolean insertTrainCoachesDataInDB(String trainNum, String coachNum)throws IOException {
        boolean f = false;
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = "insert into CoachTrainTable(TrainsNumber,CoachNumber) values(?,?)";
            PreparedStatement pstm = con.prepareStatement(q);
            //now set the values at '?' by pstm
            pstm.setString(1,trainNum);
            pstm.setString(2,coachNum);

            pstm.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }

    public static boolean insertTransactionDateInDB(String motorNum, String transacDate,String coachNum,String trainNum)  {
        boolean f = false;
        try {
            Connection con = ConnectionProvider.createC();
            String q = "select SNO from MotorTable where MotorNumber = "+"'"+motorNum+"'";
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(q);

            // it gives me a SNO inside temp then we put it into Table
        if(resultSet.next()) {
            int temp = resultSet.getInt("SNO");
            System.out.println("SNO is : "+ temp);
             con = ConnectionProvider.createC();
            String que = "insert into CoachTrainTable(TrainsNumber,CoachNumber,SNO,TransactionDate) values(?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(que);
            //now set the values at '?' by pstm
            pstm.setString(1,trainNum);
            pstm.setString(2,coachNum);
            pstm.setInt(3,temp);
            pstm.setString(4,transacDate);

            pstm.executeUpdate();
            f = true;
        }


        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return f;
    }
    public static void TransactionDate(String motorNum) {
        try {
            Connection con = ConnectionProvider.createC();
            String q = "SELECT CoachTrainTable.TransactionDate\n" +
                    "FROM CoachTrainTable\n" +
                    "INNER JOIN MotorTable ON CoachTrainTable.SNO = MotorTable.SNO\n" +
                    " where MotorNumber = " + "'" + motorNum + "'";
            Statement pstm = con.createStatement();
            ResultSet resultSet = pstm.executeQuery(q);
            if (resultSet.next()) {
                String TransDate = resultSet.getString("TransactionDate");
                System.out.println(TransDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowAllMotorsByTrainnum(String TrainNum) throws IOException {
        try {

            Connection con = ConnectionProvider.createC();
            String q = "SELECT MotorNumber\n" +
                    "FROM CoachTrainTable\n" +
                    "INNER JOIN MotorTable ON CoachTrainTable.SNO = MotorTable.SNO\n" +
                    "where CoachTrainTable.TrainsNumber ="+"'"+TrainNum+"'";
            Statement stm = con.createStatement();
            ResultSet set = stm.executeQuery(q);
            while(set.next()){
                String temp = set.getString("MotorNumber");
                System.out.println("["+temp+"]");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void ShowAllMotorsInCoach(String coachNum) {
        try {
            Connection con = ConnectionProvider.createC();
            String q = "SELECT MotorNumber\n" +
                    "FROM CoachTrainTable\n" +
                    "INNER JOIN MotorTable ON CoachTrainTable.SNO = MotorTable.SNO\n" +
                    "where CoachTrainTable.CoachNumber = "+"'"+coachNum+"'";
            Statement pstm = con.createStatement();
            ResultSet resultSet = pstm.executeQuery(q);

            if(resultSet.next()){
                String temp = resultSet.getString("MotorNumber");
                System.out.println("["+temp+"]");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //insert the data into FailureTable



    public static void AllFailureMotors() throws IOException, SQLException {
        Connection con = ConnectionProvider.createC();
        String q = "select MotorNumber from FailureTable";
        Statement stm = con.createStatement();
        ResultSet resultSet = stm.executeQuery(q);

        while(resultSet.next()){
            String temp = resultSet.getString("MotorNumber");
            System.out.println("["+temp+"]");
        }
    }

    public static void FailedMotordate(String motorNum) throws SQLException,IOException {
        Connection con = ConnectionProvider.createC();
        String q = "select FailureDate from FailureTable where MotorNumber = "+"'"+motorNum+"'";
        Statement stm = con.createStatement();
        ResultSet resultSet = stm.executeQuery(q);

        if(resultSet.next()){
            String temp = resultSet.getString("FailureDate");
            System.out.println("["+temp+"]");
        }
    }

    public static void ReplacedMotor(String motorNum) throws SQLException,IOException{
        Connection con = ConnectionProvider.createC();
        String q = "select NewMotorReplace from FailureTable where MotorNumber = "+"'"+motorNum+"'";
        Statement stm = con.createStatement();
        ResultSet resultSet = stm.executeQuery(q);
        String temp = "";

        if(resultSet.next()){
             temp = resultSet.getString("NewMotorReplace");
            System.out.println(temp);
        }

    }

    public static boolean insertFailureTableDateInDB(String motorNum, String failureDate, String newMotorNumReplaced) {
        boolean f = false;
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = "insert into FailureTable(MotorNumber,FailureDate,NewMotorReplace) values(?,?,?);";
            PreparedStatement pstm = con.prepareStatement(q);
            //now set the values at '?' by pstm
            pstm.setString(1,motorNum);
            pstm.setString(2,failureDate);
            pstm.setString(3,newMotorNumReplaced);

            pstm.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public static boolean UpadteNewMotorNumber(String newMotorNum,String FailedMotor) {
        boolean f = false;
        try {
            //connnection
            Connection con = ConnectionProvider.createC();
            String q = " update FailureTable set NewMotorReplace = "+"'"+newMotorNum+"'"+ " where MotorNumber = ?";
            PreparedStatement pstm = con.prepareStatement(q);
            //now set the values at '?' by pstm
            pstm.setString(1,FailedMotor);
            pstm.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
