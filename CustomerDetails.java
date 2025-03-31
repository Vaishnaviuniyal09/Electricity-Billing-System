package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    CustomerDetails() {
        super("Customer Details");

        setSize(1200, 650);
        setLocation(200, 150);

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Set look and feel to cross-platform Java look and feel
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            // Set custom UI properties for a black and white theme
            UIManager.put("Table.background", Color.white);
            UIManager.put("Table.foreground", Color.black);
            UIManager.put("Table.selectionBackground", Color.black);
            UIManager.put("Table.selectionForeground", Color.white);
            UIManager.put("Table.gridColor", Color.black);
            UIManager.put("TableHeader.background", Color.black);
            UIManager.put("TableHeader.foreground", Color.white);
            UIManager.put("Label.foreground", Color.black);
            UIManager.put("Button.background", Color.white);
            UIManager.put("Button.foreground", Color.black);

            // Create and show your JFrame
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new CustomerDetails();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
