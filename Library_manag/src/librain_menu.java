import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class librain_menu extends Database implements ActionListener {
    JFrame frame = new JFrame("Lib Menu");
    JButton viewbook = new JButton("View books");
    JButton addbook = new JButton("Add a book");

    JButton viewissue = new JButton("View issued books");
    JButton returnbook = new JButton("Return book");
    JButton back = new JButton("Signout");
    String id;

    librain_menu(String id){
        this.id=id;
    }
    void librainmenu() {
        viewbook.setBounds(125, 100, 150, 25);
        viewbook.addActionListener(this);
        viewissue.setBounds(125, 135, 150, 25);
        viewissue.addActionListener(this);
        addbook.setBounds(125, 170, 150, 25);
        addbook.addActionListener(this);
        back.setBounds(320, 0, 100, 25);
        back.addActionListener(this);
        returnbook.setBounds(125, 205, 150, 25);
        returnbook.addActionListener(this);
        frame.add(returnbook);
        frame.add(back);
        frame.add(viewbook);
        frame.add(viewissue);
        frame.add(addbook);
        back.setFocusable(false);
        addbook.setFocusable(false);
        returnbook.setFocusable(false);
        viewbook.setFocusable(false);
        viewissue.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Librarian menu");
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            librain_login g= new librain_login();
            g.librainlogin();
        }
        if(e.getSource()==viewbook){
            frame.dispose();
            view_books vb=new view_books(id,"L");
            vb.createViewBooks();
        }
        if(e.getSource()==viewissue){
            frame.dispose();
            viewissuedlib vl=new viewissuedlib(id);
            vl.viewissuedbooks();
        }
        if(e.getSource()==returnbook){
            frame.dispose();
            returnissue rb=new returnissue(id);
            rb.returnissuebooks();
        }
        if(e.getSource()==addbook){
            frame.dispose();
            addnewbook ab=new addnewbook(id);
            ab.createAddNewBook();
        }
    }
}


