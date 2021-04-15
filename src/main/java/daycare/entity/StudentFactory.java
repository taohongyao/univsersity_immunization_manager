package daycare.entity;

public class StudentFactory extends AbstractPersonFactory {
    @Override
    public Person getObject() {
        return new Student();
    }

    private static StudentFactory instance;
    public static StudentFactory getInstance() {
        if(instance == null) {
            instance=new StudentFactory();
        }
        return instance;
    }
}
