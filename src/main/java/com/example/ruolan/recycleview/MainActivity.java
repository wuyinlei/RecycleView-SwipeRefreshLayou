package com.example.ruolan.recycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mLayout;

    private List<String> datas = new ArrayList<>();

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (SwipeRefreshLayout) this.findViewById(R.id.refreshLayout);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
        //下面可以自己设置排版方式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //下面一行如果不写也是会自动调用的
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this, "你好", Toast.LENGTH_SHORT).show();
            }
        });
        //RecycleView的事件监听
        //初始化数据
        initDatas();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        //设置刷新的颜色
        mLayout.setColorSchemeResources(
                android.R.color.background_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark);
        //拖动多长的时候开始刷新
        mLayout.setDistanceToTriggerSync(100);

        mLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.item_press));

        //设置大小
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        //刷新监听器
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++){
                            mAdapter.addData(i,"new City " + i);
                        }
                        //通知刷新
                        mAdapter.notifyItemRangeChanged(0,10);
                        mRecyclerView.scrollToPosition(5);

                        //完成后调用完成的方法
                        mLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private void initDatas() {

        datas.add("New York");
        datas.add("Bei Jing");
        datas.add("Boston");
        datas.add("London");
        datas.add("San Francisco");
        datas.add("Chicago");
        datas.add("Shang Hai");
        datas.add("Tian Jin");
        datas.add("Zheng Zhou");
        datas.add("Hang Zhou");
        datas.add("Guang Zhou");
        datas.add("Fu Gou");
        datas.add("Zhou Kou");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mAdapter.addData(0, "new City");
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
