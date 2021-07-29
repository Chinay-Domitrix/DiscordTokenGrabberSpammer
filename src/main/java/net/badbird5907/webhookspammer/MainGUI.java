package net.badbird5907.webhookspammer;

import com.formdev.flatlaf.IntelliJTheme;
import com.intellij.uiDesigner.core.GridLayoutManager;
import lombok.Getter;
import lombok.SneakyThrows;
import net.badbird5907.webhookspammer.util.WebhookSpammer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import static com.intellij.uiDesigner.core.GridConstraints.*;

public class MainGUI implements ActionListener {
	@SneakyThrows
	public static void main(String[] args) {
		InputStream stream = MainGUI.class.getClassLoader().getResourceAsStream("XCodeDark.theme.json");
		if (stream == null) System.err.println("stream is null");
		else IntelliJTheme.setup(stream);
//		UIManager.setLookAndFeel(new IntelliJTheme.ThemeLaf());
		frame = new JFrame("Webhook Spammer");
		frame.setContentPane(new MainGUI().MainPanel);
		if (System.console() == null) {
			InputStream is = MainGUI.class.getClassLoader().getResourceAsStream("icon.png");
			byte[] targetArray = new byte[is.available()];
			is.read(targetArray);
			ImageIcon icon = new ImageIcon(targetArray);
			frame.setIconImage(icon.getImage());
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 420);
		frame.pack();
		frame.setVisible(true);
		new MainGUI();
	}

	@Getter
	private static JFrame frame;

	public MainGUI() {
		submitButton.addActionListener(this);
		resetDefaultsButton.addActionListener(e -> {
			webhookUrlField.setText("Webhook URL Here");
			messageField.setText("@everyone %token%");
			errorWaitField.setText("500");
			rateLimitWaitField.setText("2000");
			delayField.setText("500");
			nameField.setText("");
			avatarUrlField.setText("");
		});
	}

	private JPanel MainPanel;
	private JButton submitButton;
	private JTextField webhookUrlField;
	private JTabbedPane tabbedPane1;
	private JTextField messageField;
	private JLabel urlLabel;
	private JTextField errorWaitField;
	private JTextField rateLimitWaitField;
	private JTextField delayField;
	private JTextField nameField;
	private JTextField avatarUrlField;
	private JButton resetDefaultsButton;

	@SneakyThrows
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("action: " + e.getActionCommand());
		if (e.getActionCommand().toLowerCase().contains("spam"))
			if (webhookUrlField.getText().contains("Webhook URL Here") || (!webhookUrlField.getText().contains("http"))) JOptionPane.showMessageDialog(frame, "Please enter a webhook url!");
			else {
				long error, ratelimit, delay;
				try {
					error = Long.parseLong(errorWaitField.getText());
				} catch (NumberFormatException numberFormatException) {
					JOptionPane.showMessageDialog(frame, errorWaitField.getText() + " is not a valid number!");
					return;
				}
				try {
					ratelimit = Long.parseLong(rateLimitWaitField.getText());
				} catch (NumberFormatException numberFormatException) {
					JOptionPane.showMessageDialog(frame, errorWaitField.getText() + " is not a valid number!");
					return;
				}
				try {
					delay = Long.parseLong(delayField.getText());
				} catch (NumberFormatException numberFormatException) {
					JOptionPane.showMessageDialog(frame, errorWaitField.getText() + " is not a valid number!");
					return;
				}
				if (System.console() == null) {
					int input = JOptionPane.showConfirmDialog(null, "The GUI will freeze until the webhook is deleted, you can run this with cmd to forcefully terminate the process (ctrl + c)\nAre you sure you want to start?");
					if (input == 0) new WebhookSpammer(webhookUrlField.getText(), messageField.getText(), nameField.getText(), avatarUrlField.getText(), error, ratelimit, delay);
					else System.exit(0);
				} // don't show warning if they're on console
				else new WebhookSpammer(webhookUrlField.getText(), messageField.getText(), nameField.getText(), avatarUrlField.getText(), error, ratelimit, delay);
			}
	}

	{
//	GUI initializer generated by IntelliJ IDEA GUI Designer
//	>>> IMPORTANT!! <<<
//	DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		MainPanel = new JPanel();
		MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		tabbedPane1 = new JTabbedPane();
		MainPanel.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, ANCHOR_CENTER, FILL_BOTH, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		tabbedPane1.addTab("Spam", panel1);
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(panel2, new GridConstraints(0, 0, 1, 1, ANCHOR_CENTER, FILL_BOTH, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		submitButton = new JButton();
		submitButton.setText("Spam!");
		panel2.add(submitButton, new GridConstraints(8, 0, 1, 2, ANCHOR_CENTER, FILL_HORIZONTAL, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, SIZEPOLICY_FIXED, null, null, null, 0, false));
		urlLabel = new JLabel();
		urlLabel.setText("Webhook URL");
		panel2.add(urlLabel, new GridConstraints(0, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		webhookUrlField = new JTextField();
		webhookUrlField.setText("Webhook URL Here");
		panel2.add(webhookUrlField, new GridConstraints(0, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Message");
		panel2.add(label1, new GridConstraints(1, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		messageField = new JTextField();
		messageField.setText("@everyone %token%");
		panel2.add(messageField, new GridConstraints(1, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Error Wait");
		panel2.add(label2, new GridConstraints(4, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Ratelimit Wait");
		panel2.add(label3, new GridConstraints(5, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		errorWaitField = new JTextField();
		errorWaitField.setText("500");
		panel2.add(errorWaitField, new GridConstraints(4, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		rateLimitWaitField = new JTextField();
		rateLimitWaitField.setText("2000");
		panel2.add(rateLimitWaitField, new GridConstraints(5, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Delay");
		panel2.add(label4, new GridConstraints(6, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		delayField = new JTextField();
		delayField.setText("500");
		panel2.add(delayField, new GridConstraints(6, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Name");
		panel2.add(label5, new GridConstraints(2, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		nameField = new JTextField();
		nameField.setText("");
		panel2.add(nameField, new GridConstraints(2, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("Avatar URL");
		panel2.add(label6, new GridConstraints(3, 0, 1, 1, ANCHOR_WEST, FILL_NONE, SIZEPOLICY_FIXED, SIZEPOLICY_FIXED, null, null, null, 0, false));
		avatarUrlField = new JTextField();
		panel2.add(avatarUrlField, new GridConstraints(3, 1, 1, 1, ANCHOR_WEST, FILL_HORIZONTAL, SIZEPOLICY_WANT_GROW, SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		resetDefaultsButton = new JButton();
		resetDefaultsButton.setText("Reset Defaults");
		panel2.add(resetDefaultsButton, new GridConstraints(7, 0, 1, 2, ANCHOR_CENTER, FILL_HORIZONTAL, SIZEPOLICY_CAN_SHRINK | SIZEPOLICY_CAN_GROW, SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return MainPanel;
	}
}
