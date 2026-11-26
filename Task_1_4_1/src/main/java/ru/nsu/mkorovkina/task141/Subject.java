package ru.nsu.mkorovkina.task141;

/**
 * Course description for a specific semester.
 */
public class Subject {
    private final String name;
    private final ControlType controlType;
    private Grade grade;

    Subject(String name, ControlType controlType, Grade grade) {
        this.name = name;
        this.controlType = controlType;
        this.grade = grade;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public void setGrade(Grade newGrade) {
        grade = newGrade;
    }
}
