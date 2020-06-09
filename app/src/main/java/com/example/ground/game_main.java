package com.example.ground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//오락메인
public class game_main extends AppCompatActivity implements View.OnClickListener {

    Button go_other_menu, btn_gugu;
    Button cancel;
    Button top_navi, btn_setting;
    int correct =0;
    int total = 0;

    int value = 101; //시작 101로 하기 test니까 10


    public final String bringTimerThread() {

        CountDownTimer cdt = new CountDownTimer(100 * 1000, 1000) { //여기도 나중에 10 -> 100
            //100(10*1000)초 동안 1초마다 실행
            @Override
            public void onTick(long millisUntilFinished) {
                value--;
                multiplicationView.popupCountTextView().setText(value + "초");
            }

            @Override
            public void onFinish() {
                Toast.makeText(game_main.this, "시간 끝- \n 최종 점수 : "+ total + "점", Toast.LENGTH_LONG).show();//시간 끝났을때 토스트 메시지뜨는거 확인
                Log.d("TEST1234", "최종 "+total);
                value = 101; // 이거없으면 다시 구구단 게임 눌렀을때 -1으로 시작해서 설정한 시간만큼 줄어듦. 넣어줘야함
                correct = 0;//스레드끝나면 값들 초기화해주기
                total = 0;
                multiplicationView.dismiss();

            }
        };
        cdt.start();


        return "타이머 쓰레드 여기다가 생성";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);


        go_other_menu = findViewById(R.id.go_game_ranking);
        go_other_menu.setOnClickListener(this);

        btn_gugu = findViewById(R.id.btn_gugu);
        btn_gugu.setOnClickListener(this);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);


    }


    public void onClick(View v) {
        if (v.getId() == R.id.go_game_ranking) {
            Intent intent01 = new Intent(game_main.this, game_ranking.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.btn_school_information_cancel) { //???????????이거 어디일까..

//            finish();
        }
        if (v.getId() == R.id.btn_gugu) {
            //팝업뷰 생성
            setEntertainmentFunction();
            Log.d("TEST1234", "구구단 게임 선택 팝업 ");
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(game_main.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(game_main.this, configActivity.class);
            startActivity(intent6);
        }
    }

    // 오락기능 popupview show()
    private MultiplicationView multiplicationView;
    private int firstNumber;
    private int secondNumber;


    private final void setEntertainmentFunction() {
        ((Button) findViewById(R.id.btn_gugu)).setOnClickListener(
                (View.OnClickListener) (new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {// 오락버튼 눌렀을 때
                        multiplicationView = new MultiplicationView(); // 객체 삽입

                        multiplicationView.getContext((Context) game_main.this); //콘텍스트 받아오기 (부모 클래스로부터)
                        multiplicationView.getPlace((View) findViewById(R.id.btn_gugu));// 어느 뷰를 기점으로 popupview를 생성?
                        multiplicationView.showPopupView(); // popupview 생성


                        multiplicationView.popupCountTextView().setText(bringTimerThread());
                        multiplicationView.popupTextView().setText(multiplicationQuestion());
                        multiplicationView.popupEdittext().addTextChangedListener((TextWatcher) (new TextWatcher(){
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count ) {
                                try {
                                    String answer = multiplicationView.popupEdittext().getText().toString();
                                    int intConverter = Integer.parseInt(answer);

                                    if (firstNumber * secondNumber == intConverter) {

                                        correct++;
                                        total =+correct;
                                        Toast.makeText(game_main.this, "정답! ", Toast.LENGTH_SHORT).show();
                                        Log.d("TEST1234", "정답 개수: " + correct);


                                        multiplicationView.popupEdittext().setText("");
                                        multiplicationView.popupTextView().setText(multiplicationQuestion());


                                    } else {
                                        //Toast.makeText(game_main.this, "오답!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {

                                }




                              /*  try {
                                    String answer = multiplicationView.popupEdittext().getText().toString();
                                    //String answer = String.valueOf(s);
                                    int intConverter = Integer.parseInt(answer);
                                    if (firstNumber * secondNumber == intConverter) {



                                        correct++;



                                      //  multiplicationView.dismiss(); //정답이 일치할경우 mulirplicationView에 dismiss 클래스 실행 -> 팝업뷰가 사라짐 로그 뜨면서 팝업창 사라짐
                                        Toast.makeText(game_main.this, "정답!", Toast.LENGTH_SHORT).show();
                                    } else { //정답이 틀릴경우?
                                        Toast.makeText(game_main.this, "오답..\n 다시 시도해 보세요", Toast.LENGTH_SHORT).show(); //오답일경우 오답.. 이라는 토스트 메시지 출력
                                    *//*
                                    TODO :
                                     1. 백버튼 눌릴때 invalidation 에러 발생가능
                                     2. UI디자인 개선

                                     *//*
                                    }
                                } catch (NumberFormatException e) {

                                } catch (Exception e) {

                                }*/
                            }
                        }));

                        multiplicationView.popupExistButton().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(game_main.this, "구구단게임이 종료되었습니다..\n정답수 : "+total, Toast.LENGTH_LONG).show();
                                Log.d("TEST1234", "최종 "+total);
                                multiplicationView.dismiss();
                            }
                        }));
                        multiplicationView.popupNextButton().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
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

    public final int rend(int from, int to) {
        return this.random.nextInt(to - from) + from;
    }

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
    private void memoryinitialisation() {
        multiplicationView = null;
        firstNumber = 0;
        secondNumber = 0;
    }


}
