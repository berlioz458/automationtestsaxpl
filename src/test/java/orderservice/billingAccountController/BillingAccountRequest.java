
package orderservice.billingAccountController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BillingAccountRequest {

    @SerializedName("BillingAccount")
    private BillingAccount mBillingAccount;

    public BillingAccount getBillingAccount() {
        return mBillingAccount;
    }

    public void setBillingAccount(BillingAccount billingAccount) {
        mBillingAccount = billingAccount;
    }

}
