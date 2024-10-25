package com.example.baitap5sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAmount, etReason;
    private Button btnSave, btnReset;
    private TextView tvExpenses;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etAmount = findViewById(R.id.etAmount);
        etReason = findViewById(R.id.etReason);
        btnSave = findViewById(R.id.btnSave);
        btnReset = findViewById(R.id.btnReset);
        tvExpenses = findViewById(R.id.tvExpenses);

        sharedPreferences = getSharedPreferences("ExpensePrefs", MODE_PRIVATE);

        loadExpenses();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpense();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetHistory();
            }
        });
    }

    private void saveExpense() {
        String name = etName.getText().toString();
        String amount = etAmount.getText().toString();
        String reason = etReason.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String currentHistory = sharedPreferences.getString("history", "");
        String newExpense = "Họ tên: " + name + ", Số tiền: " + amount + ", Lý do: " + reason + "\n";
        editor.putString("history", currentHistory + newExpense);
        editor.apply();

        loadExpenses();
    }

    private void loadExpenses() {
        String history = sharedPreferences.getString("history", "Không có dữ liệu");
        tvExpenses.setText(history);
    }

    private void resetHistory() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("history");
        editor.apply();

        loadExpenses();
    }
}