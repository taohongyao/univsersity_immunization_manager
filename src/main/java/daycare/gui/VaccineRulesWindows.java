package daycare.gui;

import daycare.console.DayCare;
import daycare.entity.ImmunizationRules;
import daycare.entity.ImmunizationType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VaccineRulesWindows {
    private JTable immunizationRuleTable;
    private JButton removeRuleButton;
    private JTextField minAgeText;
    private JTable vaccineTable;
    private JTable availableVaccineTable;
    private JButton removeVaccine;
    private JButton addRuleButton;
    private JButton addVaccine;
    private JTextField ruleIdText;
    private JTextField maxAgeText;
    private JPanel vaccineRulesPanel;
    private DayCare dayCare;

//    public static void main(String[] args) {
//
//        DayCare dayCare = new DayCare();
//        dayCare.demo();
//        new VaccineRulesWindows(dayCare);
//    }


    public void updateImmunizationRulesTable() {
        String immunizationRulesColumns[] = {"RuleID", "Max Age", "Min Age"};
        List<ImmunizationRules> immunizationRules = dayCare.getImmunizationRules();
        int rowLength = immunizationRules.size();
        int columnsLength = immunizationRulesColumns.length;
        String[][] data = new String[rowLength][columnsLength];
        for (int i = 0; i < rowLength; i++) {
            ImmunizationRules immunizationRule = immunizationRules.get(i);
            data[i][0] = String.valueOf(immunizationRule.getImmunizationRuleID());
            data[i][1] = String.valueOf(immunizationRule.getStudentMaxAgeMonths());
            data[i][2] = String.valueOf(immunizationRule.getStudentMinAgeMonths());

        }
        DefaultTableModel tableModel = new DefaultTableModel(data, immunizationRulesColumns);
        immunizationRuleTable.setModel(tableModel);
    }

    public void updateVaccineTable(List<ImmunizationType> vaccineIdlist) {
        String vaccineColumns[] = {"Vaccine ID", "Vaccine Name", "MiniDoses", "Inject Interval"};
        String[][] data = new String[vaccineIdlist.size()][vaccineColumns.length];
        for (int j = 0; j < vaccineIdlist.size(); j++) {
            ImmunizationType v = vaccineIdlist.get(j);
            data[j][0] = String.valueOf(v.getVaccineID());
            data[j][1] = v.getVaccineName();
            data[j][2] = String.valueOf(v.getMinDoses());
            data[j][3] = v.getInjectInterval().toString();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, vaccineColumns);
        vaccineTable.setModel(tableModel);
    }

    public void updateAvailableVaccineTable(List<ImmunizationType> vaccineIdlist) {
        String vaccineColumns[] = {"Vaccine ID", "Vaccine Name", "MiniDoses", "Inject Interval"};
        String[][] data = new String[vaccineIdlist.size()][vaccineColumns.length];
        for (int j = 0; j < vaccineIdlist.size(); j++) {
            ImmunizationType v = vaccineIdlist.get(j);
            data[j][0] = String.valueOf(v.getVaccineID());
            data[j][1] = v.getVaccineName();
            data[j][2] = String.valueOf(v.getMinDoses());
            data[j][3] = v.getInjectInterval().toString();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, vaccineColumns);
        availableVaccineTable.setModel(tableModel);
    }

    public void updateVaccineTable() {
        int row = immunizationRuleTable.getSelectedRow();
        if (row != -1) {
            int ruleID = Integer.valueOf((String) immunizationRuleTable.getValueAt(row, 0));
            List<ImmunizationType> list = dayCare.getImmunizationTypesByImmunizationRuleId(ruleID);
            updateVaccineTable(list);
        } else {
            updateVaccineTable(new ArrayList<>());
        }

    }

    public void removeImmunizationRule() {
        int row = immunizationRuleTable.getSelectedRow();
        if (row != -1) {
            int ruleID = Integer.valueOf((String) immunizationRuleTable.getValueAt(row, 0));
            dayCare.removeImmunizationRulesById(ruleID);
        }
    }

    public void addVaccineToRule() {
        int row = availableVaccineTable.getSelectedRow();
        int row2 = immunizationRuleTable.getSelectedRow();
        if (row != -1 && row2 != -1) {
            int vid = Integer.parseInt((String) availableVaccineTable.getValueAt(row, 0));
            int ruleId = Integer.parseInt((String) immunizationRuleTable.getValueAt(row2, 0));
            dayCare.addVaccineToImmunizationRule(ruleId, vid);
        }
    }

    public void removeVaccineFromRule() {
        int row = vaccineTable.getSelectedRow();
        int row2 = immunizationRuleTable.getSelectedRow();
        if (row != -1 && row2 != -1) {
            String rule = (String) vaccineTable.getValueAt(row, 0);
            int vid = Integer.parseInt(rule);
            int ruleId = Integer.parseInt((String) immunizationRuleTable.getValueAt(row2, 0));
            dayCare.removeVaccineFromImmunizationRuleById(ruleId, vid);
        }
    }

    public void addImmunizationRule() {
        ImmunizationRules r = new ImmunizationRules();
        r.setImmunizationRuleID(Integer.parseInt(ruleIdText.getText()));
        r.setStudentMaxAgeMonths(Integer.parseInt(maxAgeText.getText()));
        r.setStudentMinAgeMonths(Integer.parseInt(minAgeText.getText()));
        r.setImmunizationTypeList(new ArrayList<Integer>());
        dayCare.addImmunizationRule(r);
    }

    public void fillId() {
        int id = dayCare.getImmunizationRules().get(dayCare.getImmunizationRules().size() - 1).getImmunizationRuleID() + 1;
        ruleIdText.setText(String.valueOf(id));
    }

    public void updateAvailableVaccineTable() {
        int row = immunizationRuleTable.getSelectedRow();
        if (row != -1) {
            int ruleID = Integer.valueOf((String) immunizationRuleTable.getValueAt(row, 0));
            List<ImmunizationType> list = dayCare.getAvailableVaccines(ruleID);
            updateAvailableVaccineTable(list);
        } else {
            updateAvailableVaccineTable(new ArrayList<>());
        }

    }


    public void initialListener() {

        ListSelectionModel selectionModel = immunizationRuleTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateVaccineTable();
                updateAvailableVaccineTable();
            }
        });

        ListSelectionModel selectionModel2 = vaccineTable.getSelectionModel();
        selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                availableVaccineTable.getSelectionModel().clearSelection();
            }
        });

        ListSelectionModel selectionModel3 = availableVaccineTable.getSelectionModel();
        selectionModel3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                vaccineTable.getSelectionModel().clearSelection();
            }
        });


        addRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImmunizationRule();
                fillId();
                updateImmunizationRulesTable();
                updateVaccineTable();
                updateAvailableVaccineTable();

            }
        });


        addVaccine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVaccineToRule();
                updateVaccineTable();
                updateAvailableVaccineTable();
            }
        });
        removeVaccine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVaccineFromRule();
                updateVaccineTable();
                updateAvailableVaccineTable();
            }
        });

        removeRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeImmunizationRule();
                updateImmunizationRulesTable();
                updateVaccineTable();
                updateAvailableVaccineTable();
            }
        });
    }


    public VaccineRulesWindows(DayCare dayCare) {
        this.dayCare = dayCare;

        JFrame jFrame = new JFrame();
        jFrame.setTitle("Immunization Rule Manage");
        jFrame.setContentPane(vaccineRulesPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - vaccineRulesPanel.getSize().width / 2, dim.height / 2 - vaccineRulesPanel.getSize().height / 2);
        jFrame.setResizable(false);

        initialListener();
        updateImmunizationRulesTable();
        fillId();

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
        vaccineRulesPanel = new JPanel();
        vaccineRulesPanel.setLayout(new GridBagLayout());
        vaccineRulesPanel.setAutoscrolls(false);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(200, 150));
        scrollPane1.setPreferredSize(new Dimension(200, 150));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        vaccineRulesPanel.add(scrollPane1, gbc);
        immunizationRuleTable = new JTable();
        immunizationRuleTable.setAutoResizeMode(0);
        immunizationRuleTable.setMinimumSize(new Dimension(150, 40));
        scrollPane1.setViewportView(immunizationRuleTable);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-855310));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        vaccineRulesPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Rule ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Min Age(month): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        minAgeText = new JTextField();
        minAgeText.setText("10");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(minAgeText, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Max Age(month): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(250, 100));
        scrollPane2.setPreferredSize(new Dimension(250, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane2, gbc);
        vaccineTable = new JTable();
        vaccineTable.setAutoResizeMode(0);
        vaccineTable.setMinimumSize(new Dimension(200, 50));
        scrollPane2.setViewportView(vaccineTable);
        addVaccine = new JButton();
        addVaccine.setText("<<");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(addVaccine, gbc);
        removeVaccine = new JButton();
        removeVaccine.setText(">>");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(removeVaccine, gbc);
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setMinimumSize(new Dimension(250, 100));
        scrollPane3.setPreferredSize(new Dimension(250, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane3, gbc);
        availableVaccineTable = new JTable();
        availableVaccineTable.setAutoResizeMode(0);
        availableVaccineTable.setMinimumSize(new Dimension(200, 10));
        scrollPane3.setViewportView(availableVaccineTable);
        ruleIdText = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ruleIdText, gbc);
        maxAgeText = new JTextField();
        maxAgeText.setText("20");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(maxAgeText, gbc);
        addRuleButton = new JButton();
        addRuleButton.setText("Add Rule");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(addRuleButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        removeRuleButton = new JButton();
        removeRuleButton.setText("Remove Rule");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vaccineRulesPanel.add(removeRuleButton, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vaccineRulesPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        vaccineRulesPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        vaccineRulesPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vaccineRulesPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vaccineRulesPanel.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        vaccineRulesPanel.add(spacer8, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return vaccineRulesPanel;
    }

}
