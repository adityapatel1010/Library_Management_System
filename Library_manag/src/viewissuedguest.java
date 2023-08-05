import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewissuedguest extends Database implements ActionListener{
    JFrame frame=new JFrame("Guest Menu");
    JButton back=new JButton("<- Back");
    JButton view=new JButton("View");
    JLabel result[];

    JLabel header=new JLabel("");
    String id;

    viewissuedguest(String id){
        this.id=id;
    }
    void viewissuedbooks(){
        back.setBounds(320,0,100,25);
        back.addActionListener(this);
        view.setBounds(125,50,150,25);
        view.addActionListener(this);
        header.setBounds(50,100,200,25);
        frame.add(header);
        frame.add(back);
        frame.add(view);
        back.setFocusable(false);
        view.setFocusable(false);
        header.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(750,250,420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("View Issued Books");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            guestmenu gm=new guestmenu(id);
            gm.createGuestmenu();
        }
        if(e.getSource()==view){
            try (ResultSet s = super.issue(id)) {
                result = new JLabel[size];
                System.out.println(size);
                header.setText("BOOKS               ISSUE DATE");
                int i = 0;
                while (true) {
                    try {
                        if (!s.next()) break;
                        String label=new String(" ");
                        label+=s.getString(1);
                        for(int space=0;space<(25-(s.getString(1).length()));space++){
                            label+=" ";
                        }
                        label+=s.getDate(2);
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
    }
}
