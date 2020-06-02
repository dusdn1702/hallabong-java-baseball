/*
 * Computer.java            1.0.0       2020-06-01
 *
 * Copyright (c) 2020 Yeonwoo Cho
 * ComputerScience, ProgrammingLanguage, Java, Seoul, KOREA
 * All rights reserved
 */

package com.baseball.yeonwoo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Computer.class
 * 게임을 진행하는 클래스
 *
 * @version     1.1     2020년 6월 2일
 * @author      조연우
 */

public class Computer {
    public static char[] answer;
    public Player newPlayer;
    public static char[] input;
    public void gameStart(){
        makeAnswer();
    }
    public void makeAnswer(){
        Random random = new Random();
        String nowNum = "";
        for(int i=0;i<3;i++){
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
        answer = nowNum.toCharArray();
        newGame();
    }
    public void newGame(){
        newPlayer = new Player();
        input = newPlayer.inputAnswer();
        printHint();
    }
    public void printHint(){
        int strike = 0;
        int ball =0;
        for(int i=0;i<3;i++) {
            if (answer[i] == input[i]) {
                strike++;
            } else {
                ball+=ballCount(i);
            }
        }
        if(strike==3){
            isRight();
        }else if(ball==0&&strike==0) {
            System.out.println("nothing");
            newGame();
        }else if(strike==0){
            System.out.println(ball+"ball");
            //System.out.println(ball+"볼");
            newGame();
        }else if(ball==0){
            System.out.println(strike+"strike");
            //System.out.println(strike+"스트라이크");
            newGame();
        }else{
            System.out.println(strike+"strike "+ball+"ball");
            //System.out.println(strike+"스트라이크 "+ball+"볼");
            newGame();
        }
    }
    public int ballCount(int i){
        int b = 0;
        for(int j=0;j<3;j++){
            if(answer[i]==input[j]){
                b++;
            }
        }
        return b;
    }
    public static void isRight(){
        System.out.println("You Win! Game Finish");
        //System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.print("if you want restart-> 1 done -> 2 : ");
        //System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요 : ");
        againOrDone();
    }
    public static void againOrDone(){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        if(num==1){
            Computer newComputer = new Computer();
            newComputer.gameStart();
        } else if(num==2){
            System.exit(0);
        } else{
            //System.out.print("잘못 입력하셨습니다. 다시 입력하세요 : ");
            System.out.print("wrong number. input again : ");
            againOrDone();
        }
    }
}
