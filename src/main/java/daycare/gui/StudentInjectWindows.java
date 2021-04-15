package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentInjectWindows {
    private JTable immunizationRecordTable;
    private JTable immunizationDateTable;
    private JTextField studentIdText;
    private JButton studentFindButton;
    private JButton injectRecordButton;
    private JLabel studentIdLabel;
    private JLabel studentNameLabel;
    private JLabel studentSchoolLabel;
    private JLabel studentAgeLabel;
    private JLabel studentAddressLabel;
    private JLabel studentPhoneLabel;
    private JLabel studentFatherNameLabel;
    private JLabel studentMotherNameLabel;
    private JLabel studentGradeLabel;
    private JComboBox studentSchoolBox;
    private JComboBox vaccineBox;
    private JPanel studentInjectPanel;
    private DayCare dayCare;


//    public static void main(String[] args) {
//        DayCare dayCare = new DayCare();
//        dayCare.demo();
//        new StudentInjectWindows(dayCare);
//    }

    public void updateVaccineBox() {
        int vaccineSize = dayCare.getTypes().size();
        List<ImmunizationType> vaccines = dayCare.getTypes();
        DefaultComboBoxModel model = new DefaultComboBoxModel(vaccines.toArray());

        vaccineBox.setModel(model);
    }

    public void updateSchoolBox() {
        int schoolSize = dayCare.getSchools().size();
        List<School> schools = dayCare.getSchools();
        DefaultComboBoxModel model = new DefaultComboBoxModel(schools.toArray());

        studentSchoolBox.setModel(model);
    }

    public void updateTables() {

        String immunizationRecordColumns[] = {"RecordID", "School ID", "Student ID", "Vaccine ID", "Date"};
        List<ImmunizationRecord> records = dayCare.getRecords();
        int rowLength = records.size();
        int columnsLength = immunizationRecordColumns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            ImmunizationRecord vaccineNotification = records.get(i);
            data[i][0] = String.valueOf(vaccineNotification.getRecordID());
            data[i][1] = String.valueOf(vaccineNotification.getSchoolID());
            data[i][2] = String.valueOf(vaccineNotification.getPersonID());
            data[i][3] = String.valueOf(vaccineNotification.getVaccineID());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vaccineNotification.getDate());
            DateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy");
            data[i][4] = String.valueOf(dateFormatter.format(calendar.getTime()));
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, immunizationRecordColumns);
        immunizationRecordTable.setModel(tableModel);


        String notificationColumns[] = {"Notification ID", "School ID", "Student ID", "Vaccine ID", "Due"};
        List<VaccineNotification> notificationList = dayCare.getNotifications();
        rowLength = notificationList.size();
        columnsLength = notificationColumns.length;
        data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            VaccineNotification vaccineNotification = notificationList.get(i);
            data[i][0] = String.valueOf(vaccineNotification.getNotificationId());
            data[i][1] = String.valueOf(vaccineNotification.getSchoolID());
            data[i][2] = String.valueOf(vaccineNotification.getPersonID());
            data[i][3] = String.valueOf(vaccineNotification.getVaccineID());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vaccineNotification.getDue());
            DateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy");
            data[i][4] = String.valueOf(dateFormatter.format(calendar.getTime()));
        }
        tableModel = new DefaultTableModel(data, notificationColumns);
        immunizationDateTable.setModel(tableModel);


    }

    public void recordInject() {
        int id = Integer.parseInt(studentIdText.getText());
        int vid = ((ImmunizationType) vaccineBox.getItemAt(vaccineBox.getSelectedIndex())).getVaccineID();
        int schoolId = ((School) studentSchoolBox.getItemAt(studentSchoolBox.getSelectedIndex())).getSchoolID();
        if (id != -1 && vid != -1 && schoolId != -1) {
            dayCare.studentInject(schoolId, id, vid, System.currentTimeMillis());
        }
    }

    public void updateStudentInfo(Person student) {
        if (student != null) {
            Student s = (Student) student;
            studentIdLabel.setText(String.valueOf(s.getPersonID()));
            studentNameLabel.setText(s.getName());
            studentSchoolLabel.setText(String.valueOf(s.getSchoolID()));
            studentAgeLabel.setText(String.valueOf(s.getAgeMonths()));
            studentAddressLabel.setText(s.getAddress());
            studentPhoneLabel.setText(s.getPhone());
            studentFatherNameLabel.setText(s.getFatherName());
            studentMotherNameLabel.setText(s.getMotherName());
            studentGradeLabel.setText(String.valueOf(s.getGrade()));
        }
    }

    public void listenerSetup() {
        studentFindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(studentIdText.getText());
                int schoolId = ((School) studentSchoolBox.getItemAt(studentSchoolBox.getSelectedIndex())).getSchoolID();
                Person person = dayCare.getStudentBySchoolIdAndStudentId(id, schoolId);
                updateStudentInfo(person);
            }
        });
        injectRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordInject();
                updateTables();
            }
        });
    }

    public StudentInjectWindows(DayCare dayCare) {
        this.dayCare = dayCare;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Student Inject Record Manage");
        jFrame.setContentPane(studentInjectPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - studentInjectPanel.getSize().width / 2, dim.height / 2 - studentInjectPanel.getSize().height / 2);
        jFrame.setResizable(false);


        updateTables();
        updateSchoolBox();
        updateVaccineBox();
        listenerSetup();

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
        studentInjectPanel = new JPanel();
        studentInjectPanel.setLayout(new GridBagLayout());
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(400, 250));
        scrollPane1.setPreferredSize(new Dimension(453, 250));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        studentInjectPanel.add(scrollPane1, gbc);
        immunizationRecordTable = new JTable();
        immunizationRecordTable.setAutoResizeMode(3);
        scrollPane1.setViewportView(immunizationRecordTable);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        studentInjectPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        studentInjectPanel.add(spacer2, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(400, 200));
        scrollPane2.setPreferredSize(new Dimension(453, 200));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        studentInjectPanel.add(scrollPane2, gbc);
        immunizationDateTable = new JTable();
        immunizationDateTable.setAutoResizeMode(3);
        scrollPane2.setViewportView(immunizationDateTable);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setMinimumSize(new Dimension(300, 330));
        panel1.setPreferredSize(new Dimension(300, 302));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        studentInjectPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Student ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        studentIdText = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(studentIdText, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Vaccine List: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        studentFindButton = new JButton();
        studentFindButton.setText("Student Find");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(studentFindButton, gbc);
        injectRecordButton = new JButton();
        injectRecordButton.setText("Inject Record");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(injectRecordButton, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(new Color(-14159140));
        panel2.setMinimumSize(new Dimension(300, 180));
        panel2.setPreferredSize(new Dimension(300, 180));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Student ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label3, gbc);
        studentIdLabel = new JLabel();
        Font studentIdLabelFont = this.$$$getFont$$$(null, -1, 14, studentIdLabel.getFont());
        if (studentIdLabelFont != null) studentIdLabel.setFont(studentIdLabelFont);
        studentIdLabel.setMinimumSize(new Dimension(100, 20));
        studentIdLabel.setPreferredSize(new Dimension(100, 20));
        studentIdLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentIdLabel, gbc);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label4, gbc);
        studentNameLabel = new JLabel();
        Font studentNameLabelFont = this.$$$getFont$$$(null, -1, 14, studentNameLabel.getFont());
        if (studentNameLabelFont != null) studentNameLabel.setFont(studentNameLabelFont);
        studentNameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentNameLabel, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("School ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label5, gbc);
        studentSchoolLabel = new JLabel();
        Font studentSchoolLabelFont = this.$$$getFont$$$(null, -1, 14, studentSchoolLabel.getFont());
        if (studentSchoolLabelFont != null) studentSchoolLabel.setFont(studentSchoolLabelFont);
        studentSchoolLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentSchoolLabel, gbc);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 14, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Age(month): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label6, gbc);
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 14, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label7, gbc);
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$(null, -1, 14, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label8, gbc);
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, -1, 14, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setText("Father Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label9, gbc);
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, -1, 14, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setText("Monther Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label10, gbc);
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, -1, 14, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("Grade: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label11, gbc);
        studentAgeLabel = new JLabel();
        Font studentAgeLabelFont = this.$$$getFont$$$(null, -1, 14, studentAgeLabel.getFont());
        if (studentAgeLabelFont != null) studentAgeLabel.setFont(studentAgeLabelFont);
        studentAgeLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentAgeLabel, gbc);
        studentAddressLabel = new JLabel();
        Font studentAddressLabelFont = this.$$$getFont$$$(null, -1, 14, studentAddressLabel.getFont());
        if (studentAddressLabelFont != null) studentAddressLabel.setFont(studentAddressLabelFont);
        studentAddressLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentAddressLabel, gbc);
        studentPhoneLabel = new JLabel();
        Font studentPhoneLabelFont = this.$$$getFont$$$(null, -1, 14, studentPhoneLabel.getFont());
        if (studentPhoneLabelFont != null) studentPhoneLabel.setFont(studentPhoneLabelFont);
        studentPhoneLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentPhoneLabel, gbc);
        studentFatherNameLabel = new JLabel();
        Font studentFatherNameLabelFont = this.$$$getFont$$$(null, -1, 14, studentFatherNameLabel.getFont());
        if (studentFatherNameLabelFont != null) studentFatherNameLabel.setFont(studentFatherNameLabelFont);
        studentFatherNameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentFatherNameLabel, gbc);
        studentMotherNameLabel = new JLabel();
        Font studentMotherNameLabelFont = this.$$$getFont$$$(null, -1, 14, studentMotherNameLabel.getFont());
        if (studentMotherNameLabelFont != null) studentMotherNameLabel.setFont(studentMotherNameLabelFont);
        studentMotherNameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentMotherNameLabel, gbc);
        studentGradeLabel = new JLabel();
        Font studentGradeLabelFont = this.$$$getFont$$$(null, -1, 14, studentGradeLabel.getFont());
        if (studentGradeLabelFont != null) studentGradeLabel.setFont(studentGradeLabelFont);
        studentGradeLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(studentGradeLabel, gbc);
        final JLabel label12 = new JLabel();
        label12.setText("Schools List: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label12, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);
        studentSchoolBox = new JComboBox();
        studentSchoolBox.setMinimumSize(new Dimension(78, 30));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        studentSchoolBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(studentSchoolBox, gbc);
        vaccineBox = new JComboBox();
        vaccineBox.setMinimumSize(new Dimension(78, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(vaccineBox, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer7, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("Next Immunization Date");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        studentInjectPanel.add(label13, gbc);
        final JLabel label14 = new JLabel();
        label14.setText("Immunization Records");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        studentInjectPanel.add(label14, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        studentInjectPanel.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        studentInjectPanel.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        studentInjectPanel.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        studentInjectPanel.add(spacer11, gbc);
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
        return studentInjectPanel;
    }

}
