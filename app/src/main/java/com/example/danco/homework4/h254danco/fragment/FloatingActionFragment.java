package com.example.danco.homework4.h254danco.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.danco.homework4.h254danco.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.danco.homework4.h254danco.fragment.FloatingActionFragment.FloatingActionFragmentListener} interface
 * to handle interaction events.
 * Use the {@link FloatingActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FloatingActionFragment extends Fragment {

    private FloatingActionFragmentListener listener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FloatingActionFragment.
     */
    public static FloatingActionFragment newInstance() {
        FloatingActionFragment fragment = new FloatingActionFragment();
        return fragment;
    }


    public FloatingActionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_floating_action, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageVIew = (ImageView) view.findViewById(R.id.floatingActionImageView);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface FloatingActionFragmentListener {
        // TODO: Update argument type and name
        public void onFloatingActionInteraction(Uri uri);
    }

    private ViewHolder getViewHolder() {
        View view = getView();
        return view != null ? (ViewHolder) view.getTag() : null;
    }

    /* package */ static class ViewHolder {
        final ImageView actionButton;

        ViewHolder(View view) {
            actionButton = (ImageView) view.findViewById(R.id.floatingActionImageView);
        }
    }
}
