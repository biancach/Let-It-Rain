
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        view.setSize(800, 600);
        view.setLocation(100, 100);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
        Model model = new Model();
        new Controller(model, view);
    }
}
