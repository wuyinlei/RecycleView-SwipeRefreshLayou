package com.example.ruolan.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ruolan on 2015/11/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mDatas;

    private LayoutInflater mInflater;

    private OnItemClickListener mListener;

    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyAdapter(List<String> datas){
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //读取布局文件
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itme,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
    }

    public void addData(int position,String city){
        mDatas.add(position,city);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        mListener.onClick(v,getLayoutPosition(),mDatas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    //RecycleView的事件监听
    interface  OnItemClickListener{
        //自己定义
        void onClick(View v,int position,String city);
    }
}
