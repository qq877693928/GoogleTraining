package google.example.com.googletraning;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import google.example.com.googletraning.R;
import google.example.com.googletraning.launcher.SafariListAdapter;

/**
 * Description:
 *
 * @author lizhenhua2003@gmail.com (Jones.Lee)
 * @date 16/6/23 12:28
 */
public class Android6SafariActivity extends AppCompatActivity {
  private final static String INTENT_CATEGORY = "android.intent.category.SAMPLE_ANDROID_6";
  private RecyclerView mRecyclerView;
  private SafariListAdapter mAdapter;
  private static PackageManager pm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_safari);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    findViewById(R.id.fab).setVisibility(View.GONE);
    setSupportActionBar(toolbar);

    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);

    loadData();
  }

  private void loadData() {
    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(INTENT_CATEGORY);

    pm = getPackageManager();
    List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

    if (null == list)
      return;

    Collections.sort(list, sDisplayNameComparator);
    mAdapter = new SafariListAdapter(list, this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

  }

  private final static Comparator<ResolveInfo> sDisplayNameComparator =
      new Comparator<ResolveInfo>() {
        private final Collator collator = Collator.getInstance();

        public int compare(ResolveInfo info1, ResolveInfo info2) {
          return collator.compare(info1.activityInfo.loadLabel(pm),
              info2.activityInfo.loadLabel(pm));
        }
      };
}
