/*
 * Computer.java            1.0.0       2020-06-01
 *
 * Copyright (c) 2020 Yeonwoo Cho
 * ComputerScience, ProgrammingLanguage, Java, Seoul, KOREA
 * All rights reserved
 */

package com.baseball.yeonwoo;

import java.util.Random;
import java.util.Scanner;

/**
 * Computer.class
 * 게임을 진행하는 클래스
 *
 * @version     1.0     2020년 6월 1일
 * @author      조연우
 */

public class Computer {
    public static char[] answer;
    public Player newPlayer;
    public static char[] input;

    public void gameStart(){
        makeGame();
    }
    public void makeGame(){
        newPlayer = new Player();
        input = newPlayer.inputAnswer();
        Random random = new Random();
        String nowNum = "";
        for(int i=0;i<3;i++){
            String number = Integer.toString(random.nextInt(10));
            if(!number.equals(0)){
                if(!nowNum.contains(number)){
                    nowNum += number;
                }
                else{
                    i--;
                }
            }
            else{
                i--; //i-=1;
            }
        }
        answer = nowNum.toCharArray();
        System.out.print(input);
    }
    public static void printHint(){
        //스트라이크, 볼, 낫싱 출력
        //3스트라이크는 정답
        //정답이면 isright로
    }
    public static void isRight(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요 : ");
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
            System.out.print("잘못 입력하셨습니다. 다시 입력하세요 : ");
            againOrDone();
        }
    }
}
