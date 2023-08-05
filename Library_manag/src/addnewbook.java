
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class addnewbook extends Database implements ActionListener {
    JFrame frame=new JFrame("app");
    JButton add=new JButton("Add Book");
    JButton back=new JButton(" <- Back");
    JTextField bookName=new JTextField();
    JTextField bookQuantity=new JTextField();
    JLabel nameLabel=new JLabel("Enter name of book");
    JLabel quantityLabel=new JLabel("Enter quantity of book");
    JLabel massage=new JLabel("");
    String id;

    addnewbook(String id){
        this.id=id;
    }

    void createAddNewBook(){
        nameLabel.setBounds(50,100,150,25);
        quantityLabel.setBounds(200,100,150,25);
        bookName.setBounds(50,150,100,25);
        bookQuantity.setBounds(200,150,100,25);
        bookQuantity.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed (KeyEvent k){
                if((k.getKeyChar()>='0'&&k.getKeyChar()<='9')||k.getKeyChar()==KeyEvent.VK_BACK_SPACE||k.getKeyChar()==KeyEvent.VK_LEFT||k.getKeyChar()==KeyEvent.VK_RIGHT||k.getKeyChar()==KeyEvent.VK_DELETE){
                    bookQuantity.setEditable(true);
                }
                else{
                    bookQuantity.setEditable(false);
                }
            }
        });
        add.setBounds(150,200,100,25);
        add.addActionListener(this);
        massage.setBounds(150,250,350,25);
        massage.setFont(new Font(null, Font.ITALIC,25));
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        frame.add(back);
        frame.add(massage);
        frame.add(nameLabel);
        frame.add(quantityLabel);
        frame.add(bookName);
        frame.add(bookQuantity);
        frame.add(add);
        back.setFocusable(false);
        add.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Add a Book");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add){
            String book=bookName.getText();
            book.trim();
            if(!book.equals("")){
                String quantity =bookQuantity.getText();
                int q=Integer.parseInt(quantity);
                if(q!=0){
                    if(checkBook(book)){
                        int prev=viewBook(book);
                        updateBook(book,q+prev);
                        massage.setText("Quantity of book updated");
                    }
                    else{
                        addBook(book,q);
                        massage.setText("Book Added");
                    }
                }
                else{
                    massage.setText("Enter quantity greater than 0");
                }
            }
            else{
                massage.setText("Enter a valid name for book");
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            librain_menu lm=new librain_menu(id);
            lm.librainmenu();
        }
    }
}
