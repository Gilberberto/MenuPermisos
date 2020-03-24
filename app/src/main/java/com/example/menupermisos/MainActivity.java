package com.example.menupermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private int switch_code;
    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;
    Switch switch5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);

        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        switch3.setOnCheckedChangeListener(this);
        switch4.setOnCheckedChangeListener(this);
        switch5.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch1:
                if (isChecked) {
                    switch_code = 1;
                    permissions(Manifest.permission.BLUETOOTH, 1);
                }
                break;
            case R.id.switch2:
                if (isChecked) {
                    switch_code = 2;
                    permissions(Manifest.permission.BODY_SENSORS, 2);
                }
                break;
            case R.id.switch3:
                if (isChecked) {
                    switch_code = 3;
                    permissions(Manifest.permission.READ_EXTERNAL_STORAGE, 3);
                }
                break;
            case R.id.switch4:
                if (isChecked) {
                    switch_code = 4;
                    permissions(Manifest.permission.CAMERA, 4);
                }
                break;
            case R.id.switch5:
                if (isChecked) {
                    switch_code = 5;
                    permissions(Manifest.permission.DELETE_CACHE_FILES, 5);
                }
                break;
            default:
                break;
        }
    }

    private void permissions(String permission, int switch_code) {
        if (ContextCompat.checkSelfPermission(getBaseContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "Este permiso ya fue concedido", Toast.LENGTH_SHORT).show();
        } else {
            requestStoragePermission(permission, switch_code);
        }
    }

    private void requestStoragePermission(final String permission, final int switch_code) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Permiso Necesario")
                    .setMessage("Necesitas este permiso para que la App funcione correctamente.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, switch_code);
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    togglesw(switch_code);
                    dialog.dismiss();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, switch_code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == switch_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso consedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso no concedido", Toast.LENGTH_SHORT).show();
                togglesw(requestCode);
            }
        }
    }

    private void togglesw(int requestCode){
        switch (requestCode) {
            case 1:
                switch1.toggle();
                break;
            case 2:
                switch2.toggle();
                break;
            case 3:
                switch3.toggle();
                break;
            case 4:
                switch4.toggle();
                break;
            case 5:
                switch5.toggle();
                break;
            default:
                break;
        }
    };
}
