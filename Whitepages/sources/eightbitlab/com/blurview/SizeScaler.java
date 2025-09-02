package eightbitlab.com.blurview;

public class SizeScaler {
    private final float scaleFactor;

    public SizeScaler(float f) {
        this.scaleFactor = f;
    }

    /* access modifiers changed from: package-private */
    public Size scale(int i, int i2) {
        float f = (float) i;
        int roundSize = roundSize(downscaleSize(f));
        float f2 = f / ((float) roundSize);
        return new Size(roundSize, (int) Math.ceil((double) (((float) i2) / f2)), f2);
    }

    /* access modifiers changed from: package-private */
    public boolean isZeroSized(int i, int i2) {
        return downscaleSize((float) i2) == 0 || downscaleSize((float) i) == 0;
    }

    private int roundSize(int i) {
        int i2 = i % 64;
        return i2 == 0 ? i : (i - i2) + 64;
    }

    private int downscaleSize(float f) {
        return (int) Math.ceil((double) (f / this.scaleFactor));
    }

    static class Size {
        final int height;
        final float scaleFactor;
        final int width;

        Size(int i, int i2, float f) {
            this.width = i;
            this.height = i2;
            this.scaleFactor = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Size size = (Size) obj;
            if (this.width != size.width || this.height != size.height) {
                return false;
            }
            if (Float.compare(size.scaleFactor, this.scaleFactor) == 0) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = ((this.width * 31) + this.height) * 31;
            float f = this.scaleFactor;
            return i + (f != 0.0f ? Float.floatToIntBits(f) : 0);
        }

        public String toString() {
            return "Size{width=" + this.width + ", height=" + this.height + ", scaleFactor=" + this.scaleFactor + '}';
        }
    }
}
