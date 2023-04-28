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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_fragment_cadastro_usuario_leitor,
                R.id.nav_fragment_historia_social_List,
                R.id.nav_banco_historias_fragment,
                R.id.nav_fragment_historia_social_cad,
                R.id.nav_lista_usuario_leitor_fragment)
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
                builder.setTitle("Meridiane Schessoff Nunes - Programação para web 3.");
                //define a mensagem
                builder.setMessage(R.string.sobre)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //colocar a ação aqui
                            }
                        }).setView(image);

                // Create the AlertDialog object and return it
                builder.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
  /*  @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
            case R.id.nav_fragment_historia_social_List:
                // permitir que o usuário acesse esses itens de menu
                // adicione aqui o código para abrir a Activity/Fragment correspondente
                break;
            case R.id.nav_lista_usuario_leitor_fragment:
            case R.id.nav_fragment_cadastro_usuario_leitor:
            case R.id.nav_banco_historias_fragment:
            case R.id.nav_fragment_historia_social_cad:
                // não permitir que o usuário acesse esses itens de menu
                // adicione aqui o código para exibir uma mensagem de erro ou ocultar o item de menu
                break;
        }

        // fechar o drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

}