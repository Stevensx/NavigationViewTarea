package com.example.navigationviewtarea;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navView;
    private Menu menu;
    private Fragment1 fragment;
    private DrawerLayout drawerLayout;

    // Variable para llevar un registro del ítem previamente seleccionado
    private MenuItem selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout2);
        navView = findViewById(R.id.nav_view2);

        toolbar.setTitle("Mi app");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navView.setNavigationItemSelectedListener(this);
        menu = navView.getMenu();
        crearMenuDinamico();

        fragment = new Fragment1();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame2, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void crearMenuDinamico() {
        SubMenu socialSubMenu = menu.addSubMenu("Social");

        MenuItem primaryItem = socialSubMenu.add(Menu.NONE, 1, 1, "Principal");
        primaryItem.setIcon(R.drawable.ic_action_primary);

        MenuItem socialItem = socialSubMenu.add(Menu.NONE, 2, 2, "Social");
        socialItem.setIcon(R.drawable.ic_action_social);

        MenuItem promotionsItem = socialSubMenu.add(Menu.NONE, 3, 3, "Promociones");
        promotionsItem.setIcon(R.drawable.ic_action_promociones);

        SubMenu labelsSubMenu = menu.addSubMenu("Todas las etiquetas");

        MenuItem allMailsItem = labelsSubMenu.add(Menu.NONE, 4, 4, "Todos los correos");
        allMailsItem.setIcon(R.drawable.ic_action_allinmox);

        MenuItem starredItem = labelsSubMenu.add(Menu.NONE, 5, 5, "Destacados");
        starredItem.setIcon(R.drawable.ic_action_start);

        MenuItem importantItem = labelsSubMenu.add(Menu.NONE, 6, 6, "Importantes");
        importantItem.setIcon(R.drawable.ic_action_important);

        MenuItem sentItem = labelsSubMenu.add(Menu.NONE, 7, 7, "Enviados");
        sentItem.setIcon(R.drawable.ic_action_sent);

        MenuItem outboxItem = labelsSubMenu.add(Menu.NONE, 8, 8, "Bandeja de salida");
        outboxItem.setIcon(R.drawable.ic_action_out);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Desmarca el ítem previamente seleccionado (si hay alguno)
        if (selectedItem != null) {
            selectedItem.setChecked(false);
        }

        // Marca el nuevo ítem como seleccionado
        item.setChecked(true);

        // Actualiza el título de la barra de herramientas con el nombre del ítem
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(item.getTitle());
        }

        // Resto de la lógica para mostrar el contenido correspondiente
        String contenido = "Contenido del Item " + item.getTitle();
        fragment.setContent(contenido);

        // Asigna el ítem actual como el ítem seleccionado
        selectedItem = item;

        // Cierra el cajón de navegación
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}