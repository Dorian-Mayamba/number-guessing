package com.numbers.users;

public class User {
    private String userName;
    private int score;
    public User(String userName){
        this.userName = userName;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
