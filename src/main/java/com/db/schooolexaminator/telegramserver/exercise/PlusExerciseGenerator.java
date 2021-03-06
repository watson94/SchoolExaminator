package com.db.schooolexaminator.telegramserver.exercise;

import com.db.schooolexaminator.model.OperationConstraint;

import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class PlusExerciseGenerator implements ExerciseGenerator {

    OperationConstraint operationConstraint;

    NumberConstraint constraintA;
    NumberConstraint constraintB;
    NumberConstraint constraintAns;



    Random r = new Random();



    public PlusExerciseGenerator(OperationConstraint operationConstraint) {
        constraintA = new NumberConstraint(operationConstraint.getMinA(), operationConstraint.getMaxA(), operationConstraint.getExceptAListInteger(), operationConstraint.getSpecialAListInteger());
        constraintB = new NumberConstraint(operationConstraint.getMinB(), operationConstraint.getMaxB(), operationConstraint.getExceptBListInteger(), operationConstraint.getSpecialBListInteger());
        constraintAns = new NumberConstraint(operationConstraint.getMinAnswer(), operationConstraint.getMaxAnswer());
    }

    @Override
    public Exercise generate() {
        Exercise exercise = new Exercise();
        exercise.setOperation(Operation.PLUS);

        if (constraintA.getMaxValue() > constraintAns.getMaxValue()) {
            constraintA.setMaxValue(constraintAns.getMaxValue());
        }

        int a = constraintA.generateNumber();
        exercise.setA(a);

        if ((a + constraintB.getMaxValue()) > constraintAns.getMaxValue()) {
            constraintB.setMaxValue(constraintAns.getMaxValue() - a);
        }

        if ((a + constraintB.getMinValue()) < constraintAns.getMinValue()) {
            constraintB.setMinValue(constraintAns.getMinValue() - a);
        }

        int b = constraintB.generateNumber();
        exercise.setB(b);

        exercise.setAnswer(a + b);


        return exercise;
    }
}
