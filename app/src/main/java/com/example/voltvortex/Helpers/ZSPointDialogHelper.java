package com.example.voltvortex.Helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ZSModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.ZSRecyclerViewAdapter;

import java.util.List;

public class ZSPointDialogHelper {

    private Context context;
    private MyDatabaseHelper dbHelper;
    private ZSRecyclerViewAdapter zsAdapter;
    private int buildingId, floorId, roomId;
    private float result;

    public ZSPointDialogHelper(Context context, ZSRecyclerViewAdapter zsAdapter, int buildingId, int floorId, int roomId, float result) {
        this.context = context;
        this.dbHelper = new MyDatabaseHelper(context);
        this.zsAdapter = zsAdapter;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomId = roomId;
        this.result = result;
    }

    public void setupButtons(View dialogView, AlertDialog dialog) {
        Button buttonCancelAddPAR = dialogView.findViewById(R.id.buttonCancelAddPAR);
        buttonCancelAddPAR.setOnClickListener(v -> dialog.dismiss());

        Button button1fP = dialogView.findViewById(R.id.button1fP);
        button1fP.setOnClickListener(createAddPointsListener(new int[]{1}, dialog));

        Button buttonGD = dialogView.findViewById(R.id.buttonGD);
        buttonGD.setOnClickListener(createAddPointsListener(new int[]{12, 13}, dialog));

        Button button3fP = dialogView.findViewById(R.id.button3fP);
        button3fP.setOnClickListener(createAddPointsListener(new int[]{11}, dialog));

        Button button1f2P = dialogView.findViewById(R.id.button1f2P);
        button1f2P.setOnClickListener(createAddPointsListener(new int[]{2, 3}, dialog));

        Button button1f3P = dialogView.findViewById(R.id.button1f3P);
        button1f3P.setOnClickListener(createAddPointsListener(new int[]{4, 5, 6}, dialog));

        Button button1f4P = dialogView.findViewById(R.id.button1f4P);
        button1f4P.setOnClickListener(createAddPointsListener(new int[]{7, 8, 9, 10}, dialog));

        Button button1fPK = dialogView.findViewById(R.id.button1fPK);
        button1fPK.setOnClickListener(createAddPointsListener(new int[]{14}, dialog));

        Button button1f2PK = dialogView.findViewById(R.id.button1f2PK);
        button1f2PK.setOnClickListener(createAddPointsListener(new int[]{15, 16}, dialog));

        Button button1f3PK = dialogView.findViewById(R.id.button1f3PK);
        button1f3PK.setOnClickListener(createAddPointsListener(new int[]{17, 18, 19}, dialog));

        Button button1f4PK = dialogView.findViewById(R.id.button1f4PK);
        button1f4PK.setOnClickListener(createAddPointsListener(new int[]{20, 21, 22, 23}, dialog));

        Button buttonSetOne = dialogView.findViewById(R.id.buttonSetOne);
        buttonSetOne.setOnClickListener(createAddPointsListener(new int[]{15, 16, 15, 16, 2, 3}, dialog));

        Button buttonSetTwo = dialogView.findViewById(R.id.buttonSetTwo);
        buttonSetTwo.setOnClickListener(createAddPointsListener(new int[]{15, 16, 2, 3}, dialog));

        Button buttonOswWypust = dialogView.findViewById(R.id.buttonOswWypust);
        buttonOswWypust.setOnClickListener(createAddPointsListener(new int[]{28}, dialog));

        Button buttonOswZar = dialogView.findViewById(R.id.buttonOswZar);
        buttonOswZar.setOnClickListener(createAddPointsListener(new int[]{29}, dialog));

        Button buttonOswOpr = dialogView.findViewById(R.id.buttonOswOpr);
        buttonOswOpr.setOnClickListener(createAddPointsListener(new int[]{33}, dialog));

        Button buttonOswLiniowe = dialogView.findViewById(R.id.buttonOswLiniowe);
        buttonOswLiniowe.setOnClickListener(createAddPointsListener(new int[]{30}, dialog));

        Button buttonOswSwiet = dialogView.findViewById(R.id.buttonOswSwiet);
        buttonOswSwiet.setOnClickListener(createAddPointsListener(new int[]{32}, dialog));

        Button buttonOswPodTynk = dialogView.findViewById(R.id.buttonOswPodTynk);
        buttonOswPodTynk.setOnClickListener(createAddPointsListener(new int[]{26}, dialog));

        Button buttonOswRastr = dialogView.findViewById(R.id.buttonOswRastr);
        buttonOswRastr.setOnClickListener(createAddPointsListener(new int[]{27}, dialog));

        Button buttonOswOkrg = dialogView.findViewById(R.id.buttonOswOkrg);
        buttonOswOkrg.setOnClickListener(createAddPointsListener(new int[]{25}, dialog));

        Button buttonOswKw = dialogView.findViewById(R.id.buttonOswKw);
        buttonOswKw.setOnClickListener(createAddPointsListener(new int[]{31}, dialog));

        Button buttonOswPodl = dialogView.findViewById(R.id.buttonOswPodl);
        buttonOswPodl.setOnClickListener(createAddPointsListener(new int[]{24}, dialog));

        Button buttonKlima = dialogView.findViewById(R.id.buttonKlima);
        buttonKlima.setOnClickListener(createAddPointsListener(new int[]{34}, dialog));

        Button buttonCW = dialogView.findViewById(R.id.buttonCW);
        buttonCW.setOnClickListener(createAddPointsListener(new int[]{35}, dialog));

        Button buttonPompa = dialogView.findViewById(R.id.buttonPompa);
        buttonPompa.setOnClickListener(createAddPointsListener(new int[]{36}, dialog));

        Button buttonPiec = dialogView.findViewById(R.id.buttonPiec);
        buttonPiec.setOnClickListener(createAddPointsListener(new int[]{37}, dialog));

        Button buttonSilnik = dialogView.findViewById(R.id.buttonSilnik);
        buttonSilnik.setOnClickListener(createAddPointsListener(new int[]{38}, dialog));

        Button buttonGrill = dialogView.findViewById(R.id.buttonGrill);
        buttonGrill.setOnClickListener(createAddPointsListener(new int[]{39}, dialog));
    }

    private View.OnClickListener createAddPointsListener(int[] componentIds, AlertDialog dialog) {
        return v -> {
            boolean success = true;

            for (int componentId : componentIds) {
                ZSModel newPoint = new ZSModel(
                        componentId,  // MeasuredComponentID
                        11,  // ElectricalProtectionID
                        floorId,
                        roomId,
                        16.0f,
                        0.0f,  // MeasuredZS, zastąp właściwą wartością
                        result,  // Result, zastąp właściwą wartością
                        false, // isBZ
                        false, // isBPE
                        false, // isBK
                        false, // isBKLAPKI
                        false, // isWYRW
                        false, // is2PRZEW
                        false  // wasMeasured
                );
                success &= dbHelper.addZSPoint(newPoint, buildingId);
            }

            if (success) {
                // Pobranie zaktualizowanej listy punktów ZS
                List<ZSModel> zsPoints = dbHelper.getZSPoints(buildingId, floorId, roomId);
                zsAdapter.updateData(zsPoints);
                zsAdapter.notifyDataSetChanged(); // Odświeżenie adaptera
            } else {
                Toast.makeText(context, "Nie udało się dodać wszystkich punktów", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
