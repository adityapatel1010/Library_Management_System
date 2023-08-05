import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guestmenu extends Database implements ActionListener {
    JFrame frame=new JFrame("Guest Menu");
    JButton viewbook=new JButton("View books");
    JButton issue_book=new JButton("Issue a books");
    JButton issuedbook=new JButton("View Issued books");
    JButton back=new JButton("Signout");
    String id;
    guestmenu(String id){
        this.id=id;
    }
    void createGuestmenu(){
        viewbook.setBounds(125,120,150,25);
        viewbook.addActionListener(this);
        issuedbook.setBounds(125,155,150,25);
        issuedbook.addActionListener(this);
        issue_book.setBounds(125,190,150,25);
        issue_book.addActionListener(this);
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        frame.add(back);
        frame.add(viewbook);
        frame.add(issuedbook);
        frame.add(issue_book);
        back.setFocusable(false);
        viewbook.setFocusable(false);
        issuedbook.setFocusable(false);
        issue_book.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Guest Menu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==issue_book){
            frame.dispose();
            issued_books ib=new issued_books(id);
            ib.createIssued_books();
        }
        if(e.getSource()==viewbook){
            frame.dispose();
            view_books vb=new view_books(id,"G");
            vb.createViewBooks();
        }
        if(e.getSource()==issuedbook){
            frame.dispose();
            viewissuedguest vb=new viewissuedguest(id);
            vb.viewissuedbooks();
        }
        if(e.getSource()==back){
            frame.dispose();
            guest_login gl=new guest_login();
            gl.create_guest_login();
        }
    }
}

