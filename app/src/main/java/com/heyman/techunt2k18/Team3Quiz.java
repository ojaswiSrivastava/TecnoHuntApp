package com.heyman.techunt2k18;

import android.graphics.Typeface;
import android.os.Build;

import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Team3Quiz extends AppCompatActivity {

    QuestionAnswer[] array=new QuestionAnswer[]{
            new QuestionAnswer(R.string.question31,'a',R.string.answer311,R.string.answer312,R.string.answer313,R.string.answer314),
            new QuestionAnswer(R.string.question32,'a',R.string.answer321,R.string.answer322,R.string.answer323,R.string.answer324),
            new QuestionAnswer(R.string.question33,'b',R.string.answer331,R.string.answer332,R.string.answer333,R.string.answer334),
            new QuestionAnswer(R.string.question34,'b',R.string.answer341,R.string.answer342,R.string.answer343,R.string.answer344),
            new QuestionAnswer(R.string.question35,'b',R.string.answer351,R.string.answer352,R.string.answer353,R.string.answer354)
    };
    LocQuestionAnswer[] locArray=new LocQuestionAnswer[]
            {
                    new LocQuestionAnswer(R.string.questionL31,7258),
                    new LocQuestionAnswer(R.string.questionL32,3369),
                    new LocQuestionAnswer(R.string.questionL33,9248),
                    new LocQuestionAnswer(R.string.questionL34,7901),
                    new LocQuestionAnswer(R.string.questionL35,4179)
            };


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
    DatabaseReference child=root.child("Team3");
    DatabaseReference currentTime=child.child("TimeTakenInSec");
    DatabaseReference scoreUpdate=child.child("Score");
    DatabaseReference startTime=child.child("StartTime");
    DatabaseReference timeFinished=child.child("TimeFinished");

    @Override
    public void onBackPressed()
    {
        //disable back button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team1quiz);
        time=3600000;
        countDownView=findViewById(R.id.textView4);
        countDownView.setStartDuration(time);
        countDownView.start();
        score=findViewById(R.id.scoreTextView);
        round=findViewById(R.id.roundTextView);
        question=findViewById(R.id.questionTextView);
        locationQuestion=findViewById(R.id.lQuestionTextView);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        progressBar=findViewById(R.id.progressBar);
        editText=findViewById(R.id.editText);
        //locationQuestion.setText(locArray[locIndex].getQuestionID());
        round.setText("Round " + currentRound);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        locationQuestion.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);
        question.setText(R.string.question31);
        r1.setText(R.string.answer311);
        r2.setText(R.string.answer312);
        r3.setText(R.string.answer313);
        r4.setText(R.string.answer314);

        Typeface customFont=Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        Typeface customFont2=Typeface.createFromAsset(getAssets(),"BebasNeuR.ttf");

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

        SimpleDateFormat startTimeObj=new SimpleDateFormat();
        String startTimeString=startTimeObj.format(new Date());
        startTime.setValue(startTimeString);
        timeFinished.setValue(false);
        time1=System.currentTimeMillis();

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

    public void nextQuestion(boolean update)
    {
        question=findViewById(R.id.questionTextView);
        if(update==true)
        {
            index=(index+1) %5;
            question.setText(array[index].getQuestionId());
            r1.setText(array[index].getAnswer1Id());
            r2.setText(array[index].getAnswer2Id());
            r3.setText(array[index].getAnswer3Id());
            r4.setText(array[index].getAnswer4Id());
        }
        else
        {
            question.setText(array[index].getQuestionId());
        }
    }



    public void onSubmit(View view)
    {
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
        int div=array.length+locArray.length;
        increment=(int)Math.ceil(100/div);
        char correctAnswer=array[index].getAnswer();
        if(correctAnswer==userAnswer)
        {
            Toast.makeText(this,"That's Correct",Toast.LENGTH_SHORT).show();
            if(currentScore==0)
            {
                nextLocQuestion(false);
            }
            else
            {
                nextLocQuestion(true);
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
            nextLocQuestion(false);
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

    public void onSubmit2(View view) {
        String userAns = editText.getText().toString();
        if (userAns.isEmpty()) {
            Toast.makeText(this,"Can't be empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            int userAnswer = Integer.parseInt(userAns);
            checkLocAnswer(userAnswer);
        }
    }

    void checkLocAnswer(int userAnswer)
    {
        int div=array.length+locArray.length;
        increment=(int)Math.ceil(100/div);
        int correctAnswer=locArray[locIndex].getAnswer();
        round=findViewById(R.id.roundTextView);
        score=findViewById(R.id.scoreTextView);
        if(correctAnswer==userAnswer)
        {
            progressBar.incrementProgressBy(div);
            locationQuestion.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);


            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);


            nextQuestion(true);
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
                alertDialog.setMessage("Show this message to the AB-5 MCA Lab.");
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
            nextQuestion(false);
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

    void nextLocQuestion(boolean update)
    {

        locationQuestion=findViewById(R.id.lQuestionTextView);
        if(update==true)
        {
            locIndex=(locIndex+1) % 5;
            locationQuestion.setText(locArray[locIndex].getQuestionID());
        }
        else
        {
            locationQuestion.setText(locArray[locIndex].getQuestionID());
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
