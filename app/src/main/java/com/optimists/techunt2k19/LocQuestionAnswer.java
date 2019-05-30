package com.optimists.techunt2k19;

/**
 * Created by heman on 04-03-2018.
 */

public class LocQuestionAnswer {
    private int questionID;
    private int answer;

    LocQuestionAnswer(int quesID, int ans)
    {
        questionID=quesID;
        answer=ans;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
