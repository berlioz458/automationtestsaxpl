
package orderservice.loyaltyPolicyController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LoyaltyPolicyRequest {

    @SerializedName("LoyaltyPolicy")
    private LoyaltyPolicy mLoyaltyPolicy;

    public LoyaltyPolicy getLoyaltyPolicy() {
        return mLoyaltyPolicy;
    }

    public void setLoyaltyPolicy(LoyaltyPolicy loyaltyPolicy) {
        mLoyaltyPolicy = loyaltyPolicy;
    }

}
