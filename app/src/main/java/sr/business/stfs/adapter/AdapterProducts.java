package sr.business.stfs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sr.business.stfs.R;
import sr.business.stfs.models.Category;
import sr.business.stfs.models.Currency;
import sr.business.stfs.models.Product;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolderProducts> {

    private LayoutInflater inflater;
    private ArrayList<Product> productList = new ArrayList<>();

    public AdapterProducts(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setProductList(ArrayList<Product> products){
        this.productList = products;
        notifyItemRangeChanged(0,products.size());
    }

    public void createArrayList(){
        ArrayList<Product> productList = new ArrayList<>();
        for(int i = 0;i<8;i++) {
            Product product = new Product();
            product.setP_description("Deze description is van product" + i);
            java.math.BigDecimal bd = new java.math.BigDecimal(String.valueOf(i + 5000));
            product.setP_price( bd );
            Currency currency = new Currency();
            currency.setC_currency("SRD" + i);
            product.setC_id(currency);
            product.setP_product("Naam " + i);
            Category category = new Category();
            category.setCat_category("LOL" + i);
            product.setCat_id(category);
            productList.add(product);
        }
        setProductList(productList);

    }

    @Override
    public ViewHolderProducts onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_products,parent,false);
        ViewHolderProducts vh = new ViewHolderProducts(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderProducts h, int position) {
        Product selectedProduct = productList.get(position);
        h.prodAmount.setText(selectedProduct.getP_price().toString());
        h.prodCategory.setText(selectedProduct.getCat_id().getCat_category());
        h.prodName.setText(selectedProduct.getP_product());
        h.prodCurrency.setText(selectedProduct.getC_id().getC_currency());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderProducts extends RecyclerView.ViewHolder{

        TextView prodCategory, prodName, prodCurrency, prodAmount;

        public ViewHolderProducts(View itemView) {
            super(itemView);
            prodCategory = (TextView) itemView.findViewById(R.id.prod_category);
            prodName = (TextView) itemView.findViewById(R.id.prod_name);
            prodCurrency = (TextView) itemView.findViewById(R.id.prod_currency);
            prodAmount = (TextView) itemView.findViewById(R.id.prod_price);
        }
    }
}
