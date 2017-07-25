package com.example.yangxiaoyu.calculator_y;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Button D1;
    private Button D2;
    private Button D3;
    private Button D4;
    private Button D5;
    private Button D6;
    private Button D7;
    private Button D8;
    private Button D9;
    private Button D10;
    private Button D11;
    private Button D12;
    private Button D13;
    private Button D14;
    private Button D15;
    private Button D16;
    private Button D17;
    private EditText showData;
    List datalist = new ArrayList<Integer>();
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showData = (EditText)findViewById(R.id.showData);
        D1 = (Button)findViewById(R.id.D1);
        D2 = (Button)findViewById(R.id.D2);
        D3 = (Button)findViewById(R.id.D3);
        D4 = (Button)findViewById(R.id.D4);
        D5 = (Button)findViewById(R.id.D5);
        D6 = (Button)findViewById(R.id.D6);
        D7 = (Button)findViewById(R.id.D7);
        D8 = (Button)findViewById(R.id.D8);
        D9 = (Button)findViewById(R.id.D9);
        D10 = (Button)findViewById(R.id.D10);
        D11 = (Button)findViewById(R.id.D11);
        D12 = (Button)findViewById(R.id.D12);
        D13 = (Button)findViewById(R.id.D13);
        D14 = (Button)findViewById(R.id.D14);
        D15 = (Button)findViewById(R.id.D15);
        D16 = (Button)findViewById(R.id.D16);
        D17 = (Button)findViewById(R.id.D17);


        D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("1");
            }
        });
        D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("2");
            }
        });
        D3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("3");
            }
        });
        D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("4");
            }
        });
        D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("5");
            }
        });
        D6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("6");
            }
        });
        D7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("7");
            }
        });
        D8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("8");
            }
        });
        D9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("9");
            }
        });
        D10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append(".");
            }
        });
        D11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("0");
            }
        });


        D12.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           if(showData.getText().toString().indexOf("+")>-1
                   || showData.getText().toString().indexOf("-") > -1
                   || showData.getText().toString().indexOf("*") > -1
                   || showData.getText().toString().indexOf("/")>-1 ){
              // Log.d(TAG, "onClick: "+showData.getText().toString().indexOf("+"));
               String  [] data = showData.getText().toString().split("[\\+|-|*|/|.]");
               if(showData.getText().toString().equals(data[0]+"+"+data[1])){
                   showData.setText(jia(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
                   Log.d(TAG, "onClick: 加法操作成功");
               }
               if(showData.getText().toString().equals(data[0]+"-"+data[1])){
                   showData.setText(jian(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
                   Log.d(TAG, "onClick: 减法操作成功");
               }
               if(showData.getText().toString().equals(data[0]+"*"+data[1])){
                   showData.setText(cheng(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
                   Log.d(TAG, "onClick: 乘法操作成功");
               }
               if(showData.getText().toString().equals(data[0]+"/"+data[1])){
                   showData.setText(chu(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
                   Log.d(TAG, "onClick: 除法操作成功");
               }
           }else {
               Toast.makeText(getApplicationContext(),"输入有误，请重新输入",Toast.LENGTH_SHORT).show();
           }


        }
            private String  chu(int a, int b) {
                int c = a/b;
                return (Integer.toString(c));
            }

            private String cheng(int a, int b) {
                int c = a*b;
                return (Integer.toString(c));
            }

            private String  jian(int a, int b) {
                int c = a-b;
                return (Integer.toString(c));
            }

            private String  jia(double a,double b) {
                double c = a + b;
                return (Double.toString(c));

            }
        });

        D13.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showData.getText().clear();
        }
    });
        D14.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showData.append("/");
        }
    });
        D15.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showData.append("*");
        }
    });
        D16.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showData.append("-");
        }
    });
        D17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData.append("+");
            }
        });


    }
}
