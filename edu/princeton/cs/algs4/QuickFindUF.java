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
        return id[p];
    }
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        for (int i = 0; i<id.length; i++) {
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }
}
