package io.branch.referral;

import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class BranchLinkData extends JSONObject {
    private String alias;
    private String campaign;
    private String channel;
    private int duration;
    private String feature;
    private JSONObject params;
    private String stage;
    private Collection tags;
    private int type;

    public void putTags(Collection collection) {
        if (collection != null) {
            this.tags = collection;
            JSONArray jSONArray = new JSONArray();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                jSONArray.put((String) it.next());
            }
            put(Defines$LinkParam.Tags.getKey(), jSONArray);
        }
    }

    public Collection getTags() {
        return this.tags;
    }

    public void putAlias(String str) {
        if (str != null) {
            this.alias = str;
            put(Defines$LinkParam.Alias.getKey(), str);
        }
    }

    public String getAlias() {
        return this.alias;
    }

    public void putType(int i) {
        if (i != 0) {
            this.type = i;
            put(Defines$LinkParam.Type.getKey(), i);
        }
    }

    public int getType() {
        return this.type;
    }

    public void putDuration(int i) {
        if (i > 0) {
            this.duration = i;
            put(Defines$LinkParam.Duration.getKey(), i);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void putChannel(String str) {
        if (str != null) {
            this.channel = str;
            put(Defines$LinkParam.Channel.getKey(), str);
        }
    }

    public String getChannel() {
        return this.channel;
    }

    public void putFeature(String str) {
        if (str != null) {
            this.feature = str;
            put(Defines$LinkParam.Feature.getKey(), str);
        }
    }

    public String getFeature() {
        return this.feature;
    }

    public void putStage(String str) {
        if (str != null) {
            this.stage = str;
            put(Defines$LinkParam.Stage.getKey(), str);
        }
    }

    public String getStage() {
        return this.stage;
    }

    public void putCampaign(String str) {
        if (str != null) {
            this.campaign = str;
            put(Defines$LinkParam.Campaign.getKey(), str);
        }
    }

    public String getCampaign() {
        return this.campaign;
    }

    public void putParams(JSONObject jSONObject) {
        this.params = jSONObject;
        put(Defines$LinkParam.Data.getKey(), jSONObject);
    }

    public JSONObject getParams() {
        return this.params;
    }

    public void putSource() {
        put("source", Defines$Jsonkey.URLSource.getKey());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BranchLinkData branchLinkData = (BranchLinkData) obj;
        String str = this.alias;
        if (str == null) {
            if (branchLinkData.alias != null) {
                return false;
            }
        } else if (!str.equals(branchLinkData.alias)) {
            return false;
        }
        String str2 = this.channel;
        if (str2 == null) {
            if (branchLinkData.channel != null) {
                return false;
            }
        } else if (!str2.equals(branchLinkData.channel)) {
            return false;
        }
        String str3 = this.feature;
        if (str3 == null) {
            if (branchLinkData.feature != null) {
                return false;
            }
        } else if (!str3.equals(branchLinkData.feature)) {
            return false;
        }
        String str4 = this.stage;
        if (str4 == null) {
            if (branchLinkData.stage != null) {
                return false;
            }
        } else if (!str4.equals(branchLinkData.stage)) {
            return false;
        }
        String str5 = this.campaign;
        if (str5 == null) {
            if (branchLinkData.campaign != null) {
                return false;
            }
        } else if (!str5.equals(branchLinkData.campaign)) {
            return false;
        }
        if (this.type != branchLinkData.type || this.duration != branchLinkData.duration) {
            return false;
        }
        Collection collection = this.tags;
        String str6 = null;
        if (collection != null) {
            String obj2 = collection.toString();
            Collection collection2 = branchLinkData.tags;
            if (!obj2.equals(collection2 != null ? collection2.toString() : null)) {
                return false;
            }
        } else if (branchLinkData.tags != null) {
            return false;
        }
        JSONObject jSONObject = this.params;
        if (jSONObject != null) {
            String jSONObject2 = jSONObject.toString();
            JSONObject jSONObject3 = branchLinkData.params;
            if (jSONObject3 != null) {
                str6 = jSONObject3.toString();
            }
            if (!jSONObject2.equals(str6)) {
                return false;
            }
        } else if (branchLinkData.params != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (this.type + 19) * 19;
        String str = this.alias;
        int i2 = 0;
        int hashCode = (i + (str == null ? 0 : str.toLowerCase().hashCode())) * 19;
        String str2 = this.channel;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.toLowerCase().hashCode())) * 19;
        String str3 = this.feature;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.toLowerCase().hashCode())) * 19;
        String str4 = this.stage;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.toLowerCase().hashCode())) * 19;
        String str5 = this.campaign;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.toLowerCase().hashCode())) * 19;
        JSONObject jSONObject = this.params;
        if (jSONObject != null) {
            i2 = jSONObject.toString().toLowerCase().hashCode();
        }
        int i3 = ((hashCode5 + i2) * 19) + this.duration;
        Collection<String> collection = this.tags;
        if (collection != null) {
            for (String lowerCase : collection) {
                i3 = (i3 * 19) + lowerCase.toLowerCase().hashCode();
            }
        }
        return i3;
    }
}
