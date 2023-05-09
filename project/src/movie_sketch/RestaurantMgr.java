package movie_sketch;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class RestaurantMgr extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
   
    private JRadioButton radioBtn1 ;
    private JRadioButton radioBtn2 ;
   
    private JCheckBox checkBox1 ;
    private JCheckBox checkBox2 ;
    private JCheckBox checkBox3 ;
    private JCheckBox checkBox4 ;
   
    private JComboBox comboBox ;
   

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RestaurantMgr frame = new RestaurantMgr();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RestaurantMgr() {       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        setBounds(100, 100, 450, 300);
       
        contentPane = new JPanel();
       
        setContentPane(contentPane);
        contentPane.setLayout(null);
       
        JButton button = new JButton("출력하기");
       
        button.addActionListener(new ActionListener() {
            String str = " " ;
           
            public void actionPerformed(ActionEvent e) {
                if (radioBtn1.isSelected()) {
                    str += radioBtn1.getText() ;
                }
               
                if (radioBtn2.isSelected()) {
                    str += radioBtn2.getText() ;
                }
               
                str += " " ;
                str += comboBox.getSelectedItem() ;
                str += " " ;
               
                if (checkBox1.isSelected()) {
                    str += checkBox1.getText() ;
                }
               
                str += " " ;
               
                if (checkBox2.isSelected()) {
                    str += checkBox2.getText() ;
                }
               
                str += " " ;
               
                if (checkBox3.isSelected()) {
                    str += checkBox3.getText() ;
                }
               
                str += " " ;
               
                if (checkBox4.isSelected()) {
                    str += checkBox4.getText() ;
                }
               
                str += " 예약되어 있습니다." ;
                 
                textField.setText(str) ;
            }
        });
       
        button.setBounds(333, 33, 97, 55);
        contentPane.add(button);
       
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "회원&비회원", TitledBorder.LEADING,    
                                                        TitledBorder.TOP, null, null));
        panel.setToolTipText("");
        panel.setBounds(12, 26, 309, 62);
        contentPane.add(panel);
       
        radioBtn1 = new JRadioButton("회원");
        radioBtn1.setSelected(true);
        panel.add(radioBtn1);
       
        radioBtn2 = new JRadioButton("비회원");
        panel.add(radioBtn2);
       
        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"1시간 후",
                              "2시간 후", "3시간 후", "4시간 후", "5시간 후", "6시간 후"}));
        panel.add(comboBox);
       
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder                                
                                ("TitledBorder.border"), "음식 종류", TitledBorder.LEADING, 
                                             TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(12, 110, 418, 72);
        contentPane.add(panel_1);
       
        checkBox1 = new JCheckBox("랍스타");
        panel_1.add(checkBox1);
       
        checkBox2 = new JCheckBox("인도카레");
        panel_1.add(checkBox2);
       
        checkBox3 = new JCheckBox("스테이크");
        panel_1.add(checkBox3);
       
        checkBox4 = new JCheckBox("불고기");
        panel_1.add(checkBox4);
       
        textField = new JTextField();
        textField.setBounds(12, 205, 418, 27);
        contentPane.add(textField);
        textField.setColumns(10);
    }
}
