package librarymanagement_consumer;

import librarymanagement_bookcatalog.BookCatalogService;
import librarymanagement_rentalmanagement.RentalManagementService;
import librarymanagement_duefeecalculation.DueFeeCalculationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private BookCatalogService bookCatalogService;
	private RentalManagementService rentalManagementService;
	private DueFeeCalculationService dueFeeCalculationService;
	private JTextField bookIdField;
	private JTextArea bookDetailsArea;
	private JTextArea resultArea;

	public LibraryManagementGUI(BookCatalogService bookCatalogService, RentalManagementService rentalManagementService, DueFeeCalculationService dueFeeCalculationService) {
		this.bookCatalogService = bookCatalogService;
		this.rentalManagementService = rentalManagementService;
		this.dueFeeCalculationService = dueFeeCalculationService;

		setTitle("Library Management");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initComponents();
	}

	private void initComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));

		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);

		inputPanel.add(new JLabel("Book ID:"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		bookIdField = new JTextField(20);
		inputPanel.add(bookIdField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		bookDetailsArea = new JTextArea();
		bookDetailsArea.setLineWrap(true);
		bookDetailsArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(bookDetailsArea);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Book Details"));
		inputPanel.add(scrollPane, gbc);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton addButton = new JButton("Add Book");
		JButton updateButton = new JButton("Update Book");
		JButton deleteButton = new JButton("Delete Book");
		JButton searchButton = new JButton("Search Book");
		JButton rentButton = new JButton("Rent Book");
		JButton returnButton = new JButton("Return Book");
		JButton calculateFeeButton = new JButton("Calculate Due Fee");

		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(rentButton);
		buttonPanel.add(returnButton);
		buttonPanel.add(calculateFeeButton);

		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		JScrollPane resultScrollPane = new JScrollPane(resultArea);
		resultScrollPane.setBorder(BorderFactory.createTitledBorder("Result"));

		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(resultScrollPane, BorderLayout.EAST);

		add(mainPanel);

		// Add action listeners
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				String bookDetails = bookDetailsArea.getText();
				if (!bookId.isEmpty() && !bookDetails.isEmpty()) {
					bookCatalogService.addBook(bookId, bookDetails);
					resultArea.setText("Book added: " + bookId);
				} else {
					resultArea.setText("Please provide both book ID and details");
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				String bookDetails = bookDetailsArea.getText();
				if (!bookId.isEmpty() && !bookDetails.isEmpty()) {
					bookCatalogService.updateBook(bookId, bookDetails);
					resultArea.setText("Book updated: " + bookId);
				} else {
					resultArea.setText("Please provide both book ID and details");
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				if (!bookId.isEmpty()) {
					bookCatalogService.deleteBook(bookId);
					resultArea.setText("Book deleted: " + bookId);
				} else {
					resultArea.setText("Please provide a book ID");
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				if (!bookId.isEmpty()) {
					String bookDetails = bookCatalogService.searchBookById(bookId);
					if (bookDetails != null) {
						bookDetailsArea.setText(bookDetails);
						resultArea.setText("Book found: " + bookId);
					} else {
						resultArea.setText("No book found with ID: " + bookId);
					}
				} else {
					resultArea.setText("Please provide a book ID");
				}
			}
		});

		rentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				String userId = JOptionPane.showInputDialog("Enter User ID:");
				if (!bookId.isEmpty() && userId != null && !userId.isEmpty()) {
					rentalManagementService.rentBook(bookId, userId);
					resultArea.setText("Book rented: " + bookId + " to user: " + userId);
				} else {
					resultArea.setText("Please provide both book ID and user ID");
				}
			}
		});

		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				String userId = JOptionPane.showInputDialog("Enter User ID:");
				if (!bookId.isEmpty() && userId != null && !userId.isEmpty()) {
					rentalManagementService.returnBook(bookId, userId);
					resultArea.setText("Book returned: " + bookId + " by user: " + userId);
				} else {
					resultArea.setText("Please provide both book ID and user ID");
				}
			}
		});

		calculateFeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId = bookIdField.getText();
				String overdueDaysStr = JOptionPane.showInputDialog("Enter overdue days:");
				if (!bookId.isEmpty() && overdueDaysStr != null && !overdueDaysStr.isEmpty()) {
					try {
						int overdueDays = Integer.parseInt(overdueDaysStr);
						double dueFee = dueFeeCalculationService.calculateDueFee(bookId, overdueDays);
						resultArea.setText("Due fee for book " + bookId + ": " + dueFee);
					} catch (NumberFormatException ex) {
						resultArea.setText("Invalid number of overdue days");
					}
				} else {
					resultArea.setText("Please provide both book ID and overdue days");
				}
			}
		});
	}
}
