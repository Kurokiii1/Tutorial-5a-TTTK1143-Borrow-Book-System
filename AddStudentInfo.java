import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddStudentInfo extends JFrame implements ActionListener {
    private JLabel lblName;
    private JLabel lblMatric;
    private JLabel lblStudents;
    private JTextField txtName;
    private JTextField txtMatric;
    private JButton btnAdd;
    private JButton btnList;
    private JTextArea txtAreaStudents;

    private ArrayList<Student> studentList;

    public AddStudentInfo() {
        this(new ArrayList<>());
    }

    public AddStudentInfo(ArrayList<Student> studentList) {
        this.studentList = studentList;

        setTitle("Add Student Info");
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        // Top section: form fields + action buttons
        JPanel topPanel = new JPanel(new BorderLayout(5, 8));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        lblName = new JLabel("Name:");
        txtName = new JTextField(20);
        lblMatric = new JLabel("Matric:");
        txtMatric = new JTextField(20);

        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblMatric);
        formPanel.add(txtMatric);

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

        // Bottom section: registered student list
        JPanel listPanel = new JPanel(new BorderLayout(4, 4));
        lblStudents = new JLabel("Students:");
        txtAreaStudents = new JTextArea(6, 35);
        txtAreaStudents.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(
                txtAreaStudents,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        listPanel.add(lblStudents, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String name = txtName.getText().trim();
            String matric = txtMatric.getText().trim();

            if (name.isEmpty() || matric.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter both student name and matric number.",
                        "Missing Input",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            studentList.add(new Student(name, matric));
            txtName.setText("");
            txtMatric.setText("");
            txtName.requestFocus();
        } else if (e.getSource() == btnList) {
            txtAreaStudents.setText("");
            for (Student s : studentList) {
                txtAreaStudents.append("Name : " + s.toString() + "\n");
            }
        }
    }
}
