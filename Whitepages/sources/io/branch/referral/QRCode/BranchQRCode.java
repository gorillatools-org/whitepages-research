package io.branch.referral.QRCode;

import android.content.Context;
import android.util.Base64;
import com.facebook.react.views.image.ReactImageView;
import com.google.firebase.perf.util.Constants;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import io.branch.referral.BranchQRCodeCache;
import io.branch.referral.Defines$Jsonkey;
import io.branch.referral.Defines$LinkParam;
import io.branch.referral.Defines$RequestPath;
import io.branch.referral.PrefHelper;
import io.branch.referral.ServerResponse;
import io.branch.referral.util.LinkProperties;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchQRCode {
    private String backgroundColor_ = null;
    private String centerLogo_ = null;
    private String codeColor_ = null;
    private BranchImageFormat imageFormat_ = null;
    private Integer margin_ = null;
    private Integer width_ = null;

    public enum BranchImageFormat {
        JPEG,
        PNG
    }

    public interface BranchQRCodeDataHandler {
        void onFailure(Exception exc);

        void onSuccess(byte[] bArr);
    }

    public interface BranchQRCodeRequestHandler {
        void onDataReceived(ServerResponse serverResponse);

        void onFailure(Exception exc);
    }

    public BranchQRCode setCodeColor(String str) {
        this.codeColor_ = str;
        return this;
    }

    public BranchQRCode setBackgroundColor(String str) {
        this.backgroundColor_ = str;
        return this;
    }

    public BranchQRCode setCenterLogo(String str) {
        this.centerLogo_ = str;
        return this;
    }

    public BranchQRCode setWidth(Integer num) {
        if (num.intValue() > 2000) {
            BranchLogger.v("Width was reduced to the maximum of 2000.");
            this.width_ = Integer.valueOf(Constants.MAX_URL_LENGTH);
        } else if (num.intValue() < 300) {
            BranchLogger.v("Width was increased to the minimum of 300.");
            this.width_ = Integer.valueOf(ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS);
        } else {
            this.width_ = num;
        }
        return this;
    }

    public BranchQRCode setMargin(Integer num) {
        if (num.intValue() > 20) {
            BranchLogger.v("Margin was reduced to the maximum of 20.");
            this.margin_ = 20;
        } else if (num.intValue() < 1) {
            BranchLogger.v("Margin was increased to the minimum of 1.");
            this.margin_ = 1;
        } else {
            this.margin_ = num;
        }
        return this;
    }

    public BranchQRCode setImageFormat(BranchImageFormat branchImageFormat) {
        this.imageFormat_ = branchImageFormat;
        return this;
    }

    public void getQRCodeAsData(Context context, BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, final BranchQRCodeDataHandler branchQRCodeDataHandler) {
        HashMap hashMap = new HashMap();
        if (this.codeColor_ != null) {
            hashMap.put(Defines$Jsonkey.CodeColor.getKey(), this.codeColor_);
        }
        if (this.backgroundColor_ != null) {
            hashMap.put(Defines$Jsonkey.BackgroundColor.getKey(), this.backgroundColor_);
        }
        if (this.width_ != null) {
            hashMap.put(Defines$Jsonkey.Width.getKey(), this.width_);
        }
        if (this.margin_ != null) {
            hashMap.put(Defines$Jsonkey.Margin.getKey(), this.margin_);
        }
        if (this.imageFormat_ == BranchImageFormat.JPEG) {
            hashMap.put(Defines$Jsonkey.ImageFormat.getKey(), "JPEG");
        } else {
            hashMap.put(Defines$Jsonkey.ImageFormat.getKey(), "PNG");
        }
        if (this.centerLogo_ != null) {
            hashMap.put(Defines$Jsonkey.CenterLogo.getKey(), this.centerLogo_);
        }
        final HashMap hashMap2 = new HashMap();
        if (linkProperties.getChannel() != null) {
            hashMap2.put(Defines$LinkParam.Channel.getKey(), linkProperties.getChannel());
        }
        if (linkProperties.getFeature() != null) {
            hashMap2.put(Defines$LinkParam.Feature.getKey(), linkProperties.getFeature());
        }
        if (linkProperties.getCampaign() != null) {
            hashMap2.put(Defines$LinkParam.Campaign.getKey(), linkProperties.getCampaign());
        }
        if (linkProperties.getStage() != null) {
            hashMap2.put(Defines$LinkParam.Stage.getKey(), linkProperties.getStage());
        }
        if (linkProperties.getTags() != null) {
            hashMap2.put(Defines$LinkParam.Tags.getKey(), linkProperties.getTags());
        }
        hashMap2.put(Defines$Jsonkey.QRCodeSettings.getKey(), hashMap);
        hashMap2.put(Defines$Jsonkey.QRCodeData.getKey(), branchUniversalObject.convertToJson());
        hashMap2.put(Defines$Jsonkey.QRCodeBranchKey.getKey(), PrefHelper.getInstance(context).getBranchKey());
        JSONObject jSONObject = new JSONObject(hashMap2);
        byte[] checkQRCodeCache = BranchQRCodeCache.getInstance().checkQRCodeCache(jSONObject);
        if (checkQRCodeCache != null) {
            branchQRCodeDataHandler.onSuccess(checkQRCodeCache);
            return;
        }
        Branch.getInstance().requestQueue_.handleNewRequest(new ServerRequestCreateQRCode(Defines$RequestPath.QRCode, jSONObject, context, new BranchQRCodeRequestHandler() {
            public void onDataReceived(ServerResponse serverResponse) {
                try {
                    byte[] decode = Base64.decode(serverResponse.getObject().getString(Defines$Jsonkey.QRCodeResponseString.getKey()), 0);
                    BranchQRCodeCache.getInstance().addQRCodeToCache(new JSONObject(hashMap2), decode);
                    branchQRCodeDataHandler.onSuccess(decode);
                } catch (JSONException e) {
                    e.printStackTrace();
                    branchQRCodeDataHandler.onFailure(e);
                }
            }

            public void onFailure(Exception exc) {
                branchQRCodeDataHandler.onFailure(exc);
            }
        }));
    }
}
