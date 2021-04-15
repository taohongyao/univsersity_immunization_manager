package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PeopleManageWindows {
    private JTable schoolTable;
    private JTable teacherTable;
    private JTable studentTable;
    private JTabbedPane peopleMangeTabbedPane;
    private JButton studentRemoveButton;
    private JButton teacherRemoveButton;
    private JButton schoolRemoveButton;
    private JTextField schoolIdText;
    private JButton addSchoolButton;
    private JTextField teacherIdText;
    private JTextField teacherAgeText;
    private JTextField teacherAddressText;
    private JTextField teacherNameText;
    private JTextField teacherPhoneText;
    private JTextField teacherCreditText;
    private JButton addTeacherButton;
    private JTextField studentIdText;
    private JTextField studentNameText;
    private JTextField studentAgeText;
    private JTextField studentAddressText;
    private JTextField studentPhoneText;
    private JTextField studentFatherNameText;
    private JTextField studentMotherNameText;
    private JTextField studentGradeText;
    private JButton addStudentButton;
    private JTextField schoolNameText;
    private JComboBox teacherSchoolIdBox;
    private JComboBox studentSchoolBox;
    private JPanel peopleManagerPanel;
    private DayCare dayCare;

//    public static void main(String[] args) {
//        DayCare dayCare = new DayCare();
//        dayCare.demo();
//        new PeopleManageWindows(dayCare);
//    }

    public void updateStudensTable() {
        String Columns[] = {"ID", "Student Name", "School ID", "Age(months)", "Address", "Phone", "Father Name", "Mother Name", "Grade Name"};
        List<Person> list = dayCare.getStudents();
        int rowLength = list.size();
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Student item = (Student) list.get(i);
            data[i][0] = String.valueOf(item.getPersonID());
            data[i][1] = item.getName();
            data[i][2] = String.valueOf(item.getSchoolID());
            data[i][3] = String.valueOf(item.getAgeMonths());
            data[i][4] = item.getAddress();
            data[i][5] = item.getPhone();
            data[i][6] = item.getFatherName();
            data[i][7] = item.getMotherName();
            data[i][8] = String.valueOf(item.getGrade());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        studentTable.setModel(tableModel);
    }

    public void updateTeachersTable() {
        String Columns[] = {"ID", "Teacher Name", "School ID", "Age(months)", "Address", "Phone", "Credit"};
        List<Person> teachers = dayCare.getTeachers();
        int rowLength = teachers.size();
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            data[i][0] = String.valueOf(teacher.getPersonID());
            data[i][1] = teacher.getName();
            data[i][2] = String.valueOf(teacher.getSchoolID());
            data[i][3] = String.valueOf(teacher.getAgeMonths());
            data[i][4] = teacher.getAddress();
            data[i][5] = teacher.getPhone();
            data[i][6] = String.valueOf(teacher.getCredit());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        teacherTable.setModel(tableModel);
    }

    public void addStudent() {
        Student student = (Student) StudentFactory.getInstance().getObject();
        student.setPersonID(Integer.valueOf(studentIdText.getText()));
        int schoolid = ((School) studentSchoolBox.getItemAt(studentSchoolBox.getSelectedIndex())).getSchoolID();
        student.setSchoolID(schoolid);
        student.setName(studentNameText.getText());
        student.setAgeMonths(Integer.valueOf(studentAgeText.getText()));
        student.setAddress(studentAddressText.getText());
        student.setPhone(studentPhoneText.getText());
        student.setFatherName(studentFatherNameText.getText());
        student.setMotherName(studentMotherNameText.getText());
        student.setGrade(Double.valueOf(studentGradeText.getText()));
        dayCare.enrollStudent(student);
    }

    public void addTeacher() {
        Teacher teacher = (Teacher) TeacherFactory.getInstance().getObject();
        teacher.setPersonID(Integer.valueOf(teacherIdText.getText()));
        int schoolid = ((School) teacherSchoolIdBox.getItemAt(teacherSchoolIdBox.getSelectedIndex())).getSchoolID();
        teacher.setSchoolID(schoolid);
        teacher.setName(teacherNameText.getText());
        teacher.setAgeMonths(Integer.valueOf(teacherAgeText.getText()));
        teacher.setAddress(teacherAddressText.getText());
        teacher.setPhone(teacherPhoneText.getText());
        teacher.setCredit(Integer.parseInt(teacherCreditText.getText()));
        teacher.setLastTimeAnnualReview(System.currentTimeMillis());
        dayCare.enrollTeacher(teacher);
    }

    public void addSchool() {
        School school = new School();
        school.setSchoolID(Integer.valueOf(schoolIdText.getText()));
        school.setSchoolName(schoolNameText.getText());
        dayCare.addSchool(school);
    }


    public void fillSchoolId() {
        int size = dayCare.getSchools().size();
        schoolIdText.setText(String.valueOf(dayCare.getSchools().get(size - 1).getSchoolID() + 1));
    }

    public void fillStudentId() {
        int size = dayCare.getStudents().size();
        studentIdText.setText(String.valueOf(dayCare.getStudents().get(size - 1).getPersonID() + 1));
    }

    public void fillTeacherId() {
        int size = dayCare.getTeachers().size();
        teacherIdText.setText(String.valueOf(dayCare.getTeachers().get(size - 1).getPersonID() + 1));
    }

    public void updateSchoolTable() {
        String schoolColumns[] = {"SchoolID", "School Name"};
        List<School> schools = dayCare.getSchools();
        int rowLength = schools.size();
        int columnsLength = schoolColumns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            School school = schools.get(i);
            data[i][0] = String.valueOf(school.getSchoolID());
            data[i][1] = school.getSchoolName();

        }
        DefaultTableModel tableModel = new DefaultTableModel(data, schoolColumns);
        schoolTable.setModel(tableModel);
    }

    public void listenerSetup() {
        peopleMangeTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = peopleMangeTabbedPane.getSelectedIndex();
                if (index == 0) {
                    updateSchoolTable();

                } else if (index == 1) {
                    updateTeachersTable();
                    updateTeacherSchoolBox();
                } else {
                    updateStudensTable();
                    updateStudentSchoolBox();
                }
            }
        });

        schoolRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = schoolTable.getSelectedRow();
                if (row != -1) {
                    int id = Integer.valueOf((String) schoolTable.getValueAt(row, 0));
                    dayCare.removeSchool(id);
                    updateSchoolTable();
                    fillSchoolId();
                }
            }
        });
        addSchoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSchool();
                fillSchoolId();
                updateSchoolTable();
            }
        });
        teacherRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = teacherTable.getSelectedRow();
                if (row != -1) {
                    int id = Integer.valueOf((String) teacherTable.getValueAt(row, 0));
                    dayCare.removeTeacherById(id);
                    updateTeachersTable();
                    fillTeacherId();
                }
            }
        });


        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacher();
                updateTeachersTable();
                fillTeacherId();
            }
        });
        studentRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = studentTable.getSelectedRow();
                if (row != -1) {
                    int id = Integer.valueOf((String) studentTable.getValueAt(row, 0));
                    dayCare.removeStudentById(id);
                    updateStudensTable();
                    fillStudentId();
                }
            }
        });
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
                updateStudensTable();
                fillStudentId();

            }
        });
    }

    public void updateTeacherSchoolBox() {
        List<School> list = dayCare.getSchools();
        List<String> schoolName = new ArrayList<String>();
        int rowLength = list.size();
        for (int i = 0; i < rowLength; i++) {
            School school = list.get(i);
            schoolName.add(school.getSchoolID() + "-" + school.getSchoolName());
        }
        DefaultComboBoxModel tableModel = new DefaultComboBoxModel(list.toArray());
        teacherSchoolIdBox.setModel(tableModel);
    }

    public void updateStudentSchoolBox() {
        List<School> list = dayCare.getSchools();
        List<String> schoolName = new ArrayList<String>();
        int rowLength = list.size();
        for (int i = 0; i < rowLength; i++) {
            School school = list.get(i);
            schoolName.add(school.getSchoolID() + "-" + school.getSchoolName());
        }
        DefaultComboBoxModel tableModel = new DefaultComboBoxModel(list.toArray());
        studentSchoolBox.setModel(tableModel);
    }

    public PeopleManageWindows(DayCare dayCare) {
        this.dayCare = dayCare;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("People Manage");
        jFrame.setContentPane(peopleManagerPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - peopleManagerPanel.getSize().width / 2, dim.height / 2 - peopleManagerPanel.getSize().height / 2);
        jFrame.setResizable(false);
        listenerSetup();
        updateSchoolTable();
        fillSchoolId();
        fillTeacherId();
        fillStudentId();

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
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
        peopleManagerPanel = new JPanel();
        peopleManagerPanel.setLayout(new BorderLayout(0, 0));
        peopleManagerPanel.setMinimumSize(new Dimension(680, 660));
        peopleManagerPanel.setPreferredSize(new Dimension(680, 660));
        peopleMangeTabbedPane = new JTabbedPane();
        peopleMangeTabbedPane.setMinimumSize(new Dimension(670, 670));
        peopleMangeTabbedPane.setPreferredSize(new Dimension(670, 670));
        peopleManagerPanel.add(peopleMangeTabbedPane, BorderLayout.CENTER);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        peopleMangeTabbedPane.addTab("Add School", panel1);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(650, 380));
        scrollPane1.setPreferredSize(new Dimension(650, 380));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        schoolTable = new JTable();
        schoolTable.setAutoResizeMode(3);
        scrollPane1.setViewportView(schoolTable);
        schoolRemoveButton = new JButton();
        schoolRemoveButton.setText("Remove School");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(schoolRemoveButton, gbc);
        addSchoolButton = new JButton();
        addSchoolButton.setText("Add School");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(addSchoolButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer6, gbc);
        schoolIdText = new JTextField();
        schoolIdText.setMinimumSize(new Dimension(150, 30));
        schoolIdText.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(schoolIdText, gbc);
        schoolNameText = new JTextField();
        schoolNameText.setMinimumSize(new Dimension(150, 30));
        schoolNameText.setPreferredSize(new Dimension(150, 30));
        schoolNameText.setText("Apex University");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(schoolNameText, gbc);
        final JLabel label1 = new JLabel();
        label1.setMinimumSize(new Dimension(150, 16));
        label1.setPreferredSize(new Dimension(150, 16));
        label1.setText("School ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setMinimumSize(new Dimension(150, 16));
        label2.setPreferredSize(new Dimension(150, 16));
        label2.setText("School name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        peopleMangeTabbedPane.addTab("Add teacher", panel2);
        teacherIdText = new JTextField();
        teacherIdText.setMinimumSize(new Dimension(150, 30));
        teacherIdText.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherIdText, gbc);
        teacherCreditText = new JTextField();
        teacherCreditText.setMinimumSize(new Dimension(150, 30));
        teacherCreditText.setPreferredSize(new Dimension(150, 30));
        teacherCreditText.setText("10");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherCreditText, gbc);
        final JLabel label3 = new JLabel();
        label3.setMinimumSize(new Dimension(150, 16));
        label3.setPreferredSize(new Dimension(150, 16));
        label3.setText("Credit: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setMinimumSize(new Dimension(150, 16));
        label4.setPreferredSize(new Dimension(150, 16));
        label4.setText("School ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setMinimumSize(new Dimension(150, 16));
        label5.setPreferredSize(new Dimension(150, 16));
        label5.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label5, gbc);
        teacherAddressText = new JTextField();
        teacherAddressText.setMinimumSize(new Dimension(150, 30));
        teacherAddressText.setPreferredSize(new Dimension(150, 30));
        teacherAddressText.setText("Malden 240 pleasant str");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherAddressText, gbc);
        final JLabel label6 = new JLabel();
        label6.setMinimumSize(new Dimension(150, 16));
        label6.setPreferredSize(new Dimension(150, 16));
        label6.setText("ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setMinimumSize(new Dimension(150, 16));
        label7.setPreferredSize(new Dimension(150, 16));
        label7.setText("Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label7, gbc);
        teacherNameText = new JTextField();
        teacherNameText.setMinimumSize(new Dimension(150, 30));
        teacherNameText.setPreferredSize(new Dimension(150, 30));
        teacherNameText.setText("Thomas");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherNameText, gbc);
        final JLabel label8 = new JLabel();
        label8.setMinimumSize(new Dimension(150, 16));
        label8.setPreferredSize(new Dimension(150, 16));
        label8.setText("Age (month):");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label8, gbc);
        teacherAgeText = new JTextField();
        teacherAgeText.setMinimumSize(new Dimension(150, 30));
        teacherAgeText.setPreferredSize(new Dimension(150, 30));
        teacherAgeText.setText("100");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherAgeText, gbc);
        final JLabel label9 = new JLabel();
        label9.setMinimumSize(new Dimension(150, 16));
        label9.setPreferredSize(new Dimension(150, 16));
        label9.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label9, gbc);
        teacherPhoneText = new JTextField();
        teacherPhoneText.setMinimumSize(new Dimension(150, 30));
        teacherPhoneText.setPreferredSize(new Dimension(150, 30));
        teacherPhoneText.setText("8578003366");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherPhoneText, gbc);
        addTeacherButton = new JButton();
        addTeacherButton.setText("Add Teacher");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(addTeacherButton, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(650, 265));
        scrollPane2.setPreferredSize(new Dimension(650, 265));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(scrollPane2, gbc);
        teacherTable = new JTable();
        teacherTable.setAutoResizeMode(1);
        teacherTable.setMinimumSize(new Dimension(500, 40));
        teacherTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        scrollPane2.setViewportView(teacherTable);
        teacherRemoveButton = new JButton();
        teacherRemoveButton.setText("Remove Teacher");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherRemoveButton, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer7, gbc);
        teacherSchoolIdBox = new JComboBox();
        teacherSchoolIdBox.setMinimumSize(new Dimension(150, 30));
        teacherSchoolIdBox.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(teacherSchoolIdBox, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer12, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 17;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer14, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer15, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer16, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 19;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel2.add(spacer18, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        peopleMangeTabbedPane.addTab("Add student", panel3);
        studentIdText = new JTextField();
        studentIdText.setMinimumSize(new Dimension(150, 30));
        studentIdText.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentIdText, gbc);
        studentNameText = new JTextField();
        studentNameText.setMinimumSize(new Dimension(150, 30));
        studentNameText.setPreferredSize(new Dimension(150, 30));
        studentNameText.setText("Abbott");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentNameText, gbc);
        studentAgeText = new JTextField();
        studentAgeText.setMinimumSize(new Dimension(150, 30));
        studentAgeText.setPreferredSize(new Dimension(150, 30));
        studentAgeText.setText("10");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentAgeText, gbc);
        studentAddressText = new JTextField();
        studentAddressText.setMinimumSize(new Dimension(150, 30));
        studentAddressText.setPreferredSize(new Dimension(150, 30));
        studentAddressText.setText("Boston Green House");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentAddressText, gbc);
        studentPhoneText = new JTextField();
        studentPhoneText.setMinimumSize(new Dimension(150, 30));
        studentPhoneText.setPreferredSize(new Dimension(150, 30));
        studentPhoneText.setText("8578008899");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentPhoneText, gbc);
        studentFatherNameText = new JTextField();
        studentFatherNameText.setMinimumSize(new Dimension(150, 30));
        studentFatherNameText.setPreferredSize(new Dimension(150, 30));
        studentFatherNameText.setText("God");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentFatherNameText, gbc);
        studentMotherNameText = new JTextField();
        studentMotherNameText.setMinimumSize(new Dimension(150, 30));
        studentMotherNameText.setPreferredSize(new Dimension(150, 30));
        studentMotherNameText.setText("Godness");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentMotherNameText, gbc);
        studentGradeText = new JTextField();
        studentGradeText.setMinimumSize(new Dimension(150, 30));
        studentGradeText.setPreferredSize(new Dimension(150, 30));
        studentGradeText.setText("5");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentGradeText, gbc);
        final JLabel label10 = new JLabel();
        label10.setMinimumSize(new Dimension(150, 16));
        label10.setPreferredSize(new Dimension(150, 16));
        label10.setText("ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label10, gbc);
        final JLabel label11 = new JLabel();
        label11.setMinimumSize(new Dimension(150, 16));
        label11.setPreferredSize(new Dimension(150, 16));
        label11.setText("School ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label11, gbc);
        final JLabel label12 = new JLabel();
        label12.setMinimumSize(new Dimension(150, 16));
        label12.setPreferredSize(new Dimension(150, 16));
        label12.setText("Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label12, gbc);
        final JLabel label13 = new JLabel();
        label13.setMinimumSize(new Dimension(150, 16));
        label13.setPreferredSize(new Dimension(150, 16));
        label13.setText("Age (month): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label13, gbc);
        final JLabel label14 = new JLabel();
        label14.setMinimumSize(new Dimension(150, 16));
        label14.setPreferredSize(new Dimension(150, 16));
        label14.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label14, gbc);
        final JLabel label15 = new JLabel();
        label15.setMinimumSize(new Dimension(150, 16));
        label15.setPreferredSize(new Dimension(150, 16));
        label15.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label15, gbc);
        final JLabel label16 = new JLabel();
        label16.setMinimumSize(new Dimension(150, 16));
        label16.setPreferredSize(new Dimension(150, 16));
        label16.setText("Father Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label16, gbc);
        final JLabel label17 = new JLabel();
        label17.setMinimumSize(new Dimension(150, 16));
        label17.setPreferredSize(new Dimension(150, 16));
        label17.setText("Mother Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label17, gbc);
        final JLabel label18 = new JLabel();
        label18.setMinimumSize(new Dimension(150, 16));
        label18.setPreferredSize(new Dimension(150, 16));
        label18.setText("Grade: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label18, gbc);
        addStudentButton = new JButton();
        addStudentButton.setText("Add Student");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(addStudentButton, gbc);
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setMinimumSize(new Dimension(650, 180));
        scrollPane3.setPreferredSize(new Dimension(650, 220));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane3, gbc);
        studentTable = new JTable();
        studentTable.setAutoResizeMode(0);
        scrollPane3.setViewportView(studentTable);
        studentRemoveButton = new JButton();
        studentRemoveButton.setText("Remove Student");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentRemoveButton, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer19, gbc);
        studentSchoolBox = new JComboBox();
        studentSchoolBox.setMinimumSize(new Dimension(150, 30));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        studentSchoolBox.setModel(defaultComboBoxModel1);
        studentSchoolBox.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(studentSchoolBox, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer20, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer21, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer22, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer23, gbc);
        final JPanel spacer24 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer24, gbc);
        final JPanel spacer25 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer25, gbc);
        final JPanel spacer26 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer26, gbc);
        final JPanel spacer27 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer27, gbc);
        final JPanel spacer28 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 17;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer28, gbc);
        final JPanel spacer29 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 19;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer29, gbc);
        final JPanel spacer30 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 21;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer30, gbc);
        final JPanel spacer31 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 23;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer31, gbc);
        final JPanel spacer32 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer32, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return peopleManagerPanel;
    }

}
