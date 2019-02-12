package base.baseactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.searchmovies.view.SearchMoviesActivity;

public class BaseActivity extends AppCompatActivity {

    public void setUpSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(getApplicationContext(), SearchMoviesActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);

                hideSoftKeyboard();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
                new MenuItemCompat.OnActionExpandListener () {

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        searchView.requestFocus();
                        showSoftKeyboard();

                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return true;
                    }
                });
    }

    private void hideSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            if ((getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void showSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            if ((getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
            }
        }
    }
}