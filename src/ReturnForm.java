import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;


public class ReturnForm extends JFrame {

    private JButton button1;
    private JTextArea textArea1;
    private JPanel panel1;

    public ReturnForm() {
        setContentPane(panel1);
        //setModal(true);
        getRootPane().setDefaultButton(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VKScribe.text = textArea1.getText();
                try {
                    VKScribe.DecideCaptcha(VKScribe.text);
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void CteateForm() {
        ReturnForm dialog = new ReturnForm();
        dialog.pack();
        dialog.setTitle("Решатель капчи");
        dialog.setLocation(500,0);
        dialog.setSize(700,300);
        dialog.setVisible(true);
    }
}




