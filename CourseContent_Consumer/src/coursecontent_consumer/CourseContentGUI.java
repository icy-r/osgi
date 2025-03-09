
package coursecontent_consumer;

import coursecontent_producer.CourseContentService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseContentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private CourseContentService service;
	private JTextField courseIdField;
	private JTextArea contentArea;
	private JTextArea resultArea;

	public CourseContentGUI(CourseContentService service) {
		this.service = service;

		setTitle("Course Content Management");
		setSize(600, 400);
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

		inputPanel.add(new JLabel("Course ID:"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		courseIdField = new JTextField(20);
		inputPanel.add(courseIdField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Content"));
		inputPanel.add(scrollPane, gbc);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton addButton = new JButton("Add Content");
		JButton updateButton = new JButton("Update Content");
		JButton deleteButton = new JButton("Delete Content");
		JButton searchButton = new JButton("Search");

		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(searchButton);

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
				String courseId = courseIdField.getText();
				String content = contentArea.getText();
				if (!courseId.isEmpty() && !content.isEmpty()) {
					service.addContent(courseId, content);
					resultArea.setText("Content added for course: " + courseId);
				} else {
					resultArea.setText("Please provide both course ID and content");
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId = courseIdField.getText();
				String content = contentArea.getText();
				if (!courseId.isEmpty() && !content.isEmpty()) {
					service.updateContent(courseId, content);
					resultArea.setText("Content updated for course: " + courseId);
				} else {
					resultArea.setText("Please provide both course ID and content");
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId = courseIdField.getText();
				if (!courseId.isEmpty()) {
					service.deleteContent(courseId);
					resultArea.setText("Content deleted for course: " + courseId);
				} else {
					resultArea.setText("Please provide a course ID");
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId = courseIdField.getText();
				if (!courseId.isEmpty()) {
					String content = service.searchLecturesByCourseId(courseId);
					if (content != null) {
						contentArea.setText(content);
						resultArea.setText("Content found for course: " + courseId);
					} else {
						resultArea.setText("No content found for course: " + courseId);
					}
				} else {
					resultArea.setText("Please provide a course ID");
				}
			}
		});
	}
}
