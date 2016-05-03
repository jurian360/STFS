package sr.business.stfs.models;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class ProdStat {
    private Long ps_id;
    private String ps_status;
    private String ps_description;
    private String ps_active;

    public Long getPs_id() {
        return ps_id;
    }

    public void setPs_id(Long ps_id) {
        this.ps_id = ps_id;
    }

    public String getPs_status() {
        return ps_status;
    }

    public void setPs_status(String ps_status) {
        this.ps_status = ps_status;
    }

    public String getPs_description() {
        return ps_description;
    }

    public void setPs_description(String ps_description) {
        this.ps_description = ps_description;
    }

    public String getPs_active() {
        return ps_active;
    }

    public void setPs_active(String ps_active) {
        this.ps_active = ps_active;
    }
}
