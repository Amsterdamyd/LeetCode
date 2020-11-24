package com.yangdi.leetcode.recursion;

final class RowCol {
    private int row, col;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode() {
        int result = (int) (row ^ (row >>> 32));
        return (result << 5) - 1 + (int) (col ^ (col >>> 32)); // 31 * result == (result << 5) - 1
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        RowCol rowCol = (RowCol) o;
        return row == rowCol.row && col == rowCol.col;
    }
}
