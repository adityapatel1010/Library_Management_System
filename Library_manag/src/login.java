import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class login implements ActionListener {
    JFrame frame=new JFrame("login");
    JButton guestButton=new JButton("Login as Guest");
    JButton librainButton=new JButton("Login as Librarian");
    JButton createButton=new JButton("Create a new account");
    void createlogin(){

        guestButton.setBounds(105,130,200,25);
        guestButton.addActionListener(this);
        guestButton.setFocusable(false);
        librainButton.setBounds(105,165,200,25);
        librainButton.addActionListener(this);
        librainButton.setFocusable(false);
        createButton.setBounds(105,200,200,25);
        createButton.addActionListener(this);
        createButton.setFocusable(false);

        frame.add(guestButton);
        frame.add(librainButton);
        frame.add(createButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Login");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==librainButton){
            frame.dispose();
            librain_login n=new librain_login();
            n.librainlogin();
        }
        if(e.getSource()==guestButton){
            frame.dispose();
            guest_login gl=new guest_login();
            gl.create_guest_login();
        }
        if(e.getSource()==createButton){
            frame.dispose();
            create_account ca=new create_account();
            ca.createacc();
        }
    }
}
