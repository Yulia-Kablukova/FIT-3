import java.io.Serializable;

public class Message implements Serializable {

    private String userName;
    private String text;

    public Message(String _userName, String _text) {
        userName = _userName;
        text = _text;
    }

    public String getUserName() {

        return userName;
    }

    public String getText() {

        return text;
    }

    public void setUserName(String _userName) {

        userName = _userName;
    }

    public void setText(String _text) {

        text = _text;
    }
}
