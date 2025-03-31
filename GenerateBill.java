package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;

        setSize(500, 800);
        setLocation(550, 30);

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); // Setting background to black

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY); // Setting panel background to dark gray

        JLabel heading = new JLabel("Generate Bill");
        heading.setForeground(Color.WHITE); // Setting heading text color to white
        JLabel meternumber = new JLabel(meter);
        meternumber.setForeground(Color.WHITE); // Setting meter number text color to white

        cmonth = new Choice();

        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        cmonth.setForeground(Color.black);

        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));
        area.setForeground(Color.WHITE); // Setting text area text color to white
        area.setBackground(Color.BLACK); // Setting text area background to black

        JScrollPane pane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        bill.setBackground(Color.WHITE); // Setting button background to white
        bill.setForeground(Color.BLACK); // Setting button text color to black

        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        panel.setBackground(Color.DARK_GRAY);
        add(panel, BorderLayout.NORTH);

        add(pane, BorderLayout.CENTER);
        add(bill, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();

            String month = cmonth.getSelectedItem();

            area.setText("\tChaudhary Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF " + month + ", 2022\n\n\n");
            area.append("\n---------------------------------------------------\n");

            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address             : " + rs.getString("address"));
                area.append("\n    City                 : " + rs.getString("city"));
                area.append("\n    State                : " + rs.getString("state"));
                area.append("\n    Email                : " + rs.getString("email"));
                area.append("\n    Phone                 : " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from meter_info where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\n    Meter Location: " + rs.getString("meter_location"));
                area.append("\n    Meter Type:     " + rs.getString("meter_type"));
                area.append("\n    Phase Code:        " + rs.getString("phase_code"));
                area.append("\n    Bill Type:          " + rs.getString("bill_type"));
                area.append("\n    Days:                " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                area.append("\n");
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent:     " + rs.getString("meter_rent")); // Corrected typo: changed "cost_per_unit" to "meter_rent"
                area.append("\n    Service Charge:        " + rs.getString("service_charge"));
                area.append("\n    Service Tax:          " + rs.getString("service_tax")); // Corrected typo: changed "service_charge" to "service_tax"
                area.append("\n    Swacch Bharat Cess:                " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: " + rs.getString("fixed_tax"));
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' and month='" + month + "'");

            if (rs.next()) {
                area.append("\n");
                area.append("\n    Current Month: " + rs.getString("month"));
                area.append("\n    Units Consumed:     " + rs.getString("units"));
                area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
