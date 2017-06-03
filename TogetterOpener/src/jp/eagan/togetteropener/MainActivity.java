package jp.eagan.togetteropener;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Resources res = getResources();
		Intent intentReceive = getIntent();
		Uri uriReceive = intentReceive.getData();
		String path = uriReceive.getPath();
		
		String uriTogetterSchema = res.getString(R.string.uri_togetter_schema);
		String uriTogetterPathWeb = res.getString(R.string.uri_togetter_path_web);
		String uriTogetterPathApp = res.getString(R.string.uri_togetter_path_app);
		
		if (path.startsWith(uriTogetterPathWeb)) {
			path = uriTogetterPathApp + path.substring(uriTogetterPathWeb.length());
		}
		Uri uri = Uri.parse(uriTogetterSchema + path);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		try {
			startActivity(intent);
			finish();
		} catch (ActivityNotFoundException e) {
			// probably not installed
			setContentView(R.layout.activity_main);
			Button buttonGoogleplay = (Button) findViewById(R.id.button_googleplay);
			final String uriTogetterGoogleplay = res.getString(R.string.uri_togetter_googleplay);
			buttonGoogleplay.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							onClickLink(uriTogetterGoogleplay);
						}
					});
		}
	}
	
	protected void onClickLink(String link) {
		Uri uri = Uri.parse(link);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}
