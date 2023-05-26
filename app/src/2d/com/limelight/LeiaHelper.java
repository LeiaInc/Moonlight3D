package com.limelight;

import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class LeiaHelper {
	public interface SurfaceListener {
		void onSurfaceChanged(Surface surface);
	}
	public abstract static class StreamView extends SurfaceView {
		public StreamView(Context context) {
			super(context);
		}
		public StreamView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		public StreamView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}
		private SurfaceListener surfaceListener;
		public void setSurfaceListener(SurfaceListener surfaceListener) {
			this.surfaceListener = surfaceListener;
			getHolder().addCallback(new SurfaceHolder.Callback() {
				@Override
				public void surfaceCreated(@NonNull SurfaceHolder holder) {
					// no-op
				}

				@Override
				public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
					surfaceListener.onSurfaceChanged(holder.getSurface());
				}

				@Override
				public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
					// no-op
				}
			});
		}
	}

	public static void init(Application application) {
		// no-op
	}
	public static void update3dMode(StreamView streamView, boolean enable, boolean hasFocus) {
		// no-op
	}
}
