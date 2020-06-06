/*
 * Player.java            2.0       2020-06-06
 *
 * Copyright (c) 2020 Yeonwoo Cho
 * ComputerScience, ProgrammingLanguage, Java, Seoul, KOREA
 * All rights reserved
 */

package com.baseball.yeonwoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Player.class
 * 게임을 하는 클래스
 *
 * @version     2.0     2020년 6월 6일
 * @author      조연우
 */

public class Player {
    Scanner scan = new Scanner(System.in);
    String myAnswer = "";
    ArrayList<Character> playerAnswer = new ArrayList<Character>();

    public ArrayList<Character> inputAnswer(){
        System.out.print("input three numbers : ");
        myAnswer = scan.nextLine();
        if(!rangeOk(myAnswer) || (!lengthOk(myAnswer)) || (!noSame(myAnswer))) {
            somethingWrong();
        }
        for(int i=0; i<myAnswer.length(); i++) {
            playerAnswer.add(myAnswer.charAt(i));
        }
        return playerAnswer;
    }

    public boolean rangeOk(String s){
        char[] c = s.toCharArray();

        for(int i=0; i<3; i++){
            if((c[i]<'1') || (c[i]>'9')){
                return false;
            }
        }
        return true;
    }

    public boolean lengthOk(String s){
        if(s.length() != 3) {
            return false;
        }
        return true;
    }

    public boolean noSame(String s){
        char[] in = s.toCharArray();

        for(int i=0; i<s.length()-1; i++){
            for(int j=i+1; j<s.length(); j++){
                if(in[i] == in[j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void somethingWrong(){
        System.out.println("input again");
        scan = new Scanner(System.in);
        inputAnswer();
    }
}

