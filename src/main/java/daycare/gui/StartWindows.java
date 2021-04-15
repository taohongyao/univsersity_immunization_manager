package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.Person;
import daycare.entity.Teacher;
import daycare.entity.VaccineNotification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class StartWindows {
    private JTable studentNotificationTable;
    private JTable teacherAnnualReviewTable;
    private JButton annualReviewButton;
    private JButton injectButton;
    private JButton roomMangeButton;
    private JButton vaccineMangeButton;
    private JButton vaccineRulesButton;
    private JButton stateRatioRulesButton;
    private JButton peopleManageButton;
    private JPanel startWindowsPanel;
    private JButton resetButton;
    private JButton toCSVButton;
    private DayCare dayCare;

    public void updateNotifycationTable() {
        String notificationColumns[] = {"Notification ID", "School ID", "Student ID", "Vaccine ID", "Due"};
        List<VaccineNotification> notificationList = dayCare.getNotifications();
        int rowLength = notificationList.size();
        int columnsLength = notificationColumns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            VaccineNotification vaccineNotification = notificationList.get(i);
            data[i][0] = String.valueOf(vaccineNotification.getNotificationId());
            data[i][1] = String.valueOf(vaccineNotification.getSchoolID());
            data[i][2] = String.valueOf(vaccineNotification.getPersonID());
            data[i][3] = String.valueOf(vaccineNotification.getVaccineID());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vaccineNotification.getDue());
            DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            data[i][4] = String.valueOf(dateFormat.format(calendar.getTime()));
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, notificationColumns);
        studentNotificationTable.setModel(tableModel);

        String teacherNotificationColumns[] = {"School ID", "Teacher ID", "Due"};
        List<Person> teachers = dayCare.getTeachers();
        rowLength = teachers.size();
        data = new String[rowLength][teacherNotificationColumns.length];
        for (int i = 0; i < rowLength; i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            data[i][0] = String.valueOf(teacher.getSchoolID());
            data[i][1] = String.valueOf(teacher.getPersonID());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(teacher.getLastTimeAnnualReview());
            DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            data[i][2] = String.valueOf(dateFormat.format(calendar.getTime()));
        }
        DefaultTableModel tableModel2 = new DefaultTableModel(data, teacherNotificationColumns);
        teacherAnnualReviewTable.setModel(tableModel2);
    }

    public void initializationListener() {
        injectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentInjectWindows(dayCare);
            }
        });

        annualReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnnualReviewWindows(dayCare);
            }
        });


        peopleManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PeopleManageWindows(dayCare);
            }
        });


        roomMangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomManageWindows(dayCare);
            }
        });

        stateRatioRulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StateRatioRulesWindows(dayCare);
            }
        });

        vaccineMangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VaccineManageWindows(dayCare);
            }
        });

        vaccineRulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VaccineRulesWindows(dayCare);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayCare.resetData();
                dayCare.demo();
//                dayCare.readFromFile();
                updateNotifycationTable();
            }
        });

        toCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayCare.toCSV();
                System.out.println("Finish write");
            }
        });

    }

//    public static void main(String[] args) {
//        DayCare dayCare = new DayCare();
////        dayCare.demo();
////        dayCare.demo2();
//        dayCare.readFromFile();
//        new StartWindows(dayCare);
//    }

    public StartWindows(DayCare dayCare) {
        this.dayCare = dayCare;
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Day Care System");
        jFrame.setContentPane(startWindowsPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - startWindowsPanel.getSize().width / 2, dim.height / 2 - startWindowsPanel.getSize().height / 2);
        jFrame.setResizable(false);

        initializationListener();
        updateNotifycationTable();


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
        startWindowsPanel = new JPanel();
        startWindowsPanel.setLayout(new GridBagLayout());
        startWindowsPanel.setMinimumSize(new Dimension(672, 546));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(315, 400));
        scrollPane1.setPreferredSize(new Dimension(315, 400));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        startWindowsPanel.add(scrollPane1, gbc);
        studentNotificationTable = new JTable();
        studentNotificationTable.setAutoResizeMode(0);
        scrollPane1.setViewportView(studentNotificationTable);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(315, 400));
        scrollPane2.setPreferredSize(new Dimension(315, 400));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        startWindowsPanel.add(scrollPane2, gbc);
        teacherAnnualReviewTable = new JTable();
        teacherAnnualReviewTable.setAutoResizeMode(3);
        scrollPane2.setViewportView(teacherAnnualReviewTable);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startWindowsPanel.add(spacer1, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        startWindowsPanel.add(panel1, gbc);
        annualReviewButton = new JButton();
        annualReviewButton.setMinimumSize(new Dimension(150, 30));
        annualReviewButton.setPreferredSize(new Dimension(150, 30));
        annualReviewButton.setText("Annual Review");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(annualReviewButton, gbc);
        roomMangeButton = new JButton();
        roomMangeButton.setMinimumSize(new Dimension(150, 30));
        roomMangeButton.setPreferredSize(new Dimension(150, 30));
        roomMangeButton.setText("Room Manage");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(roomMangeButton, gbc);
        peopleManageButton = new JButton();
        peopleManageButton.setMinimumSize(new Dimension(150, 30));
        peopleManageButton.setPreferredSize(new Dimension(150, 30));
        peopleManageButton.setText("People Manage");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(peopleManageButton, gbc);
        injectButton = new JButton();
        injectButton.setMinimumSize(new Dimension(150, 30));
        injectButton.setOpaque(true);
        injectButton.setPreferredSize(new Dimension(150, 30));
        injectButton.setText("Inject");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(injectButton, gbc);
        vaccineRulesButton = new JButton();
        vaccineRulesButton.setMinimumSize(new Dimension(150, 30));
        vaccineRulesButton.setPreferredSize(new Dimension(150, 30));
        vaccineRulesButton.setText("Vaccine Rules");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(vaccineRulesButton, gbc);
        vaccineMangeButton = new JButton();
        vaccineMangeButton.setMinimumSize(new Dimension(150, 30));
        vaccineMangeButton.setPreferredSize(new Dimension(150, 30));
        vaccineMangeButton.setText("Vaccine Manage");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(vaccineMangeButton, gbc);
        stateRatioRulesButton = new JButton();
        stateRatioRulesButton.setMinimumSize(new Dimension(150, 30));
        stateRatioRulesButton.setPreferredSize(new Dimension(150, 30));
        stateRatioRulesButton.setText("State Ratio Rules");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(stateRatioRulesButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer5, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);
        resetButton = new JButton();
        resetButton.setText("Reset");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(resetButton, gbc);
        toCSVButton = new JButton();
        toCSVButton.setText("ToCSV");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(toCSVButton, gbc);
        final JLabel label1 = new JLabel();
        label1.setMinimumSize(new Dimension(200, 16));
        label1.setPreferredSize(new Dimension(200, 16));
        label1.setText("Student Immunization Notification");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        startWindowsPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setMinimumSize(new Dimension(200, 16));
        label2.setPreferredSize(new Dimension(200, 16));
        label2.setText("Teacher Annual Review Notification");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        startWindowsPanel.add(label2, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        startWindowsPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startWindowsPanel.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        startWindowsPanel.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        startWindowsPanel.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        startWindowsPanel.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        startWindowsPanel.add(spacer11, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return startWindowsPanel;
    }

}
