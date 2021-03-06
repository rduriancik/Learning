package com.example.robert.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    private float percentage = 0;
    private float tipTotal = 0;
    private float finalBillAmount = 0;
    private float totalBillAmount = 0;

    private static final float REGULAR_TIP_PERCENTAGE = 10;
    private static final float DEFAULT_TIP_PERCENTAGE = 15;
    private static final float EXCELLENT_TIP_PERCENTAGE = 20;

    public static final String KEY_PERCENTAGE = "percentage";
    public static final String KEY_TIP_TOTAL = "tip_total";
    public static final String KEY_FINAL_BILL_AMOUNT = "final_bill_amount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            percentage = savedInstanceState.getFloat(KEY_PERCENTAGE);
            tipTotal = savedInstanceState.getFloat(KEY_TIP_TOTAL);
            finalBillAmount = savedInstanceState.getFloat(KEY_FINAL_BILL_AMOUNT);
        }

        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if (percentage == 0) {
            percentage = DEFAULT_TIP_PERCENTAGE;
        }

        if (!etBillAmount.getText().toString().isEmpty() && !etBillAmount.getText().toString().equals(".")) {
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        } else {
            totalBillAmount = 0;
        }

        tipTotal = (totalBillAmount * percentage) / 100;
        finalBillAmount = totalBillAmount + tipTotal;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(KEY_PERCENTAGE, percentage);
        outState.putFloat(KEY_TIP_TOTAL, tipTotal);
        outState.putFloat(KEY_FINAL_BILL_AMOUNT, finalBillAmount);
        super.onSaveInstanceState(outState);
    }
}
