import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.*;

public class view_books extends Database implements ActionListener {
    JFrame frame=new JFrame("View Books");
    JLabel result[];
    JLabel  header=new JLabel();
//    JComboBox comboBox;
    JButton view=new JButton("View books");
    JButton back=new JButton(" <- Back");
    String id;
    String type;

    view_books(String id,String type){
        this.id=id;
        this.type=type;
    }

    void createViewBooks(){
        view.setBounds(150,50,150,25);
        view.addActionListener(this);
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        header.setBounds(50,100,200,25);
        frame.add(header);
        frame.add(view);
        frame.add(back);
        view.setFocusable(false);
        back.setFocusable(false);
        header.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("View books");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view){
            try (ResultSet s =fetchBook()) {
                result = new JLabel[size];
                header.setText("BOOKS               QUANTITY");
                int i = 0;
                while (true) {
                    try {
                        if (!s.next()) break;
                        String label=new String(" ");
                        label+=s.getString(1);
                        for(int space=0;space<(25-(s.getString(1).length()));space++){
                            label+=" ";
                        }
                        label+=s.getInt(2);
                        result[i] = new JLabel(label);
                        result[i].setBounds(50, 150 + (i * 50), 200, 25);
                        frame.add(result[i]);
                        i++;
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.repaint();
                }
            }
            catch (SQLException m) {
                throw new RuntimeException(m);
            }
        }
        if(e.getSource()==back){
            frame.dispose();
            if(type.equals("G")) {
                guestmenu gm = new guestmenu(id);
                gm.createGuestmenu();
            }
            else{
                librain_menu lm=new librain_menu(id);
                lm.librainmenu();
            }
        }
    }
}

