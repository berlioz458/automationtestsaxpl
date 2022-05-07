
package orderservice.contractTemplateController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ContractTemplateRequest {

    @SerializedName("ContractTemplate")
    private ContractTemplate mContractTemplate;

    public ContractTemplate getContractTemplate() {
        return mContractTemplate;
    }

    public void setContractTemplate(ContractTemplate contractTemplate) {
        mContractTemplate = contractTemplate;
    }

}
