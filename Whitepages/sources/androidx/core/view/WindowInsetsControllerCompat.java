package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.collection.SimpleArrayMap;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.b;

public final class WindowInsetsControllerCompat {
    private final Impl mImpl;

    public WindowInsetsControllerCompat(Window window, View view) {
        SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat = new SoftwareKeyboardControllerCompat(view);
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(window, this, softwareKeyboardControllerCompat);
        } else {
            this.mImpl = new Impl26(window, softwareKeyboardControllerCompat);
        }
    }

    public void show(int i) {
        this.mImpl.show(i);
    }

    public void hide(int i) {
        this.mImpl.hide(i);
    }

    public void setAppearanceLightStatusBars(boolean z) {
        this.mImpl.setAppearanceLightStatusBars(z);
    }

    public void setAppearanceLightNavigationBars(boolean z) {
        this.mImpl.setAppearanceLightNavigationBars(z);
    }

    public void setSystemBarsBehavior(int i) {
        this.mImpl.setSystemBarsBehavior(i);
    }

    private static class Impl {
        /* access modifiers changed from: package-private */
        public abstract void hide(int i);

        public abstract void setAppearanceLightNavigationBars(boolean z);

        public abstract void setAppearanceLightStatusBars(boolean z);

        /* access modifiers changed from: package-private */
        public abstract void setSystemBarsBehavior(int i);

        /* access modifiers changed from: package-private */
        public abstract void show(int i);

        Impl() {
        }
    }

    private static class Impl20 extends Impl {
        private final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected final Window mWindow;

        Impl20(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mWindow = window;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }

        /* access modifiers changed from: package-private */
        public void show(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    showForType(i2);
                }
            }
        }

        private void showForType(int i) {
            if (i == 1) {
                unsetSystemUiFlag(4);
                unsetWindowFlag(1024);
            } else if (i == 2) {
                unsetSystemUiFlag(2);
            } else if (i == 8) {
                this.mSoftwareKeyboardControllerCompat.show();
            }
        }

        /* access modifiers changed from: package-private */
        public void hide(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    hideForType(i2);
                }
            }
        }

        private void hideForType(int i) {
            if (i == 1) {
                setSystemUiFlag(4);
            } else if (i == 2) {
                setSystemUiFlag(2);
            } else if (i == 8) {
                this.mSoftwareKeyboardControllerCompat.hide();
            }
        }

        /* access modifiers changed from: protected */
        public void setSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void unsetSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void setWindowFlag(int i) {
            this.mWindow.addFlags(i);
        }

        /* access modifiers changed from: protected */
        public void unsetWindowFlag(int i) {
            this.mWindow.clearFlags(i);
        }

        /* access modifiers changed from: package-private */
        public void setSystemBarsBehavior(int i) {
            if (i == 0) {
                unsetSystemUiFlag(6144);
            } else if (i == 1) {
                unsetSystemUiFlag(b.v);
                setSystemUiFlag(b.u);
            } else if (i == 2) {
                unsetSystemUiFlag(b.u);
                setSystemUiFlag(b.v);
            }
        }
    }

    private static class Impl23 extends Impl20 {
        Impl23(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        public void setAppearanceLightStatusBars(boolean z) {
            if (z) {
                unsetWindowFlag(67108864);
                setWindowFlag(ExploreByTouchHelper.INVALID_ID);
                setSystemUiFlag(UserMetadata.MAX_INTERNAL_KEY_SIZE);
                return;
            }
            unsetSystemUiFlag(UserMetadata.MAX_INTERNAL_KEY_SIZE);
        }
    }

    private static class Impl26 extends Impl23 {
        Impl26(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        public void setAppearanceLightNavigationBars(boolean z) {
            if (z) {
                unsetWindowFlag(134217728);
                setWindowFlag(ExploreByTouchHelper.INVALID_ID);
                setSystemUiFlag(16);
                return;
            }
            unsetSystemUiFlag(16);
        }
    }

    private static class Impl30 extends Impl {
        final WindowInsetsControllerCompat mCompatController;
        final WindowInsetsController mInsetsController;
        private final SimpleArrayMap mListeners;
        final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected Window mWindow;

        Impl30(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat, softwareKeyboardControllerCompat);
            this.mWindow = window;
        }

        Impl30(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mListeners = new SimpleArrayMap();
            this.mInsetsController = windowInsetsController;
            this.mCompatController = windowInsetsControllerCompat;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }

        /* access modifiers changed from: package-private */
        public void show(int i) {
            if ((i & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.show();
            }
            this.mInsetsController.show(i & -9);
        }

        /* access modifiers changed from: package-private */
        public void hide(int i) {
            if ((i & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.hide();
            }
            this.mInsetsController.hide(i & -9);
        }

        public void setAppearanceLightStatusBars(boolean z) {
            if (z) {
                if (this.mWindow != null) {
                    setSystemUiFlag(UserMetadata.MAX_INTERNAL_KEY_SIZE);
                }
                this.mInsetsController.setSystemBarsAppearance(8, 8);
                return;
            }
            if (this.mWindow != null) {
                unsetSystemUiFlag(UserMetadata.MAX_INTERNAL_KEY_SIZE);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 8);
        }

        public void setAppearanceLightNavigationBars(boolean z) {
            if (z) {
                if (this.mWindow != null) {
                    setSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(16, 16);
                return;
            }
            if (this.mWindow != null) {
                unsetSystemUiFlag(16);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 16);
        }

        /* access modifiers changed from: package-private */
        public void setSystemBarsBehavior(int i) {
            this.mInsetsController.setSystemBarsBehavior(i);
        }

        /* access modifiers changed from: protected */
        public void unsetSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void setSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }
    }
}
