package org.cocos2dx.javascript;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

public class MainActivity extends Cocos2dxActivity{
	FacebookConnectPlugin facebookConnectPlugin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		facebookConnectPlugin = new FacebookConnectPlugin(this);
	    facebookConnectPlugin.onCreate(savedInstanceState);
		//For supports translucency
		
		//1.change "attribs" in cocos\2d\platform\android\nativeactivity.cpp
		/*const EGLint attribs[] = {
	            EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
	            EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,  
	            //EGL_BLUE_SIZE, 5,   -->delete 
	            //EGL_GREEN_SIZE, 6,  -->delete 
	            //EGL_RED_SIZE, 5,    -->delete 
	            EGL_BUFFER_SIZE, 32,  //-->new field
	            EGL_DEPTH_SIZE, 16,
	            EGL_STENCIL_SIZE, 8,
	            EGL_NONE
	    };*/
		
		//2.Set the format of window
		// getWindow().setFormat(PixelFormat.TRANSLUCENT);
		
		try {

		    PackageInfo info = getPackageManager().getPackageInfo(
		            "com.cocos.fgame", PackageManager.GET_SIGNATURES);
		    for (Signature signature : info.signatures) {
		        MessageDigest md = MessageDigest.getInstance("SHA");
		        md.update(signature.toByteArray());
		        Log.e("************** MY KEY HASH:",
		                Base64.encodeToString(md.digest(), Base64.DEFAULT));
		    }					
			
	    } catch (NameNotFoundException e) {

	    } catch (NoSuchAlgorithmException e) {

	    }		
		
	}

	@Override
	public void onResume() {
	    super.onResume();
	    Log.i("Rye", "onResume");
	    //facebookConnectPlugin.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("Rye", "onActivityResult");
	    super.onActivityResult(requestCode, resultCode, data);
	    facebookConnectPlugin.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    Log.i("Rye", "onPause");
	    facebookConnectPlugin.onPause();
	}

	@Override
	public void onDestroy() {
		Log.i("Rye", "onDestroy");
		facebookConnectPlugin.onDestory();
	    super.onDestroy();
	    
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    Log.i("Rye", "onSaveInstanceState");
	    facebookConnectPlugin.onSaveInstanceState(outState);
	}
}
