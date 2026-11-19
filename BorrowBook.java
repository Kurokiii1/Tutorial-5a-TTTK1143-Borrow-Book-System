import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorrowBook extends JFrame implements ActionListener {
    private JLabel lblStudent;
    private JLabel lblBook;
    private JLabel lblInfoHeader;
    private JLabel lblBorrowInfo;
    private JComboBox<Student> cmbStudent;
    private JComboBox<Book> cmbBook;
    private JButton btnBorrow;

    public BorrowBook() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public BorrowBook(ArrayList<Student> studentList, ArrayList<Book> bookList) {
        setTitle("Borrow Book");
        setSize(520, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(14, 14, 14, 14));
        setContentPane(mainPanel);

        // Top selection area
        JPanel topPanel = new JPanel(new BorderLayout(8, 10));

        JPanel selectPanel = new JPanel(new GridLayout(2, 2, 10, 6));
        lblStudent = new JLabel("Student Name & Matric:");
        cmbStudent = new JComboBox<>(studentList.toArray(new Student[0]));

        lblBook = new JLabel("Book Title & Author:");
        cmbBook = new JComboBox<>(bookList.toArray(new Book[0]));

        selectPanel.add(lblStudent);
        selectPanel.add(cmbStudent);
        selectPanel.add(lblBook);
        selectPanel.add(cmbBook);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnBorrow = new JButton("Borrow");
        btnBorrow.setPreferredSize(new Dimension(85, 26));
        btnBorrow.addActionListener(this);
        buttonPanel.add(btnBorrow);

        topPanel.add(selectPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        // Bottom borrow status area
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 4, 4));
        lblInfoHeader = new JLabel("Borrow Information :");
        lblBorrowInfo = new JLabel(" ");

        bottomPanel.add(lblInfoHeader);
        bottomPanel.add(lblBorrowInfo);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBorrow) {
            Student selectedStudent = (Student) cmbStudent.getSelectedItem();
            Book selectedBook = (Book) cmbBook.getSelectedItem();

            if (selectedStudent == null || selectedBook == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please register at least one student and one book before borrowing.",
                        "No Records Found",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (selectedStudent.hasBorrowed()) {
                JOptionPane.showMessageDialog(
                        this,
                        selectedStudent.getName() + " has already borrowed \""
                                + selectedStudent.getBorrowedBook().getTitle() + "\".",
                        "Borrow Limit Reached",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (selectedBook.isBorrowed()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Book \"" + selectedBook.getTitle() + "\" is currently borrowed by "
                                + selectedBook.getBorrower().getName() + ".",
                        "Book Unavailable",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String confirmation = selectedBook.borrowBy(selectedStudent);
            lblBorrowInfo.setText(confirmation);
        }
    }
}
