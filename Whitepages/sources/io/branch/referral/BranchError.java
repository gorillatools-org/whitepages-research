package io.branch.referral;

public class BranchError {
    int errorCode_ = -122;
    String errorMessage_ = "";

    public String getMessage() {
        return this.errorMessage_;
    }

    public int getErrorCode() {
        return this.errorCode_;
    }

    public String toString() {
        return getMessage();
    }

    public BranchError(String str, int i) {
        this.errorMessage_ = str + initErrorCodeAndGetLocalisedMessage(i);
    }

    private String initErrorCodeAndGetLocalisedMessage(int i) {
        if (i == -113) {
            this.errorCode_ = -113;
            return " Check network connectivity or DNS settings.";
        } else if (i == -114) {
            this.errorCode_ = -114;
            return " Branch API Error: Please enter your branch_key in your project's manifest file first.";
        } else if (i == -104) {
            this.errorCode_ = -104;
            return " Did you forget to call init? Make sure you init the session before making Branch calls.";
        } else if (i == -101) {
            this.errorCode_ = -101;
            return " Unable to initialize Branch. Check network connectivity or that your branch key is valid.";
        } else if (i == -102) {
            this.errorCode_ = -102;
            return " Please add 'android.permission.INTERNET' in your applications manifest file.";
        } else if (i == -105) {
            this.errorCode_ = -105;
            return " Unable to create a URL with that alias. If you want to reuse the alias, make sure to submit the same properties for all arguments and that the user is the same owner.";
        } else if (i == -108) {
            this.errorCode_ = -108;
            return "BranchApp class can be used only with API level 14 or above. Please make sure your minimum API level supported is 14. If you wish to use API level below 14 consider calling getInstance(Context) instead.";
        } else if (i == -109) {
            this.errorCode_ = -109;
            return "Branch instance is not created. Make  sure your Application class is an instance of BranchLikedApp.";
        } else if (i == -110) {
            this.errorCode_ = -110;
            return " Unable create share options. Couldn't find applications on device to share the link.";
        } else if (i == -111) {
            this.errorCode_ = -111;
            return " Request to Branch server timed out. Please check your internet connectivity";
        } else if (i == -117) {
            this.errorCode_ = -117;
            return " Tracking is disabled. Requested operation cannot be completed when tracking is disabled";
        } else if (i == -118) {
            this.errorCode_ = -118;
            return " Session initialization already happened. To force a new session, set intent extra, \"branch_force_new_session\", to true.";
        } else if (i >= 500 || i == -112) {
            this.errorCode_ = -112;
            return " Unable to reach the Branch servers, please try again shortly.";
        } else if (i == 409 || i == -115) {
            this.errorCode_ = -115;
            return " A resource with this identifier already exists.";
        } else if (i >= 400 || i == -116) {
            this.errorCode_ = -116;
            return " The request was invalid.";
        } else if (i == -119) {
            this.errorCode_ = -119;
            return "Intra-app linking (i.e. session reinitialization) requires an intent flag, \"branch_force_new_session\".";
        } else if (i == -120) {
            this.errorCode_ = -120;
            return " Task exceeded timeout.";
        } else {
            this.errorCode_ = -122;
            return " See exception message or logs for more details. ";
        }
    }
}
