package daycare.util;


import daycare.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {


    public static List<List<String>> readCSV(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void writeCSV(String fileName, List<List<String>> data) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter csvWriter = new FileWriter(fileName);
            for (List<String> line : data) {
                csvWriter.append(String.join(",", line));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSchool(List<School> schools, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (School s : schools) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(s.getSchoolID()));
            line.add(s.getSchoolName());
            data.add(line);
        }
        writeCSV(fileName, data);
    }

    public static List<School> readSchool(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<School> schools = new ArrayList<School>();
        for (List<String> line : data) {
            School school = new School();
            school.setSchoolID(Integer.parseInt(line.get(0)));
            school.setSchoolName(line.get(1));
            schools.add(school);
        }
        return schools;
    }


    public static void writeStudents(List<Person> students, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (Person p : students) {
            Student s = (Student) p;
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(s.getPersonID()));
            line.add(String.valueOf(s.getSchoolID()));
            line.add(s.getName());
            line.add(String.valueOf(s.getAgeMonths()));
            line.add(s.getAddress());
            line.add(s.getPhone());
            line.add(s.getFatherName());
            line.add(s.getMotherName());
            line.add(String.valueOf(s.getGrade()));
            data.add(line);
        }
        writeCSV(fileName, data);
    }

    public static List<Student> readStudents(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<Student> students = new ArrayList<Student>();
        StudentFactory factory = StudentFactory.getInstance();
        for (List<String> line : data) {
            Student studenttTemp = (Student) factory.getObject();
            studenttTemp.setPersonID(Integer.parseInt(line.get(0)));
            studenttTemp.setSchoolID(Integer.parseInt(line.get(1)));
            studenttTemp.setName(line.get(2));
            studenttTemp.setAgeMonths(Integer.parseInt(line.get(3)));
            studenttTemp.setAddress(line.get(4));
            studenttTemp.setPhone(line.get(5));
            studenttTemp.setFatherName(line.get(6));
            studenttTemp.setMotherName(line.get(7));
            studenttTemp.setGrade(Double.parseDouble(line.get(8)));
            students.add(studenttTemp);
        }
        return students;
    }


    public static void writeTeachers(List<Person> teachers, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (Person p : teachers) {
            Teacher t = (Teacher) p;
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(t.getPersonID()));
            line.add(String.valueOf(t.getSchoolID()));
            line.add(t.getName());
            line.add(String.valueOf(t.getAgeMonths()));
            line.add(t.getAddress());
            line.add(t.getPhone());
            line.add(String.valueOf(t.getCredit()));
            line.add(String.valueOf(t.getLastTimeAnnualReview()));
            data.add(line);
        }
        writeCSV(fileName, data);
    }


    public static List<Teacher> readTeachers(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<Teacher> teachers = new ArrayList<Teacher>();
        TeacherFactory factory = TeacherFactory.getInstance();
        for (List<String> line : data) {
            Teacher teacherTemp = (Teacher) factory.getObject();
            teacherTemp.setPersonID(Integer.parseInt(line.get(0)));
            teacherTemp.setSchoolID(Integer.parseInt(line.get(1)));
            teacherTemp.setName(line.get(2));
            teacherTemp.setAgeMonths(Integer.parseInt(line.get(3)));
            teacherTemp.setAddress(line.get(4));
            teacherTemp.setPhone(line.get(5));
            teacherTemp.setCredit(Integer.parseInt(line.get(6)));
            teacherTemp.setLastTimeAnnualReview(Long.parseLong(line.get(7)));
            teachers.add(teacherTemp);

        }
        return teachers;
    }


    public static void writeImmunizationType(List<ImmunizationType> types, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (ImmunizationType t : types) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(t.getVaccineID()));
            line.add(t.getVaccineName());
            line.add(String.valueOf(t.getMinDoses()));
            String interval = "";
            for (int i = 0; i < t.getInjectInterval().size(); i++) {
                interval += t.getInjectInterval().get(i);
                if (i != t.getInjectInterval().size() - 1) {
                    interval += "-";
                }
            }
            line.add(interval);
            data.add(line);
        }
        writeCSV(fileName, data);
    }

    public static List<ImmunizationType> readImmunizationType(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<ImmunizationType> types = new ArrayList<ImmunizationType>();

        for (List<String> line : data) {
            ImmunizationType type = new ImmunizationType();
            type.setVaccineID(Integer.parseInt(line.get(0)));
            type.setVaccineName(line.get(1));
            type.setMinDoses(Integer.parseInt(line.get(2)));
            List<Integer> interval = new ArrayList<Integer>();
            for (String s : line.get(3).split("-")) {
                interval.add(Integer.parseInt(s));
            }
            type.setInjectInterval(interval);
            types.add(type);
        }
        return types;
    }

    public static void writeImmunizationRules(List<ImmunizationRules> rules, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (ImmunizationRules r : rules) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(r.getImmunizationRuleID()));
            line.add(String.valueOf(r.getStudentMinAgeMonths()));
            line.add(String.valueOf(r.getStudentMaxAgeMonths()));
            String interval = "";
            for (int i = 0; i < r.getImmunizationTypeList().size(); i++) {
                interval += r.getImmunizationTypeList().get(i);
                if (i != r.getImmunizationTypeList().size() - 1) {
                    interval += "-";
                }
            }
            line.add(interval);
            data.add(line);
        }
        writeCSV(fileName, data);
    }


    public static List<ImmunizationRules> readImmunizationRules(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<ImmunizationRules> rules = new ArrayList<ImmunizationRules>();

        for (List<String> line : data) {
            ImmunizationRules immunizationRules = new ImmunizationRules();
            immunizationRules.setImmunizationRuleID(Integer.parseInt(line.get(0)));
            immunizationRules.setStudentMinAgeMonths(Integer.parseInt(line.get(1)));
            immunizationRules.setStudentMaxAgeMonths(Integer.parseInt(line.get(2)));
            List<Integer> list = new ArrayList<>();
            for (String s : line.get(3).split("-")) {
                list.add(Integer.parseInt(s));
            }

            immunizationRules.setImmunizationTypeList(list);

            rules.add(immunizationRules);
        }
        return rules;
    }

    public static void writeImmunizationRecord(List<ImmunizationRecord> records, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (ImmunizationRecord r : records) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(r.getRecordID()));
            line.add(String.valueOf(r.getSchoolID()));
            line.add(String.valueOf(r.getPersonID()));
            line.add(String.valueOf(r.getVaccineID()));
            line.add(String.valueOf(r.getDate()));
            data.add(line);
        }
        writeCSV(fileName, data);
    }

    public static List<ImmunizationRecord> readImmunizationRecord(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<ImmunizationRecord> records = new ArrayList<ImmunizationRecord>();
        for (List<String> line : data) {
            ImmunizationRecord recordTemp = new ImmunizationRecord();
            recordTemp.setRecordID(Integer.parseInt(line.get(0)));
            recordTemp.setSchoolID(Integer.parseInt(line.get(1)));
            recordTemp.setPersonID(Integer.parseInt(line.get(2)));
            recordTemp.setVaccineID(Integer.parseInt(line.get(3)));
            recordTemp.setDate(Long.parseLong((line.get(4))));
            records.add(recordTemp);

        }
        return records;
    }

    public static void writeDayCareRatioRule(List<DayCareRatioRule> rules, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (DayCareRatioRule r : rules) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(r.getRatioRuleID()));
            line.add(String.valueOf(r.getStudentMinAgeMonths()));
            line.add(String.valueOf(r.getStudentMaxAgeMonths()));
            line.add(String.valueOf(r.getMaxGroups()));
            line.add(String.valueOf(r.getStudent2TeacherRatio()));
            line.add(String.valueOf(r.getMaxGroupSize()));
            data.add(line);
        }
        writeCSV(fileName, data);
    }

    public static List<DayCareRatioRule> readDayCareRatioRule(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<DayCareRatioRule> rules = new ArrayList<DayCareRatioRule>();
        for (List<String> line : data) {
            DayCareRatioRule dayCareRatioRule = new DayCareRatioRule();
            dayCareRatioRule.setRatioRuleID(Integer.parseInt(line.get(0)));
            dayCareRatioRule.setStudentMinAgeMonths(Integer.parseInt(line.get(1)));
            dayCareRatioRule.setStudentMaxAgeMonths(Integer.parseInt(line.get(2)));
            dayCareRatioRule.setMaxGroups(Integer.parseInt(line.get(3)));
            dayCareRatioRule.setStudent2TeacherRatio(Integer.parseInt(line.get(4)));
            dayCareRatioRule.setMaxGroupSize(Integer.parseInt(line.get(5)));

            rules.add(dayCareRatioRule);

        }
        return rules;
    }

    public static void writeNotification(List<VaccineNotification> notifications, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (VaccineNotification notification : notifications) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(notification.getNotificationId()));
            line.add(String.valueOf(notification.getSchoolID()));
            line.add(String.valueOf(notification.getPersonID()));
            line.add(String.valueOf(notification.getVaccineID()));
            line.add(String.valueOf(notification.getDue()));
            data.add(line);
        }
        writeCSV(fileName, data);
    }


    public static List<VaccineNotification> readNotification(String fileName) {
        List<List<String>> data = readCSV(fileName);
        List<VaccineNotification> notifications = new ArrayList<VaccineNotification>();
        for (List<String> line : data) {
            VaccineNotification notification = new VaccineNotification();
            notification.setNotificationId(Integer.parseInt(line.get(0)));
            notification.setSchoolID(Integer.parseInt(line.get(1)));
            notification.setPersonID(Integer.parseInt(line.get(2)));
            notification.setVaccineID(Integer.parseInt(line.get(3)));
            notification.setDue(Long.parseLong(line.get(4)));
            notifications.add(notification);
        }
        return notifications;
    }

    public static void writeClassRoom(List<ClassRoom> classRooms, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> data = new ArrayList<List<String>>();
        for (ClassRoom classRoom : classRooms) {
            List<String> line = new ArrayList<String>();
            line.add(String.valueOf(classRoom.getRoomID()));
            line.add(String.valueOf(classRoom.getStudentMinAgeMonths()));
            line.add(String.valueOf(classRoom.getStudentMaxAgeMonths()));
            line.add(String.valueOf(classRoom.getMaxGroups()));
            line.add(String.valueOf(classRoom.getStudent2TeacherRatio()));
            line.add(String.valueOf(classRoom.getGroupSize()));
            StringBuffer students = new StringBuffer();
            for (int i = 0; i < classRoom.getStudents().size(); i++) {
                students.append(classRoom.getStudents().get(i).getPersonID());
                if (i != classRoom.getStudents().size() - 1) {
                    students.append("-");
                }
            }
            line.add(students.toString());

            StringBuffer teachers = new StringBuffer();
            for (int i = 0; i < classRoom.getTeachers().size(); i++) {
                teachers.append(classRoom.getTeachers().get(i).getPersonID());
                if (i != classRoom.getTeachers().size() - 1) {
                    teachers.append("-");
                }
            }
            line.add(teachers.toString());
            data.add(line);
        }
        writeCSV(fileName, data);
    }


    public static List<ClassRoom> readClassRoom(String fileName, List<Student> studentsList, List<Teacher> teachersList) {
        List<List<String>> data = readCSV(fileName);
        List<ClassRoom> classRooms = new ArrayList<ClassRoom>();
        for (List<String> line : data) {
            ClassRoom classRoom = new ClassRoom();
            classRoom.setRoomID(Integer.parseInt(line.get(0)));
            classRoom.setStudentMinAgeMonths(Integer.parseInt(line.get(1)));
            classRoom.setStudentMaxAgeMonths(Integer.parseInt(line.get(2)));
            classRoom.setMaxGroups(Integer.parseInt(line.get(3)));
            classRoom.setStudent2TeacherRatio(Integer.parseInt(line.get(4)));
            classRoom.setMaxGroupSize(Integer.parseInt(line.get(5)));
            List<Person> students = new ArrayList<Person>();
            if (line.size() == 7) {
                for (String s : line.get(6).split("-")) {
                    if (!s.equals("")) {
                        for (int i = 0; i < studentsList.size(); i++) {
                            Student student = studentsList.get(i);
                            if (student.getPersonID() == Integer.parseInt(s)) {
                                students.add(student);
                            }
                        }

                    }
                }
            }
            classRoom.setStudents(students);


            List<Person> teachers = new ArrayList<Person>();
            if (line.size() == 8) {
                for (String s : line.get(7).split("-")) {
                    if (!s.equals("")) {
                        for (int i = 0; i < teachersList.size(); i++) {
                            Teacher teacher = teachersList.get(i);
                            if (teacher.getPersonID() == Integer.parseInt(s)) {
                                teachers.add(teacher);
                            }
                        }
                    }
                }
            }
            classRoom.setTeachers(teachers);

            classRooms.add(classRoom);
        }
        return classRooms;
    }

}
