/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author Puri
 */
public class CRM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Login frame = new Login();
        //Product_details frame = new Product_details();
        Home frame = new Home();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
        
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        StringBuilder sb = new StringBuilder();
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", "root", "1691991");
            System.out.println(con.toString());
            String sql = "SELECT * FROM company_loan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                StringBuilder rowString = new StringBuilder();
                ArrayList row = new ArrayList(columns);
                        
                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                    System.out.println(rs.getObject(i).toString());
                    if (i == columns){
                        Date date = rs.getDate(i);
                        Date now = new Date();
                        rowString.append(date.toString());
                        rowString.append("\n");
                        if (TimeUnit.MILLISECONDS.toDays(now.getTime() - date.getTime()) < 5)
                            sb.append(rowString);
                        
                    }else {
                        rowString.append(rs.getObject(i).toString() + " | ");
                    }
                }

                data.add( row );
            }
            JOptionPane.showMessageDialog(null,sb );
            System.out.println("DONE");
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
