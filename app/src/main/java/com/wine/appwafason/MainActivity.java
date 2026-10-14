package com.wine.appwafason;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class MainActivity extends Activity {
  private WebView web;
  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    web = new WebView(this);
    setContentView(web);
    WebSettings s = web.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setDatabaseEnabled(true);
    s.setAllowFileAccess(true);
    s.setAllowFileAccessFromFileURLs(true);
    s.setAllowUniversalAccessFromFileURLs(true);
    s.setMediaPlaybackRequiresUserGesture(false);
    s.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    web.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView v, String u) {
        if (u.startsWith("http://") || u.startsWith("https://") || u.startsWith("file:")) return false;
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(u))); } catch (Exception e) {}
        return true;
      }
    });
    web.loadUrl("file:///android_asset/index.html");
  }
  @Override public boolean onKeyDown(int k, KeyEvent e) {
    if (k == KeyEvent.KEYCODE_BACK && web.canGoBack()) { web.goBack(); return true; }
    return super.onKeyDown(k, e);
  }
}
