package co.io.headytest.view.main;

import android.os.Bundle;

import co.io.headytest.R;
import co.io.headytest.base.BaseActivity;
import co.io.headytest.view.category.CategoryFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) { //if it's not rotated
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screenContainer, new CategoryFragment()).commit();
        }

    }
}
