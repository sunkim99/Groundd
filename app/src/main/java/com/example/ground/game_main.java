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
import android.widget.Toast;

import java.util.Random;

//오락메인
public class game_main extends AppCompatActivity implements View.OnClickListener {

    Button go_other_menu, btn_gugu;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        go_other_menu = (Button) findViewById(R.id.go_game_ranking);

        btn_gugu = findViewById(R.id.btn_gugu);

        go_other_menu.setOnClickListener(this);

        btn_gugu.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.go_game_ranking) {
            Intent intent01 = new Intent(game_main.this, game_ranking.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
        if (v.getId() == R.id.btn_gugu) {
            //팝업뷰 생성
            setEntertainmentFunction();
            Log.d("TEST1234", "구구단 게임 선택 팝업 ");
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


                        Log.d("TEST1234", "스레드 진행1" + Thread.currentThread());
                        multiplicationView.popupTextView().setText(multiplicationQuestion());
                        multiplicationView.popupEdittext().addTextChangedListener((TextWatcher) (new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                try {
                                    String answer = String.valueOf(s);
                                    int intConverter = Integer.parseInt(answer);
                                    if (firstNumber * secondNumber == intConverter) {
                                        multiplicationView.dismiss(); //정답이 일치할경우 mulirplicationView에 dismiss 클래스 실행 -> 팝업뷰가 사라짐 로그 뜨면서 팝업창 사라짐
                                        Toast.makeText(game_main.this, "정답!", Toast.LENGTH_SHORT).show();
                                    } else { //정답이 틀릴경우?
                                        Toast.makeText(game_main.this, "오답..", Toast.LENGTH_SHORT).show(); //오답일경우 오답.. 이라는 토스트 메시지 출력
                                    /*
                                    TODO :
                                     1. 백버튼 눌릴때 invalidation 에러 발생가능
                                     2. UI디자인 개선

                                     */
                                    }
                                } catch (NumberFormatException e) {

                                } catch (Exception e) {

                                }
                            }
                        }));

                        multiplicationView.popupExistButton().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
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
