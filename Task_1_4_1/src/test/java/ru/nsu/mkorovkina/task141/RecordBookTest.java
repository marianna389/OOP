package ru.nsu.mkorovkina.task141;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class RecordBookTest {

    @Test
    void averageGrade() {
        Semester semester1 = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.SATISFACTORY),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.GOOD),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT,
                        Grade.SATISFACTORY),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester2 = new Semester(2, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Discrete mathematics", ControlType.EXAM, null),
                new Subject("Imperative programming", ControlType.EXAM, Grade.SATISFACTORY),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        List<Semester> semesterList = List.of(semester1, semester2);
        RecordBook recordBook = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList);
        recordBook.nextSemester();
        assertEquals(3.6, recordBook.averageGrade());
        semester2.getSubjectList().get(1).setGrade(Grade.SATISFACTORY);
        assertEquals(3.5, recordBook.averageGrade());
        Semester semester = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, null),
                new Subject("Discrete mathematics", ControlType.EXAM, null),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT, null)
        ));
        List<Semester> semesterList2 = List.of(semester);
        RecordBook recordBook2 = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList2);
        assertEquals(0.0, recordBook2.averageGrade());
    }

    @Test
    void transferToBudget() {
        Semester semester1 = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.GOOD),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.GOOD),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT,
                        Grade.SATISFACTORY),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester2 = new Semester(2, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, null),
                new Subject("Discrete mathematics", ControlType.EXAM, null),
                new Subject("Imperative programming", ControlType.EXAM, null),
                new Subject("Physical education", ControlType.CREDIT, null)
        ));
        List<Semester> semesterList = List.of(semester1, semester2);
        RecordBook recordBook = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList);
        assertFalse(recordBook.transferToBudget());
        recordBook.nextSemester();
        assertTrue(recordBook.transferToBudget());
        semester2.getSubjectList().get(0).setGrade(Grade.UNSATISFACTORY);
        assertFalse(recordBook.transferToBudget());
        semester2.getSubjectList().get(0).setGrade(Grade.GOOD);
        semester2.getSubjectList().get(1).setGrade(Grade.SATISFACTORY);
        assertFalse(recordBook.transferToBudget());
        semester2.getSubjectList().get(1).setGrade(Grade.GOOD);
        assertTrue(recordBook.transferToBudget());
        semester2.getSubjectList().get(3).setGrade(Grade.NOT_PASSED);
        assertFalse(recordBook.transferToBudget());
        semester2.getSubjectList().get(3).setGrade(Grade.PASSED);
        assertTrue(recordBook.transferToBudget());
        recordBook.setEducationType(EducationType.BUDGET);
        assertFalse(recordBook.transferToBudget());
    }

    @Test
    void increasedScholarship() {
        Semester semester1 = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT,
                        Grade.EXCELLENT),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester2 = new Semester(2, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, null),
                new Subject("Discrete mathematics", ControlType.EXAM, null),
                new Subject("Imperative programming", ControlType.EXAM, null),
                new Subject("Physical education", ControlType.CREDIT, null)
        ));
        List<Semester> semesterList = List.of(semester1, semester2);
        RecordBook recordBook = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList);
        assertFalse(recordBook.increasedScholarship());
        recordBook.setEducationType(EducationType.BUDGET);
        assertTrue(recordBook.increasedScholarship());
        recordBook.nextSemester();
        semester2.getSubjectList().get(0).setGrade(Grade.EXCELLENT);
        semester2.getSubjectList().get(1).setGrade(Grade.EXCELLENT);
        assertTrue(recordBook.increasedScholarship());
        semester2.getSubjectList().get(2).setGrade(Grade.GOOD);
        assertFalse(recordBook.increasedScholarship());
        semester2.getSubjectList().get(2).setGrade(Grade.EXCELLENT);
        assertTrue(recordBook.increasedScholarship());
        semester2.getSubjectList().get(3).setGrade(Grade.NOT_PASSED);
        assertFalse(recordBook.increasedScholarship());
        semester2.getSubjectList().get(3).setGrade(Grade.PASSED);
        assertTrue(recordBook.increasedScholarship());
    }

    @Test
    void redDiploma() {
        Semester semester1 = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.GOOD),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT,
                        Grade.GOOD),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester2 = new Semester(2, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.GOOD),
                new Subject("Imperative programming", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester3 = new Semester(3, List.of(
                new Subject("Models of computation", ControlType.DIFF_CREDIT, null),
                new Subject("Introduction to AI", ControlType.EXAM, null),
                new Subject("Object-oriented programming", ControlType.DIFF_CREDIT, null)
        ));
        List<Semester> semesterList = List.of(semester1, semester2, semester3);
        RecordBook recordBook = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList);
        recordBook.nextSemester();
        assertTrue(recordBook.redDiploma());
        semester2.getSubjectList().get(3).setGrade(Grade.NOT_PASSED);
        assertFalse(recordBook.redDiploma());
        semester2.getSubjectList().get(3).setGrade(Grade.PASSED);
        recordBook.nextSemester();
        semester3.getSubjectList().get(0).setGrade(Grade.EXCELLENT);
        semester3.getSubjectList().get(1).setGrade(Grade.GOOD);
        assertFalse(recordBook.redDiploma());
        semester3.getSubjectList().get(1).setGrade(Grade.EXCELLENT);
        assertTrue(recordBook.redDiploma());
        recordBook.setDiplomaGrade(Grade.GOOD);
        assertFalse(recordBook.redDiploma());
        recordBook.setDiplomaGrade(Grade.EXCELLENT);
        semester3.getSubjectList().get(2).setGrade(Grade.SATISFACTORY);
        assertFalse(recordBook.redDiploma());
    }

    @Test
    void complexTest() {
        Semester semester1 = new Semester(1, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.GOOD),
                new Subject("Discrete mathematics", ControlType.EXAM, Grade.GOOD),
                new Subject("Imperative programming", ControlType.DIFF_CREDIT,
                        Grade.SATISFACTORY),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester2 = new Semester(2, List.of(
                new Subject("Mathematical analysis", ControlType.EXAM, Grade.EXCELLENT),
                new Subject("Discrete mathematics", ControlType.EXAM, null),
                new Subject("Imperative programming", ControlType.EXAM, Grade.GOOD),
                new Subject("Physical education", ControlType.CREDIT, Grade.PASSED)
        ));
        Semester semester3 = new Semester(3, List.of(
                new Subject("Models of computation", ControlType.DIFF_CREDIT, null),
                new Subject("Introduction to AI", ControlType.EXAM, null),
                new Subject("Object-oriented programming", ControlType.DIFF_CREDIT, null)
        ));
        Semester semester4 = new Semester(4, List.of(
                new Subject("Models of computation", ControlType.EXAM, null),
                new Subject("Theory of concurrency", ControlType.EXAM, null),
                new Subject("Object-oriented programming", ControlType.EXAM, null)
        ));
        Semester semester5 = new Semester(5, List.of(
                new Subject("Software architecture", ControlType.DIFF_CREDIT, null),
                new Subject("Industrial programming in C++", ControlType.EXAM, null),
                new Subject("Computational mathematics", ControlType.EXAM, null)
        ));
        Semester semester6 = new Semester(6, List.of(
                new Subject("Software architecture", ControlType.EXAM, null),
                new Subject("Computational linguistics", ControlType.DIFF_CREDIT, null),
                new Subject("User interface design", ControlType.DIFF_CREDIT, null)
        ));
        Semester semester7 = new Semester(7, List.of(
                new Subject("Practice", ControlType.PRACTICE, null),
                new Subject("information protection", ControlType.DIFF_CREDIT, null)
        ));
        Semester semester8 = new Semester(8, List.of(
                new Subject("Industrial practice", ControlType.PRACTICE, null),
                new Subject("Economics", ControlType.DIFF_CREDIT, null)
        ));
        List<Semester> semesterList = List.of(semester1, semester2, semester3, semester4,
                semester5, semester6, semester7, semester8);
        RecordBook recordBook = new RecordBook("Ivan Ivanov", EducationType.PAID,
                semesterList);
        recordBook.nextSemester();
        assertEquals(4.0, recordBook.averageGrade());
        assertTrue(recordBook.transferToBudget());
        recordBook.setEducationType(EducationType.BUDGET);
        assertFalse(recordBook.increasedScholarship());
        semester1.getSubjectList().get(2).setGrade(Grade.EXCELLENT);
        semester2.getSubjectList().get(2).setGrade(Grade.EXCELLENT);
        assertTrue(recordBook.increasedScholarship());
        assertTrue(recordBook.redDiploma());
        semester2.getSubjectList().get(1).setGrade(Grade.GOOD);
        semester2.getSubjectList().get(2).setGrade(Grade.GOOD);
        recordBook.nextSemester();
        semester3.getSubjectList().get(0).setGrade(Grade.EXCELLENT);
        semester3.getSubjectList().get(1).setGrade(Grade.GOOD);
        semester3.getSubjectList().get(2).setGrade(Grade.EXCELLENT);
        recordBook.nextSemester();
        semester4.getSubjectList().get(0).setGrade(Grade.GOOD);
        semester4.getSubjectList().get(1).setGrade(Grade.GOOD);
        assertFalse(recordBook.redDiploma());
        semester4.getSubjectList().get(1).setGrade(Grade.EXCELLENT);
        assertTrue(recordBook.redDiploma());
        recordBook.setDiplomaGrade(Grade.GOOD);
        assertFalse(recordBook.redDiploma());
    }
}
