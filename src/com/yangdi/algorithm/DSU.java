package com.yangdi.algorithm;

import java.util.Arrays;

/**
 * Disjoint Set (Or Union-Find)
 * basic implementation with path compression
 */
public class DSU {
    int[] parent;

    public DSU(int N) {
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x); // find root
        int rootY = find(y); // find root

        if (rootX != rootY) {
            parent[find(x)] = find(y); // connect roots together(ignore which is parent or child)
        }
    }
}

/**
 * Union-Find improved with size / weight
 */
class DSU2 {
    int[] parent;
    int[] size;

    public DSU2(int N) {
        parent = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        Arrays.fill(size, 1);
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (size[rootX] <= size[rootY]) { // connect roots based on their size, small one will connect on the big one
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}

/**
 * Union-Find improved with rank
 */
class DSU3 {
    int[] parent;
    int[] rank;

    public DSU3(int N) {
        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        Arrays.fill(rank, 1);
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) { // connect roots based on their rank
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++; // maintain rank
        }
    }
}