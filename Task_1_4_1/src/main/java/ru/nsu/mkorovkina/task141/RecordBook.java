package ru.nsu.mkorovkina.task141;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Student’s record book for assessing academic progress.
 * The semester list contains the entire curriculum, including future semesters.
 * Ungraded assessments are represented by null grades.
 */
public class RecordBook {
    private final String name;
    private EducationType educationType;
    private Grade diplomaGrade;
    private int currentSemester;
    private final List<Semester> semesterList;

    RecordBook(String name, EducationType educationType, List<Semester> semesterList) {
        this.name = name;
        this.educationType = educationType;
        this.diplomaGrade = null;
        this.currentSemester = 1;
        this.semesterList = semesterList;
    }

    /**
     * Calculates the average grade for all assessed subjects.
     *
     * @return the average grade or 0.0 if there are no assessed subjects.
     */
    public double averageGrade() {
        return semesterList.stream()
                .map(Semester::getSubjectList)
                .flatMap(List::stream)
                .map(Subject::getGrade)
                .filter(Objects::nonNull)
                .map(Grade::getValue)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Predicts whether the student can transfer to state-funded education
     * based on the current and previous semesters.
     *
     * @return true if this is possible, otherwise false
     */
    public boolean transferToBudget() {
        if (educationType == EducationType.BUDGET || currentSemester < 2) {
            return false;
        }
        return semesterList.stream()
                .filter(s -> (s.getNumber() == currentSemester ||
                        s.getNumber() == currentSemester - 1))
                .map(Semester::getSubjectList)
                .flatMap(List::stream)
                .noneMatch(s -> (s.getGrade() == Grade.NOT_PASSED ||
                        s.getGrade() == Grade.UNSATISFACTORY || (s.getControlType() ==
                        ControlType.EXAM && s.getGrade() == Grade.SATISFACTORY)));
    }

    /**
     * Predicts whether the student can receive an increased scholarship
     * based on the current semester.
     *
     * @return true if this is possible, otherwise false
     */
    public boolean increasedScholarship() {
        if (educationType == EducationType.PAID) {
            return false;
        }
        return semesterList.stream()
                .filter(s -> s.getNumber() == currentSemester)
                .map(Semester::getSubjectList)
                .flatMap(List::stream)
                .allMatch(s -> s.getGrade() == Grade.EXCELLENT ||
                        s.getGrade() == Grade.PASSED || s.getGrade() == null);
    }

    /**
     * Predicts whether the student can graduate with honors.
     *
     * @return true if this is possible, otherwise false
     */
    public boolean redDiploma() {
        if (diplomaGrade != null && diplomaGrade != Grade.EXCELLENT) {
            return false;
        }
        boolean hasBadGrades = semesterList.stream()
                .map(Semester::getSubjectList)
                .flatMap(List::stream)
                .map(Subject::getGrade)
                .anyMatch(g -> g == Grade.NOT_PASSED || g == Grade.UNSATISFACTORY);
        if (hasBadGrades) {
            return false;
        }
        Map<String, Subject> finalGrades = semesterList.stream()
                .map(Semester::getSubjectList)
                .flatMap(List::stream)
                .filter(s -> s.getControlType() != ControlType.CREDIT)
                .collect(toMap(Subject::getName, s -> s,
                        (oldSubject, newSubject) -> newSubject));
        boolean hasSatisfactoryGrades = finalGrades.values().stream()
                .map(Subject::getGrade)
                .anyMatch(g -> g == Grade.SATISFACTORY);
        if (hasSatisfactoryGrades) {
            return false;
        }
        long total = finalGrades.size();
        long excellentGrades = finalGrades.values().stream()
                .map(Subject::getGrade)
                .filter(g -> g == Grade.EXCELLENT || g == null)
                .count();
        double percent = (double) excellentGrades / total;
        return percent >= 0.75;
    }

    public void setDiplomaGrade(Grade grade) {
        diplomaGrade = grade;
    }

    /**
     * Proceeds to the next semester.
     *
     * @throws IllegalStateException if the last semester has been reached
     */
    public void nextSemester() {
        if (currentSemester == semesterList.size()) {
            throw new IllegalStateException("Last semester");
        }
        currentSemester++;
    }

    public void setEducationType(EducationType newEducationType) {
        educationType = newEducationType;
    }
}
