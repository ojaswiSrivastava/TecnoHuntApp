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

public class Team5Quiz extends AppCompatActivity {

    QuestionAnswer[] array=new QuestionAnswer[]{
            new QuestionAnswer(R.string.question51,'d',R.string.answer511,R.string.answer512,R.string.answer513,R.string.answer514),
            new QuestionAnswer(R.string.question52,'b',R.string.answer521,R.string.answer522,R.string.answer523,R.string.answer524),
            new QuestionAnswer(R.string.question53,'d',R.string.answer531,R.string.answer532,R.string.answer533,R.string.answer534),
            new QuestionAnswer(R.string.question54,'b',R.string.answer541,R.string.answer542,R.string.answer543,R.string.answer544),
            new QuestionAnswer(R.string.question55,'a',R.string.answer551,R.string.answer552,R.string.answer553,R.string.answer554)
    };
    LocQuestionAnswer[] locArray=new LocQuestionAnswer[]
            {
                    new LocQuestionAnswer(R.string.questionL51,8932),
                    new LocQuestionAnswer(R.string.questionL52,2485),
                    new LocQuestionAnswer(R.string.questionL53,5253),
                    new LocQuestionAnswer(R.string.questionL54,5195),
                    new LocQuestionAnswer(R.string.questionL55,6124)
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
    DatabaseReference child=root.child("Team5");
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
        question.setText(R.string.question51);
        r1.setText(R.string.answer511);
        r2.setText(R.string.answer512);
        r3.setText(R.string.answer513);
        r4.setText(R.string.answer514);

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

        SimpleDateFormat startTimeObj = new SimpleDateFormat();
        String startTimeString = startTimeObj.format(new Date());
        startTime.setValue(startTimeString);
        timeFinished.setValue(false);
        time1 = System.currentTimeMillis();

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
            score.setText("Score- " + currentScore + "/" + "10");
            scoreUpdate.setValue(currentScore);
            time2=System.currentTimeMillis();
            time3=(time2-time1);
            currentTime.setValue(time3/1000);

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
