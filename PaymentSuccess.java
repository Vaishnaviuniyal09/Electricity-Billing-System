package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class PaymentSuccess extends JFrame {
    
    PaymentSuccess(String meter, String month, String units, String totalbill) {
        super("Payment Successful");
        setLayout(null);
        setBounds(450, 150, 500, 300);
        
        Color backgroundColor = new Color(45, 45, 45); // Dark background color
        Color textColor = Color.WHITE; // White text color

        JLabel heading = new JLabel("Payment Successful");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(100, 20, 300, 30);
        heading.setForeground(textColor);
        add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number: ");
        lblmeter.setBounds(50, 80, 200, 20);
        lblmeter.setForeground(textColor);
        add(lblmeter);
        
        JLabel meternumber = new JLabel(meter);
        meternumber.setBounds(200, 80, 200, 20);
        meternumber.setForeground(textColor);
        add(meternumber);
        
        JLabel lblmonth = new JLabel("Month: ");
        lblmonth.setBounds(50, 110, 200, 20);
        lblmonth.setForeground(textColor);
        add(lblmonth);
        
        JLabel monthlabel = new JLabel(month);
        monthlabel.setBounds(200, 110, 200, 20);
        monthlabel.setForeground(textColor);
        add(monthlabel);
        
        JLabel lblunits = new JLabel("Units Consumed: ");
        lblunits.setBounds(50, 140, 200, 20);
        lblunits.setForeground(textColor);
        add(lblunits);
        
        JLabel unitslabel = new JLabel(units);
        unitslabel.setBounds(200, 140, 200, 20);
        unitslabel.setForeground(textColor);
        add(unitslabel);
        
        JLabel lbltotalbill = new JLabel("Total Bill: ");
        lbltotalbill.setBounds(50, 170, 200, 20);
        lbltotalbill.setForeground(textColor);
        add(lbltotalbill);
        
        JLabel totalbilllabel = new JLabel(totalbill);
        totalbilllabel.setBounds(200, 170, 200, 20);
        totalbilllabel.setForeground(textColor);
        add(totalbilllabel);
        
        JButton close = new JButton("Close");
        close.setBounds(200, 220, 100, 25);
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        close.addActionListener(e -> setVisible(false));
        add(close);
        
        getContentPane().setBackground(backgroundColor); // Dark background color
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PaymentSuccess("", "", "", "");
    }
}
