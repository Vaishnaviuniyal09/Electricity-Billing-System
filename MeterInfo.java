package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;

    MeterInfo(String meternumber) {
        super("Enter Meter Details");
        this.meternumber = meternumber;

        setSize(700, 500);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close operation

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(9, 25, 31)); // Dark background color
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(Color.WHITE); // White text color
        p.add(heading);

        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setForeground(Color.WHITE); // White text color
        p.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        lblmeternumber.setForeground(Color.WHITE); // White text color
        p.add(lblmeternumber);

        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        lblmeterno.setForeground(Color.WHITE); // White text color
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 200, 20);
        meterlocation.setForeground(Color.BLACK); // Set text color to black
        p.add(meterlocation);

        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        lbladdress.setForeground(Color.WHITE); // White text color
        p.add(lbladdress);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 200, 20);
        metertype.setForeground(Color.BLACK); // Set text color to black
        p.add(metertype);

        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        lblcity.setForeground(Color.WHITE); // White text color
        p.add(lblcity);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240, 200, 200, 20);
        phasecode.setForeground(Color.BLACK); // Set text color to black
        p.add(phasecode);

        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        lblstate.setForeground(Color.WHITE); // White text color
        p.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240, 240, 200, 20);
        billtype.setForeground(Color.BLACK); // Set text color to black
        p.add(billtype);

        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        lblemail.setForeground(Color.WHITE); // White text color
        p.add(lblemail);

        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(240, 280, 100, 20);
        lblemails.setForeground(Color.WHITE); // White text color
        p.add(lblemails);

        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        lblphone.setForeground(Color.WHITE); // White text color
        p.add(lblphone);

        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setBounds(240, 320, 500, 20);
        lblphones.setForeground(Color.WHITE); // White text color
        p.add(lblphones);

        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel"); // Cancel button
        cancel.setBounds(340, 390, 100, 25);
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

        getContentPane().setBackground(Color.white); // Dark background for content pane

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values('" + meter + "', '" + location + "', '" + type + "', '" + code + "', '" + typebill + "', '" + days + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
