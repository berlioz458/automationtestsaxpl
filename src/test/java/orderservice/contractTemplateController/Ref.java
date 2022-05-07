
package orderservice.contractTemplateController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Ref {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private Object mName;
    @SerializedName("type")
    private String mType;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getName() {
        return mName;
    }

    public void setName(Object name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
