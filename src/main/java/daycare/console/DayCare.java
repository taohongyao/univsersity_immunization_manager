package daycare.console;

import daycare.entity.*;
import daycare.util.CSVUtil;

import java.util.*;

public class DayCare {
    private List<School> schools;
    private List<ImmunizationType> types;
    private List<DayCareRatioRule> dayCareRatioRules;
    private List<ImmunizationRules> immunizationRules;
    private List<ImmunizationRecord> records;
    private List<VaccineNotification> notifications;
    private List<Person> students;
    private List<Person> teachers;
    private List<ClassRoom> classRooms;

    public void demo2() {
//       read dataset from csv files.
        List<School> schools = CSVUtil.readSchool("data/schools.csv");
        List<Student> students = CSVUtil.readStudents("data/students.csv");
        List<Teacher> teachers = CSVUtil.readTeachers("data/teachers.csv");
        List<DayCareRatioRule> dayCareRatioRules = CSVUtil.readDayCareRatioRule("data/dayCareRationRules.csv");
        List<ImmunizationRecord> records = CSVUtil.readImmunizationRecord("data/immunizationRecords.csv");
        List<ImmunizationRules> immunizationRules = CSVUtil.readImmunizationRules("data/immunizationRules.csv");
        List<ImmunizationType> types = CSVUtil.readImmunizationType("data/vaccineTypes.csv");
        List<VaccineNotification> notifications = CSVUtil.readNotification("data/notifications.csv");
        List<ClassRoom> classRooms = CSVUtil.readClassRoom("data/classRoom.csv", students, teachers);
        setSchools(schools);
        setTypes(types);
        setDayCareRatioRules(dayCareRatioRules);
        setImmunizationRules(immunizationRules);
        setRecords(records);
        setNotifications(notifications);
        List<Person> studentsP = new ArrayList<Person>();
        for (Student s : students) {
            studentsP.add(s);
        }
        setStudents(studentsP);
        List<Person> teacherP = new ArrayList<Person>();
        for (Teacher t : teachers) {
            teacherP.add(t);
        }
        setTeachers(teacherP);
        setClassRooms(classRooms);
        displayAll();
    }
    public void readFromFile(){
        List<School> schools = CSVUtil.readSchool("data/schools.csv");
        List<Student> students = CSVUtil.readStudents("data/students.csv");
        List<Teacher> teachers = CSVUtil.readTeachers("data/teachers.csv");
        List<DayCareRatioRule> dayCareRatioRules = CSVUtil.readDayCareRatioRule("data/dayCareRationRules.csv");
        List<ImmunizationRecord> records = CSVUtil.readImmunizationRecord("data/immunizationRecords.csv");
        List<ImmunizationRules> immunizationRules = CSVUtil.readImmunizationRules("data/immunizationRules.csv");
        List<ImmunizationType> types = CSVUtil.readImmunizationType("data/vaccineTypes.csv");
        List<VaccineNotification> notifications = CSVUtil.readNotification("data/notifications.csv");
        List<ClassRoom> classRooms = CSVUtil.readClassRoom("data/classRoom.csv", students, teachers);
        setSchools(schools);
        setTypes(types);
        setDayCareRatioRules(dayCareRatioRules);
        setImmunizationRules(immunizationRules);
        setRecords(records);
        setNotifications(notifications);
        List<Person> studentsP = new ArrayList<Person>();
        for (Student s : students) {
            studentsP.add(s);
        }
        setStudents(studentsP);
        List<Person> teacherP = new ArrayList<Person>();
        for (Teacher t : teachers) {
            teacherP.add(t);
        }
        setTeachers(teacherP);
        setClassRooms(classRooms);
    }

    public void resetData(){
        types.clear();
        records.clear();
        dayCareRatioRules.clear();
        immunizationRules.clear();
        notifications.clear();
        schools.clear();
        students.clear();
        teachers.clear();
        classRooms.clear();
    }

    public List<Teacher> getTeachersBySchoolId(int schoolId){
        List<Teacher> list=new ArrayList<>();
        for(Person teacher:teachers){
            Teacher teacher1= (Teacher) teacher;
            if(teacher1.getSchoolID()==schoolId){
                list.add(teacher1);
            }
        }
        return  list;
    }

    public List<Person> getAvailableTeachers(int schoolId){
        Map<Integer, Person> map=new HashMap<>();
        for(Person person:teachers){
            if(person.getSchoolID()==schoolId){
                map.put(person.getPersonID(),person);
            }
        }

        for(ClassRoom classRoom:classRooms){
            if(classRoom.getSchoolId()==schoolId){
                for(Person teacher:classRoom.getTeachers()){
                    map.remove(teacher.getPersonID());
                }
            }
        }
        List<Person> list=new ArrayList<Person>(map.values());
        return list;
    }

    public void removeTeacherFromRoom(int teacherId, int roomId,int schoolId){
        for(ClassRoom c:classRooms){
            if(c.getRoomID()==roomId && c.getSchoolId()==schoolId){
                List<Person> list=c.getTeachers();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getPersonID()==teacherId){
                        list.remove(i);
                        return;
                    }
                }
            }
        }
    }

    public void removeStudentFromRoom(int studentId, int roomId,int schoolId){
        for(ClassRoom c:classRooms){
            if(c.getRoomID()==roomId && c.getSchoolId()==schoolId){
                List<Person> list=c.getStudents();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getPersonID()==studentId){
                        list.remove(i);
                        return;
                    }
                }
            }
        }
    }

    public void addTeacherIntoRoom(int teacherId, int roomId,int schoolId){
        Teacher teacher=null;
        for(Person s:teachers){
            if(s.getPersonID()==teacherId && s.getSchoolID()==schoolId){
                teacher= (Teacher) s;
                break;
            }
        }

        for(ClassRoom c:classRooms){
            if(c.getRoomID()==roomId && c.getSchoolId()==schoolId){
                c.addTeacher(teacher);
                break;
            }
        }
    }
    public void removeStudentById(int id){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getPersonID()==id){
                students.remove(i);
                return;
            }
        }
    }
    public void removeTeacherById(int id){
        for(int i=0;i<teachers.size();i++){
            if(teachers.get(i).getPersonID()==id){
                teachers.remove(i);
                return;
            }
        }
    }
    public Person getTeacherById(int id,int schoolid){
        for (Person p:teachers){
            if(p.getPersonID()==id && p.getSchoolID()==schoolid){
                return p;
            }
        }
        return null;
    }
    public void removeVaccineFromImmunizationRuleById(int immunizationId,int vaccineId){
        for (ImmunizationRules r:immunizationRules){
            if(r.getImmunizationRuleID()==immunizationId){
                for (int i=0;i<r.getImmunizationTypeList().size();i++){
                    if(r.getImmunizationTypeList().get(i)==vaccineId){
                        r.getImmunizationTypeList().remove(i);
                        return;
                    }
                }
            }
        }
    }
    public void removeImmunizationRulesById(int id){
        for(int i=0;i<immunizationRules.size();i++){
            if(immunizationRules.get(i).getImmunizationRuleID()==id){
                immunizationRules.remove(i);
                return;
            }
        }
    }

    public List<ImmunizationType> getImmunizationTypesByImmunizationRuleId(int id){
        List<ImmunizationType> list=new ArrayList<>();
        for(ImmunizationRules r:immunizationRules){
            if(r.getImmunizationRuleID()==id){
                for (int i:r.getImmunizationTypeList()){
                    list.add(getImmunizationTypeByID(i));
                }
            }
        }
        return list;
    }

    public void addVaccineToImmunizationRule(int immunizationId,int vaccineId){
        for (ImmunizationRules r:immunizationRules){
            if(r.getImmunizationRuleID()==immunizationId){
                r.getImmunizationTypeList().add(vaccineId);
                break;
            }
        }
    }

    public List<ImmunizationType> getAvailableVaccines(int immunizationId){
        Map<Integer,ImmunizationType> map=new HashMap<Integer,ImmunizationType>();
        for (ImmunizationType t:types){
            map.put(t.getVaccineID(),t);
        }
        for (ImmunizationRules r:immunizationRules){
            if(r.getImmunizationRuleID()==immunizationId){
                for (Integer id:r.getImmunizationTypeList()){
                    map.remove(id);
                }
                break;
            }
        }
        return new ArrayList<ImmunizationType>(map.values());

    }

    public void addImmunizationRule(ImmunizationRules immunizationRule){
        immunizationRules.add(immunizationRule);
    }

    public void removeRuleByRuleId(int id){
        for (int i=0;i<immunizationRules.size();i++){

            if(immunizationRules.get(i).getImmunizationRuleID()==id){
                immunizationRules.remove(i);
                return;
            }
        }
    }
    public Person getStudentById(int id,int schoolid){
        for (Person p:students){
            if(p.getPersonID()==id&& p.getSchoolID()==schoolid){
                return p;
            }
        }
        return null;
    }
    public void removeSchool(int id){
        for(int i=0;i<schools.size();i++){
            if(schools.get(i).getSchoolID()==id){
                schools.remove(i);
            }
        }
    }

    public void addSchool(School school){
        schools.add(school);
    }

    public void addStudentIntoRoom(int studentId, int roomId,int schoolId){
        Student student=null;
        for(Person s:students){
            if(s.getPersonID()==studentId && s.getSchoolID()==schoolId){
                student= (Student) s;
                break;
            }
        }

        for(ClassRoom c:classRooms){
            if(c.getRoomID()==roomId && c.getSchoolId()==schoolId){
                c.addStudent(student);
                break;
            }
        }
    }
    public void addStateRule(DayCareRatioRule dayCareRatioRule){
        dayCareRatioRules.add(dayCareRatioRule);
    }
    public void addVaccine(ImmunizationType immunizationType){
        types.add(immunizationType);
    }
    public void removeVaccineById(int id){
        for(int i=0;i<types.size();i++){
            if(types.get(i).getVaccineID()==id){
                types.remove(i);
                return;
            }
        }
    }

    public void removeStatesRuleById(int ruleId){
        for(int i=0;i<dayCareRatioRules.size();i++){
            if(dayCareRatioRules.get(i).getRatioRuleID()==ruleId){
                dayCareRatioRules.remove(i);
                return;
            }
        }
    }


    public List<Person> getAvailableStudents(int schoolId){
        Map<Integer, Person> map=new HashMap<>();
        for(Person person:students){
            if(person.getSchoolID()==schoolId){
                map.put(person.getPersonID(),person);
            }
        }

        for(ClassRoom classRoom:classRooms){
            if(classRoom.getSchoolId()==schoolId){
                for(Person student:classRoom.getStudents()){
                    map.remove(student.getPersonID());
                }
            }
        }
        List<Person> list=new ArrayList<Person>(map.values());
        return list;
    }
    public List<ClassRoom> getRoomBySchool(int schoolId){
        List<ClassRoom> list=new ArrayList<>();
        for(ClassRoom classRoom:classRooms){
            if(classRoom.getSchoolId()==schoolId){
                list.add(classRoom);
            }
        }
        return  list;
    }
    public void addRoom(ClassRoom classRoom){
        classRooms.add(classRoom);
    }

    public void removeRoom(int roomId,int schoolId){
        for(int i=0;i<classRooms.size();i++){
            ClassRoom classRoom=classRooms.get(i);
            if(classRoom.getRoomID()==roomId && classRoom.getSchoolId()==schoolId){
                classRooms.remove(i);
                return;
            }
        }
    }

    public List<Person> getTeachersByRoomIdAndSchoolId(int roomId, int schoolId){
        for(ClassRoom item: classRooms){
            if(item.getSchoolId()==schoolId && item.getRoomID()==roomId){
                return item.getTeachers();
            }
        }
        return null;
    }

    public Student getStudentBySchoolIdAndStudentId(int StudentId,int schoolId){
        for(Person item: students){
            if(item.getSchoolID()==schoolId && item.getPersonID()==StudentId){
                return (Student) item;
            }
        }
        return null;
    }


    public Teacher getTeacherBySchoolIdAndTeacherId(int teacherId,int schoolId){
        for(Person item: teachers){
            if(item.getSchoolID()==schoolId && item.getPersonID()==teacherId){
                return (Teacher) item;
            }
        }
        return null;
    }


    public ClassRoom getClassRoomBySchoolIdAndRoomId(int roomId,int schoolId){
        for(ClassRoom item: classRooms){
            if(item.getSchoolId()==schoolId && item.getRoomID()==roomId){
                return item;
            }
        }
        return null;
    }
    public List<Person> getStudentsByRoomIdAndSchoolId(int roomId, int schoolId){
        for(ClassRoom item: classRooms){
            if(item.getSchoolId()==schoolId && item.getRoomID()==roomId){
                return item.getStudents();
            }
        }
        return null;
    }


    public List<Teacher> getExpireTeachersBySchoolIdAndDays(int schoolId, int days,int year){
        List<Teacher> list=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(Person teacher:teachers){
            Teacher teacher1= (Teacher) teacher;
            long date=teacher1.getLastTimeAnnualReview();
            calendar.setTimeInMillis(date);
            calendar.add(Calendar.YEAR,year);
            calendar.add(Calendar.DATE, -days);
            long nextReviewDate=calendar.getTimeInMillis();
            if(teacher1.getSchoolID()==schoolId && nextReviewDate<System.currentTimeMillis()){
                list.add(teacher1);
            }
        }
        return  list;
    }

    public void toCSV(){
        CSVUtil.writeDayCareRatioRule(dayCareRatioRules, "data/dayCareRationRules.csv");
        CSVUtil.writeImmunizationType(types, "data/vaccineTypes.csv");
        CSVUtil.writeImmunizationRules(immunizationRules, "data/immunizationRules.csv");
        CSVUtil.writeSchool(schools, "data/schools.csv");
        CSVUtil.writeStudents(students, "data/students.csv");
        CSVUtil.writeTeachers(teachers, "data/teachers.csv");
        CSVUtil.writeClassRoom(getClassRooms(), "data/classRoom.csv");
        CSVUtil.writeImmunizationRecord(getRecords(), "data/immunizationRecords.csv");
        CSVUtil.writeNotification(getNotifications(), "data/notifications.csv");
    }

    public void demo() {
        // creat data and write data into csv files.
        DayCareRatioRule dayCareRatioRule1 = new DayCareRatioRule(0, 6, 12, 4, 4, 3);
        DayCareRatioRule dayCareRatioRule2 = new DayCareRatioRule(1, 13, 24, 5, 5, 3);
        DayCareRatioRule dayCareRatioRule3 = new DayCareRatioRule(2, 25, 35, 6, 6, 3);
        DayCareRatioRule dayCareRatioRule4 = new DayCareRatioRule(3, 36, 47, 8, 8, 3);
        DayCareRatioRule dayCareRatioRule5 = new DayCareRatioRule(4, 48, 59, 12, 12, 2);
        DayCareRatioRule dayCareRatioRule6 = new DayCareRatioRule(5, 60, 999, 15, 15, 2);
        dayCareRatioRules.add(dayCareRatioRule1);
        dayCareRatioRules.add(dayCareRatioRule2);
        dayCareRatioRules.add(dayCareRatioRule3);
        dayCareRatioRules.add(dayCareRatioRule4);
        dayCareRatioRules.add(dayCareRatioRule5);
        dayCareRatioRules.add(dayCareRatioRule6);
        CSVUtil.writeDayCareRatioRule(dayCareRatioRules, "data/dayCareRationRules.csv");

        List<Integer> interval1 = new ArrayList<Integer>();
        interval1.add(10);
        interval1.add(20);
        interval1.add(5);
        ImmunizationType type1 = new ImmunizationType(0, "Hib", 4, interval1);
        List<Integer> interval2 = new ArrayList<Integer>();
        interval2.add(5);
        interval2.add(10);
        interval2.add(15);
        interval2.add(15);
        ImmunizationType type2 = new ImmunizationType(1, "DTap", 5, interval2);
        List<Integer> interval3 = new ArrayList<Integer>();
        interval3.add(2);
        interval3.add(10);
        interval3.add(15);
        ImmunizationType type3 = new ImmunizationType(2, "Polio", 4, interval3);
        List<Integer> interval4 = new ArrayList<Integer>();
        interval4.add(10);
        interval4.add(20);
        ImmunizationType type4 = new ImmunizationType(3, "Hepatitis B", 3, interval4);
        List<Integer> interval5 = new ArrayList<Integer>();
        interval5.add(7);
        ImmunizationType type5 = new ImmunizationType(4, "MMR", 2, interval5);
        List<Integer> interval6 = new ArrayList<Integer>();
        interval6.add(15);
        ImmunizationType type6 = new ImmunizationType(5, "Varicella", 2, interval6);
        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        types.add(type5);
        types.add(type6);
        CSVUtil.writeImmunizationType(types, "data/vaccineTypes.csv");

        List<Integer> vaccineID = new ArrayList<Integer>();
        vaccineID.add(0);
        vaccineID.add(1);
        vaccineID.add(2);
        vaccineID.add(3);
        vaccineID.add(4);
        vaccineID.add(5);
        ImmunizationRules immunizationRules1 = new ImmunizationRules(0, 0, 24, vaccineID);

        List<Integer> vaccineID2 = new ArrayList<Integer>();
        vaccineID2.add(1);
        vaccineID2.add(2);
        vaccineID2.add(3);
        vaccineID2.add(4);
        vaccineID2.add(5);
        ImmunizationRules immunizationRules2 = new ImmunizationRules(1, 25, 144, vaccineID2);
        immunizationRules.add(immunizationRules1);
        immunizationRules.add(immunizationRules2);
        CSVUtil.writeImmunizationRules(immunizationRules, "data/immunizationRules.csv");

        School school = new School();
        school.setSchoolID(0);
        school.setSchoolName("Northeastern University");
        schools.add(school);
        CSVUtil.writeSchool(schools, "data/schools.csv");

        AbstractPersonFactory factory = new StudentFactory();
        Student student1 = (Student) factory.getObject();
        student1.setPersonID(0);
        student1.setName("Jimmy");
        student1.setAgeMonths(12);
        student1.setAddress("190 pleasantstreet");
        student1.setPhone("1111111");
        student1.setMotherName("White");
        student1.setFatherName("Bob");
        student1.setGrade(10);
        enrollStudent(student1);

        Student student2 = (Student) factory.getObject();
        student2.setPersonID(1);
        student2.setName("Sally");
        student2.setAgeMonths(12);
        student2.setAddress("30 watts street");
        student2.setPhone("2222222");
        student2.setMotherName("God");
        student2.setFatherName("Doe");
        student2.setGrade(10);
        enrollStudent(student2);

        Student student3 = (Student) factory.getObject();
        student3.setPersonID(2);
        student3.setName("Lizzy");
        student3.setAgeMonths(12);
        student3.setAddress("Boston");
        student3.setPhone("333333");
        student3.setMotherName("Joy");
        student3.setFatherName("Tomas");
        student3.setGrade(10);
        enrollStudent(student3);

        CSVUtil.writeStudents(getStudents(), "data/students.csv");

        factory = new TeacherFactory();
        Teacher teacher1 = (Teacher) factory.getObject();
        teacher1.setName("Aden");
        teacher1.setPersonID(3);
        teacher1.setPhone("4444444");
        teacher1.setAddress("Malden");
        teacher1.setAgeMonths(50);
        teacher1.setCredit(1);
        enrollTeacher(teacher1);


        Teacher teacher2 = (Teacher) factory.getObject();
        teacher2.setName("Declan");
        teacher2.setPersonID(4);
        teacher2.setPhone("555555");
        teacher2.setAddress("Meford");
        teacher2.setAgeMonths(50);
        teacher2.setCredit(1);
        enrollTeacher(teacher2);

        Teacher teacher3 = (Teacher) factory.getObject();
        teacher3.setName("Tao Hongyao");
        teacher3.setPersonID(5);
        teacher3.setPhone("6666666");
        teacher3.setAddress("Malden");
        teacher3.setAgeMonths(50);
        teacher3.setCredit(1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -1);
        teacher3.setLastTimeAnnualReview(calendar.getTimeInMillis());
        enrollTeacher(teacher3);




        teacherAttendAnnualReview(teacher1.getPersonID(), System.currentTimeMillis());
        teacherAttendAnnualReview(teacher2.getPersonID(), System.currentTimeMillis());
        CSVUtil.writeTeachers(getTeachers(), "data/teachers.csv");

        ClassRoom classRoom1 = new ClassRoom(dayCareRatioRule1);
        classRoom1.setRoomID(0);
        classRoom1.addTeacher(teacher1);
        classRoom1.addStudent(student1);
        classRoom1.addStudent(student2);

        ClassRoom classRoom2 = new ClassRoom(dayCareRatioRule2);
        classRoom2.setRoomID(1);
        classRoom2.addTeacher(teacher2);
        classRoom2.addStudent(student3);
        assignClassRoom(classRoom1);
        assignClassRoom(classRoom2);
        CSVUtil.writeClassRoom(getClassRooms(), "data/classRoom.csv");

        studentInject(0, student1.getPersonID(), type1.getVaccineID(), System.currentTimeMillis());
        studentInject(0, student1.getPersonID(), type2.getVaccineID(), System.currentTimeMillis());
        studentInject(0, student1.getPersonID(), type3.getVaccineID(), System.currentTimeMillis());


        studentInject(0, student2.getPersonID(), type3.getVaccineID(), System.currentTimeMillis());
        studentInject(0, student2.getPersonID(), type4.getVaccineID(), System.currentTimeMillis());
        studentInject(0, student2.getPersonID(), type5.getVaccineID(), System.currentTimeMillis());
        CSVUtil.writeImmunizationRecord(getRecords(), "data/immunizationRecords.csv");
        CSVUtil.writeNotification(getNotifications(), "data/notifications.csv");
        displayAll();
    }

    public void display(List list) {
        for (Object s : list) {
            System.out.println(s);
        }
    }

    public void displayAll() {
        System.out.println("-----------Schools:-----------");
        display(schools);
        System.out.println("-----------ImmunizationType:-----------");
        display(types);
        System.out.println("-----------DayCareRatioRules:-----------");
        display(dayCareRatioRules);
        System.out.println("-----------DayCareRatioRules:-----------");
        display(dayCareRatioRules);
        System.out.println("-----------ImmunizationRules:-----------");
        display(immunizationRules);
        System.out.println("-----------ImmunizationRecord:-----------");
        display(records);
        System.out.println("-----------Notifications:-----------");
        display(notifications);
        System.out.println("-----------students:-----------");
        display(students);
        System.out.println("-----------teachers:-----------");
        display(teachers);
        System.out.println("-----------classrooms:-----------");
        display(classRooms);

    }

    public DayCare() {
        types = new ArrayList<ImmunizationType>();
        records = new ArrayList<ImmunizationRecord>();
        dayCareRatioRules = new ArrayList<DayCareRatioRule>();
        immunizationRules = new ArrayList<ImmunizationRules>();
        notifications = new ArrayList<VaccineNotification>();
        schools = new ArrayList<School>();
        students = new ArrayList<Person>();
        teachers = new ArrayList<Person>();
        classRooms = new ArrayList<ClassRoom>();
    }

    public void teacherAttendAnnualReview(int teacherID, long date) {
        getTeacherByID(teacherID).setLastTimeAnnualReview(date);

    }

    public Teacher getTeacherByID(int teacherID) {
        for (Person t : teachers) {
            if (t.getPersonID() == teacherID) {
                return (Teacher) t;
            }
        }
        return null;
    }

    public void studentInject(int schoolID, int studentID, int vaccineID, long date) {
        records.add(new ImmunizationRecord(schoolID, studentID, vaccineID, date));
        removeNotification(schoolID, studentID, vaccineID);
        int nextInterval = getStudentNextInterval(schoolID, studentID, vaccineID);
        if (nextInterval != -1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            calendar.add(Calendar.DATE, nextInterval);
            notifications.add(new VaccineNotification(schoolID, studentID, vaccineID, calendar.getTimeInMillis()));
        }
    }

    public void removeNotification(int schoolID, int studentID, int vaccineID) {
        for (int i = 0; i < notifications.size(); i++) {
            VaccineNotification notification = notifications.get(i);
            if (notification.getSchoolID() == schoolID & notification.getPersonID() == studentID & notification.getVaccineID() == vaccineID) {
                notifications.remove(i);
                return;
            }
        }
    }
    public ImmunizationRules getImmunizationRuleByID(int immunizationID) {
        for (ImmunizationRules i : immunizationRules) {
            if (i.getImmunizationRuleID() == immunizationID) {
                return i;
            }
        }
        return null;
    }

    public ImmunizationType getImmunizationTypeByID(int vaccineID) {
        for (ImmunizationType i : types) {
            if (i.getVaccineID() == vaccineID) {
                return i;
            }
        }
        return null;
    }

    public int getStudentNextInterval(int schoolID, int studentID, int vaccineID) {
        int count = 0;
        for (int i = 0; i < records.size(); i++) {
            ImmunizationRecord immunizationRecord = records.get(i);
            if (immunizationRecord.getSchoolID() == schoolID & immunizationRecord.getPersonID() == studentID & immunizationRecord.getVaccineID() == vaccineID) {
                count++;
            }
        }
        ImmunizationType vaccine = getImmunizationTypeByID(vaccineID);
        if (count <= vaccine.getInjectInterval().size()) {
            return vaccine.getInjectInterval().get(count - 1);
        } else {
            return -1;
        }
    }

    public ImmunizationType getVaccineByID(int id){
        for (ImmunizationType immunizationType: types){
            if(immunizationType.getVaccineID()==id){ return immunizationType;}
        }
        return null;
    }
    public List<ImmunizationRules> getImmunizationRules() {
        return immunizationRules;
    }

    public void setImmunizationRules(List<ImmunizationRules> immunizationRules) {
        this.immunizationRules = immunizationRules;
    }

    public List<VaccineNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<VaccineNotification> notifications) {
        this.notifications = notifications;
    }

    public List<DayCareRatioRule> getDayCareRatioRules() {
        return dayCareRatioRules;
    }

    public void setDayCareRatioRules(List<DayCareRatioRule> dayCareRatioRules) {
        this.dayCareRatioRules = dayCareRatioRules;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<ImmunizationType> getTypes() {
        return types;
    }

    public void setTypes(List<ImmunizationType> types) {
        this.types = types;
    }

    public List<ImmunizationRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ImmunizationRecord> records) {
        this.records = records;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }

    public void setClassRooms(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    public void assignClassRoom(ClassRoom classRoom) {
        classRooms.add(classRoom);
    }

    public void enrollStudent(Student student) {
        students.add(student);
    }

    public void enrollTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public List<Person> getStudents() {
        return students;
    }

    public List<Person> getTeachers() {
        return teachers;
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
}
