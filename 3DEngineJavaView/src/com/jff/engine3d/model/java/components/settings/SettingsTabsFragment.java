package com.jff.engine3d.model.java.components.settings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class SettingsTabsFragment extends Composite {

    public SettingsTabsFragment(Composite parent) {
        super(parent, SWT.NONE);


        Layout layout = new GridLayout(1, false);
        this.setLayout(layout);

        GridData tabsData = new GridData(SWT.FILL, SWT.FILL, false, true);


        final TabFolder tabFolder = new TabFolder(this, SWT.NONE);

        tabFolder.setLayoutData(tabsData);


        String title;
        Control control;

        title = "Create";
        control = new CreateFragment(tabFolder);
        createTab(tabFolder, title, control);


        title = "Modify";
        control = new ModifyFragment(tabFolder);
        createTab(tabFolder, title, control);

        title = "Camera";
        control = new CameraFragment(tabFolder);
        createTab(tabFolder, title, control);

        title = "View";
        control = new ViewFragment(tabFolder);
        createTab(tabFolder, title, control);

        title = "Save";
        control = new SaveFragment(tabFolder);
        createTab(tabFolder, title, control);


    }

    private void createTab(TabFolder tabFolder, String title, Control control) {
        TabItem item = new TabItem(tabFolder, SWT.NULL);
        item.setText(title);
        item.setControl(control);
    }


    //
//    private Primitive object;
//
//    private EditText xCoords;
//    private EditText yCoords;
//    private EditText zCoords;
//
//    private ImageButton removeButton;
//    private EditText wheelsNumber;
//    private LinearLayout wheelsNumberLayout;
//
//    private View fragment;
//
//    public OnPrimitiveChangingListener listener;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        fragment = inflater.inflate(R.layout.object_settings, container, false);
//        initializeViews();
//
//        return fragment;
//    }
//
//    private void initializeViews(){
//        xCoords = (EditText) fragment.findViewById(R.id.X);
//        yCoords = (EditText) fragment.findViewById(R.id.Y);
//        zCoords = (EditText) fragment.findViewById(R.id.Z);
//
//        removeButton = (ImageButton) fragment.findViewById(R.id.remove);
//        wheelsNumber = (EditText) fragment.findViewById(R.id.wheelsNumber);
//        wheelsNumberLayout = (LinearLayout) fragment.findViewById(R.id.wheelsNumberLayout);
//
//        wheelsNumberLayout.setVisibility(View.GONE);
//
//        updateCoordinates();
//        initializeListeners();
//    }
//
//    public void updateCoordinates() {
//        try {
//            xCoords.setText(((Double) object.center.getX()).toString());
//            yCoords.setText(((Double)object.center.getY()).toString());
//            zCoords.setText(((Double)object.center.getZ()).toString());
//
//            if (object instanceof Roller) {
//                wheelsNumberLayout.setVisibility(View.VISIBLE);
//                wheelsNumber.setText(((Integer)((Roller)object).getWheelsCount()).toString());
//            }
//            else
//                wheelsNumberLayout.setVisibility(View.GONE);
//        }
//        catch (NullPointerException e) {
//            Log.e("MyLogs", "Fragment is not attached");
//        }
//    }
//
//    private void initializeListeners() {
//        xCoords.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                Coordinates coordsToMove = object.center.clone();
//                coordsToMove.setX(Double.parseDouble(v.getText().toString()));
//                object.move(object.center, coordsToMove);
//                return false;
//            }
//        });
//        yCoords.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                Coordinates coordsToMove = object.center.clone();
//                coordsToMove.setY(Double.parseDouble(v.getText().toString()));
//                object.move(object.center, coordsToMove);
//                return false;
//            }
//        });
//        zCoords.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                Coordinates coordsToMove = object.center.clone();
//                coordsToMove.setZ(Double.parseDouble(v.getText().toString()));
//                object.move(object.center, coordsToMove);
//                return false;
//            }
//        });
//
//        removeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onPrimitiveChanged(object);
//            }
//        });
//
//        wheelsNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                //save pre-edited object
//                Coordinates temp = object.center.clone();
//                //set center which is non calculated
//                object.center = ((Roller)object).oldCenter;
//                //set new wheels count
//                ((Roller)object).setWheelsCount(Integer.parseInt(v.getText().toString()));
//                //reinitialize object
//                object.initialize();
//                //enable object
//                object.setEnabled(true);
//                //move and rotate object
//                object.move(object.center, temp);
//                object.rotate(new Coordinates(object.ax, object.ay, object.az), object.center);
//                return false;
//            }
//        });
//    }
//
//    public void setObject(Primitive object) {
//        this.object = object;
//    }
//
//    public void setOnPrimitiveChangedListener(OnPrimitiveChangingListener listener) {
//        this.listener = listener;
//    }
}
