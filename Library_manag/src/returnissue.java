import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class returnissue extends Database implements ActionListener{
    JFrame frame=new JFrame("Guest Menu");
    JButton back=new JButton("<- Back");
    JButton ret=new JButton("Return");
    String issueid[];

    String issuebook[];
    JComboBox user;

    JComboBox user_books;

    JLabel message=new JLabel("");
    String id;

    returnissue(String id){
        this.id=id;
    }
    void combouser(){
        ResultSet s=issueUnique();
        issueid=new String[size+1];
        int i=0;
        issueid[i]="";
        i++;
        while(true){
            try {
                if (!s.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                issueid[i]= s.getString(1);
                i++;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        user=new JComboBox<>(issueid);
        frame.add(user);
        user.setBounds(50,75,100,25);
        user.addActionListener(this);
    }
    void returnissuebooks(){
        combouser();
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        ret.setBounds(150,125,150,25);
        ret.addActionListener(this);

        message.setBounds(150,175,150,25);
        frame.add(message);
        frame.add(back);
        frame.add(ret);
        back.setFocusable(false);
        ret.setFocusable(false);
        user.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Return Book");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            librain_menu lm=new librain_menu(id);
            lm.librainmenu();
        }
        if(e.getSource()==user){
            message.setText("");
            if(!String.valueOf(user.getSelectedItem()).equals("")){
                ResultSet s=super.issue(String.valueOf(user.getSelectedItem()));
                issuebook=new String[size+1];
                int i=0;
                issuebook[i]= "";
                i++;
                while(true){
                    try {
                        if (!s.next()) break;
                    } catch (SQLException m) {
                        throw new RuntimeException(m);
                    }
                    try {
                        issuebook[i]= s.getString(1);
                        i++;
                    } catch (SQLException m) {
                        throw new RuntimeException(m);
                    }
                }
                user_books=new JComboBox<>(issuebook);
                user_books.setBounds(200,75,100,25);
                user_books.addActionListener(this);
                user_books.setVisible(true);
                frame.add(user_books);
                frame.repaint();
            }
            else{
                if(user_books.isVisible()){
                    user_books.setVisible(false);
                    user_books.remove(user_books);
                }
            }
        }
        if(e.getSource()==ret){
            if(!String.valueOf(user.getSelectedItem()).equals("")&&!String.valueOf(user_books.getSelectedItem()).equals("")){
                issueRemove(String.valueOf(user.getSelectedItem()),String.valueOf(user_books.getSelectedItem()));
                message.setText("Book returned");
                message.setText("");
                user.setSelectedIndex(0);
                user_books.setSelectedIndex(0);
                frame.remove(user);
                combouser();
                frame.remove(user_books);
            }
            else{
                message.setText("Select item from drop-box");
            }
        }
    }
}
