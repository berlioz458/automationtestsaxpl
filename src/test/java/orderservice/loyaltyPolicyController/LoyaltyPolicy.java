
package orderservice.loyaltyPolicyController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LoyaltyPolicy {

    @SerializedName("contractPersonalDiscountLoyaltySystemThreshold")
    private Long mContractPersonalDiscountLoyaltySystemThreshold;
    @SerializedName("firstParty")
    private FirstPartyLoyalPolicy mFirstPartyLoyalPolicy;
    @SerializedName("name")
    private String mName;
    @SerializedName("selfAndPersonalDiscountLimit")
    private Long mSelfAndPersonalDiscountLimit;
    @SerializedName("selfOrderDiscountPercent")
    private Long mSelfOrderDiscountPercent;

    public Long getContractPersonalDiscountLoyaltySystemThreshold() {
        return mContractPersonalDiscountLoyaltySystemThreshold;
    }

    public void setContractPersonalDiscountLoyaltySystemThreshold(Long contractPersonalDiscountLoyaltySystemThreshold) {
        mContractPersonalDiscountLoyaltySystemThreshold = contractPersonalDiscountLoyaltySystemThreshold;
    }

    public FirstPartyLoyalPolicy getFirstParty() {
        return mFirstPartyLoyalPolicy;
    }

    public void setFirstParty(FirstPartyLoyalPolicy firstPartyLoyalPolicy) {
        mFirstPartyLoyalPolicy = firstPartyLoyalPolicy;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getSelfAndPersonalDiscountLimit() {
        return mSelfAndPersonalDiscountLimit;
    }

    public void setSelfAndPersonalDiscountLimit(Long selfAndPersonalDiscountLimit) {
        mSelfAndPersonalDiscountLimit = selfAndPersonalDiscountLimit;
    }

    public Long getSelfOrderDiscountPercent() {
        return mSelfOrderDiscountPercent;
    }

    public void setSelfOrderDiscountPercent(Long selfOrderDiscountPercent) {
        mSelfOrderDiscountPercent = selfOrderDiscountPercent;
    }

}
