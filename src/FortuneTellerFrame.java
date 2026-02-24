import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    // components
    JPanel mainPnl;
    JPanel topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JLabel label;
    ImageIcon icon;
    JTextArea fortuneArea;
    JScrollPane scroller;
    JButton fortuneBtn, quitBtn;

    // logic
    ArrayList<String> fortunes;
    int lastIndex = -1; // stores the index of the prev fortune for no repeats
    Random rnd = new Random();


    public FortuneTellerFrame() {
        // main layout
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout()); // splits window into NSEW and center

        // call helper methods to build ui
        createTopPanel();
        createMiddlePanel();
        createBottomPanel();
        loadFortunes();

        // add the finished main panel to frame
        add(mainPnl);

        // set window size to 3/4 and center
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // 3/4 width and 3/4 height
        setSize(3 * screenWidth / 4, 3 * screenHeight / 4);
        // center the frame by calculating the starting X/Y coordinates
        setLocation(screenWidth / 8, screenHeight / 8);

        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
            topPnl = new JPanel();


            icon = new ImageIcon("fortuneTeller.png");
            label = new JLabel("Fortune Teller", icon, JLabel.CENTER);
            label.setIcon(icon);
            label.setFont(new Font("Serif", Font.BOLD, 48));
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.CENTER);

            topPnl.add(label);
            mainPnl.add(topPnl, BorderLayout.NORTH);

        }

    private void createMiddlePanel() {
        midPnl = new JPanel();

        fortuneArea = new JTextArea(10, 35);
        fortuneArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        fortuneArea.setEditable(false);


        scroller = new JScrollPane(fortuneArea);
        midPnl.add(scroller);
        mainPnl.add(midPnl, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        botPnl = new JPanel();
        fortuneBtn = new JButton("Read My Fortune!");
        quitBtn = new JButton("Quit");

        Font btnFont = new Font("Arial", Font.BOLD, 20);
        fortuneBtn.setFont(btnFont);
        quitBtn.setFont(btnFont);


        fortuneBtn.addActionListener(e -> displayFortune());
        quitBtn.addActionListener(e -> System.exit(0));

        botPnl.add(fortuneBtn);
        botPnl.add(quitBtn);
        mainPnl.add(botPnl, BorderLayout.SOUTH); // Put buttons at the bottom
    }

    private void loadFortunes() {
        fortunes = new ArrayList<>();
        fortunes.add("You will find a hidden snack in your jacket pocket.");
        fortunes.add("A clean kitchen is a sign of a wasted life.");
        fortunes.add("You will soon be hungry again. Order more takeout.");
        fortunes.add("A quiet opportunity is closer than it looks.");
        fortunes.add("The usefulness of a cup is in its emptiness");
        fortunes.add("The weather will be exactly what it is.");
        fortunes.add("You will encounter a very tall person today.");
        fortunes.add("You already know what to do.");
        fortunes.add("Silence is golden, but duct tape is silver.");
        fortunes.add("A small win is still a win.");
        fortunes.add("You will discover you are talented at something useless.");
        fortunes.add("Today is a great day to finish that one assignment.");
    }

    private void displayFortune() {
        int newIndex;
        // keep generating a random number until it's diff from the last one
        do {
            newIndex = rnd.nextInt(fortunes.size());
        } while (newIndex == lastIndex);

        lastIndex = newIndex;
        String fortune = fortunes.get(newIndex);

        fortuneArea.append(fortune + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FortuneTellerFrame();
        });
    }
}