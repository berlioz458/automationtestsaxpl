
package orderservice.contractTemplateController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ContractType {

    @SerializedName("#ref")
    private RefContractTemplate mRefContractTemplate;

    public RefContractTemplate getRef() {
        return mRefContractTemplate;
    }

    public void setRef(RefContractTemplate ref) {
        mRefContractTemplate = ref;
    }

}
