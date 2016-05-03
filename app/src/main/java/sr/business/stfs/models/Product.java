package sr.business.stfs.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class Product {
    private Long p_id;
    private String p_product;
    private String p_description;
    private BigDecimal p_price;
    private sr.business.stfs.models.Currency c_id;
    private Stfsuser s_id;
    private Category cat_id;
    private Integer p_stock;
    private ProdStat ps_id;
    private Date p_post_date;
    private String p_actief;
    private Date p_end_date;

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getP_product() {
        return p_product;
    }

    public void setP_product(String p_product) {
        this.p_product = p_product;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public BigDecimal getP_price() {
        return p_price;
    }

    public void setP_price(BigDecimal p_price) {
        this.p_price = p_price;
    }

    public Currency getC_id() {
        return c_id;
    }

    public void setC_id(Currency c_id) {
        this.c_id = c_id;
    }

    public Stfsuser getS_id() {
        return s_id;
    }

    public void setS_id(Stfsuser s_id) {
        this.s_id = s_id;
    }

    public Category getCat_id() {
        return cat_id;
    }

    public void setCat_id(Category cat_id) {
        this.cat_id = cat_id;
    }

    public Integer getP_stock() {
        return p_stock;
    }

    public void setP_stock(Integer p_stock) {
        this.p_stock = p_stock;
    }

    public ProdStat getPs_id() {
        return ps_id;
    }

    public void setPs_id(ProdStat ps_id) {
        this.ps_id = ps_id;
    }

    public Date getP_post_date() {
        return p_post_date;
    }

    public void setP_post_date(Date p_post_date) {
        this.p_post_date = p_post_date;
    }

    public String getP_actief() {
        return p_actief;
    }

    public void setP_actief(String p_actief) {
        this.p_actief = p_actief;
    }

    public Date getP_end_date() {
        return p_end_date;
    }

    public void setP_end_date(Date p_end_date) {
        this.p_end_date = p_end_date;
    }
}
