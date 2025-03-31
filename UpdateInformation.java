package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener {

    JTextField tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton update, cancel;
    String meter;
    JLabel name;

    UpdateInformation(String meter) {
        super("View Information");
        this.meter = meter;
        setBounds(300, 150, 1050, 450);
        getContentPane().setBackground(new Color(34, 34, 34)); // Dark background
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setForeground(Color.WHITE); // White text
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 70, 100, 20);
        lblname.setForeground(Color.WHITE); // White text
        add(lblname);

        name = new JLabel("");
        name.setBounds(230, 70, 200, 25);
        name.setForeground(Color.WHITE); // White text
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30, 110, 100, 25);
        lblmeternumber.setForeground(Color.WHITE); // White text
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(230, 110, 200, 25);
        meternumber.setForeground(Color.WHITE); // White text
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 150, 100, 20);
        lbladdress.setForeground(Color.WHITE); // White text
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(230, 150, 200, 25);
        tfaddress.setBackground(new Color(50, 50, 50)); // Darker background
        tfaddress.setForeground(Color.WHITE); // White text
        add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        lblcity.setForeground(Color.WHITE); // White text
        add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(230, 190, 200, 25);
        tfcity.setBackground(new Color(50, 50, 50)); // Darker background
        tfcity.setForeground(Color.WHITE); // White text
        add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230, 100, 20);
        lblstate.setForeground(Color.WHITE); // White text
        add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(230, 230, 200, 25);
        tfstate.setBackground(new Color(50, 50, 50)); // Darker background
        tfstate.setForeground(Color.WHITE); // White text
        add(tfstate);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30, 270, 100, 20);
        lblemail.setForeground(Color.WHITE); // White text
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(230, 270, 200, 25);
        tfemail.setBackground(new Color(50, 50, 50)); // Darker background
        tfemail.setForeground(Color.WHITE); // White text
        add(tfemail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 310, 100, 20);
        lblphone.setForeground(Color.WHITE); // White text
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(230, 310, 200, 25);
        tfphone.setBackground(new Color(50, 50, 50)); // Darker background
        tfphone.setForeground(Color.WHITE); // White text
        add(tfphone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70, 360, 100, 25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230, 360, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '" + address + "', city = '" + city + "', state = '" + state + "', email = '" + email + "', phone = '" + phone + "' where meter_no = '" + meter + "'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
