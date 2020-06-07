/*
 * Computer.java            2.1.0       2020-06-07
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
 * @version 2.1     2020년 6월 7일
 */

public class Computer {
    public static final int NUMBER_LENGTH = 3;
    protected Player newPlayer;

    ArrayList<Character> computerAnswer = new ArrayList<Character>();     //컴퓨터가 만든 답
    ArrayList<Character> playerInput = new ArrayList<Character>();      //게임하는 사람이 추측한 입력 값
    private Scanner scan = new Scanner(System.in);

    protected Computer(){
        newPlayer = new Player();
    }

    protected void gameStart() {    //처음 시작
        makeAnswer();
    }

    private void makeAnswer() {
        String currentNumber = "";
        Random random = new Random();
        for (int generatingNumberIndex = 0; generatingNumberIndex < NUMBER_LENGTH; generatingNumberIndex++) {
            String number = Integer.toString(random.nextInt(10));
            if (number.equals("0")){
                generatingNumberIndex--;
            } else {
                if (!currentNumber.contains(number)) {
                    currentNumber += number;
                } else {
                    generatingNumberIndex--;
                }
            }
        }
        for (int storeNumberIndex = 0; storeNumberIndex < currentNumber.length(); storeNumberIndex++) {
            computerAnswer.add(currentNumber.charAt(storeNumberIndex));
        }
        getNewInput();
    }

    private void getNewInput() {  //새롭게 입력받는 부분
        playerInput = newPlayer.inputAnswer();
        printHint();
    }

    private void printHint() {    //입력에 대한 힌트 계산, 출력
        int strike = 0;
        int ball = 0;
        for (int findHintIndex = 0; findHintIndex < NUMBER_LENGTH; findHintIndex++) {
            if (computerAnswer.get(findHintIndex) == playerInput.get(findHintIndex)) {
                strike++;
            } else {
                ball += countBall(findHintIndex);
            }
        }
        if (strike == 3) {
            isRight();
        } else if ((ball == 0) && (strike == 0)) {
            System.out.println("nothing");
            getNewInput();
        } else if (strike == 0) {
            System.out.println(ball + "ball");
            getNewInput();
        } else if (ball == 0) {
            System.out.println(strike + "strike");
            getNewInput();
        } else {
            System.out.println(strike + "strike " + ball + "ball");
            getNewInput();
        }
    }

    private int countBall(int findHintComputerIndex) {
        int countBall = 0;
        for (int findHintInPlayerIndex = 0; findHintInPlayerIndex < NUMBER_LENGTH; findHintInPlayerIndex++) {
            if (computerAnswer.get(findHintComputerIndex) == playerInput.get(findHintInPlayerIndex)) {
                countBall++;
            }
        }
        return countBall;
    }

    private void isRight() {
        System.out.println("You Win! Game Finish");
        System.out.print("if you want restart-> 1 done -> 2 : ");
        playAgainOrDone();
    }

    private void playAgainOrDone() {
        int playAgainNumber = scan.nextInt();
        if (playAgainNumber == 1) {
            Computer newComputer = new Computer();
            newComputer.gameStart();
        } else if (playAgainNumber == 2) {
            System.exit(0);
        } else {
            System.out.print("wrong number. input again : ");
            playAgainOrDone();
        }
    }
}
