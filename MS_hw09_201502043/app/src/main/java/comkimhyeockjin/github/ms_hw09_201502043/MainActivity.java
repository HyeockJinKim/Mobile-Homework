package comkimhyeockjin.github.ms_hw09_201502043;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SquareView(this));
    }

    class SquareView extends View {
        private final Paint square;
        private final int size = 143;
        private int coordinateX = 43;
        private int coordinateY = 43; // 학번 : 201502043에서 참고.
        SquareView(Context context) {
            super(context);
            square = new Paint();
            square.setColor(Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(coordinateX, coordinateY, coordinateX+size, coordinateY+size, square);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    coordinateX = (int) event.getX() - size / 2;
                    coordinateY = (int) event.getY() - size / 2;
                    invalidate();
                    return true;
                case MotionEvent.ACTION_UP:
                    coordinateX = (int) event.getX() - size / 2;
                    coordinateY = (int) event.getY() - size / 2;
                    invalidate();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    coordinateX = (int) event.getX() - size / 2;
                    coordinateY = (int) event.getY() - size / 2;
                    invalidate();
                    return true;
            }
            return false;
        }
    }
}
