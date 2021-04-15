package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RoomManageWindows {
    private JList schoolList;
    private JTable classRoomTable;
    private JButton classRoomRemove;
    private JButton classRoomAdd;
    private JTable teacherTable;
    private JButton teacherAdd;
    private JButton teacherRemove;
    private JTable availableTeacherTable;
    private JTable studentTable;
    private JButton studentAdd;
    private JButton studentRemove;
    private JTable availableStudentTable;
    private JLabel teacherId;
    private JLabel t_schoolID;
    private JLabel teacherName;
    private JLabel teacherAge;
    private JLabel teacherAddress;
    private JLabel teacherPhone;
    private JLabel teacherCreadit;
    private JLabel lastTimeReview;
    private JLabel studentId;
    private JLabel studentSchoolId;
    private JLabel studentName;
    private JLabel studentAge;
    private JLabel studetnAddress;
    private JLabel studentPhone;
    private JLabel studentFather;
    private JLabel studetnMother;
    private JLabel studetnGrade;
    private JLabel roomId;
    private JLabel ratio;
    private JLabel maxGroups;
    private JLabel maxGroupSize;
    private JLabel minAge;
    private JLabel maxAge;
    private JPanel roomManagePanel;
    private JComboBox schoolBox;
    private DayCare dayCare;


//    public static void main(String[] args) {
//        DayCare dayCare = new DayCare();
//        dayCare.demo();
//        new RoomManageWindows(dayCare);
//    }

    public int getSchoolId() {
        School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
        int schoolId = item.getSchoolID();
        return schoolId;
    }

    public void updateSchoolBox() {
        List<School> list = dayCare.getSchools();
        List<String> schoolName = new ArrayList<String>();
        int rowLength = list.size();
        for (int i = 0; i < rowLength; i++) {
            School school = list.get(i);
            schoolName.add(school.getSchoolID() + "-" + school.getSchoolName());
        }
        DefaultComboBoxModel tableModel = new DefaultComboBoxModel(list.toArray());
        schoolBox.setModel(tableModel);
    }

    public void updateRoomTable() {
        School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
        int schoolId = item.getSchoolID();
        List<ClassRoom> list = dayCare.getRoomBySchool(schoolId);

        String columns[] = {"RoomId", "Min age", "Max age", "Ratio"};
        int rowLength = list.size();
        int columnsLength = columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            ClassRoom foritem = list.get(i);
            data[i][0] = String.valueOf(foritem.getRoomID());
            data[i][1] = String.valueOf(foritem.getStudentMinAgeMonths());
            data[i][2] = String.valueOf(foritem.getStudentMaxAgeMonths());
            data[i][3] = String.valueOf(foritem.getStudent2TeacherRatio());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        classRoomTable.setModel(tableModel);
    }

    public void updateTeacherTable(List<Person> list) {
        String Columns[] = {"ID", "Teacher Name", "Age(months)", "Phone", "Credit"};
        int rowLength;
        if (list != null) {
            rowLength = list.size();
        } else {
            rowLength = 0;
        }
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Teacher teacher = (Teacher) list.get(i);
            data[i][0] = String.valueOf(teacher.getPersonID());
            data[i][1] = teacher.getName();
            data[i][2] = String.valueOf(teacher.getAgeMonths());
            data[i][3] = teacher.getPhone();
            data[i][4] = String.valueOf(teacher.getCredit());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        teacherTable.setModel(tableModel);

    }

    public void updateStudentTable(List<Person> list) {
        String Columns[] = {"ID", "Student Name", "School ID", "Age(months)", "Phone"};
        int rowLength;
        if (list != null) {
            rowLength = list.size();
        } else {
            rowLength = 0;
        }
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Student item = (Student) list.get(i);
            data[i][0] = String.valueOf(item.getPersonID());
            data[i][1] = item.getName();
            data[i][2] = String.valueOf(item.getSchoolID());
            data[i][3] = String.valueOf(item.getAgeMonths());
            data[i][4] = item.getPhone();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        studentTable.setModel(tableModel);


    }

    public void updateAvailableStudentTable(List<Person> list) {
        String Columns[] = {"ID", "Student Name", "School ID", "Age(months)", "Phone"};
        int rowLength;
        if (list != null) {
            rowLength = list.size();
        } else {
            rowLength = 0;
        }

        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Student item = (Student) list.get(i);
            data[i][0] = String.valueOf(item.getPersonID());
            data[i][1] = item.getName();
            data[i][2] = String.valueOf(item.getSchoolID());
            data[i][3] = String.valueOf(item.getAgeMonths());
            data[i][4] = item.getPhone();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        availableStudentTable.setModel(tableModel);
    }

    public void updateAvailableTeacherTable(List<Person> list) {
        String Columns[] = {"ID", "Teacher Name", "Age(months)", "Phone", "Credit"};
        int rowLength;
        if (list != null) {
            rowLength = list.size();
        } else {
            rowLength = 0;
        }
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            Teacher teacher = (Teacher) list.get(i);
            data[i][0] = String.valueOf(teacher.getPersonID());
            data[i][1] = teacher.getName();
            data[i][2] = String.valueOf(teacher.getAgeMonths());
            data[i][3] = teacher.getPhone();
            data[i][4] = String.valueOf(teacher.getCredit());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        availableTeacherTable.setModel(tableModel);

    }

    public void listenerSetup() {
        schoolBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomTable();
            }
        });

        ListSelectionModel selectionModel = classRoomTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = classRoomTable.getSelectedRow();
                if (row != -1) {
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();

                    List<Person> list = dayCare.getStudentsByRoomIdAndSchoolId(roomId, schoolId);
                    updateStudentTable(list);
                    List<Person> list2 = dayCare.getTeachersByRoomIdAndSchoolId(roomId, schoolId);
                    updateTeacherTable(list2);
                    List<Person> list3 = dayCare.getAvailableStudents(schoolId);
                    updateAvailableStudentTable(list3);
                    List<Person> list4 = dayCare.getAvailableTeachers(schoolId);
                    updateAvailableTeacherTable(list4);

                    ClassRoom classRoom = dayCare.getClassRoomBySchoolIdAndRoomId(roomId, schoolId);
                    updateRoomInfo(classRoom);

                }
            }
        });

        ListSelectionModel selectionModel2 = teacherTable.getSelectionModel();
        selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = teacherTable.getSelectedRow();
                if (row != -1) {
                    availableTeacherTable.getSelectionModel().clearSelection();
                    int teacherId = Integer.valueOf((String) teacherTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    Teacher teacher = dayCare.getTeacherBySchoolIdAndTeacherId(teacherId, schoolId);
                    updateTeacherInfo(teacher);
                }


            }
        });

        ListSelectionModel selectionModel3 = studentTable.getSelectionModel();
        selectionModel3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = studentTable.getSelectedRow();
                if (row != -1) {
                    availableStudentTable.getSelectionModel().clearSelection();
                    int studentId = Integer.valueOf((String) studentTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    Student student = dayCare.getStudentBySchoolIdAndStudentId(studentId, schoolId);
                    updateStudentInfo(student);
                }
            }
        });

        ListSelectionModel selectionModel4 = availableTeacherTable.getSelectionModel();
        selectionModel4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel4.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = availableTeacherTable.getSelectedRow();
                if (row != -1) {
                    teacherTable.getSelectionModel().clearSelection();
                    int teacherId = Integer.valueOf((String) availableTeacherTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    Teacher teacher = dayCare.getTeacherBySchoolIdAndTeacherId(teacherId, schoolId);
                    updateTeacherInfo(teacher);
                }


            }
        });

        ListSelectionModel selectionModel5 = availableStudentTable.getSelectionModel();
        selectionModel5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel5.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = availableStudentTable.getSelectedRow();
                if (row != -1) {
                    studentTable.getSelectionModel().clearSelection();
                    int studentId = Integer.valueOf((String) availableStudentTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    Student student = dayCare.getStudentBySchoolIdAndStudentId(studentId, schoolId);
                    updateStudentInfo(student);
                }
            }
        });


        teacherAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = availableTeacherTable.getSelectedRow();
                if (row != -1) {
                    int teacherId = Integer.valueOf((String) availableTeacherTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(classRoomTable.getSelectedRow(), 0));

                    dayCare.addTeacherIntoRoom(teacherId, roomId, schoolId);
                    updateTeacherTableAndAvailableTable(schoolId, roomId);
                }
            }
        });

        studentAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = availableStudentTable.getSelectedRow();
                if (row != -1) {
                    int studentId = Integer.valueOf((String) availableStudentTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(classRoomTable.getSelectedRow(), 0));

                    dayCare.addStudentIntoRoom(studentId, roomId, schoolId);
                    updateStudentTableAndAvailableTable(schoolId, roomId);
                }
            }
        });

        teacherRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = teacherTable.getSelectedRow();
                if (row != -1) {
                    int teacherId = Integer.valueOf((String) teacherTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(classRoomTable.getSelectedRow(), 0));
                    dayCare.removeTeacherFromRoom(teacherId, roomId, schoolId);
                    updateTeacherTableAndAvailableTable(schoolId, roomId);
                }

            }
        });

        studentRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = studentTable.getSelectedRow();
                if (row != -1) {
                    int studentId = Integer.valueOf((String) studentTable.getValueAt(row, 0));
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(classRoomTable.getSelectedRow(), 0));
                    dayCare.removeStudentFromRoom(studentId, roomId, schoolId);
                    updateStudentTableAndAvailableTable(schoolId, roomId);
                }
            }
        });

        classRoomRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = classRoomTable.getSelectedRow();
                if (row != -1) {
                    School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                    int schoolId = item.getSchoolID();
                    int roomId = Integer.valueOf((String) classRoomTable.getValueAt(row, 0));
                    dayCare.removeRoom(roomId, schoolId);

                    updateRoomTable();
                    updateStudentTableAndAvailableTable(schoolId, roomId);
                    updateTeacherTableAndAvailableTable(schoolId, roomId);
                }

            }
        });

        classRoomAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void updateTeacherTableAndAvailableTable(int schoolId, int roomId) {
        List<Person> teachers = dayCare.getAvailableTeachers(schoolId);
        List<Person> list = dayCare.getTeachersByRoomIdAndSchoolId(roomId, schoolId);
        updateTeacherTable(list);
        updateAvailableTeacherTable(teachers);
    }

    public void updateStudentTableAndAvailableTable(int schoolId, int roomId) {
        List<Person> availableStudents = dayCare.getAvailableStudents(schoolId);
        List<Person> list = dayCare.getStudentsByRoomIdAndSchoolId(roomId, schoolId);
        updateStudentTable(list);
        updateAvailableStudentTable(availableStudents);
    }

    public void updateRoomInfo(ClassRoom classRoom) {
        roomId.setText(String.valueOf(classRoom.getRoomID()));
        ratio.setText(String.valueOf(classRoom.getStudent2TeacherRatio()));
        maxGroups.setText(String.valueOf(classRoom.getMaxGroups()));
        maxGroupSize.setText(String.valueOf(classRoom.getMaxGroupSize()));
        minAge.setText(String.valueOf(classRoom.getStudentMinAgeMonths()));
        maxAge.setText(String.valueOf(classRoom.getStudentMaxAgeMonths()));
    }

    public void updateStudentInfo(Student student) {
        studentId.setText(String.valueOf(student.getPersonID()));
        studentSchoolId.setText(String.valueOf(student.getSchoolID()));
        studentName.setText(String.valueOf(student.getName()));
        studentAge.setText(String.valueOf(student.getAgeMonths()));
        studetnAddress.setText(String.valueOf(student.getAddress()));
        studentPhone.setText(String.valueOf(student.getPhone()));
        studentFather.setText(String.valueOf(student.getFatherName()));
        studetnMother.setText(String.valueOf(student.getMotherName()));
        studetnGrade.setText(String.valueOf(student.getGrade()));
    }

    public void updateTeacherInfo(Teacher teacher) {
        teacherId.setText(String.valueOf(teacher.getPersonID()));
        t_schoolID.setText(String.valueOf(teacher.getSchoolID()));
        teacherName.setText(String.valueOf(teacher.getName()));
        teacherAge.setText(String.valueOf(teacher.getAgeMonths()));
        teacherAddress.setText(String.valueOf(teacher.getAddress()));
        teacherPhone.setText(String.valueOf(teacher.getPhone()));
        teacherCreadit.setText(String.valueOf(teacher.getCredit()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(teacher.getLastTimeAnnualReview());
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        lastTimeReview.setText(String.valueOf(sdf.format(calendar.getTime())));
    }

    public RoomManageWindows(DayCare dayCare) {
        this.dayCare = dayCare;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Room Manage");
        jFrame.setContentPane(roomManagePanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - roomManagePanel.getSize().width / 2, dim.height / 2 - roomManagePanel.getSize().height / 2);
        jFrame.setResizable(false);

        listenerSetup();
        updateSchoolBox();
        updateRoomTable();


        classRoomAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoomWindows(dayCare, RoomManageWindows.this);
            }
        });
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
        roomManagePanel = new JPanel();
        roomManagePanel.setLayout(new GridBagLayout());
        roomManagePanel.setMinimumSize(new Dimension(1346, 577));
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        roomManagePanel.add(spacer1, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-1));
        scrollPane1.setMinimumSize(new Dimension(200, 400));
        scrollPane1.setPreferredSize(new Dimension(200, 400));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(scrollPane1, gbc);
        classRoomTable = new JTable();
        classRoomTable.setAutoResizeMode(3);
        scrollPane1.setViewportView(classRoomTable);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        roomManagePanel.add(spacer2, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-856772));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 12, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setMinimumSize(new Dimension(100, 20));
        label1.setPreferredSize(new Dimension(100, 20));
        label1.setText("Teacher ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 12, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setMinimumSize(new Dimension(100, 20));
        label2.setPreferredSize(new Dimension(100, 20));
        label2.setText("School ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 12, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setMinimumSize(new Dimension(100, 20));
        label3.setPreferredSize(new Dimension(100, 20));
        label3.setText("Teacher Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 12, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setMinimumSize(new Dimension(100, 20));
        label4.setPreferredSize(new Dimension(100, 20));
        label4.setText("age(months):");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label4, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 12, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setMinimumSize(new Dimension(100, 20));
        label5.setPreferredSize(new Dimension(100, 20));
        label5.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label5, gbc);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 12, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setMinimumSize(new Dimension(100, 20));
        label6.setPreferredSize(new Dimension(100, 20));
        label6.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label6, gbc);
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 12, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setMinimumSize(new Dimension(100, 20));
        label7.setPreferredSize(new Dimension(100, 20));
        label7.setText("Credit: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label7, gbc);
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$(null, -1, 12, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setMinimumSize(new Dimension(100, 20));
        label8.setPreferredSize(new Dimension(100, 20));
        label8.setText("Last time annual review: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label8, gbc);
        teacherId = new JLabel();
        Font teacherIdFont = this.$$$getFont$$$(null, -1, 12, teacherId.getFont());
        if (teacherIdFont != null) teacherId.setFont(teacherIdFont);
        teacherId.setMinimumSize(new Dimension(100, 20));
        teacherId.setPreferredSize(new Dimension(100, 20));
        teacherId.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherId, gbc);
        teacherAddress = new JLabel();
        Font teacherAddressFont = this.$$$getFont$$$(null, -1, 12, teacherAddress.getFont());
        if (teacherAddressFont != null) teacherAddress.setFont(teacherAddressFont);
        teacherAddress.setMinimumSize(new Dimension(100, 20));
        teacherAddress.setPreferredSize(new Dimension(100, 20));
        teacherAddress.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherAddress, gbc);
        t_schoolID = new JLabel();
        Font t_schoolIDFont = this.$$$getFont$$$(null, -1, 12, t_schoolID.getFont());
        if (t_schoolIDFont != null) t_schoolID.setFont(t_schoolIDFont);
        t_schoolID.setMinimumSize(new Dimension(100, 20));
        t_schoolID.setPreferredSize(new Dimension(100, 20));
        t_schoolID.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(t_schoolID, gbc);
        teacherPhone = new JLabel();
        Font teacherPhoneFont = this.$$$getFont$$$(null, -1, 12, teacherPhone.getFont());
        if (teacherPhoneFont != null) teacherPhone.setFont(teacherPhoneFont);
        teacherPhone.setMinimumSize(new Dimension(100, 20));
        teacherPhone.setPreferredSize(new Dimension(100, 20));
        teacherPhone.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherPhone, gbc);
        teacherName = new JLabel();
        Font teacherNameFont = this.$$$getFont$$$(null, -1, 12, teacherName.getFont());
        if (teacherNameFont != null) teacherName.setFont(teacherNameFont);
        teacherName.setMinimumSize(new Dimension(100, 20));
        teacherName.setPreferredSize(new Dimension(100, 20));
        teacherName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherName, gbc);
        teacherCreadit = new JLabel();
        Font teacherCreaditFont = this.$$$getFont$$$(null, -1, 12, teacherCreadit.getFont());
        if (teacherCreaditFont != null) teacherCreadit.setFont(teacherCreaditFont);
        teacherCreadit.setMinimumSize(new Dimension(100, 20));
        teacherCreadit.setPreferredSize(new Dimension(100, 20));
        teacherCreadit.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherCreadit, gbc);
        teacherAge = new JLabel();
        Font teacherAgeFont = this.$$$getFont$$$(null, -1, 12, teacherAge.getFont());
        if (teacherAgeFont != null) teacherAge.setFont(teacherAgeFont);
        teacherAge.setMinimumSize(new Dimension(100, 20));
        teacherAge.setPreferredSize(new Dimension(100, 20));
        teacherAge.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherAge, gbc);
        lastTimeReview = new JLabel();
        Font lastTimeReviewFont = this.$$$getFont$$$(null, -1, 12, lastTimeReview.getFont());
        if (lastTimeReviewFont != null) lastTimeReview.setFont(lastTimeReviewFont);
        lastTimeReview.setMinimumSize(new Dimension(100, 20));
        lastTimeReview.setPreferredSize(new Dimension(100, 20));
        lastTimeReview.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(lastTimeReview, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(new Color(-891225));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel2, gbc);
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, -1, 12, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setMinimumSize(new Dimension(100, 20));
        label9.setPreferredSize(new Dimension(100, 20));
        label9.setText("Room ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label9, gbc);
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, -1, 12, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setMinimumSize(new Dimension(100, 20));
        label10.setPreferredSize(new Dimension(100, 20));
        label10.setText("Max groups: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label10, gbc);
        roomId = new JLabel();
        Font roomIdFont = this.$$$getFont$$$(null, -1, 12, roomId.getFont());
        if (roomIdFont != null) roomId.setFont(roomIdFont);
        roomId.setMinimumSize(new Dimension(100, 20));
        roomId.setPreferredSize(new Dimension(100, 20));
        roomId.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(roomId, gbc);
        maxGroups = new JLabel();
        Font maxGroupsFont = this.$$$getFont$$$(null, -1, 12, maxGroups.getFont());
        if (maxGroupsFont != null) maxGroups.setFont(maxGroupsFont);
        maxGroups.setMinimumSize(new Dimension(100, 20));
        maxGroups.setPreferredSize(new Dimension(100, 20));
        maxGroups.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(maxGroups, gbc);
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, -1, 12, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setMinimumSize(new Dimension(100, 20));
        label11.setPreferredSize(new Dimension(100, 20));
        label11.setText("Min age: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label11, gbc);
        minAge = new JLabel();
        Font minAgeFont = this.$$$getFont$$$(null, -1, 12, minAge.getFont());
        if (minAgeFont != null) minAge.setFont(minAgeFont);
        minAge.setMinimumSize(new Dimension(100, 20));
        minAge.setPreferredSize(new Dimension(100, 20));
        minAge.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(minAge, gbc);
        final JLabel label12 = new JLabel();
        Font label12Font = this.$$$getFont$$$(null, -1, 12, label12.getFont());
        if (label12Font != null) label12.setFont(label12Font);
        label12.setMinimumSize(new Dimension(100, 20));
        label12.setPreferredSize(new Dimension(100, 20));
        label12.setText("Max age: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label12, gbc);
        maxAge = new JLabel();
        Font maxAgeFont = this.$$$getFont$$$(null, -1, 12, maxAge.getFont());
        if (maxAgeFont != null) maxAge.setFont(maxAgeFont);
        maxAge.setMinimumSize(new Dimension(100, 20));
        maxAge.setPreferredSize(new Dimension(100, 20));
        maxAge.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(maxAge, gbc);
        final JLabel label13 = new JLabel();
        Font label13Font = this.$$$getFont$$$(null, -1, 12, label13.getFont());
        if (label13Font != null) label13.setFont(label13Font);
        label13.setMinimumSize(new Dimension(100, 20));
        label13.setPreferredSize(new Dimension(100, 20));
        label13.setText("Ratio(student to teacher): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label13, gbc);
        ratio = new JLabel();
        Font ratioFont = this.$$$getFont$$$(null, -1, 12, ratio.getFont());
        if (ratioFont != null) ratio.setFont(ratioFont);
        ratio.setMinimumSize(new Dimension(100, 20));
        ratio.setPreferredSize(new Dimension(100, 20));
        ratio.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(ratio, gbc);
        final JLabel label14 = new JLabel();
        Font label14Font = this.$$$getFont$$$(null, -1, 12, label14.getFont());
        if (label14Font != null) label14.setFont(label14Font);
        label14.setMinimumSize(new Dimension(100, 20));
        label14.setPreferredSize(new Dimension(100, 20));
        label14.setText("Max group size: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label14, gbc);
        maxGroupSize = new JLabel();
        Font maxGroupSizeFont = this.$$$getFont$$$(null, -1, 12, maxGroupSize.getFont());
        if (maxGroupSizeFont != null) maxGroupSize.setFont(maxGroupSizeFont);
        maxGroupSize.setMinimumSize(new Dimension(100, 20));
        maxGroupSize.setPreferredSize(new Dimension(100, 20));
        maxGroupSize.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(maxGroupSize, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        roomManagePanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        roomManagePanel.add(spacer4, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel3, gbc);
        classRoomAdd = new JButton();
        classRoomAdd.setMinimumSize(new Dimension(100, 30));
        classRoomAdd.setPreferredSize(new Dimension(100, 30));
        classRoomAdd.setText("Add");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(classRoomAdd, gbc);
        classRoomRemove = new JButton();
        classRoomRemove.setMaximumSize(new Dimension(100, 30));
        classRoomRemove.setMinimumSize(new Dimension(100, 30));
        classRoomRemove.setPreferredSize(new Dimension(100, 30));
        classRoomRemove.setText("Remove");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(classRoomRemove, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer6, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel4, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(400, 190));
        scrollPane2.setPreferredSize(new Dimension(400, 190));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(scrollPane2, gbc);
        availableTeacherTable = new JTable();
        scrollPane2.setViewportView(availableTeacherTable);
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setMinimumSize(new Dimension(400, 190));
        scrollPane3.setPreferredSize(new Dimension(400, 190));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(scrollPane3, gbc);
        teacherTable = new JTable();
        teacherTable.setAutoResizeMode(3);
        scrollPane3.setViewportView(teacherTable);
        teacherAdd = new JButton();
        teacherAdd.setText("<<");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(teacherAdd, gbc);
        teacherRemove = new JButton();
        teacherRemove.setText(">>");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(teacherRemove, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel4.add(spacer7, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel5, gbc);
        final JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setMinimumSize(new Dimension(400, 150));
        scrollPane4.setPreferredSize(new Dimension(400, 150));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel5.add(scrollPane4, gbc);
        studentTable = new JTable();
        studentTable.setAutoResizeMode(3);
        scrollPane4.setViewportView(studentTable);
        studentAdd = new JButton();
        studentAdd.setText("<<");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(studentAdd, gbc);
        studentRemove = new JButton();
        studentRemove.setText(">>");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(studentRemove, gbc);
        final JScrollPane scrollPane5 = new JScrollPane();
        scrollPane5.setMinimumSize(new Dimension(400, 150));
        scrollPane5.setPreferredSize(new Dimension(400, 150));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel5.add(scrollPane5, gbc);
        availableStudentTable = new JTable();
        scrollPane5.setViewportView(availableStudentTable);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel5.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        roomManagePanel.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        roomManagePanel.add(spacer10, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        panel6.setBackground(new Color(-12667150));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        roomManagePanel.add(panel6, gbc);
        final JLabel label15 = new JLabel();
        Font label15Font = this.$$$getFont$$$(null, -1, 12, label15.getFont());
        if (label15Font != null) label15.setFont(label15Font);
        label15.setMinimumSize(new Dimension(100, 20));
        label15.setPreferredSize(new Dimension(100, 20));
        label15.setText("Student ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label15, gbc);
        final JLabel label16 = new JLabel();
        Font label16Font = this.$$$getFont$$$(null, -1, 12, label16.getFont());
        if (label16Font != null) label16.setFont(label16Font);
        label16.setMinimumSize(new Dimension(100, 20));
        label16.setPreferredSize(new Dimension(100, 20));
        label16.setText("School ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label16, gbc);
        studentSchoolId = new JLabel();
        Font studentSchoolIdFont = this.$$$getFont$$$(null, -1, 12, studentSchoolId.getFont());
        if (studentSchoolIdFont != null) studentSchoolId.setFont(studentSchoolIdFont);
        studentSchoolId.setMinimumSize(new Dimension(100, 20));
        studentSchoolId.setPreferredSize(new Dimension(100, 20));
        studentSchoolId.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentSchoolId, gbc);
        final JLabel label17 = new JLabel();
        Font label17Font = this.$$$getFont$$$(null, -1, 12, label17.getFont());
        if (label17Font != null) label17.setFont(label17Font);
        label17.setMinimumSize(new Dimension(100, 20));
        label17.setPreferredSize(new Dimension(100, 20));
        label17.setText("Student Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label17, gbc);
        studentName = new JLabel();
        Font studentNameFont = this.$$$getFont$$$(null, -1, 12, studentName.getFont());
        if (studentNameFont != null) studentName.setFont(studentNameFont);
        studentName.setMinimumSize(new Dimension(100, 20));
        studentName.setPreferredSize(new Dimension(100, 20));
        studentName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentName, gbc);
        final JLabel label18 = new JLabel();
        Font label18Font = this.$$$getFont$$$(null, -1, 12, label18.getFont());
        if (label18Font != null) label18.setFont(label18Font);
        label18.setMinimumSize(new Dimension(100, 20));
        label18.setPreferredSize(new Dimension(100, 20));
        label18.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label18, gbc);
        final JLabel label19 = new JLabel();
        Font label19Font = this.$$$getFont$$$(null, -1, 12, label19.getFont());
        if (label19Font != null) label19.setFont(label19Font);
        label19.setMinimumSize(new Dimension(100, 20));
        label19.setPreferredSize(new Dimension(100, 20));
        label19.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label19, gbc);
        studentPhone = new JLabel();
        Font studentPhoneFont = this.$$$getFont$$$(null, -1, 12, studentPhone.getFont());
        if (studentPhoneFont != null) studentPhone.setFont(studentPhoneFont);
        studentPhone.setMinimumSize(new Dimension(100, 20));
        studentPhone.setPreferredSize(new Dimension(100, 20));
        studentPhone.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentPhone, gbc);
        final JLabel label20 = new JLabel();
        Font label20Font = this.$$$getFont$$$(null, -1, 12, label20.getFont());
        if (label20Font != null) label20.setFont(label20Font);
        label20.setMinimumSize(new Dimension(100, 20));
        label20.setPreferredSize(new Dimension(100, 20));
        label20.setText("Father name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label20, gbc);
        studentFather = new JLabel();
        Font studentFatherFont = this.$$$getFont$$$(null, -1, 12, studentFather.getFont());
        if (studentFatherFont != null) studentFather.setFont(studentFatherFont);
        studentFather.setMinimumSize(new Dimension(100, 20));
        studentFather.setPreferredSize(new Dimension(100, 20));
        studentFather.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentFather, gbc);
        final JLabel label21 = new JLabel();
        Font label21Font = this.$$$getFont$$$(null, -1, 12, label21.getFont());
        if (label21Font != null) label21.setFont(label21Font);
        label21.setMinimumSize(new Dimension(100, 20));
        label21.setPreferredSize(new Dimension(100, 20));
        label21.setText("Mother name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label21, gbc);
        studentId = new JLabel();
        Font studentIdFont = this.$$$getFont$$$(null, -1, 12, studentId.getFont());
        if (studentIdFont != null) studentId.setFont(studentIdFont);
        studentId.setMinimumSize(new Dimension(100, 20));
        studentId.setPreferredSize(new Dimension(100, 20));
        studentId.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentId, gbc);
        studetnAddress = new JLabel();
        Font studetnAddressFont = this.$$$getFont$$$(null, -1, 12, studetnAddress.getFont());
        if (studetnAddressFont != null) studetnAddress.setFont(studetnAddressFont);
        studetnAddress.setMinimumSize(new Dimension(100, 20));
        studetnAddress.setPreferredSize(new Dimension(100, 20));
        studetnAddress.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studetnAddress, gbc);
        final JLabel label22 = new JLabel();
        Font label22Font = this.$$$getFont$$$(null, -1, 12, label22.getFont());
        if (label22Font != null) label22.setFont(label22Font);
        label22.setMinimumSize(new Dimension(100, 20));
        label22.setPreferredSize(new Dimension(100, 20));
        label22.setText("Age(months): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label22, gbc);
        final JLabel label23 = new JLabel();
        Font label23Font = this.$$$getFont$$$(null, -1, 12, label23.getFont());
        if (label23Font != null) label23.setFont(label23Font);
        label23.setMinimumSize(new Dimension(100, 20));
        label23.setPreferredSize(new Dimension(100, 20));
        label23.setText("Grede: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(label23, gbc);
        studetnGrade = new JLabel();
        Font studetnGradeFont = this.$$$getFont$$$(null, -1, 12, studetnGrade.getFont());
        if (studetnGradeFont != null) studetnGrade.setFont(studetnGradeFont);
        studetnGrade.setMinimumSize(new Dimension(100, 20));
        studetnGrade.setPreferredSize(new Dimension(100, 20));
        studetnGrade.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studetnGrade, gbc);
        studetnMother = new JLabel();
        Font studetnMotherFont = this.$$$getFont$$$(null, -1, 12, studetnMother.getFont());
        if (studetnMotherFont != null) studetnMother.setFont(studetnMotherFont);
        studetnMother.setMinimumSize(new Dimension(100, 20));
        studetnMother.setPreferredSize(new Dimension(100, 20));
        studetnMother.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studetnMother, gbc);
        studentAge = new JLabel();
        Font studentAgeFont = this.$$$getFont$$$(null, -1, 12, studentAge.getFont());
        if (studentAgeFont != null) studentAge.setFont(studentAgeFont);
        studentAge.setMinimumSize(new Dimension(100, 20));
        studentAge.setPreferredSize(new Dimension(100, 20));
        studentAge.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel6.add(studentAge, gbc);
        schoolBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        roomManagePanel.add(schoolBox, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return roomManagePanel;
    }

}
