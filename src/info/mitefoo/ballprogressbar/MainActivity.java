package info.mitefoo.ballprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private BallProgressBar ball = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ball = (BallProgressBar) findViewById(R.id.ball);
	}

	public void start(View v){
		if(ball.isShown()){
			((Button)findViewById(R.id.btn)).setText(R.string.start);
			ball.setVisibility(View.INVISIBLE);
		}else{
			((Button)findViewById(R.id.btn)).setText(R.string.stop);
			ball.setVisibility(View.VISIBLE);
		}
	}

}
