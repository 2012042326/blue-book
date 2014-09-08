package quizapp.example.com.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import quizapp.example.com.quizapp.R;

public class QuestionActivity extends ActionBarActivity {
   // public
    public static int quesno=1,clicked_btn=0,score=0;
    public static String str[][] = new String[5][5];
    public static String cor_ans="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        quesno=1;
        score=0;
        str[0][0]="What letter comes after 'a'?";
        str[0][1]="b";
        str[0][2]="c";
        str[0][3]="d";
        str[0][4]="None of the above";
        str[1][0]="Solve the problem: (1*3/4+2)-(6-(5+2-3))";
        str[1][1]=".75";
        str[1][2]="-1.5";
        str[1][3]="undefined";
        str[1][4]="does not exist";
        str[2][0]="If it rained the floor is wet. The floor is wet because: ";
        str[2][1]="It rained.";
        str[2][2]="I mopped.";
        str[2][3]="I spilled water.";
        str[2][4]="All of the above";
        str[3][0]="How many wheels does a car have?";
        str[3][1]="4";
        str[3][2]="3 because someone robbed 1";
        str[3][3]="2 because I lost 2 wheels";
        str[3][4]="I have no car";
        str[4][0]="Fill in the blanks: This android application _________: ";
        str[4][1]="is #1 in the class";
        str[4][2]="is awesome";
        str[4][3]="deserves a perfect score";
        str[4][4]="All of the above";


    }
    public void clickedA(View view){
        clicked_btn=1;
        quesSeq();

    }
    public void clickedB(View view){
        clicked_btn=2;
        quesSeq();

    }
    public void clickedC(View view){
        clicked_btn=3;
        quesSeq();

    }
    public void clickedD(View view){
        clicked_btn=4;
        quesSeq();

    }

    public void setCorAns(){
        if(quesno==5){cor_ans = str[quesno-1][4];}
        else{
        cor_ans = str[quesno-1][1];}
    }

    public void quesSeq(){
        if(quesno>1&&quesno-1<5){
            System.out.println("check"+quesno);
            checker();
        }
        if(quesno>0&&quesno-1<5){
            System.out.println("setcor"+quesno);
            setCorAns();}


        startNewQuestion();

    }

    public void checker(){
        String cur_ans=str[quesno-2][clicked_btn];
        Context context = getApplicationContext();
        CharSequence text="";
        if(cur_ans.compareTo(cor_ans)==0){

            score++;
            System.out.println(quesno+" points>"+score);
            text = "Correct";

        }
        else{
            text = "Wrong. The correct answer is "+cor_ans;

        }
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static void shuffle(String str[][], int a, int b){

        if(a==b){}

        else{
            String temp;
            int x = (int) (Math.random() * (a)) + 1;

            temp = str[quesno-1][a];
            str[quesno-1][a]= str[quesno-1][x];
            str[quesno-1][x]=temp;
            shuffle(str,a+1,b);
        }
    }

    public void startNewQuestion() {

        try{Thread.sleep(500);}
        catch(InterruptedException ie){}
        if(quesno<=5) {

            if(str[quesno-1][4].compareTo("None of the above")==0 ||str[quesno-1][4].compareTo("All of the above")==0) {
                System.out.println("yes!!" + quesno);
                for (int j=0;j<3;j++) {
                    shuffle(str, 1, 3);
                }
            }
            else{
                System.out.println("no!!"+quesno);
                for (int j=0;j<3;j++) {
                shuffle(str,1,4);}

            }
            TextView btna= (TextView)findViewById(R.id.a_tv);
            btna.setText("\t"+str[quesno-1][1]);
            TextView btnb= (TextView)findViewById(R.id.b_tv);
            btnb.setText("\t"+str[quesno-1][2]);
            TextView btnc= (TextView)findViewById(R.id.c_tv);
            btnc.setText("\t"+str[quesno-1][3]);
            TextView btnd= (TextView)findViewById(R.id.d_tv);
            btnd.setText("\t"+str[quesno-1][4]);
            TextView quesTV = (TextView)findViewById(R.id.ques_tv);
            quesTV.setText(str[quesno-1][0]);
            //quesTV.setText(str[quesno-1]);
            TextView quesnoTV = (TextView)findViewById(R.id.quesno_tv);
            quesnoTV.setText("#"+quesno);
            quesno++;
            clicked_btn=0;
        }
    else{
            checker();
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score_val",score);
            startActivity(intent);


        }

    }




    @Override
    public void onBackPressed()
    {
        //super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_question, container, false);
            return rootView;
        }
    }
}
