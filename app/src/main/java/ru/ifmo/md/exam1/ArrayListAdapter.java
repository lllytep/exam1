package ru.ifmo.md.exam1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anton on 21.01.15.
 */
public class ArrayListAdapter extends ArrayAdapter<ObjectItem>{
    private final Activity context;
    private int layoutResourceId;
    private List<ObjectItem> list;


    public ArrayListAdapter(Activity context, List<ObjectItem> list) {
        super(context, R.layout.rowlist2, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder{
        public TextView title;
        public EditText editTitle;
        public TextView content;
        public EditText editContent;
        public Button delete, edit, save;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null){
            /*
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
             */
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowlist, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.editTitle = (EditText)view.findViewById(R.id.edittitle);
            viewHolder.content = (TextView) view.findViewById(R.id.content);
            viewHolder.editContent = (EditText)view.findViewById(R.id.editcontent);
            viewHolder.edit = (Button) view.findViewById(R.id.edit);
            viewHolder.delete = (Button) view.findViewById(R.id.delete);
            viewHolder.save = (Button) view.findViewById(R.id.save);

            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.title.setVisibility(View.GONE);
                    viewHolder.editTitle.setVisibility(View.VISIBLE);
                    viewHolder.editTitle.setText(viewHolder.title.getText());
                    viewHolder.content.setVisibility(View.GONE);
                    viewHolder.editContent.setVisibility(View.VISIBLE);
                    viewHolder.editContent.setText(viewHolder.content.getText());
                    viewHolder.edit.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            viewHolder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectItem item = (ObjectItem) viewHolder.save.getTag();
                    item.title = String.valueOf(viewHolder.editTitle.getText());
                    viewHolder.title.setText(viewHolder.editTitle.getText());
                    item.content = String.valueOf(viewHolder.editContent.getText());
                    viewHolder.content.setText(viewHolder.editContent.getText());
                    viewHolder.title.setVisibility(View.VISIBLE);
                    viewHolder.editTitle.setVisibility(View.GONE);
                    viewHolder.content.setVisibility(View.VISIBLE);
                    viewHolder.editContent.setVisibility(View.GONE);
                    viewHolder.save.setVisibility(View.GONE);
                    viewHolder.edit.setVisibility(View.VISIBLE);
                    viewHolder.delete.setVisibility(View.VISIBLE);
                }
            });
            view.setTag(viewHolder);
            viewHolder.save.setTag(list.get(position));
            viewHolder.edit.setTag(list.get(position));
            viewHolder.delete.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).save.setTag(list.get(position));
            ((ViewHolder) view.getTag()).edit.setTag(list.get(position));
            ((ViewHolder) view.getTag()).delete.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        /*holder.
        holder.text.setText(list.get(position).getName());
        holder.checkbox.setChecked(list.get(position).isSelected());*/
        return view;
        //return super.getView(position, convertView, parent);
    }
}
