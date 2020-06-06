/*
 * Computer.java            2.0       2020-06-06
 *
 * Copyright (c) 2020 Yeonwoo Cho
 * ComputerScience, ProgrammingLanguage, Java, Seoul, KOREA
 * All rights reserved
 */

package com.baseball.yeonwoo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Computer.class
 * 게임을 진행하는 클래스
 *
 * @author 조연우
 * @version 2.0     2020년 6월 6일
 */

public class Computer {
    public static final int NUMBER_LENGTH = 3;

    public Player newPlayer;

    ArrayList<Character> computerAnswer = new ArrayList<Character>();     //컴퓨터가 만든 답
    ArrayList<Character> playerInput = new ArrayList<Character>();      //게임하는 사람이 추측한 입력 값

    protected void gameStart() {    //처음 시작
        makeAnswer();
    }

    private void makeAnswer() {
        String currentNumber = "";
        Random random = new Random();
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            String number = Integer.toString(random.nextInt(10));
            if (!number.equals(0)) {
                if (!currentNumber.contains(number)) {
                    currentNumber += number;
                } else {
                    i--;
                }
            } else {
                i--;
            }
        }
        for (int i = 0; i < currentNumber.length(); i++) {
            computerAnswer.add(currentNumber.charAt(i));
        }
        newGame();
    }

    private void newGame() {  //새롭게 입력받는 부분
        newPlayer = new Player();
        playerInput = newPlayer.inputAnswer();
        printHint();
    }

    private void printHint() {    //입력에 대한 힌트 계산, 출력
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (computerAnswer.get(i) == playerInput.get(i)) {
                strike++;
            } else {
                ball += ballCount(i);
            }
        }
        if (strike == 3) {
            isRight();
        } else if ((ball == 0) && (strike == 0)) {
            System.out.println("nothing");
            newGame();
        } else if (strike == 0) {
            System.out.println(ball + "ball");
            newGame();
        } else if (ball == 0) {
            System.out.println(strike + "strike");
            newGame();
        } else {
            System.out.println(strike + "strike " + ball + "ball");
            newGame();
        }
    }

    private int ballCount(int i) {
        int b = 0;
        for (int j = 0; j < NUMBER_LENGTH; j++) {
            if (computerAnswer.get(i) == playerInput.get(j)) {
                b++;
            }
        }
        return b;
    }

    private static void isRight() {
        System.out.println("You Win! Game Finish");
        System.out.print("if you want restart-> 1 done -> 2 : ");
        againOrDone();
    }

    private static void againOrDone() {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        if (num == 1) {
            Computer newComputer = new Computer();
            newComputer.gameStart();
        } else if (num == 2) {
            System.exit(0);
        } else {
            System.out.print("wrong number. input again : ");
            againOrDone();
        }
    }
}
