
package orderservice.loyaltyPolicyController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class FirstPartyLoyalPolicy {

    @SerializedName("#ref")
    private RefLoyalPolicy mRefLoyalPolicy;

    public RefLoyalPolicy getRef() {
        return mRefLoyalPolicy;
    }

    public void setRef(RefLoyalPolicy refLoyalPolicy) {
        mRefLoyalPolicy = refLoyalPolicy;
    }

}
