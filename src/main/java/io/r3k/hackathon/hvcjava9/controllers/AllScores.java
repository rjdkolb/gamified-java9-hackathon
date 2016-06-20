package io.r3k.hackathon.hvcjava9.controllers;

import java.util.Map;

public class AllScores {

    Map<String, UserScore> score;

    public AllScores() {
    }
    public AllScores(Map<String, UserScore> score) {
        this.score = score;
    }
    public Map<String, UserScore> getScore() {
        return score;
    }

    public void setScore(Map<String, UserScore> score) {
        this.score = score;
    }

}
