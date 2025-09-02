package com.horcrux.svg;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.horcrux.svg.RenderableViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class SvgPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private Map<String, ModuleSpec> mViewManagers;

    private Map<String, ModuleSpec> getViewManagersMap(ReactApplicationContext reactApplicationContext) {
        if (this.mViewManagers == null) {
            HashMap newHashMap = MapBuilder.newHashMap();
            newHashMap.put(RenderableViewManager.GroupViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.GroupViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.PathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.PathViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.CircleViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.CircleViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.EllipseViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.EllipseViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.LineViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.LineViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.RectViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.RectViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.TextViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.TextViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.TSpanViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.TSpanViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.TextPathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.TextPathViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.ImageViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.ImageViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.ClipPathViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.ClipPathViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.DefsViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.DefsViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.UseViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.UseViewManager();
                }
            }));
            newHashMap.put(RenderableViewManager.SymbolManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.SymbolManager();
                }
            }));
            newHashMap.put(RenderableViewManager.LinearGradientManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.LinearGradientManager();
                }
            }));
            newHashMap.put(RenderableViewManager.RadialGradientManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.RadialGradientManager();
                }
            }));
            newHashMap.put(RenderableViewManager.PatternManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.PatternManager();
                }
            }));
            newHashMap.put(RenderableViewManager.MaskManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.MaskManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FilterManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FilterManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeBlendManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeBlendManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeColorMatrixManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeColorMatrixManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeCompositeManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeCompositeManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeFloodManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeFloodManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeGaussianBlurManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeGaussianBlurManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeMergeManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeMergeManager();
                }
            }));
            newHashMap.put(RenderableViewManager.FeOffsetManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.FeOffsetManager();
                }
            }));
            newHashMap.put(RenderableViewManager.ForeignObjectManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.ForeignObjectManager();
                }
            }));
            newHashMap.put(RenderableViewManager.MarkerManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new RenderableViewManager.MarkerManager();
                }
            }));
            newHashMap.put(SvgViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() {
                public NativeModule get() {
                    return new SvgViewManager();
                }
            }));
            this.mViewManagers = newHashMap;
        }
        return this.mViewManagers;
    }

    public List<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap(reactApplicationContext).keySet());
    }

    /* access modifiers changed from: protected */
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap(reactApplicationContext).values());
    }

    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap(reactApplicationContext).get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        if (str.equals("RNSVGRenderableModule")) {
            return new RNSVGRenderableManager(reactApplicationContext);
        }
        if (!str.equals("RNSVGSvgViewModule")) {
            return null;
        }
        return new SvgViewModule(reactApplicationContext);
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.horcrux.svg.SvgPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() {
                public Map getReactModuleInfos() {
                    HashMap hashMap = new HashMap();
                    Class[] clsArr = {SvgViewModule.class, RNSVGRenderableManager.class};
                    for (int i = 0; i < 2; i++) {
                        Class cls = clsArr[i];
                        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                        hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), true));
                    }
                    return hashMap;
                }
            };
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for MyPackage$$ReactModuleInfoProvider", e);
        }
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
