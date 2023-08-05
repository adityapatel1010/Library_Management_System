
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class guest_login extends Database implements ActionListener {
    JFrame frame=new JFrame("app");
    JButton loginbutton=new JButton("Login");
    JButton resetbutton=new JButton("Reset");
    JButton back=new JButton(" <- Back");
    JTextField userid=new JTextField();
    JPasswordField userpass=new JPasswordField();
    JLabel useridlab=new JLabel("User id: ");
    JLabel userpasslab=new JLabel("Password : ");
    JLabel massage=new JLabel("");

    void create_guest_login(){
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
        loginbutton.setFocusable(false);
        resetbutton.setFocusable(false);
        back.setFocusable(false);
        frame.add(back);
        frame.add(useridlab);
        frame.add(userpasslab);
        frame.add(massage);
        frame.add(userid);
        frame.add(userpass);
        frame.add(loginbutton);
        frame.add(resetbutton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Guest login");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetbutton){
            userid.setText("");
            userpass.setText("");
        }
        if(e.getSource()==loginbutton){
            String id=userid.getText();
            String pass=String.valueOf(userpass.getPassword());
            if(!id.equals("")&&!pass.equals("")){
                if(super.login(id,pass,"G")){
                    massage.setForeground(Color.green);
                    massage.setText("Login successful");
                    frame.repaint();
                    frame.dispose();
                    guestmenu gm=new guestmenu(id);
                    gm.createGuestmenu();
                }
                else{
                    massage.setForeground(Color.red);
                    massage.setText("INVALID CREDENTIALS");
                }
            }
            else{
                massage.setForeground(Color.red);
                massage.setText("INVALID CREDENTIALS");
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            login l=new login();
            l.createlogin();
        }
    }
}
