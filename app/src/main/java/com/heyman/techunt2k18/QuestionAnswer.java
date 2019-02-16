package com.heyman.techunt2k18;

public class QuestionAnswer {
    private int questionId;
    private int answer1Id;
    private int answer2Id;

    public int getAnswer1Id() {
        return answer1Id;
    }

    public void setAnswer1Id(int answer1Id) {
        this.answer1Id = answer1Id;
    }

    public int getAnswer2Id() {
        return answer2Id;
    }

    public void setAnswer2Id(int answer2Id) {
        this.answer2Id = answer2Id;
    }

    public int getAnswer3Id() {
        return answer3Id;
    }

    public void setAnswer3Id(int answer3Id) {
        this.answer3Id = answer3Id;
    }

    public int getAnswer4Id() {
        return answer4Id;
    }

    public void setAnswer4Id(int answer4Id) {
        this.answer4Id = answer4Id;
    }

    private int answer3Id;
    private int answer4Id;
    private char answer;

    QuestionAnswer(int quesId,char ans,int ans1Id,int ans2Id,int ans3Id, int ans4Id)
    {
        questionId=quesId;
        answer=ans;
        answer1Id=ans1Id;
        answer2Id=ans2Id;
        answer3Id=ans3Id;
        answer4Id=ans4Id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }
}
