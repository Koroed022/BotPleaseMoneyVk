import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormClass extends JFrame {

    private JPanel panel;
    private JButton addKeyButton;
    private JTextArea fromTextArea;
    private JTextArea toTextArea;
    private JLabel labelCount;
    private JLabel labelInput;
    private JLabel countlabel;

    private JTextArea countTextArea;

    public FormClass() {
        setContentPane(panel);
        //setModal(true);
        getRootPane().setDefaultButton(addKeyButton);

        addKeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VKScribe.VkAll(fromTextArea.getText(), toTextArea.getText(), countTextArea.getText());
                } catch (Exception e1) {
                    e1.getMessage();
                }
                dispose();
            }
        });


        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void CteateForm() {
        FormClass dialog = new FormClass();

        dialog.pack();
        dialog.setTitle("Через сколько часов || photos");
        dialog.setLocation(500,300);
        dialog.setSize(700,300);
        dialog.setVisible(true);

    }



}
