package google.example.com.googletraning.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import google.example.com.googletraning.R;
import google.example.com.googletraning.utils.SLogUtil;

/**
 * @author lizhehua9@wanda.cn (lzh)
 * @date 16/5/27 09:14
 */
public class SafariListAdapter extends RecyclerView.Adapter<SafariListAdapter.ActivityItemHolder> {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private static List<ResolveInfo> mData;
  private PackageManager pm;

  public SafariListAdapter(List<ResolveInfo> list, Context context) {
    mContext = context;
    pm = mContext.getPackageManager();
    mData = list;
    mLayoutInflater = LayoutInflater.from(context);
  }

  public void setData(List<ResolveInfo> data) {
    mData = data;
    notifyDataSetChanged();
  }

  @Override
  public ActivityItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ActivityItemHolder(mLayoutInflater.inflate(R.layout.layout_safari_item, parent,
        false));
  }

  @Override
  public void onBindViewHolder(ActivityItemHolder holder, int position) {
      SLogUtil.d(mData.get(position).activityInfo.loadLabel(pm));
    holder.mTextView.setText(mData.get(position).activityInfo.loadLabel(pm));
  }

  @Override
  public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }

  public static class ActivityItemHolder extends RecyclerView.ViewHolder {
    TextView mTextView;

    ActivityItemHolder(View view) {
      super(view);
      mTextView = (TextView) view.findViewById(R.id.activity_item_text);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          SLogUtil.d("onClick--> position = " + getAdapterPosition());
          v.getContext().startActivity(
              activityIntent(mData.get(getPosition()).activityInfo.applicationInfo.packageName,
                  mData.get(getPosition()).activityInfo.name));
        }
      });
    }

    protected Intent activityIntent(String pkg, String componentName) {
      Intent result = new Intent();
      result.setClassName(pkg, componentName);
      return result;
    }
  }
}
