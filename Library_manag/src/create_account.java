import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class create_account extends Database implements ActionListener {
    JFrame frame=new JFrame("Create account");
    JButton createaccbutton=new JButton("Create Account");
    JButton resetbutton=new JButton("Reset");
    JTextField userid=new JTextField();
    JPasswordField userpass=new JPasswordField();
    JPasswordField userconpass=new JPasswordField();
    JLabel useridlab=new JLabel("User id: ");
    JLabel message=new JLabel("");
    JButton back=new JButton("<- Back");
    JLabel userpasslab=new JLabel("User pass: ");
    JLabel conpass=new JLabel("Confirm: ");
    void createacc(){
        useridlab.setBounds(50,100,75,25);
        userpasslab.setBounds(50,150,75,25);
        userid.setBounds(125,100,200,25);
        userpass.setBounds(125,150,200,25);
        userconpass.setBounds(125,200,200,25);
        conpass.setBounds(50,200,200,25);
        createaccbutton.setBounds(80,250,150,25);
        message.setBounds(75,300,350,25);
        message.setFont(new Font(null, Font.ITALIC,20));
        createaccbutton.addActionListener(this);
        resetbutton.setBounds(230,250,150,25);
        resetbutton.addActionListener(this);
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        frame.add(back);
        frame.add(useridlab);
        frame.add(userpasslab);
        frame.add(conpass);
        frame.add(userconpass);
        frame.add(userid);
        frame.add(userpass);
        frame.add(createaccbutton);
        frame.add(resetbutton);
        frame.add(message);
        back.setFocusable(false);
        createaccbutton.setFocusable(false);
        resetbutton.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Create account");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetbutton){
            userid.setText("");
            userpass.setText("");
            userconpass.setText("");
        }
        if(e.getSource()==createaccbutton){
            String id=userid.getText();
            id.trim();
            String pass=String.valueOf(userpass.getPassword());
            pass.trim();
            String cpass=String.valueOf(userconpass.getPassword());
            cpass.trim();
            if(pass.equals(cpass)) {
                if (!super.login(id, "G")) {
                    super.login_create(id, pass, "G");
                    message.setText("Account Created");
                } else {
                    message.setText("ID Already Exits");
                }
            }
            else{
                message.setText("Password doesn't match");
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            login l=new login();
            l.createlogin();
        }
    }
}
