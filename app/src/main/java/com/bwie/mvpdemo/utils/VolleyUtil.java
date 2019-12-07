package com.bwie.mvpdemo.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bwie.mvpdemo.app.App;

import java.util.Map;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 04:19
 * @Description:${DESCRIPTION}
 */
public class VolleyUtil {
    //volley 请求队列
    private RequestQueue requestQueue;

    private static  VolleyUtil volleyUtil;

    private VolleyUtil(){
        requestQueue = Volley.newRequestQueue(App.getContext());
    }

    /**
     * 暴露刚公共方法 创建私有对象 让外部调用    双重 两次判断 检验锁  同步锁
     * @return
     */
    public static VolleyUtil getInstance() {
        //第一重
        if(volleyUtil == null){
            //加锁 为了解决多线程并发安全
            synchronized ((VolleyUtil.class)){
                //二重
                if (volleyUtil == null) {
                    volleyUtil = new VolleyUtil();
                }
            }
        }
        return volleyUtil;
    }

    //get请求
    public void doGet(Map<String,String> params, String url, final VolleyCallback volleyCallback){

        String endUrl = url +"?";
        if(params!=null && params.size()>0){
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                endUrl += stringStringEntry.getKey()+ "=" + stringStringEntry.getValue()+"&";
            }
        }
        System.out.println("endUrl"+endUrl);

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (volleyUtil != null) {
                    volleyCallback.success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyUtil != null) {
                    volleyCallback.failure(error);
                }
            }
        });
        //添加
        requestQueue.add(stringRequest);
    }

    //post
    public void doPost(final Map<String,String> params, String url, final VolleyCallback volleyCallback){
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (volleyUtil != null) {
                    volleyCallback.success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyUtil != null) {
                    volleyCallback.failure(error);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        //添加
        requestQueue.add(stringRequest);
    }
    /**
     * 回调volley响应数据
     *
     */
    public interface  VolleyCallback{
        void success(String respose);

        void failure(Throwable throwable);
    }

}
