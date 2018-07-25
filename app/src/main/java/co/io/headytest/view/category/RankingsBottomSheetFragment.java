package co.io.headytest.view.category;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.io.headytest.R;
import co.io.headytest.data.model.ProductResponse;


import co.io.headytest.util.ViewModelFactory;
import co.io.headytest.viewmodel.RankingViewModel;

public class RankingsBottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.btn1)
     Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    Unbinder unbinder;

    List<ProductResponse.Ranking> rankingList;

    @Inject
    ViewModelFactory viewModelFactory;
    private RankingViewModel rankingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_dialog_bottom_sheet, container, false);
        unbinder = ButterKnife.bind(this, v);

        rankingViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(RankingViewModel.class);

        rankingList = rankingViewModel.getSelectedRepo().getValue();

        btn1.setText(""+rankingList.get(0).getRanking());
        btn2.setText(""+rankingList.get(1).getRanking());
        btn3.setText(""+rankingList.get(2).getRanking());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     startActivity(new Intent(getActivity(),BillDeskMainActivity.class));
            }
        });
        return v;
    }
}
