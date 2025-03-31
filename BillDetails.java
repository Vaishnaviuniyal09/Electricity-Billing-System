package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.table.JTableHeader;

public class BillDetails extends JFrame {

    BillDetails(String meter) {
        super("Bill Details");

        setSize(700, 650);
        setLocation(400, 150);

        // Set background color to black
        getContentPane().setBackground(Color.BLACK);

        JTable table = new JTable();
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.GRAY); // Set grid color to a lighter shade

        // Set table header colors
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(Color.WHITE);
        
        try {
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        sp.getViewport().setBackground(Color.BLACK); // Set scroll pane viewport background to black
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}
