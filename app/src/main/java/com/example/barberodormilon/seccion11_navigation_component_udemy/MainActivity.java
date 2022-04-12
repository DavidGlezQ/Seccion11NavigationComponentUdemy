package com.example.barberodormilon.seccion11_navigation_component_udemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            toolbar.setTitle(navDestination.getLabel());
            toolbar.setNavigationIcon(null);
        });

        ConstraintLayout bottomSheet = findViewById(R.id.bottom_sheet);
        ImageButton btnClose = findViewById(R.id.btnClose);
        MaterialButton btnExit = findViewById(R.id.btnExit);

        btnClose.setOnClickListener(view -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
        btnExit.setOnClickListener(view -> finish());

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if (item.getItemId() == R.id.action_confirmation){
            navController.navigate(R.id.action_global_conformationFragment);
        }
        return super.onOptionsItemSelected(item);*/
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        /*new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.bottom_sheet_tittle)
                .setPositiveButton(R.string.bottom_sheet_exit, (dialogInterface, i) -> finish())
                .setNegativeButton("Cancelar", null).show();*/

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}