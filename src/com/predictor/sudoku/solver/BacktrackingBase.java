package com.predictor.sudoku.solver;

public abstract class BacktrackingBase {

    protected boolean TryFindSolution() {
        while (true) {
            if (TryExecuteStep()) {
                if (IsProblemSolved()) {
                    return true;
                }
                if (TryMoveNext()) continue;
            }
            if (!TryMoveBack()) {
                return false;
            }
        }
    }

    protected abstract boolean TryMoveNext();

    protected abstract boolean TryExecuteStep();

    protected abstract boolean IsProblemSolved();

    protected abstract boolean TryMoveBack();

}
