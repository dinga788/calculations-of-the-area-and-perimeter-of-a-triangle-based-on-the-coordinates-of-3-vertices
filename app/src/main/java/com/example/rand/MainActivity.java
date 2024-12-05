package com.example.rand;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textViewCoordinates;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textViewCoordinates = findViewById(R.id.textView);
        textViewResults = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomCoordinates();
            }
        });
    }

    private void generateRandomCoordinates() {
        Random random = new Random();

        int x1 = random.nextInt(10) + 1;
        int y1 = random.nextInt(10) + 1;
        int x2 = random.nextInt(10) + 1;
        int y2 = random.nextInt(10) + 1;
        int x3 = random.nextInt(10) + 1;
        int y3 = random.nextInt(10) + 1;

        String coordinates = "Точка A: (" + x1 + ", " + y1 + ")\n";
        coordinates += "Точка B: (" + x2 + ", " + y2 + ")\n";
        coordinates += "Точка C: (" + x3 + ", " + y3 + ")";

        textViewCoordinates.setText(coordinates);

        calculateTriangleParameters(x1, y1, x2, y2, x3, y3);
    }

    private void calculateTriangleParameters(int x1, int y1, int x2, int y2, int x3, int y3) {
        double sideA = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double sideB = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double sideC = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        if ((sideA < sideB + sideC) && (sideB < sideA + sideC) && (sideC < sideA + sideB)) {

            double perimeter = sideA + sideB + sideC;
            double semiPerimeter = perimeter / 2;
            double area = Math.sqrt(semiPerimeter * (semiPerimeter - sideA) *
                    (semiPerimeter - sideB) * (semiPerimeter - sideC));

            String results = "Длины сторон:\n";
            results += "AB: " + String.format("%.2f", sideA) + "\n";
            results += "BC: " + String.format("%.2f", sideB) + "\n";
            results += "CA: " + String.format("%.2f", sideC) + "\n\n";
            results += "Периметр: " + String.format("%.2f", perimeter) + "\n";
            results += "Площадь: " + String.format("%.2f", area);

            textViewResults.setText(results);
        } else {
            textViewResults.setText("Треугольник с такими вершинами не существует.");
        }
    }
}