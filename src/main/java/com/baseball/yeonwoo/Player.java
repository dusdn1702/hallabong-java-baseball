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
    String myAnswer = "";

    ArrayList<Character> playerAnswer = new ArrayList<Character>();
    Scanner scan = new Scanner(System.in);

    protected ArrayList<Character> inputAnswer() {
        System.out.print("input three numbers : ");
        myAnswer = scan.nextLine();
        if (!rangeOk(myAnswer) || (!lengthOk(myAnswer)) || (!noSame(myAnswer))) {
            somethingWrong();
        }
        for (int i = 0; i < myAnswer.length(); i++) {
            playerAnswer.add(myAnswer.charAt(i));
        }
        return playerAnswer;
    }

    private boolean rangeOk(String s) {
        char[] c = s.toCharArray();

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if ((c[i] < '1') || (c[i] > '9')) {
                return false;
            }
        }
        return true;
    }

    private boolean lengthOk(String s) {
        if (s.length() != NUMBER_LENGTH) {
            return false;
        }
        return true;
    }

    private boolean noSame(String s) {
        char[] in = s.toCharArray();

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (in[i] == in[j]) {
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

