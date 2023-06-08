package com.example.projetofinalpw3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projetofinalpw3.databinding.ActivityMainBinding;
import com.example.projetofinalpw3.dto.TipoUsuarioDTO;
import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //implements NavigationView.OnNavigationItemSelectedListener
    private ActivityResultRegistry mRegistry;
    private ActivityResultLauncher<String> mGetContent;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private View view;

    private TokenDTO token = new TokenDTO("");

    private String email = "";

    private TipoUsuarioDTO tipoUsuario = new TipoUsuarioDTO("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        Intent intent = getIntent();
        token.setToken(intent.getStringExtra("token"));
        tipoUsuario.setTipo(intent.getStringExtra("tipoUsuario"));
        email = intent.getStringExtra("email");

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        view = this.view;
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        Menu menu = navigationView.getMenu();
        MenuItem administrarUsuariosMenuItem1 = menu.findItem(R.id.nav_lista_usuario_leitor_fragment);
        administrarUsuariosMenuItem1.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("RESPONSAVEL"));

        MenuItem administrarUsuariosMenuItem2 = menu.findItem(R.id.nav_fragment_cadastro_usuario_leitor);
        administrarUsuariosMenuItem2.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("RESPONSAVEL"));

        MenuItem administrarUsuariosMenuItem3 = menu.findItem(R.id.nav_fragment_historia_social_cad);
        administrarUsuariosMenuItem3.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("RESPONSAVEL"));

        MenuItem administrarUsuariosMenuItem4 = menu.findItem(R.id.nav_lista_banco_historia_social_fragment);
        administrarUsuariosMenuItem4.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("RESPONSAVEL"));

        MenuItem administrarUsuariosMenuItem5 = menu.findItem(R.id.nav_lista_historia_social_fragment);
        administrarUsuariosMenuItem5.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("RESPONSAVEL"));

        MenuItem administrarUsuariosMenuItem6 = menu.findItem(R.id.nav_lista_historia_social_usuario_leitor_fragment);
        administrarUsuariosMenuItem6.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("LEITOR"));

        MenuItem administrarUsuariosMenuItem7 = menu.findItem(R.id.nav_lista_banco_de_historia_social_usuario_leitor_fragment);
        administrarUsuariosMenuItem7.setVisible(tipoUsuario.getTipo().equalsIgnoreCase("LEITOR"));

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_lista_usuario_leitor_fragment,
                R.id.nav_fragment_cadastro_usuario_leitor,
                R.id.nav_fragment_historia_social_cad,
                R.id.nav_lista_banco_historia_social_fragment,
                R.id.nav_lista_historia_social_fragment,
                R.id.nav_lista_historia_social_usuario_leitor_fragment,
                R.id.nav_lista_banco_de_historia_social_usuario_leitor_fragment)
                .setOpenableLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sobre:
                ImageView image = new ImageView(this);
                image.setImageResource(R.mipmap.logotipo_fundo_foreground);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Meridiane Schessoff Nunes - Trabalho de conclusão de curso.");
                //define a mensagem
                builder.setMessage(R.string.sobre)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //colocar a ação aqui
                            }
                        }).setView(image);

                builder.create().show();
                return true;
            case R.id.action_sair:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}