package com.jff.engine3d.model;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.custom_3DEnjine.drawing_utils.Coordinates;
import com.custom_3DEnjine.my_primitives.*;

public class DrawingObjectActivity extends Activity implements OnPrimitiveChangingListener {
	
	private MyCanvasView myCanvasView;
    protected ObjectSettingsFragment objectSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawing_object);
		
		myCanvasView = (MyCanvasView) findViewById(R.id.canvas);
		myCanvasView.setBackgroundColor(Color.WHITE);

        objectSettings = new ObjectSettingsFragment();
        objectSettings.setOnPrimitiveChangedListener(new OnPrimitiveChangingListener() {
            @Override
            public void onPrimitiveChanged(Primitive object) {
                myCanvasView.removeObject(object);
            }
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_drawing_object, menu);
		menu.add("Add object");
		
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				openCreateObjectDialog();
				return false;
			}
		});
		return true;
	}

	protected void openCreateObjectDialog() {
		
		final String[] myObjects = {Constants.PARALLELEPIPED_NAME, 
									Constants.CILINDER_NAME, 
									Constants.CONE_NAME,
                                    Constants.HALF_FRUST_CONE_NAME,
									Constants.ROLLER_NAME};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(myObjects, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int position) {
				if (myObjects[position] == Constants.PARALLELEPIPED_NAME) {
					Parallelepiped parallelepiped = new Parallelepiped(new Coordinates(100, 100, 0), 50, 100, 30);
					myCanvasView.addObject(parallelepiped);
				}
				if (myObjects[position] == Constants.CONE_NAME) {
					Cone cone = new Cone(new Coordinates(100, 100, 100), 50, 200);
					myCanvasView.addObject(cone);
				}
				if (myObjects[position] == Constants.CILINDER_NAME) {
					Cilinder cilinder = new Cilinder(new Coordinates(100, 100, 100), 100, 100, 200);
					myCanvasView.addObject(cilinder);
				}
                if (myObjects[position] == Constants.HALF_FRUST_CONE_NAME) {
                    HalfFrustCone cone = new HalfFrustCone(new Coordinates(100, 100, 100), 50, 100, 200);
                    myCanvasView.addObject(cone);
                }
				if (myObjects[position] == Constants.ROLLER_NAME) {
					Roller roller = new Roller(new Coordinates(100, 100, 100), 100, 50, 30, 100, 50, 10, 20, 150, 30, 30, 4);
					myCanvasView.addObject(roller);
				}
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void showObjectSettingsFragment(Primitive object) {
        objectSettings.setObject(object);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, objectSettings);
        transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void hideObjectSettingsFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(objectSettings);
        transaction.commit();
    }

    @Override
    public void onPrimitiveChanged(Primitive object) {
        objectSettings.setObject(object);
        objectSettings.updateCoordinates();
    }
}
