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
 * @version     2.0     2020년 6월 6일
 * @author      조연우
 */

public class Computer {
    ArrayList<Character> answer = new ArrayList<Character>();     //컴퓨터가 만든 답
    ArrayList<Character> input = new ArrayList<Character>();      //게임하는 사람이 추측한 입력 값

    public Player newPlayer;

    protected void gameStart(){    //처음 시작
        makeAnswer();
    }

    private void makeAnswer(){
        Random random = new Random();
        String nowNum = "";
        for(int i=0; i<3; i++){
            String number = Integer.toString(random.nextInt(10));
            if(!number.equals(0)){
                if(!nowNum.contains(number)){
                    nowNum += number;
                } else{
                    i--;
                }
            } else{
                i--;
            }
        }
        for(int i=0; i<nowNum.length(); i++) {
            answer.add(nowNum.charAt(i));
        }
        newGame();
    }

    private void newGame(){  //새롭게 입력받는 부분
        newPlayer = new Player();

        input = newPlayer.inputAnswer();
        printHint();
    }

    private void printHint(){    //입력에 대한 힌트 계산, 출력
        int strike = 0;
        int ball = 0;

        for(int i=0; i<3; i++) {
            if(answer.get(i)==input.get(i)){
                strike++;
            } else {
                ball+=ballCount(i);
            }
        }
        if(strike == 3){
            isRight();
        }else if((ball == 0) && (strike == 0)) {
            System.out.println("nothing");
            newGame();
        }else if(strike == 0){
            System.out.println(ball+"ball");
            newGame();
        }else if(ball == 0){
            System.out.println(strike+"strike");
            newGame();
        }else{
            System.out.println(strike+"strike "+ball+"ball");
            newGame();
        }
    }

    private int ballCount(int i){
        int b = 0;
        for(int j=0; j<3; j++){
            if(answer.get(i)==input.get(j)){
                b++;
            }
        }
        return b;
    }

    private static void isRight(){
        System.out.println("You Win! Game Finish");
        System.out.print("if you want restart-> 1 done -> 2 : ");
        againOrDone();
    }

    private static void againOrDone(){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        if(num == 1){
            Computer newComputer = new Computer();
            newComputer.gameStart();
        } else if(num == 2){
            System.exit(0);
        } else{
            System.out.print("wrong number. input again : ");
            againOrDone();
        }
    }
}
