package com.can.mysqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.can.mysqlite.adapter.MessageAdapter;
import com.can.mysqlite.bean.MessageBean;
import com.can.mysqlite.utils.MessageDataBaseUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText et_title;
    private Button btn_submit;
    private ListView lv;

    private MessageDataBaseUtils utils ; //数据库操作
    private MessageAdapter adapter; //适配器
    private List<MessageBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    //数据初始化
    private void initData() {
        utils = new MessageDataBaseUtils(this);
        list = utils.getMessages();
        adapter = new MessageAdapter(this,list);
        lv.setAdapter(adapter);
    }

    //view初始化
    private void initView() {
        et_title = (EditText) findViewById(R.id.et_title);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        lv = (ListView) findViewById(R.id.lv);
    }

    //初始化监听器
    private void initListener() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString().trim();
                if(title!=null&&!title.equals("")){
                    et_title.setText("");
                    if(utils.searchMessage(title)){
                        Toast.makeText(MainActivity.this,"消息已存在",Toast.LENGTH_SHORT).show();
                    }else{
                        long time=System.currentTimeMillis();
                        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
                        String d=format.format(new Date(time));
                        utils.addMessage(title,d,"0");
                        list.add(new MessageBean(title,d,"0"));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MessageBean bean = list.get(i);
                String title = bean.getNoeTitle();
                if(!utils.searchMessageIsRead(title)){
                    utils.setMessageIsRead(title);
                    bean.setIsRead("1");
                    list.set(i,bean);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
