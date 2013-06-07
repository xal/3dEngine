package com.jff.engine3d.view.android;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by mba on 06.06.13.
 */
public class CameraFragment extends Fragment {

    public CameraFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_camera, container, false);

        return myFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initUI();
    }

    private void init() {
        Context context = this.getActivity();


    }

    private void initUI() {


    }
}
