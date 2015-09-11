package masterhoracio.com.calculatebills;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar sb;
    private TextView porc;
    private EditText total;
    private double currenttotal;
    private double propina;
    private EditText propina10;
    private EditText propina15;
    private EditText propina20;
    private EditText totalpropina10;
    private EditText totalpropina15;
    private EditText totalpropina20;
    private EditText propiacustom;
    private EditText totalcustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.seekBarcustom);
        porc = (TextView) findViewById(R.id.textporcentaje);
        total = (EditText) findViewById(R.id.editTotal);
        propina10 = (EditText) findViewById(R.id.edit10);
        propina15 = (EditText) findViewById(R.id.edit15);
        propina20 = (EditText) findViewById(R.id.edit20);
        totalpropina10 = (EditText) findViewById(R.id.edit10Total);
        totalpropina15 = (EditText) findViewById(R.id.edit15Total);
        totalpropina20 = (EditText) findViewById(R.id.edit20Total);

        propiacustom = (EditText) findViewById(R.id.editTipCuscom);
        totalcustom = (EditText) findViewById(R.id.editTotalcustom);

        total.addTextChangedListener(totalWatcher);


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porc.setText(progress+"%");
                propina = progress;
                updatecustom();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private TextWatcher totalWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{
                currenttotal = Double.parseDouble(s.toString());
            }catch(NumberFormatException e){
                currenttotal = 0.0;
            }
            updatestandard();
            updatecustom();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void updatestandard()
    {
        propina10.setText(String.valueOf(Math.rint(currenttotal*0.1)));
        propina15.setText(String.valueOf(Math.rint(currenttotal*0.15)));
        propina20.setText(String.valueOf(Math.rint(currenttotal*0.20)));
        totalpropina10.setText(String.valueOf(Math.rint(currenttotal*1.1)));
        totalpropina15.setText(String.valueOf(Math.rint(currenttotal*1.15)));
        totalpropina20.setText(String.valueOf(Math.rint(currenttotal*1.20)));
    }

    public void updatecustom()
    {
        propiacustom.setText(String.valueOf(Math.rint(currenttotal*(propina/100))));
        totalcustom.setText(String.valueOf(Math.rint(currenttotal*(1+(propina/100)))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
