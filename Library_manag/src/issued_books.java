import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class issued_books extends Database implements ActionListener {
    JFrame frame=new JFrame("Issue a book");
    JLabel quantity=new JLabel("");
    JLabel result=new JLabel("");
    JComboBox comboBox;
    JButton new_issuedbook=new JButton("Issue the book");
    String id;
    JButton back=new JButton(" <- Back");
    int x=0,temp;

    issued_books(String id){
        this.id=id;
    }
    void createIssued_books(){
        ResultSet s=fetchBook();
        String lib_book[]=new String[size+1];
        int i=0;
        lib_book[i]="";
        i++;
        while(true){
            try {
                if (!s.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                lib_book[i]= s.getString(1);
                i++;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        comboBox=new JComboBox<>(lib_book);
        new_issuedbook.setBounds(125,150,150,25);
        new_issuedbook.addActionListener(this);
        comboBox.setBounds(50,50,100,25);
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        comboBox.addActionListener(this);
        quantity.setBounds(200,50,150,25);
        result.setBounds(150,250,150,25);
        frame.add(new_issuedbook);
        frame.add(comboBox);
        frame.add(back);
        frame.add(quantity);
        frame.add(result);
        new_issuedbook.setFocusable(false);
        back.setFocusable(false);
        comboBox.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Issue a book");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboBox){
            if(!String.valueOf(comboBox.getSelectedItem()).equals("")){
                x=viewBook(String.valueOf(comboBox.getSelectedItem()));
                quantity.setText("The quantity of book is : "+x);
            }
            else{
                quantity.setText("");
            }
        }
        if(e.getSource()==new_issuedbook){
            if(String.valueOf(comboBox.getSelectedItem()).equals("")){
                result.setText("No book selected");
            }
            else if(x==0){
                result.setText("Book not available");
            }
            else{
                temp=issueAdd(id,String.valueOf(comboBox.getSelectedItem()),1);
                if(temp>0){
                    result.setText("Book Issued");
                    comboBox.setSelectedIndex(0);
                    quantity.setText("");
                }
                else if(temp==-1){
                    result.setText("Book already issued");
                }
                else if(temp==-2){
                    result.setText("Insufficient Books");
                }
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            guestmenu gm=new guestmenu(id);
            gm.createGuestmenu();
        }
    }
}

