package ru.startandroid.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;

import ru.startandroid.simplecalculator.exception.BracketsMissmatchException;
import ru.startandroid.simplecalculator.exception.DivideByZeroException;
import ru.startandroid.simplecalculator.exception.InvalidPowOfNum;
import ru.startandroid.simplecalculator.exception.UnsupportedSymbolsInMathExpression;
import ru.startandroid.simplecalculator.math.MathCalcParser;
import ru.startandroid.simplecalculator.valid.Validator;

public class MainActivity extends Activity implements
        AdapterView.OnItemSelectedListener, OnClickListener {

    private static final String defaultExpression = "Выражение: ";
    private static final String defaultResult = "Результат: ";
    private GridView gvMain;
    private DataAdapter mAdapter;
    private TextView mSelectText;
    private TextView mResultText;
    private Button mResultBtn;
    private Button mClearBtn;
    private Button mClearLast;
    private StringBuilder mathExpression = new StringBuilder("");
    private String resultExpression  = "";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mSelectText = (TextView) findViewById(R.id.info);
        mResultText = (TextView) findViewById(R.id.tvResult);
        mResultBtn = (Button) findViewById(R.id.btnResult);
        mClearBtn = (Button) findViewById(R.id.btnClear);
        mClearLast = (Button) findViewById(R.id.btnClearLast);
        mAdapter = new DataAdapter(this, R.layout.item, R.id.tvText);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(mAdapter);
        gvMain.setOnItemSelectedListener(this);
        gvMain.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // TODO Auto-generated method stub
                mathExpression.append(mAdapter.getItem(position));
                mSelectText.setText(defaultExpression
                        + mathExpression.toString());

            }
        });

        mResultBtn.setOnClickListener(this);
        mClearBtn.setOnClickListener(this);
        mClearLast.setOnClickListener(this);
        if (savedInstanceState != null) {
            mathExpression = new StringBuilder(savedInstanceState.getString("saveExpression"));
            resultExpression = savedInstanceState.getString("saveResult");
        }
        mSelectText.setText(defaultExpression + mathExpression);
        mResultText.setText(defaultResult + resultExpression);
        adjustGridView();
    }
    private void adjustGridView() {
        gvMain.setNumColumns(6);
        gvMain.setColumnWidth(120);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

        mSelectText.setText(defaultExpression + mathExpression);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSelectText.setText(defaultExpression);
        mResultText.setText(defaultResult);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSelectText.setText(defaultExpression + savedInstanceState.getString("saveExpression"));
        mResultText.setText(defaultResult + savedInstanceState.getString("saveResult"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saveResult", resultExpression);
        outState.putString("saveExpression", mathExpression.toString());
    }

    @Override
    public void onClick(View view) {
        Validator val = new Validator();
        switch (view.getId()) {
            case R.id.btnResult:
                try {
                    if (val.isMathExpressionValid(mathExpression.toString())) {
                        MathCalcParser parser = new MathCalcParser();
                        resultExpression = parser.parseAndCalcByReversePolishNotation
                                (mathExpression.toString()).toString();
                        mResultText.setText(defaultResult + resultExpression);
                    }
                } catch (UnsupportedSymbolsInMathExpression | BracketsMissmatchException |
                        DivideByZeroException | InvalidPowOfNum e) {
                    showToast(e.getMessage());
                }

                break;
            case(R.id.btnClear):
                mathExpression = new StringBuilder("");
                mResultText.setText(defaultResult);
                mSelectText.setText(defaultExpression);
                break;
            case(R.id.btnClearLast):
                mathExpression.deleteCharAt(mathExpression.length() - 1);
                mSelectText.setText(defaultExpression + mathExpression);
        }
    }
    private void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}