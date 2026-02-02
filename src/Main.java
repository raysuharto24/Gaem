import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    private static JFrame frame;
    private static JPanel menuPanel;
    private static JPanel gamePanel;

    private static void createWindow() {

        frame = new JFrame("Gaem v. 26.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        createMenuPanel();
        createGamePanel();

        frame.setContentPane(menuPanel);
        frame.setVisible(true);
    }

    // ===== MENU PANEL =====
    private static void createMenuPanel() {

        menuPanel = new JPanel(new BorderLayout());

        // CENTER CONTENT
        JPanel centerPanel = new JPanel(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // LOGO
        ImageIcon original = new ImageIcon("src/logo.png");
        Image scaled = original.getImage().getScaledInstance(500, -1, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaled));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // BUTTONS
        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");

        playButton.setFont(new Font("Arial", Font.BOLD, 26));
        quitButton.setFont(new Font("Arial", Font.PLAIN, 22));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ADD BUTTONS TO PANEL
        contentPanel.add(logoLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(playButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(quitButton);

        centerPanel.add(contentPanel);
        menuPanel.add(centerPanel, BorderLayout.CENTER);

        // BOTTOM-RIGHT COPYRIGHT
        JLabel copyright = new JLabel(
                "Pizeltray Studios - all rights reserved 2026"
        );
        copyright.setFont(new Font("Arial", Font.PLAIN, 12));
        copyright.setForeground(Color.DARK_GRAY);
        copyright.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        bottomPanel.add(copyright, BorderLayout.EAST);

        menuPanel.add(bottomPanel, BorderLayout.SOUTH);

        // ACTIONS
        playButton.addActionListener(e -> switchToGame());
        quitButton.addActionListener(e -> System.exit(0));

        // HOVER + HAND CURSOR EFFECTS
        addHoverEffect(playButton, Color.LIGHT_GRAY, Color.BLACK);
        addHoverEffect(quitButton, Color.LIGHT_GRAY, Color.BLACK);
    }

    // ===== GAME PANEL =====
    private static void createGamePanel() {

        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(Color.GRAY);

        // TOP LEFT BACK BUTTON
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setOpaque(false);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));

        topBar.add(backButton);
        gamePanel.add(topBar, BorderLayout.NORTH);

        backButton.addActionListener(e -> switchToMenu());

        // HOVER + HAND CURSOR EFFECT
        addHoverEffect(backButton, Color.LIGHT_GRAY, Color.BLACK);

        // CENTER LOADING TEXT
        JLabel loadingLabel = new JLabel("LOADING...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 50));
        loadingLabel.setForeground(Color.BLACK);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setVerticalAlignment(SwingConstants.CENTER);

        gamePanel.add(loadingLabel, BorderLayout.CENTER);
    }

    // ===== SCREEN SWITCHING =====
    private static void switchToGame() {
        frame.setContentPane(gamePanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void switchToMenu() {
        frame.setContentPane(menuPanel);
        frame.revalidate();
        frame.repaint();
    }

    // ===== HOVER EFFECT HELPER =====
    private static void addHoverEffect(JButton button, Color hoverBackground, Color hoverForeground) {
        Color originalBg = button.getBackground();
        Color originalFg = button.getForeground();

        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
                button.setForeground(hoverForeground);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // change mouse to hand
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalBg);
                button.setForeground(originalFg);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // reset mouse
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createWindow);
        System.out.println("Pizeltray Studios - All Rights Reserved 2026");
    }
}

// return 0;
