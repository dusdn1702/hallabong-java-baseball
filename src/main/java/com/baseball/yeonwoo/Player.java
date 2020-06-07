/*
 * Player.java            2.0       2020-06-06
 *
 * Copyright (c) 2020 Yeonwoo Cho
 * ComputerScience, ProgrammingLanguage, Java, Seoul, KOREA
 * All rights reserved
 */

package com.baseball.yeonwoo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player.class
 * 게임을 하는 클래스
 *
 * @author 조연우
 * @version 2.0     2020년 6월 6일
 */

public class Player {
    public static final int NUMBER_LENGTH = 3;

    ArrayList<Character> playerAnswer = new ArrayList<Character>();
    Scanner scan = new Scanner(System.in);

    protected ArrayList<Character> inputAnswer() {
        String myAnswer = "";
        playerAnswer.clear();

        myAnswer = scan.nextLine();
        System.out.print("input three numbers : ");
        if (!rangeOk(myAnswer) || (!lengthOk(myAnswer)) || (!noSame(myAnswer))) {
            somethingWrong();
        }
        for (int storeNumberIndex = 0; storeNumberIndex < myAnswer.length(); storeNumberIndex++) {
            playerAnswer.add(myAnswer.charAt(storeNumberIndex));
        }
        return playerAnswer;
    }

    private boolean rangeOk(String myAnswer) {
        char[] tempMyAnswer = myAnswer.toCharArray();

        for (int findWrongNumberIndex = 0; findWrongNumberIndex < NUMBER_LENGTH; findWrongNumberIndex++) {
            if ((tempMyAnswer[findWrongNumberIndex] < '1') || (tempMyAnswer[findWrongNumberIndex] > '9')) {
                return false;
            }
        }
        return true;
    }

    private boolean lengthOk(String myAnswer) {
        if (myAnswer.length() != NUMBER_LENGTH) {
            return false;
        }
        return true;
    }

    private boolean noSame(String myAnswer) {
        char[] tempMyAnswer = myAnswer.toCharArray();

        for (int findSameNumberIndex = 0; findSameNumberIndex < myAnswer.length() - 1; findSameNumberIndex++) {
            for (int compareSameNumberIndex = findSameNumberIndex + 1; compareSameNumberIndex < myAnswer.length(); compareSameNumberIndex++) {
                if (tempMyAnswer[findSameNumberIndex] == tempMyAnswer[compareSameNumberIndex]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void somethingWrong() {
        System.out.println("input again");
        scan = new Scanner(System.in);
        inputAnswer();
    }
}

