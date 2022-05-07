
package orderservice.documentController;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Document {

    @SerializedName("changedAt")
    private Object mChangedAt;
    @SerializedName("changedByParty")
    private Object mChangedByParty;
    @SerializedName("changedByUser")
    private Object mChangedByUser;
    @SerializedName("createdAt")
    private Object mCreatedAt;
    @SerializedName("createdByParty")
    private String mCreatedByParty;
    @SerializedName("createdByUser")
    private String mCreatedByUser;
    @SerializedName("guid")
    private Object mGuid;
    @SerializedName("id")
    private Object mId;
    @SerializedName("jsonBody")
    private String mJsonBody;
    @SerializedName("name")
    private String mName;
    @SerializedName("ownerAgentId")
    private Long mOwnerAgentId;
    @SerializedName("parentObjectId")
    private Object mParentObjectId;
    @SerializedName("parentObjectType")
    private String mParentObjectType;
    @SerializedName("referenceId")
    private Object mReferenceId;
    @SerializedName("type")
    private String mType;

    public Object getChangedAt() {
        return mChangedAt;
    }

    public void setChangedAt(Object changedAt) {
        mChangedAt = changedAt;
    }

    public Object getChangedByParty() {
        return mChangedByParty;
    }

    public void setChangedByParty(Object changedByParty) {
        mChangedByParty = changedByParty;
    }

    public Object getChangedByUser() {
        return mChangedByUser;
    }

    public void setChangedByUser(Object changedByUser) {
        mChangedByUser = changedByUser;
    }

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedByParty() {
        return mCreatedByParty;
    }

    public void setCreatedByParty(String createdByParty) {
        mCreatedByParty = createdByParty;
    }

    public String getCreatedByUser() {
        return mCreatedByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        mCreatedByUser = createdByUser;
    }

    public Object getGuid() {
        return mGuid;
    }

    public void setGuid(Object guid) {
        mGuid = guid;
    }

    public Object getId() {
        return mId;
    }

    public void setId(Object id) {
        mId = id;
    }

    public String getJsonBody() {
        return mJsonBody;
    }

    public void setJsonBody(String jsonBody) {
        mJsonBody = jsonBody;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getOwnerAgentId() {
        return mOwnerAgentId;
    }

    public void setOwnerAgentId(Long ownerAgentId) {
        mOwnerAgentId = ownerAgentId;
    }

    public Object getParentObjectId() {
        return mParentObjectId;
    }

    public void setParentObjectId(Object parentObjectId) {
        mParentObjectId = parentObjectId;
    }

    public String getParentObjectType() {
        return mParentObjectType;
    }

    public void setParentObjectType(String parentObjectType) {
        mParentObjectType = parentObjectType;
    }

    public Object getReferenceId() {
        return mReferenceId;
    }

    public void setReferenceId(Object referenceId) {
        mReferenceId = referenceId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
