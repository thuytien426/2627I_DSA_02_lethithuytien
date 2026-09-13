package princeton.cs.algs4;

public class QuickFindUF {
    public int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int p) {
        if (p == id[p]) {
            return p;
        }
        return find(id[p]);
    }

    public void union(int p, int q) {
        int a = find(p);;
        int b = find(q);
        if (a != b) {
            id[a] = b;
        }
    }
}
