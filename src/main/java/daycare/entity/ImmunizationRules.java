package daycare.entity;

import java.util.ArrayList;
import java.util.List;

public class ImmunizationRules {
    private int immunizationRuleID;
    private int studentMinAgeMonths=0;
    private int studentMaxAgeMonths=24;
    private List<Integer> immunizationTypeList;

    public ImmunizationRules(int immunizationRuleID, int studentMinAgeMonths, int studentMaxAgeMonths, List<Integer> immunizationTypeList) {
        this.immunizationRuleID = immunizationRuleID;
        this.studentMinAgeMonths = studentMinAgeMonths;
        this.studentMaxAgeMonths = studentMaxAgeMonths;
        this.immunizationTypeList = immunizationTypeList;
    }

    public ImmunizationRules() {
        immunizationTypeList=new ArrayList<Integer>();
    }

    public int getImmunizationRuleID() {
        return immunizationRuleID;
    }

    public void setImmunizationRuleID(int immunizationRuleID) {
        this.immunizationRuleID = immunizationRuleID;
    }

    public int getStudentMinAgeMonths() {
        return studentMinAgeMonths;
    }

    public void setStudentMinAgeMonths(int studentMinAgeMonths) {
        this.studentMinAgeMonths = studentMinAgeMonths;
    }

    public int getStudentMaxAgeMonths() {
        return studentMaxAgeMonths;
    }

    public void setStudentMaxAgeMonths(int studentMaxAgeMonths) {
        this.studentMaxAgeMonths = studentMaxAgeMonths;
    }

    public List<Integer> getImmunizationTypeList() {
        return immunizationTypeList;
    }

    public void setImmunizationTypeList(List<Integer> immunizationTypeList) {
        this.immunizationTypeList = immunizationTypeList;
    }

    @Override
    public String toString() {
        return "ImmunizationRules{" +
                "immunizationRuleID=" + immunizationRuleID +
                ", studentMinAgeMonths=" + studentMinAgeMonths +
                ", studentMaxAgeMonths=" + studentMaxAgeMonths +
                ", immunizationTypeList=" + immunizationTypeList +
                '}';
    }
}
