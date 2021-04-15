package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.ImmunizationRules;
import daycare.entity.Person;
import daycare.entity.School;
import daycare.entity.Teacher;

import javax.swing.*;
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

public class AnnualReviewWindows {
    private JTable annualReviewTable;
    private JComboBox schoolBox;
    private JButton annualReviewButton;
    private JTextField teacherIdText;
    private JButton findTeacherButton;
    private JLabel teacherIDLabel;
    private JLabel teacherNameLabel;
    private JLabel teacherAddressLabel;
    private JLabel teacherCreditLabel;
    private JLabel teacherSchoolLabel;
    private JLabel teacherAgeLabel;
    private JLabel teacherPhoneLabel;
    private JLabel teacherLastTimeLabel;
    private JPanel annualReviewPanel;
    private DayCare dayCare;

//    public static void main(String[] args) {
//        DayCare dayCare = new DayCare();
//        dayCare.demo();
//        new AnnualReviewWindows(dayCare);
//    }

    public void updateTeacherInfo(Person teacher) {
        if (teacher != null) {
            Teacher item = (Teacher) teacher;
            teacherIDLabel.setText(String.valueOf(item.getPersonID()));
            teacherSchoolLabel.setText(String.valueOf(item.getSchoolID()));
            teacherNameLabel.setText(String.valueOf(item.getName()));
            teacherAgeLabel.setText(String.valueOf(item.getAgeMonths()));
            teacherAddressLabel.setText(String.valueOf(item.getAddress()));
            teacherPhoneLabel.setText(String.valueOf(item.getPhone()));
            teacherCreditLabel.setText(String.valueOf(item.getCredit()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(item.getLastTimeAnnualReview());
            SimpleDateFormat dtf = new SimpleDateFormat("d MMM yyyy");
            teacherLastTimeLabel.setText(String.valueOf(dtf.format(calendar.getTime())));
        }
    }

    public void updateSchoolList() {
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

    public void updateAnnualReviewTable() {
        School item = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
        int schoolId = item.getSchoolID();
        List<Teacher> list = dayCare.getExpireTeachersBySchoolIdAndDays(schoolId, 31, 1);

        String Columns[] = {"ID", "Next Review Date", "Teacher Name", "Teacher ID", "School ID", "Age(months)", "Address", "Phone", "Credit"};

        int rowLength = list.size();
        int columnsLength = Columns.length;
        String[][] data = new String[rowLength][columnsLength];
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < rowLength; i++) {
            Teacher teacher = (Teacher) list.get(i);
            long date = teacher.getLastTimeAnnualReview();
            calendar.setTimeInMillis(date);
            calendar.add(Calendar.YEAR, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
            data[i][0] = String.valueOf(teacher.getSchoolID());
            data[i][1] = sdf.format(calendar.getTime());
            data[i][2] = teacher.getName();
            data[i][3] = String.valueOf(teacher.getPersonID());
            data[i][4] = String.valueOf(teacher.getSchoolID());
            data[i][5] = String.valueOf(teacher.getAgeMonths());
            data[i][6] = teacher.getAddress();
            data[i][7] = teacher.getPhone();
            data[i][8] = String.valueOf(teacher.getCredit());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, Columns);
        annualReviewTable.setModel(tableModel);
    }

    public void recordReview() {
        int id = Integer.parseInt(teacherIDLabel.getText());
        if (id != -1) {
            dayCare.teacherAttendAnnualReview(id, System.currentTimeMillis());
        }
    }

    public void listenerSetup() {
        schoolBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnnualReviewTable();
            }
        });
        findTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                School school = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                String id = teacherIdText.getText();
                if (!id.equals("")) {
                    Teacher teacher = (Teacher) dayCare.getTeacherById(Integer.parseInt(id), school.getSchoolID());
                    updateTeacherInfo(teacher);
                }
            }
        });

        annualReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                School school = (School) schoolBox.getItemAt(schoolBox.getSelectedIndex());
                String id = teacherIdText.getText();
                if (!id.equals("")) {
                    recordReview();
                    Teacher teacher = (Teacher) dayCare.getTeacherById(Integer.parseInt(id), school.getSchoolID());
                    updateTeacherInfo(teacher);
                    updateAnnualReviewTable();
                }
            }
        });
    }

    public AnnualReviewWindows(DayCare dayCare) {
        this.dayCare = dayCare;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Annual Review");
        jFrame.setContentPane(annualReviewPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - annualReviewPanel.getSize().width / 2, dim.height / 2 - annualReviewPanel.getSize().height / 2);
        jFrame.setResizable(false);
//        annualReviewTable.setMa
//        annualReviewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        updateSchoolList();
        updateAnnualReviewTable();
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
        annualReviewPanel = new JPanel();
        annualReviewPanel.setLayout(new GridBagLayout());
        annualReviewPanel.setMinimumSize(new Dimension(850, 310));
        annualReviewPanel.setPreferredSize(new Dimension(880, 310));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(300, 250));
        scrollPane1.setPreferredSize(new Dimension(300, 250));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 9;
        gbc.fill = GridBagConstraints.BOTH;
        annualReviewPanel.add(scrollPane1, gbc);
        annualReviewTable = new JTable();
        annualReviewTable.setAutoResizeMode(0);
        scrollPane1.setViewportView(annualReviewTable);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(spacer1, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-14159140));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        annualReviewPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        teacherIDLabel = new JLabel();
        Font teacherIDLabelFont = this.$$$getFont$$$(null, -1, 14, teacherIDLabel.getFont());
        if (teacherIDLabelFont != null) teacherIDLabel.setFont(teacherIDLabelFont);
        teacherIDLabel.setMinimumSize(new Dimension(150, 20));
        teacherIDLabel.setPreferredSize(new Dimension(150, 20));
        teacherIDLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherIDLabel, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("School ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        teacherSchoolLabel = new JLabel();
        Font teacherSchoolLabelFont = this.$$$getFont$$$(null, -1, 14, teacherSchoolLabel.getFont());
        if (teacherSchoolLabelFont != null) teacherSchoolLabel.setFont(teacherSchoolLabelFont);
        teacherSchoolLabel.setMaximumSize(new Dimension(150, 20));
        teacherSchoolLabel.setMinimumSize(new Dimension(150, 20));
        teacherSchoolLabel.setPreferredSize(new Dimension(150, 20));
        teacherSchoolLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherSchoolLabel, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        teacherNameLabel = new JLabel();
        Font teacherNameLabelFont = this.$$$getFont$$$(null, -1, 14, teacherNameLabel.getFont());
        if (teacherNameLabelFont != null) teacherNameLabel.setFont(teacherNameLabelFont);
        teacherNameLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherNameLabel, gbc);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Age(month): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label4, gbc);
        teacherAgeLabel = new JLabel();
        Font teacherAgeLabelFont = this.$$$getFont$$$(null, -1, 14, teacherAgeLabel.getFont());
        if (teacherAgeLabelFont != null) teacherAgeLabel.setFont(teacherAgeLabelFont);
        teacherAgeLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherAgeLabel, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Address: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label5, gbc);
        teacherAddressLabel = new JLabel();
        Font teacherAddressLabelFont = this.$$$getFont$$$(null, -1, 14, teacherAddressLabel.getFont());
        if (teacherAddressLabelFont != null) teacherAddressLabel.setFont(teacherAddressLabelFont);
        teacherAddressLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherAddressLabel, gbc);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 14, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Phone: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label6, gbc);
        teacherPhoneLabel = new JLabel();
        Font teacherPhoneLabelFont = this.$$$getFont$$$(null, -1, 14, teacherPhoneLabel.getFont());
        if (teacherPhoneLabelFont != null) teacherPhoneLabel.setFont(teacherPhoneLabelFont);
        teacherPhoneLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherPhoneLabel, gbc);
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 14, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setText("Credit: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label7, gbc);
        teacherCreditLabel = new JLabel();
        Font teacherCreditLabelFont = this.$$$getFont$$$(null, -1, 14, teacherCreditLabel.getFont());
        if (teacherCreditLabelFont != null) teacherCreditLabel.setFont(teacherCreditLabelFont);
        teacherCreditLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherCreditLabel, gbc);
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$(null, -1, 14, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setText("Last time annual review: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label8, gbc);
        teacherLastTimeLabel = new JLabel();
        Font teacherLastTimeLabelFont = this.$$$getFont$$$(null, -1, 14, teacherLastTimeLabel.getFont());
        if (teacherLastTimeLabelFont != null) teacherLastTimeLabel.setFont(teacherLastTimeLabelFont);
        teacherLastTimeLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(teacherLastTimeLabel, gbc);
        schoolBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(schoolBox, gbc);
        teacherIdText = new JTextField();
        teacherIdText.setMinimumSize(new Dimension(49, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(teacherIdText, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("School: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        annualReviewPanel.add(label9, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        annualReviewPanel.add(label10, gbc);
        findTeacherButton = new JButton();
        findTeacherButton.setText("Find Teacher");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(findTeacherButton, gbc);
        annualReviewButton = new JButton();
        annualReviewButton.setText("Annual Review");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(annualReviewButton, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Annual Review Notification");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        annualReviewPanel.add(label11, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        annualReviewPanel.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        annualReviewPanel.add(spacer9, gbc);
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
        return annualReviewPanel;
    }

}
