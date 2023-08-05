import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class librain_login extends Database implements ActionListener {
    JFrame frame=new JFrame("Librarian login");
    JButton loginbutton=new JButton("Login");
    JButton resetbutton=new JButton("Reset");
    JButton back=new JButton(" <- Back");
    JTextField userid=new JTextField();
    JPasswordField userpass=new JPasswordField();
    JLabel useridlab=new JLabel("User id: ");
    JLabel userpasslab=new JLabel("User pass: ");
    JLabel massage=new JLabel("");

    void librainlogin(){
        useridlab.setBounds(50,100,75,25);
        userpasslab.setBounds(50,150,75,25);
        massage.setBounds(100,250,350,25);
        massage.setFont(new Font(null, Font.ITALIC,25));
        userid.setBounds(125,100,200,25);
        userpass.setBounds(125,150,200,25);
        loginbutton.setBounds(125,200,100,25);
        loginbutton.addActionListener(this);
        resetbutton.setBounds(225,200,100,25);
        resetbutton.addActionListener(this);
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        frame.add(back);
        frame.add(useridlab);
        frame.add(userpasslab);
        frame.add(massage);
        frame.add(userid);
        frame.add(userpass);
        frame.add(loginbutton);
        frame.add(resetbutton);
        back.setFocusable(false);
        loginbutton.setFocusable(false);
        resetbutton.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Librarian login");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetbutton){
            userid.setText("");
            userpass.setText("");
        }
        if(e.getSource()==loginbutton){
            String id=userid.getText();
            String pass=String.valueOf(userpass.getPassword());
            if(!id.equals("")||!pass.equals("")){
                if(login(id,pass,"L")){
                    massage.setForeground(Color.green);
                    massage.setText("Login successful");
                    frame.dispose();
                    librain_menu lm=new librain_menu(id);
                    lm.librainmenu();
                }
                else{
                    massage.setForeground(Color.red);
                    massage.setText("Invalid Credentials");
                }
            }
            else{
                massage.setForeground(Color.red);
                massage.setText("Invalid Credentials");
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            login l=new login();
            l.createlogin();
        }
    }
}