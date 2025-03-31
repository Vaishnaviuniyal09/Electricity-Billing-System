package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;

    ViewInformation(String meter) {
        super("View Information");
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.DARK_GRAY); // Dark background
        setLayout(null);

        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setForeground(Color.WHITE); // White text
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        lblname.setForeground(Color.WHITE); // White text
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        name.setForeground(Color.WHITE); // White text
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70, 140, 100, 20);
        lblmeternumber.setForeground(Color.WHITE); // White text
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        meternumber.setForeground(Color.WHITE); // White text
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        lbladdress.setForeground(Color.WHITE); // White text
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        address.setForeground(Color.WHITE); // White text
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        lblcity.setForeground(Color.WHITE); // White text
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        city.setForeground(Color.WHITE); // White text
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        lblstate.setForeground(Color.WHITE); // White text
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        state.setForeground(Color.WHITE); // White text
        add(state);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500, 140, 100, 20);
        lblemail.setForeground(Color.WHITE); // White text
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        email.setForeground(Color.WHITE); // White text
        add(email);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 200, 100, 20);
        lblphone.setForeground(Color.WHITE); // White text
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        phone.setForeground(Color.WHITE); // White text
        add(phone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
