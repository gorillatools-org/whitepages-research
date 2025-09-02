package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    private static int POOL_SIZE = 1000;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    private int TABLE_SIZE;
    public boolean graphOptimizer;
    public boolean hasSimpleDefinition;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private Row mTempGoal;
    private HashMap mVariables;
    int mVariablesID;
    public boolean newgraphOptimizer;

    interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    public static Metrics getMetrics() {
        return null;
    }

    public void fillMetrics(Metrics metrics) {
    }

    class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.hasSimpleDefinition = false;
        this.mVariablesID = 0;
        this.mVariables = null;
        this.TABLE_SIZE = 32;
        this.mMaxColumns = 32;
        this.mRows = null;
        this.graphOptimizer = false;
        this.newgraphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[32];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = 32;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    private void increaseTableSize() {
        int i = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
        int i2 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i2];
        this.mMaxColumns = i2;
        this.mMaxRows = i2;
    }

    private void releaseRows() {
        int i = 0;
        if (OPTIMIZED_ENGINE) {
            while (i < this.mNumRows) {
                ArrayRow arrayRow = this.mRows[i];
                if (arrayRow != null) {
                    this.mCache.optimizedArrayRowPool.release(arrayRow);
                }
                this.mRows[i] = null;
                i++;
            }
            return;
        }
        while (i < this.mNumRows) {
            ArrayRow arrayRow2 = this.mRows[i];
            if (arrayRow2 != null) {
                this.mCache.arrayRowPool.release(arrayRow2);
            }
            this.mRows[i] = null;
            i++;
        }
    }

    public void reset() {
        Cache cache;
        int i = 0;
        while (true) {
            cache = this.mCache;
            SolverVariable[] solverVariableArr = cache.mIndexedVariables;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i++;
        }
        cache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        HashMap hashMap = this.mVariables;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow != null) {
                arrayRow.used = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i = solverVariable.id;
            if (i == -1 || i > this.mVariablesID || this.mCache.mIndexedVariables[i] == null) {
                if (i != -1) {
                    solverVariable.reset();
                }
                int i2 = this.mVariablesID + 1;
                this.mVariablesID = i2;
                this.mNumColumns++;
                solverVariable.id = i2;
                solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[i2] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow;
        if (OPTIMIZED_ENGINE) {
            arrayRow = (ArrayRow) this.mCache.optimizedArrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                arrayRow.reset();
            }
        } else {
            arrayRow = (ArrayRow) this.mCache.arrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                arrayRow.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return arrayRow;
    }

    public SolverVariable createSlackVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable.id = i;
        this.mCache.mIndexedVariables[i] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable.id = i;
        this.mCache.mIndexedVariables[i] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    /* access modifiers changed from: package-private */
    public void addSingleError(ArrayRow arrayRow, int i, int i2) {
        arrayRow.addSingleError(createErrorVariable(i2, (String) null), i);
    }

    public SolverVariable createErrorVariable(int i, String str) {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable.id = i2;
        acquireSolverVariable.strength = i;
        this.mCache.mIndexedVariables[i2] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariable = (SolverVariable) this.mCache.solverVariablePool.acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(type, str);
            solverVariable.setType(type, str);
        } else {
            solverVariable.reset();
            solverVariable.setType(type, str);
        }
        int i = this.mPoolVariablesCount;
        int i2 = POOL_SIZE;
        if (i >= i2) {
            int i3 = i2 * 2;
            POOL_SIZE = i3;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i3);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i4 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i4 + 1;
        solverVariableArr[i4] = solverVariable;
        return solverVariable;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public void minimize() {
        if (this.mGoal.isEmpty()) {
            computeValues();
        } else if (this.graphOptimizer || this.newgraphOptimizer) {
            for (int i = 0; i < this.mNumRows; i++) {
                if (!this.mRows[i].isSimpleDefinition) {
                    minimizeGoal(this.mGoal);
                    return;
                }
            }
            computeValues();
        } else {
            minimizeGoal(this.mGoal);
        }
    }

    /* access modifiers changed from: package-private */
    public void minimizeGoal(Row row) {
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addConstraint(androidx.constraintlayout.core.ArrayRow r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r5.mNumRows
            r1 = 1
            int r0 = r0 + r1
            int r2 = r5.mMaxRows
            if (r0 >= r2) goto L_0x0012
            int r0 = r5.mNumColumns
            int r0 = r0 + r1
            int r2 = r5.mMaxColumns
            if (r0 < r2) goto L_0x0015
        L_0x0012:
            r5.increaseTableSize()
        L_0x0015:
            boolean r0 = r6.isSimpleDefinition
            r2 = 0
            if (r0 != 0) goto L_0x0084
            r6.updateFromSystem(r5)
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x0024
            return
        L_0x0024:
            r6.ensurePositiveConstant()
            boolean r0 = r6.chooseSubject(r5)
            if (r0 == 0) goto L_0x007b
            androidx.constraintlayout.core.SolverVariable r0 = r5.createExtraVariable()
            r6.variable = r0
            int r3 = r5.mNumRows
            r5.addRow(r6)
            int r4 = r5.mNumRows
            int r3 = r3 + r1
            if (r4 != r3) goto L_0x007b
            androidx.constraintlayout.core.LinearSystem$Row r2 = r5.mTempGoal
            r2.initFromRow(r6)
            androidx.constraintlayout.core.LinearSystem$Row r2 = r5.mTempGoal
            r5.optimize(r2, r1)
            int r2 = r0.definitionId
            r3 = -1
            if (r2 != r3) goto L_0x007c
            androidx.constraintlayout.core.SolverVariable r2 = r6.variable
            if (r2 != r0) goto L_0x0059
            androidx.constraintlayout.core.SolverVariable r0 = r6.pickPivot(r0)
            if (r0 == 0) goto L_0x0059
            r6.pivot(r0)
        L_0x0059:
            boolean r0 = r6.isSimpleDefinition
            if (r0 != 0) goto L_0x0062
            androidx.constraintlayout.core.SolverVariable r0 = r6.variable
            r0.updateReferencesWithNewDefinition(r5, r6)
        L_0x0062:
            boolean r0 = OPTIMIZED_ENGINE
            if (r0 == 0) goto L_0x006e
            androidx.constraintlayout.core.Cache r0 = r5.mCache
            androidx.constraintlayout.core.Pools$Pool r0 = r0.optimizedArrayRowPool
            r0.release(r6)
            goto L_0x0075
        L_0x006e:
            androidx.constraintlayout.core.Cache r0 = r5.mCache
            androidx.constraintlayout.core.Pools$Pool r0 = r0.arrayRowPool
            r0.release(r6)
        L_0x0075:
            int r0 = r5.mNumRows
            int r0 = r0 - r1
            r5.mNumRows = r0
            goto L_0x007c
        L_0x007b:
            r1 = r2
        L_0x007c:
            boolean r0 = r6.hasKeyVariable()
            if (r0 != 0) goto L_0x0083
            return
        L_0x0083:
            r2 = r1
        L_0x0084:
            if (r2 != 0) goto L_0x0089
            r5.addRow(r6)
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addConstraint(androidx.constraintlayout.core.ArrayRow):void");
    }

    private final void addRow(ArrayRow arrayRow) {
        int i;
        if (!SIMPLIFY_SYNONYMS || !arrayRow.isSimpleDefinition) {
            ArrayRow[] arrayRowArr = this.mRows;
            int i2 = this.mNumRows;
            arrayRowArr[i2] = arrayRow;
            SolverVariable solverVariable = arrayRow.variable;
            solverVariable.definitionId = i2;
            this.mNumRows = i2 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        } else {
            arrayRow.variable.setFinalValue(this, arrayRow.constantValue);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i3 = 0;
            while (i3 < this.mNumRows) {
                if (this.mRows[i3] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow2 = this.mRows[i3];
                if (arrayRow2 != null && arrayRow2.isSimpleDefinition) {
                    arrayRow2.variable.setFinalValue(this, arrayRow2.constantValue);
                    if (OPTIMIZED_ENGINE) {
                        this.mCache.optimizedArrayRowPool.release(arrayRow2);
                    } else {
                        this.mCache.arrayRowPool.release(arrayRow2);
                    }
                    this.mRows[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.mNumRows;
                        if (i4 >= i) {
                            break;
                        }
                        ArrayRow[] arrayRowArr2 = this.mRows;
                        int i6 = i4 - 1;
                        ArrayRow arrayRow3 = arrayRowArr2[i4];
                        arrayRowArr2[i6] = arrayRow3;
                        SolverVariable solverVariable2 = arrayRow3.variable;
                        if (solverVariable2.definitionId == i4) {
                            solverVariable2.definitionId = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.mRows[i5] = null;
                    }
                    this.mNumRows = i - 1;
                    i3--;
                }
                i3++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private final int optimize(Row row, boolean z) {
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            i2++;
            if (i2 >= this.mNumColumns * 2) {
                return i2;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i3 = pivotCandidate.id;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (pivotCandidate != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.mNumRows; i5++) {
                    ArrayRow arrayRow = this.mRows[i5];
                    if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.hasVariable(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.constantValue) / f2;
                            if (f3 < f) {
                                i4 = i5;
                                f = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i4];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.variable;
                    solverVariable.definitionId = i4;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z2 = true;
            }
        }
        return i2;
    }

    private int enforceBFS(Row row) {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && arrayRow.constantValue < 0.0f) {
                boolean z = false;
                int i2 = 0;
                while (!z) {
                    i2++;
                    float f = Float.MAX_VALUE;
                    int i3 = 0;
                    int i4 = -1;
                    int i5 = -1;
                    int i6 = 0;
                    while (true) {
                        if (i3 >= this.mNumRows) {
                            break;
                        }
                        ArrayRow arrayRow2 = this.mRows[i3];
                        if (arrayRow2.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow2.isSimpleDefinition && arrayRow2.constantValue < 0.0f) {
                            int i7 = 9;
                            if (SKIP_COLUMNS) {
                                int currentSize = arrayRow2.variables.getCurrentSize();
                                int i8 = 0;
                                while (i8 < currentSize) {
                                    SolverVariable variable = arrayRow2.variables.getVariable(i8);
                                    float f2 = arrayRow2.variables.get(variable);
                                    if (f2 > 0.0f) {
                                        int i9 = 0;
                                        while (i9 < i7) {
                                            float f3 = variable.strengthVector[i9] / f2;
                                            if ((f3 < f && i9 == i6) || i9 > i6) {
                                                i6 = i9;
                                                i5 = variable.id;
                                                i4 = i3;
                                                f = f3;
                                            }
                                            i9++;
                                            i7 = 9;
                                        }
                                    }
                                    i8++;
                                    i7 = 9;
                                }
                            } else {
                                for (int i10 = 1; i10 < this.mNumColumns; i10++) {
                                    SolverVariable solverVariable = this.mCache.mIndexedVariables[i10];
                                    float f4 = arrayRow2.variables.get(solverVariable);
                                    if (f4 > 0.0f) {
                                        for (int i11 = 0; i11 < 9; i11++) {
                                            float f5 = solverVariable.strengthVector[i11] / f4;
                                            if ((f5 < f && i11 == i6) || i11 > i6) {
                                                i4 = i3;
                                                i5 = i10;
                                                i6 = i11;
                                                f = f5;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i3++;
                    }
                    if (i4 != -1) {
                        ArrayRow arrayRow3 = this.mRows[i4];
                        arrayRow3.variable.definitionId = -1;
                        arrayRow3.pivot(this.mCache.mIndexedVariables[i5]);
                        SolverVariable solverVariable2 = arrayRow3.variable;
                        solverVariable2.definitionId = i4;
                        solverVariable2.updateReferencesWithNewDefinition(this, arrayRow3);
                    } else {
                        z = true;
                    }
                    if (i2 > this.mNumColumns / 2) {
                        z = true;
                    }
                }
                return i2;
            }
        }
        return 0;
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        int i4 = i3;
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i4 != 8) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (!USE_BASIC_SYNONYMS || i2 != 8 || !solverVariable2.isFinalValue || solverVariable.definitionId != -1) {
            ArrayRow createRow = createRow();
            createRow.createRowEquals(solverVariable, solverVariable2, i);
            if (i2 != 8) {
                createRow.addError(this, i2);
            }
            addConstraint(createRow);
            return createRow;
        }
        solverVariable.setFinalValue(this, solverVariable2.computedValue + ((float) i));
        return null;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        if (!USE_BASIC_SYNONYMS || solverVariable.definitionId != -1) {
            int i2 = solverVariable.definitionId;
            if (i2 != -1) {
                ArrayRow arrayRow = this.mRows[i2];
                if (arrayRow.isSimpleDefinition) {
                    arrayRow.constantValue = (float) i;
                } else if (arrayRow.variables.getCurrentSize() == 0) {
                    arrayRow.isSimpleDefinition = true;
                    arrayRow.constantValue = (float) i;
                } else {
                    ArrayRow createRow = createRow();
                    createRow.createRowEquals(solverVariable, i);
                    addConstraint(createRow);
                }
            } else {
                ArrayRow createRow2 = createRow();
                createRow2.createRowDefinition(solverVariable, i);
                addConstraint(createRow2);
            }
        } else {
            float f = (float) i;
            solverVariable.setFinalValue(this, f);
            for (int i3 = 0; i3 < this.mVariablesID + 1; i3++) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i3];
                if (solverVariable2 != null && solverVariable2.isSynonym && solverVariable2.synonym == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.synonymDelta + f);
                }
            }
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return linearSystem.createRow().createRowDimensionPercent(solverVariable, solverVariable2, f);
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintWidget constraintWidget3 = constraintWidget;
        ConstraintWidget constraintWidget4 = constraintWidget2;
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget3.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget3.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget3.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget3.getAnchor(type4));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget4.getAnchor(type));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget4.getAnchor(type2));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget4.getAnchor(type3));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget4.getAnchor(type4));
        ArrayRow createRow = createRow();
        double d = (double) f;
        SolverVariable solverVariable = createObjectVariable7;
        double d2 = (double) i;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d) * d2));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        createRow2.createRowWithAngle(createObjectVariable, createObjectVariable3, createObjectVariable5, solverVariable, (float) (Math.cos(d) * d2));
        addConstraint(createRow2);
    }
}
