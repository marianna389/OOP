package ru.nsu.mkorovkina.task141;

/**
 * Grade for a subject.
 */
public enum Grade {
    UNSATISFACTORY(2),
    SATISFACTORY(3),
    GOOD(4),
    EXCELLENT(5),
    PASSED(null),
    NOT_PASSED(null);

    private final Integer value;

    Grade(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
