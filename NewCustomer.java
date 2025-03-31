package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;

    NewCustomer() {
        super("Register a Customer");
        setSize(700, 500);
        setLocation(400, 200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(9, 25, 31));
        add(p);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(Color.WHITE);
        p.add(heading);

        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setForeground(Color.WHITE);
        p.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(240, 80, 200, 25);
        p.add(tfname);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100, 120, 100, 25);
        lblmeterno.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lblmeterno);

        lblmeter = new JLabel("");
        lblmeter.setBounds(240, 120, 100, 25);
        lblmeter.setForeground(Color.WHITE); // Setting color for lblmeter
        p.add(lblmeter);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblmeter.setText("" + Math.abs(number));

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 25);
        lbladdress.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(240, 160, 200, 25);
        p.add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 200, 100, 25);
        lblcity.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(240, 200, 200, 25);
        p.add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 240, 100, 25);
        lblstate.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(240, 240, 200, 25);
        p.add(tfstate);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 280, 100, 25);
        lblemail.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(240, 280, 200, 25);
        p.add(tfemail);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100, 320, 100, 25);
        lblphone.setForeground(Color.WHITE); // Correct label for setting color
        p.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(240, 320, 200, 25);
        p.add(tfphone);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 390, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setLayout(new BorderLayout());

        add(p, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            String query1 = "insert into customer values('" + name + "', '" + meter + "', '" + address + "', '" + city + "', '" + state + "', '" + email + "', '" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);

                // new frame
                new MeterInfo(meter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
