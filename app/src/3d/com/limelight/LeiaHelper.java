package com.limelight;

import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.widget.Toast;

import com.leia.core.LogLevel;
import com.leia.sdk.LeiaSDK;
import com.leia.sdk.views.InputViewsAsset;
import com.leia.sdk.views.InterlacedSurfaceView;
import com.leia.sdk.views.InterlacedSurfaceViewConfigAccessor;

public class LeiaHelper {
	public interface SurfaceListener {
		void onSurfaceChanged(Surface surface);
	}
	public abstract static class StreamView extends InterlacedSurfaceView {
		public StreamView(Context context) {
			super(context);
		}
		public StreamView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		public void setSurfaceListener(SurfaceListener surfaceListener) {
			InputViewsAsset surfaceAsset = InputViewsAsset.createEmptySurfaceForVideo(surfaceTexture -> {
				surfaceListener.onSurfaceChanged(new Surface(surfaceTexture));
    	    });
	        setViewAsset(surfaceAsset);
		}
	}

	public static void init(Application application) {
		try {
			LeiaSDK.InitArgs initArgs = new LeiaSDK.InitArgs();
			initArgs.platform.app = application;
			initArgs.platform.logLevel = LogLevel.Default;
			LeiaSDK leiaSDK = LeiaSDK.createSDK(initArgs);

			leiaSDK.startFaceTracking(false);
		} catch (Exception e) {
			Log.e("MainApp", String.format("Failed to initialize LeiaSDK: %s", e));
			Toast.makeText(application, "Failed to initialize LeiaSDK", Toast.LENGTH_SHORT).show();
		}
	}
	public static void update3dMode(StreamView streamView, boolean enable3dMode, boolean hasFocus) {
		try (InterlacedSurfaceViewConfigAccessor config = streamView.getConfig()) {
            config.setNumTiles(enable3dMode ? 2 : 1, 1);
        }

        LeiaSDK leiaSDK = LeiaSDK.getInstance();
        if (leiaSDK != null) {
            leiaSDK.startFaceTracking(enable3dMode && hasFocus);
            leiaSDK.enableBacklight(enable3dMode && hasFocus);
        }
	}
}
