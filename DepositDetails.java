package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    DepositDetails() {
        super("Deposit Details");

        setSize(700, 700);
        setLocation(400, 100);

        setLayout(null);
        getContentPane().setBackground(Color.BLACK); // Setting background to black

        // Label for "Search By Meter Number"
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        lblmeternumber.setForeground(Color.white); // Setting foreground (text) color to white
        add(lblmeternumber);

        // Choice for meter numbers
        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 25);
        meternumber.setBackground(Color.BLACK);
        meternumber.setForeground(Color.white);
        add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Label for "Search By Month"
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        lblmonth.setForeground(Color.WHITE); // Setting foreground (text) color to white
        add(lblmonth);

        // Choice for months
        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 25);
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
        cmonth.setBackground(Color.BLACK);
        add(cmonth);

        // JTable for displaying data
        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);

        // Button for searching data
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 25);
        search.addActionListener(this);
        add(search);

        // Button for printing data
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 25);
        print.addActionListener(this);
        add(print);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from bill where meter_no = '" + meternumber.getSelectedItem() + "' and month = '" + cmonth.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Set look and feel to Nimbus with dark theme
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Override default UIManager properties for a dark theme
            UIManager.put("control", new Color(128, 128, 128)); // Gray control background
            UIManager.put("nimbusBase", new Color(18, 30, 49)); // Dark base color
            UIManager.put("nimbusFocus", new Color(115, 164, 209)); // Focus color
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49)); // Light background
            UIManager.put("nimbusDarkBackground", new Color(18, 30, 49)); // Dark background
            UIManager.put("nimbusSelectionBackground", new Color(115, 164, 209)); // Selection background
            UIManager.put("text", Color.WHITE); // Text color
            UIManager.put("nimbusSelection", new Color(18, 30, 49)); // Selection color
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128)); // Disabled text color

            // Create and show your JFrame
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new DepositDetails();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
