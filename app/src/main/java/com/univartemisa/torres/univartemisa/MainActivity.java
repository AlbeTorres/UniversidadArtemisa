package com.univartemisa.torres.univartemisa;

import android.content.Loader;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Loader.OnLoadCompleteListener, OnRenderListener, OnLoadCompleteListener {

    private String _currentPdf = "Historia del béisbol.pdf";
    private PDFView _pdfView;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Historia del béisbol");
        setSupportActionBar(toolbar);




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this._pdfView = (PDFView)findViewById(R.id.pdfView);

        loadPdf(this._currentPdf);


    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.getItem(0);
        menuItem.setEnabled(false);
        setMenuItemColor(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int i = item.getItemId();
        MenuItem menuItem = toolbar.getMenu().getItem(0);
        int id = item.getItemId();

        if (id == R.id.historia) {
            toolbar.setTitle("Historia del béisbol");
            menuItem.setIcon(R.mipmap.ic_menu_history);
            setMenuItemColor(menuItem);
            loadPdf("Historia del béisbol.pdf");

        } else if (id == R.id.definición) {
            toolbar.setTitle("Definición de términos");
            menuItem.setIcon(R.mipmap.ic_menu_university_council);
            setMenuItemColor(menuItem);
            loadPdf("Definición de términos.pdf");


        } else if (id == R.id.director) {
            toolbar.setTitle("Director de equipo y auxiliares");
            menuItem.setIcon(R.mipmap.ic_menu_agreements);
            setMenuItemColor(menuItem);
            loadPdf("Director de equipo y auxiliares.pdf");


        } else if (id == R.id.bateador) {
            toolbar.setTitle("El bateador");
            menuItem.setIcon(R.mipmap.ic_menu_people);
            setMenuItemColor(menuItem);
            loadPdf("El bateador.pdf");


        } else if (id == R.id.lanzador) {
            toolbar.setTitle("Lanzador");
            menuItem.setIcon(R.mipmap.ic_menu_gold_titles);
            setMenuItemColor(menuItem);
            loadPdf("Lanzador.pdf");


        }else if (id == R.id.receptor) {
            toolbar.setTitle("Receptor");
            menuItem.setIcon(R.mipmap.ic_menu_rector_awards);
            setMenuItemColor(menuItem);
            loadPdf("Receptor.pdf");


        }else if (id == R.id.cuadro) {
            toolbar.setTitle("Jugadores de cuadro");
            menuItem.setIcon(R.mipmap.ic_menu_villena_awards);
            setMenuItemColor(menuItem);
            loadPdf("Jugadores de cuadro.pdf");


        }else if (id == R.id.jardineros) {
            toolbar.setTitle("Jardineros");
            menuItem.setIcon(R.mipmap.ic_menu_location);
            setMenuItemColor(menuItem);
            loadPdf("Jardineros.pdf");


        }else if (id == R.id.táctica) {
            toolbar.setTitle("Táctica defensiva");
            menuItem.setIcon(R.mipmap.ic_menu_about);
            setMenuItemColor(menuItem);
            loadPdf("Táctica defensiva.pdf");


        }else if (id == R.id.categorías) {
        toolbar.setTitle("Categorías inferiores");
        menuItem.setIcon(R.mipmap.ic_menu_about);
        setMenuItemColor(menuItem);
        loadPdf("Categorías inferiores.pdf");


        }else if (id == R.id.logros) {
        toolbar.setTitle("Logros internacionales");
        menuItem.setIcon(R.mipmap.ic_menu_about);
        setMenuItemColor(menuItem);
        loadPdf("Logros internacionales.pdf");


         }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void loadPdf(String paramString) {
        this._currentPdf = paramString;
        this._pdfView.fromAsset(paramString).defaultPage(0).enableSwipe(true).onLoad(this).onRender(this).spacing(5).load();
    }

    @Override
    public void loadComplete(int i) {

    }

    @Override
    public void onLoadComplete(Loader loader, Object data) {

    }

    @Override
    public void onInitiallyRendered(int i, float v, float v1) {
        this._pdfView.fitToWidth();
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
        super.onConfigurationChanged(paramConfiguration);
        getWindow().setFlags(1024, 1024);
        loadPdf(this._currentPdf);
    }

    private void setMenuItemColor(MenuItem paramMenuItem) {
        Drawable drawable = paramMenuItem.getIcon();
        drawable.mutate();
        drawable.setColorFilter(-1, PorterDuff.Mode.SRC_IN);

    }



}
