package id.renaldirey.syncofflinedatafromapi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.renaldirey.syncofflinedatafromapi.R;
import id.renaldirey.syncofflinedatafromapi.database.DatabaseConfig;
import id.renaldirey.syncofflinedatafromapi.database.MyDatabase;
import id.renaldirey.syncofflinedatafromapi.database.entity.MealEntity;
import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    Context context;
    List<ListMeal> data;

    public MealAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void add(ListMeal item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void addAll(List<ListMeal> items) {
        for (ListMeal item : items) {
            add(item);
        }
    }

    public ListMeal getData(int position) {
        return data.get(position);
    }

    public void remove(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_meal) ImageView ivMeal;
        @BindView(R.id.tv_name) TextView tvName;
        @BindView(R.id.btn_add) Button btnAdd;
        ListMeal item;

        public ViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meal, parent, false));
        }

        public void bindData(ListMeal item) {
            ButterKnife.bind(this, itemView);

            this.item = item;

            tvName.setText(item.getName());
            btnAdd.setText(isAvailable()?
                    context.getResources().getString(R.string.button_remove):
                    context.getResources().getString(R.string.button_add));

            Picasso.with(context)
                    .load(item.getThumbnail())
                    .into(ivMeal);
        }

        private boolean isAvailable() {
            return DatabaseConfig.getDataCurriculumWhere(MyDatabase.getInstance(context), item) != null;
        }

        @OnClick(R.id.btn_add)
        void onAdd() {
            if(!isAvailable()) {
                DatabaseConfig.insertData(MyDatabase.getInstance(context), new MealEntity(item));
                notifyDataSetChanged();
            } else {
                DatabaseConfig.deleteMealFrom(MyDatabase.getInstance(context),
                        DatabaseConfig.getDataCurriculumWhere(MyDatabase.getInstance(context), item));
                notifyDataSetChanged();
            }
        }
    }
}
