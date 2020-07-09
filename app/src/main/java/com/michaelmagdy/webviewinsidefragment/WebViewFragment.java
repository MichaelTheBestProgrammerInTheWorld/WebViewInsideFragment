package com.michaelmagdy.webviewinsidefragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    private WebView webView;


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.facebook.com/");
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                        if(webView!=null)
                        {
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                            } else {
                                getActivity().onBackPressed();
                            }
                        }
                    }
                }

                return true;
            }
        });


        //second way to achieve webView onBackPressed
//        webView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK
//                        && event.getAction() == MotionEvent.ACTION_UP
//                        && webView.canGoBack()) {
//                    handler.sendEmptyMessage(1);
//                    return true;
//                }
//                return false;
//            }
//        });

        return view;
    }


    //second way to achieve webView onBackPressed
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };

    private void webViewGoBack(){
        webView.goBack();
    }

}
