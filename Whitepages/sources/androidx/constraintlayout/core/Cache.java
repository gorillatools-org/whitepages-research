package androidx.constraintlayout.core;

public class Cache {
    Pools$Pool arrayRowPool = new Pools$SimplePool(256);
    SolverVariable[] mIndexedVariables = new SolverVariable[32];
    Pools$Pool optimizedArrayRowPool = new Pools$SimplePool(256);
    Pools$Pool solverVariablePool = new Pools$SimplePool(256);
}
