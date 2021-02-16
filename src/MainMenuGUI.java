import javax.swing.*;

public class MainMenuGUI extends JFrame{
    private JPanel mainMenuPanel;
    private JLabel titleLabel;

    public MainMenuGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
