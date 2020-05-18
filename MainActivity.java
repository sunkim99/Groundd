package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/*봉근오빠 이메세지보이시면 밑에 똑같이 답장한번 달아서 깃 올려주세욤*/
/* ㄴㄴㄴㄴㄴ */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_school, btn_board, btn_character, btn_event, btn_game, btn_setting;
    TextView et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_school = (Button) findViewById(R.id.btn_school);
        btn_board = findViewById(R.id.btn_board);
        btn_character = findViewById(R.id.btn_character);
        btn_event =  findViewById(R.id.btn_event);
        btn_game = findViewById(R.id.btn_game);
        btn_setting = findViewById(R.id.btn_setting);

        btn_school.setOnClickListener(this);
        btn_board.setOnClickListener(this);
        btn_character.setOnClickListener(this);
        btn_event.setOnClickListener(this);
        btn_game.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        Intent intent2 = getIntent();
        String userID1 = intent2.getStringExtra("userID");
        et = findViewById(R.id.editText11);
        et.setText(userID1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_school) { //학교 게시판
            Intent intent1 = new Intent(MainActivity.this, school_information.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_board) { // 게시판
            Intent intent2 = new Intent(MainActivity.this, forum_forum.class);
            startActivity(intent2);
        }
        if (v.getId() == R.id.btn_character) { // 내 캐릭터
            Intent intent3 = new Intent(MainActivity.this, char_inven.class);
            startActivity(intent3);
        }
        if (v.getId() == R.id.btn_event) { // 공지 이벤트
            Intent intent4 = new Intent(MainActivity.this, notice_notice.class);
            startActivity(intent4);
        }
        if (v.getId() == R.id.btn_game) { // 게임
            //팝업뷰 생성
            setEntertainmentFunction();
            Log.d("test123", "클릭됨");
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            String userID= et.getText().toString();
            Intent intent6 = new Intent(MainActivity.this, configActivity.class);

            intent6.putExtra("userID", userID);
            startActivity(intent6);
        }
    }

    // 오락기능 popupview show()
    private MultiplicationView multiplicationView;
    private int firstNumber;
    private int secondNumber;
    private final void setEntertainmentFunction(){
        ((Button)findViewById(R.id.btn_game)).setOnClickListener(
                (View.OnClickListener)(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {// 오락버튼 눌렀을 때
                        multiplicationView = new MultiplicationView(); // 객체 삽입

                        multiplicationView.getContext((Context)MainActivity.this); //콘텍스트 받아오기 (부모 클래스로부터)
                        multiplicationView.getPlace((View)findViewById(R.id.btn_game));// 어느 뷰를 기점으로 popupview를 생성?
                        multiplicationView.showPopupView(); // popupview 생성

                        multiplicationView.popupTextView().setText(multiplicationQuestion());
                        multiplicationView.popupEdittext().addTextChangedListener((TextWatcher)(new TextWatcher(){
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                            @Override
                            public void afterTextChanged(Editable s) { }
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                try{
                                    String answer = String.valueOf(s);
                                    int intConverter = Integer.parseInt(answer);
                                    if (firstNumber * secondNumber == intConverter){
                                        multiplicationView.dismiss();
                                        Toast.makeText(MainActivity.this, "정답!",Toast.LENGTH_SHORT).show();

                                    }else {


                                        class Stopwatch {
                                            private int interval;
                                            private Timer timer;

                                            public void main(String[] args) {
                                                Scanner sc = new Scanner(System.in);
                                                System.out.print("Input seconds => : ");
                                                String secs = sc.nextLine();
                                                int delay = 1000;
                                                int period = 1000;
                                                timer = new Timer();
                                                interval = Integer.parseInt(secs);
                                                System.out.println(secs);
                                                timer.scheduleAtFixedRate(new TimerTask() {

                                                    public void run() {
                                                        System.out.println(setInterval());

                                                    }
                                                }, delay, period);
                                            }

                                            private final int setInterval() {
                                                if (interval == 1)
                                                    timer.cancel();
                                                return --interval;
                                            }
                                        }
                                    /*
                                    TODO :
                                     1. 백버튼 눌릴때 invalidation 에러 발생가능
                                     2. UI디자인 개선

                                     */
                                    }
                                }catch (NumberFormatException e){

                                }catch (Exception e){

                                }
                            }
                        }));

                        multiplicationView.popupExistButton().setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                multiplicationView.dismiss();
                            }
                        }));
                        multiplicationView.popupNextButton().setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                multiplicationView.dismiss();
                            }
                        }));
                    }
                })
        );
    }

    private final Random random = new Random();
    public final int rend(int from, int to) { return this.random.nextInt(to - from) + from; }
    private final String multiplicationQuestion() {
        this.firstNumber = this.rend(1, 10);
        this.secondNumber = this.rend(1, 10);
        return this.firstNumber + " x " + this.secondNumber + " = ?";
    }

    // --------------------------------- 액티비티 종료시 -----------------//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoryinitialisation();
    }

    // 메모리 해제
    private void memoryinitialisation(){
        multiplicationView = null;
        firstNumber = 0;
        secondNumber = 0;
    }

}
