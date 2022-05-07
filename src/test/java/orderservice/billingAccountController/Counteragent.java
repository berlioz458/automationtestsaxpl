
package orderservice.billingAccountController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Counteragent {

    @SerializedName("#ref")
    private RefBillingAccount mRefBillingAccount;

    public RefBillingAccount getRef() {
        return mRefBillingAccount;
    }

    public void setRef(RefBillingAccount refBillingAccount) {
        mRefBillingAccount = refBillingAccount;
    }

}
