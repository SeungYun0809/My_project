package kr.ac.cnu.computer.myapplication;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button button;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentDiagonsis fragmentDiagonsis = new FragmentDiagonsis();
    private FragmentAlarm fragmentAlarm = new FragmentAlarm();
    private FragmentMyPage fragmentMyPage = new FragmentMyPage();
    private FragmentMain fragmentMain = new FragmentMain();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//네비게이션바 설정하기
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.invisible);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
//다음
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
//네비게이션 바 클릭시 레이아웃 옮겨지는 부분
    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.invisible:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.Diagnosis:
                    transaction.replace(R.id.frameLayout, fragmentDiagonsis).commitAllowingStateLoss();
                    break;
                case R.id.Alarm:
                    transaction.replace(R.id.frameLayout, fragmentAlarm).commitAllowingStateLoss();
                    break;
                case R.id.MyPage:
                    transaction.replace(R.id.frameLayout, fragmentMyPage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
//끝
}
