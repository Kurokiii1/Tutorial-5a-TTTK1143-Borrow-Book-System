import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddBookInfo extends JFrame implements ActionListener {
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblBooks;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JButton btnAdd;
    private JButton btnList;
    private JTextArea txtAreaBooks;

    private ArrayList<Book> bookList;

    public AddBookInfo() {
        this(new ArrayList<>());
    }

    public AddBookInfo(ArrayList<Book> bookList) {
        this.bookList = bookList;

        setTitle("Add Book Info");
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        // Top section: form fields + action buttons
        JPanel topPanel = new JPanel(new BorderLayout(5, 8));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        lblTitle = new JLabel("Book Title:");
        txtTitle = new JTextField(20);
        lblAuthor = new JLabel("Author:");
        txtAuthor = new JTextField(20);

        formPanel.add(lblTitle);
        formPanel.add(txtTitle);
        formPanel.add(lblAuthor);
        formPanel.add(txtAuthor);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnAdd = new JButton("Add");
        btnList = new JButton("List");
        btnAdd.setPreferredSize(new Dimension(75, 26));
        btnList.setPreferredSize(new Dimension(75, 26));

        btnAdd.addActionListener(this);
        btnList.addActionListener(this);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnList);

        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Bottom section: registered book list
        JPanel listPanel = new JPanel(new BorderLayout(4, 4));
        lblBooks = new JLabel("Books:");
        txtAreaBooks = new JTextArea(6, 35);
        txtAreaBooks.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(
                txtAreaBooks,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        listPanel.add(lblBooks, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();

            if (title.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter both book title and author.",
                        "Missing Input",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            bookList.add(new Book(title, author));
            txtTitle.setText("");
            txtAuthor.setText("");
            txtTitle.requestFocus();
        } else if (e.getSource() == btnList) {
            txtAreaBooks.setText("");
            for (Book b : bookList) {
                txtAreaBooks.append("Book title : " + b.getTitle() + " (Author : " + b.getAuthor() + ")\n");
            }
        }
    }
}
