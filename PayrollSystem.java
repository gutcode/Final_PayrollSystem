/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author martin
 */
public class PayrollSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Employee> arrEmp = new ArrayList<Employee>();
        String varCont = "N";
        byte menuOption = 0;
        do{
                menuOption = showMenu();
                switch (menuOption) {
                    case 1:
                        FullTime ft;
                        ft = readNewFullTime();
                        addEmployee(arrEmp, ft); // add full time employee to arraylist
                        break;
                    case 2:
                        PartTime pt;
                        pt = readNewPartTime();
                        addEmployee(arrEmp, pt); // add part time employee to arraylist
                        break;
                    case 3:
                        calcPayroll(arrEmp);
                        break;
                    default:
                        break;
                }
             } while (menuOption != 4);
        
        }
    
    public static FullTime readNewFullTime (){
               /* ************************************************************/
               /* this methods CREATES and POPULATES Employee objects */
               /* Parameters: None */
               /* Return values: new FullTime
               /* ************************************************************/
               int id=0;
               String name=null;
               double sal=0.0;
               double hourAndHalf = 0.0; //overtime
               Scanner kbd = new Scanner(System.in);
               System.out.print("Enter Id: ");        id = kbd.nextInt();
               System.out.print("\nEnter Name: ");            name = kbd.next();
               System.out.print("\nEnter Salary: ");          sal = kbd.nextDouble();
               System.out.print("\nEnter Bonus: ");           hourAndHalf = kbd.nextDouble();
               
               FullTime ft1 = null;
               ft1 = new FullTime(id,name, sal, hourAndHalf, getVehicle());
               
               
               return ft1;
        
    }
    
    public static PartTime readNewPartTime (){
            /* *******************************************************/
            /* this methods CREATES and POPULATES Employee objects */
            /* Parameters: None */
            /* Return values: new PartTime
            /* *******************************************************/
            int id=0;
            String name=null;
            double rate=0.0;
            double hoursWorked = 0.0; // overtime
                    
            Scanner kbd = new Scanner(System.in);
            System.out.print("Enter Id: ");          id = kbd.nextInt();
            System.out.print("\nEnter Name: ");           name = kbd.next();
            System.out.print("\nEnter Hourly Rate: ");    rate = kbd.nextDouble();
            System.out.print("\nEnter Number of Hours Worked: ");     hoursWorked = kbd.nextDouble();
            
            Vehicle v1 = getVehicle();
            PartTime pt1 = null;
            pt1 = new PartTime(id,name, rate, hoursWorked,v1);
            
            return pt1;
            
    }
    
    public static byte showMenu ()
    {
            byte menuOption = 0;
            Scanner kbd = new Scanner(System.in);
            
            System.out.println(""
                    + "/* *****************************************************/"
                    + "\n/* 1. Add FullTime                                   */"
                    + "\n/* 2. Add PartTime                                   */"
                    + "\n/* 3. Calculate Payroll                              */"
                    + "\n/* 4. Exit                                           */"
                    + "\n/* ***************************************************/");
            System.out.print("Input: ");         menuOption = kbd.nextByte();
            
            return menuOption;
            
    }  
    
    public static Vehicle getVehicle(){ 
                /*
                * creates and returns a Vehicle Object if "Y" Else returns null
                */
                Scanner kbd = new Scanner(System.in);
                String hasVehicle = "N";
                
                System.out.print("\nDoes this employee have a vehicle? Y/N : \nInput : ");
                hasVehicle = kbd.next();
                
                if (hasVehicle.equalsIgnoreCase("Y")){
                        // create and populate object Vehicle
                        System.out.print("\nEnter plate number: ");      String auxPlate = kbd.next();
                        System.out.print("\nEnter vehicle colour: ");    String auxColour = kbd.next();
                        return (new Vehicle(auxPlate, auxColour));
                }
                else{ // employee does not have a vehicle
                    return (null);
                }   
}

    public static void addEmployee(ArrayList<Employee> pArrEmp, Employee pEmp)
    {

            /* this method add one employee e, to the aaraylist arrEmp */
            pArrEmp.add(pEmp);
    } 

    public static void calcPayroll(ArrayList<Employee> pArrEmp){
        double totalCompanyPay = 0.0;
        double individualPay;

            // calculate salary - manipulating array only
            for (int i=0; i<pArrEmp.size(); i++){
                System.out.println("\n*********************\n");
                individualPay = (pArrEmp.get(i)).calculatePay();
                Vehicle v = (pArrEmp.get(i)).getVehicle();
                String hasVehicle;
                // check employee has a vehicle or not
                if (v == null)
                    hasVehicle = "No";
                else
                    hasVehicle = "Yes";

                System.out.println("Employee Name: " + (pArrEmp.get(i)).getName());
                System.out.println("Has Vehicle:  " + hasVehicle);

                if (v !=null){
                    System.out.println("Plate Number: " + v.getPlateNumber());
                    System.out.println("Colour: " + v.getColour());
                }

                System.out.println("Take Home Pay:" + individualPay);

                totalCompanyPay = totalCompanyPay + individualPay;
        }
    System.out.print("--------------\nTotal payroll of the company: " + totalCompanyPay + "\n -----\n");
    }

}
