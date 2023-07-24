class Celula {
    public int num;
    public Celula dir, esq, sup, inf;

    public Celula() {
        this.num = -1;
    }
    
    public Celula (int num) {
        this.num = num;
        this.dir = null;
        this.esq = null;
        this.sup = null;
        this.inf = null;
    }
}