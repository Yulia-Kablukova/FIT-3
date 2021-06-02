import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientWindow extends JFrame implements Serializable {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8000;
    private JTextField jtfMessage;
    private JTextField jtfName;
    private JTextArea jtaTextAreaMessage;
    private Socket socket;
    private ObjectInputStream inMessage;
    private ObjectOutputStream outMessage;

    public ClientWindow() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new ObjectInputStream(socket.getInputStream());
            outMessage = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBounds(600, 300, 600, 500);
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jtaTextAreaMessage = new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("Send");
        bottomPanel.add(jbSendMessage, BorderLayout.EAST);

        JRootPane rootPane = SwingUtilities.getRootPane(jbSendMessage);
        rootPane.setDefaultButton(jbSendMessage);

        jtfMessage = new JTextField("Enter your message: ");
        bottomPanel.add(jtfMessage, BorderLayout.CENTER);
        jtfName = new JTextField("Enter your name: ");
        bottomPanel.add(jtfName, BorderLayout.WEST);

        jbSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!jtfMessage.getText().equals("Enter your message: ") && !jtfName.getText().equals("Enter your name: ") && !jtfMessage.getText().trim().isEmpty() && !jtfName.getText().trim().isEmpty()) {
                    try {
                        sendMsg();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    jtfMessage.grabFocus();
                }
            }
        });

        jtfMessage.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMessage.setText("");
            }
        });
        jtfName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfName.setText("");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        if (inMessage != null) {
                            Message msg = (Message) inMessage.readObject();
                            if (!msg.getText().equals("")) {
                                String inMes = msg.getUserName() + ": " + msg.getText() + '\n';
                                jtaTextAreaMessage.append(inMes);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    socket.close();
                    inMessage.close();
                    outMessage.close();
                } catch (IOException exp) {
                    exp.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public void sendMsg() throws IOException {
        Message msg2 = new Message(jtfName.getText(), jtfMessage.getText());
        outMessage.writeObject(msg2);
        outMessage.flush();
        jtfMessage.setText("");
    }
}
