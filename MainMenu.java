import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu extends JFrame implements ActionListener {
    private JLabel lblHeader;
    private JButton btnAddStudent;
    private JButton btnAddBook;
    private JButton btnBorrowBook;

    private ArrayList<Student> studentList;
    private ArrayList<Book> bookList;

    public MainMenu() {
        studentList = new ArrayList<>();
        bookList = new ArrayList<>();

        setTitle("Main Menu");
        setSize(320, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridLayout(4, 1, 10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        lblHeader = new JLabel("~~ BORROW BOOK SYSTEM ~~", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 13));

        btnAddStudent = new JButton("Add Student Info");
        btnAddBook = new JButton("Add Book Info");
        btnBorrowBook = new JButton("Borrow Book");

        btnAddStudent.addActionListener(this);
        btnAddBook.addActionListener(this);
        btnBorrowBook.addActionListener(this);

        contentPane.add(lblHeader);
        contentPane.add(btnAddStudent);
        contentPane.add(btnAddBook);
        contentPane.add(btnBorrowBook);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddStudent) {
            AddStudentInfo studentFrame = new AddStudentInfo(studentList);
            studentFrame.setVisible(true);
        } else if (e.getSource() == btnAddBook) {
            AddBookInfo bookFrame = new AddBookInfo(bookList);
            bookFrame.setVisible(true);
        } else if (e.getSource() == btnBorrowBook) {
            BorrowBook borrowFrame = new BorrowBook(studentList, bookList);
            borrowFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
    }
}
