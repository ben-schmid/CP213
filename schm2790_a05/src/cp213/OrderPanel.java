package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    final PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(order);

	    if (printJob.printDialog()) {
		try {
		    printJob.print();
		} catch (final Exception printException) {
		    System.err.println(printException);
		}
	    }

	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item;

	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
	    JTextField source = (JTextField) e.getSource();
	    try {
		int quantity = Integer.parseInt(source.getText());
		if (quantity < 0)
		    throw new NumberFormatException();

		// Update the order with the new quantity
		order.update(item, quantity);
	    } catch (NumberFormatException ex) {
		source.setText("0"); // Reset to 0 if the input is not a valid integer
	    }

	    // Update the UI with the new order totals
	    updateOrderSummary();
	}
    }

    private void updateOrderSummary() {
	// Use the existing methods to get the subtotal, tax, and total
	BigDecimal subtotal = order.getSubTotal();
	BigDecimal tax = order.getTaxes();
	BigDecimal total = order.getTotal();

	// Update the UI labels
	subtotalLabel.setText(String.format("$%,.2f", subtotal));
	taxLabel.setText(String.format("$%,.2f", tax));
	totalLabel.setText(String.format("$%,.2f", total));
    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
	// Remove all components before laying out the new view
	this.removeAll();

	// Calculate the number of rows needed: one for each menu item, one for headers,
	// and one for totals
	int rows = this.menu.size() + 5; // plus two for the header and footer
	int columns = 3; // one for item name, one for price, and one for quantity

	// Set the layout manager with specified rows and columns
	this.setLayout(new GridLayout(rows, columns));

	// Add header labels
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));

	// Initialize GUI components for each menu item
	for (int i = 0; i < this.menu.size(); i++) {
	    MenuItem item = menu.getItem(i);

	    // Create labels and text field for each menu item
	    JLabel nameLabel = new JLabel(item.getName());
	    JLabel priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));
	    JTextField quantityField = new JTextField("0", 5);

	    priceLabel.setHorizontalAlignment(JLabel.RIGHT);

	    quantityField.setHorizontalAlignment(JTextField.RIGHT);

	    // Add the components to the panel
	    this.add(nameLabel);
	    this.add(priceLabel);
	    this.add(quantityField);

	    // Store the references to the components
	    nameLabels[i] = nameLabel;
	    priceLabels[i] = priceLabel;
	    quantityFields[i] = quantityField;
	}

	JLabel subtotalTextLabel = new JLabel("Subtotal:", SwingConstants.LEFT);
	JLabel taxTextLabel = new JLabel("Tax:", SwingConstants.LEFT);
	JLabel totalTextLabel = new JLabel("Total:", SwingConstants.LEFT);

	double initialSubtotal = 0.0; // Initial value
	double initialTax = 0.0; // Initial value
	double initialTotal = 0.0; // Initial value

	// Update the text of subtotal, tax, and total labels with initial formatted
	// values
	subtotalLabel.setText(String.format("$%,.2f", initialSubtotal));
	taxLabel.setText(String.format("$%,.2f", initialTax));
	totalLabel.setText(String.format("$%,.2f", initialTotal));

	subtotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	taxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

	// Adding text labels for subtotal, tax, and total
	this.add(subtotalTextLabel);
	this.add(new JLabel("")); // Placeholder for the second column
	this.add(subtotalLabel); // Subtotal value

	this.add(taxTextLabel);
	this.add(new JLabel("")); // Placeholder for the second column
	this.add(taxLabel); // Tax value

	this.add(totalTextLabel);
	this.add(new JLabel("")); // Placeholder for the second column
	this.add(totalLabel); // Total value

	// Add print button with placeholders for alignment
	this.add(new JLabel("")); // Placeholder for the first column
	this.add(printButton);
	this.add(new JLabel("")); // Placeholder for the third column

    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	// Register QuantityListeners for each quantity field.
	for (int i = 0; i < this.menu.size(); i++) {
	    MenuItem item = menu.getItem(i);
	    QuantityListener listener = new QuantityListener(item);

	    quantityFields[i].addFocusListener(listener);
	}

    }

}