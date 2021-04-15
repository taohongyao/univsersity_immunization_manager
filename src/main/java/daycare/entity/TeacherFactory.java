package daycare.entity;

public class TeacherFactory extends AbstractPersonFactory {
    @Override
    public Person getObject() {
        return new Teacher();
    }
    private static TeacherFactory instance;
    public static TeacherFactory getInstance() {
        if(instance == null) {
            instance=new TeacherFactory();
        }
        return instance;
    }
}
