package com.example.mysql;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;

public class TambahTeman extends AppCompatActivity {
    private EditText editNama,editTelpon;
    private Button simpanBtn;
    String nm,tlp;
    int succsess;

    private static String url_insert = "http://10.0.2.2/umyTI/tambahtm.php";
    private static final String TAG = TambahTeman.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        editNama    = findViewById(R.id.edNama);
        editTelpon  = findViewById(R.id.edTelpon);
        simpanBtn   = findViewById(R.id.btnSimpan);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNama.getText().toString().equals("")||editTelpon.getText().toString().equals("")){
                    Toast.makeText(TambahTeman.this,"Semua harus diisi data",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nm  = editNama.getText().toString();
                    tlp = editTelpon.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>()){
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG,"response : "+response.toString());

                            try{
                                JSONObject jobj = new JSONObject(response);
                                succsess = jobj.getInt(TAG_SUCCES);
                                if (succsess == 1) {
                                    Toast.makeText(TambahTeman.this,"Sukses simpan data",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(TambahTeman.this, "gagal",Toast.LENGTH_SHORT).show();
                                }
                            }catch (JarException e){
                                e.printStackTrace();
                            }}
                        },new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Log.e(TAG,"error : "+error.getMessage());
                                Toast.makeText(TambahTeman.this,"Gagal simpan data",Toast.LENGTH_SHORT).show();
                            }
                    }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<>();

                            params.put("nama", nm);
                            params.put("telpon",tlp);

                            return params;
                        }
                    };
                    requestQueue.add(strReq);
                }

            }
        });

    }
}


