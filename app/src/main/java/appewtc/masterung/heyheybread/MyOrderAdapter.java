package appewtc.masterung.heyheybread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 1/19/16 AD.
 */
public class MyOrderAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] noStrings, nameOrderStrings,
            itemStrings, priceStrings, amountStrings;

    public MyOrderAdapter(Context objContext, String[] noStrings, String[] nameOrderStrings, String[] itemStrings, String[] priceStrings, String[] amountStrings) {
        this.objContext = objContext;
        this.noStrings = noStrings;
        this.nameOrderStrings = nameOrderStrings;
        this.itemStrings = itemStrings;
        this.priceStrings = priceStrings;
        this.amountStrings = amountStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return nameOrderStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.my_order_listview, viewGroup, false);

        TextView noTextView = (TextView) objView1.findViewById(R.id.textView23);
        noTextView.setText(noStrings[i]);

        TextView nameOrderTextView = (TextView) objView1.findViewById(R.id.textView24);
        nameOrderTextView.setText(nameOrderStrings[i]);

        TextView itemTextView = (TextView) objView1.findViewById(R.id.textView25);
        itemTextView.setText(itemStrings[i]);

        TextView priceTextView = (TextView) objView1.findViewById(R.id.textView26);
        priceTextView.setText(priceStrings[i]);

        TextView amountTextView = (TextView) objView1.findViewById(R.id.textView27);
        amountTextView.setText(amountStrings[i]);

        return objView1;
    }
}   // Main Class
