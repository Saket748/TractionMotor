import com.Traction.motor.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Start {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Welcome to my Application...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("PRESS 1 FOR Inserting Data(BHEL)");
            System.out.println("PRESS 2 FOR Delete Motor");
            System.out.println("PRESS 3 FOR Total Motors in DB");
            System.out.println("PRESS 4 FOR All motors in Project");
            System.out.println("PRESS 5 FOR Motors between dates");
            System.out.println("PRESS 6 FOR Inserting Data of Trains-Coaches(BHEL)");
            System.out.println("PRESS 7 FOR Inserting Transaction-Date(Railway)");
            System.out.println("PRESS 8 FOR Transaction-Date by Motor-Number");
            System.out.println("PRESS 9 FOR All Motors in Train/Rack");
            System.out.println("PRESS 10 FOR motor in a specific Coach");
            System.out.println("PRESS 11 FOR Inserting data into Failure-Table");
            System.out.println("PRESS 17 FOR insert New Motor-Number");
            System.out.println("PRESS 12 FOR Failure-Date of Motor");
            System.out.println("PRESS 13 FOR Replaced Motor");
            System.out.println("PRESS 14 FOR All Failed-Motors");

            int c ;
            c = Integer.parseInt(br.readLine());

            if(c==1){
                //Add students
                System.out.println("Enter your Project Name :");
                String ProjectName = br.readLine();
                System.out.println("Enter your Motor Number :");
                String MotorNumber = br.readLine();
                System.out.println("Enter your Supply-Date(YYYY-MM-DD)");
                String SupplyDate = br.readLine();
                //create Trains object to store trains info...
                MotorTable motorTable = new MotorTable(MotorNumber,ProjectName,SupplyDate);
                boolean temp = TXMmotorDAO.insertTXMDataInDB(motorTable);
                if(temp){
                    System.out.println("Motor Added Successfully");
                }
                else{
                    System.out.println("Not Added !! try Again");
                }
                System.out.println(motorTable);

            }
            else if(c==2){
                //delete Motor
                System.out.println("Enter Serial Number");
                int Sno = Integer.parseInt(br.readLine());
                boolean answer = TXMmotorDAO.DeleteTXMDataInDB(Sno);
                if(answer){
                    System.out.println("Motor Deleted Successfully");
                }
                else{
                    System.out.println("Not Deleted !! try again");
                }
            }
            else if(c==3){
                //show information
                System.out.println("Your Data is here");
                TXMmotorDAO.ShowAllMotors();

            }
            else if(c==4){
                // Project wise Motors
                System.out.println("Enter Project Name : ");
                String projectName = br.readLine();
                System.out.println("Your Motors are here :");
                TXMmotorDAO.ShowAllMotorsByProject(projectName);
            }
            else if(c==5){
                System.out.println("Enter Starting Date");
                String stdate = br.readLine();
                System.out.println("Enter Ending Date");
                String endDate = br.readLine();
                TXMmotorDAO.ShowAllMotorsByDate(stdate,endDate);

            }
            else if(c==6) {
                System.out.println("Enter Your Train-Number");
                String TrainNum = br.readLine();
                System.out.println("Enter your Coach-Number");
                String CoachNum = br.readLine();
                boolean ans = TXMmotorDAO.insertTrainCoachesDataInDB(TrainNum,CoachNum);
                if(ans){
                    System.out.println("Train added Successfully !!");
                }
                else{
                    System.out.println("Try again !!");
                }

            } else if (c==7) {
                System.out.println("Enter Your Motor Number :");
                String MotorNum  = br.readLine();
                System.out.println("Enter Your TransactionDate");
                String TransacDate = br.readLine();
                System.out.println("Enter Your Coach Number");
                String CoachNum = br.readLine();
                System.out.println("Enter Your Train Number");
                String TrainNum = br.readLine();
                boolean ans = TXMmotorDAO.insertTransactionDateInDB(MotorNum,TransacDate,CoachNum,TrainNum);
                if(ans){
                    System.out.println("Transaction-Date added Successfully !!");
                }
                else{
                    System.out.println("Try again !!");
                }
            }
            else if(c==8){
                System.out.println("Enter Motor-Number : ");
                String MotorNum = br.readLine();
                TXMmotorDAO.TransactionDate(MotorNum);
            }
            else if(c==9){
                System.out.println("Enter Train-Number");
                String TrainNum = br.readLine();
                TXMmotorDAO.ShowAllMotorsByTrainnum(TrainNum);
            }
            else if(c==10){
                System.out.println("Enter Coach Number :");
                String coachNum = br.readLine();
                TXMmotorDAO.ShowAllMotorsInCoach(coachNum);
            }
            else if(c==11){
                System.out.println("Enter Your Motor Number :");
                String MotorNum  = br.readLine();
                System.out.println("Enter Your Failure-Date");
                String FailureDate = br.readLine();
                System.out.println("Enter Your New Motor-Number");
                String NewMotorNumReplaced = br.readLine();

                boolean ans = TXMmotorDAO.insertFailureTableDateInDB(MotorNum,FailureDate,NewMotorNumReplaced);
                if(ans){
                    System.out.println("Transaction-Date added Successfully !!");
                }
                else{
                    System.out.println("Try again !!");
                }
            }
            else if(c==17){
                System.out.println("Enter Failed Motor-Number");
                String FailedMotorNum = br.readLine();
                System.out.println("Enter New Motor Number");
                String NewMotorNum = br.readLine();
                boolean ans = TXMmotorDAO.UpadteNewMotorNumber(NewMotorNum,FailedMotorNum);
                if(ans){
                    System.out.println(" added Successfully !!");
                }
                else{
                    System.out.println("Try again !!");
                }
            }
            else if(c==12){
                System.out.println("Enter Motor Number");
                String motorNum = br.readLine();
                TXMmotorDAO.FailedMotordate(motorNum);
            }
            else if(c==13){
                System.out.println("Enter Motor Number");
                String motorNum = br.readLine();
                TXMmotorDAO.ReplacedMotor(motorNum);
            }
            else if (c==14){
                TXMmotorDAO.AllFailureMotors();
            }
            else if (c==15){
                break;
            }
        }

        System.out.println("Thankyou for using My Application");
        System.out.println("Visit Again....");

        }

}
