import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuiFirstPage implements ActionListener {
    JButton button,button2,button3;
    public GuiFirstPage(){

        JLabel labelID = new JLabel();
        labelID.setText("User Id");
        labelID.setHorizontalAlignment(JLabel.CENTER);
        labelID.setVerticalAlignment(JLabel.CENTER);
        labelID.setForeground(Color.cyan);

        button = new JButton("Create Account");
        button.setBounds(50,50,120,25);
        button.addActionListener(this);
        button.setBackground(Color.black);
        button.setFont(new Font("Comic Sans",Font.ITALIC,15));
        button.setForeground(Color.cyan);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setFocusable(false);

        button2 = new JButton("Log In");
        button2.setBounds(50,77,120,25);
        button2.addActionListener(this);
        button2.setBackground(Color.black);
        button2.setFont(new Font("Comic Sans",Font.ITALIC,15));
        button2.setForeground(Color.cyan);
        button2.setBorder(BorderFactory.createEtchedBorder());
        button2.setFocusable(false);

        button3 = new JButton("Exit ");
        button3.setBounds(50,104,120,25);
        button3.addActionListener(this);
        button3.setBackground(Color.black);
        button3.setFont(new Font("Comic Sans",Font.ITALIC,15));
        button3.setForeground(Color.cyan);
        button3.setBorder(BorderFactory.createEtchedBorder());
        button3.setFocusable(false);


        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,600);
        myFrame.setLayout(null);
        myFrame.getContentPane().setBackground(Color.DARK_GRAY);
        myFrame.add(button);
        myFrame.add(button2);
        myFrame.add(button3);
        myFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PossibleMain2 ps = new PossibleMain2();


        if(e.getSource()==button){
            System.out.println("Create Account");
            try {
                ps.createAccount();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        else if(e.getSource()==button2){
            System.out.println("Log In");
            try {
                ps.LogIn();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        else if(e.getSource()==button3){
            System.out.println("Exit ! ");
            ps.exit();
        }
    }
}
