package ru.nsu.mkorovkina.task141;

import java.util.List;

/**
 * Description of the academic semester with a list of subjects.
 */
public class Semester {
    private final int number;
    private final List<Subject> subjectList;

    Semester(int number, List<Subject> subjectList) {
        this.number = number;
        this.subjectList = subjectList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public int getNumber() {
        return number;
    }
}
