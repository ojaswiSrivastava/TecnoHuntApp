package com.optimists.techunt2k19;

import android.graphics.Typeface;
import android.os.Build;

import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alexfu.countdownview.CountDownView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TeamQuiz extends AppCompatActivity {



    CountDownView countDownView;
    TextView score;
    TextView round;
    TextView question;
    TextView locationQuestion;
    RadioButton r1,r2,r3,r4;
    ProgressBar progressBar;
    EditText editText;
    Button b1,b2;
    private int currentScore=0;
    private int currentRound=1;
    private int index=0,locIndex=0;
    private int increment=0;
    private int time;
    long time1,time2,time3;
    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    DatabaseReference child;
    DatabaseReference currentTime;
    DatabaseReference scoreUpdate;
    DatabaseReference startTime;
    DatabaseReference timeFinished;



    String[] question_answer;
    String[] location_questions;
    int teamNumberInteger;
    int numOfSetOfQuestions;
    int numOfSetOfLocationQuestions;
    int indexForSettingNextQuestion;
    int indexForSettingNextLocationQuestion;


    @Override
    public void onBackPressed()
    {
        //disable back button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_quiz);

        String teamSelectedInWords = getIntent().getStringExtra("Team");



        child=root.child(teamSelectedInWords);
        currentTime=child.child("TimeTakenInSec");
        scoreUpdate=child.child("Score");
        startTime=child.child("StartTime");
        timeFinished=child.child("TimeFinished");

        time = 3600000;
        countDownView = findViewById(R.id.textView4);
        countDownView.setStartDuration(time);
        countDownView.start();
        score = findViewById(R.id.scoreTextView);
        round = findViewById(R.id.roundTextView);
        question = findViewById(R.id.questionTextView);
        locationQuestion = findViewById(R.id.lQuestionTextView);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "OswaldR.ttf");
        Typeface customFont2 = Typeface.createFromAsset(getAssets(), "BebasNeuR.ttf");

        question_answer = getResources().getStringArray(R.array.questions);
        location_questions = getResources().getStringArray(R.array.loc_questions);
        numOfSetOfQuestions = ((question_answer.length)/6)/5;
        numOfSetOfLocationQuestions = ((location_questions.length)/2)/5;


        //int indexForSetting = (teamNumberInteger-1) % numOfSetOfQuestions;
        //Log.d(TAG,"indexForSetting="+indexForSetting);

        //Setting text fonts
        round.setTypeface(customFont2);
        question.setTypeface(customFont);
        r1.setTypeface(customFont);
        r2.setTypeface(customFont);
        r3.setTypeface(customFont);
        r4.setTypeface(customFont);
        b1.setTypeface(customFont);
        score.setTypeface(customFont);
        locationQuestion.setTypeface(customFont);
        editText.setTypeface(customFont);
        b2.setTypeface(customFont);

        round.setText("Round " + currentRound);


        locationQuestion.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);


        String teamNumber= teamSelectedInWords.replaceAll("[^0-9]", "");
        teamNumberInteger = Integer.parseInt(teamNumber);
        Log.d("OJASWI","teamSelectedInWords="+teamSelectedInWords);
        Log.d("OJASWI","teamNumber="+teamNumber);
        Log.d("OJASWI","teamNumberInteger="+teamNumberInteger);






        //INITIAL SET OF LOGICAL QUESTIONS SHOWN
        indexForSettingNextQuestion = findIndexForQuestion(teamNumberInteger, numOfSetOfQuestions);
        question.setText(question_answer[indexForSettingNextQuestion]);
        r1.setText(question_answer[indexForSettingNextQuestion +1]);
        r2.setText(question_answer[indexForSettingNextQuestion +2]);
        r3.setText(question_answer[indexForSettingNextQuestion +3]);
        r4.setText(question_answer[indexForSettingNextQuestion +4]);

        String ans_s = question_answer[indexForSettingNextQuestion+5];
        char ans = ans_s.charAt(0);
        String ans_s2=String.valueOf(ans);



        SimpleDateFormat startTimeObj = new SimpleDateFormat();
        String startTimeString = startTimeObj.format(new Date());
        startTime.setValue(startTimeString);
        time1 = System.currentTimeMillis();
        timeFinished.setValue(false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                alertPopup();

            }
        }, 3600000);

    }


    void alertPopup()
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("The Time is Up");
        alertDialog.setMessage("Thanks For Playing! Better Luck Next Year ;-)");
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.alertgameover,null));
        alertDialog.setCancelable(false);
        alertDialog.show();
        timeFinished.setValue(true);
    }

    public int findIndexForQuestion(int teamNumberInteger, int numOfSetOfQuestions){


        int indexForSetting = ((teamNumberInteger-1) % numOfSetOfQuestions)*30;
        Log.d("OJASWI","indexForSetting="+indexForSetting);

        return indexForSetting;
    }
    public int findIndexForLocationQuestion(int teamNumberInteger, int numOfSetOfLocationQuestions){


        int indexForSetting = ((teamNumberInteger-1) % numOfSetOfLocationQuestions)*9;
        Log.d("OJASWI","indexForSettinglocques="+indexForSetting);

        return indexForSetting;
    }





    public void setNextQuestion(boolean ifUpdate)
    {
        question=findViewById(R.id.questionTextView);
        if(ifUpdate==true)
        {

            indexForSettingNextQuestion = (indexForSettingNextQuestion +6);
            question.setText(question_answer[indexForSettingNextQuestion]);
            r1.setText(question_answer[indexForSettingNextQuestion +1]);
            r2.setText(question_answer[indexForSettingNextQuestion +2]);
            r3.setText(question_answer[indexForSettingNextQuestion +3]);
            r4.setText(question_answer[indexForSettingNextQuestion +4]);
        }
        else
        {
            question.setText(question_answer[indexForSettingNextQuestion]);//OR PUT 0 for first INDEX
        }
    }



    public void onSubmit(View view)
    {
        Log.d("OJASWI","indexForSettingNextQuestion="+indexForSettingNextQuestion);
        Log.d("OJASWI","numOfSetOfQuestions="+ numOfSetOfQuestions);
        Log.d("OJASWI","teamNumberInteger="+teamNumberInteger);

        if(r1.isChecked())
            checkAnswer('a');
        if(r2.isChecked())
            checkAnswer('b');
        if(r3.isChecked())
            checkAnswer('c');
        if(r4.isChecked())
            checkAnswer('d');
    }

    public void checkAnswer(char userAnswer)
    {
        int totalQuestions= 10;
        increment=(int)Math.ceil(100/totalQuestions);

        char correctAnswer= (question_answer[indexForSettingNextQuestion+5].charAt(0));;
        if(correctAnswer==userAnswer)
        {
            Toast.makeText(this,"That's Correct",Toast.LENGTH_SHORT).show();
            if(currentScore==0)
            {
                setNextLocQuestion(false);
            }
            else
            {
                setNextLocQuestion(true);
            }
            question.setVisibility(View.INVISIBLE);
            r1.setVisibility(View.INVISIBLE);
            r2.setVisibility(View.INVISIBLE);
            r3.setVisibility(View.INVISIBLE);
            r4.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);

            locationQuestion.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);

            progressBar.incrementProgressBy(increment);
            currentScore++;
            scoreUpdate.setValue(currentScore);
            time2=System.currentTimeMillis();
            time3=(time2-time1);
            currentTime.setValue(time3/1000);
            score.setText("Score- " + currentScore + "/" + "10");
        }
        else
        {
            Toast.makeText(this,"Wrong!",Toast.LENGTH_SHORT).show();
            setNextLocQuestion(false);
            shakeIt();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Wrong Answer");
            builder.setMessage("After 30 seconds dialog will close automatically");
            builder.setCancelable(false);

            final AlertDialog closedialog= builder.create();

            closedialog.show();

            final Timer timer2 = new Timer();
            timer2.schedule(new TimerTask() {
                public void run() {
                    closedialog.dismiss();
                    timer2.cancel(); //this will cancel the timer of the system
                }
            }, 30000);//60000

        }
    }

    public void onSubmit2(View view) {
        String userAns = editText.getText().toString();
        if (userAns.isEmpty()) {
            Toast.makeText(this,"Can't be empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            int userAnswer = Integer.parseInt(userAns);
            checkLocAnswer(userAnswer);
            Log.d("OJASWI","indexForSettingNextLocationQuestion="+indexForSettingNextLocationQuestion);
            Log.d("OJASWI","numOfSetOfLocationQuestions="+ numOfSetOfLocationQuestions);
            Log.d("OJASWI","teamNumberInteger="+teamNumberInteger);
        }
    }

    void checkLocAnswer(int userAnswer)
    {
        int totalQuestions= 10;
        increment=(int)Math.ceil(100/totalQuestions);
        int correctAnswer=Integer.parseInt(location_questions[indexForSettingNextLocationQuestion+1]);
        round=findViewById(R.id.roundTextView);
        score=findViewById(R.id.scoreTextView);
        if(correctAnswer==userAnswer)
        {
            progressBar.incrementProgressBy(increment);
            locationQuestion.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);


            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);


            setNextQuestion(true);
            currentScore++;
            Toast.makeText(this,"That's Corrent!",Toast.LENGTH_SHORT).show();
            score.setText("Score- " + currentScore + "/" + "10");
            currentRound++;

            scoreUpdate.setValue(currentScore);
            time2=System.currentTimeMillis();
            time3=(time2-time1);
            currentTime.setValue(time3/1000);

            if(currentRound==5)
            {
                round.setText("Final Round");
            }
            else if(currentRound==6)
            {
                round.setText("Hunt Has Been Completed");
            }
            else {
                round.setText("Round " + currentRound);
            }
            if(currentScore==10)
            {
                LayoutInflater inflater = this.getLayoutInflater();

                AlertDialog alertDialog=new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Your team has finished the game.");
                alertDialog.setMessage("Show this message to the Room no.119 in AB-5.");
                alertDialog.setView(inflater.inflate(R.layout.alert,null));
                alertDialog.setCancelable(false);
                alertDialog.show();


                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.INVISIBLE);
                r4.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);


            }
        }
        else
        {
            setNextQuestion(false);
            Toast.makeText(this,"Wrong!",Toast.LENGTH_SHORT).show();
            shakeIt();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Wrong Answer");
            builder.setMessage("After 10 seconds dialog will close automatically");
            builder.setCancelable(false);

            final AlertDialog closedialog= builder.create();

            closedialog.show();

            final Timer timer2 = new Timer();
            timer2.schedule(new TimerTask() {
                public void run() {
                    closedialog.dismiss();
                    timer2.cancel(); //this will cancel the timer of the system
                }
            }, 10000);//60000

        }

    }

    void setNextLocQuestion(boolean update)
    {

        locationQuestion=findViewById(R.id.lQuestionTextView);
        if(update==true)
        {

            indexForSettingNextLocationQuestion = (indexForSettingNextLocationQuestion +2);
            locationQuestion.setText(location_questions[indexForSettingNextLocationQuestion]);

        }
        else
        {
            indexForSettingNextLocationQuestion = findIndexForLocationQuestion(teamNumberInteger, numOfSetOfLocationQuestions);
            locationQuestion.setText(location_questions[indexForSettingNextLocationQuestion]);


        }
    }

    private void shakeIt() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(200);
        }
    }
}
